package di.fa.kakeep.feign.service;

import di.fa.kakeep.feign.client.KeycloakFeignClient;
import di.fa.kakeep.feign.dto.request.KeycloakCreateRootUserRequest;
import di.fa.kakeep.feign.dto.response.KeycloakLoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakService {

    final KeycloakFeignClient keycloakFeignClient;

    @Value("${keycloak.endpoint}")
    String keycloakUrl;

    @Value("${keycloak.realm}")
    String keycloakRealm;

    @Value("${keycloak.client-id}")
    String keycloakClientId;

    @Value("${keycloak.client-secret}")
    String keycloakClientSecret;

    private Keycloak keycloak;
    private Keycloak mockKeycloak;
    private Long lastConnect = 0L;

    private static final String CLIENT_ID = "client_id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String REQUESTED_SUBJECT = "requested_subject";
    private static final String REFRESH_TOKEN = "refresh_token";

    public void getInstance() {
        if (mockKeycloak != null) {
            keycloak = mockKeycloak;
            return;
        }

        var current = System.currentTimeMillis();

        if ((current - lastConnect) > 350000L) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(keycloakUrl)
                    .realm(keycloakRealm)
                    .clientId(keycloakClientId)
                    .clientSecret(keycloakClientSecret)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .build();

            lastConnect = System.currentTimeMillis();
        }
    }

    public String createUser(KeycloakCreateRootUserRequest request, String username, String password, Map<String, List<String>> attribute) {
        this.getInstance();

        var user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(request.getUsername().toLowerCase());
        user.setFirstName(request.getFirstName().toLowerCase());
        user.setLastName(request.getLastName().toLowerCase());
        user.setEmail(request.getEmail().toLowerCase());
        user.setEmailVerified(true);
        user.setAttributes(attribute);

        var client = new ClientRepresentation();

        var r = new RoleRepresentation();
        var c = new ClientRepresentation();
        var g = new GroupRepresentation();
        var cs = new ClientScopeRepresentation();
        var realmResource = keycloak.realm(keycloakRealm);
        var role = realmResource.roles();
        var group = realmResource.groups();
        var clients = realmResource.clients();
        var clientScopes = realmResource.clientScopes();


        var usersResource = realmResource.users();
        var response = usersResource.create(user);
        var userId = CreatedResponseUtil.getCreatedId(response);
        var userResource = usersResource.get(userId);

        this.resetPassword(userResource, password);
        return userId;
    }

    public void updatePassword(String userId, String password) {
        this.getInstance();

        var realmResource = keycloak.realm(keycloakRealm);
        var usersResource = realmResource.users();
        var userResource = usersResource.get(userId);

        this.resetPassword(userResource, password);
    }

    private void resetPassword(UserResource userResource, String password) {
        var passwordCredential = new CredentialRepresentation();
        passwordCredential.setTemporary(false);
        passwordCredential.setType(CredentialRepresentation.PASSWORD);
        passwordCredential.setValue(password);

        userResource.resetPassword(passwordCredential);
    }

    public void logoutUser(String userId) {
        this.getInstance();

        var realmResource = keycloak.realm(keycloakRealm);
        var usersResource = realmResource.users();
        var userResource = usersResource.get(userId);

        userResource.logout();
    }

    public String removeUser(String userId) {
        this.getInstance();
        var realmResource = keycloak.realm(keycloakRealm);
        var usersResource = realmResource.users();
        var userResource = usersResource.get(userId);
        userResource.toRepresentation();
        userResource.remove();
        return "Success";
    }

    public KeycloakLoginResponse loginUsername(String username, String password) {
        var keyCloakRequest = new HashMap<String, String>();
        keyCloakRequest.put(CLIENT_ID, keycloakClientId);
        keyCloakRequest.put(USERNAME, username.toLowerCase());
        keyCloakRequest.put(PASSWORD, password);
        keyCloakRequest.put(GRANT_TYPE, OAuth2Constants.PASSWORD);
        keyCloakRequest.put(CLIENT_SECRET, keycloakClientSecret);

        var response = keycloakFeignClient.login(keycloakRealm, keyCloakRequest);
        return response.getBody();
    }

    public KeycloakLoginResponse loginPinBiometric(String username) {
        var keyCloakRequest = new HashMap<String, String>();
        keyCloakRequest.put(CLIENT_ID, keycloakClientId);
        keyCloakRequest.put(REQUESTED_SUBJECT, username.toLowerCase());
        keyCloakRequest.put(GRANT_TYPE, OAuth2Constants.TOKEN_EXCHANGE_GRANT_TYPE);
        keyCloakRequest.put(CLIENT_SECRET, keycloakClientSecret);

        var response = keycloakFeignClient.login(keycloakRealm, keyCloakRequest);
        return response.getBody();
    }

    public KeycloakLoginResponse getAccessToken(String refreshToken) {
        var keyCloakRequest = new HashMap<String, String>();
        keyCloakRequest.put(CLIENT_ID, keycloakClientId);
        keyCloakRequest.put(REFRESH_TOKEN, refreshToken);
        keyCloakRequest.put(GRANT_TYPE, OAuth2Constants.REFRESH_TOKEN);
        keyCloakRequest.put(CLIENT_SECRET, keycloakClientSecret);

        var response = keycloakFeignClient.login(keycloakRealm, keyCloakRequest);
        return response.getBody();
    }

    public String changePassword(String keyCloakUserId, String newPassword) {
        this.getInstance();

        var realmResource = keycloak.realm(keycloakRealm);
        var usersResource = realmResource.users();
        var userResource = usersResource.get(keyCloakUserId);

        var passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(newPassword);

        userResource.resetPassword(passwordCred);

        return "Success";
    }
}

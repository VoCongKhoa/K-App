package di.fa.kacommon.security;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import di.fa.kacommon.common.Constants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @author
 *
 * CallCredentials implementation, which carries the JWT value that will be propagated to the
 * server in the request metadata with the "Authorization" key and the "Bearer" prefix.
 */
@RequiredArgsConstructor
public class CredentialsHolderProvider extends CallCredentials {

    private static final Logger logger = LoggerFactory.getLogger(CredentialsHolderProvider.class);

    private final CredentialsHolder credentialsHolder;

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    //
    // Follows partial good practices ref at https://datatracker.ietf.org/doc/html/rfc7519
    // Here, we use symmetric signing key to reduce the cost of token signing/verifying
    // The reason is the token intentionally being used by inter-communication channels only
    //
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    @Override
    public void applyRequestMetadata(RequestInfo requestInfo, Executor executor, MetadataApplier applier) {
        final String jwt = CredentialsHolderProvider.asGrpcToken(credentialsHolder);

        executor.execute(() -> {
            try {
                Metadata metadata = new Metadata();
                metadata.put(Constants.AUTHORIZATION_METADATA_KEY, String.format("%s %s", Constants.JWT_BEARER_PREFIX, jwt));
                applier.apply(metadata);
            } catch (Throwable e) {
                logger.error("Cannot apply gRPC request metadata. Exception {} - {}", e.getClass().getSimpleName(), e.getMessage());
                applier.fail(Status.UNAUTHENTICATED.withDescription(e.getMessage()).withCause(e));
            }
        });
    }

    public static CredentialsHolderProvider asGrpcCredentials(CredentialsHolder credentialsHolder) {
        return new CredentialsHolderProvider(credentialsHolder);
    }

    public static String asGrpcToken(CredentialsHolder credentialsHolder) {
        Map<String, Object> additionalClaims = new LinkedHashMap<>();
        // TODO add more Claims
        var dateNow = new Timestamp(System.currentTimeMillis());
        var expiration = new Timestamp(System.currentTimeMillis() + Constants.JWT_EXPIRATION);
        var headers = Map.of(JwsHeader.ALGORITHM, Jwts.SIG.HS256);
        // https://stackoverflow.com/questions/40252903/static-secret-as-byte-key-or-string/40274325#40274325
        byte[] keyBytes = Decoders.BASE64.decode(Constants.JWT_SIGNING_KEY);
        Key secretKey = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.builder()
                .subject("TEST")
                .issuedAt(dateNow)
                .notBefore(dateNow)
                .expiration(expiration)
                .header().add(headers).and()
                .claims(additionalClaims)
                .signWith(secretKey)
                .compact();
    }
}

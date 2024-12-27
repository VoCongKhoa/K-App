package di.fa.kadevtools.core.configs;//package k.sls.kaccountservice.configs;
//
//import org.keycloak.Config;
//import org.keycloak.events.EventListenerProvider;
//import org.keycloak.events.EventListenerProviderFactory;
//import org.keycloak.models.KeycloakSession;
//import org.keycloak.models.KeycloakSessionFactory;
//import org.keycloak.provider.ProviderConfigProperty;
//
//import java.util.List;
//
//public class ExternalDbSyncProviderFactory implements EventListenerProviderFactory {
//
//    private static final String PROVIDER_ID = "k-sls-db-sync-user";
//
//    @Override
//    public EventListenerProvider create(KeycloakSession keycloakSession) {
//        return new ExternalDbSyncProvider();
//    }
//
//    @Override
//    public void init(Config.Scope scope) {
//
//    }
//
//    @Override
//    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
//
//    }
//
//    @Override
//    public void close() {
//
//    }
//
//    @Override
//    public String getId() {
//        return PROVIDER_ID;
//    }
//
//    @Override
//    public int order() {
//        return EventListenerProviderFactory.super.order();
//    }
//
//    @Override
//    public List<ProviderConfigProperty> getConfigMetadata() {
//        return EventListenerProviderFactory.super.getConfigMetadata();
//    }
//}

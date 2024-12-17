package di.fa.kaauth.core.configs;//package k.sls.kaccountservice.configs;
//
//import org.keycloak.events.Event;
//import org.keycloak.events.EventListenerProvider;
//import org.keycloak.events.EventType;
//import org.keycloak.events.admin.AdminEvent;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.text.MessageFormat;
//import java.util.UUID;
//
//public class ExternalDbSyncProvider implements EventListenerProvider {
//
//    private final String REALM_ID = System.getenv("TABUNGAN_REALM_ID");
//    private final String DB_URL = MessageFormat.format("{0}/{1}",
//            System.getenv("JDBC_DB_URL"), System.getenv("TABUNGAN_DB_NAME"));
//    private final String DB_USER = System.getenv("JDBC_DB_USERNAME");
//    private final String DB_PASSWORD = System.getenv("JDBC_DB_PASSWORD");
//    private final String DB_SQL = "INSERT INTO users (user_id, first_name, last_name, email, username) VALUES (?,?,?,?,?)";
//    public ExternalDbSyncProvider() {};
//
//    @Override
//    public void onEvent(Event event) {
//        if (event.getRealmId().equals(REALM_ID) && event.getType() == EventType.REGISTER) {
//            try (Connection conn = DbConnect(); var pstmt = conn.prepareStatement(DB_SQL)) {
//                pstmt.setObject(1, UUID.fromString(event.getUserId()));
//                pstmt.setString(2, event.getDetails().get("first_name"));
//                pstmt.setString(3, event.getDetails().get("last_name"));
//                pstmt.setString(4, event.getDetails().get("email"));
//                pstmt.setString(5, event.getDetails().get("username"));
//
//                int affectedRows = pstmt.executeUpdate();
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    @Override
//    public void onEvent(AdminEvent adminEvent, boolean b) {
//
//    }
//
//    @Override
//    public void close() {
//
//    }
//
//    private Connection DbConnect() {
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return conn;
//    }
//}

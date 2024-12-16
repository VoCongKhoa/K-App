package di.fa.kacommon.common;

import io.grpc.Metadata;
import org.springframework.http.HttpHeaders;

public class Constants {

    public static final Metadata.Key<String> AUTHORIZATION_METADATA_KEY = Metadata.Key.of(HttpHeaders.AUTHORIZATION,
            Metadata.ASCII_STRING_MARSHALLER);

    public static final String JWT_BEARER_PREFIX = "Bearer";

    public static final long JWT_EXPIRATION = 1000 * 60 * 5; // 5 minutes
    public static final String JWT_SIGNING_KEY = "ksls";
}

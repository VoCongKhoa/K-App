package di.fa.kaauth.configs.feign;

import feign.Client;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContexts;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLSocketFactory;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Configuration
public class FeignConfiguration {

    @Bean
    @ConditionalOnExpression("'${ignore.ssl-validation}' == 'true'")
    public Client client() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return new Client.Default(this.getSSLSocketFactory(), new NoopHostnameVerifier());
    }

    private SSLSocketFactory getSSLSocketFactory() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        var acceptTrust = (TrustStrategy) (X509Certificate[] chain, String authType) -> true;
        var sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptTrust).build();
        return sslContext.getSocketFactory();
    }
}

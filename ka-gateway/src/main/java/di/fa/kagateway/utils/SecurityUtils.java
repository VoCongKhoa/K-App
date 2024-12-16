package di.fa.kagateway.utils;

import di.fa.kacommon.security.CredentialsHolder;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static CredentialsHolder getPrincipal() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
            if (principal instanceof CredentialsHolder) {
                return (CredentialsHolder) principal;
            }
        }
        return null;
    }
}

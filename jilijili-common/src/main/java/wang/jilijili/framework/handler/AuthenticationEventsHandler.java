package wang.jilijili.framework.handler;


import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author admin
 */
@Component
public class AuthenticationEventsHandler {

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        Authentication authentication = success.getAuthentication();
        System.out.println("======================onSuccess============================");
        System.out.println("authentication.getName=>" + authentication.getName());
        System.out.println("authentication.getAuthorities=>" + authentication.getAuthorities());
        System.out.println("authentication.getPrincipal=>" + authentication.getPrincipal());
        System.out.println("authentication.getDetails=>" + authentication.getDetails());
        System.out.println("success.getSource=>" + success.getSource());
        System.out.println("======================onSuccess============================");
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        System.out.println("======================onFailure============================");
        System.out.println("AuthenticationEvents.onFailure=>认证错误");
        failures.getException().printStackTrace();
        System.out.println("======================onFailure============================");
    }
}

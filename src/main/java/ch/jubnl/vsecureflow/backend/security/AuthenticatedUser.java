package ch.jubnl.vsecureflow.backend.security;

import ch.jubnl.vsecureflow.backend.dto.SecurityUserDto;
import ch.jubnl.vsecureflow.backend.service.SecurityUserService;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AuthenticatedUser {

    private final SecurityUserService securityUserService;
    private final AuthenticationContext authenticationContext;

    public AuthenticatedUser(AuthenticationContext authenticationContext, SecurityUserService securityUserService) {
        this.securityUserService = securityUserService;
        this.authenticationContext = authenticationContext;
    }

    @Transactional
    public Optional<SecurityUserDto> get() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class)
                .flatMap(userDetails -> securityUserService.findByEmail(userDetails.getUsername()));
    }

    public void logout() {
        authenticationContext.logout();
    }

}

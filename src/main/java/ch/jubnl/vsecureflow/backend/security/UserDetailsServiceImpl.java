package ch.jubnl.vsecureflow.backend.security;

import ch.jubnl.vsecureflow.backend.dto.SecurityUserDto;
import ch.jubnl.vsecureflow.backend.service.SecurityUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SecurityUserService securityUserService;

    public UserDetailsServiceImpl(SecurityUserService userRepository) {
        this.securityUserService = userRepository;
    }

    private static List<GrantedAuthority> getAuthorities(SecurityUserDto user) {
        return user.getGroups().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUserDto user = securityUserService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user present with username: " + username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthorities(user));
    }

}

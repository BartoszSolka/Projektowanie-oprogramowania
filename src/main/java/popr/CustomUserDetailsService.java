package popr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import popr.model.Admin;
import popr.repository.AdminRepository;

import java.util.Arrays;
import java.util.Optional;

@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Optional<Admin> userFromDataBase = adminRepository.findByUsername(username);
        return userFromDataBase
                .map(admin -> new org.springframework.security.core.userdetails.User(admin.getUsername(),
                        admin.getPassword(), true, true, true,
                        true, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))))
                .orElseThrow(() -> new UsernameNotFoundException("UsernameNotFound"));
    }
}


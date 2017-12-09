package popr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import popr.model.Person;
import popr.repository.PersonRepository;

import java.util.Arrays;
import java.util.Optional;

@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Optional<Person> userFromDataBase = Optional.of(personRepository.findByUsername(username));
        return userFromDataBase
                .map(admin -> new org.springframework.security.core.userdetails.User(admin.getUsername(),
                        admin.getPassword(), true, true, true,
                        true, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))))
                .orElseThrow(() -> new UsernameNotFoundException("UsernameNotFound"));
    }
}


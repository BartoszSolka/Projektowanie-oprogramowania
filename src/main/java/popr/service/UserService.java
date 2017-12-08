package popr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import popr.model.Person;
import popr.repository.PersonRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PersonRepository personRepository;

    public Person readCurrent() {
        //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return personRepository.findByUsername("user1");
    }
}

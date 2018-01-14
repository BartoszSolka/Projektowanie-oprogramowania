package popr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import popr.model.Person;
import popr.repository.PersonRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/userInfo")
@RequiredArgsConstructor
public class UserInfoController {

    private final PersonRepository personRepository;



    @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    public Person currentUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Person person = personRepository.findByUsername(authentication.getName());
            return person;
        }
        return null;
    }


}

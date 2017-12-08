package popr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import popr.interfaces.AdminOperationsInterface;
import popr.model.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController implements AdminManager {

    @Autowired
    AdminOperationsInterface adminOperationsService;

    @Override
    @PostMapping (path = "/addProvider")
    public Provider addProvider(@RequestBody Provider provider) {
        System.out.println(provider);
        return adminOperationsService.addProvider(provider);
    }

    @Override
    @PostMapping (path = "/addUser")
    public Person addUser(@RequestBody Person person) {
        return adminOperationsService.addPerson(person);
    }

    @Override
    @PutMapping (path = "/changeStatus/{changeId}")
    public void changeStatusOfChange(@PathVariable Long changeId, Long statusId, String description) {
        //adminOperationsService.changeStatusOfChange(changeId, statusId, description);
    }

    @Override
    @GetMapping (path = "/zones")
    public String getZonesList() {
        return adminOperationsService.getZonesList();
    }

    @Override
    @GetMapping (path = "/notValidatedServiceChanges")
    public String getNotValidatedServiceChanges() {
        return adminOperationsService.getNotValidatedServiceChanges();
    }

    @Override
    @GetMapping (path = "/statusChangeDictionary")
    public String getStatusChangeDictionary() {
        return adminOperationsService.getStatusChangeDictionary();
    }
}

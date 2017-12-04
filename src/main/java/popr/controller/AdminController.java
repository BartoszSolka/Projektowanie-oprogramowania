package popr.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import popr.interfaces.AdminOperationsInterface;
import popr.model.*;

import javax.persistence.Embedded;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    @PostMapping (path = "/addAdmin")
    public Admin addAdmin(@RequestBody Admin admin) {
        return adminOperationsService.addAdmin(admin);
    }

    @Override
    @PostMapping (path = "/addUser")
    public User addUser(@RequestBody User user) {
        return adminOperationsService.addUser(user);
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

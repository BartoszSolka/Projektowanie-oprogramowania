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
/*
        String name = provider.getName();
        String nip = provider.getNip();
        String phoneNo = provider.getPhoneNo();
        String address = provider.getAdress();
        Location location = provider.getLocation();*/
        return adminOperationsService.addProvider(provider);
    }

    @Override
    public Admin addAdmin(String username, String password) {
        return null;
    }

    @Override
    public User addUser(String email, String name, String surname, Zone zone, String phoneNo, String address, String username, String password) {
        return null;
    }

    @Override
    public void changeStatusOfChange(String changeId, String statusId, String description) {

    }

    @Override
    @GetMapping (path = "/zones")
    public String getZonesList() {
        return adminOperationsService.getZonesList();
    }

    @Override
    public String getNotValidatedServiceChanges(Pageable pageable) {
        return null;
    }

    @Override
    public String getStatusChangeDictionary() {
        return null;
    }
}

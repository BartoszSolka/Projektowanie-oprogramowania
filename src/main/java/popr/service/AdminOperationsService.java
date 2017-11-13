package popr.service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import popr.model.Location;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import popr.interfaces.AdminOperationsInterface;
import popr.model.*;
import popr.repository.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminOperationsService implements AdminOperationsInterface {

    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final ServiceChangeRepository serviceChangeRepository;
    private final PasswordEncoder passwordEncoder;
    private final ZoneRepository zoneRepository;
    private final ChangeStatusRepository changeStatusRepository;


    @Override
    public Provider addProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void changeStatusOfChange(String changeId, String statusId, String description) {

    }

    @Override
    public String getZonesList() {
        List<Zone> zones = zoneRepository.findAll();
        String json = new Gson().toJson(zones);
        return json;
    }

    @Override
    public String getNotValidatedServiceChanges() {
        List<ServiceChange> changes = serviceChangeRepository.findByValidatedByIsNull();
        System.out.println(changes);
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(changes);
        return json;
    }

    @Override
    public String getStatusChangeDictionary() {
        List<ChangeStatus> dict = changeStatusRepository.findAll();
        String json = new Gson().toJson(dict);
        return json;
    }

}

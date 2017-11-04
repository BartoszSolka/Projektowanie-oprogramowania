package popr.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import popr.model.Employee;
import popr.model.Location;
import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import popr.interfaces.AdminOperationsInterface;
import popr.model.*;
import popr.repository.AdminRepository;
import popr.repository.ProviderRepository;
import popr.repository.ServiceChangeRepository;
import popr.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AdminOperationsService implements AdminOperationsInterface {

    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final ServiceChangeRepository serviceChangeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Provider addProvider(String nip, String phoneNo, String address, String name, Location location) {
        Provider provider = new Provider();

        provider.setName(name);
        provider.setNip(nip);
        provider.setPhoneNo(phoneNo);
        provider.setAddress(address);
        provider.setLocation(location);
        return providerRepository.save(provider);
    }

    @Override
    public Page<ServiceChange> getNotValidatedServiceChanges(Pageable pageable) {
        return serviceChangeRepository.findByValidatedByIsNull(pageable);
    }

    @Override
    public User addUser(String email, String name, String surname, Zone zone, String phoneNo, String address, String username, String password) {
        User user = new User();

        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setPhoneNo(phoneNo);
        user.setAddress(address);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setZone(zone);
        return userRepository.save(user);

    }

    @Override
    public Admin addAdmin(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        return adminRepository.save(admin);

    }
}

package popr;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import popr.model.*;
import popr.repository.*;

@Component
@RequiredArgsConstructor
public class Bootstrap implements ApplicationRunner {

    private final AdminRepository adminRepository;
    private final ChangeStatusRepository changeStatusRepository;
    private final ComplaintRepository complaintRepository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final ProviderRepository providerRepository;
    private final ProviderStatusRepository providerStatusRepository;
    private final ReportRepository reportRepository;
    private final ServiceChangeRepository serviceChangeRepository;
    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceRepository serviceRepository;
    private final ZoneRepository zoneRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        generate();
    }

    private void generate() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));

        adminRepository.save(admin);

        User user = new User();
        user.setUsername("user1");
        user.setPassword(passwordEncoder.encode("user1"));
        user.setName("Jan");
        user.setSurname("Testowy");
        user.setAddress("Test address");
        user.setEmail("test@test.test");
        user = userRepository.save(user);

        Zone zone = new Zone();
        zone.setPostalCode("01-111");
        zone = zoneRepository.save(zone);

        Provider provider = new Provider();
        provider.setName("Testowy uslugodawca");
        provider.setNip("NIP");
        provider.setAddress("ABC");
        provider.setZone(zone);
        provider.setPhoneNo("+48111222333");
        provider.setActive(true);

        provider = providerRepository.save(provider);

        Employee employee = new Employee();
        employee.setName("Testowy pracownik");
        employee.setProvider(provider);
        employee.setSurname("Testowe imie");

        employeeRepository.save(employee);

        Service service = new Service();
        service.setProvider(provider);
        service.setDescription("Usługa zdejmowania kota z drzewa");
        service.setEstimatedRealisationTime(5);
        service.setPrice(1000);
        service = serviceRepository.save(service);

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setService(service);
        serviceOrder.setDescription("Testowa usługa");
        serviceOrder.setRating(0);
        serviceOrder.setDescription("Super, daję 0");
        serviceOrder.setZone(zone);
        serviceOrder.setOrderedBy(user);
        serviceOrder = serviceOrderRepository.save(serviceOrder);

        Complaint complaint = new Complaint();
        complaint.setCreatedBy(user);
        complaint.setDescription("Zepsuli mi drzewo");
        complaint.setServiceOrder(serviceOrder);
        complaint = complaintRepository.save(complaint);
    }
}

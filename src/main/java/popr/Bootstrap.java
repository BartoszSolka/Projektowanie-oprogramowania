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

    private final ChangeStatusRepository changeStatusRepository;
    private final ComplaintRepository complaintRepository;
    private final PersonRepository personRepository;
    private final ProviderRepository providerRepository;
    private final ProviderStatusRepository providerStatusRepository;
    private final ReportRepository reportRepository;
    private final ServiceChangeRepository serviceChangeRepository;
    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceRepository serviceRepository;
    private final ZoneRepository zoneRepository;
    private final ServiceOrderStatusRepository serviceOrderStatusRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        generate();
    }

    private void generate() {
        Person admin = new Person();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setAdmin(true);

        personRepository.save(admin);

        Person user = new Person();
        user.setUsername("user1");
        user.setPassword(passwordEncoder.encode("user1"));
        user.setName("Jan");
        user.setSurname("Testowy");
        user.setAddress("Test address");
        user.setEmail("test@test.test");
        user = personRepository.save(user);

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

        Person employee = new Person();
        employee.setName("Testowy pracownik");
        employee.setProvider(provider);
        employee.setSurname("Testowe imie");

        personRepository.save(employee);

        Service service = new Service();
        service.setProvider(provider);
        service.setDescription("Usługa zdejmowania kota z drzewa");
        service.setEstimatedRealisationTime(5);
        service.setPrice(1000);
        service = serviceRepository.save(service);

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setService(service);
        serviceOrder.setAddress("Piękna 123");
        serviceOrder.setDescription("Testowa usługa");
        serviceOrder.setRating(0);
        serviceOrder.setRatingDescription("Super, daję 0");
        serviceOrder.setZone(zone);
        serviceOrder.setOrderedBy(user);
        serviceOrder = serviceOrderRepository.save(serviceOrder);

        ServiceOrderStatus serviceOrderStatus = new ServiceOrderStatus();
        serviceOrderStatus.setServiceOrder(serviceOrder);
        serviceOrderStatus = serviceOrderStatusRepository.save(serviceOrderStatus);

        Complaint complaint = new Complaint();
        complaint.setCreatedBy(user);
        complaint.setDescription("Zepsuli mi drzewo");
        complaint.setServiceOrder(serviceOrder);
        complaint = complaintRepository.save(complaint);
    }
}

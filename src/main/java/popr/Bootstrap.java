package popr;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import popr.model.*;
import popr.model.enums.ServiceOrderStatusDict;
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
    private final ServiceTypeRepository serviceTypeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        generate();
    }

    private void generate() {
        Person admin = new Person();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("test"));
        admin.setAdmin(true);

        personRepository.save(admin);

        Person user = new Person();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("test"));
        user.setName("Jan");
        user.setSurname("Testowy");
        user.setAddress("Test address");
        user.setEmail("test@test.test");
        user = personRepository.save(user);

        Zone zone = new Zone();
        zone.setPostalCode("01-111");
        zoneRepository.save(zone);

        Zone zone2 = new Zone();
        zone2.setPostalCode("01-112");
        zoneRepository.save(zone2);

        zone2 = new Zone();
        zone2.setPostalCode("01-113");
        zoneRepository.save(zone2);

        zone2 = new Zone();
        zone2.setPostalCode("01-114");
        zoneRepository.save(zone2);

        zone2 = new Zone();
        zone2.setPostalCode("01-115");
        zoneRepository.save(zone2);

        zone2 = new Zone();
        zone2.setPostalCode("01-116");
        zoneRepository.save(zone2);

        zone2 = new Zone();
        zone2.setPostalCode("01-117");
        zoneRepository.save(zone2);

        zone2 = new Zone();
        zone2.setPostalCode("01-118");
        zoneRepository.save(zone2);

        zone2 = new Zone();
        zone2.setPostalCode("01-119");
        zoneRepository.save(zone2);

        zone2 = new Zone();
        zone2.setPostalCode("01-121");
        zoneRepository.save(zone2);

        zone2 = new Zone();
        zone2.setPostalCode("01-122");
        zoneRepository.save(zone2);

        Provider provider = new Provider();
        provider.setName("Testowy uslugodawca");
        provider.setNip("NIP");
        provider.setAddress("ABC");
        provider.setZone(zone);
        provider.setPhoneNo("+48111222333");
        provider.setActive(true);
        provider.setEmail("nowak9185@gmail.com");

        provider = providerRepository.save(provider);

        Person employee = new Person();
        employee.setName("Testowy pracownik");
        employee.setUsername("employee");
        employee.setPassword(passwordEncoder.encode("test"));
        employee.setProvider(provider);
        employee.setSurname("Testowe imie");

        Person employee2 = new Person();
        employee2.setName("Testowy pracownik2");
        employee2.setUsername("employee2");
        employee2.setPassword(passwordEncoder.encode("test"));
        employee2.setProvider(provider);
        employee2.setSurname("Testowe imie2");

        personRepository.save(employee);
        personRepository.save(employee2);

        ServiceType serviceType = new ServiceType();
        serviceType.setDescription("Testowy opis typu usługi");
        serviceType.setName("Tesowa nazwa usługi");
        serviceType = serviceTypeRepository.save(serviceType);

        Service service = new Service();
        service.setTitle("Testowy opis usługi 1");
        service.setProvider(provider);
        service.setDescription("Usługa zdejmowania kota z drzewa");
        service.setEstimatedRealisationTime(5);
        service.setPrice(1000);
        service.setServiceType(serviceType);
        service = serviceRepository.save(service);

        Service service2 = new Service();
        service2.setTitle("Testowy opis usługi 2");
        service2.setProvider(provider);
        service2.setDescription("Usługa ścinania drzewa");
        service2.setEstimatedRealisationTime(50);
        service2.setPrice(500);
        service2.setServiceType(serviceType);
        service2 = serviceRepository.save(service2);

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setService(service);
        serviceOrder.setAddress("Piękna 123");
        serviceOrder.setDescription("Testowa usługa");
        serviceOrder.setRating(0);
        serviceOrder.setRatingDescription("Super, daję 0");
        serviceOrder.setZone(zone);
        serviceOrder.setOrderedBy(user);
        serviceOrder.setProvider(provider);
        serviceOrder = serviceOrderRepository.save(serviceOrder);

        ServiceOrderStatus serviceOrderStatus = new ServiceOrderStatus();
        serviceOrderStatus.setOrderStatusDict(ServiceOrderStatusDict.NEW);
        serviceOrderStatus.setServiceOrder(serviceOrder);
        serviceOrderStatus = serviceOrderStatusRepository.save(serviceOrderStatus);

        ServiceOrder serviceOrder2 = new ServiceOrder();
        serviceOrder2.setService(service2);
        serviceOrder2.setAddress("Piękna 12");
        serviceOrder2.setDescription("Testowa usługa2");
        serviceOrder2.setRating(5);
        serviceOrder2.setRatingDescription("Super, daję 5/5");
        serviceOrder2.setZone(zone);
        serviceOrder2.setOrderedBy(user);
        serviceOrder2.setProvider(provider);
        serviceOrder2 = serviceOrderRepository.save(serviceOrder2);

        ServiceOrderStatus serviceOrderStatus2 = new ServiceOrderStatus();
        serviceOrderStatus2.setServiceOrder(serviceOrder2);
        serviceOrderStatus2.setOrderStatusDict(ServiceOrderStatusDict.COMPLETED);
        serviceOrderStatus2 = serviceOrderStatusRepository.save(serviceOrderStatus2);

        Complaint complaint = new Complaint();
        complaint.setCreatedBy(user);
        complaint.setDescription("Zepsuli mi drzewo");
        complaint.setServiceOrder(serviceOrder);
        complaint = complaintRepository.save(complaint);
    }
}

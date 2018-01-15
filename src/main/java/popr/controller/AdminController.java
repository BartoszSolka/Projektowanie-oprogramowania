package popr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import popr.interfaces.AdminOperationsInterface;
import popr.model.*;
import popr.repository.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController implements AdminManager {

    @Autowired
    AdminOperationsInterface adminOperationsService;
    @Autowired
    ZoneRepository zoneRepository;
    @Autowired
    ServiceTypeRepository serviceTypeRepository;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    ServiceRepository serviceRepository;

    @Override
    @PostMapping (path = "/addProvider")
    public void addProvider(String nip, String telefon, String siedzibaFirmy, String nazwa, String kodStrefy, String email) {
        Provider provider = new Provider();
        provider.setNip(nip);
        provider.setPhoneNo(telefon);
        provider.setAddress(siedzibaFirmy);
        provider.setName(nazwa);
        provider.setEmail(email);
        Zone zone = zoneRepository.findByPostalCode(kodStrefy);
        if (zone != null) {
            provider.setZone(zone);
        } else {
            zone = new Zone();
            zone.setPostalCode(kodStrefy);
            zoneRepository.save(zone);
            provider.setZone(zone);
        }

        adminOperationsService.addProvider(provider);
    }

    @Override
    @PostMapping ("/editProvider/{idUslugodawcy}")
    public void editProvider(@PathVariable String idUslugodawcy, String nip, String telefon, String siedzibaFirmy, String nazwa, String kodStrefy, String email) {
        Provider provider = new Provider();
        provider.setId(Long.parseLong(idUslugodawcy));
        provider.setNip(nip);
        provider.setPhoneNo(telefon);
        provider.setAddress(siedzibaFirmy);
        provider.setName(nazwa);
        provider.setEmail(email);
        Zone zone = zoneRepository.findByPostalCode(kodStrefy);
        if (zone != null) {
            provider.setZone(zone);
        } else {
            zone = new Zone();
            zone.setPostalCode(kodStrefy);
            zoneRepository.save(zone);
            provider.setZone(zone);
        }

        adminOperationsService.editProvider(provider);
    }

    @Override
    @DeleteMapping("/deleteProvider/{providerId}")
    public void deleteProvider(@PathVariable String providerId) {
        adminOperationsService.deleteProvider(providerId);
    }

    @Override
    @PostMapping ("/addService")
    public void addService(String idUslugodawcy, String cena, String estymowanyCzas, String idRodzajuUslugi) {
        Service service = new Service();
        ServiceType serviceType = serviceTypeRepository.findById(Long.parseLong(idRodzajuUslugi));
        service.setServiceType(serviceType);
        service.setPrice(Integer.parseInt(cena));
        service.setEstimatedRealisationTime(Integer.parseInt(estymowanyCzas));
        Provider provider = providerRepository.findById(Long.parseLong(idUslugodawcy));
        service.setProvider(provider);

        adminOperationsService.addService(service);
    }

    @Override
    @PostMapping ("/editService/{idUslugi}")
    public void editService(String opis, String cena, String estymowanyCzas, String idRodzajuUslugi, @PathVariable String idUslugi) {
        Service service = serviceRepository.findById(Long.parseLong(idUslugi));
        ServiceType serviceType = serviceTypeRepository.findById(Long.parseLong(idRodzajuUslugi));
        service.setServiceType(serviceType);
        service.setPrice(Integer.parseInt(cena));
        service.setEstimatedRealisationTime(Integer.parseInt(estymowanyCzas));
        service.setDescription(opis);

        adminOperationsService.addService(service);
    }

    @Override
    @DeleteMapping ("/deleteService/{serviceId}")
    public void deleteService(@PathVariable String serviceId) {
        adminOperationsService.deleteService(serviceId);
    }

    @Override
    @PostMapping ("/addPerson")
    public void addPerson(String email, String imie, String nazwisko, String kodStrefy, String telefon, String adres, String login, String haslo, boolean czyAdmin, String idUslugodawcy) {
        Person person = new Person();
        person.setEmail(email);
        person.setName(imie);
        person.setSurname(nazwisko);
        person.setPhoneNo(telefon);
        person.setAddress(adres);
        person.setUsername(login);
        person.setPassword(haslo);
        Zone zone = zoneRepository.findByPostalCode(kodStrefy);
        if (zone != null) {
            person.setZone(zone);
        } else {
            zone = new Zone();
            zone.setPostalCode(kodStrefy);
            zoneRepository.save(zone);
            person.setZone(zone);
        }
        Provider provider = providerRepository.findById(Long.parseLong(idUslugodawcy));
        person.setProvider(provider);
        person.setAdmin(czyAdmin);

        adminOperationsService.addPerson(person);
    }

    @Override
    @PostMapping ("/editPerson/{idOsoby}")
    public void editPerson(@PathVariable String idOsoby, String email, String imie, String nazwisko, String kodStrefy, String telefon, String adres, String login, String haslo, boolean czyAdmin, String idUslugodawcy) {
        Person person = new Person();
        person.setId(Long.parseLong(idOsoby));
        person.setEmail(email);
        person.setName(imie);
        person.setSurname(nazwisko);
        person.setPhoneNo(telefon);
        person.setAddress(adres);
        person.setUsername(login);
        person.setPassword(haslo);
        Zone zone = zoneRepository.findByPostalCode(kodStrefy);
        if (zone != null) {
            person.setZone(zone);
        } else {
            zone = new Zone();
            zone.setPostalCode(kodStrefy);
            zoneRepository.save(zone);
            person.setZone(zone);
        }
        Provider provider = providerRepository.findById(Long.parseLong(idUslugodawcy));
        person.setProvider(provider);
        person.setAdmin(czyAdmin);

        adminOperationsService.addPerson(person);
    }

    @Override
    @DeleteMapping ("/deletePerson/{personId}")
    public void deletePerson(@PathVariable String personId) {
        adminOperationsService.deletePerson(personId);
    }

    @Override
    @GetMapping ("/allServiceChanges")
    public List<ServiceChange> getAllServiceChanges() {
        return adminOperationsService.getAllServiceChanges();
    }

    @Override
    @GetMapping ("/statusChangeDictionary")
    public List<ChangeStatus> getStatusChangeDictionary() {
        return adminOperationsService.getStatusChangeDictionary();
    }

    @Override
    @GetMapping ("/zones")
    public List<Zone> getZonesList() {
        return adminOperationsService.getZonesList();
    }

    @Override
    @GetMapping ("/providers")
    public List<Provider> getProvidersList() {
        return adminOperationsService.getProvidersList();
    }

    @Override
    @PutMapping ("/changeStatus/{changeId}")
    public void changeStatusOfChange(@PathVariable String changeId, String statusId, String description) {
        adminOperationsService.changeStatusOfChange(changeId, statusId, description);
    }



    @Override
    @GetMapping("/users")
    public List<Person> getUsers() {
        return adminOperationsService.getUsers();
    }

    @Override
    @GetMapping("/user/{userId}")
    public Person getUser(@PathVariable Long userId) {
        return adminOperationsService.getUserById(userId);
    }

    @Override
    @GetMapping("/provider/{providerId}")
    public Provider getProvider(@PathVariable Long providerId) {
        return adminOperationsService.getProviderById(providerId);
    }

    @Override
    @GetMapping("/zone/{zoneId}")
    public String getPostalCode(@PathVariable Long zoneId) {
        return adminOperationsService.getPostalCodeOfZone(zoneId);
    }

    @Override
    @GetMapping("/zoneid/{postalCode}")
    public Long getPostalCodeId(@PathVariable String postalCode) {
        return adminOperationsService.getIdOfPostalCode(postalCode);
    }

    //iteracja 3. -->
    @Override
    @PostMapping("/report")
    public List<ServiceOrder> generateReport(@DateTimeFormat(pattern = "ddMMyyyy") Date poczatek, @DateTimeFormat(pattern = "ddMMyyyy") Date koniec) {
        return adminOperationsService.generateReport(poczatek, koniec);
    }

    @Override
    @PostMapping("/calculations")
    public Map<Long, Integer> generateCalculations(@DateTimeFormat(pattern = "ddMMyyyy") Date poczatek, @DateTimeFormat(pattern = "ddMMyyyy") Date koniec) {
        return adminOperationsService.generateCalculations(poczatek, koniec);
    }
    //iteracja 3. <--

}

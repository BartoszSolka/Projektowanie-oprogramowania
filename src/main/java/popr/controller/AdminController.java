package popr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import popr.interfaces.AdminOperationsInterface;
import popr.model.*;
import popr.repository.ProviderRepository;
import popr.repository.ServiceTypeRepository;
import popr.repository.ZoneRepository;

import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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

    @Override
    @PostMapping (path = "/addProvider")
    public void addProvider(String nip, String telefon, String siedzibaFirmy, String nazwa, String idStrefy, String email) {
        Provider provider = new Provider();
        provider.setNip(nip);
        provider.setPhoneNo(telefon);
        provider.setAddress(siedzibaFirmy);
        provider.setName(nazwa);
        provider.setEmail(email);
        Zone zone = zoneRepository.findById(Long.parseLong(idStrefy));
        provider.setZone(zone);

        adminOperationsService.addProvider(provider);
    }

    @Override
    @PostMapping ("/editProvider/{idUslugodawcy}")
    public void editProvider(@PathVariable String idUslugodawcy, String nip, String telefon, String siedzibaFirmy, String nazwa, String idStrefy, String email) {
        Provider provider = new Provider();
        provider.setId(Long.parseLong(idUslugodawcy));
        provider.setNip(nip);
        provider.setPhoneNo(telefon);
        provider.setAddress(siedzibaFirmy);
        provider.setName(nazwa);
        provider.setEmail(email);
        Zone zone = zoneRepository.findById(Long.parseLong(idStrefy));
        provider.setZone(zone);

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
    public void editService(String idUslugodawcy, String cena, String estymowanyCzas, String idRodzajuUslugi, @PathVariable String idUslugi) {
        Service service = new Service();
        service.setId(Long.parseLong(idUslugi));
        ServiceType serviceType = serviceTypeRepository.findById(Long.parseLong(idRodzajuUslugi));
        service.setServiceType(serviceType);
        service.setPrice(Integer.parseInt(cena));
        service.setEstimatedRealisationTime(Integer.parseInt(estymowanyCzas));
        Provider provider = providerRepository.findById(Long.parseLong(idUslugodawcy));
        service.setProvider(provider);

        adminOperationsService.addService(service);
    }

    @Override
    @DeleteMapping ("/deleteService/{serviceId}")
    public void deleteService(@PathVariable String serviceId) {
        adminOperationsService.deleteService(serviceId);
    }

    @Override
    @PostMapping ("/addPerson")
    public void addPerson(String email, String imie, String nazwisko, String strefa, String telefon, String adres, String login, String haslo, boolean czyAdmin, String idUslugodawcy) {
        Person person = new Person();
        person.setEmail(email);
        person.setName(imie);
        person.setSurname(nazwisko);
        person.setPhoneNo(telefon);
        person.setAddress(adres);
        person.setUsername(login);
        person.setPassword(haslo);
        Zone zone = zoneRepository.findById(Long.parseLong(strefa));
        person.setZone(zone);
        Provider provider = providerRepository.findById(Long.parseLong(idUslugodawcy));
        person.setProvider(provider);
        person.setAdmin(czyAdmin);

        adminOperationsService.addPerson(person);
    }

    @Override
    @PostMapping ("/editPerson/{idOsoby}")
    public void editPerson(@PathVariable String idOsoby, String email, String imie, String nazwisko, String strefa, String telefon, String adres, String login, String haslo, boolean czyAdmin, String idUslugodawcy) {
        Person person = new Person();
        person.setId(Long.parseLong(idOsoby));
        person.setEmail(email);
        person.setName(imie);
        person.setSurname(nazwisko);
        person.setPhoneNo(telefon);
        person.setAddress(adres);
        person.setUsername(login);
        person.setPassword(haslo);
        Zone zone = zoneRepository.findById(Long.parseLong(strefa));
        person.setZone(zone);
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
    @GetMapping ("/notValidatedServiceChanges")
    public List<ServiceChange> getNotValidatedServiceChanges() {
        return adminOperationsService.getNotValidatedServiceChanges();
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

    //iteracja 3. -->
    @Override
    public void generateReport(Date begin, Date end) {

    }

    @Override
    public void generateCalculations(Date begin, Date end) {

    }

    //iteracja 3. <--


    @Override
    @GetMapping("/users")
    public List<Person> getUsers() {
        return adminOperationsService.getUsers();
    }
}

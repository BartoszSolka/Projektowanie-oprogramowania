package popr.interfaces;

import popr.model.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AdminOperationsInterface {

    void addProvider(Provider provider);
    void deleteProvider(String providerId);
    void editProvider(Provider provider);

    List<ServiceChange> getNotValidatedServiceChanges();
    List<ChangeStatus> getStatusChangeDictionary();
    void changeStatusOfChange(String changeId, String statusId, String description);
    void editService(Service service);
    void deleteService(String serviceId);
    void addService(Service service);

    List<ServiceOrder> generateReport(Date poczatek, Date koniec);
    Map<Long, Integer> generateCalculations(Date begin, Date end);

    void addPerson(Person person);
    void deletePerson(String personId);
    void editPerson(Person person);
    List<Zone> getZonesList();
    List<Provider> getProvidersList();

    List<Person> getUsers();

    Person getUserById(Long userId);

    Provider getProviderById(Long providerId);

    String getPostalCodeOfZone(Long zoneId);
}


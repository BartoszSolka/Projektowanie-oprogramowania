package popr.interfaces;

import popr.model.*;

import java.util.Date;
import java.util.List;

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

    void generateReport(Date begin, Date end);
    void generateCalculations(Date begin, Date end);

    void addPerson(Person person);
    void deletePerson(String personId);
    void editPerson(Person person);
    List<Zone> getZonesList();
    List<Provider> getProvidersList();

    List<Person> getUsers();
}


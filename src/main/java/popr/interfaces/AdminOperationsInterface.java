package popr.interfaces;

import popr.model.Person;
import popr.model.Provider;

public interface AdminOperationsInterface {

    Provider addProvider(Provider provider);
    Person addPerson(Person person);
    void changeStatusOfChange(Long changeId, Long statusId, String description);

    String getZonesList();
    String getNotValidatedServiceChanges();
    String getStatusChangeDictionary();

}

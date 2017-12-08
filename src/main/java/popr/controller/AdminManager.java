package popr.controller;

import popr.model.Person;
import popr.model.Provider;

public interface AdminManager {

    Provider addProvider(Provider provider);
    Person addUser(Person person);
    void changeStatusOfChange(Long changeId, Long statusId, String description);

    String getZonesList();
    String getNotValidatedServiceChanges();
    String getStatusChangeDictionary();

}

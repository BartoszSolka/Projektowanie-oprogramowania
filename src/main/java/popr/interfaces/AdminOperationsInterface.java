package popr.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;

public interface AdminOperationsInterface {

    Provider addProvider(Provider provider);
    Admin addAdmin(Admin admin);
    User addUser(User user);
    void changeStatusOfChange(String changeId, String statusId, String description);

    String getZonesList();
    String getNotValidatedServiceChanges();
    String getStatusChangeDictionary();

}

package popr.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import popr.interfaces.AdminOperationsInterface;
import popr.model.*;

@Service
public class AdminOperationsService implements AdminOperationsInterface {

    @Override
    public Provider addProvider(String nip, String phoneNo, String address, String name, Location location) {
        return null;
    }

    @Override
    public Page<ServiceChange> getNotValidatedServiceChanges(Pageable pageable) {
        return null;
    }

    @Override
    public User addUser(String email, String name, String surname, Zone zone, String phoneNo, String address, String login, String password) {
        return null;
    }

    @Override
    public Admin addAdmin(String username, String password) {
        return null;
    }

    @Override
    public Provider addProvider(String name, String nip, String phoneNo, String address, String location) {
        return null;
    }
}

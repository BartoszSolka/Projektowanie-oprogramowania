package popr.service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import popr.interfaces.AdminOperationsInterface;
import popr.model.*;
import popr.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminOperationsService implements AdminOperationsInterface {

    private final ProviderRepository providerRepository;
    private final ServiceChangeRepository serviceChangeRepository;
    private final PasswordEncoder passwordEncoder;
    private final ZoneRepository zoneRepository;
    private final ChangeStatusRepository changeStatusRepository;
    private final ServiceRepository serviceRepository;
    private final ServiceOrderRepository serviceOrderRepository;
    private final ComplaintRepository complaintRepository;
    private final ServiceOrderStatusRepository serviceOrderStatusRepository;
    private final PersonRepository personRepository;

    @Override
    public void addProvider(Provider provider) {
        providerRepository.save(provider);
    }

    @Override
    public void deleteProvider(String providerId) {
        providerRepository.delete(Long.parseLong(providerId));
    }

    @Override
    public void editProvider(Provider provider) {
        providerRepository.save(provider);
    }

    @Override
    public List<ServiceChange> getNotValidatedServiceChanges() {
        return serviceChangeRepository.findByValidatedByIsNull();
    }

    @Override
    public List<ChangeStatus> getStatusChangeDictionary() {
        return changeStatusRepository.findAll();
    }

    @Override
    public void changeStatusOfChange(String changeId, String statusId, String description) {
        serviceChangeRepository.validateChangeById(Long.parseLong(changeId));

        ChangeStatus changeStatus = new ChangeStatus();
        changeStatus.setComment(description);
        ServiceChange serviceChange = serviceChangeRepository.findById(Long.parseLong(changeId));
        changeStatus.setServiceChange(serviceChange);
        changeStatusRepository.save(changeStatus);

        serviceChange.addStatus(changeStatus);
        serviceChangeRepository.save(serviceChange);
    }

    @Override
    public void editService(popr.model.Service service) {
        serviceRepository.save(service);
    }

    @Override
    public void deleteService(String serviceId) {
        List<ServiceOrder> serviceOrders = serviceOrderRepository.findByServiceId(Long.parseLong(serviceId));
        System.out.println(serviceOrders);
        for (ServiceOrder o : serviceOrders) {
            complaintRepository.deleteByServiceOrderId(o.getId());
            serviceOrderStatusRepository.deleteByServiceOrderId(o.getId());
        }
        serviceOrderRepository.deleteByServiceId(Long.parseLong(serviceId));
        serviceRepository.delete(Long.parseLong(serviceId));
    }

    @Override
    public void addService(popr.model.Service service) {
        serviceRepository.save(service);
    }

    @Override
    public void generateReport(Date begin, Date end) {

    }

    @Override
    public void generateCalculations(Date begin, Date end) {

    }

    @Override
    public void addPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deletePerson(String id) {
        personRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public void editPerson(Person person) {
        personRepository.save(person);
    }


    @Override
    public List<Zone> getZonesList() {
        return zoneRepository.findAll();
    }

    @Override
    public List<Provider> getProvidersList() {
        return providerRepository.findAll();
    }

    @Override
    public List<Person> getUsers() {
        return personRepository.findAll();
    }
}

package popr.service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import popr.interfaces.AdminOperationsInterface;
import popr.model.*;
import popr.repository.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@org.springframework.stereotype.Service
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
        Provider provider = providerRepository.findById(Long.parseLong(providerId));
        List<popr.model.Service> services = serviceRepository.findByProviderId(provider.getId());
        if (!services.isEmpty()) {
            for (popr.model.Service s : services) {
                List<ServiceOrder> serviceOrders = serviceOrderRepository.findByServiceId(s.getId());
                for (ServiceOrder so : serviceOrders) {
                    List<Complaint> complaints = complaintRepository.findByServiceOrderId(so.getId());
                    for (Complaint c : complaints) {
                        complaintRepository.delete(c);
                    }
                    List<ServiceOrderStatus> serviceOrderStatuses = serviceOrderStatusRepository.findByServiceOrderId(so.getId());
                    for (ServiceOrderStatus sos : serviceOrderStatuses) {
                        serviceOrderStatusRepository.delete(sos);
                    }
                    serviceOrderRepository.delete(so);
                }
                serviceRepository.delete(s);
            }
        }
        providerRepository.delete(Long.parseLong(providerId));
    }

    @Override
    public void editProvider(Provider provider) {
        providerRepository.save(provider);
    }

    @Override
    public List<ServiceChange> getAllServiceChanges() {
        return serviceChangeRepository.findAll();
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

    @Override
    public Person getUserById(Long userId) {
        return personRepository.findById(userId);
    }

    @Override
    public Provider getProviderById(Long providerId) {
        return providerRepository.findById(providerId);
    }

    @Override
    public String getPostalCodeOfZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId);
        return zone.getPostalCode();
    }

    @Override
    public Long getIdOfPostalCode(String postalCode) {
        Zone zone = zoneRepository.findByPostalCode(postalCode);
        return zone.getId();
    }


    @Override
    public List<ServiceOrder> generateReport(Date begin, Date end) {
        ZonedDateTime b = ZonedDateTime.ofInstant(begin.toInstant(), ZoneId.systemDefault());
        ZonedDateTime e = ZonedDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault());

        List<ServiceOrder> sorders = serviceOrderRepository.findByDateBeginEnd(b, e);
        return sorders;
    }

    @Override
    public Map<Long, Integer> generateCalculations(Date begin, Date end) {
        ZonedDateTime b = ZonedDateTime.ofInstant(begin.toInstant(), ZoneId.systemDefault());
        ZonedDateTime e = ZonedDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault());

        List<ServiceOrder> sorders = serviceOrderRepository.findByDateBeginEnd(b, e);
        Map<Long, Integer> p = new TreeMap<>();
        for (ServiceOrder s : sorders) {
            popr.model.Service ser = serviceRepository.findById(s.getService().getId());
            Integer cost;
            if (p.containsKey(ser.getProvider().getId()))
                cost = p.get(ser.getProvider().getId());
            else
                cost = 0;
            p.put(ser.getProvider().getId(), cost + ser.getPrice());
        }
        return p;
    }
}

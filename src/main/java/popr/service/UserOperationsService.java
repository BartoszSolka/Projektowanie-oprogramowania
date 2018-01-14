package popr.service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.interfaces.UserOperationsInterface;
import popr.model.*;
import popr.model.enums.ServiceOrderStatusDict;
import popr.repository.*;

import java.time.ZonedDateTime;



@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class UserOperationsService implements UserOperationsInterface {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceOrderStatusRepository serviceOrderStatusRepository;
    private final ServiceRepository serviceRepository;
    private final UserService userService;
    private final ZoneRepository zoneRepository;
    private final ProviderRepository providerRepository;
    private final ComplaintRepository complaintRepository;
    private final MailServiceImpl mailService;

    @Override
    public ServiceOrder createServiceOrder(String description, String address, Zone zone, Long serviceId) {
        popr.model.Service service = serviceRepository.findOne(serviceId);
        String providerEmail  = service.getProvider().getEmail();
        String mailContent;
        if (service == null) {
            return null;//handle error
        }

        ServiceOrder serviceOrder = new ServiceOrder();

        serviceOrder.setDescription(description);
        serviceOrder.setCreationDate(ZonedDateTime.now());
        serviceOrder.setAddress(address);
        serviceOrder.setZone(zone);
        serviceOrder.setService(service);
        serviceOrder.setOrderedBy(userService.readCurrent());
        serviceOrder.setProvider(service.getProvider());
        serviceOrder = serviceOrderRepository.save(serviceOrder);

        ServiceOrderStatus serviceOrderStatus = new ServiceOrderStatus();

        serviceOrderStatus.setServiceOrder(serviceOrder);
        serviceOrderStatus.setOrderStatusDict(ServiceOrderStatusDict.NEW);
        serviceOrderStatus = serviceOrderStatusRepository.save(serviceOrderStatus);

        mailContent = ("Przypisano zlecenie" + "\n" + "Opis: " + serviceOrder.getDescription() + "\n" +  " Strefa: " + serviceOrder.getZone().getPostalCode() + "\n" +  " Rodzaj usługi: " +serviceOrder.getService().getDescription() + "\n" +  " Adres: " + serviceOrder.getAddress() );
        mailService.sendEmail(mailContent, providerEmail);
        return serviceOrder;
    }

    @Override
    public Page<Provider> getProviders(Pageable pageable) {
        return providerRepository.findAll(pageable);
    }

    @Override
    public Page<popr.model.Service> getServices(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    @Override
    public Page<ServiceOrder> getServiceOrdersByZone(Zone zone, Pageable pageable) {
        return serviceOrderRepository.findByZone(zone, pageable);
    }

    @Override
    public Page<ServiceOrder> getServiceOrdersByUser(Pageable pageable) {
        Person user = userService.readCurrent();
        return serviceOrderRepository.findByOrderedBy(user, pageable);
    }

    @Override
    public ServiceOrderStatus getServiceOrderStatus(ServiceOrder serviceOrder) {
        return serviceOrderStatusRepository.findByServiceOrderAndCurrentIsTrue(serviceOrder);
    }

    @Override
    public ServiceOrderStatus cancelServiceOrder(Long orderId ) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(orderId);

        ServiceOrderStatus serviceOrderStatus = serviceOrderStatusRepository.findByServiceOrderAndCurrentIsTrue(serviceOrder);
        serviceOrderStatus.setOrderStatusDict(ServiceOrderStatusDict.CANCELED);

        Service service = serviceOrder.getService();
        String mailContent = ("Anulowano zlecenie" + "\n" + "Opis: " + serviceOrder.getDescription() + "\n" +  " Strefa: " + serviceOrder.getZone().getPostalCode() + "\n" +  " Rodzaj usługi: " +serviceOrder.getService().getDescription() + "\n" +  " Adres: " + serviceOrder.getAddress() );
        mailService.sendEmail(mailContent, service.getProvider().getEmail());
        return serviceOrderStatusRepository.save(serviceOrderStatus);
    }

    @Override
    public ServiceOrder editServiceOrder(String description, String postalCode, Long serviceId, Long orderId, String address) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(orderId);
        Service service = serviceRepository.findById(serviceId);
        Zone zone = zoneRepository.findByPostalCode(postalCode);

        if (description != null) {
            serviceOrder.setDescription(description);
        }
        if (address != null) {
            serviceOrder.setAddress(address);
        }
        if (postalCode != null) {
            serviceOrder.setZone(zone);
        }
        if (service != null) {
            serviceOrder.setService(service);
        }

        return serviceOrderRepository.save(serviceOrder);
    }

    @Override
    public ServiceOrder rateServiceOrder(Long orderId, Integer rating, String description) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(orderId);
        serviceOrder.setRating(rating);
        serviceOrder.setRatingDescription(description);
        return serviceOrderRepository.save(serviceOrder);
    }

    @Override
    public ServiceOrder createComplaint(String description, Long orderId){
        ServiceOrder serviceOrder = serviceOrderRepository.findById(orderId);
        ServiceOrder complaintServiceOrder = new ServiceOrder();


        complaintServiceOrder.setDescription("[REKLAMACJA - " + serviceOrder.getDescription() + "] " + description);
        complaintServiceOrder.setCreationDate(ZonedDateTime.now());
        complaintServiceOrder.setAddress(serviceOrder.getAddress());
        complaintServiceOrder.setZone(serviceOrder.getZone());
        complaintServiceOrder.setService(serviceOrder.getService());
        complaintServiceOrder.setOrderedBy(serviceOrder.getOrderedBy());
        complaintServiceOrder.setProvider(serviceOrder.getProvider());
        complaintServiceOrder = serviceOrderRepository.save(complaintServiceOrder);

        ServiceOrderStatus serviceOrderStatus = new ServiceOrderStatus();
        serviceOrderStatus.setServiceOrder(complaintServiceOrder);
        serviceOrderStatus.setOrderStatusDict(ServiceOrderStatusDict.COMPLAIN);
        serviceOrderStatus = serviceOrderStatusRepository.save(serviceOrderStatus);

        return complaintServiceOrder;
    }
}

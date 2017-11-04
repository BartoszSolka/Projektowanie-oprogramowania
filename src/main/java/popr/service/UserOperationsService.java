package popr.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import popr.interfaces.UserOperationsInterface;
import popr.model.Provider;
import popr.model.ServiceOrder;
import popr.model.Zone;
import popr.repository.ServiceOrderRepository;

@Service
public class UserOperationsService implements UserOperationsInterface {

    @Override
    public ServiceOrder createServiceOrder(String description, Zone zone, Long serviceId, Long providerId) {
        return null;
    }

    @Override
    public Page<Provider> getProviders(Pageable pageable) {
        return null;
    }

    @Override
    public Page<popr.model.Service> getServices(Pageable pageable) {
        return null;
    }

    @Override
    public Page<ServiceOrder> getServicesByZone(Zone zone, Pageable pageable) {
        return null;
    }

    @Override
    public Page<ServiceOrder> getServicesByUser(Long userId) {
        return null;
    }
}

package popr.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import popr.interfaces.ProviderOperationsInterface;
import popr.model.enums.ServiceOrderStatusDict;

import java.util.List;

@Service
public class ProviderOperationsService implements ProviderOperationsInterface {

    @Override
    public Page<popr.model.Service> getServicesByProvider(Long providerId) {
        return null;
    }

    @Override
    public popr.model.Service addService(Long providerId, String description, int price, int estimatedRealisationTime, String name) {
        return null;
    }

    @Override
    public popr.model.Service changeServiceStatus(ServiceOrderStatusDict status, String description, Long serviceOrderId) {
        return null;
    }

    @Override
    public List<ServiceOrderStatusDict> getAllServiceOrderStatuses() {
        return null;
    }
}

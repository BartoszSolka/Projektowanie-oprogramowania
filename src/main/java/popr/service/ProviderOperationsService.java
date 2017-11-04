package popr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import popr.interfaces.ProviderOperationsInterface;
import popr.model.Provider;
import popr.model.ServiceOrder;
import popr.model.enums.ServiceOrderStatusDict;
import popr.repository.ProviderRepository;
import popr.repository.ServiceOrderRepository;
import popr.repository.ServiceRepository;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderOperationsService implements ProviderOperationsInterface {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public Page<popr.model.ServiceOrder> getServiceOrdersByProvider(Provider provider, Pageable pageable) {
        return serviceOrderRepository.findByService_Provider(provider, pageable);
    }

    @Override
    public popr.model.Service addService(Provider provider, String description, int price, int estimatedRealisationTime, String name) {
        popr.model.Service service = new popr.model.Service();
        service.setDescription(description);
        service.setPrice(price);
        service.setEstimatedRealisationTime(estimatedRealisationTime);
        service.setProvider(provider);

        return serviceRepository.save(service);
    }

    @Override
    public popr.model.Service changeServiceStatus(ServiceOrderStatusDict status, String description, ServiceOrder serviceOrder) {
        return null;
    }

    @Override
    public List<ServiceOrderStatusDict> getAllServiceOrderStatuses() {
        return Arrays.asList(ServiceOrderStatusDict.values());
    }
}

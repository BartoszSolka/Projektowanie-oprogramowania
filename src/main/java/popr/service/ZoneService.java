package popr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import popr.model.Zone;
import popr.repository.ZoneRepository;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;

    public Page<Zone> readZones(Pageable pageable) {
        return zoneRepository.findAll(pageable);
    }

    /*public Zone readZoneByPostalCode(String postalCode) {
        return zoneRepository.findByPostalCode(postalCode).orElse(null);
    }*/
}

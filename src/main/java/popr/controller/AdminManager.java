package popr.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;

import java.util.Date;
import java.util.List;

public interface AdminManager {

    void addProvider(String nip, String telefon, String siedzibaFirmy, String nazwa, String idStrefy, String email);
    void deleteProvider(String idUslugodawcy);
    void editProvider(String idUslugodawcy, String nip, String telefon, String siedzibaFirmy, String nazwa, String idStrefy, String email);

    void editService(String idUslugodawcy, String cena, String estymowanyCzas, String idRodzajuUslugi, String idUslugi);
    void deleteService(String idUslugi);
    void addService(String idUslugodawcy, String cena, String estymowanyCzas, String idRodzajuUslugi);

    void addPerson(String email, String imie, String nazwisko, String strefa, String telefon, String adres, String login, String haslo, boolean czyAdmin, String idUslugodawcy);
    void deletePerson(String personId);
    void editPerson(String idOsoby, String email, String imie, String nazwisko, String strefa, String telefon, String adres, String login, String haslo, boolean czyAdmin, String idUslugodawcy);

    List<ServiceChange> getNotValidatedServiceChanges();
    List<ChangeStatus> getStatusChangeDictionary();
    void changeStatusOfChange(String idZmiany, String idStatusu, String opis);
    List<Zone> getZonesList();
    List<Provider> getProvidersList();

    void generateReport(Date poczatek, Date koniec);
    void generateCalculations(Date poczatek, Date koniec);

    List<Person> getUsers();
    Person getUser(Long userId);
    Provider getProvider(Long providerId);
    String getPostalCode(Long zoneId);
}

package popr.controller;

import popr.model.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AdminManager {

    void addProvider(String nip, String telefon, String siedzibaFirmy, String nazwa, String kodStrefy, String email);
    void deleteProvider(String idUslugodawcy);
    void editProvider(String idUslugodawcy, String nip, String telefon, String siedzibaFirmy, String nazwa, String kodStrefy, String email);

    void editService(String idUslugodawcy, String cena, String estymowanyCzas, String idRodzajuUslugi, String idUslugi);
    void deleteService(String idUslugi);
    void addService(String idUslugodawcy, String cena, String estymowanyCzas, String idRodzajuUslugi);

    void addPerson(String email, String imie, String nazwisko, String kodStrefy, String telefon, String adres, String login, String haslo, boolean czyAdmin, String idUslugodawcy);
    void deletePerson(String personId);
    void editPerson(String idOsoby, String email, String imie, String nazwisko, String kodStrefy, String telefon, String adres, String login, String haslo, boolean czyAdmin, String idUslugodawcy);

    List<ServiceChange> getNotValidatedServiceChanges();
    List<ChangeStatus> getStatusChangeDictionary();
    void changeStatusOfChange(String idZmiany, String idStatusu, String opis);
    List<Zone> getZonesList();
    List<Provider> getProvidersList();

    List<ServiceOrder> generateReport(Date poczatek, Date koniec);
    Map<Long, Integer> generateCalculations(Date poczatek, Date koniec);

    List<Person> getUsers();
    Person getUser(Long userId);
    Provider getProvider(Long providerId);
    String getPostalCode(Long zoneId);
    Long getPostalCodeId(String postalCode);
}

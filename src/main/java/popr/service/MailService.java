package popr.service;

import popr.model.Provider;
import popr.model.ServiceOrder;

public interface MailService {

    public void sendEmail(String mailContent, String providerEmail);
}

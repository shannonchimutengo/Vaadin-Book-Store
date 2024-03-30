package org.vaadin.example.Service;

import org.springframework.stereotype.Service;
import org.vaadin.example.Entities.*;

import java.util.List;

@Service
public class CrmService {

    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;

    public CrmService(ContactRepository contactRepository,
                      CompanyRepository companyRepository,
                      StatusRepository statusRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
    }
    public List<Contact> findAllContacts(String filterText){

        if(filterText == null || filterText.isEmpty()){
           return contactRepository.findAll();
        }else {

            return contactRepository.search(filterText);
        }
    }
    public Long countContacts(){
      return   contactRepository.count();
    }
    public void deleteContact(Contact contact){
        contactRepository.delete(contact);
    }
    public void saveContact(Contact contact){
        if (contact == null){
            System.err.println("Contact is null");
            return;
        }
        contactRepository.save(contact);
    }

    public List<Company> findAllCompanies(){
       return companyRepository.findAll();
    }
    public List<Status> findAllStatuses(){
        return statusRepository.findAll();
    }
}

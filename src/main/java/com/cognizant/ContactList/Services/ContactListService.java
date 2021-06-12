package com.cognizant.ContactList.Services;

import com.cognizant.ContactList.DTO.ContactDTO;
import com.cognizant.ContactList.Domains.ContactList;
import com.cognizant.ContactList.Repositories.ContactListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactListService {

    @Autowired
    ContactListRepository repo;

    public List<ContactList> findAllContacts() {

        return repo.findAll();
    }

    public ContactDTO save(ContactList contact) {
        return transformModelToDTO(repo.save(contact));
    }

    public ContactDTO transformModelToDTO(ContactList contact){
        ContactDTO response = new ContactDTO();
        response.setId(""+contact.getId());
        response.setGivenName(contact.getGivenName());
        response.setSurName(contact.getSurName());
        response.setPhoneNumber(contact.getPhoneNumber());
        return response;
    }
}

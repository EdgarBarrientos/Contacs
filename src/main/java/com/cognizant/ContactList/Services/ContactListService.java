package com.cognizant.ContactList.Services;

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

    public ContactList save(ContactList contact) {
         return repo.save(contact);
    }
}

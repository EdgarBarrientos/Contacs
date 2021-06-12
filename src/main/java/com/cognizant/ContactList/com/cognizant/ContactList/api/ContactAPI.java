package com.cognizant.ContactList.com.cognizant.ContactList.api;

import com.cognizant.ContactList.Domains.ContactList;
import com.cognizant.ContactList.Services.ContactListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactAPI {

    @Autowired
    private ContactListService service;

    @GetMapping("/Contacts")
    public ResponseEntity<Iterable<ContactList>> getContacts(){
        return new ResponseEntity(service.findAllContacts(), HttpStatus.OK);
    }

    @PostMapping("/Contact")
    public ResponseEntity<ContactList> createContact(@RequestBody ContactList contact){
        return new ResponseEntity(service.save(contact),HttpStatus.CREATED);
    }
}

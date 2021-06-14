package com.cognizant.ContactList.com.cognizant.ContactList.api;

import com.cognizant.ContactList.DTO.ContactDTO;
import com.cognizant.ContactList.Domains.ContactList;
import com.cognizant.ContactList.Services.ContactListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ContactAPI {

    @Autowired
    private ContactListService service;


    @PostMapping("/Contact")
    public ResponseEntity<ContactDTO> createContact(@RequestBody ContactList contact){
        return new ResponseEntity<>(service.save(contact),HttpStatus.CREATED);
    }

    @GetMapping("/Contact/{id}")
    public ResponseEntity getContactByID(@PathVariable Long id){
        ContactDTO contact= service.findContactByID(id);
        if (contact==null)
            return new ResponseEntity(id,HttpStatus.NOT_FOUND);
        else
            return  new ResponseEntity(contact,HttpStatus.OK);
    }
    @RequestMapping( value = {"/Contacts","/Contacts/{givenName}","/Contacts/{surName}","/Contacts/{givenName}/{surName}"}, method = RequestMethod.GET)
    public ResponseEntity<List<ContactDTO>> getContactByNameSurname(@RequestParam(value = "givenName",required = false) String givenName,
                                                                    @RequestParam(value = "surName",required = false) String surName){

        List<ContactDTO> response= new ArrayList<>();
        ContactList filter= new ContactList();
        boolean findname = false, findsurname = false;

        if(givenName!=null)
            findname=true;
        if(surName!=null)
            findsurname=true;

        //ContactList> spec= new ContactSpecification(filter);
        if (givenName!=null || surName!=null)
            return new ResponseEntity<>(service.findAllContacts(findname?Optional.of(givenName):Optional.empty(),
                                                                findsurname?Optional.of(surName):Optional.empty()),
                                                                HttpStatus.OK);
        else
            return new ResponseEntity<>(service.findAllContacts(Optional.empty(),Optional.empty()), HttpStatus.OK);


    }

}

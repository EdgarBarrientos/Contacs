package com.cognizant.ContactList.com.cognizant.ContactList.api;

import com.cognizant.ContactList.DTO.ContactDTO;
import com.cognizant.ContactList.Domains.ContactList;
import com.cognizant.ContactList.Services.ContactListService;
import com.cognizant.ContactList.Specifications.ContactSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    public ResponseEntity createContact(@RequestBody ContactList contact){
        return new ResponseEntity(service.save(contact),HttpStatus.CREATED);
    }

    @GetMapping("/Contact/{id}")
    public ResponseEntity getContactByID(@RequestParam Long id){
        ContactDTO contact= service.findContactByID(id);
        if (contact==null)
            return new ResponseEntity(id,HttpStatus.NOT_FOUND);
        else
            return  new ResponseEntity(contact,HttpStatus.OK);
    }
    @RequestMapping( value = {"/Contacts","/Contacts/{name}","/Contacts/{surname}","/Contacts/{name}/{surname}"}, method = RequestMethod.GET)
    public ResponseEntity getContactByNameSurname(@PathVariable(value = "givenName",required = false) String givenName,
                                                                    @PathVariable(value = "surname",required = false) String surname){

        List<ContactDTO> response= new ArrayList<>();
        ContactList filter= new ContactList();

        if(givenName!=null)
            filter.setGivenName(givenName);
        if(surname!=null)
            filter.setSurName(surname);

        Specification<ContactList> spec= new ContactSpecification(filter);
        if (givenName!=null || surname!=null)
            return new ResponseEntity(service.findAllContacts(Optional.of(spec)), HttpStatus.OK);
        else
            return new ResponseEntity(service.findAllContacts(Optional.empty()), HttpStatus.OK);


    }

}

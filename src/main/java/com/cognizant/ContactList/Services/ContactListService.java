package com.cognizant.ContactList.Services;

import com.cognizant.ContactList.DTO.ContactDTO;
import com.cognizant.ContactList.Domains.ContactList;
import com.cognizant.ContactList.Repositories.ContactListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//import org.modelmapper.ModelMapper;
import javax.persistence.criteria.*;

@Service
public class ContactListService  {

    @Autowired
    ContactListRepository repo;

    @PersistenceContext
    private EntityManager em;


    public List<ContactDTO> findAllContacts( Optional<String> findGivenName, Optional<String> findSurName){
        List<ContactDTO> returnDTOs= new ArrayList<>();
        List<ContactList> found;


        found=repo.findAll();

        List<ContactList> filtered= new ArrayList<>();
        findGivenName.ifPresent(s -> filtered.addAll(found.stream()
                .filter(c -> c.getGivenName().contains(s))
                .collect(Collectors.toList())));
        findSurName.ifPresent(s -> filtered.addAll(found.stream()
                .filter(c -> c.getSurName().contains(s))
                .collect(Collectors.toList())));
        if(findSurName.isEmpty() && findGivenName.isEmpty())
            filtered.addAll(found);
        for (ContactList item :filtered
             ) {
            returnDTOs.add(transformModelToDTO(item));
        }
        return returnDTOs;
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

    public ContactDTO findContactByID(Long id) {
        Optional<ContactList> found=repo.findById(id);
        return found.map(this::transformModelToDTO).orElse(null);
    }

}

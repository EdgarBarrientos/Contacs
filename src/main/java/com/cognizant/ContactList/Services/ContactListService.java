package com.cognizant.ContactList.Services;

import com.cognizant.ContactList.DTO.ContactDTO;
import com.cognizant.ContactList.Domains.ContactList;
import com.cognizant.ContactList.Repositories.ContactListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ContactListService {

    @Autowired
    ContactListRepository repo;

    public List<ContactDTO> findAllContacts( Optional<Specification<ContactList>> spec){
        List<ContactDTO> returnDTOs= new ArrayList<>();
        List<ContactList> found;
        if (spec!=null && spec.isPresent() )
            found=repo.findAll(spec.get());
        else {
            found=repo.findAll();
        }
        for (ContactList item :found
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

    public List<ContactDTO> findContactBySpecification(Specification<ContactList> spec) {

        List<ContactDTO> response= new ArrayList<>();
        for (ContactList item :repo.findAll(spec)
        ) {
            response.add(transformModelToDTO(item));
        }

        return response;
    }

}

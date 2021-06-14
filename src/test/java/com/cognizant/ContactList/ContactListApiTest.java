package com.cognizant.ContactList;

import com.cognizant.ContactList.DTO.ContactDTO;
import com.cognizant.ContactList.Domains.ContactList;
import com.cognizant.ContactList.Services.ContactListService;
import com.cognizant.ContactList.Specifications.ContactSpecification;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
public class ContactListApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactListService service;

    @Test
    public void getContactsTest() throws Exception{
        List<ContactDTO> listOfContacts = new ContactsSetup().getContactDTOLists();
        when(service.findAllContacts(Optional.empty(),Optional.empty())).thenReturn(listOfContacts);

        mockMvc.perform(get("/Contacts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect( jsonPath("length()" ).value(listOfContacts.size()) );

    }
    @Test
    public void getContactsWithSpecTest() throws Exception{
        List<ContactDTO> listOfContacts = new ContactsSetup().getContactDTOLists();
        ContactList filter= new ContactList();
        filter.setSurName("Test");
        //Optional<Specification<ContactList>> spec= Optional.of(new ContactSpecification(filter));
        when(service.findAllContacts(Optional.empty(),Optional.of("Test"))).thenReturn(listOfContacts);

        mockMvc.perform(get("/Contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("givenName","Paul")
                        .param("surName","Garcia")
                        )
                .andExpect(status().isOk())
               // .andExpect( jsonPath("length()" ).value(listOfContacts.size()) )
        ;

    }

    @Test
    public void createContactTest() throws Exception{
        String contactJson = new ContactsSetup().getJSON("/Contact.json");
        mockMvc.perform(post("/Contact").
                contentType(MediaType.APPLICATION_JSON).
                content(contactJson)).
                andExpect(status().isCreated());
    }



}

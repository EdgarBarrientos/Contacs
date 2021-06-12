package com.cognizant.ContactList;

import com.cognizant.ContactList.Domains.ContactList;
import com.cognizant.ContactList.Services.ContactListService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        List<ContactList> listOfContacts = new ContactsSetup().getContactLists();
        when(service.findAllContacts()).thenReturn(listOfContacts);

        mockMvc.perform(get("/Contacts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect( jsonPath("length()" ).value(listOfContacts.size()) );

    }



}

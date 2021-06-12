package com.cognizant.ContactList;

import com.cognizant.ContactList.Domains.ContactList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ContactsSetup {

    public List<ContactList> getContactLists() throws Exception {
        String contactList = getJSON("/ContactList.json");
        ObjectMapper objectMapper = new ObjectMapper();

        List<ContactList> listOfContacts = new ObjectMapper().
                readValue(contactList,new TypeReference<List<ContactList>>(){});
        return listOfContacts;
    }

    private String getJSON(String path) throws Exception {
        return new String(this.getClass().getResourceAsStream(path).readAllBytes());
    }
}

package com.cognizant.ContactList;

import com.cognizant.ContactList.DTO.ContactDTO;
import com.cognizant.ContactList.Domains.ContactList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;

public class ContactsSetup {

    public List<ContactList> getContactLists() throws Exception {
        String contactList = getJSON("/ContactList.json");
        ObjectMapper objectMapper = new ObjectMapper();

        return new ObjectMapper().
                readValue(contactList, new TypeReference<>() {
                });
    }
    public List<ContactDTO> getContactDTOLists() throws Exception {
        String contactList = getJSON("/ContactListSave.json");
        ObjectMapper objectMapper = new ObjectMapper();

        return new ObjectMapper().
                readValue(contactList, new TypeReference<>() {
                });
    }

    public String getJSON(String path) throws Exception {
        return new String(Objects.requireNonNull(this.getClass().getResourceAsStream(path)).readAllBytes());
    }
}

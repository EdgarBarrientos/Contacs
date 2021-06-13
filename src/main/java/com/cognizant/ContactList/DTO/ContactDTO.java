package com.cognizant.ContactList.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDTO {
    private String id;
    private String givenName;
    private String surName;
    private String phoneNumber;

    @Override
    public boolean equals(Object o){
        if (!o.getClass().equals(ContactDTO.class))
            return false;
        ContactDTO dto = (ContactDTO) o;
        return this.givenName.equals(dto.getGivenName()) && this.surName.equals(dto.getSurName());
    }
}

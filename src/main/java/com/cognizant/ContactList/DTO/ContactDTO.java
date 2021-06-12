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
        ContactDTO dto = (ContactDTO) o;
        if(this.id.equals( dto.getId()))
            return true;
        else return false;
    }
}

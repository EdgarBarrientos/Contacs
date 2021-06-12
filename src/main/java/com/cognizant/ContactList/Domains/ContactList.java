package com.cognizant.ContactList.Domains;


import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name = "Contacts")
@Getter
@Setter
public class ContactList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String givenName;

    private String surName;

    private  String phoneNumber;
}

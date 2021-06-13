package com.cognizant.ContactList.Repositories;

import com.cognizant.ContactList.Domains.ContactList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ContactListRepository extends JpaRepository<ContactList, Long>, JpaSpecificationExecutor<ContactList> {
    Iterable<ContactList> deleteById(int id);
}



package com.cognizant.ContactList.Repositories;

import com.cognizant.ContactList.Domains.ContactList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactListRepository extends JpaRepository<ContactList, Long> {

}

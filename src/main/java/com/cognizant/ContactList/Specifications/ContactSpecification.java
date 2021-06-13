package com.cognizant.ContactList.Specifications;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cognizant.ContactList.Domains.ContactList;
import org.springframework.data.jpa.domain.Specification;

public class ContactSpecification  implements Specification<ContactList> {

    private ContactList filter;

    public ContactSpecification(ContactList filter) {
        super();
        this.filter = filter;
    }

    public Predicate toPredicate(Root<ContactList> root, CriteriaQuery<?> cq,
                                 CriteriaBuilder cb) {

        Predicate p = cb.disjunction();

        if (filter.getGivenName()!= null) {
            p.getExpressions().add(cb.equal(root.get("name"), filter.getGivenName()));
        }

        if (filter.getSurName() != null) {
            p.getExpressions().add(cb.and(
                    cb.equal(root.get("surname"), filter.getSurName())
            ));
        }


        return p;

    }


}
package com.cognizant.ContactList.Specifications;
import javax.persistence.criteria.*;

import com.cognizant.ContactList.Domains.ContactList;
import org.springframework.data.jpa.domain.Specification;

public class ContactSpecification  implements Specification<ContactList> {

    private final ContactList filter;

    public ContactSpecification(ContactList filter) {
        super();
        this.filter = filter;
    }

    public Predicate toPredicate(Root<ContactList> root, CriteriaQuery<?> cq,
                                 CriteriaBuilder cb) {

//        Predicate p = cb.disjunction();
//
//        if (filter.getGivenName()!= null) {
//            p.getExpressions().add(cb.like(root.get("givenName"), filter.getGivenName()));
//        }
//
//        if (filter.getSurName() != null) {
//            p.getExpressions().add(cb.or(
//                    cb.like(root.get("surName"), filter.getSurName())
//            ));
//        }
//
//
//        return p;

        // if (filter.getGivenName()!= null) {
        //            p.getExpressions().add(cb.like(root.get("givenName"), filter.getGivenName()));
        //        }
        Predicate givenNamePredicate = cb.equal(root.get("givenName"), filter.getGivenName());
        Predicate surNamePredicate = cb.equal(root.get("surName"), filter.getSurName());
        return cb.or(givenNamePredicate, surNamePredicate);


    }


}
package com.ega_api.models;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.id.IdentifierGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

public class CompteNumGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String id=null;

        while (id==null || isIdExists(id,sharedSessionContractImplementor)) {
            id = UUID.randomUUID().toString().substring(0, 8).toUpperCase() + '-'
                    + LocalDateTime.now().getYear();
        }
        return id;
    }

    private boolean isIdExists(String id, SharedSessionContractImplementor session) {
        Long count = (Long) session.createQuery("SELECT COUNT(e) FROM Compte e WHERE e.id = :id")
                .setParameter("id", id)
                .uniqueResult();
        System.out.println(count>0);
        return count > 0;
    }
}

package com.ega_api.models;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.IdentityGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

public class ClientIdGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String id;
        if(o instanceof Client){
            Client client = (Client) o;
            System.out.println(client.getNom());
            id = client.getNom().substring(0,3)+client.getPrenom().substring(0,3) + System.currentTimeMillis();
            id = id.toUpperCase();
            return id;

        }
        throw new HibernateException("Unsupported object type: " + o.getClass().getName());
    }
}

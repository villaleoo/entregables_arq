package org.example.microusers.services;

import java.util.List;

public interface Service<T,ID> {
    List<T> findAll()throws Exception;
    T findById(ID id)throws Exception;
    T save(T entity)throws  Exception;
    T update(ID id, T entity)throws Exception;
    T delete(ID id)throws Exception;
}

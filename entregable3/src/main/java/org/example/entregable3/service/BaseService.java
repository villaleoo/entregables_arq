package org.example.entregable3.service;



import java.util.List;

public interface BaseService<T, ID>{
    List<T> findAll()throws Exception;
    T findById(ID id)throws Exception;
    T save(T entity)throws  Exception;
    T update(ID id, T entity)throws Exception;
    boolean delete(ID id)throws Exception;
}
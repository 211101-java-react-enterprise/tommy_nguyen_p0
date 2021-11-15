package com.revature.banking.daos;

import com.revature.banking.util.List;

//CRUD: Create, Read, Update, Delete
public interface CrudDAO<T> {
    //Create
    T save(T newObj);

    //Read
    List<T> findAll();
    T findById(String id);

    //Update
    boolean update(T updatedObj);

    //Remove
    boolean removeById(String id);


}

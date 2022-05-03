package com.mvc.many.repositories;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.many.models.Entry;

@Repository
public interface EntryRepo extends CrudRepository<Entry, Long>{
	List <Entry> findAll();
}


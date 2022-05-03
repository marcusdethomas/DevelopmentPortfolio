package com.mvc.many.services;

import java.awt.Image;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mvc.many.models.Entry;
import com.mvc.many.models.User;
import com.mvc.many.repositories.EntryRepo;

@Service
public class EntryService {
	@Autowired
    private final EntryRepo repository;
    
    public EntryService(EntryRepo repository) {
		this.repository = repository;
	}
    public List<Entry> all() {
        return repository.findAll();
    }
   
    public Entry findBy(Long id) {
        Optional<Entry> EntryList = repository.findById(id);
        if(EntryList.isPresent()) {
            return EntryList.get();
        } else {
            return null;
        }
    }
    public void delete(Entry entry) {
    	repository.delete(entry);
    }
    
    public Entry save(Entry entry) {
        return repository.save(entry);
    }
    
    // photo handling
}


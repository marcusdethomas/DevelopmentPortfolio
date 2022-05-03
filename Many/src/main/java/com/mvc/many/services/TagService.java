package com.mvc.many.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.many.models.Entry;
import com.mvc.many.models.Tag;
import com.mvc.many.repositories.TagRepo;


@Service
public class TagService {
	@Autowired
    private final TagRepo repository;
    
    public TagService(TagRepo repository) {
		this.repository = repository;
	}
    public List<Tag> all() {
        return repository.findAll();
    }
   
    public Tag findBy(Long id) {
        Optional<Tag> PhotoList = repository.findById(id);
        if(PhotoList.isPresent()) {
            return PhotoList.get();
        } else {
            return null;
        }
    }
    public void delete(Tag tag) {
    	repository.delete(tag);
    }
    
    public Tag save(Tag tag) {
        return repository.save(tag);
    }
}


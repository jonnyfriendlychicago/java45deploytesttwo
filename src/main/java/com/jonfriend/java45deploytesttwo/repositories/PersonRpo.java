package com.jonfriend.java45deploytesttwo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.java45deploytesttwo.models.PersonMdl;

@Repository
public interface PersonRpo extends CrudRepository<PersonMdl, Long>{
	List<PersonMdl> findAll(); 

	// end repo
}

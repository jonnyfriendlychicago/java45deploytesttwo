package com.jonfriend.java45deploytesttwo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.java45deploytesttwo.models.ExpenseMdl;

@Repository
public interface ExpenseRpo extends CrudRepository<ExpenseMdl, Long>{
	
	List<ExpenseMdl> findAll(); 
	


// end repo
}

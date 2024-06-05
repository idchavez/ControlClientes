package com.id.dao;

import com.id.domain.Persona;
import org.springframework.data.repository.CrudRepository;


public interface IPersonaDao extends CrudRepository<Persona, Long>{
    
}

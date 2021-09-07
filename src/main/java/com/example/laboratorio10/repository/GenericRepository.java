package com.example.laboratorio10.repository;


import com.example.laboratorio10.model.ModelBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@SuppressWarnings("rawtypes")
public interface GenericRepository<T extends ModelBase> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}

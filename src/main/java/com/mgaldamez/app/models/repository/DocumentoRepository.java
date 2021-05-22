package com.mgaldamez.app.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mgaldamez.app.models.entity.Documento;

@Repository
public interface DocumentoRepository extends CrudRepository<Documento, Long> {

}

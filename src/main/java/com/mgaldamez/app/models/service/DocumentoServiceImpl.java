package com.mgaldamez.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgaldamez.app.models.entity.Documento;
import com.mgaldamez.app.models.repository.DocumentoRepository;

@Service
public class DocumentoServiceImpl implements IDocumentoService {

	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Override
	public List<Documento> listarAll() {
		
		return (List<Documento>) documentoRepository.findAll();
	}

}

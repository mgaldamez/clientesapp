package com.mgaldamez.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgaldamez.app.models.entity.Cliente;
import com.mgaldamez.app.models.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> listarAll() {

		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public void guardar(Cliente cliente) {

		clienteRepository.save(cliente);

	}

	@Override
	public Cliente buscarById(Long id) {

		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {

		clienteRepository.deleteById(id);

	}

}

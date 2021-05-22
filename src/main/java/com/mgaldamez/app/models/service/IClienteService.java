package com.mgaldamez.app.models.service;

import java.util.List;

import com.mgaldamez.app.models.entity.Cliente;



public interface IClienteService {
	public List<Cliente> listarAll();
	public void guardar(Cliente cliente);
	public Cliente buscarById(Long id);
	public void eliminar(Long id);

}

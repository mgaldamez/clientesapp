package com.mgaldamez.app.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgaldamez.app.models.entity.Cliente;
import com.mgaldamez.app.models.service.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("")
	public List<Cliente> list() {

		return clienteService.listarAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> get(@PathVariable Long id) {

		try {
			Cliente cliente = clienteService.buscarById(id);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/")
	public void add(@RequestBody Cliente cliente) {

		clienteService.guardar(cliente);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
		try {

			Cliente existCliente = clienteService.buscarById(id);

			if (existCliente == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			cliente.setId(id);
			clienteService.guardar(cliente);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		clienteService.eliminar(id);
	}

}

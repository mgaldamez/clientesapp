package com.mgaldamez.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mgaldamez.app.models.entity.Cliente;
import com.mgaldamez.app.models.entity.Documento;
import com.mgaldamez.app.models.service.IClienteService;
import com.mgaldamez.app.models.service.IDocumentoService;

@Controller
@RequestMapping("views/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IDocumentoService documentoService;

	@GetMapping({ "/", "", "listar" })
	public String listarClientes(Model model) {
		List<Cliente> listadoClientes = clienteService.listarAll();
		model.addAttribute("titulo", "Listado de Clientes");

		model.addAttribute("clientes", listadoClientes);

		return "views/clientes/listar";
	}

	@GetMapping("/create")
	public String crear(Model model) {
		model.addAttribute("titulo", "Nuevo Cliente");

		Cliente cliente = new Cliente();
		List<Documento> listDocumentType = documentoService.listarAll();

		model.addAttribute("cliente", cliente);
		model.addAttribute("documentTypes", listDocumentType);

		return "views/clientes/frmCrear";
	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute Cliente cliente, RedirectAttributes attribute) {

		clienteService.guardar(cliente);
		System.out.println("Cliente Guardado con Exito: " + cliente);

		attribute.addFlashAttribute("success", "El Cliente fue Guardado con Exito");

		return "redirect:/views/clientes/";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idCliente, Model model, RedirectAttributes attribute) {
		model.addAttribute("titulo", "Editar Cliente");

		Cliente cliente = null;

		if (idCliente > 0) {
			cliente = clienteService.buscarById(idCliente);

			if (cliente == null) {
				System.out.println("Error: Cliente no encontrado");
				attribute.addFlashAttribute("error", "El Cliente no fue encontrado");
				return "redirect:/views/clientes/";
			}

		} else {
			System.out.println("Error: Cliente no encontrado");
			attribute.addFlashAttribute("error", "El Cliente no fue encontrado");
			return "redirect:/views/clientes/";
		}

		List<Documento> listDocumentType = documentoService.listarAll();

		model.addAttribute("cliente", cliente);
		model.addAttribute("documentTypes", listDocumentType);

		return "views/clientes/frmCrear";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idCliente, RedirectAttributes attribute) {

		Cliente cliente = null;

		if (idCliente > 0) {
			cliente = clienteService.buscarById(idCliente);

			if (cliente == null) {
				System.out.println("Error: Cliente no encontrado");
				attribute.addFlashAttribute("error", "El Cliente no fue encontrado");
				return "redirect:/views/clientes/";
			}

		} else {
			System.out.println("Error: Cliente no encontrado");
			attribute.addFlashAttribute("error", "El Cliente no fue encontrado");
			return "redirect:/views/clientes/";
		}

		clienteService.eliminar(idCliente);
		System.out.println("Cliente Eliminado con Exito: ");
		attribute.addFlashAttribute("success", "El Cliente fue Eliminado con Exito");
		return "redirect:/views/clientes/";
	}

}

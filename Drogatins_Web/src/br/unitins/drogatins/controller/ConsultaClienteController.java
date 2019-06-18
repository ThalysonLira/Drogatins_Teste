package br.unitins.drogatins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.dao.ClienteDAO;
import br.unitins.drogatins.model.Perfil;
import br.unitins.drogatins.model.Cliente;

@Named
@ViewScoped
public class ConsultaClienteController implements Serializable{

	private static final long serialVersionUID = 3749349832496776513L;

	private Cliente cliente;

	private List<Cliente> listaCliente = null;
	
	public void excluir(Cliente cliente) {
		ClienteDAO dao = new ClienteDAO();
		if (dao.delete(getCliente().getId())) {
			limpar();
			// para atualizar o data table
			listaCliente = null;
		}
		dao.closeConnection();
	}
	
	public void editar(int id) {
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = dao.findById(id);
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("clienteFlash", cliente);
		
		Util.redirect("cadastrocliente.xhtml");
	}
	
	public void novo() {
		Util.redirect("cadastrocliente.xhtml");
	}
	
	public void voltar() {
		Util.redirect("menu.xhtml");
	}

	public void limpar() {
		cliente = null;
	}
	
	public List<Cliente> getListaCliente() {
		if (listaCliente == null) {
			ClienteDAO dao = new ClienteDAO();
			listaCliente = dao.findAll();
			if (listaCliente == null)
				listaCliente = new ArrayList<Cliente>();
			dao.closeConnection();
		}
		return listaCliente;
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public Cliente getCliente() {
		if (cliente == null)
			cliente = new Cliente();
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
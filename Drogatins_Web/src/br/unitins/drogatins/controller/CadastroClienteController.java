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
public class CadastroClienteController implements Serializable {

	private static final long serialVersionUID = 8713033117401907672L;

	private Cliente cliente;

	private List<Cliente> listaCliente = null;

	public CadastroClienteController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		cliente = (Cliente) flash.get("clienteFlash");
	}
	
	public void editar(int id) {
		ClienteDAO dao = new ClienteDAO();
		setCliente(dao.findById(id));
	}

	public void incluir() {
		// encriptando a senha do cliente
//		getCliente().setSenha(Util.encrypt(getCliente().getSenha()));

		ClienteDAO dao = new ClienteDAO();
		if (dao.create(getCliente())) {
			limpar();
			// para atualizar o data table
			listaCliente = null;
		}
		dao.closeConnection();
	}

	public void alterar(Cliente cliente) {
		// encriptando a senha do cliente
//		getCliente().setSenha(Util.encrypt(getCliente().getSenha()));

		ClienteDAO dao = new ClienteDAO();
		if (dao.update(getCliente())) {
			limpar();
			// para atualizar o data table
			listaCliente = null;
		}
		dao.closeConnection();
	}
	
	public void voltar() {
		Util.redirect("consulta.xhtml");
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
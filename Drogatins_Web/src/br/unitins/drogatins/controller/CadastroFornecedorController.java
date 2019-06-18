package br.unitins.drogatins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.dao.FornecedorDAO;
import br.unitins.drogatins.model.Perfil;
import br.unitins.drogatins.model.Fornecedor;

@Named
@ViewScoped
public class CadastroFornecedorController implements Serializable {

	private static final long serialVersionUID = 2672134864050143504L;

	private Fornecedor fornecedor;

	private List<Fornecedor> listaFornecedor = null;

	public CadastroFornecedorController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		fornecedor = (Fornecedor) flash.get("fornecedorFlash");
	}
	
	public void editar(int id) {
		FornecedorDAO dao = new FornecedorDAO();
		setFornecedor(dao.findById(id));
	}

	public void incluir() {
		// encriptando a senha do fornecedor
//		getFornecedor().setSenha(Util.encrypt(getFornecedor().getSenha()));

		FornecedorDAO dao = new FornecedorDAO();
		if (dao.create(getFornecedor())) {
			limpar();
			// para atualizar o data table
			listaFornecedor = null;
		}
		dao.closeConnection();
	}

	public void alterar(Fornecedor fornecedor) {
		// encriptando a senha do fornecedor
//		getFornecedor().setSenha(Util.encrypt(getFornecedor().getSenha()));

		FornecedorDAO dao = new FornecedorDAO();
		if (dao.update(getFornecedor())) {
			limpar();
			// para atualizar o data table
			listaFornecedor = null;
		}
		dao.closeConnection();
	}
	
	public void voltar() {
		Util.redirect("consulta.xhtml");
	}

	public void limpar() {
		fornecedor = null;
	}

	public List<Fornecedor> getListaFornecedor() {
		if (listaFornecedor == null) {
			FornecedorDAO dao = new FornecedorDAO();
			listaFornecedor = dao.findAll();
			if (listaFornecedor == null)
				listaFornecedor = new ArrayList<Fornecedor>();
			dao.closeConnection();
		}
		return listaFornecedor;
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public Fornecedor getFornecedor() {
		if (fornecedor == null)
			fornecedor = new Fornecedor();
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
}
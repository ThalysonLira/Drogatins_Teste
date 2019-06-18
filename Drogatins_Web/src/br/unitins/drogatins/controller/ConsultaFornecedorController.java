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
public class ConsultaFornecedorController implements Serializable{

	private static final long serialVersionUID = -5547167199087921958L;

	private Fornecedor fornecedor;

	private List<Fornecedor> listaFornecedor = null;
	
	public void excluir(Fornecedor fornecedor) {
		FornecedorDAO dao = new FornecedorDAO();
		if (dao.delete(getFornecedor().getId())) {
			limpar();
			// para atualizar o data table
			listaFornecedor = null;
		}
		dao.closeConnection();
	}
	
	public void editar(int id) {
		FornecedorDAO dao = new FornecedorDAO();
		Fornecedor fornecedor = dao.findById(id);
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("fornecedorFlash", fornecedor);
		
		Util.redirect("cadastrofornecedor.xhtml");
	}
	
	public void novo() {
		Util.redirect("cadastrofornecedor.xhtml");
	}
	
	public void voltar() {
		Util.redirect("menu.xhtml");
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
package br.unitins.drogatins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.dao.FuncionarioDAO;
import br.unitins.drogatins.model.Perfil;
import br.unitins.drogatins.model.Funcionario;

@Named
@ViewScoped
public class ConsultaFuncionarioController implements Serializable{

	private static final long serialVersionUID = -3519999269600542554L;

	private Funcionario funcionario;

	private List<Funcionario> listaFuncionario = null;
	
	public void excluir(Funcionario funcionario) {
		FuncionarioDAO dao = new FuncionarioDAO();
		if (dao.delete(getFuncionario().getId())) {
			limpar();
			// para atualizar o data table
			listaFuncionario = null;
		}
		dao.closeConnection();
	}
	
	public void editar(int id) {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.findById(id);
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("funcionarioFlash", funcionario);
		
		Util.redirect("cadastrofuncionario.xhtml");
	}
	
	public void novo() {
		Util.redirect("cadastrofuncionario.xhtml");
	}
	
	public void voltar() {
		Util.redirect("menu.xhtml");
	}

	public void limpar() {
		funcionario = null;
	}
	
	public List<Funcionario> getListaFuncionario() {
		if (listaFuncionario == null) {
			FuncionarioDAO dao = new FuncionarioDAO();
			listaFuncionario = dao.findAll();
			if (listaFuncionario == null)
				listaFuncionario = new ArrayList<Funcionario>();
			dao.closeConnection();
		}
		return listaFuncionario;
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public Funcionario getFuncionario() {
		if (funcionario == null)
			funcionario = new Funcionario();
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}
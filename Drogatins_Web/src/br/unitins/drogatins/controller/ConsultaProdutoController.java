package br.unitins.drogatins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.dao.ProdutoDAO;
import br.unitins.drogatins.model.Produto;

@Named
@ViewScoped
public class ConsultaProdutoController implements Serializable{

	private static final long serialVersionUID = -3519999269600542554L;

	private String nome;
	private Produto produto;

	private List<Produto> listaProduto = null;
	
	public void excluir(Produto produto) {
		ProdutoDAO dao = new ProdutoDAO();
		if (dao.delete(getProduto().getId())) {
			limpar();
			// para atualizar o data table
			listaProduto = null;
		}
		dao.closeConnection();
	}
	
	public void editar(int id) {
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = dao.findById(id);
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("produtoFlash", produto);
		
		Util.redirect("cadastroproduto.xhtml");
	}
	
	public void pesquisar() {
		listaProduto = null;
	}
	
	public void novo() {
		Util.redirect("cadastroproduto.xhtml");
	}
	
	public void voltar() {
		Util.redirect("menu.xhtml");
	}

	public void limpar() {
		produto = null;
	}
	
	public List<Produto> getListaProduto() {
		if (listaProduto == null) {
			ProdutoDAO dao = new ProdutoDAO();
			listaProduto = dao.findByNome(getNome());
			if (listaProduto == null)
				listaProduto = new ArrayList<Produto>();
			dao.closeConnection();
		}
		return listaProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Produto getProduto() {
		if (produto == null)
			produto = new Produto();
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
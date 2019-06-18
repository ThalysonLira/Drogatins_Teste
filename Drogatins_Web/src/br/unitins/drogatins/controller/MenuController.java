package br.unitins.drogatins.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.drogatins.application.Session;
import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.model.Usuario;

@Named
@ViewScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = -3540211319186607809L;

	Usuario usuarioLogado = null;

	public MenuController() {
		usuarioLogado = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
	}

	public void consultar() {
		Util.redirect("consulta.xhtml");
	}

	public void cadastrar() {
		Util.redirect("cadastro.xhtml");
	}

	public void sair() {
		Util.redirect("login.xhtml");
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
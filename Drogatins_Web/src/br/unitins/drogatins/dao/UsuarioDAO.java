package br.unitins.drogatins.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.model.Perfil;
import br.unitins.drogatins.model.Usuario;

public class UsuarioDAO extends DAO<Usuario> {
	
	public Usuario findUsuario(String login, String senha) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Usuario usuario = null;
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM usuario WHERE login = ? AND senha = ? ");
			stat.setString(1, login);
			stat.setString(2, senha);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuario;		
	}

	@Override
	public Usuario findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Usuario usuario = null;
		
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Usuario WHERE id = ?");
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuario;
	}

	@Override
	public List<Usuario> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Usuario");
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));

				listaUsuario.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaUsuario = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaUsuario;

	}

	@Override
	public boolean create(Usuario obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Usuario obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
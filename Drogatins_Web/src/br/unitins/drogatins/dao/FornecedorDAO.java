package br.unitins.drogatins.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.model.Fornecedor;
import br.unitins.drogatins.model.Endereco;

public class FornecedorDAO extends DAO<Fornecedor> {

	public Fornecedor findFornecedor(String login, String senha) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Fornecedor fornecedor = null;
		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM fornecedor WHERE login = ? AND senha = ? ");
			stat.setString(1, login);
			stat.setString(2, senha);

			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				fornecedor = new Fornecedor();
				fornecedor.setId(rs.getInt("id"));
				fornecedor.setNome(rs.getString("nome"));
				fornecedor.setCnpj(rs.getString("cnpj"));
				fornecedor.setEndereco((Endereco) rs.getObject("endereco"));
				fornecedor.setLogin(rs.getString("login"));
				fornecedor.setSenha(rs.getString("senha"));
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
		return fornecedor;
	}

	@Override
	public boolean create(Fornecedor obj) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement("UPDATE fornecedor SET " + "  nome = ?, " + "  cnpj = ? "
					+ "  endereco = ? " + "  login = ?, " + "  senha = ?, " + "WHERE id = ? ");
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getCnpj());
			stat.setObject(3, obj.getEndereco());
			stat.setString(4, obj.getLogin());
			stat.setString(5, obj.getSenha());
			stat.setInt(6, obj.getId());

			stat.execute();
			Util.addMessageError("Cadastro realizado com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao incluir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public boolean update(Fornecedor obj) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement("UPDATE fornecedor SET " + "  nome = ?, " + "  cnpj = ? "
					+ "  endereco = ? " + "  login = ?, " + "  senha = ?, " + "WHERE id = ? ");
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getCnpj());
			stat.setObject(3, obj.getEndereco());
			stat.setString(4, obj.getLogin());
			stat.setString(5, obj.getSenha());
			stat.setInt(6, obj.getId());

			stat.execute();
			Util.addMessageError("Altera��o realizada com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao Alterar.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;

	}

	@Override
	public boolean delete(int id) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement("DELETE FROM fornecedor WHERE id = ? ");
			stat.setInt(1, id);

			stat.execute();
			Util.addMessageError("Exclus�o realizada com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao Excluir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public Fornecedor findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Fornecedor fornecedor = null;

		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Fornecedor WHERE id = ?");
			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				fornecedor = new Fornecedor();
				fornecedor.setId(rs.getInt("id"));
				fornecedor.setNome(rs.getString("nome"));
				fornecedor.setCnpj(rs.getString("cnpj"));
				fornecedor.setEndereco((Endereco) rs.getObject("endereco"));
				fornecedor.setLogin(rs.getString("login"));
				fornecedor.setSenha(rs.getString("senha"));
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
		return fornecedor;
	}

	@Override
	public List<Fornecedor> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}

		List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();

		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Fornecedor");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(rs.getInt("id"));
				fornecedor.setNome(rs.getString("nome"));
				fornecedor.setCnpj(rs.getString("cnpj"));
				fornecedor.setEndereco((Endereco) rs.getObject("endereco"));
				fornecedor.setLogin(rs.getString("login"));
				fornecedor.setSenha(rs.getString("senha"));

				listaFornecedor.add(fornecedor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaFornecedor = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaFornecedor;

	}

	public List<Fornecedor> findByNome(String nome) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}

		List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();

		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Fornecedor WHERE nome ILIKE ?");
			stat.setString(1, (nome == null ? "%" : "%" + nome + "%"));
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(rs.getInt("id"));
				fornecedor.setNome(rs.getString("nome"));
				fornecedor.setCnpj(rs.getString("cnpj"));
				fornecedor.setEndereco((Endereco) rs.getObject("endereco"));
				fornecedor.setLogin(rs.getString("login"));
				fornecedor.setSenha(rs.getString("senha"));

				listaFornecedor.add(fornecedor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaFornecedor = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaFornecedor;
	}
}

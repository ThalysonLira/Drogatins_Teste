package br.unitins.drogatins.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.drogatins.application.Util;
import br.unitins.drogatins.model.Cliente;
import br.unitins.drogatins.model.Endereco;
import br.unitins.drogatins.model.Sexo;

public class ClienteDAO extends DAO<Cliente> {

	public Cliente findCliente(String login, String senha) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Cliente cliente = null;
		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM cliente WHERE login = ? AND senha = ? ");
			stat.setString(1, login);
			stat.setString(2, senha);

			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDataNascimento(
						rs.getDate("datanascimento") == null ? null : (rs.getDate("datanascimento").toLocalDate()));
				cliente.setEndereco((Endereco) rs.getObject("endereco"));
				cliente.setLogin(rs.getString("login"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setSexo(Sexo.valueOf(rs.getInt("sexo")));
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
		return cliente;
	}

	@Override
	public boolean create(Cliente obj) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection()
					.prepareStatement("UPDATE cliente SET " + "  nome = ?, " + "  cpf = ? " + "  datanascimento = ? "
							+ "  endereco = ? " + "  login = ?, " + "  senha = ?, " + "  sexo = ?, " + "WHERE id = ? ");
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getCpf());
			stat.setDate(3, (obj.getDataNascimento() == null ? null : java.sql.Date.valueOf(obj.getDataNascimento())));
			stat.setObject(4, obj.getEndereco());
			stat.setString(5, obj.getLogin());
			stat.setString(6, obj.getSenha());
			stat.setInt(7, obj.getSexo().getValue());
			stat.setInt(8, obj.getId());

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
	public boolean update(Cliente obj) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection()
					.prepareStatement("UPDATE cliente SET " + "  nome = ?, " + "  cpf = ? " + "  datanascimento = ? "
							+ "  endereco = ? " + "  login = ?, " + "  senha = ?, " + "  sexo = ?, " + "WHERE id = ? ");
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getCpf());
			stat.setDate(3, (obj.getDataNascimento() == null ? null : java.sql.Date.valueOf(obj.getDataNascimento())));
			stat.setObject(4, obj.getEndereco());
			stat.setString(5, obj.getLogin());
			stat.setString(6, obj.getSenha());
			stat.setInt(7, obj.getSexo().getValue());
			stat.setInt(8, obj.getId());

			stat.execute();
			Util.addMessageError("Alteração realizada com sucesso!");
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
			stat = getConnection().prepareStatement("DELETE FROM cliente WHERE id = ? ");
			stat.setInt(1, id);

			stat.execute();
			Util.addMessageError("Exclusão realizada com sucesso!");
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
	public Cliente findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Cliente cliente = null;

		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Cliente WHERE id = ?");
			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDataNascimento(
						rs.getDate("datanascimento") == null ? null : (rs.getDate("datanascimento").toLocalDate()));
				cliente.setEndereco((Endereco) rs.getObject("endereco"));
				cliente.setLogin(rs.getString("login"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setSexo(Sexo.valueOf(rs.getInt("sexo")));
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
		return cliente;
	}

	@Override
	public List<Cliente> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}

		List<Cliente> listaCliente = new ArrayList<Cliente>();

		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Cliente");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDataNascimento(
						rs.getDate("datanascimento") == null ? null : (rs.getDate("datanascimento").toLocalDate()));
				cliente.setEndereco((Endereco) rs.getObject("endereco"));
				cliente.setLogin(rs.getString("login"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setSexo(Sexo.valueOf(rs.getInt("sexo")));

				listaCliente.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaCliente = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaCliente;

	}

	public List<Cliente> findByNome(String nome) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}

		List<Cliente> listaCliente = new ArrayList<Cliente>();

		PreparedStatement stat = null;

		try {
			stat = getConnection().prepareStatement("SELECT * FROM Cliente WHERE nome ILIKE ?");
			stat.setString(1, (nome == null ? "%" : "%" + nome + "%"));
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDataNascimento(
						rs.getDate("datanascimento") == null ? null : (rs.getDate("datanascimento").toLocalDate()));
				cliente.setEndereco((Endereco) rs.getObject("endereco"));
				cliente.setLogin(rs.getString("login"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setSexo(Sexo.valueOf(rs.getInt("sexo")));

				listaCliente.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaCliente = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaCliente;
	}
}
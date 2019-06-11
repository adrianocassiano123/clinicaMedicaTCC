package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import ModeloConexao.ConexaoBD;
import modelo.BeanMarcConsulta;

public class DaoMarcConsulta {

	BeanMarcConsulta marcConsulta = new BeanMarcConsulta();
	ConexaoBD conexao = new ConexaoBD();
	ConexaoBD conexaoPaciente = new ConexaoBD();
	ConexaoBD conexaoMedico = new ConexaoBD();

	int idMedico;
	int idPaciente;

	public void salvar(BeanMarcConsulta marcar) {
		pesquisarMedico(marcar.getNomeMedico());
		pesquisarPaciente(marcar.getNomePaciente());
		conexao.conexao();

		Date data = marcar.getData();
		String novaData = novaData(data);

		try {
			PreparedStatement pst = conexao.conex.prepareStatement("INSERT INTO tab_marcacao("
					+ "	cod_paciente_marcacao, turno_marcacao, cod_medico_marcacao, data_marcacao, motivo_marcacao, status_marcacao)"
					+ "	VALUES ( ?, ?, ?, to_date(?, 'dd/MM/yyyy'), ?, ?);");

			pst.setInt(1, idPaciente);
			pst.setString(2, marcar.getTurno());
			pst.setInt(3, idMedico);
			pst.setString(4, novaData);
			pst.setString(5, marcar.getMotivo());
			pst.setString(6, marcar.getStatus());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Consulta Marcada");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Salvar" + e);
		}

		conexao.desconectar();

	}

	public String novaData(Date data) {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data);

		return dataFormatada;
	}

	public void pesquisarMedico(String nomeMedico) {
		conexaoMedico.conexao();
		conexaoMedico.executarSQL("select *from tabmedico where nomemedico ='" + nomeMedico + "'");

		try {
			conexaoMedico.rs.first();
			idMedico = conexaoMedico.rs.getInt("idmedico");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao encontrar Médico" + e);

		}

	}

	public int pesquisarIdMedico(String nomeMedico) {
		conexaoMedico.conexao();
		conexaoMedico.executarSQL("select *from tabmedico where nomemedico LIKE '%" + nomeMedico + "%'");

		try {
			conexaoMedico.rs.first();
			idMedico = conexaoMedico.rs.getInt("idmedico");
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, "Erro ao encontrar Médico" + e);

		}

		return idMedico;

	}

	public BeanMarcConsulta pesquisarConsultaPorCodigo(int codigo) {
		BeanMarcConsulta marcConsulta = new BeanMarcConsulta();
		conexao.conexao();
		conexao.executarSQL(
				"SELECT *FROM tab_marcacao INNER JOIN tab_paciente  on cod_paciente_marcacao=id_paciente \r\n"
						+ "INNER JOIN tabmedico ON cod_medico_marcacao=idmedico where id_marcacao='" + codigo + "'");

		try {
			conexao.rs.first();
			marcConsulta.setIdMarcConsulta(conexao.rs.getInt("id_marcacao"));
			marcConsulta.setNomePaciente(conexao.rs.getString("nome_paciente"));
			marcConsulta.setNomeMedico(conexao.rs.getString("nomemedico"));
			marcConsulta.setMotivo(conexao.rs.getString("motivo_marcacao"));
			marcConsulta.setNascimento(conexao.rs.getString("nasc_paciente"));

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Buscar No banco" + e);

		}
		return marcConsulta;

	}

	public void pesquisarPaciente(String nomePaciente) {
		conexaoPaciente.conexao();
		conexaoPaciente.executarSQL("select *from tab_paciente where nome_paciente ='" + nomePaciente + "'");

		try {
			conexaoPaciente.rs.first();

			idPaciente = conexaoPaciente.rs.getInt("id_paciente");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao encontrar Paciente" + e);

		}

	}

	public void alterar(BeanMarcConsulta marcacao) {

		conexao.conexao();

		try {
			PreparedStatement pst = conexao.conex
					.prepareStatement("update tab_marcacao set status_marcacao=? where id_marcacao=?");
			pst.setString(1, marcacao.getStatus());
			pst.setInt(2, marcacao.getIdMarcConsulta());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Confirmada Presença");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao confirmar \n Erro: " + e);
			e.printStackTrace();
		}

		conexao.desconectar();

	}

	public void cancelarConsulta(BeanMarcConsulta marcacao) {

		conexao.conexao();

		try {
			PreparedStatement pst = conexao.conex
					.prepareStatement("update tab_marcacao set status_marcacao=? where id_marcacao=?");
			pst.setString(1, marcacao.getStatus());
			pst.setInt(2, marcacao.getIdMarcConsulta());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Cancelada com Sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Cancelar");
			System.out.println(e);

		}

		conexao.desconectar();

	}

	public void remarcarConsulta(BeanMarcConsulta marcar) {

		pesquisarMedico(marcar.getNomeMedico());
		pesquisarPaciente(marcar.getNomePaciente());
		conexao.conexao();

		Date data = marcar.getData();
		String novaData = novaData(data);

		try {
			PreparedStatement pst = conexao.conex.prepareStatement("INSERT INTO tab_marcacao("
					+ "	cod_paciente_marcacao, turno_marcacao, cod_medico_marcacao, data_marcacao, motivo_marcacao, status_marcacao)"
					+ "	VALUES ( ?, ?, ?, to_date(?, 'dd/MM/yyyy'), ?, ?);");

			pst.setInt(1, idPaciente);
			pst.setString(2, marcar.getTurno());
			pst.setInt(3, idMedico);
			pst.setString(4, novaData);
			pst.setString(5, marcar.getMotivo());
			pst.setString(6, marcar.getStatus());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Consulta Remarcada");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Salvar" + e);
		}

		conexao.desconectar();

	}

	public void cancelarConsulAnterior(BeanMarcConsulta marcacao) {

		conexao.conexao();

		try {
			PreparedStatement pst = conexao.conex
					.prepareStatement("update tab_marcacao set status_marcacao=? where id_marcacao=?");
			pst.setString(1, marcacao.getStatus());
			pst.setInt(2, marcacao.getIdMarcConsulta());
			pst.execute();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Cancelar");
			System.out.println(e);

		}

		conexao.desconectar();

	}

	public void retorno(BeanMarcConsulta marcacao) {

		conexao.conexao();

		try {
			PreparedStatement pst = conexao.conex
					.prepareStatement("update tab_marcacao set status_marcacao=? where id_marcacao=?");
			pst.setString(1, marcacao.getStatus());
			pst.setInt(2, marcacao.getIdMarcConsulta());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Retorno Confirmado!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Cancelar");
			System.out.println(e);

		}

		conexao.desconectar();

	}

	public BeanMarcConsulta pesquisarReimpressao(BeanMarcConsulta marcacao) {

		conexao.conexao();
		conexao.executarSQL("SELECT tab_marcacao.id_marcacao as id,"
				+ "			tab_paciente.nome_paciente as paciente," + "			tabmedico.nomemedico as medico,"
				+ "			tab_marcacao.data_marcacao as data" + "FROM   tab_marcacao," + "		tab_paciente,"
				+ "		tabmedico" + "WHERE tab_marcacao.cod_paciente_marcacao = tab_paciente.id_paciente "
				+ "		AND tab_marcacao.cod_medico_marcacao = tabmedico.idmedico"
				+ "		AND tab_marcacao.cod_paciente_marcacao = tab_paciente.id_paciente"
				+ "		AND nome_paciente like'%" + marcacao.getPesquisa() + "%'");
		try {
			conexao.rs.first();

			marcConsulta.setIdMarcConsulta(conexao.rs.getInt("id"));
			marcConsulta.setNomePaciente(conexao.rs.getString("paciente"));
			marcConsulta.setNomeMedico(conexao.rs.getString("medico"));
			marcConsulta.setData(conexao.rs.getDate("data"));

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Paciente Não Encontrado");

		}
		conexao.desconectar();
		return marcacao;

	}

}

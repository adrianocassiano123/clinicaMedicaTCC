package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ModeloConexao.ConexaoBD;
import modelo.BeanMarcConsulta;
import modelo.BeanPEP;

public class DaoPEP {
	
	BeanPEP beanPep = new BeanPEP();
	ConexaoBD conexao = new ConexaoBD();
	ConexaoBD conexaoPaciente = new ConexaoBD();
	ConexaoBD conexaoMedico = new ConexaoBD();
	//ConexaoBD conexaoMarcacao = new ConexaoBD();
	BeanMarcConsulta status = new BeanMarcConsulta();
	

	int idMedico;
	int idPaciente;
	//int idMarcacao;

	public void finalizarConsulta(BeanPEP finalizar) {
		pesquisarMedico(finalizar.getNomeMedico());
		pesquisarPaciente(finalizar.getNome());
		conexao.conexao();

		try {
			PreparedStatement pst = conexao.conex.prepareStatement("INSERT INTO tab_pep(id_marcacaofk, diagnost_consulta, evolucao_consulta, id_pacientefk, id_medicofk) VALUES (?, ?, ?, ?, ?);");
			
			
			pst.setInt(1, finalizar.getIdMarcacao());
			pst.setString(2, finalizar.getDiagnostico());
			pst.setString(3, finalizar.getEvolucao());
			pst.setInt(4, idPaciente);
			pst.setInt(5, idMedico);				
			pst.execute();
			JOptionPane.showMessageDialog(null, "Consulta Finalizada");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Finalizar Consulta" + e);
		}

		conexao.desconectar();

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
	

}
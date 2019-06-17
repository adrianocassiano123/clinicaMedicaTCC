package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ModeloConexao.ConexaoBD;
import modelo.BeanExame;
import modelo.BeanMarcConsulta;

public class DaoExame {
	
	ConexaoBD conexao = new ConexaoBD();
	ConexaoBD conexaoPaciente = new ConexaoBD();
	ConexaoBD conexaoMedico = new ConexaoBD();
	
	
	int idMedico;
	int idPaciente;
	

	public void finalizarExame(BeanExame finalizar,int idMarcacao) {
		pesquisarMedico(finalizar.getNomeMedico());
		pesquisarPaciente(finalizar.getNomePaciente());
		conexao.conexao();

		try {
			PreparedStatement pst = conexao.conex.prepareStatement("INSERT INTO tab_exame( descricao_exame, id_pacientefk, id_medicofk, id_marcacaofk) VALUES (?, ?, ?, ?);");
			
			
			pst.setString(1, finalizar.getDescricao());
			pst.setInt(2, idPaciente);
			pst.setInt(3, idMedico);
			pst.setInt(4, idMarcacao);
			pst.execute();
			JOptionPane.showMessageDialog(null, "Imprima o Exame");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Solicitar Exame" + e);
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
	
	
	public int pegarIdExame(int idMarcacao) {
		int idExame = 0 ;
		conexao.conexao();
		conexao.executarSQL(
				" SELECT id_exame FROM tab_exame  where id_marcacaofk='" + idMarcacao + "'");

		try {
			conexao.rs.first();
			idExame=(conexao.rs.getInt("id_atestado"));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Buscar No banco" + e);

		}
		
		return idExame;

	}
	
	
	

}
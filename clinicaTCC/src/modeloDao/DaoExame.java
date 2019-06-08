package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ModeloConexao.ConexaoBD;
import modelo.BeanExame;

public class DaoExame {
	
	ConexaoBD conexao = new ConexaoBD();
	ConexaoBD conexaoPaciente = new ConexaoBD();
	ConexaoBD conexaoMedico = new ConexaoBD();
	
	
	int idMedico;
	int idPaciente;
	

	public void finalizarExame(BeanExame finalizar) {
		pesquisarMedico(finalizar.getNomeMedico());
		pesquisarPaciente(finalizar.getNomePaciente());
		conexao.conexao();

		try {
			PreparedStatement pst = conexao.conex.prepareStatement("INSERT INTO tab_exame( descricao_exame, id_pacientefk, id_medicofk) VALUES (?, ?, ?);");
			
			
			pst.setString(1, finalizar.getDescricao());
			pst.setInt(2, idPaciente);
			pst.setInt(3, idMedico);				
			pst.execute();
			JOptionPane.showMessageDialog(null, "Imprima o Atestado");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Solicitar Atestado" + e);
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
	
	
	

}
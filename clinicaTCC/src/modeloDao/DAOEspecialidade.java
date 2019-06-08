package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ModeloConexao.ConexaoBD;
import modelo.BeanEspecialidade;
import modelo.BeanMedico;

public class DAOEspecialidade {
	
	BeanEspecialidade especialidade = new BeanEspecialidade();
	ConexaoBD conexao = new ConexaoBD();
	
	
	public void salvar(BeanEspecialidade modelo) {
		conexao.conexao();
		try {

			PreparedStatement pst = conexao.conex.prepareStatement(
					"INSERT INTO tab_especialidade(tipo_especialidade, valor) VALUES (?, ?);");
			pst.setString(1, modelo.getTipo());
			pst.setBigDecimal(2, modelo.getValor());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados \n ERRO:" + e);
			System.out.println(e);
		}

		conexao.desconectar();
		
	}
	
	
	public BeanEspecialidade pesquisarEspecialidade(BeanEspecialidade mod) {
		conexao.conexao();

		conexao.executarSQL("select *from tab_especialidade where tipo_especialidade like'%" + mod.getPesquisa() + "%'");
		try {
			conexao.rs.first();
			especialidade.setIdEspecialide(conexao.rs.getInt("id_especialidade"));
			especialidade.setTipo(conexao.rs.getString("tipo_especialidade"));
			especialidade.setValor(conexao.rs.getBigDecimal("valor"));
			

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Médico Não Encontrado");
			
		}
		conexao.desconectar();
		return especialidade; // OBS

	}
	
	public void editarEspecialidade(BeanEspecialidade modelo) {
		conexao.conexao();
		try {
			PreparedStatement pst = conexao.conex.prepareStatement(
					"update tab_especialidade set tipo_especialidade=?,valor=? where id_especialidade=?");
			
			pst.setString(1, modelo.getTipo());
			pst.setBigDecimal(2, modelo.getValor());
			pst.setInt(3, modelo.getIdEspecialide());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Editado com sucesso!!!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao editar \n Erro: " + e);
			e.printStackTrace();
		}

	

}}

package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ModeloConexao.ConexaoBD;
import modelo.BeanMedicos;

//Classe responsável por todos os métodos
public class DaoMedicos {
	ConexaoBD conexao = new ConexaoBD();
	BeanMedicos model = new BeanMedicos();

	public void salvar(BeanMedicos modelo) {
		conexao.conexao();
		try {

			PreparedStatement pst = conexao.conex.prepareStatement(
					"insert into tab_medico (nome_medico,crm_medico,especial_medico,sexo_medico,logradouro_medico"
							+ "cidade_medico,estado_medico,bairro_medico,telefone_medico,email_medico,numero_resi_medico)"
							+ " values (?,?,?,?,?,?,?,?,?,?,?)");

			pst.setString(1, modelo.getNome());
			pst.setInt(2, modelo.getCrm());
			pst.setString(3, modelo.getEspecialidadeMedico());
			pst.setString(4, modelo.getSexo());
			pst.setString(5, modelo.getEndereco().getLogradouro());
			pst.setString(6, modelo.getEndereco().getCidade());
			pst.setString(7, modelo.getEndereco().getEstado());
			pst.setString(8, modelo.getEndereco().getBairro());
			pst.setInt(9, modelo.getTelefone());
			pst.setString(10, modelo.getEmail());
			pst.setInt(11, modelo.getEndereco().getNumero());

			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados \n ERRO:" + e);
			// e.printStackTrace();
		}

		conexao.desconectar();

	}

	public BeanMedicos pesquisarMedico(BeanMedicos mod) {
		conexao.conexao();

		conexao.executarSQL("select *from tab_medico where nomemedico like'%" + mod.getPesquisaMedico() + "%'");
		try {
			conexao.rs.first();
			model.setCodigoMedico(conexao.rs.getInt("id_medico"));
			model.setNome(conexao.rs.getString("nome_medico"));
			model.setCrm(conexao.rs.getInt("crm_medico"));
			model.setEspecialidadeMedico(conexao.rs.getString("especial_medico"));
			model.setSexo(conexao.rs.getString("sexo_medico"));
			model.getEndereco().setLogradouro(conexao.rs.getString("logradouro_medico")); ////OBS OBS
			model.getEndereco().setCidade(conexao.rs.getString("cidade_medico"));
			model.getEndereco().setEstado(conexao.rs.getString("estado_medico"));
			model.getEndereco().setBairro(conexao.rs.getString("bairro_medico"));
			model.setTelefone(conexao.rs.getInt("telefone_medico"));
			model.setEmail(conexao.rs.getString("email_medico"));
			model.getEndereco().setNumero(conexao.rs.getInt("numero_resi_medico"));
			

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Médico Não Cadastrado");
			System.out.println(e);
		}
		conexao.desconectar();
		return model; // OBS

	}

	public void editarMedico(BeanMedicos mod) {
		conexao.conexao();
		try {
			PreparedStatement pst = conexao.conex.prepareStatement(
					"update tab_medico set nome_medico=?,crm_medico=? ,especial_medico=?, sexo_medico=?,"
					+ "logradouro_medico=?, cidade_medico=?, estado_medico=?,bairro_medico=?, telefone_medico=?,"
					+ "email_medico=?, numero_resi_medico  where idmedico=?");
			pst.setInt(1, mod.getCodigoMedico());
			pst.setString(2, mod.getNome());
			pst.setInt(3, mod.getCrm());
			pst.setString(4, mod.getEspecialidadeMedico());
			pst.setString(5, mod.getEndereco().getLogradouro());
			pst.setString(6, mod.getEndereco().getCidade());
			pst.setString(7, mod.getEndereco().getEstado());
			pst.setString(8, mod.getEndereco().getBairro());
			pst.setString(9, mod.getEspecialidadeMedico());
			pst.setInt(9, mod.getTelefone());
			pst.setString(10, mod.getEmail());
			pst.setInt(11, mod.getEndereco().getNumero());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Editado com sucesso!!!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao editar \n Erro: " + e);
			e.printStackTrace();
		}

		conexao.desconectar();

	}

	// Polimorfismo

	public void excluir() {
		conexao.conexao();

		try {
			PreparedStatement pst = conexao.conex.prepareStatement(" delete from tab_medico where idmedico=?");
			pst.setInt(1, model.getCodigoMedico());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Excluido com Sucesso!!!");
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "Erro ao excluir" + e);

		}

		conexao.desconectar();

	}

	public void excluir(int id) {
		conexao.conexao();

		try {
			PreparedStatement pst = conexao.conex.prepareStatement(" delete from tab_medico where idmedico=?");
			pst.setInt(1, id);
			pst.execute();
			JOptionPane.showMessageDialog(null, "Excluido com Sucesso!!!");
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "Erro ao excluir" + e);
			// e.printStackTrace();
		}

		conexao.desconectar();

	}

}

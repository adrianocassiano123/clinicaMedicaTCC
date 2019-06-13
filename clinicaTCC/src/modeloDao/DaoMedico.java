package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ModeloConexao.ConexaoBD;
import modelo.BeanMedico;

//Classe responsável por todos os métodos
public class DaoMedico {
	ConexaoBD conexao = new ConexaoBD();
	BeanMedico model = new BeanMedico();

	public void salvar(BeanMedico modelo) {
		conexao.conexao();
		try {

			PreparedStatement pst = conexao.conex.prepareStatement(
					"insert into tabmedico (nomemedico,crmmedico,especialidadefk) values (?,?,?)");
			pst.setString(1, modelo.getNome());
			pst.setInt(2, modelo.getCrm());
			pst.setInt(3, modelo.getEspecialidade());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados \n ERRO:" + e);
			// e.printStackTrace();
		}

		conexao.desconectar();
		
	}

	public BeanMedico pesquisarMedico(BeanMedico mod) {
		conexao.conexao();

		conexao.executarSQL("select *from tabmedico where nomemedico like'%" + mod.getPesquisa() + "%'");
		try {
			conexao.rs.first();
			model.setCodigo(conexao.rs.getInt("idmedico"));
			model.setNome(conexao.rs.getString("nomemedico"));
			model.setCrm(conexao.rs.getInt("crmmedico"));
			model.setEspecialidade(conexao.rs.getInt("especialidadefk"));

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Médico Não Encontrado");
			
		}
		conexao.desconectar();
		return model; // OBS

	}

		public void editarMedico(BeanMedico mod) {
		conexao.conexao();
		
		//String especialidade = retornaEspecialidade(mod.getEspecialidade());
		
		try {
			PreparedStatement pst = conexao.conex.prepareStatement(
					"update tabmedico set nomemedico=? ,crmmedico=? , especialidadefk=?  where idmedico=?");
			
			pst.setString(1, mod.getNome());
			pst.setInt(2, mod.getCrm());
			pst.setInt(3, mod.getEspecialidade());
			pst.setInt(4, mod.getCodigo());
			
			pst.execute();
			JOptionPane.showMessageDialog(null, "Editado com sucesso!!!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao editar \n Erro: " + e);
			e.printStackTrace();
		}

		conexao.desconectar();

	}
		
		
		public void excluir() {
			conexao.conexao();
			
			try {
				PreparedStatement pst =conexao.conex.prepareStatement(" delete from tabmedico where idmedico=?");
				pst.setInt(1, model.getCodigo());
				pst.execute();
				JOptionPane.showMessageDialog(null, "Excluido com Sucesso!!!");
			} catch (SQLException e) {
				JOptionPane.showConfirmDialog(null, "Erro ao excluir");
				
			}
			
			conexao.desconectar();

		}
		
		//Polimorfismo
		public void excluir(int id) {
			conexao.conexao();
			
			try {
				PreparedStatement pst =conexao.conex.prepareStatement(" delete from tabmedico where idmedico=?");
				pst.setInt(1, id);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Excluido com Sucesso!!!");
			} catch (SQLException e) {
				JOptionPane.showConfirmDialog(null, "Você não pode excluir este médico");
				System.out.println(e);
				//e.printStackTrace();
			}
			
			conexao.desconectar();

		}
		
		public void retornaEspecialidade(String especialidade) {
			
		}
		
		
//		public BeanMedico pesquisarMedico(BeanMedico mod) {
//			conexao.conexao();
//
//			conexao.executarSQL("select *from tabmedico where nomemedico like'%" + mod.getPesquisa() + "%'");
//			try {
//				conexao.rs.first();
//				model.setCodigo(conexao.rs.getInt("idmedico"));
//				model.setNome(conexao.rs.getString("nomemedico"));
//				model.setCrm(conexao.rs.getInt("crmmedico"));
//				model.setEspecialidade(conexao.rs.getString("especialidademedico"));
//
//			} catch (SQLException e) {
//				JOptionPane.showMessageDialog(null, "Erro ao Pesquisar Médico \n ERRO:" + e);
//			}
//			conexao.desconectar();
//			return model; // OBS
//
//		}
		

}

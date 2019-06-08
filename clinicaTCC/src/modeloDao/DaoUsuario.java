package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ModeloConexao.ConexaoBD;
import modelo.BeanUsuario;

public class DaoUsuario {

	ConexaoBD conexao = new ConexaoBD();
	BeanUsuario model = new BeanUsuario();

	public void salvar(BeanUsuario modelo) {
		conexao.conexao();
		try {

			PreparedStatement pst = conexao.conex.prepareStatement(
					"insert into tab_usuarios (nome_usuario,tipo_usuario,senha_usuario)" + " values (?,?,?)");
			pst.setString(1, modelo.getLoginUsuario());
			pst.setString(2, modelo.getTipoUsuario());
			pst.setString(3, modelo.getSenhaUsuario());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados \n ERRO:" + e);
			// e.printStackTrace();
		}

		conexao.desconectar();

	}

	public BeanUsuario pesquisarUsuario(BeanUsuario mod) {
		conexao.conexao();

		conexao.executarSQL("select *from tab_usuarios where nome_usuario like'%" + mod.getPesquisaUsuario() + "%'");
		try {
			conexao.rs.first();
			model.setIdUsuario(conexao.rs.getInt("id_usuario"));
			model.setLoginUsuario(conexao.rs.getString("nome_usuario"));
			model.setTipoUsuario(conexao.rs.getString("tipo_usuario"));
			model.setSenhaUsuario((conexao.rs.getString("senha_usuario")));
			

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Usuário Não Encontrado");
			
		}
		conexao.desconectar();
		return model; // OBS

	}

	public void editarUsuario(BeanUsuario mod) {
		conexao.conexao();
		try {
			PreparedStatement pst = conexao.conex.prepareStatement(
					"update tab_usuarios set nome_usuario=?,tipo_usuario=?,senha_usuario=? where id_usuario=?");
			pst.setString(1, mod.getLoginUsuario());
			pst.setString(2, mod.getTipoUsuario());
			pst.setString(3, mod.getSenhaUsuario());
			pst.setInt(4, mod.getIdUsuario());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Editado com sucesso!!!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao editar \n Erro: " + e);
			e.printStackTrace();
		}

		conexao.desconectar();

	}

	public void excluir(BeanUsuario model) {

		conexao.conexao();

		try {
			PreparedStatement pst = conexao.conex.prepareStatement(" delete from tab_usuarios where id_usuario=?");
			pst.setInt(1, model.getIdUsuario());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Excluido com Sucesso!!!");
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "Erro ao excluir" + e);
			// e.printStackTrace();
		}

		conexao.desconectar();
	}
	
	//Polimorfismo
	
	public void excluir(int id) {
		conexao.conexao();
		
		try {
			PreparedStatement pst =conexao.conex.prepareStatement(" delete from tab_usuarios where id_usuario=?");
			pst.setInt(1, id);
			pst.execute();
			JOptionPane.showMessageDialog(null, "Excluido com Sucesso!!!");
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "Erro ao excluir"+e);
			//e.printStackTrace();
		}
		
		conexao.desconectar();

	}
	
	
	
	
	

}

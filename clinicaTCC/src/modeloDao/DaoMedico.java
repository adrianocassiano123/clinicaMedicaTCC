package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ModeloConexao.ConexaoBD;
import modelo.BeanMedico;

//Classe responsável por todos métodos
public class DaoMedico {
	ConexaoBD conexao = new ConexaoBD();
	BeanMedico model = new BeanMedico();

	public void salvar(BeanMedico modelo) {
		conexao.conexao();
		try {

			PreparedStatement pst = conexao.conex.prepareStatement(
					"insert into tabmedico (nomemedico,especialidademedico,crmmedico)" + " values (?,?,?)");
			pst.setString(1, modelo.getNome());
			pst.setString(2, modelo.getEspecialidade());
			pst.setInt(3, modelo.getCrm());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados \n ERRO:" + e);
			//e.printStackTrace();
		}

		conexao.desconectar();

	}
	
	
	public BeanMedico pesquisarMedico(BeanMedico mod) {
		conexao.conexao();
		
		conexao.executarSQL("select *from tabmedico where nomemedico like'%"+mod.getPesquisa()+"%'");
		try {
			conexao.rs.first();
			model.setCodigo(conexao.rs.getInt("idmedico"));
			model.setNome(conexao.rs.getString("nomemedico"));
			model.setCrm(conexao.rs.getInt("crmmedico"));
			model.setEspecialidade(conexao.rs.getString("especialidademedico"));


		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Pesquisar Médico \n ERRO:" + e);
		}
		conexao.desconectar();
		return model; //OBS
		
	}

}

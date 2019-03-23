package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.ModeloMedico;

//Classe responsável por todos métodos
public class ControleMedico {

	ConexaoBD conexao = new ConexaoBD();
	ModeloMedico model = new ModeloMedico();

	public void salvar(ModeloMedico modelo) {
		conexao.conexao();
		try {

			PreparedStatement pst = conexao.conex.prepareStatement(
					"insert into tabMedico(nomeMedico,especialidadeMedico,crmMedico)" + " values (?,?,?)");
			pst.setString(1, modelo.getNome());
			pst.setString(2, modelo.getEspecialidade());
			pst.setInt(3, modelo.getCrm());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados \n ERRO:"+e);
		}

		conexao.desconectar();

	}

}

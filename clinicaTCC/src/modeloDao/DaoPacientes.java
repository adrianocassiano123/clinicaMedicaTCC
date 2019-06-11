package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ModeloConexao.ConexaoBD;
import modelo.BeanPaciente;
import modelo.BeanUsuario;

public class DaoPacientes {

	ConexaoBD conexao = new ConexaoBD();
	BeanPaciente model = new BeanPaciente();

	public void salvar(BeanPaciente modelo) {
		conexao.conexao();
		try {

			PreparedStatement pst = conexao.conex.prepareStatement("INSERT INTO public.tab_paciente(nome_paciente, sexo_paciente, logradouro_paciente, cidade_paciente, estado_paciente, bairro_paciente, email_paciente, convenio_paciente, nasc_paciente, num_resid_paciente, rg_paciente, cpf_paciente, cep_paciente, telefone_paciente)"+
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			pst.setString(1, modelo.getNome());
			pst.setString(2, modelo.getSexo());
			pst.setString(3, modelo.getLogradouro());
			pst.setString(4, modelo.getCidade());
			pst.setString(5, modelo.getEstado());
			pst.setString(6, modelo.getBairro());
			pst.setString(7, modelo.getEmail());
			pst.setString(8, modelo.getConvenioPaciente());
			pst.setString(9, modelo.getNascimento());
			pst.setInt(10, modelo.getNumero());
			pst.setString(11, modelo.getRg());
			pst.setString(12, modelo.getCpf());
			pst.setString(13, modelo.getCep());
			pst.setString(14, modelo.getTelefone());

			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados \n ERRO:" + e);
			// e.printStackTrace();
		}

		conexao.desconectar();

	}

	public BeanPaciente pesquisarPaciente(BeanPaciente mod) {
		conexao.conexao();

		conexao.executarSQL("select *from tab_paciente where nome_paciente like'%" + mod.getPesquisaPaciente() + "%'");
		try {
			conexao.rs.first();
			model.setCodPaciente(conexao.rs.getInt("id_paciente"));
			model.setNome(conexao.rs.getString("nome_paciente"));
			model.setSexo(conexao.rs.getString("sexo_paciente"));
			model.setLogradouro((conexao.rs.getString("logradouro_paciente")));
			model.setCidade((conexao.rs.getString("cidade_paciente")));
			model.setEstado((conexao.rs.getString("estado_paciente")));
			model.setBairro((conexao.rs.getString("bairro_paciente")));
			model.setEmail((conexao.rs.getString("email_paciente")));
			model.setConvenioPaciente((conexao.rs.getString("convenio_paciente")));
			model.setNascimento((conexao.rs.getString("nasc_paciente")));
			model.setNumero((conexao.rs.getInt("num_resid_paciente")));
			model.setRg((conexao.rs.getString("rg_paciente")));
			model.setCpf((conexao.rs.getString("cpf_paciente")));
			model.setCep((conexao.rs.getString("cep_paciente")));
			model.setTelefone((conexao.rs.getString("telefone_paciente")));
					

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Paciente Não Encontrado");
			
		}
		conexao.desconectar();
		return model; // OBS

	}

	public void editarPaciente(BeanPaciente mod) {
		conexao.conexao();
		try {
			PreparedStatement pst = conexao.conex.prepareStatement(
					"update tab_paciente set nome_paciente=?, sexo_paciente=?, logradouro_paciente=?, cidade_paciente=?, estado_paciente=?, bairro_paciente=?, email_paciente=?, convenio_paciente=?, nasc_paciente=?, num_resid_paciente=?, rg_paciente=?, cpf_paciente=?, cep_paciente=?, telefone_paciente=? where id_paciente=?");
					
			pst.setString(1, mod.getNome());
			pst.setString(2, mod.getSexo());
			pst.setString(3, mod.getLogradouro());
			pst.setString(4, mod.getCidade());
			pst.setString(5, mod.getEstado());
			pst.setString(6, mod.getBairro());
			pst.setString(7, mod.getEmail());
			pst.setString(8, mod.getConvenioPaciente());
			pst.setString(9, mod.getNascimento());
			pst.setInt(10, mod.getNumero());
			pst.setString(11, mod.getRg());
			pst.setString(12, mod.getCpf());
			pst.setString(13, mod.getCep());
			pst.setString(14, mod.getTelefone());
			pst.setInt(15, mod.getCodPaciente());
			
			pst.execute();
			JOptionPane.showMessageDialog(null, "Editado com sucesso!!!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao editar \n Erro: " + e);
			e.printStackTrace();
		}

		conexao.desconectar();

	}

	public void excluir(BeanPaciente model) {

		conexao.conexao();

		try {
			PreparedStatement pst = conexao.conex.prepareStatement(" delete from tab_paciente where id_paciente=?");
			pst.setInt(1, model.getCodPaciente());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Excluido com Sucesso!!!");
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "Erro ao excluir");
			System.out.println(e);
			
		}

		conexao.desconectar();
	}
	
	//Polimorfismo
	
	public void excluir(int id) {
		conexao.conexao();
		
		try {
			PreparedStatement pst =conexao.conex.prepareStatement(" delete from tab_paciente where id_paciente=?");
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

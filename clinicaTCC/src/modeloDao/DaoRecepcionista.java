package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ModeloConexao.ConexaoBD;
import modelo.BeanEndereco;
import modelo.BeanPaciente;
import modelo.BeanRecepcionista;

public class DaoRecepcionista {

	ConexaoBD conexao = new ConexaoBD();
	BeanRecepcionista beanRecep = new BeanRecepcionista();
	BeanEndereco endereco = new BeanEndereco();
	
	public void salvar(BeanRecepcionista modelo,BeanEndereco endereco) {
		conexao.conexao();
		try {

			PreparedStatement pst = conexao.conex.prepareStatement("INSERT INTO tab_recepcionista(nome_recep, sexo_recep, logradouro_recep, num_resid_recep, cidade_recep, "
					+ "estado_recep, bairro_recep, matricula_recep, nasc_recep, rg_recep, cpf_recep, cep_recep, telefone_recep, email_recep)\r\n" + 
					"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			
			pst.setString(1, modelo.getNome());
			pst.setString(2, modelo.getSexo());
			pst.setString(3, endereco.getLogradouro());
			pst.setInt(4, endereco.getNumero());
			pst.setString(5,endereco.getCidade());
			pst.setString(6, endereco.getEstado());
			pst.setString(7,endereco.getBairro());
			pst.setString(8, modelo.getMatricula());
			pst.setString(9, modelo.getNascimento());
			pst.setString(10, modelo.getRg());
			pst.setString(11, modelo.getCpf());
			pst.setString(12, endereco.getCep());
			pst.setString(13, modelo.getTelefone());
			pst.setString(14, modelo.getEmail());

			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados \n ERRO:");
			System.out.println(e);
		}

		conexao.desconectar();

	}
	
	public void editarRecepcionista(BeanRecepcionista modelo , BeanEndereco endereco) {
		conexao.conexao();
		try {
			PreparedStatement pst = conexao.conex.prepareStatement(" UPDATE tab_recepcionista SET  nome_recep=?, sexo_recep=?, logradouro_recep=?, num_resid_recep=?, cidade_recep=?, estado_recep=?, bairro_recep=?, "
					+ "matricula_recep=?, nasc_recep=?, rg_recep=?, cpf_recep=?, cep_recep=?, telefone_recep=?, email_recep=?"+
					"WHERE id_recep;");
					
			pst.setString(1, modelo.getNome());
			pst.setString(2, modelo.getSexo());
			pst.setString(3, endereco.getLogradouro());
			pst.setInt(4, endereco.getNumero());
			pst.setString(5,endereco.getCidade());
			pst.setString(6, endereco.getEstado());
			pst.setString(7,endereco.getBairro());
			pst.setString(8, modelo.getMatricula());
			pst.setString(9, modelo.getNascimento());
			pst.setString(10, modelo.getRg());
			pst.setString(11, modelo.getCpf());
			pst.setString(12, endereco.getCep());
			pst.setString(13, modelo.getTelefone());
			pst.setString(14, modelo.getEmail());
			pst.setInt(15, modelo.getIdRecep());
			
			pst.execute();
			JOptionPane.showMessageDialog(null, "Editado com sucesso!!!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao editar \n Erro: " + e);
			e.printStackTrace();
		}

		conexao.desconectar();

	}
	
	
	public void excluir(BeanRecepcionista model) {

		conexao.conexao();

		try {
			PreparedStatement pst = conexao.conex.prepareStatement(" delete from tab_recepcionista where id_recep=?");
			pst.setInt(1, model.getIdRecep());
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
			PreparedStatement pst =conexao.conex.prepareStatement(" delete from tab_recepcionista where id_recep=?");
			pst.setInt(1, id);
			pst.execute();
			JOptionPane.showMessageDialog(null, "Excluido com Sucesso!!!");
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "Erro ao excluir"+e);
			//e.printStackTrace();
		}
		
		conexao.desconectar();

	}
	
	public BeanRecepcionista pesquisarPaciente(BeanRecepcionista mod ) {
		conexao.conexao();

		conexao.executarSQL("select *from tab_recepcionista where nome_recep like'%" + mod.getPesquisaRecep() + "%'");
		try {
			conexao.rs.first();
			beanRecep.setIdRecep(conexao.rs.getInt("id_recep"));
			beanRecep.setNome(conexao.rs.getString("nome_recep"));
			beanRecep.setSexo(conexao.rs.getString("sexo_recep"));
			endereco.setLogradouro((conexao.rs.getString("logradouro_recep")));
			endereco.setCidade((conexao.rs.getString("cidade_recep")));
			endereco.setEstado((conexao.rs.getString("estado_recep")));
			endereco.setBairro((conexao.rs.getString("bairro_recep")));
			beanRecep.setEmail((conexao.rs.getString("email_recep")));
			beanRecep.setMatricula((conexao.rs.getString("metricula_recep")));
			beanRecep.setNascimento((conexao.rs.getString("nasc_recep")));
			endereco.setNumero((conexao.rs.getInt("num_resid_recep")));
			beanRecep.setRg((conexao.rs.getString("rg_recep")));
			beanRecep.setCpf((conexao.rs.getString("cpf_paciente")));
			endereco.setCep((conexao.rs.getString("cep_paciente")));
			beanRecep.setTelefone((conexao.rs.getString("telefone_paciente")));
					

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Paciente Não Encontrado");
			
		}
		conexao.desconectar();
		return beanRecep; // OBS
		
	}

	
	

}

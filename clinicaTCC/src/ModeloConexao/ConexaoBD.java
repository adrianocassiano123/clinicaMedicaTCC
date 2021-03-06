package ModeloConexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConexaoBD {

	public Statement stm; // Realiza pesquisa no BD
	public ResultSet rs; // Armazena o resultado do que procuramos
	private String driver = "org.postgresql.Driver";
	private String caminho = "jdbc:postgresql://localhost:5432/clinica";
	private String usuario = "postgres";
	private String senha = "admin";
	public Connection conex; // Realiza conex�o com o DB
	

	public void conexao() {

		try {
			System.setProperty("jdbc.Drivers", driver);
			conex = DriverManager.getConnection(caminho, usuario, senha);
			//JOptionPane.showMessageDialog(null, "Conectado ao BD com sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar com DB: \n" + e.getMessage());

		}

	}
	public void executarSQL(String sql) {
		try {
			stm = conex.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
			rs = stm.executeQuery(sql);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao executarSQL \n" + e.getMessage());
		}
	}
	public void desconectar() {
		try {
			conex.close();
			//JOptionPane.showMessageDialog(null, "BD Desconectado");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao DESCONECTAR com DB: \n" + e.getMessage());
		}

	}
	
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica", "postgres", "admin");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	

}

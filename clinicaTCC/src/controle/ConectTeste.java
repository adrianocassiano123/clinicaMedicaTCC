package controle;

import ModeloConexao.ConexaoBD;

public class ConectTeste {

	public static void main(String[] args) {

		ConexaoBD conect = new ConexaoBD();

		conect.conexao();

		conect.desconectar();

	}

}

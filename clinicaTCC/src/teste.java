import modelo.BeanEndereco;
import modelo.BeanRecepcionista;

public class teste {

	
	public static void main(String[] args) {
		BeanRecepcionista recepcionista = new BeanRecepcionista();
		BeanEndereco end = new BeanEndereco();
		
		end.setLogradouro(recepcionista.getEndereco().getLogradouro());
		recepcionista.setNome("Maria");
		
		
		
	}
	
	
	
}

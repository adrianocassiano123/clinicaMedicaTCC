package modelo;

public class BeanRecepcionista extends BeanPessoa {

	private int idRecep;
	private String matricula;
	private String pesquisaRecep;

	public int getIdRecep() {
		return idRecep;
	}

	public void setIdRecep(int idRecep) {
		this.idRecep = idRecep;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getPesquisaRecep() {
		return pesquisaRecep;
	}

	public void setPesquisaRecep(String pesquisaRecep) {
		this.pesquisaRecep = pesquisaRecep;
	}

}

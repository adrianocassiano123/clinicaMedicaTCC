package modelo;

public class BeanPaciente extends BeanPessoa{
	private int codPaciente;
	private String logradouro;
	private int numero;
	private String Cidade;
	private String estado;
	private String cep;
	private String bairro;
	private String convenioPaciente;
	private String pesquisaPaciente;
	
	
	public int getCodPaciente() {
		return codPaciente;
	}
	public void setCodPaciente(int codPaciente) {
		this.codPaciente = codPaciente;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCidade() {
		return Cidade;
	}
	public void setCidade(String cidade) {
		Cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getConvenioPaciente() {
		return convenioPaciente;
	}
	public void setConvenioPaciente(String convenioPaciente) {
		this.convenioPaciente = convenioPaciente;
	}
	public String getPesquisaPaciente() {
		return pesquisaPaciente;
	}
	public void setPesquisaPaciente(String pesquisaPaciente) {
		this.pesquisaPaciente = pesquisaPaciente;
	}
	
	
	
	
}

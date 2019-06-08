package modelo;

public class BeanMedico extends BeanPessoa{
	
	private int codigo;
	private String nomeMedico;
	private BeanEndereco endMedico;
	private String especialidade;
	private int crm;
	private String pesquisa;
	
	
	
	
	public String getNomeMedico() {
		return nomeMedico;
	}
	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}
	public BeanEndereco getEndMedico() {
		return endMedico;
	}
	public void setEndMedico(BeanEndereco endMedico) {
		this.endMedico = endMedico;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nomeMedico;
	}
	public void setNome(String nome) {
		this.nomeMedico = nome;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public int getCrm() {
		return crm;
	}
	public void setCrm(int crm) {
		this.crm = crm;
	}
	public String getPesquisa() {
		return pesquisa;
	}
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
	
	
	
	

}
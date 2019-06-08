package modelo;

public class BeanMedicos extends BeanPessoa{
	
	private int codigoMedico;
	private BeanEndereco endMedico;
	private String especialidadeMedico;
	private int crm;
	private String pesquisaMedico;
	
	
		
	public BeanEndereco getEndMedico() {
		return endMedico;
	}
	public void setEndMedico(BeanEndereco endMedico) {
		this.endMedico = endMedico;
	}
	public int getCodigoMedico() {
		return codigoMedico;
	}
	public void setCodigoMedico(int codigo) {
		this.codigoMedico = codigo;
	}
	
		
	public String getEspecialidadeMedico() {
		return especialidadeMedico;
	}
	public void setEspecialidadeMedico(String especialidadeMedico) {
		this.especialidadeMedico = especialidadeMedico;
	}
	public int getCrm() {
		return crm;
	}
	public void setCrm(int crm) {
		this.crm = crm;
	}
	public String getPesquisaMedico() {
		return pesquisaMedico;
	}
	public void setPesquisaMedico(String pesquisa) {
		this.pesquisaMedico = pesquisa;
	}
	
	
	
	

}

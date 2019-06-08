package modelo;

public class BeanDeclaracao {

	
private int idDeclaracao;
private String nomePaciente;
private String nomeMedico;
private String descricao;

public int getIdAtestado() {
	return idDeclaracao;
}
public void setIdAtestado(int idAtestado) {
	this.idDeclaracao = idAtestado;
}
public String getNomePaciente() {
	return nomePaciente;
}
public void setNomePaciente(String nomePaciente) {
	this.nomePaciente = nomePaciente;
}
public String getNomeMedico() {
	return nomeMedico;
}
public void setNomeMedico(String nomeMedico) {
	this.nomeMedico = nomeMedico;
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}



}

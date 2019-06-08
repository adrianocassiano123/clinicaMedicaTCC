package modelo;

import java.util.Date;

public class BeanMarcConsulta {
	private int idMarcConsulta;
	private String nomeMedico;
	private String nomePaciente;
	private Date data;
	private String status;
	private String motivo;
	private String turno;
	private String nascimento;
	
	
	public int getIdMarcConsulta() {
		return idMarcConsulta;
	}
	public void setIdMarcConsulta(int idMarcConsulta) {
		this.idMarcConsulta = idMarcConsulta;
	}
	public String getNomeMedico() {
		return nomeMedico;
	}
	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	
	

}

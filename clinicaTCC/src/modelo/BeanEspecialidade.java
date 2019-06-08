package modelo;

import java.math.BigDecimal;

public class BeanEspecialidade {
	private int idEspecialide;
	private String tipo;
	private BigDecimal valor;
	private String pesquisa;
	
	
	
	public int getIdEspecialide() {
		return idEspecialide;
	}
	public void setIdEspecialide(int idEspecialide) {
		this.idEspecialide = idEspecialide;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getPesquisa() {
		return pesquisa;
	}
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
	
	
	
}

package modelo;

import java.util.ArrayList;

import javax.jws.WebParam.Mode;
import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel{

	private ArrayList linhas= null;
	private String [] colunas = null;
	
	public ModeloTabela(ArrayList linhas, String[] colunas) {
		super();
		setLinhas(linhas);
		setColunas(colunas);
		
	}

	public ArrayList getLinhas() {
		return linhas;
	}

	public void setLinhas(ArrayList<String> linhas) {
		this.linhas = linhas;
	}

	public String[] getColunas() {
		return colunas;
	}

	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}
	
	// Método de contar a quantidade de colunas 
	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	
	//Método que conta a quantidade ( temaho) de linhas do array
	public int getRowCount () {
		
		return linhas.size();
	}
	
	//Método retorna o valor do nome da coluna a quantidade de colunas pelo nome
	public String getColumnName(int numCol) {
		return colunas[numCol];
	}
	
	
	//Método monta a tabela , adciona as linhas da tabela
	public Object getValueAt(int numLin, int numCol ) {
		
		Object [] linha = (Object[])getLinhas().get(numLin);
		return linha[numCol];
		
	}

	
	
	
	
	
	
	
}

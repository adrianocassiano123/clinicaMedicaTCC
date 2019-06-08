package relatorios;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import ModeloConexao.ConexaoBD;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio {

	public void relatMedico() {

		ConexaoBD conexao = new ConexaoBD();

		String src = "C:/ClinicalMEDS/clinicaTCC/src/relatorios/medicos.jasper";

		JasperPrint jasperPrint = null;
		try {

			jasperPrint = JasperFillManager.fillReport(src, null, conexao.getConnection());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel gerar relatório!" + e);

		}

		JasperViewer view = new JasperViewer(jasperPrint, false);
		view.setVisible(true);
	}
	
	public void relatAtendido() {

		ConexaoBD conexao = new ConexaoBD();

		String src = "C:/ClinicalMEDS/clinicaTCC/src/relatorios/Atendidos.jasper";

		JasperPrint jasperPrint = null;
		try {

			jasperPrint = JasperFillManager.fillReport(src, null, conexao.getConnection());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel gerar relatório!" + e);

		}

		JasperViewer view = new JasperViewer(jasperPrint, false);
		view.setVisible(true);
	}
	
	public void relatCancelados() {

		ConexaoBD conexao = new ConexaoBD();

		String src = "C:/ClinicalMEDS/clinicaTCC/src/relatorios/Cancelados.jasper";

		JasperPrint jasperPrint = null;
		try {

			jasperPrint = JasperFillManager.fillReport(src, null, conexao.getConnection());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel gerar relatório!" + e);
			

		}

		JasperViewer view = new JasperViewer(jasperPrint, false);
		view.setVisible(true);
	}
	
	
	
	
	public void SolicitarDeclaracao(int codigoMarcacao) {

		ConexaoBD conexao = new ConexaoBD();

		Map<String, Object> codigo = new HashMap<>();

		String src = "C:/ClinicalMEDS/clinicaTCC/src/relatorios/Declaracao.jasper";

		codigo.put("CODMARCACAO", codigoMarcacao);

		JasperPrint jasperPrint = null;

		try {

			jasperPrint = JasperFillManager.fillReport(src, codigo, conexao.getConnection());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel gerar relatório!" + e);

		}

		JasperViewer view = new JasperViewer(jasperPrint, false);
		view.setVisible(true);
	}
	
	
	public void SolicitarExame(int codigoMarcacao) {

		ConexaoBD conexao = new ConexaoBD();

		Map<String, Object> codigo = new HashMap<>();

		String src = "C:/ClinicalMEDS/clinicaTCC/src/relatorios/Exames.jasper";

		codigo.put("CODMARCEXAME", codigoMarcacao);

		JasperPrint jasperPrint = null;

		try {

			jasperPrint = JasperFillManager.fillReport(src, codigo, conexao.getConnection());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel gerar Exame!" + e);

		}

		JasperViewer view = new JasperViewer(jasperPrint, false);
		view.setVisible(true);
	}
	
	public void SolicitarAtestado(int codigoMarcacao) {

		ConexaoBD conexao = new ConexaoBD();

		Map<String, Object> codigo = new HashMap<>();

		String src = "C:/ClinicalMEDS/clinicaTCC/src/relatorios/Atestado.jasper";

		codigo.put("CODMARCACAO", codigoMarcacao);

		JasperPrint jasperPrint = null;

		try {

			jasperPrint = JasperFillManager.fillReport(src, codigo, conexao.getConnection());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel gerar Exame!" + e);

		}

		JasperViewer view = new JasperViewer(jasperPrint, false);
		view.setVisible(true);
	}

}

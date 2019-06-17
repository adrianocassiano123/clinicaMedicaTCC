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
			JOptionPane.showMessageDialog(null, "N�o foi possivel gerar relat�rio!" + e);

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
			JOptionPane.showMessageDialog(null, "N�o foi possivel gerar relat�rio!" + e);

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
			JOptionPane.showMessageDialog(null, "N�o foi possivel gerar relat�rio!" + e);

		}

		JasperViewer view = new JasperViewer(jasperPrint, false);
		view.setVisible(true);
	}

	public void SolicitarDeclaracao(int codigoMarcacao, int codigoDeclaracao) {

		ConexaoBD conexao = new ConexaoBD();

		Map<String, Object> codigo = new HashMap<>();

		String src = "C:/ClinicalMEDS/clinicaTCC/src/relatorios/Declaracao.jasper";

		codigo.put("CODMARCACAO", codigoMarcacao);
		codigo.put("CODDECLARACAO", codigoDeclaracao);

//		 jasperPrint = null;

		try {

			JasperPrint jasperPrint = JasperFillManager.fillReport(src, codigo, conexao.getConnection());
			JasperViewer view = new JasperViewer(jasperPrint, false);
			view.setVisible(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o Existe Declara��o para este Paciente");
			System.out.println(e);

		}

	}

	public void SolicitarExame(int codigoMarcacao, int codigoExame) {

		ConexaoBD conexao = new ConexaoBD();

		Map<String, Object> codigo = new HashMap<>();

		String src = "C:/ClinicalMEDS/clinicaTCC/src/relatorios/Exames.jasper";

		codigo.put("CODMARCEXAME", codigoMarcacao);
		codigo.put("CODEXAME", codigoExame);

		// JasperPrint jasperPrint = null;

		try {

			JasperPrint jasperPrint = JasperFillManager.fillReport(src, codigo, conexao.getConnection());
			JasperViewer view = new JasperViewer(jasperPrint, false);
			view.setVisible(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o Existe Exame para esse paciente!");
			System.out.println(e);

		}

	}


	public void SolicitarAtestado(int codigoMarcacao ,int codigoAtestado) {

		ConexaoBD conexao = new ConexaoBD();

		Map<String, Object> codigo = new HashMap<>();

		String src = "C:/ClinicalMEDS/clinicaTCC/src/relatorios/Atestado.jasper";

		codigo.put("CODMARCACAO", codigoMarcacao);
		codigo.put("CODATESTADO", codigoAtestado);

		// jasperPrint = null;

		try {

			JasperPrint jasperPrint = JasperFillManager.fillReport(src, codigo, conexao.getConnection());
			JasperViewer view = new JasperViewer(jasperPrint, false);
			view.setVisible(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "N�o Existe Atestado para esse Paciente!");
			System.out.println(e);

		}

	}

}

//	public void Solicitar2(String codigoMarcacao, String descricaoMarcacao, String nomeMedico, int crmMedico,
//			String nomePaciente, String nascimento) {
//
//		ConexaoBD conexao = new ConexaoBD();
//
//		Map<String, Object> codigo = new HashMap<>();
//
//		String src = "C:/ClinicalMEDS/clinicaTCC/src/relatorios/Exames.jasper";
//
//		codigo.put("CODMARCEXAME", codigoMarcacao);
//		codigo.put("DESCRICAOMARCACAO", descricaoMarcacao);
//		codigo.put("NOMEMEDICO", nomeMedico);
//		codigo.put("CRMMEDICO", crmMedico);
//		codigo.put("NOMEPACIENTE", nomePaciente);
//		codigo.put("NASCIMENTO", nascimento);
//
//		try {
//
//			JasperPrint jasperPrint = JasperFillManager.fillReport(src, codigo, conexao.getConnection());
//			JasperViewer view = new JasperViewer(jasperPrint, false);
//			view.setVisible(true);
//
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "N�o Existe Exame para esse paciente!");
//			System.out.println(e);
//
//		}
//
//	}
package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.toedter.calendar.demo.TestDateEvaluator;

import ModeloConexao.ConexaoBD;
import modelo.BeanMarcConsulta;
import modelo.ModeloTabela;
import modeloDao.DaoMarcConsulta;

public class FormProntuarioEletronico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	

	BeanMarcConsulta modPaciente = new BeanMarcConsulta();
	BeanMarcConsulta prontuario = new BeanMarcConsulta();
	DaoMarcConsulta dao = new DaoMarcConsulta();
	ConexaoBD conexao = new ConexaoBD();
	
	private final JTextField textFieldIdPEP = new JTextField();
	private final JPanel panel_2 = new JPanel();;
	JButton btnConfirmarMarcao = new JButton();
	JButton btnCancelarMarcao = new JButton();
	String cod_prontuario;
	JFormattedTextField formattedTextFieldNascimento = new JFormattedTextField();

	String dataHoje;
	String status;
	private JTextField textFieldNome;
	private JTextField textFieldMotivo;
	private JTextField textFieldEvolucao;
	private JTextField textFieldDiagnostico;
	JLabel labelMedico = new JLabel("");

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					FormProntuarioEletronico frame = new FormProntuarioEletronico();
//					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public FormProntuarioEletronico(String codAtendimento) {
		
		//BeanMarcConsulta marcConsulta = new BeanMarcConsulta();
		
		cod_prontuario=codAtendimento;
		int cod = Integer.parseInt(cod_prontuario);


		try {
			prontuario = dao.pesquisarConsultaPorCodigo(cod);
			
			textFieldNome.setText("a");
			
			textFieldNome.setText(prontuario.getNomePaciente());
			formattedTextFieldNascimento.setText(prontuario.getNascimento());
			textFieldMotivo.setText(prontuario.getMotivo());
			labelMedico.setText(prontuario.getNomeMedico());
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Listar Paciente");
			System.out.println(e);
		}
		
		
		//JOptionPane.showMessageDialog(null, prontuario.getNomePaciente() + prontuario.getNascimento() + prontuario.getMotivo() +prontuario.getNomeMedico());
		
		
				
		panel_2.setBackground(SystemColor.activeCaption);

		textFieldIdPEP.setColumns(10);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);

		textFieldIdPEP.setVisible(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaption));
		
		JLabel lblEvolucao = new JLabel("Evolu\u00E7\u00E3o :");
		lblEvolucao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDiagnostico = new JLabel("Diagn\u00F3stico :");
		lblDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textFieldEvolucao = new JTextField();
		textFieldEvolucao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldEvolucao.setEnabled(false);
		textFieldEvolucao.setColumns(10);
		
		textFieldDiagnostico = new JTextField();
		textFieldDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldDiagnostico.setEnabled(false);
		textFieldDiagnostico.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFieldDiagnostico, 789, 789, Short.MAX_VALUE)
						.addComponent(lblEvolucao)
						.addComponent(lblDiagnostico, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldEvolucao))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEvolucao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldEvolucao, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblDiagnostico, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textFieldDiagnostico, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.activeCaption));
		
		JLabel lblDadosDoPaciente = new JLabel("Dados do Paciente :");
		lblDadosDoPaciente.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel label_1 = new JLabel("Nome :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNome.setEnabled(false);
		textFieldNome.setColumns(10);
		
		textFieldMotivo = new JTextField();
		textFieldMotivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldMotivo.setEnabled(false);
		textFieldMotivo.setColumns(10);
		
		JLabel label_5 = new JLabel("Nascimento :");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMotivo = new JLabel("Motivo :");
		lblMotivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		;
		formattedTextFieldNascimento.setEnabled(false);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDadosDoPaciente)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1)
								.addComponent(lblMotivo, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(label_5)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(formattedTextFieldNascimento, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
								.addComponent(textFieldMotivo, 725, 725, 725))))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(lblDadosDoPaciente)
					.addGap(7)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5)
						.addComponent(formattedTextFieldNascimento, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldMotivo, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMotivo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblMdico = new JLabel("M\u00E9dico :");
		lblMdico.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		
		labelMedico.setForeground(Color.RED);
		labelMedico.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnRemarcar = new JButton("Remarcar");
		btnRemarcar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnAtestado = new JButton("Atestado");
		btnAtestado.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnDeclarao = new JButton("Declara\u00E7\u00E3o");
		btnDeclarao.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnExame = new JButton("Exame");
		btnExame.setFont(new Font("Tahoma", Font.BOLD, 14));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 827, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(textFieldIdPEP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 827, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(31, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnFinalizar, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addGap(34)
								.addComponent(btnRemarcar, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
								.addGap(51)
								.addComponent(btnExame, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
								.addComponent(btnAtestado, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addGap(55)
								.addComponent(btnDeclarao, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
								.addGap(31))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblMdico, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(labelMedico, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
									.addComponent(panel_3, 0, 0, Short.MAX_VALUE))
								.addGap(31)))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldIdPEP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMdico, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelMedico, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnFinalizar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnDeclarao, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAtestado, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnRemarcar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(btnExame, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
					.addGap(47))
		);

		JLabel lblCadastroDePacientes = new JLabel("Prontu\u00E1rio Eletr\u00F4nico");
		panel_2.add(lblCadastroDePacientes);
		lblCadastroDePacientes.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.setLayout(gl_contentPane);

	}
}

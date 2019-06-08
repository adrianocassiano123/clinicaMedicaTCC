package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

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

import ModeloConexao.ConexaoBD;
import modelo.BeanMarcConsulta;
import modelo.BeanPEP;
import modeloDao.DaoMarcConsulta;
import modeloDao.DaoPEP;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PEP extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMedico;
	private JTextField textFieldNome;
	private JTextField textFieldMotivo;
	private JTextField textFieldDiagnostico;
	private JTextField textFieldEvolucao;
	JLabel labelMedico = new JLabel((String) null);
	JFormattedTextField formattedTextFieldNascimento = new JFormattedTextField();
	private JTextField textFieldIdPEP;
	private JTextField textFieldIdMarcacao;
	DaoPEP daoPEP = new DaoPEP();
	BeanPEP beanPEP = new BeanPEP();
	BeanMarcConsulta status = new BeanMarcConsulta();
	ConexaoBD conexao = new ConexaoBD();
	BeanMarcConsulta consulta = new BeanMarcConsulta();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PEP(String codigoAtendimento) {

		if (codigoAtendimento.isEmpty()) {
			return;
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 730);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {

				String cod_prontuario = codigoAtendimento;
				BeanMarcConsulta prontuario = new BeanMarcConsulta();
				DaoMarcConsulta dao = new DaoMarcConsulta();

				int cod = Integer.parseInt(cod_prontuario);

				try {
					prontuario = dao.pesquisarConsultaPorCodigo(cod);

					textFieldNome.setText(prontuario.getNomePaciente());
					textFieldIdMarcacao.setText(String.valueOf(prontuario.getIdMarcConsulta()));
					formattedTextFieldNascimento.setText(prontuario.getNascimento());
					textFieldMotivo.setText(prontuario.getMotivo());
					textFieldMedico.setText(prontuario.getNomeMedico());

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao Listar Paciente");
					System.out.println(e);
				}

			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		textFieldMedico = new JTextField();
		textFieldMedico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMedico.setEnabled(false);
		// textFieldNomes.setText("a");

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);

		JLabel label = new JLabel("Prontu\u00E1rio Eletr\u00F4nico");
		label.setFont(new Font("Tahoma", Font.BOLD, 21));
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.activeCaption));

		JLabel label_1 = new JLabel("Dados do Paciente :");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel label_2 = new JLabel("Nome :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel label_3 = new JLabel("Motivo :");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNome.setEnabled(false);

		// textFieldNome.setColumns(10);

		JLabel label_4 = new JLabel("Nascimento :");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));

		formattedTextFieldNascimento.setText((String) null);
		formattedTextFieldNascimento.setEnabled(false);

		textFieldMotivo = new JTextField();
		textFieldMotivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldMotivo.setEnabled(false);
		// textFieldMotivo.setColumns(10);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 827, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(label_2)
										.addComponent(
												label_3, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
												.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 403,
														GroupLayout.PREFERRED_SIZE)
												.addGap(30).addComponent(label_4)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(formattedTextFieldNascimento, GroupLayout.PREFERRED_SIZE,
														200, GroupLayout.PREFERRED_SIZE))
										.addComponent(textFieldMotivo, 725, 725, 725))))
						.addContainerGap(28, Short.MAX_VALUE)));
		gl_panel_1
				.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 118, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup().addComponent(label_1).addGap(7)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(label_2)
										.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_4).addComponent(formattedTextFieldNascimento,
												GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldMotivo, GroupLayout.PREFERRED_SIZE, 55,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 17,
												GroupLayout.PREFERRED_SIZE))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(SystemColor.activeCaption));

		textFieldDiagnostico = new JTextField();
		textFieldDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 13));
		// textFieldDiagnostico.setEnabled(false);

		// textFieldDiagnostico.setColumns(10);

		JLabel label_5 = new JLabel("Evolu\u00E7\u00E3o :");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel label_6 = new JLabel("Diagn\u00F3stico :");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldEvolucao = new JTextField();
		textFieldEvolucao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		// textFieldEvolucao.setEnabled(false);
		// textFieldEvolucao.setColumns(10);

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGap(0, 827, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldDiagnostico, 789, 789, Short.MAX_VALUE).addComponent(label_5)
								.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldEvolucao))
						.addContainerGap(26, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGap(0, 325, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textFieldEvolucao, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE).addGap(18)
						.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(textFieldDiagnostico, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addGap(39)));
		panel_2.setLayout(gl_panel_2);

		JButton buttonFinalizar = new JButton("Finalizar");
		buttonFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				beanPEP.setIdMarcacao(Integer.parseInt(textFieldIdMarcacao.getText()));
				beanPEP.setNome(textFieldNome.getText()); // paciente
				beanPEP.setNomeMedico(textFieldMedico.getText());
				beanPEP.setDiagnostico(textFieldDiagnostico.getText());
				beanPEP.setEvolucao(textFieldEvolucao.getText());
				status.setStatus("Finalizado");
				status.setIdMarcConsulta(Integer.parseInt(textFieldIdMarcacao.getText()));
				daoPEP.alterar(status);
				daoPEP.finalizarConsulta(beanPEP);

			}
		});
		buttonFinalizar.setFont(new Font("Tahoma", Font.BOLD, 14));

		JButton buttonRetorno = new JButton("Retorno");
		buttonRetorno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
								
					
					String idMarcacaoCons =textFieldIdMarcacao.getText();
					FormRetorno remarcar = new FormRetorno(idMarcacaoCons);
					remarcar.setVisible(true);
					

					
								
			}
		});
		buttonRetorno.setFont(new Font("Tahoma", Font.BOLD, 14));

		JButton buttonExame = new JButton("Exame");
		buttonExame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String cod_agendamento =codigoAtendimento;
				FormExame exame = new FormExame(cod_agendamento);
				exame.setVisible(true);
				
				
				
			
				
				
			}
		});
		buttonExame.setFont(new Font("Tahoma", Font.BOLD, 14));

		JButton buttonAtestado = new JButton("Atestado");
		buttonAtestado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cod_agendamento =codigoAtendimento;
				FormAtestado atestado = new FormAtestado(cod_agendamento);
				atestado.setVisible(true);
				
			
			}
		});
		buttonAtestado.setFont(new Font("Tahoma", Font.BOLD, 14));

		JButton btnTest = new JButton("test");
		textFieldIdPEP = new JTextField();
		textFieldIdPEP.setEnabled(false);

		JLabel lblMdico = new JLabel("M\u00E9dico :");
		lblMdico.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldIdMarcacao = new JTextField();
		textFieldIdMarcacao.setEnabled(false);

		JLabel lblPep = new JLabel("PEP");

		JLabel lblMarcacao = new JLabel("Marcacao");

		JButton btnDeclaracao = new JButton("Declara\u00E7\u00E3o");
		btnDeclaracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cod_agendamento =codigoAtendimento;
				FormDeclaracao declaracao = new FormDeclaracao(cod_agendamento);
				declaracao.setVisible(true);
			}
		});
		btnDeclaracao.setFont(new Font("Tahoma", Font.BOLD, 14));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addGroup(gl_contentPane
								.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
										.createSequentialGroup().addGroup(gl_contentPane
												.createParallelGroup(Alignment.LEADING, false)
												.addComponent(panel, GroupLayout.PREFERRED_SIZE, 827,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 827,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														panel_2, GroupLayout.PREFERRED_SIZE, 827,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(buttonFinalizar, GroupLayout.PREFERRED_SIZE, 119,
																GroupLayout.PREFERRED_SIZE)
														.addGap(47)
														.addComponent(buttonRetorno, GroupLayout.PREFERRED_SIZE, 128,
																GroupLayout.PREFERRED_SIZE)
														.addGap(75)
														.addComponent(buttonExame, GroupLayout.PREFERRED_SIZE, 123,
																GroupLayout.PREFERRED_SIZE)
														.addGap(61)
														.addComponent(buttonAtestado, GroupLayout.PREFERRED_SIZE, 123,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(
																btnDeclaracao, GroupLayout.PREFERRED_SIZE, 123,
																GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(31, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup().addGap(10)
														.addComponent(textFieldIdPEP, GroupLayout.PREFERRED_SIZE, 95,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane
														.createSequentialGroup().addGap(20).addComponent(lblPep)))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(textFieldIdMarcacao, GroupLayout.PREFERRED_SIZE, 95,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblMarcacao))
										.addGap(53).addComponent(btnTest)
										.addPreferredGap(ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
										.addComponent(lblMdico).addGap(26).addComponent(textFieldMedico,
												GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
										.addGap(64)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addComponent(panel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
				.addGap(14)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnTest)
										.addComponent(lblPep).addComponent(lblMarcacao))
								.addGap(36))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldIdPEP, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldMedico, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMdico).addComponent(textFieldIdMarcacao,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)))
				.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonFinalizar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonRetorno, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonExame, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonAtestado, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDeclaracao, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
				.addGap(38)));
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
		setResizable(false);

	}
}

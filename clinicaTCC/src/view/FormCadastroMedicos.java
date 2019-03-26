package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class FormCadastroMedicos extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodMedico;
	private JTextField textFieldNomeMedico;
	private JTextField textField_2;
	private JTextField textFieldEndMedico;
	private JTextField textFieldCidadeMedico;
	private JTextField textFieldEstadoMedico;
	private JTextField textFieldBairroMedico;
	private JTextField textFieldEmailMedico;
	private JTextField textFieldCepMedico;
	private JTextField textFieldTelefoneMedico;
	private JTextField textFieldEspecialidadeMedica;
	private JTextField textFieldPesquisarMedico;
	private JTable tableCadMedico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastroMedicos frame = new FormCadastroMedicos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormCadastroMedicos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 695);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblCadastroDeMdicos = new JLabel("Cadastro de M\u00E9dicos");
		lblCadastroDeMdicos.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel label_1 = new JLabel("C\u00F3digo");
		
		textFieldCodMedico = new JTextField();
		textFieldCodMedico.setColumns(10);
		
		JButton buttonNovoMedico = new JButton("Novo");
		buttonNovoMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel label_2 = new JLabel("Nome:");
		
		textFieldNomeMedico = new JTextField();
		textFieldNomeMedico.setColumns(10);
		
		JLabel crmMedico = new JLabel("CRM");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel label_5 = new JLabel("Sexo: ");
		
		JRadioButton radioButtonMasculinoMedico = new JRadioButton("Masculino");
		
		JRadioButton radioButtonFemininoMedico = new JRadioButton("Feminino");
		
		JLabel label_6 = new JLabel("Endere\u00E7o: ");
		
		textFieldEndMedico = new JTextField();
		textFieldEndMedico.setColumns(10);
		
		JLabel label_7 = new JLabel("Cidade:");
		
		textFieldCidadeMedico = new JTextField();
		textFieldCidadeMedico.setColumns(10);
		
		JLabel label_8 = new JLabel("Estado: ");
		
		textFieldEstadoMedico = new JTextField();
		textFieldEstadoMedico.setColumns(10);
		
		JLabel label_9 = new JLabel("Bairro: ");
		
		textFieldBairroMedico = new JTextField();
		textFieldBairroMedico.setColumns(10);
		
		JLabel label_10 = new JLabel("E-mail: ");
		
		textFieldEmailMedico = new JTextField();
		textFieldEmailMedico.setColumns(10);
		
		JLabel label_11 = new JLabel("CEP: ");
		
		JLabel label_12 = new JLabel("Telefone: ");
		
		textFieldCepMedico = new JTextField();
		textFieldCepMedico.setColumns(10);
		
		textFieldTelefoneMedico = new JTextField();
		textFieldTelefoneMedico.setColumns(10);
		
		JButton buttonAlterarMedico = new JButton("Alterar");
		
		JButton buttonSalvarMedico = new JButton("Salvar");
		
		JButton buttonExcluirMedico = new JButton("Excluir");
		
		JButton buttonCancelarMedico = new JButton("Cancelar");
		
		JLabel lblEspecialidade = new JLabel("Especialidade: ");
		
		textFieldEspecialidadeMedica = new JTextField();
		textFieldEspecialidadeMedica.setColumns(10);
		
		textFieldPesquisarMedico = new JTextField();
		textFieldPesquisarMedico.setColumns(10);
		
		JButton btnPesquisarMedicos = new JButton("Pesquisar");
		btnPesquisarMedicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		tableCadMedico = new JTable();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldNomeMedico, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(crmMedico)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblEspecialidade)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textFieldEspecialidadeMedica, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(label_5)
									.addGap(18)
									.addComponent(radioButtonMasculinoMedico)
									.addGap(30)
									.addComponent(radioButtonFemininoMedico)
									.addGap(52))))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_6)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldEndMedico, GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(label_7)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textFieldCidadeMedico, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
											.addGap(26)
											.addComponent(label_8)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textFieldEstadoMedico, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(label_9)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textFieldBairroMedico, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)))
									.addGap(42)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(label_11)
										.addComponent(label_12))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldTelefoneMedico, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
										.addComponent(textFieldCepMedico, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_10)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldEmailMedico, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(textFieldPesquisarMedico, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
									.addGap(35)
									.addComponent(btnPesquisarMedicos))))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addContainerGap(234, Short.MAX_VALUE)
							.addComponent(buttonAlterarMedico)
							.addGap(41)
							.addComponent(buttonSalvarMedico)
							.addGap(34)
							.addComponent(buttonExcluirMedico)
							.addGap(18)
							.addComponent(buttonCancelarMedico))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldCodMedico, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCadastroDeMdicos)
								.addComponent(buttonNovoMedico, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(tableCadMedico, GroupLayout.PREFERRED_SIZE, 597, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblCadastroDeMdicos)
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textFieldCodMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonNovoMedico, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(textFieldNomeMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(crmMedico)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(radioButtonMasculinoMedico)
						.addComponent(radioButtonFemininoMedico)
						.addComponent(lblEspecialidade)
						.addComponent(textFieldEspecialidadeMedica, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_6)
						.addComponent(textFieldEndMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7)
						.addComponent(textFieldCidadeMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_8)
						.addComponent(textFieldCepMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_11)
						.addComponent(textFieldEstadoMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldBairroMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_9)
						.addComponent(textFieldTelefoneMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_12))
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_10)
						.addComponent(textFieldEmailMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonAlterarMedico)
						.addComponent(buttonExcluirMedico)
						.addComponent(buttonSalvarMedico)
						.addComponent(buttonCancelarMedico))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPesquisarMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisarMedicos))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tableCadMedico, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(36, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 613, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FormCadPaciente extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodigoPaciente;
	private JTextField textFieldNomePaciente;
	private JTextField textFieldRgPaciente;
	private JTextField textFieldCpfPaciente;
	private JTextField textFieldLogradouroPaciente;
	private JTextField textFieldCidadePaciente;
	private JTextField textFieldEstadoPaciente;
	private JTextField textFieldCepPaciente;
	private JTextField textFieldBairroPaciente;
	private JTextField textFieldTelefonePaciente;
	private JTextField textFieldEmailPaciente;
	private JTextField textFieldConvenioPaciente;
	private JTextField textFieldPesquisarPaciente;
	private JTable table;
	private JTextField textField;
	private JTextField textFieldNasciPaciente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadPaciente frame = new FormCadPaciente();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormCadPaciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		
		JLabel lblCadastroDePacientes = new JLabel("Cadastro de Pacientes");
		lblCadastroDePacientes.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		
		textFieldCodigoPaciente = new JTextField();
		textFieldCodigoPaciente.setColumns(10);
		
		JButton btnNovoPaciente = new JButton("Novo");
		btnNovoPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JLabel lblNome = new JLabel("Nome:");
		
		textFieldNomePaciente = new JTextField();
		textFieldNomePaciente.setColumns(10);
		
		JLabel lblRg = new JLabel("RG: ");
		
		textFieldRgPaciente = new JTextField();
		textFieldRgPaciente.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF: ");
		
		textFieldCpfPaciente = new JTextField();
		textFieldCpfPaciente.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo: ");
		
		JLabel lblEndereo = new JLabel("Logradouro");
		
		textFieldLogradouroPaciente = new JTextField();
		textFieldLogradouroPaciente.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		
		textFieldCidadePaciente = new JTextField();
		textFieldCidadePaciente.setColumns(10);
		
		JLabel lblEstado = new JLabel("UF:  ");
		
		textFieldEstadoPaciente = new JTextField();
		textFieldEstadoPaciente.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP: ");
		
		textFieldCepPaciente = new JTextField();
		textFieldCepPaciente.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro: ");
		
		textFieldBairroPaciente = new JTextField();
		textFieldBairroPaciente.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone: ");
		
		textFieldTelefonePaciente = new JTextField();
		textFieldTelefonePaciente.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail: ");
		
		JLabel lblConvnio = new JLabel("Conv\u00EAnio:");
		
		textFieldEmailPaciente = new JTextField();
		textFieldEmailPaciente.setColumns(10);
		
		textFieldConvenioPaciente = new JTextField();
		textFieldConvenioPaciente.setColumns(10);
		
		JButton btnAlterarPaciente = new JButton("Alterar");
		
		textFieldPesquisarPaciente = new JTextField();
		textFieldPesquisarPaciente.setColumns(10);
		
		JButton btnPesquisarPaciente = new JButton("Pesquisar");
		
		table = new JTable();
		
		JButton butnSalvar = new JButton("Salvar");
		butnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnExcluirPaciente = new JButton("Excluir");
		
		JButton btnCancelarPaciente = new JButton("Cancelar");
		
		JLabel lblNmero = new JLabel("N\u00FAmero: ");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNascimento = new JLabel("Nascimento:");
		
		textFieldNasciPaciente = new JTextField();
		textFieldNasciPaciente.setColumns(10);
		
		JComboBox comboBoxPaciente = new JComboBox();
		comboBoxPaciente.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino"}));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(table, GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCdigo)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textFieldCodigoPaciente, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblCidade)
											.addGap(18)
											.addComponent(textFieldCidadePaciente, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblBairro)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textFieldBairroPaciente, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblEmail)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(btnNovoPaciente, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(btnAlterarPaciente, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
													.addGap(42))
												.addComponent(textFieldEmailPaciente, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE))))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(6)
													.addComponent(lblTelefone))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(27)
													.addComponent(lblEstado)
													.addGap(18)
													.addComponent(textFieldEstadoPaciente, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(textFieldTelefonePaciente, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblCep)
													.addGap(18)
													.addComponent(textFieldCepPaciente, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(18)
													.addComponent(lblConvnio))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(44)
													.addComponent(butnSalvar, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(btnExcluirPaciente, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
													.addGap(88)
													.addComponent(btnCancelarPaciente, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
												.addComponent(textFieldConvenioPaciente, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblEndereo)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textFieldLogradouroPaciente, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblCpf)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(textFieldCpfPaciente, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
													.addGap(37)
													.addComponent(lblNascimento)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(textFieldNasciPaciente, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblNome)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(textFieldNomePaciente, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)))
											.addPreferredGap(ComponentPlacement.RELATED, 18, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblRg)))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(textFieldRgPaciente)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblSexo)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(comboBoxPaciente, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
												.addGap(66)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNmero)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textField, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(textFieldPesquisarPaciente, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnPesquisarPaciente)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblCadastroDePacientes)
							.addGap(306))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblCadastroDePacientes)
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCdigo)
						.addComponent(textFieldCodigoPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(textFieldNomePaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldRgPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRg))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(textFieldCpfPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSexo)
						.addComponent(lblNascimento)
						.addComponent(textFieldNasciPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNmero)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEndereo)
						.addComponent(textFieldLogradouroPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCidade)
						.addComponent(textFieldCidadePaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldCepPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCep)
						.addComponent(textFieldEstadoPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEstado))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefone)
						.addComponent(textFieldTelefonePaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBairro)
						.addComponent(textFieldBairroPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textFieldEmailPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConvnio)
						.addComponent(textFieldConvenioPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelarPaciente, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExcluirPaciente, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNovoPaciente, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAlterarPaciente, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(butnSalvar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPesquisarPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisarPaciente))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(203, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

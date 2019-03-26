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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class FormCadPaciente extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodigoPaciente;
	private JTextField textFieldNomePaciente;
	private JTextField textFieldRgPaciente;
	private JTextField textFieldCpfPaciente;
	private JTextField textFieldEnderecoPaciente;
	private JTextField textFieldCidadePaciente;
	private JTextField textFieldEstadoPaciente;
	private JTextField textFieldCepPaciente;
	private JTextField textFieldBairroPaciente;
	private JTextField textFieldTelefonePaciente;
	private JTextField textFieldEmailPaciente;
	private JTextField textFieldConvenioPaciente;
	private JTextField textFieldPesquisarPaciente;
	private JTable table;

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
		setBounds(100, 100, 670, 688);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
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
		
		JRadioButton rdbtnMasculinoPaciente = new JRadioButton("Masculino");
		
		JRadioButton rdbtnFemininoPaciente = new JRadioButton("Feminino");
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o: ");
		
		textFieldEnderecoPaciente = new JTextField();
		textFieldEnderecoPaciente.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		
		textFieldCidadePaciente = new JTextField();
		textFieldCidadePaciente.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado: ");
		
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
		
		JButton btnSalvarPaciente = new JButton("Salvar");
		
		JButton btnExcluirPaciente = new JButton("Excluir");
		
		JButton btnCancelarPaciente = new JButton("Cancelar");
		
		textFieldPesquisarPaciente = new JTextField();
		textFieldPesquisarPaciente.setColumns(10);
		
		JButton btnPesquisarPaciente = new JButton("Pesquisar");
		
		table = new JTable();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCdigo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldCodigoPaciente, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNovoPaciente, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNome)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldNomePaciente, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblRg)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldRgPaciente, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCpf)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textFieldCpfPaciente, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
									.addComponent(lblSexo)
									.addGap(18)
									.addComponent(rdbtnMasculinoPaciente)
									.addGap(30)
									.addComponent(rdbtnFemininoPaciente)
									.addGap(52))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEndereo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldEnderecoPaciente, GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCidade)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textFieldCidadePaciente, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addGap(26)
									.addComponent(lblEstado)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textFieldEstadoPaciente, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblBairro)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldBairroPaciente, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblEmail)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldEmailPaciente, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCep, Alignment.TRAILING)
										.addComponent(lblTelefone, Alignment.TRAILING))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(textFieldCepPaciente, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
										.addComponent(textFieldTelefonePaciente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(lblConvnio)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textFieldConvenioPaciente, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textFieldPesquisarPaciente, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
							.addComponent(btnPesquisarPaciente))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAlterarPaciente)
							.addGap(41)
							.addComponent(btnSalvarPaciente)
							.addGap(34)
							.addComponent(btnExcluirPaciente)))
					.addGap(18)
					.addComponent(btnCancelarPaciente)
					.addGap(57))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 624, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(223, Short.MAX_VALUE)
					.addComponent(lblCadastroDePacientes)
					.addGap(198))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblCadastroDePacientes)
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCdigo)
						.addComponent(textFieldCodigoPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNovoPaciente, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(textFieldNomePaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRg)
						.addComponent(textFieldRgPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(textFieldCpfPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSexo)
						.addComponent(rdbtnMasculinoPaciente)
						.addComponent(rdbtnFemininoPaciente))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEndereo)
						.addComponent(textFieldEnderecoPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCidade)
						.addComponent(textFieldCidadePaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEstado)
						.addComponent(textFieldCepPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCep)
						.addComponent(textFieldEstadoPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBairro)
						.addComponent(textFieldBairroPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelefone)
						.addComponent(textFieldTelefonePaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textFieldEmailPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConvnio)
						.addComponent(textFieldConvenioPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAlterarPaciente)
						.addComponent(btnExcluirPaciente)
						.addComponent(btnSalvarPaciente)
						.addComponent(btnCancelarPaciente))
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPesquisarPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisarPaciente))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

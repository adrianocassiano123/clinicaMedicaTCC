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
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FormCadRecepcionistas extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodigoRecep;
	private JTextField textFieldNomeRecep;
	private JTextField textFieldRgRecep;
	private JTextField textFieldCpfPaciente;
	private JTextField textFieldLogradouroRecep;
	private JTextField textFieldCidadeRecep;
	private JTextField textFieldEstadoRecep;
	private JTextField textFieldCepRecep;
	private JTextField textFieldBairroRecep;
	private JTextField textFieldTelefoneRecep;
	private JTextField textFieldEmailRecep;
	private JTextField textFieldPesquisarRecep;
	private JTable tableRecep;
	private JTextField textFieldNumeroRecep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadRecepcionistas frame = new FormCadRecepcionistas();
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
	public FormCadRecepcionistas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		
		JLabel lblCadastroDePacientes = new JLabel("Cadastro de Recepcionistas");
		lblCadastroDePacientes.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		
		textFieldCodigoRecep = new JTextField();
		textFieldCodigoRecep.setColumns(10);
		
		JButton btnNovoRecep = new JButton("Novo");
		btnNovoRecep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JLabel lblNome = new JLabel("Nome:");
		
		textFieldNomeRecep = new JTextField();
		textFieldNomeRecep.setColumns(10);
		
		JLabel lblRg = new JLabel("RG: ");
		
		textFieldRgRecep = new JTextField();
		textFieldRgRecep.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF: ");
		
		textFieldCpfPaciente = new JTextField();
		textFieldCpfPaciente.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo: ");
		
		JLabel lblLogradouro = new JLabel("Logradouro");
		
		textFieldLogradouroRecep = new JTextField();
		textFieldLogradouroRecep.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		
		textFieldCidadeRecep = new JTextField();
		textFieldCidadeRecep.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado: ");
		
		textFieldEstadoRecep = new JTextField();
		textFieldEstadoRecep.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP: ");
		
		textFieldCepRecep = new JTextField();
		textFieldCepRecep.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro: ");
		
		textFieldBairroRecep = new JTextField();
		textFieldBairroRecep.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone: ");
		
		textFieldTelefoneRecep = new JTextField();
		textFieldTelefoneRecep.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail: ");
		
		textFieldEmailRecep = new JTextField();
		textFieldEmailRecep.setColumns(10);
		
		JButton btnAlterarRecep = new JButton("Alterar");
		
		textFieldPesquisarRecep = new JTextField();
		textFieldPesquisarRecep.setColumns(10);
		
		JButton btnPesquisarRecep = new JButton("Pesquisar");
		
		JButton butnSalvarRecep = new JButton("Salvar");
		
		JButton btnExcluirRecep = new JButton("Excluir");
		
		JButton btnCancelarRecep = new JButton("Cancelar");
		
		JLabel lblNmero = new JLabel("N\u00FAmero: ");
		
		textFieldNumeroRecep = new JTextField();
		textFieldNumeroRecep.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino"}));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblCdigo)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldCodigoRecep, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNome)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldNomeRecep, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblRg)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldRgRecep, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblCpf)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textFieldCpfPaciente, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
										.addComponent(lblSexo)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblLogradouro)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldLogradouroRecep, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE)
										.addGap(26)
										.addComponent(lblNmero)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldNumeroRecep, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblCidade)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(textFieldCidadeRecep, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addComponent(lblBairro)
													.addComponent(lblEmail))
												.addGap(18)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPane.createSequentialGroup()
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(btnNovoRecep, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
															.addComponent(btnAlterarRecep, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
															.addGap(66))
														.addGroup(gl_contentPane.createSequentialGroup()
															.addComponent(textFieldEmailRecep, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED)))
													.addComponent(textFieldBairroRecep, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
													.addComponent(lblTelefone)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblEstado)
														.addGap(18)
														.addComponent(textFieldEstadoRecep, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
													.addComponent(textFieldTelefoneRecep, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblCep)
														.addGap(18)
														.addComponent(textFieldCepRecep, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(28)
												.addComponent(butnSalvarRecep, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
												.addGap(96)
												.addComponent(btnExcluirRecep, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
												.addComponent(btnCancelarRecep, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))))
								.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(textFieldPesquisarRecep, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
								.addGap(18)
								.addComponent(btnPesquisarRecep)
								.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(lblCadastroDePacientes)
								.addGap(306)))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblCadastroDePacientes)
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCdigo)
						.addComponent(textFieldCodigoRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(textFieldNomeRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRg)
						.addComponent(textFieldRgRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(textFieldCpfPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSexo)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLogradouro)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(textFieldLogradouroRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNmero)
							.addComponent(textFieldNumeroRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCidade)
						.addComponent(textFieldCidadeRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldCepRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCep)
						.addComponent(textFieldEstadoRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEstado))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldTelefoneRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBairro)
						.addComponent(textFieldBairroRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelefone))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textFieldEmailRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(butnSalvarRecep, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnExcluirRecep, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnCancelarRecep, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNovoRecep, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAlterarRecep, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPesquisarRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisarRecep))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tableRecep = new JTable();
		tableRecep.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(tableRecep);
		contentPane.setLayout(gl_contentPane);
	}
}

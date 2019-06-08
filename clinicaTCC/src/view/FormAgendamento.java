package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import ModeloConexao.ConexaoBD;
import modelo.BeanMedico;
import modelo.ModeloTabela;
import modeloDao.DaoMedico;
import relatorios.Relatorio;

public class FormAgendamento extends JFrame {

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCrm;
	private JComboBox<?> comboBoxEspecialidade;
	int flag = 0;

	/**
	 * Launch the application.
	 */

	BeanMedico modMedico = new BeanMedico();
	DaoMedico control = new DaoMedico();
	ConexaoBD conexao = new ConexaoBD();
	private JTextField txtEspecialidade;
	private JTextField textFieldPesquisa;

	JButton btnPesquisar = new JButton("Pesquisar");
	JButton btnSalvarCadMedico = new JButton("Salvar");
	JButton btnCancelar = new JButton("Cancelar");
	JButton btnNovoCadMedico = new JButton("Novo");
	JButton btnExcluir = new JButton("Excluir ");
	JButton btnEditarCadMedico = new JButton("Editar");
	private JLabel lblCodigo;
	private JTextField textFieldCodMedico;
	private JTable tableMedicos = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	private JButton btnExcluirMous;
	Relatorio relatMedico = new Relatorio();

	public void preencherTabela(String Sql) {

		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "ID", "NOME", "ESPECIALIDADE", "CRM" };
		conexao.conexao();
		conexao.executarSQL(Sql);

		try {
			conexao.rs.first(); // Seta o primeiro registro

			do {
				dados.add(new Object[] { conexao.rs.getInt("idmedico"), conexao.rs.getString("nomemedico"),
						conexao.rs.getString("especialidademedico"), conexao.rs.getInt("crmmedico") });

			} while (conexao.rs.next());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, "Faça outra Pesquisa!");
		}

		ModeloTabela modeloTabela = new ModeloTabela(dados, colunas);
		contentPane.add(scrollPane);
		tableMedicos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableMedicos.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				String nomeMedico = "" + tableMedicos.getValueAt(tableMedicos.getSelectedRow(), 1);
				conexao.conexao();
				conexao.executarSQL("select *from tabmedico where nomemedico= '" + nomeMedico + "'");

				try {

					conexao.rs.first();
					textFieldCodMedico.setText(String.valueOf(conexao.rs.getInt("idmedico")));
					textFieldNome.setText(conexao.rs.getString("nomemedico"));
					txtEspecialidade.setText(conexao.rs.getString("especialidademedico"));
					textFieldCrm.setText(String.valueOf(conexao.rs.getInt("crmmedico")));

//					modMedico.setPesquisa(textFieldNome.getText().toUpperCase());
//					BeanMedico model = control.pesquisarMedico(modMedico);

					btnEditarCadMedico.setEnabled(true);
					btnNovoCadMedico.setEnabled(false);
					btnCancelar.setEnabled(true);
					btnExcluir.setVisible(false);
					btnExcluirMous.setEnabled(true);

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro ao acessar dados" + e);
				}

				conexao.desconectar();

			}
		});

		tableMedicos.setModel(modeloTabela);
		tableMedicos.getColumnModel().getColumn(0).setPreferredWidth(30); // Zero é o primeiro registro
		tableMedicos.getColumnModel().getColumn(0).setResizable(false); // Não permite esticar a tabela com o mouse
		tableMedicos.getColumnModel().getColumn(1).setPreferredWidth(180);
		tableMedicos.getColumnModel().getColumn(1).setResizable(false);
		tableMedicos.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableMedicos.getColumnModel().getColumn(2).setResizable(false);
		tableMedicos.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableMedicos.getColumnModel().getColumn(3).setResizable(false);

		tableMedicos.getTableHeader().setReorderingAllowed(false); // Não permite reordenar o cabeçalho
		tableMedicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite selecionar apenas um dado por

		conexao.desconectar();

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAgendamento frame = new FormAgendamento();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormAgendamento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		preencherTabela("select *from tabmedico order by nomemedico");

		JLabel lblCadastroDeMdicos = new JLabel("Marca\u00E7\u00E3o de Consulta");
		lblCadastroDeMdicos.setFont(new Font("Tahoma", Font.BOLD, 21));

		btnEditarCadMedico.setEnabled(false);
		btnEditarCadMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;
				textFieldNome.setEnabled(true);
				txtEspecialidade.setEnabled(true);
				textFieldCrm.setEnabled(true);
				comboBoxEspecialidade.setEnabled(true);
				btnCancelar.setEnabled(true);
				btnSalvarCadMedico.setEnabled(true);
				btnEditarCadMedico.setEnabled(false);
				btnNovoCadMedico.setEnabled(false);
				btnExcluir.setEnabled(false);

			}
		});

		btnSalvarCadMedico.setEnabled(false);
		btnSalvarCadMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (textFieldNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Nome ");

				} else if (textFieldCrm.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o CRM ");

				} else {

					if (flag == 1) {

						modMedico.setNome(textFieldNome.getText().toUpperCase()); // .toUpperCase() to uppercase para
						modMedico.setCrm(Integer.parseInt(textFieldCrm.getText()));
						modMedico.setEspecialidade(comboBoxEspecialidade.getSelectedItem().toString().toUpperCase());

						control.salvar(modMedico);

						textFieldNome.setText("");
						txtEspecialidade.setText("");
						textFieldCrm.setText("");
						textFieldCodMedico.setText("");
						textFieldNome.setEnabled(false);
						txtEspecialidade.setEnabled(false);
						comboBoxEspecialidade.setEnabled(false);
						textFieldCrm.setEnabled(false);
						btnSalvarCadMedico.setEnabled(false);
						preencherTabela("select *from tabmedico order by nomemedico");

					} else {
						modMedico.setCodigo(Integer.parseInt(textFieldCodMedico.getText()));
						modMedico.setNome(textFieldNome.getText().toUpperCase());
						modMedico.setEspecialidade(txtEspecialidade.getText());
						modMedico.setCrm(Integer.parseInt(textFieldCrm.getText()));
						control.editarMedico(modMedico);
						textFieldNome.setText("");
						txtEspecialidade.setText("");
						textFieldCrm.setText("");
						textFieldCodMedico.setText("");
						textFieldNome.setEnabled(false);
						txtEspecialidade.setEnabled(false);
						comboBoxEspecialidade.setEnabled(false);
						textFieldCrm.setEnabled(false);
						btnSalvarCadMedico.setEnabled(false);
						btnNovoCadMedico.setEnabled(true);
						btnCancelar.setEnabled(false);
						preencherTabela("select *from tabmedico order by nomemedico");

					}
				}
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textFieldNome.setEnabled(false);
				txtEspecialidade.setEnabled(false);
				textFieldCrm.setEnabled(false);
				comboBoxEspecialidade.setEnabled(false);
				btnCancelar.setEnabled(false);
				btnNovoCadMedico.setEnabled(true);
				btnEditarCadMedico.setEnabled(false);
				btnSalvarCadMedico.setEnabled(false);
				btnExcluir.setEnabled(false);
				textFieldNome.setText("");
				txtEspecialidade.setText("");
				textFieldCrm.setText("");
				textFieldCodMedico.setText("");

			}
		});

		btnNovoCadMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 1;
				textFieldNome.setEnabled(true);
				txtEspecialidade.setEnabled(true);
				textFieldCrm.setEnabled(true);
				comboBoxEspecialidade.setEnabled(true);
				btnCancelar.setEnabled(true);
				btnSalvarCadMedico.setEnabled(true);

			}
		});

		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = 0;
				resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir?");
				if (resposta == JOptionPane.YES_NO_OPTION) {
					modMedico.setCodigo(Integer.parseInt(textFieldCodMedico.getText()));
					control.excluir();
					textFieldNome.setText("");
					txtEspecialidade.setText("");
					textFieldCrm.setText("");
					textFieldCodMedico.setText("");
					btnEditarCadMedico.setEnabled(false);
					btnExcluir.setEnabled(false);
					preencherTabela("select *from tabmedico order by nomemedico");
				}

			}

		}

		);

		JLabel lblNome = new JLabel("Paciente :");

		JLabel lblCrm = new JLabel("CRM:");

		textFieldNome = new JTextField();
		textFieldNome.setEnabled(false);
		textFieldNome.setColumns(10);

		JLabel lblEspecialidade = new JLabel("Especialidade: ");

		textFieldCrm = new JTextField();
		textFieldCrm.setEnabled(false);
		textFieldCrm.setColumns(10);

		txtEspecialidade = new JTextField();
		txtEspecialidade.setColumns(10);

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setColumns(10);

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modMedico.setPesquisa(textFieldPesquisa.getText().toUpperCase());
				BeanMedico model = control.pesquisarMedico(modMedico);
				textFieldNome.setText(model.getNome());
				textFieldCodMedico.setText(String.valueOf(model.getCodigo()));
				textFieldCrm.setText(String.valueOf(model.getCrm()));
				txtEspecialidade.setText(model.getEspecialidade());
				btnEditarCadMedico.setEnabled(true);
				btnExcluir.setEnabled(true);
				btnNovoCadMedico.setEnabled(false);
				btnCancelar.setEnabled(true);
				textFieldPesquisa.setText("");
				preencherTabela("select *from tabmedico where nomemedico like'%" + modMedico.getPesquisa() + "%'"); // Mostra
																													// todos
																													// os
																													// médicos
																													// de
																													// acordo
																													// com
																													// a
																													// letra
																													// pesquisada

			}
		});

//		JComboBox comboBoxEspecialidade = new JComboBox();
//		comboBoxEspecialidade.setModel(new DefaultComboBoxModel(new String[] {"Dermatologista", "Cirurgi\u00E3o", "Ginecologista", "Urologista", "Cardiologista", "Fisioterapeuta", "Ortopedista", "Dentista"}));

		String especiali[] = { "Dermatologista", "Ginecologista", "Ortopedista" };
		comboBoxEspecialidade = new JComboBox<Object>(especiali);
		comboBoxEspecialidade.setEnabled(false);

		JLabel lblListaEspecialidade = new JLabel("Lista Especialidade");

		lblCodigo = new JLabel("Codigo: ");

		textFieldCodMedico = new JTextField();
		textFieldCodMedico.setEnabled(false);
		textFieldCodMedico.setColumns(10);

		scrollPane = new JScrollPane();

		// Polimorfismo
		btnExcluirMous = new JButton("Excluir");
		btnExcluirMous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				control.excluir(Integer.parseInt(textFieldCodMedico.getText()));
				preencherTabela("select *from tabmedico order by nomemedico");

			}
		});

		JButton btnRelatorio = new JButton("Relatorio");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				relatMedico.relatMedico();

			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(290)
					.addComponent(lblCadastroDeMdicos)
					.addContainerGap(253, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCodigo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldCodMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(592))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNome)
										.addComponent(lblCrm))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(textFieldNome, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textFieldCrm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNovoCadMedico, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addGap(46)
									.addComponent(btnEditarCadMedico, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
									.addComponent(btnSalvarCadMedico, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnPesquisar)
								.addComponent(lblEspecialidade))
							.addGap(4)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblListaEspecialidade)
									.addPreferredGap(ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
									.addComponent(comboBoxEspecialidade, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnExcluirMous)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnExcluir)
											.addGap(95))
										.addComponent(txtEspecialidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRelatorio)
									.addGap(33)
									.addComponent(btnCancelar)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(23))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(160, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastroDeMdicos)
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigo)
						.addComponent(textFieldCodMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnExcluir)
						.addComponent(btnEditarCadMedico)
						.addComponent(btnNovoCadMedico)
						.addComponent(btnSalvarCadMedico)
						.addComponent(btnExcluirMous)
						.addComponent(btnRelatorio))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEspecialidade)
						.addComponent(lblNome)
						.addComponent(txtEspecialidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldCrm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCrm)
						.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar)
						.addComponent(comboBoxEspecialidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblListaEspecialidade))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(200, Short.MAX_VALUE))
		);
		scrollPane.setViewportView(tableMedicos);

		// tableMedicos = new JTable();
		// tabbedPane.addTab("New tab", null, tableMedicos, null);
		contentPane.setLayout(gl_contentPane);

	}
}

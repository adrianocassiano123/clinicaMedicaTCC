package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.border.LineBorder;

import ModeloConexao.ConexaoBD;
import modelo.BeanMedico;
import modelo.ModeloTabela;
import modeloDao.DaoMedico;
import relatorios.Relatorio;

public class FormCadMedico extends JFrame {


	
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCrm;
	private JComboBox<?> comboBoxEspecialidade;
	int flag = 0;

	BeanMedico modMedico = new BeanMedico();
	DaoMedico control = new DaoMedico();
	ConexaoBD conexao = new ConexaoBD();
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
	private JPanel panel;
	private JLabel label;
	private JPanel panel_1;

	public void preencherTabela() {

		String Sql = " SELECT tabmedico.idmedico as id, " + 
				"  tabmedico.nomemedico as nome,  " + 
				"  tab_especialidade.tipo_especialidade as especialidade, " + 
				"  tabmedico.crmmedico as crm " + 
				"  FROM tab_especialidade, " + 
				"	    tabmedico " + 
				" WHERE tabmedico.especialidadefk = tab_especialidade.id_especialidade ";
		
		
		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "ID", "NOME", "ESPECIALIDADE", "CRM" };
		conexao.conexao();
		conexao.executarSQL(Sql);

		try {
			conexao.rs.first(); // Seta o primeiro registro

			do {
				dados.add(new Object[] { 
												conexao.rs.getInt("id")
											  , conexao.rs.getString("nome")
											  , conexao.rs.getString("especialidade")
											  , conexao.rs.getInt("crm") 
										});

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
				conexao.executarSQL(" SELECT tabmedico.idmedico, " + 
						"	tabmedico.nomemedico, " + 
						"	tab_especialidade.tipo_especialidade, " + 
						"	tabmedico.crmmedico " + 
						" FROM tab_especialidade, " + 
						"	tabmedico " + 
						" WHERE tabmedico.especialidadefk = tab_especialidade.id_especialidade " + 
						" AND nomemedico= '" + nomeMedico + "' ");

				try {

					conexao.rs.first();
					textFieldCodMedico.setText(String.valueOf(conexao.rs.getInt("idmedico")));
					textFieldNome.setText(conexao.rs.getString("nomemedico"));
					comboBoxEspecialidade.setSelectedItem(conexao.rs.getString("tipo_especialidade"));
					textFieldCrm.setText(String.valueOf(conexao.rs.getInt("crmmedico")));
					



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
					FormCadMedico frame = new FormCadMedico();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void preencherEspecialidade() {

		
		List<String> strList = new ArrayList<String>();
		List<Integer> idList = new ArrayList<Integer>();

		conexao.conexao();
		conexao.executarSQL("select tipo_especialidade, id_especialidade as id from tab_especialidade");

		try {
			conexao.rs.first();
			
			do {

				strList.add(conexao.rs.getString("tipo_especialidade"));
				idList.add(conexao.rs.getInt("id"));

			} while (conexao.rs.next());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro no comboBox");
			System.out.println(e);
		}

		comboBoxEspecialidade = new JComboBox<>(strList.toArray());

		conexao.desconectar();

	}

	public FormCadMedico() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		preencherEspecialidade();

		preencherTabela();

		btnEditarCadMedico.setEnabled(false);
		btnEditarCadMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;
				textFieldNome.setEnabled(true);

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

						modMedico.setNome(textFieldNome.getText().toUpperCase());
						modMedico.setCrm(Integer.parseInt(textFieldCrm.getText()));
						
						String especialidade = comboBoxEspecialidade.getSelectedItem().toString();
						
						modMedico.setEspecialidade(pegarIdEspecialidade(especialidade));
						
						
						control.salvar(modMedico);

						textFieldNome.setText("");
						textFieldCrm.setText("");
						textFieldCodMedico.setText("");
						textFieldNome.setEnabled(false);
						comboBoxEspecialidade.setEnabled(false);
						textFieldCrm.setEnabled(false);
						btnSalvarCadMedico.setEnabled(false);
						//preencherTabela("select *from tabmedico order by nomemedico");

					} else {
						modMedico.setCodigo(Integer.parseInt(textFieldCodMedico.getText()));
						modMedico.setNome(textFieldNome.getText().toUpperCase());

						modMedico.setCrm(Integer.parseInt(textFieldCrm.getText()));
						control.editarMedico(modMedico);
						textFieldNome.setText("");

						textFieldCrm.setText("");
						textFieldCodMedico.setText("");
						textFieldNome.setEnabled(false);

						comboBoxEspecialidade.setEnabled(false);
						textFieldCrm.setEnabled(false);
						btnSalvarCadMedico.setEnabled(false);
						btnNovoCadMedico.setEnabled(true);
						btnCancelar.setEnabled(false);
						//preencherTabela("select *from tabmedico order by nomemedico");

					}
				}
			}

			private int pegarIdEspecialidade(String especilidade) {
				int codEspecialidade = 0;
				//String id;
				conexao.conexao();
				conexao.executarSQL("select *from tab_especialidade where tipo_especialidade like'%" + especilidade + "%'");

				try {
					conexao.rs.first();
					
					do {

						codEspecialidade = conexao.rs.getInt("id_especialidade");
						

					} while (conexao.rs.next());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro no comboBox" + e);
				}finally {
					
					conexao.desconectar();
				}	
				return codEspecialidade;	
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textFieldNome.setEnabled(false);

				textFieldCrm.setEnabled(false);
				comboBoxEspecialidade.setEnabled(false);
				btnCancelar.setEnabled(false);
				btnNovoCadMedico.setEnabled(true);
				btnEditarCadMedico.setEnabled(false);
				btnSalvarCadMedico.setEnabled(false);
				btnExcluir.setEnabled(false);
				textFieldNome.setText("");

				textFieldCrm.setText("");
				textFieldCodMedico.setText("");

			}
		});

		btnNovoCadMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 1;
				textFieldNome.setEnabled(true);

				textFieldCrm.setEnabled(true);
				comboBoxEspecialidade.setEnabled(true);
				btnCancelar.setEnabled(true);
				btnSalvarCadMedico.setEnabled(true);

			}
		});

		btnExcluir.setEnabled(false);
		btnExcluir.setVisible(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = 0;
				resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir?");
				if (resposta == JOptionPane.YES_NO_OPTION) {
					modMedico.setCodigo(Integer.parseInt(textFieldCodMedico.getText()));
					control.excluir();
					textFieldNome.setText("");

					textFieldCrm.setText("");
					textFieldCodMedico.setText("");
					btnEditarCadMedico.setEnabled(false);
					btnExcluir.setEnabled(false);
					// preencherTabela("select *from tabmedico order by nomemedico");
				}

			}

		}

		);

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setColumns(10);

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modMedico.setPesquisa(textFieldPesquisa.getText().toUpperCase());
				BeanMedico model = control.pesquisarMedico(modMedico);
				textFieldNome.setText(model.getNome());
				textFieldCodMedico.setText(String.valueOf(model.getCodigo()));
				textFieldCrm.setText(String.valueOf(model.getCrm()));

				btnEditarCadMedico.setEnabled(true);
				btnExcluir.setEnabled(true);
				btnNovoCadMedico.setEnabled(false);
				btnCancelar.setEnabled(true);
				textFieldPesquisa.setText("");
				// preencherTabela("select *from tabmedico where nomemedico like'%" + modMedico.getPesquisa() + "%'"); // Mostra
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


		lblCodigo = new JLabel("Codigo: ");

		lblCodigo.setVisible(false);
		textFieldCodMedico = new JTextField();
		textFieldCodMedico.setEnabled(false);
		textFieldCodMedico.setColumns(10);
		textFieldCodMedico.setVisible(false);

		scrollPane = new JScrollPane();

		// Polimorfismo
		btnExcluirMous = new JButton("Excluir");
		btnExcluirMous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				control.excluir(Integer.parseInt(textFieldCodMedico.getText()));
				// preencherTabela("select *from tabmedico order by nomemedico");

			}
		});

		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaption));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblCodigo)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textFieldCodMedico,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 827,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE,
																716, GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(btnPesquisar, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addComponent(scrollPane, Alignment.TRAILING,
														GroupLayout.PREFERRED_SIZE, 820, GroupLayout.PREFERRED_SIZE))))
						.addGap(31))
						.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnNovoCadMedico, GroupLayout.PREFERRED_SIZE, 91,
												GroupLayout.PREFERRED_SIZE)
										.addGap(61)
										.addComponent(btnEditarCadMedico, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
										.addGap(79)
										.addComponent(btnSalvarCadMedico, GroupLayout.PREFERRED_SIZE, 86,
												GroupLayout.PREFERRED_SIZE)
										.addGap(70)
										.addComponent(btnExcluirMous, GroupLayout.PREFERRED_SIZE, 82,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnExcluir)
										.addGap(118).addComponent(btnCancelar))
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 819,
										GroupLayout.PREFERRED_SIZE))
								.addContainerGap(39, Short.MAX_VALUE)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE).addGap(12)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblCodigo).addComponent(
						textFieldCodMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(38)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovoCadMedico, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExcluirMous, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalvarCadMedico, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditarCadMedico, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
				.addGap(47)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnPesquisar)
						.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(142, Short.MAX_VALUE)));

		JLabel lblNome = new JLabel("Nome:");

		textFieldNome = new JTextField();
		textFieldNome.setEnabled(false);
		textFieldNome.setColumns(10);

		JLabel lblEspecialidade = new JLabel("Especialidade: ");

		comboBoxEspecialidade.setEnabled(false);

		JLabel lblCrm = new JLabel("CRM:");

		textFieldCrm = new JTextField();
		textFieldCrm.setEnabled(false);
		textFieldCrm.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblNome)
					.addGap(11)
					.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblEspecialidade)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBoxEspecialidade, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblCrm)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldCrm, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(lblCrm)
						.addComponent(comboBoxEspecialidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEspecialidade)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldCrm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);

		label = new JLabel("Cadastro de M\u00E9dicos");
		label.setFont(new Font("Tahoma", Font.BOLD, 21));
		panel.add(label);
		scrollPane.setViewportView(tableMedicos);

		// tableMedicos = new JTable();
		// tabbedPane.addTab("New tab", null, tableMedicos, null);
		contentPane.setLayout(gl_contentPane);

	}
}

package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
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
import modelo.BeanEspecialidade;
import modelo.ModeloTabela;
import modeloDao.DAOEspecialidade;

public class FormEspecialidade extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldValor;
	int flag = 0;

	BeanEspecialidade especialidade = new BeanEspecialidade();
	DAOEspecialidade dao = new DAOEspecialidade();
	ConexaoBD conexao = new ConexaoBD();
	private JTextField txtEspecialidade;
	private JTextField textFieldPesquisa;

	JButton btnPesquisar = new JButton("Pesquisar");
	JButton btnSalvarEspecialidade = new JButton("Salvar");
	JButton btnCancelar = new JButton("Cancelar");
	JButton btnNovaEspecialidade = new JButton("Novo");
	private JLabel lblCodigo;
	private JTextField textFieldCodEspecialidade;
	private JTable tableEspecialidade = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	
	
	

	public void preencherTabela(String Sql) {

		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "ID","ESPECIALIDADE", "MOEDA" ,"VALOR" };
		conexao.conexao();
		conexao.executarSQL(Sql);

		try {
			conexao.rs.first(); // Seta o primeiro registro

			do {
				dados.add(new Object[] { conexao.rs.getInt("id_especialidade"), conexao.rs.getString("tipo_especialidade"),"R$", conexao.rs.getBigDecimal("valor") });
				
			} while (conexao.rs.next());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, "Faça outra Pesquisa!");
		}

		ModeloTabela modeloTabela = new ModeloTabela(dados, colunas);
		contentPane.add(scrollPane);
		tableEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableEspecialidade.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				String especialidade = "" + tableEspecialidade.getValueAt(tableEspecialidade.getSelectedRow(), 1);
				conexao.conexao();
				conexao.executarSQL("select *from tab_especialidade where tipo_especialidade= '" + especialidade + "'");
				flag=2;
				try {

					conexao.rs.first();
					textFieldCodEspecialidade.setText(String.valueOf(conexao.rs.getInt("id_especialidade")));
					
					textFieldValor.setText(conexao.rs.getString("valor"));
										
									
					txtEspecialidade.setText(conexao.rs.getString("tipo_especialidade"));
					btnNovaEspecialidade.setEnabled(false);
					btnCancelar.setEnabled(true);
					textFieldValor.setEnabled(true);
					txtEspecialidade.setEditable(true);
					btnSalvarEspecialidade.setEnabled(true);

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro ao acessar dados" + e);
				}

				conexao.desconectar();

			}
		});

		tableEspecialidade.setModel(modeloTabela);
		tableEspecialidade.getColumnModel().getColumn(0).setPreferredWidth(30); // Zero é o primeiro registro
		tableEspecialidade.getColumnModel().getColumn(0).setResizable(false); // Não permite esticar a tabela com o mouse
		tableEspecialidade.getColumnModel().getColumn(1).setPreferredWidth(180);
		tableEspecialidade.getColumnModel().getColumn(1).setResizable(false);
		tableEspecialidade.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableEspecialidade.getColumnModel().getColumn(2).setResizable(false);
		

		tableEspecialidade.getTableHeader().setReorderingAllowed(false); // Não permite reordenar o cabeçalho
		tableEspecialidade.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite selecionar apenas um dado
																					// por

		conexao.desconectar();

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormEspecialidade frame = new FormEspecialidade();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormEspecialidade() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 688, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		preencherTabela("select *from tab_especialidade order by tipo_especialidade");
		
		
		try {
			javax.swing.text.MaskFormatter valor = new javax.swing.text.MaskFormatter("##.##"); // Mascara Monetário
			textFieldValor = new javax.swing.JFormattedTextField(valor);
			textFieldValor.setEnabled(false);
		} catch (Exception e) {
		}

		btnSalvarEspecialidade.setEnabled(false);
		btnSalvarEspecialidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				

					if (flag == 1) {

						especialidade.setValor(new BigDecimal(textFieldValor.getText()));
						especialidade.setTipo(txtEspecialidade.getText());
						

						dao.salvar(especialidade);

						textFieldValor.setText("");
						txtEspecialidade.setText("");
						textFieldCodEspecialidade.setText("");
						textFieldValor.setEnabled(false);
						txtEspecialidade.setEnabled(false);
						btnSalvarEspecialidade.setEnabled(false);
						tableEspecialidade.setEnabled(true);
						
						preencherTabela("select *from tab_especialidade order by tipo_especialidade");

					} else {
						especialidade.setIdEspecialide(Integer.parseInt(textFieldCodEspecialidade.getText()));
						especialidade.setValor(new BigDecimal(textFieldValor.getText()));
						especialidade.setTipo(txtEspecialidade.getText());
						

						dao.editarEspecialidade(especialidade);

						textFieldValor.setText("");
						txtEspecialidade.setText("");
						textFieldCodEspecialidade.setText("");
						textFieldValor.setEnabled(false);
						txtEspecialidade.setEnabled(false);
						btnSalvarEspecialidade.setEnabled(false);
						tableEspecialidade.setEnabled(true);
						preencherTabela("select *from tab_especialidade order by tipo_especialidade");

					}
				}
			
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textFieldValor.setEnabled(false);
				txtEspecialidade.setEnabled(false);
				btnCancelar.setEnabled(false);
				btnNovaEspecialidade.setEnabled(true);
				btnSalvarEspecialidade.setEnabled(false);
				textFieldValor.setText("");
				txtEspecialidade.setText("");
				textFieldCodEspecialidade.setText("");

			}
		});

		btnNovaEspecialidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 1;
				textFieldValor.setEnabled(true);
				txtEspecialidade.setEnabled(true);
				btnCancelar.setEnabled(true);
				btnSalvarEspecialidade.setEnabled(true);
				tableEspecialidade.setEnabled(false);

			}
		});
		
		

		textFieldValor = new JTextField();
		
		textFieldValor.setColumns(10);

		JLabel lblEspecialidade = new JLabel("Especialidade: ");
		lblEspecialidade.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtEspecialidade = new JTextField();
		txtEspecialidade.setEnabled(false);
		txtEspecialidade.setColumns(10);

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setColumns(10);
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				especialidade.setPesquisa(textFieldPesquisa.getText());
				BeanEspecialidade model = dao.pesquisarEspecialidade(especialidade);
				textFieldValor.setText(String.valueOf(model.getValor()));
				textFieldCodEspecialidade.setText(String.valueOf(model.getIdEspecialide()));
				txtEspecialidade.setText(model.getTipo());
				
				btnNovaEspecialidade.setEnabled(false);
				btnCancelar.setEnabled(true);
				textFieldPesquisa.setText("");
				preencherTabela("select *from tab_especialidade where tipo_especialidade like'%" + especialidade.getPesquisa() + "%'"); // Mostra
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

		textFieldCodEspecialidade = new JTextField();
		textFieldCodEspecialidade.setEnabled(false);
		textFieldCodEspecialidade.setColumns(10);

		scrollPane = new JScrollPane();

		JLabel lblValor = new JLabel("Valor :");
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 14));

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);

		JLabel lblCadastrarEspecialidade = new JLabel("Cadastrar Especialidade");
		lblCadastrarEspecialidade.setFont(new Font("Tahoma", Font.BOLD, 21));
		panel.add(lblCadastrarEspecialidade);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCodigo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldCodEspecialidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEspecialidade)
								.addComponent(txtEspecialidade, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
							.addGap(166)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldValor, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblValor, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNovaEspecialidade, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSalvarEspecialidade)
							.addGap(18)
							.addComponent(btnCancelar)
							.addGap(37)
							.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnPesquisar))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
						.addComponent(scrollPane))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigo)
						.addComponent(textFieldCodEspecialidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovaEspecialidade, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalvarEspecialidade, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEspecialidade)
						.addComponent(lblValor))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEspecialidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		scrollPane.setViewportView(tableEspecialidade);
		contentPane.setLayout(gl_contentPane);

	}
}

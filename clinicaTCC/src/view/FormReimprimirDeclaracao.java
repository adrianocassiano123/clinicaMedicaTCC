package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import modelo.BeanMarcConsulta;
import modelo.BeanPaciente;
import modelo.ModeloTabela;
import modeloDao.DaoMarcConsulta;
import relatorios.Relatorio;

public class FormReimprimirDeclaracao extends JFrame {

	private JPanel contentPane;
	
	BeanMarcConsulta paciente = new BeanMarcConsulta();
	DaoMarcConsulta dao = new DaoMarcConsulta();
	ConexaoBD conexao = new ConexaoBD();
	private JTextField textFieldPesquisa;
	JButton btnPesquisar = new JButton("Pesquisar");
	JButton btnImprimir = new JButton("Imprimir");
	JButton btnCancelar = new JButton("Cancelar");
	private JLabel lblCodigo;
	private JTextField textFieldIdMarcacao;
	private JTable tableReimprimirDeclaracao = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	BeanMarcConsulta marcacao = new BeanMarcConsulta();
	Relatorio relatorio = new Relatorio();
	
	
	

	public void preencherTabela(String Sql) {

		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "ID","PACIENTE", "MEDICO", "DATA" };
		conexao.conexao();
		conexao.executarSQL(Sql);

		try {
			conexao.rs.first(); // Seta o primeiro registro

			do {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				dados.add(new Object[] {  conexao.rs.getInt("id")
										, conexao.rs.getString("paciente")
										, conexao.rs.getString("medico")
										, sdf.format(conexao.rs.getDate("data")) 
									 });
				
			} while (conexao.rs.next());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, "Faça outra Pesquisa!");
		}

		ModeloTabela modeloTabela = new ModeloTabela(dados, colunas);
		contentPane.add(scrollPane);
		tableReimprimirDeclaracao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableReimprimirDeclaracao.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				String declaracao = "" + tableReimprimirDeclaracao.getValueAt(tableReimprimirDeclaracao.getSelectedRow(), 1);
				
				System.out.println(declaracao);
				
				
				conexao.conexao();
				conexao.executarSQL("SELECT tab_marcacao.id_marcacao as id," + 
						"               tab_paciente.nome_paciente as paciente," + 
						"    	        tabmedico.nomemedico as medico," + 
						"	            tab_marcacao.data_marcacao as data" + 
						
								" FROM  tab_marcacao," + 
								"	    tab_paciente," + 
								"	    tabmedico" + 
						
								" WHERE tab_marcacao.cod_paciente_marcacao = tab_paciente.id_paciente " + 
								"	AND tab_marcacao.cod_medico_marcacao = tabmedico.idmedico " + 
								"	AND tab_marcacao.cod_paciente_marcacao = tab_paciente.id_paciente"+
								"	AND tab_marcacao.status_marcacao LIKE 'Finalizado'"+
								"	AND nome_paciente like'%" + declaracao + "%'");
				
				try {
					conexao.rs.first();
					textFieldIdMarcacao.setText(String.valueOf(conexao.rs.getInt("id")));
					
					relatorio.SolicitarDeclaracao(Integer.parseInt(textFieldIdMarcacao.getText()));
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Não existe Solicitação de Exame para esse Paciente");
					System.out.println(e);
				}

				conexao.desconectar();

			
				//textFieldIdMarcacao.setText(String.valueOf());
			
				
				

			}
		});

		tableReimprimirDeclaracao.setModel(modeloTabela);
		tableReimprimirDeclaracao.getColumnModel().getColumn(0).setPreferredWidth(30); // Zero é o primeiro registro
		tableReimprimirDeclaracao.getColumnModel().getColumn(0).setResizable(false); // Não permite esticar a tabela com o mouse
		tableReimprimirDeclaracao.getColumnModel().getColumn(1).setPreferredWidth(180);
		tableReimprimirDeclaracao.getColumnModel().getColumn(1).setResizable(false);
		tableReimprimirDeclaracao.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableReimprimirDeclaracao.getColumnModel().getColumn(2).setResizable(false);
		tableReimprimirDeclaracao.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableReimprimirDeclaracao.getColumnModel().getColumn(3).setResizable(false);
		

		tableReimprimirDeclaracao.getTableHeader().setReorderingAllowed(false); // Não permite reordenar o cabeçalho
		tableReimprimirDeclaracao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite selecionar apenas um dado
																					// por

		conexao.desconectar();

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormReimprimirDeclaracao frame = new FormReimprimirDeclaracao();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormReimprimirDeclaracao() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 688, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		preencherTabela("SELECT tab_marcacao.id_marcacao as id," + 
				"               tab_paciente.nome_paciente as paciente," + 
				"    	        tabmedico.nomemedico as medico," + 
				"	            tab_marcacao.data_marcacao as data" + 
				
						" FROM  tab_marcacao," + 
						"	    tab_paciente," + 
						"	    tabmedico" + 
				
						" WHERE tab_marcacao.cod_paciente_marcacao = tab_paciente.id_paciente " + 
						"	AND tab_marcacao.cod_medico_marcacao = tabmedico.idmedico " +
						"	AND tab_marcacao.status_marcacao LIKE 'Finalizado'"+
						"	AND tab_marcacao.cod_paciente_marcacao = tab_paciente.id_paciente");

		btnImprimir.setEnabled(false);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				} 
				
			
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();

			}
		});

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setColumns(10);
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});


		lblCodigo = new JLabel("Codigo: ");
		lblCodigo.setVisible(false);
		textFieldIdMarcacao = new JTextField();
		textFieldIdMarcacao.setEnabled(false);
		textFieldIdMarcacao.setColumns(10);
		textFieldIdMarcacao.setVisible(false);

		scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);

		JLabel lblCadastrarEspecialidade = new JLabel("Reimprimir Declara\u00E7\u00E3o");
		lblCadastrarEspecialidade.setFont(new Font("Tahoma", Font.BOLD, 21));
		panel.add(lblCadastrarEspecialidade);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCodigo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldIdMarcacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnImprimir, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(39)
							.addComponent(btnCancelar)
							.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
							.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnPesquisar))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
						.addComponent(scrollPane))
					.addGap(22))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigo)
						.addComponent(textFieldIdMarcacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar)
						.addComponent(btnImprimir, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(96, Short.MAX_VALUE))
		);
		scrollPane.setViewportView(tableReimprimirDeclaracao);
		contentPane.setLayout(gl_contentPane);

	}
}

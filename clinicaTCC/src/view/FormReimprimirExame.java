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
import java.util.Calendar;
import java.util.Date;

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
import modeloDao.DaoExame;
import modeloDao.DaoMarcConsulta;
import relatorios.Relatorio;

public class FormReimprimirExame extends JFrame {

	private JPanel contentPane;
	
	BeanMarcConsulta paciente = new BeanMarcConsulta();
	DaoMarcConsulta dao = new DaoMarcConsulta();
	ConexaoBD conexao = new ConexaoBD();
	JButton btnCancelar = new JButton("Cancelar");
	private JLabel lblCodigo;
	private JTextField textFieldIdMarcacao;
	private JTable tableReimprimirExame = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	BeanMarcConsulta marcacao = new BeanMarcConsulta();
	Relatorio relatorio = new Relatorio();
	String data;
	
	DaoExame daoExame = new DaoExame();
	private JTextField textFieldExame;
	
	
	

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
		
		
		
//		Calendar data = Calendar.getInstance();
//		Date dat = data.getTime();
//
//		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy"); // Mascara;

//		dateformat.format(dat);
	//	data = dateformat.format(dat);

		ModeloTabela modeloTabela = new ModeloTabela(dados, colunas);
		contentPane.add(scrollPane);
		tableReimprimirExame.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableReimprimirExame.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				String exame = "" + tableReimprimirExame.getValueAt(tableReimprimirExame.getSelectedRow(), 1);
				
			//	System.out.println(exame);
			
				
				
				conexao.conexao();
				conexao.executarSQL(	"SELECT tab_exame.id_exame," + 
										"	tab_marcacao.id_marcacao as id, " + 
										"	tab_paciente.id_paciente, " + 
										"	tab_paciente.nome_paciente, " + 
										"	tab_paciente.nasc_paciente, " + 
										"	tabmedico.crmmedico, " + 
										"	tabmedico.nomemedico," + 
										"	tab_exame.descricao_exame " + 
										"FROM tab_marcacao, " + 
										"	tab_paciente, " + 
										"	tabmedico, " + 
										"	tab_exame " + 
										"WHERE   tab_exame.id_pacientefk = tab_paciente.id_paciente  " + 
										"	 AND tab_exame.id_medicofk = tabmedico.idmedico  " + 
										"	 AND tab_marcacao.cod_paciente_marcacao = tab_paciente.id_paciente  " + 
										"	 AND tab_exame.id_marcacaofk = tab_marcacao.id_marcacao "+  
										"	 AND tab_marcacao.status_marcacao LIKE 'Finalizado' " + 
										"	 AND nome_paciente like'%" + exame + "%'"						
						
						);
				
				
				
				try {
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					conexao.rs.first();
					
					textFieldIdMarcacao.setText(String.valueOf(conexao.rs.getInt("id")));
					textFieldExame.setText(String.valueOf(conexao.rs.getInt("id_exame")));
				//	int idExame = daoExame.pegarIdExame(Integer.parseInt(textFieldIdMarcacao.getText()));
					//System.out.println(idExame);
					
					relatorio.SolicitarExame(Integer.parseInt(textFieldIdMarcacao.getText()),Integer.parseInt(textFieldExame.getText()));
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Não existe Solicitação de Exame para esse Paciente");
					System.out.println(e);
				}

				conexao.desconectar();

			
				//textFieldIdMarcacao.setText(String.valueOf());
			
				
				

			}
		});

		tableReimprimirExame.setModel(modeloTabela);
		tableReimprimirExame.getColumnModel().getColumn(0).setPreferredWidth(30); // Zero é o primeiro registro
		tableReimprimirExame.getColumnModel().getColumn(0).setResizable(false); // Não permite esticar a tabela com o mouse
		tableReimprimirExame.getColumnModel().getColumn(1).setPreferredWidth(180);
		tableReimprimirExame.getColumnModel().getColumn(1).setResizable(false);
		tableReimprimirExame.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableReimprimirExame.getColumnModel().getColumn(2).setResizable(false);
		tableReimprimirExame.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableReimprimirExame.getColumnModel().getColumn(3).setResizable(false);
		

		tableReimprimirExame.getTableHeader().setReorderingAllowed(false); // Não permite reordenar o cabeçalho
		tableReimprimirExame.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite selecionar apenas um dado
																					// por

		conexao.desconectar();

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormReimprimirExame frame = new FormReimprimirExame();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormReimprimirExame() {
		
		textFieldExame.setVisible(false);
		
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

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();

			}
		});


		lblCodigo = new JLabel("Codigo: ");

		textFieldIdMarcacao = new JTextField();
		textFieldIdMarcacao.setEnabled(false);
		textFieldIdMarcacao.setColumns(10);
		textFieldIdMarcacao.setVisible(false);
		lblCodigo.setVisible(false);

		scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);

		JLabel lblCadastrarEspecialidade = new JLabel("Reimprimir Exame");
		lblCadastrarEspecialidade.setFont(new Font("Tahoma", Font.BOLD, 21));
		panel.add(lblCadastrarEspecialidade);
		
		textFieldExame = new JTextField();
		textFieldExame.setEnabled(false);
		textFieldExame.setColumns(10);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(lblCodigo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldIdMarcacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addComponent(textFieldExame, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 312, Short.MAX_VALUE)
							.addComponent(btnCancelar))
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))
					.addGap(22))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCodigo)
								.addComponent(textFieldIdMarcacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldExame, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
					.addContainerGap())
		);
		scrollPane.setViewportView(tableReimprimirExame);
		contentPane.setLayout(gl_contentPane);

	}
}

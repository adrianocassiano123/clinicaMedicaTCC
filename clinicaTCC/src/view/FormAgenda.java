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
import modelo.ModeloTabela;
import modeloDao.DaoMarcConsulta;

public class FormAgenda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	BeanMarcConsulta modPaciente = new BeanMarcConsulta();
	ConexaoBD conexao = new ConexaoBD();
	private JTable tableAgenda = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	private final JTextField textFieldIdMarcar = new JTextField();
	private final JPanel panel_2 = new JPanel();;
	BeanMarcConsulta marcarConsulta = new BeanMarcConsulta();
	JButton btnConfirmarMarcao = new JButton();
	JButton btnCancelarMarcao = new JButton();
	BeanMarcConsulta consulta = new BeanMarcConsulta();
	DaoMarcConsulta dao = new DaoMarcConsulta();
	JButton buttonRemarcar = new JButton("Remarcar Consulta");

	String dataHoje;
	String status;
	private JTextField textFieldIdMarcacao;

	public void cancelarConsulta() {
		try {
			conexao.rs.first();
			consulta.setIdMarcConsulta(conexao.rs.getInt("id_marcacao"));
			consulta.setStatus("Cancelado");
			dao.cancelarConsulta(consulta);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Cancelar");
			e.printStackTrace();
		}

		btnConfirmarMarcao.setEnabled(true);
		btnCancelarMarcao.setEnabled(true);
	}
	
	

	public void listarTabelaAgenda() {
		preencherTabela(
				"SELECT 	*FROM tab_marcacao INNER JOIN tab_paciente ON cod_paciente_marcacao=id_paciente INNER JOIN tabmedico ON cod_medico_marcacao=idmedico where data_marcacao='"
						+ dataHoje + "' and status_marcacao='" + status + "' ORDER BY turno_marcacao");
	}

	public void preencherTabela(String Sql) {

		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "ID", "PACIENTE", "TURNO", "DATA", "STATUS", "MÉDICO" };
		conexao.conexao();
		conexao.executarSQL(Sql);

		try {
			conexao.rs.first(); // Seta o primeiro registro

			do {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				dados.add(new Object[] { conexao.rs.getInt("id_marcacao"), conexao.rs.getString("nome_paciente"),
						conexao.rs.getString("turno_marcacao"), sdf.format(conexao.rs.getDate("data_marcacao")),
						conexao.rs.getString("status_marcacao"), conexao.rs.getString("nomemedico") });

			} while (conexao.rs.next());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, "Não existe Atendimento para hoje");
		}

		ModeloTabela modeloTabela = new ModeloTabela(dados, colunas);
		tableAgenda.setBackground(SystemColor.controlHighlight);
		tableAgenda.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableAgenda.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				String idMarcacao = "" + tableAgenda.getValueAt(tableAgenda.getSelectedRow(), 0);
				conexao.conexao();
				conexao.executarSQL("select *from tab_marcacao where id_marcacao= '" + idMarcacao + "'");

				try {

					conexao.rs.first();

					consulta.setStatus("Atendimento");
					consulta.setIdMarcConsulta(conexao.rs.getInt("id_marcacao"));

					btnConfirmarMarcao.setEnabled(true);

					btnCancelarMarcao.setEnabled(true);
					buttonRemarcar.setEnabled(true);

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro ao acessar dados" + e);
				}

				conexao.desconectar();

			}
		});

		tableAgenda.setModel(modeloTabela);
		tableAgenda.getColumnModel().getColumn(0).setPreferredWidth(30); // Zero é o primeiro registro
		tableAgenda.getColumnModel().getColumn(0).setResizable(false); // Não permite esticar a tabela com o mouse
		tableAgenda.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableAgenda.getColumnModel().getColumn(1).setResizable(true);
		tableAgenda.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableAgenda.getColumnModel().getColumn(2).setResizable(false);
		tableAgenda.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableAgenda.getColumnModel().getColumn(3).setResizable(false);
		tableAgenda.getColumnModel().getColumn(4).setPreferredWidth(200);
		tableAgenda.getColumnModel().getColumn(4).setResizable(false);
		tableAgenda.getTableHeader().setReorderingAllowed(false); // Não permite reordenar o cabeçalho
		tableAgenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite selecionar apenas um dado por

		conexao.desconectar();

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAgenda frame = new FormAgenda();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormAgenda() {
		Calendar data = Calendar.getInstance();
		Date dat = data.getTime();

		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy"); // Mascara;

		dateformat.format(dat);

		dataHoje = dateformat.format(dat);

		status = "Aberto";

		listarTabelaAgenda();
		panel_2.setBackground(SystemColor.activeCaption);

		textFieldIdMarcar.setColumns(10);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);

		textFieldIdMarcar.setVisible(false);

		btnConfirmarMarcao = new JButton("Confirmar Paciente");
		btnConfirmarMarcao.setEnabled(false);
		btnConfirmarMarcao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dao.alterar(consulta);
				preencherTabela(
						"SELECT 	*FROM tab_marcacao INNER JOIN tab_paciente ON cod_paciente_marcacao=id_paciente INNER JOIN tabmedico ON cod_medico_marcacao=idmedico where data_marcacao='"
								+ dataHoje + "' and status_marcacao='" + status + "' ORDER BY turno_marcacao");

				btnConfirmarMarcao.setEnabled(false);
				btnCancelarMarcao.setEnabled(false);

			}

		});
		btnConfirmarMarcao.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnCancelarMarcao = new JButton("Cancelar Consulta");
		btnCancelarMarcao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
					cancelarConsulta();
					listarTabelaAgenda();
				
			}

		});

		btnCancelarMarcao.setEnabled(false);
		btnCancelarMarcao.setFont(new Font("Tahoma", Font.BOLD, 14));

		textFieldIdMarcacao = new JTextField();
		textFieldIdMarcacao.setEnabled(false);
		textFieldIdMarcacao.setColumns(10);
		textFieldIdMarcacao.setVisible(false);

		buttonRemarcar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					conexao.rs.first();
					consulta.setIdMarcConsulta(conexao.rs.getInt("id_marcacao"));
					textFieldIdMarcacao.setText(Integer.toString(conexao.rs.getInt("id_marcacao")));
					
					String idMarcacaoCons =textFieldIdMarcacao.getText();
					FormRemarcConsulta remarcar = new FormRemarcConsulta(idMarcacaoCons);
					remarcar.setVisible(true);
					

					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro ao Cancelar");
					e.printStackTrace();
				}
				

			}

		});
		extracted();
		buttonRemarcar.setEnabled(false);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(textFieldIdMarcar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(textFieldIdMarcacao,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE))
				.addGap(20))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(38)
						.addComponent(btnConfirmarMarcao, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addGap(73)
						.addComponent(btnCancelarMarcao, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
						.addComponent(buttonRemarcar, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addGap(78))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE).addGap(20)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldIdMarcar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldIdMarcacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(61).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
				.addGap(34)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnConfirmarMarcao, GroupLayout.PREFERRED_SIZE, 37,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCancelarMarcao, GroupLayout.PREFERRED_SIZE, 37,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(buttonRemarcar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
				.addGap(188)));

		JLabel lblCadastroDePacientes = new JLabel("Agenda do Dia");
		panel_2.add(lblCadastroDePacientes);
		lblCadastroDePacientes.setFont(new Font("Tahoma", Font.BOLD, 21));
		scrollPane.setViewportView(tableAgenda);
		contentPane.setLayout(gl_contentPane);

	}



	private void extracted() {
		buttonRemarcar.setFont(new Font("Tahoma", Font.BOLD, 14));
	}
}

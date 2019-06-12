package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.border.MatteBorder;

import com.mongodb.util.StringParseUtil;
import com.toedter.calendar.JDateChooser;

import ModeloConexao.ConexaoBD;
import modelo.BeanMarcConsulta;
import modelo.BeanPaciente;
import modelo.ModeloTabela;
import modeloDao.DaoMarcConsulta;
import modeloDao.DaoPacientes;
import modeloDao.Parametros;

import javax.swing.border.LineBorder;
import javax.swing.JFormattedTextField;
import java.awt.event.MouseMotionAdapter;

public class FormMarcConsulta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<?> comboBoxMedico;
	private JComboBox<?> comboBoxTurno;

	BeanPaciente modPaciente = new BeanPaciente();
	DaoPacientes daoPaciente = new DaoPacientes();
	ConexaoBD conexao = new ConexaoBD();
	private JTextField textFieldPesquisaPaciente;
	JButton btnPesquisarPaciente = new JButton("Pesquisar");
	private JTable tablePacientes = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	private final JTextField textFieldIdMarcar = new JTextField();
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JLabel lblMdico = new JLabel("M\u00E9dico :");
	private JTextField textFieldMotivo;;
	BeanMarcConsulta marcarConsulta = new BeanMarcConsulta();
	JDateChooser dateConsulta = new JDateChooser();
	JButton btnConfirmarMarcao = new JButton();
	JButton btnCancelarMarcao = new JButton();
	private JTextField textFieldPagar;
	private JTextField textFieldEspecialidade;
	JButton btnPagar = new JButton("Pagar");

	public void preencherTabela(String Sql) {

		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "ID", "PACIENTE", "EMAIL", "CONVÊNIO", "NASCIMENTO", "RG", "CPF",
				"TELEFONE" };
		conexao.conexao();
		conexao.executarSQL(Sql);

		try {
			conexao.rs.first(); // Seta o primeiro registro

			do {
				dados.add(new Object[] { conexao.rs.getInt("id_paciente"), conexao.rs.getString("nome_paciente"),
						conexao.rs.getString("email_paciente"), conexao.rs.getString("convenio_paciente"),
						conexao.rs.getString("nasc_paciente"), conexao.rs.getString("rg_paciente"),
						conexao.rs.getString("cpf_paciente"), conexao.rs.getString("telefone_paciente") });

			} while (conexao.rs.next());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, "Faça outra Pesquisa");
		}

		ModeloTabela modeloTabela = new ModeloTabela(dados, colunas);
		tablePacientes.setBackground(SystemColor.controlHighlight);
		tablePacientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tablePacientes.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				String nomePaciente = "" + tablePacientes.getValueAt(tablePacientes.getSelectedRow(), 1);
				conexao.conexao();
				conexao.executarSQL("select *from tab_paciente where nome_paciente= '" + nomePaciente + "'");

				try {

					conexao.rs.first();

					textFieldPesquisaPaciente.setText(conexao.rs.getString("nome_paciente"));
					comboBoxTurno.setEnabled(true);
					comboBoxMedico.setEnabled(true);

					textFieldMotivo.setEnabled(true);

					dateConsulta.setEnabled(true);
					btnConfirmarMarcao.setEnabled(true);
					;
					btnCancelarMarcao.setEnabled(true);

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro ao acessar dados" + e);
				}

				conexao.desconectar();

			}
		});

		tablePacientes.setModel(modeloTabela);
		tablePacientes.getColumnModel().getColumn(0).setPreferredWidth(30); // Zero é o primeiro registro
		tablePacientes.getColumnModel().getColumn(0).setResizable(false); // Não permite esticar a tabela com o mouse
		tablePacientes.getColumnModel().getColumn(1).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(1).setResizable(true);
		tablePacientes.getColumnModel().getColumn(2).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(2).setResizable(false);
		tablePacientes.getColumnModel().getColumn(3).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(3).setResizable(false);
		tablePacientes.getColumnModel().getColumn(4).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(4).setResizable(false);
		tablePacientes.getColumnModel().getColumn(5).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(5).setResizable(false);
		tablePacientes.getColumnModel().getColumn(6).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(6).setResizable(false);
		tablePacientes.getColumnModel().getColumn(7).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(7).setResizable(false);
		tablePacientes.getTableHeader().setReorderingAllowed(false); // Não permite reordenar o cabeçalho
		tablePacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite selecionar apenas um dado por

		conexao.desconectar();

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMarcConsulta frame = new FormMarcConsulta();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void preencherMedicos() {

		String sql = " SELECT tabmedico.idmedico as id " + "		,	tabmedico.nomemedico as nome "
				+ "		,	tab_especialidade.tipo_especialidade as especialidade "
				+ "		,	tabmedico.crmmedico as crm " + "		, 	tab_especialidade.valor as valor "
				+ " FROM tab_especialidade " + " ,	tabmedico "
				+ " WHERE tabmedico.especialidadefk = tab_especialidade.id_especialidade ";

		List<String> strList = new ArrayList<String>();

		conexao.conexao();
		conexao.executarSQL(sql);

		try {
			conexao.rs.first();

			do {

				strList.add(conexao.rs.getString("nome"));

			} while (conexao.rs.next());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro no comboBox" + e);
		}

		comboBoxMedico = new JComboBox<>(strList.toArray());
		comboBoxMedico.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String combo = comboBoxMedico.getSelectedItem().toString();

				String sql = " SELECT tabmedico.idmedico as id " + "		,	tabmedico.nomemedico as nome "
						+ "		,	tab_especialidade.tipo_especialidade as especialidade "
						+ "		,	tabmedico.crmmedico as crm " + "		, 	tab_especialidade.valor as valor "
						+ " FROM tab_especialidade " + " ,	tabmedico "
						+ " WHERE tabmedico.especialidadefk = tab_especialidade.id_especialidade "
						+ " AND  tabmedico.nomemedico like'%" + combo + "%'";

				conexao.conexao();
				conexao.executarSQL(sql);

				try {

					conexao.rs.first();
					textFieldEspecialidade.setText(conexao.rs.getString("especialidade").toString());

					textFieldPagar.setText(conexao.rs.getString("valor").toString());

				} catch (Exception ezx) {
					ezx.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro no ");
				}

			}

		});

		conexao.desconectar();

	}

	public FormMarcConsulta() {
		
		preencherMedicos();
		lblMdico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		preencherTabela("select *from tab_paciente order by nome_paciente");
		panel_2.setBackground(SystemColor.activeCaption);
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.activeCaption));
		textFieldPesquisaPaciente = new JTextField();
		textFieldPesquisaPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPesquisaPaciente.setColumns(10);
		btnPesquisarPaciente.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnPagar.setEnabled(false); 
		

		btnPesquisarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modPaciente.setPesquisaPaciente(textFieldPesquisaPaciente.getText());
				BeanPaciente model = daoPaciente.pesquisarPaciente(modPaciente);
				textFieldIdMarcar.setText(String.valueOf(model.getCodPaciente()));

				textFieldPesquisaPaciente.setText("");
				preencherTabela("select *from tab_paciente where nome_paciente like'%"
						+ modPaciente.getPesquisaPaciente() + "%'");

			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_1.createSequentialGroup()
						.addComponent(textFieldPesquisaPaciente, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnPesquisarPaciente,
								GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_1
				.createSequentialGroup().addContainerGap(70, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPesquisaPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisarPaciente, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));

		textFieldIdMarcar.setColumns(10);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);

		// textFieldIdPaciente.setEditable(false);
		textFieldIdMarcar.setVisible(false);

		scrollPane = new JScrollPane();
		panel_1.setLayout(gl_panel_1);
		comboBoxMedico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxMedico.setEnabled(false);

		dateConsulta.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		dateConsulta.setForeground(new Color(0, 0, 128));
		dateConsulta.setEnabled(false);

		JLabel lblData = new JLabel("Data :");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblMotivoDaConsulta = new JLabel("Motivo da Consulta:");
		lblMotivoDaConsulta.setFont(new Font("Tahoma", Font.BOLD, 16));

		textFieldMotivo = new JTextField();
		textFieldMotivo.setEnabled(false);
		textFieldMotivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMotivo.setColumns(10);

		btnConfirmarMarcao = new JButton("Confirmar Marca\u00E7\u00E3o");
		btnConfirmarMarcao.setEnabled(false);
		btnConfirmarMarcao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Date dataFormatada = null;
				marcarConsulta.setNomePaciente(textFieldPesquisaPaciente.getText());

				marcarConsulta.setNomeMedico(comboBoxMedico.getSelectedItem().toString());
				marcarConsulta.setTurno(comboBoxTurno.getSelectedItem().toString());
				marcarConsulta.setMotivo(textFieldMotivo.getText());

				marcarConsulta.setData(dateConsulta.getDate());

				marcarConsulta.setStatus("Aberto");
				DaoMarcConsulta dao = new DaoMarcConsulta();
				dao.salvar(marcarConsulta);

				textFieldPesquisaPaciente.setText("");

				textFieldMotivo.setText("");
				comboBoxTurno.setEnabled(false);
				comboBoxMedico.setEnabled(false);

				textFieldMotivo.setEnabled(false);
				dateConsulta.setEnabled(false);
				btnConfirmarMarcao.setEnabled(false);
				;
				btnCancelarMarcao.setEnabled(false);

			}

		});
		btnConfirmarMarcao.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnCancelarMarcao = new JButton("Cancelar");
		btnCancelarMarcao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldPesquisaPaciente.setText("");

				textFieldMotivo.setText("");
				comboBoxTurno.setEnabled(false);
				comboBoxMedico.setEnabled(false);

				textFieldMotivo.setEnabled(false);
				dateConsulta.setEnabled(false);
				btnConfirmarMarcao.setEnabled(false);
				;
				btnCancelarMarcao.setEnabled(false);
			}
		});
		btnCancelarMarcao.setEnabled(false);
		btnCancelarMarcao.setFont(new Font("Tahoma", Font.BOLD, 14));

		String turno[] = { "Manhã", "Tarde" };
		comboBoxTurno = new JComboBox<Object>(turno);
		comboBoxTurno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxTurno.setEnabled(false);

		JLabel lblTurno = new JLabel("Turno :");
		lblTurno.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 14));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.activeCaption));

		textFieldEspecialidade = new JTextField();
		textFieldEspecialidade.setEnabled(false);
		textFieldEspecialidade.setColumns(10);

		JLabel lblEspecialidade = new JLabel("Especialidade :");
		lblEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(btnConfirmarMarcao, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addGap(113)
					.addComponent(btnCancelarMarcao, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
					.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblEspecialidade, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTurno, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldEspecialidade, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)))
					.addGap(579))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textFieldMotivo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMdico, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxMedico, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE))
							.addGap(210)
							.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateConsulta, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
						.addComponent(textFieldIdMarcar, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMotivoDaConsulta, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBoxTurno, 0, 182, Short.MAX_VALUE)
							.addGap(269)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)))
					.addGap(28))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldIdMarcar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMdico, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEspecialidade, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldEspecialidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTurno, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxTurno, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblMotivoDaConsulta, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textFieldMotivo, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnCancelarMarcao, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnConfirmarMarcao, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(62))
		);

		textFieldPagar = new JTextField();
		textFieldPagar.setFont(new Font("Tahoma", Font.BOLD, 20));
		textFieldPagar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				
			//	textFieldPagar.setText("");
				Parametros parametro = new Parametros();
				textFieldPagar.setText(parametro.getParametro());
				
				
			}
		});
		textFieldPagar.setEnabled(false);
		textFieldPagar.setColumns(10);

		JButton btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Pagamento pagamento = new Pagamento(textFieldPagar.getText());
				pagamento.setVisible(true);

			}
		});
		btnPagar.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addContainerGap(83, Short.MAX_VALUE)
						.addComponent(textFieldPagar, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(btnPagar, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnPagar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldPagar, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
						.addContainerGap()));
		panel.setLayout(gl_panel);

		JLabel lblCadastroDePacientes = new JLabel("Marca\u00E7\u00E3o de Consulta");
		panel_2.add(lblCadastroDePacientes);
		lblCadastroDePacientes.setFont(new Font("Tahoma", Font.BOLD, 21));
		scrollPane.setViewportView(tablePacientes);
		contentPane.setLayout(gl_contentPane);

	}
}

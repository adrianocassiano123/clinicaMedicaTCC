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

public class FormConsultas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	BeanMarcConsulta modPaciente = new BeanMarcConsulta();
	ConexaoBD conexao = new ConexaoBD();
	private JTable tableConsulta = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	private final JTextField textFieldIdConsulta = new JTextField();
	private final JPanel panel_2 = new JPanel();;
	BeanMarcConsulta marcarConsulta = new BeanMarcConsulta();
	JButton btnConfirmarMarcao = new JButton();
	private JButton btnConfirmarAtendimento;
	JButton btnCancelarMarcao = new JButton();
	private JButton btnSair;
	BeanMarcConsulta consulta = new BeanMarcConsulta();
	DaoMarcConsulta dao = new DaoMarcConsulta();

	String dataHoje;
	String status;
	private JTextField textFieldMedico;
	String cod_agendamento;

	public void preencherTabela(String Sql) {

		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "ID", "PACIENTE", "TURNO", "DATA", "STATUS", "MÉDICO" };
		conexao.conexao();
		conexao.executarSQL(Sql);

		try {
			conexao.rs.first(); // Seta o primeiro registro

			do {
				dados.add(new Object[] { conexao.rs.getInt("id_marcacao"), conexao.rs.getString("nome_paciente"),
						conexao.rs.getString("turno_marcacao"), conexao.rs.getString("data_marcacao"),
						conexao.rs.getString("status_marcacao"), conexao.rs.getString("nomemedico") });

			} while (conexao.rs.next());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, "Não existe Atendimento para hoje");
		}

		ModeloTabela modeloTabela = new ModeloTabela(dados, colunas);
		tableConsulta.setBackground(SystemColor.controlHighlight);
		tableConsulta.setFont(new Font("Arial", Font.PLAIN, 12));
		tableConsulta.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				btnConfirmarAtendimento.setEnabled(true);
				btnSair.setEnabled(true);
				cod_agendamento = "" + tableConsulta.getValueAt(tableConsulta.getSelectedRow(), 0);
				conexao.conexao();
				conexao.executarSQL("select *from tab_marcacao where id_marcacao= '" + cod_agendamento + "'");

				try {

					conexao.rs.first();

					consulta.setStatus("Atendimento");
					consulta.setIdMarcConsulta(conexao.rs.getInt("id_marcacao"));

					btnConfirmarMarcao.setEnabled(true);

					btnCancelarMarcao.setEnabled(true);

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro ao acessar dados" + e);
				}

				conexao.desconectar();

			}
		});

		tableConsulta.setModel(modeloTabela);
		tableConsulta.getColumnModel().getColumn(0).setPreferredWidth(30); // Zero é o primeiro registro
		tableConsulta.getColumnModel().getColumn(0).setResizable(false); // Não permite esticar a tabela com o mouse
		tableConsulta.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableConsulta.getColumnModel().getColumn(1).setResizable(true);
		tableConsulta.getColumnModel().getColumn(2).setPreferredWidth(90);
		tableConsulta.getColumnModel().getColumn(2).setResizable(false);
		tableConsulta.getColumnModel().getColumn(3).setPreferredWidth(90);
		tableConsulta.getColumnModel().getColumn(3).setResizable(false);
		tableConsulta.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableConsulta.getColumnModel().getColumn(4).setResizable(false);
		tableConsulta.getColumnModel().getColumn(5).setPreferredWidth(200);
		tableConsulta.getColumnModel().getColumn(5).setResizable(false);
		tableConsulta.getTableHeader().setReorderingAllowed(false); // Não permite reordenar o cabeçalho
		tableConsulta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite selecionar apenas um dado por

		conexao.desconectar();

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormConsultas frame = new FormConsultas();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormConsultas() {
		Calendar data = Calendar.getInstance();
		Date dat = data.getTime();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd"); // Mascara;
		dateformat.format(dat);
		dataHoje = dateformat.format(dat);
		status = "Atendimento";

		preencherTabela(
				"SELECT 	*FROM tab_marcacao INNER JOIN tab_paciente ON cod_paciente_marcacao=id_paciente INNER JOIN tabmedico ON cod_medico_marcacao=idmedico where data_marcacao='"
						+ dataHoje + "' and status_marcacao='" + status + "' ORDER BY turno_marcacao");
		panel_2.setBackground(SystemColor.activeCaption);

		textFieldIdConsulta.setColumns(10);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);

		textFieldIdConsulta.setVisible(false);

		btnConfirmarAtendimento = new JButton("Atender Paciente");
		btnConfirmarAtendimento.setEnabled(false);
		btnConfirmarAtendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				PEP pep = new PEP(cod_agendamento);

				pep.setVisible(true);
				dispose();

				dao.alterar(consulta);


				preencherTabela(
						"SELECT 	*FROM tab_marcacao INNER JOIN tab_paciente ON cod_paciente_marcacao=id_paciente INNER JOIN tabmedico ON cod_medico_marcacao=idmedico where data_marcacao='"
								+ dataHoje + "' and status_marcacao='" + status + "' ORDER BY turno_marcacao");

				btnConfirmarMarcao.setEnabled(false);
				btnCancelarMarcao.setEnabled(false);

			}

		});
		btnConfirmarAtendimento.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnSair = new JButton("Sair"); // CANCELA A CONSULTA
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				
				btnConfirmarMarcao.setEnabled(false);
				btnCancelarMarcao.setEnabled(false);
			}
		});
		btnSair.setEnabled(false);
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblMedico = new JLabel("M\u00E9dico :");
		lblMedico.setFont(new Font("Tahoma", Font.PLAIN, 18));

		textFieldMedico = new JTextField();
		textFieldMedico.setColumns(10);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int id = dao.pesquisarIdMedico(textFieldMedico.getText().toUpperCase());
				String idi = String.valueOf(id); // convert o id que é inteiro em String
				preencherTabela(
						"SELECT 	*FROM tab_marcacao INNER JOIN tab_paciente ON cod_paciente_marcacao=id_paciente INNER JOIN tabmedico ON cod_medico_marcacao=idmedico WHERE cod_medico_marcacao='"
								+ idi + "' AND data_marcacao='" + dataHoje + "' AND status_marcacao='" + status
								+ "'  ");

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(237)
					.addComponent(btnConfirmarAtendimento, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(307, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblMedico)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldMedico, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
						.addComponent(textFieldIdConsulta, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE))
					.addGap(20))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE))
					.addGap(20))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldIdConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldMedico, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMedico))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConfirmarAtendimento, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(152))
		);

		JLabel lblCadastroDePacientes = new JLabel("Consultas");
		panel_2.add(lblCadastroDePacientes);
		lblCadastroDePacientes.setFont(new Font("Tahoma", Font.BOLD, 21));
		scrollPane.setViewportView(tableConsulta);
		contentPane.setLayout(gl_contentPane);

	}
}

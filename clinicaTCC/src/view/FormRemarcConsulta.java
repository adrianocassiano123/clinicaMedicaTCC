package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import ModeloConexao.ConexaoBD;
import modelo.BeanMarcConsulta;
import modelo.BeanPaciente;
import modelo.ModeloTabela;
import modeloDao.DaoMarcConsulta;
import modeloDao.DaoPacientes;
import javax.swing.border.LineBorder;

public class FormRemarcConsulta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<?> comboBoxMedico;
	private JComboBox<?> comboBoxTurno;
	BeanPaciente modPaciente = new BeanPaciente();
	DaoPacientes daoPaciente = new DaoPacientes();
	ConexaoBD conexao = new ConexaoBD();
	JScrollPane scrollPane = new JScrollPane();
	private final JTextField textFieldIdMarcar = new JTextField();
	private final JPanel panel_2 = new JPanel();
	private final JLabel lblMdico = new JLabel("M\u00E9dico :");
	private JTextField textFieldMotivo;;
	BeanMarcConsulta marcarConsulta = new BeanMarcConsulta();
	JDateChooser dateConsulta = new JDateChooser();
	JButton btnConfirmarMarcao = new JButton();
	JButton btnCancelarMarcao = new JButton();
	private JTextField textFieldPaciente;
	BeanMarcConsulta consulta = new BeanMarcConsulta();
	DaoMarcConsulta dao = new DaoMarcConsulta();
	

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					FormRemarcConsulta frame = new FormRemarcConsulta();
//					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void cancelarConsulta() {
		try {
			conexao.rs.first();
			consulta.setIdMarcConsulta(Integer.parseInt(textFieldIdMarcar.getText()));
			consulta.setStatus("Cancelado");
			dao.cancelarConsulAnterior(consulta);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Cancelar");
			e.printStackTrace();
		}

		btnConfirmarMarcao.setEnabled(true);
		btnCancelarMarcao.setEnabled(true);
	}
	
	public void preencherMedicos() {

		List<String> strList = new ArrayList<String>();

		conexao.conexao();
		conexao.executarSQL("select nomemedico from tabmedico order by nomemedico");

		try {
			conexao.rs.first();

			do {

				strList.add(conexao.rs.getString("nomemedico"));

			} while (conexao.rs.next());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro no comboBox" + e);
		}

		comboBoxMedico = new JComboBox<>(strList.toArray());

		conexao.desconectar();

	}
	
	public FormRemarcConsulta(String CodMarcacao) {
		
	
		
		
		preencherMedicos();
		lblMdico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		panel_2.setBackground(SystemColor.activeCaption);
		textFieldIdMarcar.setEditable(false);
		textFieldIdMarcar.setEnabled(false);
		textFieldIdMarcar.setVisible(false);

		textFieldIdMarcar.setColumns(10);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 550);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				

				DaoMarcConsulta dao = new DaoMarcConsulta();

				int cod = Integer.parseInt(CodMarcacao);

				try {
					marcarConsulta = dao.pesquisarConsultaPorCodigo(cod);

					textFieldPaciente.setText(marcarConsulta.getNomePaciente());
					textFieldIdMarcar.setText(Integer.toString(marcarConsulta.getIdMarcConsulta()));
					textFieldMotivo.setText(marcarConsulta.getMotivo());
					comboBoxMedico.setSelectedItem(marcarConsulta.getNomeMedico());
					//comboBoxTurno.setSelectedItem(marcarConsulta.getIdMarcConsulta());
					
					System.out.println(textFieldPaciente);
					System.out.println(textFieldPaciente);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao Listar Paciente");
					System.out.println(e);
				}
				
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		comboBoxMedico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxMedico.setEnabled(false);

		dateConsulta.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		dateConsulta.setForeground(new Color(0, 0, 128));
		dateConsulta.setEnabled(true);

		JLabel lblData = new JLabel("Data :");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblMotivoDaConsulta = new JLabel("Motivo da Consulta:");
		lblMotivoDaConsulta.setFont(new Font("Tahoma", Font.BOLD, 16));

		textFieldMotivo = new JTextField();
		textFieldMotivo.setEnabled(false);
		textFieldMotivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMotivo.setColumns(10);

		btnConfirmarMarcao = new JButton("Confirmar Remarca\u00E7\u00E3o");
		btnConfirmarMarcao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cancelarConsulta();
				
				marcarConsulta.setNomePaciente(textFieldPaciente.getText());
				marcarConsulta.setNomeMedico(comboBoxMedico.getSelectedItem().toString());
				marcarConsulta.setTurno(comboBoxTurno.getSelectedItem().toString());
				marcarConsulta.setMotivo(textFieldMotivo.getText());

				marcarConsulta.setData(dateConsulta.getDate());

				marcarConsulta.setStatus("Aberto");
				DaoMarcConsulta dao = new DaoMarcConsulta();
				dao.remarcarConsulta(marcarConsulta);

				textFieldPaciente.setText("");

				textFieldMotivo.setText("");
				comboBoxTurno.setEnabled(false);
				comboBoxMedico.setEnabled(false);

				textFieldMotivo.setEnabled(false);
				dateConsulta.setEnabled(false);
				btnConfirmarMarcao.setEnabled(false);
				;
				btnCancelarMarcao.setEnabled(false);
				
				dispose();

			}

		});
		btnConfirmarMarcao.setFont(new Font("Tahoma", Font.BOLD, 14));

		String turno[] = { "Manhã", "Tarde" };

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.activeCaption));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
						.addComponent(textFieldIdMarcar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 837, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMdico, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, Short.MAX_VALUE)
					.addComponent(comboBoxMedico, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
					.addGap(178)
					.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(dateConsulta, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMotivoDaConsulta, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(648, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(textFieldMotivo, GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(151)
					.addComponent(btnConfirmarMarcao, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addGap(78)
					.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(131, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldIdMarcar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBoxMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblMdico, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addComponent(dateConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(lblMotivoDaConsulta, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldMotivo, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConfirmarMarcao, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(248))
		);
		
				JLabel lblPaciente = new JLabel("Paciente :");
				lblPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
				textFieldPaciente = new JTextField();
				textFieldPaciente.setEnabled(false);
				textFieldPaciente.setColumns(10);
		
				JLabel lblTurno = new JLabel("Turno :");
				lblTurno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxTurno = new JComboBox<Object>(turno);
		comboBoxTurno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPaciente, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textFieldPaciente, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addComponent(lblTurno, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addComponent(comboBoxTurno, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(7)
								.addComponent(lblPaciente))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(7)
								.addComponent(textFieldPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(3)
								.addComponent(lblTurno))
							.addComponent(comboBoxTurno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);

		JLabel lblCadastroDePacientes = new JLabel("Remarcar Consulta");
		panel_2.add(lblCadastroDePacientes);
		lblCadastroDePacientes.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.setLayout(gl_contentPane);

	}
}

package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import ModeloConexao.ConexaoBD;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import relatorios.Relatorio;

public class TelaPrincipal extends JFrame {

	JLabel labelUsuario = new JLabel("");

	Relatorio relatorio = new Relatorio();

	ConexaoBD conecta = new ConexaoBD(); // Instancia o Conexão BD

	private JPanel contentPane;

	public static void main(String[] args) {
//		ConexaoBD conecta = new ConexaoBD(); // Instancia o Conexão BD
//		conecta.conexao();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// TelaPrincipal frame = new TelaPrincipal();
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal(String usuario) {

		labelUsuario.setText(usuario);

		setTitle("Home");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/imagem/ClinicalMeds.svg.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 730);
		setLocationRelativeTo(null);
		setResizable(false);

		conecta.conexao();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuCadastro = new JMenu("Cadastro");
		menuBar.add(menuCadastro);

		JMenuItem menuItemMedicos = new JMenuItem("M\u00E9dico");
		menuCadastro.add(menuItemMedicos);
		menuItemMedicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Administrador")
							|| conecta.rs.getString("tipo_usuario").equals("ADMINISTRADOR")) {

						FormCadMedico cadMedico = new FormCadMedico();
						cadMedico.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}
		});

		JMenuItem menuItemPacientes = new JMenuItem("Paciente");
		menuCadastro.add(menuItemPacientes);
		menuItemPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Administrador")
							|| conecta.rs.getString("tipo_usuario").equals("ADMINISTRADOR")) {

						FormCadPacientes cadPacientes = new FormCadPacientes();
						cadPacientes.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}
		});

		JMenuItem menuItemRecepcionista = new JMenuItem("Recepcionista");
		menuCadastro.add(menuItemRecepcionista);
		menuItemRecepcionista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Administrador")
							|| conecta.rs.getString("tipo_usuario").equals("ADMINISTRADOR")) {

						FormCadRecepcionistas cadRecepcionista = new FormCadRecepcionistas();
						cadRecepcionista.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}
		});

		JMenuItem menuItemUsuario = new JMenuItem("Usuario");
		menuCadastro.add(menuItemUsuario);

		menuItemUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) { // Liberação de acesso do formulário de cadastro de Usuários
				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Administrador")
							|| conecta.rs.getString("tipo_usuario").equals("ADMINISTRADOR")) {

						FormCadUsuario cadUsuario = new FormCadUsuario();
						cadUsuario.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}
		});

		JMenuItem menuItemEspecialidade = new JMenuItem("Especialidade");
		menuCadastro.add(menuItemEspecialidade);
		menuItemEspecialidade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) { // Liberação de acesso do formulário de cadastro de Usuários
				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Administrador")
							|| conecta.rs.getString("tipo_usuario").equals("ADMINISTRADOR")) {

						FormEspecialidade especialidade = new FormEspecialidade();
						especialidade.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}
		});

		JMenu mnRelatorios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatorios);

		JMenuItem mntmConsultasCanceladas = new JMenuItem("Consultas Canceladas");
		mnRelatorios.add(mntmConsultasCanceladas);
		mntmConsultasCanceladas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Administrador")
							|| conecta.rs.getString("tipo_usuario").equals("ADMINISTRADOR")) {

						relatorio.relatCancelados();

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}
		});

		JMenuItem mntmConsultasRealizadas = new JMenuItem("Consultas Realizadas");
		mnRelatorios.add(mntmConsultasRealizadas);
		mntmConsultasRealizadas.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Administrador")
							|| conecta.rs.getString("tipo_usuario").equals("ADMINISTRADOR")) {

						relatorio.relatAtendido();

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}
		});

		JMenu mnConsultas = new JMenu("Consultas");
		menuBar.add(mnConsultas);

		JMenuItem mntmMarcar = new JMenuItem("Marcar");
		mnConsultas.add(mntmMarcar);
		mntmMarcar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Administrador")
							|| conecta.rs.getString("tipo_usuario").equals("Recepcionista")) {

						FormMarcConsulta marcConslta = new FormMarcConsulta();
						marcConslta.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}
		});

		JMenuItem mntmAgendados = new JMenuItem("Agendadas");
		mnConsultas.add(mntmAgendados);
		mntmAgendados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FormAgenda agendada = new FormAgenda();
				agendada.setVisible(true);

			}
		});

		JMenuItem mntmPronturioEletnico = new JMenuItem("Prontu\u00E1rio Elet\u00F4nico");
		mnConsultas.add(mntmPronturioEletnico);
		mntmPronturioEletnico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Médico")
							|| conecta.rs.getString("tipo_usuario").equals("MÉDICO")) {

						FormConsultas pep = new FormConsultas();
						pep.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}
		});
		
		JMenu mnImpresses = new JMenu("Reimpress\u00E3o");
		menuBar.add(mnImpresses);
		
		JMenuItem mntmAtestado = new JMenuItem("Atestado");
		mnImpresses.add(mntmAtestado);
		
		JMenuItem mntmExame = new JMenuItem("Exame");
		mnImpresses.add(mntmExame);
		
		JMenuItem mntmDeclarao = new JMenuItem("Declara\u00E7\u00E3o");
		mnImpresses.add(mntmDeclarao);

		JMenu menuSair = new JMenu("Sair");
		menuBar.add(menuSair);

		JMenuItem menuItemSair = new JMenuItem("Sair");
		menuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				conecta.desconectar();
				System.exit(0);

			}
		});
		menuSair.add(menuItemSair);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton CadButMedico = new JButton("M\u00E9dico");
		CadButMedico.setBackground(Color.WHITE);
		CadButMedico.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagem/medicoIcone.png")));
		CadButMedico.setFont(new Font("Tahoma", Font.BOLD, 10));
		CadButMedico.setToolTipText("M\u00E9dico");
		CadButMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Administrador")
							|| conecta.rs.getString("tipo_usuario").equals("ADMINISTRADOR")) {

						FormCadMedico cadMedico = new FormCadMedico();
						cadMedico.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}
		});

		JButton CadButPaciente = new JButton("Paciente");
		CadButPaciente.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagem/sneeze.png")));
		CadButPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Administrador")
							|| conecta.rs.getString("tipo_usuario").equals("ADMINISTRADOR")) {

						FormCadPacientes cadPacientes = new FormCadPacientes();
						cadPacientes.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}
		});
		CadButPaciente.setToolTipText("Paciente");
		CadButPaciente.setFont(new Font("Tahoma", Font.BOLD, 10));

		JButton CadButRecepcionista = new JButton("Recepcionista");
		CadButRecepcionista.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagem/recepcionista.png")));
		CadButRecepcionista.setToolTipText("Recepcionista");
		CadButRecepcionista.setFont(new Font("Tahoma", Font.BOLD, 10));
		CadButRecepcionista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Administrador")
							|| conecta.rs.getString("tipo_usuario").equals("ADMINISTRADOR")) {

						FormCadRecepcionistas cadRecepcionista = new FormCadRecepcionistas();
						cadRecepcionista.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}
		});

		JButton BtnAgendamento = new JButton("Marcar");
		BtnAgendamento.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagem/relogio.png")));
		BtnAgendamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Administrador")
							|| conecta.rs.getString("tipo_usuario").equals("Recepcionista")) {

						FormMarcConsulta marcConslta = new FormMarcConsulta();
						marcConslta.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}
		});

		BtnAgendamento.setToolTipText("Agendamento");
		BtnAgendamento.setFont(new Font("Tahoma", Font.BOLD, 10));

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBorder(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBorder(null);

		JLabel lblRelatrios = new JLabel("Relat\u00F3rios");
		lblRelatrios.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(lblRelatrios);

		JButton btnRelaConsulta = new JButton("Consultas");
		btnRelaConsulta.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagem/relatorio02.png")));
		btnRelaConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRelaConsulta.setToolTipText("Agendamento");
		btnRelaConsulta.setFont(new Font("Tahoma", Font.BOLD, 10));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagem/ClinicalMedsGrande.svg.png")));

		JLabel lblSejaBemVindo = new JLabel("Bem Vindo :");
		lblSejaBemVindo.setForeground(Color.DARK_GRAY);
		lblSejaBemVindo.setFont(new Font("Arial", Font.BOLD, 18));

		labelUsuario.setForeground(Color.RED);
		labelUsuario.setFont(new Font("Arial", Font.BOLD, 18));

		JButton buttonAgendadas = new JButton("Agendadas");
		buttonAgendadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Administrador")
							|| conecta.rs.getString("tipo_usuario").equals("Recepcionista")) {

						FormAgenda agendado = new FormAgenda();
						agendado.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}

		});
		buttonAgendadas.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagem/agendado.png")));
		buttonAgendadas.setToolTipText("Agendamento");
		buttonAgendadas.setFont(new Font("Tahoma", Font.BOLD, 10));

		JButton btnPep = new JButton("PEP");
		btnPep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				conecta.executarSQL("select * from tab_usuarios where nome_usuario='" + labelUsuario.getText() + "'");
				try {
					conecta.rs.first();
					if (conecta.rs.getString("tipo_usuario").equals("Médico")
							|| conecta.rs.getString("tipo_usuario").equals("MÉDICO")) {

						FormConsultas pep = new FormConsultas();
						pep.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Você não tem Permissão para acessar esse Item");
				}

			}
		});
		btnPep.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagem/consulta.png")));
		btnPep.setToolTipText("");
		btnPep.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(panel, GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup().addGap(20)
												.addComponent(CadButMedico, GroupLayout.PREFERRED_SIZE, 176,
														GroupLayout.PREFERRED_SIZE)
												.addGap(130)
												.addComponent(CadButPaciente, GroupLayout.PREFERRED_SIZE, 173,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
												.addComponent(CadButRecepcionista, GroupLayout.PREFERRED_SIZE, 180,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 839,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup().addGap(24).addComponent(
												btnRelaConsulta, GroupLayout.PREFERRED_SIZE, 178,
												GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup().addGap(20)
												.addComponent(BtnAgendamento, GroupLayout.PREFERRED_SIZE, 177,
														GroupLayout.PREFERRED_SIZE)
												.addGap(132)
												.addComponent(buttonAgendadas, GroupLayout.PREFERRED_SIZE, 177,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
												.addComponent(btnPep, GroupLayout.PREFERRED_SIZE, 177,
														GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(264).addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblSejaBemVindo)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(labelUsuario)))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap(16, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblSejaBemVindo)
								.addComponent(labelUsuario)))
				.addGap(18).addComponent(panel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(CadButMedico, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addComponent(CadButRecepcionista, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addComponent(CadButPaciente, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
				.addGap(13).addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(BtnAgendamento, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonAgendadas, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPep, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
				.addGap(24).addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(btnRelaConsulta, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));

		JLabel lblConsultas = new JLabel("Consultas");
		lblConsultas.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblConsultas);

		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblCadastro);
		contentPane.setLayout(gl_contentPane);

	}
}

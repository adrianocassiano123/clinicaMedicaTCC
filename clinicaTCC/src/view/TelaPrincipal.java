package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ModeloConexao.ConexaoBD;
import controle.ConectTeste;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class TelaPrincipal extends JFrame {

	ConexaoBD conecta = new ConexaoBD(); // Instancia o Conexão BD

	private JPanel contentPane;

	public static void main(String[] args) {
//		ConexaoBD conecta = new ConexaoBD(); // Instancia o Conexão BD
//		conecta.conexao();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1040, 615);

		conecta.conexao();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuCadastro = new JMenu("Cadastro");
		menuBar.add(menuCadastro);

		JMenuItem menuItemEmpresa = new JMenuItem("Empresa");
		menuCadastro.add(menuItemEmpresa);

		JMenuItem menuItemMedicos = new JMenuItem("M\u00E9dicos");
		menuCadastro.add(menuItemMedicos);

		JMenuItem menuItemPacientes = new JMenuItem("Pacientes");
		menuCadastro.add(menuItemPacientes);

		JMenuItem menuItemRecepcionista = new JMenuItem("Recepcionista");
		menuCadastro.add(menuItemRecepcionista);

		JMenu mnRelatorios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatorios);

		JMenu mnFerramentas = new JMenu("Ferramentas");
		menuBar.add(mnFerramentas);

		JMenuItem menuItemTelaBem = new JMenuItem("Tela Bem - Vindo");
		mnFerramentas.add(menuItemTelaBem);

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton CadButMedico = new JButton("M\u00E9dico");
		CadButMedico.setFont(new Font("Tahoma", Font.BOLD, 17));
		CadButMedico.setToolTipText("M\u00E9dico");
		CadButMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JLabel lblCadastros = new JLabel("Cadastros");
		lblCadastros.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton CadButPaciente = new JButton("Paciente");
		CadButPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		CadButPaciente.setToolTipText("Paciente");
		CadButPaciente.setFont(new Font("Tahoma", Font.BOLD, 17));

		JButton CadButRecepcionista = new JButton("Recepcionista");
		CadButRecepcionista.setToolTipText("Recepcionista");
		CadButRecepcionista.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblAgendamento = new JLabel("Agendamento");
		lblAgendamento.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton AgendaBotao = new JButton("Agendamento");
		AgendaBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		AgendaBotao.setToolTipText("Agendamento");
		AgendaBotao.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(CadButMedico, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(CadButPaciente, GroupLayout.PREFERRED_SIZE, 146,
										GroupLayout.PREFERRED_SIZE)
								.addGap(10).addComponent(CadButRecepcionista, GroupLayout.PREFERRED_SIZE, 146,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(lblCadastros)
						.addComponent(lblAgendamento, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addComponent(AgendaBotao, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(550, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(59).addComponent(lblCadastros)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(CadButPaciente, GroupLayout.PREFERRED_SIZE, 92,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(CadButRecepcionista, GroupLayout.PREFERRED_SIZE, 92,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(CadButMedico, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(lblAgendamento).addGap(18)
				.addComponent(AgendaBotao, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(226, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

}

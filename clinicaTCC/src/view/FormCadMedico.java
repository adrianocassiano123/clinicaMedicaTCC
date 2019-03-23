package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.ConexaoBD;
import controle.ControleMedico;
import modelo.ModeloMedico;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormCadMedico extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCrm;

	/**
	 * Launch the application.
	 */
	
	ModeloMedico modMedico = new ModeloMedico();
	ControleMedico control = new ControleMedico();
	//ConexaoBD conDB =new ConexaoBD();
	
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

	/**
	 * Create the frame.
	 */
	public FormCadMedico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCadastroDeMdicos = new JLabel("Cadastro de M\u00E9dicos");
		lblCadastroDeMdicos.setFont(new Font("Tahoma", Font.BOLD, 21));
		
		JButton btnNovoCadMedico = new JButton("Novo");
		btnNovoCadMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnEditarCadMedico = new JButton("Editar");
		btnEditarCadMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnSalvarCadMedico = new JButton("Salvar");
		btnSalvarCadMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				modMedico.setNome(textFieldNome.getText());
				
				
				
				
			}
		});
		
		JButton btnExcluir = new JButton("Excluir ");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JLabel lblNome = new JLabel("Nome:");
		
		JLabel lblCrm = new JLabel("CRM:");
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		
		JLabel lblEspecialidade = new JLabel("Especialidade: ");
		
		textFieldCrm = new JTextField();
		textFieldCrm.setColumns(10);
		
		JComboBox comboBoxEspecialidadeMedica = new JComboBox();
		comboBoxEspecialidadeMedica.setModel(new DefaultComboBoxModel(new String[] {"", "Cirurgi\u00E3o", "Cardiologista", "Pediatra", "Ginecologista", "Ortopedista"}));
		
		JList list = new JList();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(290)
					.addComponent(lblCadastroDeMdicos)
					.addContainerGap(297, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(list, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNome)
										.addComponent(lblCrm))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textFieldCrm, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
											.addGap(190))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNovoCadMedico, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addGap(46)
									.addComponent(btnEditarCadMedico, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
									.addComponent(btnSalvarCadMedico, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
									.addComponent(btnExcluir)
									.addGap(97)
									.addComponent(btnCancelar))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(28)
									.addComponent(lblEspecialidade)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBoxEspecialidadeMedica, 0, 206, Short.MAX_VALUE)))))
					.addGap(60))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastroDeMdicos)
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnExcluir)
						.addComponent(btnEditarCadMedico)
						.addComponent(btnNovoCadMedico)
						.addComponent(btnSalvarCadMedico))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEspecialidade)
						.addComponent(comboBoxEspecialidadeMedica, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldCrm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCrm))
					.addGap(18)
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
					.addGap(35))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

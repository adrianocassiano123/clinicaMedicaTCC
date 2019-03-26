package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.BeanMedico;
import modeloDao.DaoMedico;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;

public class FormCadMedico extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCrm;
	private JComboBox comboBoxEspecialidade;

	/**
	 * Launch the application.
	 */

	BeanMedico modMedico = new BeanMedico();
	DaoMedico control = new DaoMedico();
	private JTextField txtEspecialidade;
	private JTextField textFieldPesquisa;
	// ConexaoBD conDB =new ConexaoBD();

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

		JButton btnEditarCadMedico = new JButton("Editar");
		btnEditarCadMedico.setEnabled(false);
		btnEditarCadMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
			}
		});

		JButton btnSalvarCadMedico = new JButton("Salvar");
		btnSalvarCadMedico.setEnabled(false);
		btnSalvarCadMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				modMedico.setNome(textFieldNome.getText().toUpperCase());
				// modMedico.setEspecialidade(txtEspecialidade.getText().toUpperCase());
				modMedico.setCrm(Integer.parseInt(textFieldCrm.getText()));
				modMedico.setEspecialidade(comboBoxEspecialidade.getSelectedItem().toString());

				control.salvar(modMedico);

				textFieldNome.setText("");
				txtEspecialidade.setText("");
				textFieldCrm.setText("");
				textFieldNome.setEnabled(false);
				txtEspecialidade.setEnabled(false);
				comboBoxEspecialidade.setEnabled(false);
				textFieldCrm.setEnabled(false);
				btnSalvarCadMedico.setEnabled(false);

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textFieldNome.setEnabled(!true);
				txtEspecialidade.setEnabled(!true);
				textFieldCrm.setEnabled(!true);
				comboBoxEspecialidade.setEnabled(!true);
				btnCancelar.setEnabled(!true);
				btnSalvarCadMedico.setEnabled(!true);
			}
		});
		btnCancelar.setEnabled(false);
		
		
		
		JButton btnNovoCadMedico = new JButton("Novo");
		btnNovoCadMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textFieldNome.setEnabled(true);
				txtEspecialidade.setEnabled(true);
				textFieldCrm.setEnabled(true);
				comboBoxEspecialidade.setEnabled(true);
				btnCancelar.setEnabled(true);
				btnSalvarCadMedico.setEnabled(true);

			}
		});

		JButton btnExcluir = new JButton("Excluir ");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JLabel lblNome = new JLabel("Nome:");

		JLabel lblCrm = new JLabel("CRM:");

		textFieldNome = new JTextField();
		textFieldNome.setEnabled(false);
		textFieldNome.setColumns(10);

		JLabel lblEspecialidade = new JLabel("Especialidade: ");

		textFieldCrm = new JTextField();
		textFieldCrm.setEnabled(false);
		textFieldCrm.setColumns(10);
		JList<String> list = new JList<String>();

		txtEspecialidade = new JTextField();
		txtEspecialidade.setColumns(10);

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modMedico.setPesquisa(textFieldPesquisa.getText().toUpperCase());
				BeanMedico model = control.pesquisarMedico(modMedico);
				textFieldNome.setText(model.getNome());
				textFieldCrm.setText(String.valueOf(model.getCrm()));
				txtEspecialidade.setText(model.getEspecialidade());
				btnEditarCadMedico.setEnabled(true);
				btnExcluir.setEnabled(true);

			}
		});

//		JComboBox comboBoxEspecialidade = new JComboBox();
//		comboBoxEspecialidade.setModel(new DefaultComboBoxModel(new String[] {"Dermatologista", "Cirurgi\u00E3o", "Ginecologista", "Urologista", "Cardiologista", "Fisioterapeuta", "Ortopedista", "Dentista"}));

		String especAr[] = { "Dermatologista", "Ginecologista", "Ortopedista" };
		comboBoxEspecialidade = new JComboBox(especAr);
		comboBoxEspecialidade.setEnabled(false);

		JLabel lblListaEspecialidade = new JLabel("Lista Especialidade");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(290).addComponent(lblCadastroDeMdicos)
						.addContainerGap(243, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(28)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(list, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
										.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
												.createSequentialGroup().addGroup(
														gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(lblNome).addComponent(lblCrm))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(gl_contentPane.createParallelGroup(
														Alignment.TRAILING).addComponent(textFieldNome,
																Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 306,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(textFieldCrm, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED, 40,
																		Short.MAX_VALUE)
																.addComponent(textFieldPesquisa,
																		GroupLayout.PREFERRED_SIZE, 218,
																		GroupLayout.PREFERRED_SIZE))))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(btnNovoCadMedico, GroupLayout.PREFERRED_SIZE, 85,
														GroupLayout.PREFERRED_SIZE)
												.addGap(46)
												.addComponent(btnEditarCadMedico, GroupLayout.PREFERRED_SIZE, 84,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
												.addComponent(
														btnSalvarCadMedico, GroupLayout.PREFERRED_SIZE, 85,
														GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane
														.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED, 38,
																Short.MAX_VALUE)
														.addComponent(btnExcluir).addGap(97).addComponent(btnCancelar))
												.addGroup(gl_contentPane.createSequentialGroup().addGap(10)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
																.addComponent(btnPesquisar)
																.addComponent(lblEspecialidade))
														.addGroup(gl_contentPane
																.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_contentPane.createSequentialGroup()
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addComponent(txtEspecialidade,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_contentPane.createSequentialGroup()
																		.addGap(4).addComponent(lblListaEspecialidade)
																		.addPreferredGap(ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(comboBoxEspecialidade,
																				GroupLayout.PREFERRED_SIZE, 109,
																				GroupLayout.PREFERRED_SIZE)))
														.addPreferredGap(ComponentPlacement.RELATED)))))
						.addGap(60)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(lblCadastroDeMdicos)
						.addGap(37)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnCancelar)
								.addComponent(btnExcluir).addComponent(btnEditarCadMedico)
								.addComponent(btnNovoCadMedico).addComponent(btnSalvarCadMedico))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEspecialidade).addComponent(lblNome).addComponent(txtEspecialidade,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(
								gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldCrm, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCrm)
										.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnPesquisar)
										.addComponent(comboBoxEspecialidade, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblListaEspecialidade))
						.addGap(18).addComponent(list, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE).addGap(35)));
		contentPane.setLayout(gl_contentPane);
	}
}

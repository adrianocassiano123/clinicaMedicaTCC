package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modelo.BeanExame;
import modelo.BeanMarcConsulta;
import modeloDao.DaoExame;
import modeloDao.DaoMarcConsulta;
import relatorios.Relatorio;

public class FormExame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldIdMarcacao;
	private JTextField textFieldMedico;
	private JTextField textFieldMotivo;

	DaoExame daoExame = new DaoExame();
	BeanExame beanExame = new BeanExame();

	Relatorio exame = new Relatorio();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// FormAtestado frame = new FormAtestado();
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormExame(String codigoAtendimento) {

		textFieldIdMarcacao = new JTextField();
		System.out.println(codigoAtendimento);
		JEditorPane editorPaneDescricao = new JEditorPane();
		editorPaneDescricao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JFormattedTextField formattedTextFieldNascimento = new JFormattedTextField();
		formattedTextFieldNascimento.setEnabled(false);
		textFieldIdMarcacao.setVisible(false);
		textFieldIdMarcacao.setEnabled(false);
		textFieldMedico = new JTextField();
		textFieldMedico.setVisible(false);
		textFieldMedico.setEnabled(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		editorPaneDescricao.setText("Solicito : \n\n ");

		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				String cod_prontuario = codigoAtendimento;
				BeanMarcConsulta prontuario = new BeanMarcConsulta();
				DaoMarcConsulta dao = new DaoMarcConsulta();

				int cod = Integer.parseInt(cod_prontuario);

				try {
					prontuario = dao.pesquisarConsultaPorCodigo(cod);

					textFieldNome.setText(prontuario.getNomePaciente());
					textFieldIdMarcacao.setText(String.valueOf(prontuario.getIdMarcConsulta()));
					formattedTextFieldNascimento.setText(prontuario.getNascimento());
					// textFieldMotivo.setText(prontuario.getMotivo());
					textFieldMedico.setText(prontuario.getNomeMedico());

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao Listar Paciente");
					System.out.println(e);
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);

		JLabel lblExame = new JLabel("Solicita\u00E7\u00E3o de  Exames");
		lblExame.setFont(new Font("Tahoma", Font.BOLD, 21));
		panel.add(lblExame);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaption));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(SystemColor.activeCaption));

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addComponent(editorPaneDescricao, GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
						.addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_2.createSequentialGroup().addContainerGap()
						.addComponent(editorPaneDescricao, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE).addGap(22)));
		panel_2.setLayout(gl_panel_2);

		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				beanExame.setDescricao(editorPaneDescricao.getText());
				beanExame.setNomeMedico(textFieldMedico.getText());
				beanExame.setNomePaciente(textFieldNome.getText());
				daoExame.finalizarExame(beanExame);

				exame.SolicitarExame(Integer.parseInt(textFieldIdMarcacao.getText()));

				dispose();

				// exame.SolicitarExame();

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));

		textFieldIdMarcacao.setColumns(10);

		textFieldMedico.setColumns(10);

		textFieldMotivo = new JTextField();
		textFieldMotivo.setVisible(false);
		textFieldMotivo.setEnabled(false);
		textFieldMotivo.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 858, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(panel_2,
						GroupLayout.PREFERRED_SIZE, 828, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(326).addComponent(btnNewButton,
						GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING,
								false)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
								.addComponent(textFieldIdMarcacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(108)
								.addComponent(textFieldMotivo, GroupLayout.PREFERRED_SIZE, 302,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textFieldMedico, GroupLayout.PREFERRED_SIZE, 208,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(10).addComponent(panel_1,
								GroupLayout.PREFERRED_SIZE, 828, GroupLayout.PREFERRED_SIZE))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldIdMarcacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(textFieldMotivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE).addGap(37)
				.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(117, Short.MAX_VALUE)));

		JLabel lblNewLabel = new JLabel("Dados do paciente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblNome = new JLabel("Nome :");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNascimento = new JLabel("Nascimento :");
		lblNascimento.setFont(new Font("Tahoma", Font.BOLD, 14));

		textFieldNome = new JTextField();
		textFieldNome.setEnabled(false);
		textFieldNome.setColumns(10);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(lblNome).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)
						.addGap(36).addComponent(lblNascimento).addGap(4).addComponent(formattedTextFieldNascimento,
								GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel))
				.addContainerGap(22, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblNewLabel).addGap(18)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblNome)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNascimento, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(formattedTextFieldNascimento, GroupLayout.PREFERRED_SIZE, 33,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(17, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);

	}
}

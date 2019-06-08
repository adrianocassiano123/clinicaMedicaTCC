package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.apache.hadoop.classification.InterfaceAudience.Public;

import ModeloConexao.ConexaoBD;

public class TelaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuarioLogin;
	private JPasswordField passwordFieldSenha;

	ConexaoBD conexao = new ConexaoBD();

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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

	public void acessar() {

		if (!textUsuarioLogin.getText().isEmpty() || !passwordFieldSenha.getText().isEmpty()) {

			conexao.conexao();

			try {
				conexao.executarSQL(
						"select *from tab_usuarios where nome_usuario='" + textUsuarioLogin.getText() + "'");
				conexao.rs.first();

				if (conexao.rs.getString("senha_usuario").equals(passwordFieldSenha.getText())) {
					TelaPrincipal telaPrincipal = new TelaPrincipal(textUsuarioLogin.getText());
					telaPrincipal.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(rootPane, "Senha ou Usuário Invalidos");
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(rootPane, "Senha ou Usuário Invalidos");
			}

			conexao.desconectar();

		} else {
			JOptionPane.showMessageDialog(null, "Digite seu login e sua senha");
		}

	}

	public TelaLogin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);

		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 13));

		textUsuarioLogin = new JTextField();
		textUsuarioLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					acessar();

				}
			}
		});
		textUsuarioLogin.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 13));

		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				acessar();
			}
		});

		btnAcessar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				acessar();
			}
		});

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				dispose();

			}
		});

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					acessar();

				}

			}
		});

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\LHSPC\\Desktop\\campos.png"));

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(TelaLogin.class.getResource("/imagem/ClinicalMeds.svg.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addGap(70)
						.addGroup(gl_contentPane
								.createParallelGroup(Alignment.TRAILING).addComponent(lblUsurio).addComponent(lblSenha))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textUsuarioLogin, 227, 227, 227)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(passwordFieldSenha, GroupLayout.PREFERRED_SIZE, 227,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnAcessar)
												.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
												.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 61,
														GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(101).addComponent(label_1)))
				.addGap(125).addComponent(label, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(78)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(label_1)
								.addPreferredGap(ComponentPlacement.UNRELATED)))
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblUsurio)
						.addComponent(textUsuarioLogin, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addGap(14)
				.addGroup(
						gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(passwordFieldSenha, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSenha))
				.addGap(26).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnAcessar)
						.addComponent(btnSair))
				.addGap(27)));
		contentPane.setLayout(gl_contentPane);
	}
}

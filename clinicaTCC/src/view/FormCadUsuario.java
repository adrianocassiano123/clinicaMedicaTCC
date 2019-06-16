package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import ModeloConexao.ConexaoBD;
import modelo.BeanUsuario;
import modelo.ModeloTabela;
import modeloDao.DaoUsuario;
import modeloDao.TabelaTela;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class FormCadUsuario extends JFrame implements TabelaTela{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JComboBox<?> comboBoxTipoUsuario;
	int flag = 0; // Define se o usuário vai salvar ou alterar

	BeanUsuario modUsuario = new BeanUsuario();
	DaoUsuario daoUsuario = new DaoUsuario();
	ConexaoBD conexao = new ConexaoBD();
	private JTextField textFieldPesquisa;
	JButton btnPesquisarUsuario = new JButton("Pesquisar");
	JButton btnSalvarUsuario = new JButton("Salvar");
	JButton btnCancelarUsuario = new JButton("Cancelar");
	JButton btnNovoUsuario = new JButton("Novo");
	JButton btnEditarUsuario = new JButton("Editar");
	private JTable tableUsuarios = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	private final JPasswordField passwordSenha = new JPasswordField();
	private final JPasswordField passwordConfSenha = new JPasswordField();
	private final JTextField textFieldIdUsuario = new JTextField();
	JButton buttonExcluirMouClick = new JButton("Excluir ");
	private final JPanel panel = new JPanel();
	private final JLabel label = new JLabel("Cadastro de Usu\u00E1rios");
	private final JPanel panel_1 = new JPanel();

	@Override
	public void preencherTabela(String Sql) {

		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "ID", "LOGIN", "TIPO", "SENHA" };
		conexao.conexao();
		conexao.executarSQL(Sql);

		try {
			conexao.rs.first(); // Seta o primeiro registro

			do {
				dados.add(new Object[] { conexao.rs.getInt("id_usuario"), conexao.rs.getString("nome_usuario"),
						conexao.rs.getString("tipo_usuario"), conexao.rs.getString("senha_usuario") });

			} while (conexao.rs.next());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, "Faça outra Pesquisa");
		}

		ModeloTabela modeloTabela = new ModeloTabela(dados, colunas);
		tableUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableUsuarios.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				String nomeUsuario = "" + tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(), 1);
				conexao.conexao();
				conexao.executarSQL("select *from tab_usuarios where nome_usuario= '" + nomeUsuario + "'");

				try {

					conexao.rs.first();

					textFieldUsuario.setText(conexao.rs.getString("nome_usuario"));
					comboBoxTipoUsuario.setEditable(true);
					comboBoxTipoUsuario.setSelectedItem(conexao.rs.getString("tipo_usuario"));
					comboBoxTipoUsuario.setEditable(false);
					passwordSenha.setText(String.valueOf(conexao.rs.getInt("senha_usuario")));
					textFieldIdUsuario.setText(String.valueOf(conexao.rs.getInt("id_usuario")));

					btnEditarUsuario.setEnabled(true);
					btnNovoUsuario.setEnabled(false);
					btnCancelarUsuario.setEnabled(true);
				//	btnExcluirUsuario.setVisible(false);
					buttonExcluirMouClick.setVisible(true);
					buttonExcluirMouClick.setVisible(true);

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro ao acessar dados" + e);
				}

				conexao.desconectar();

			}
		});

		tableUsuarios.setModel(modeloTabela);
		tableUsuarios.getColumnModel().getColumn(0).setPreferredWidth(30); // Zero é o primeiro registro
		tableUsuarios.getColumnModel().getColumn(0).setResizable(false); // Não permite esticar a tabela com o mouse
		tableUsuarios.getColumnModel().getColumn(1).setPreferredWidth(180);
		tableUsuarios.getColumnModel().getColumn(1).setResizable(false);
		tableUsuarios.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableUsuarios.getColumnModel().getColumn(2).setResizable(false);
		tableUsuarios.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableUsuarios.getColumnModel().getColumn(3).setResizable(false);

		tableUsuarios.getTableHeader().setReorderingAllowed(false); // Não permite reordenar o cabeçalho
		tableUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite selecionar apenas um dado por

		conexao.desconectar();

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadUsuario frame = new FormCadUsuario();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormCadUsuario() {
		panel.setBackground(SystemColor.activeCaption);
		textFieldIdUsuario.setColumns(10);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		preencherTabela("select *from tab_usuarios order by nome_usuario");
		textFieldIdUsuario.setEditable(false);
		textFieldIdUsuario.setVisible(false);
		btnEditarUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnEditarUsuario.setEnabled(false);
		btnEditarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;
				textFieldUsuario.setEnabled(true);
				passwordConfSenha.setEnabled(true);
				passwordSenha.setEnabled(true);
				comboBoxTipoUsuario.setEnabled(true);
				btnCancelarUsuario.setEnabled(true);
				btnSalvarUsuario.setEnabled(true);
				btnEditarUsuario.setEnabled(false);
				btnNovoUsuario.setEnabled(true);
				//btnExcluirUsuario.setEnabled(false);

			}
		});
		btnSalvarUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnSalvarUsuario.setEnabled(false);
		btnSalvarUsuario.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				if (textFieldUsuario.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Usuário ");
					textFieldUsuario.requestFocus();

				} else if (passwordSenha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite a Senha ");

				} else if (passwordConfSenha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "confirme a Senha ");

				} else {

					if (flag == 1) {

						if (passwordSenha.getText().equals(passwordConfSenha.getText())) {

							modUsuario.setLoginUsuario(textFieldUsuario.getText());
							modUsuario.setSenhaUsuario(passwordSenha.getText());
							modUsuario.setTipoUsuario(comboBoxTipoUsuario.getSelectedItem().toString());

							daoUsuario.salvar(modUsuario);

							textFieldUsuario.setText("");
							passwordConfSenha.setText("");
							passwordSenha.setText("");
							textFieldUsuario.setEnabled(false);
							comboBoxTipoUsuario.setEnabled(false);
							btnSalvarUsuario.setEnabled(false);
							passwordConfSenha.setEnabled(false);
							passwordSenha.setEnabled(false);
							preencherTabela("select *from tab_usuarios order by nome_usuario");

						} else {
							JOptionPane.showMessageDialog(null, "Senhas não convergem");
						}

					} else {

						if (passwordSenha.getText().equals(passwordConfSenha.getText())) {
							modUsuario.setLoginUsuario(textFieldUsuario.getText());
							modUsuario.setSenhaUsuario(passwordSenha.getText());
							modUsuario.setTipoUsuario(comboBoxTipoUsuario.getSelectedItem().toString());
							modUsuario.setIdUsuario(Integer.parseInt(textFieldIdUsuario.getText()));
							daoUsuario.editarUsuario(modUsuario);

							textFieldUsuario.setText("");
							passwordConfSenha.setText("");
							passwordSenha.setText("");
							textFieldIdUsuario.setText("");
							textFieldIdUsuario.setEnabled(false);
							textFieldUsuario.setEnabled(false);
							comboBoxTipoUsuario.setEnabled(false);
							btnSalvarUsuario.setEnabled(false);
							passwordConfSenha.setEnabled(false);
							passwordSenha.setEnabled(false);
							preencherTabela("select *from tab_usuarios order by nome_usuario");

						} else {
							JOptionPane.showMessageDialog(null, "Senhas não convergem");
						}

					}
				}
			}
		});
		btnCancelarUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnCancelarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textFieldUsuario.setText("");
				passwordConfSenha.setText("");
				passwordSenha.setText("");
				textFieldIdUsuario.setText("");
				textFieldIdUsuario.setEnabled(false);
				textFieldUsuario.setEnabled(false);
				comboBoxTipoUsuario.setEnabled(false);
				btnSalvarUsuario.setEnabled(false);
				passwordConfSenha.setEnabled(false);
				passwordSenha.setEnabled(false);
				//btnExcluirUsuario.setEnabled(false);
				btnNovoUsuario.setEnabled(true);

			}
		});
		btnNovoUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnNovoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 1;
				textFieldUsuario.setEnabled(true);
				passwordSenha.setEnabled(true);
				passwordConfSenha.setEnabled(true);
				comboBoxTipoUsuario.setEnabled(true);
				btnCancelarUsuario.setEnabled(true);
				btnSalvarUsuario.setEnabled(true);

			}
		});

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPesquisa.setColumns(10);
		btnPesquisarUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnPesquisarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modUsuario.setPesquisaUsuario(textFieldPesquisa.getText());
				BeanUsuario model = daoUsuario.pesquisarUsuario(modUsuario);
				textFieldIdUsuario.setText(String.valueOf(model.getIdUsuario()));
				textFieldUsuario.setText(model.getLoginUsuario());
				passwordSenha.setText(model.getSenhaUsuario());
				comboBoxTipoUsuario.setEditable(true);/////////////////////////////// Editar combobox
				comboBoxTipoUsuario.setSelectedItem(model.getTipoUsuario());
				comboBoxTipoUsuario.setEditable(false);
				btnEditarUsuario.setEnabled(true);
				//btnExcluirUsuario.setEnabled(true);
				btnNovoUsuario.setEnabled(false);
				btnCancelarUsuario.setEnabled(true);
				textFieldPesquisa.setText("");
				preencherTabela("select *from tab_usuarios where nome_usuario like'%" + modUsuario.getPesquisaUsuario() + "%'");

			}
		});

		String tipoUsu[] = { "Administrador", "Recepcionista", "M\u00E9dico" };

		scrollPane = new JScrollPane();

		buttonExcluirMouClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = 0;
				resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir?");
				if (resposta == JOptionPane.YES_NO_OPTION) {

					daoUsuario.excluir(Integer.parseInt(textFieldIdUsuario.getText()));
					textFieldUsuario.setText("");
					passwordConfSenha.setText("");
					passwordSenha.setText("");
					btnEditarUsuario.setEnabled(false);
					btnNovoUsuario.setEnabled(true);
					buttonExcluirMouClick.setEnabled(false);
					preencherTabela("select *from tab_usuarios order by nome_usuario");

				}
			}
		});
		buttonExcluirMouClick.setFont(new Font("Tahoma", Font.BOLD, 14));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 838, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 858, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textFieldIdUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(772, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnPesquisarUsuario, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnNovoUsuario, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
										.addComponent(btnEditarUsuario, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
										.addGap(93)
										.addComponent(btnSalvarUsuario, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
										.addGap(90)
										.addComponent(buttonExcluirMouClick)
										.addGap(39))
									.addGap(18)
									.addComponent(btnCancelarUsuario, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
							.addGap(29))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldIdUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovoUsuario, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelarUsuario, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonExcluirMouClick, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalvarUsuario, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditarUsuario, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisarUsuario, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
				JLabel lblUsuario = new JLabel("Usu\u00E1rio :");
				lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
				textFieldUsuario = new JTextField();
				textFieldUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
				textFieldUsuario.setEnabled(false);
				textFieldUsuario.setColumns(10);
		
				JLabel lblSenha = new JLabel("Senha :");
				lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordSenha.setEnabled(false);
		
				JLabel labelConfirmarSenha = new JLabel("Confirmar Senha :");
				labelConfirmarSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
				passwordConfSenha.setEnabled(false);
		comboBoxTipoUsuario = new JComboBox<Object>(tipoUsu);
		comboBoxTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxTipoUsuario.setEnabled(false);
		
				JLabel lblTipoUsuario = new JLabel("Tipo de Usu\u00E1rio :");
				lblTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblUsuario)
					.addGap(4)
					.addComponent(textFieldUsuario, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(lblSenha)
					.addGap(4)
					.addComponent(passwordSenha, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(labelConfirmarSenha, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addComponent(passwordConfSenha, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(553, Short.MAX_VALUE)
					.addComponent(lblTipoUsuario)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBoxTipoUsuario, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(17)
							.addComponent(lblUsuario))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(11)
							.addComponent(textFieldUsuario, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(16)
							.addComponent(lblSenha))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(12)
							.addComponent(passwordSenha, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(16)
							.addComponent(labelConfirmarSenha))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(12)
							.addComponent(passwordConfSenha, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxTipoUsuario, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipoUsuario))
					.addContainerGap())
		);
		panel_1.setBorder(new LineBorder(SystemColor.activeCaption));
		panel_1.setLayout(gl_panel_1);
		label.setFont(new Font("Tahoma", Font.BOLD, 21));
		
		panel.add(label);
		scrollPane.setViewportView(tableUsuarios);
		contentPane.setLayout(gl_contentPane);

	}
}

package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import ModeloConexao.ConexaoBD;
import modelo.BeanPaciente;
import modelo.ModeloTabela;
import modeloDao.DaoPacientes;
import modeloDao.TabelaTela;

import javax.swing.ScrollPaneConstants;

public class FormCadPacientes extends JFrame implements TabelaTela{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomePaciente;
	private JComboBox<?> comboBoxSexoPaciente;
	int flag = 0; // Define se o usuário vai salvar ou alterar

	BeanPaciente modPaciente = new BeanPaciente();
	DaoPacientes daoPaciente = new DaoPacientes();
	ConexaoBD conexao = new ConexaoBD();
	private JTextField textFieldPesquisaPaciente;
	JButton btnPesquisarPaciente = new JButton("Pesquisar");
	JButton btnSalvarPaciente = new JButton("Salvar");
	JButton btnCancelarPaciente = new JButton("Cancelar");
	JButton btnNovoPaciente = new JButton("Novo");
	JButton btnEditarPaciente = new JButton("Editar");
	private JTable tablePacientes = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	private final JTextField textFieldIdPaciente = new JTextField();
	JButton buttonExcluirMouClick = new JButton("Excluir ");
	private JTextField textFieldRgPaciente;
	private JTextField textFieldNascimentPaciente = new JTextField();
	private final JLabel lblNascimento = new JLabel("Nascimento :");
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JLabel lblLogradouro = new JLabel("Logradouro :");
	private final JTextField textFieldLogradouroPaciente = new JTextField();
	private final JLabel lblNmero = new JLabel("N\u00FAmero :");
	private final JTextField textFieldNumeroPaciente = new JTextField();
	private final JLabel lblCpf = new JLabel("CPF :");
	private final JLabel lblCidade = new JLabel("Cidade :");
	private final JTextField textFieldCidadePaciente = new JTextField();
	private final JLabel labelUFPaciente = new JLabel("UF :");
	private final JTextField textFieldEstadoPaciente = new JTextField();
	private final JLabel lblBairro = new JLabel("Bairro :");
	private final JTextField textFieldBairroPaciente = new JTextField();
	private final JLabel label = new JLabel("Bairro :");
	private final JTextField textField = new JTextField();
	private final JLabel lblCep = new JLabel("CEP :");
	private JTextField textFieldCepPaciente = new JTextField();
	private final JLabel lblDadosPessoais = new JLabel("Dados Pessoais:");
	private final JLabel lblEndereo = new JLabel("Endere\u00E7o :");
	private JTextField textFieldEmailPaciente;
	private JTextField textFieldConvenioPaci;
	private final JLabel lblTelefone = new JLabel("Telefone :");
	private final JTextField textFieldTelefonePaciente = new JTextField();
	private JTextField textFieldCpfPaciente = new JTextField();;

	
	@Override
	public void preencherTabela(String Sql) {

		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "ID", "NOME", "SEXO", "LOGRADOURO", "CIDADE", "ESTADO", "BAIRRO", "EMAIL",
				"CONVÊNIO", "NASCIMENTO", "NUMERO", "RG", "CPF", "CEP", "TELEFONE" };
		conexao.conexao();
		conexao.executarSQL(Sql);

		try {
			conexao.rs.first(); // Seta o primeiro registro

			do {
				dados.add(new Object[] { conexao.rs.getInt("id_paciente"), conexao.rs.getString("nome_paciente"),
						conexao.rs.getString("sexo_paciente"), conexao.rs.getString("logradouro_paciente"),
						conexao.rs.getString("cidade_paciente"), conexao.rs.getString("estado_paciente"),
						conexao.rs.getString("bairro_paciente"), conexao.rs.getString("email_paciente"),
						conexao.rs.getString("convenio_paciente"), conexao.rs.getString("nasc_paciente"),
						conexao.rs.getInt("num_resid_paciente"), conexao.rs.getString("rg_paciente"),
						conexao.rs.getString("cpf_paciente"), conexao.rs.getString("cep_paciente"),
						conexao.rs.getString("telefone_paciente") });

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

					textFieldIdPaciente.setText(String.valueOf(conexao.rs.getInt("id_paciente")));
					textFieldNomePaciente.setText(conexao.rs.getString("nome_paciente"));
					comboBoxSexoPaciente.setEditable(true);
					comboBoxSexoPaciente.setSelectedItem(conexao.rs.getString("sexo_paciente"));
					comboBoxSexoPaciente.setEditable(false);
					textFieldLogradouroPaciente.setText(conexao.rs.getString("logradouro_paciente"));
					textFieldCidadePaciente.setText(conexao.rs.getString("cidade_paciente"));
					textFieldEstadoPaciente.setText(conexao.rs.getString("estado_paciente"));
					textFieldBairroPaciente.setText(conexao.rs.getString("bairro_paciente"));
					textFieldEmailPaciente.setText(conexao.rs.getString("email_paciente"));
					textFieldConvenioPaci.setText(conexao.rs.getString("convenio_paciente"));
					textFieldNascimentPaciente.setText(conexao.rs.getString("nasc_paciente"));
					textFieldNumeroPaciente.setText(conexao.rs.getString("num_resid_paciente"));
					textFieldRgPaciente.setText(conexao.rs.getString("rg_paciente"));
					textFieldCpfPaciente.setText(conexao.rs.getString("cpf_paciente"));
					textFieldCepPaciente.setText(conexao.rs.getString("cep_paciente"));
					textFieldTelefonePaciente.setText(conexao.rs.getString("telefone_paciente"));

					btnEditarPaciente.setEnabled(true);
					btnNovoPaciente.setEnabled(false);
					btnCancelarPaciente.setEnabled(true);
			//		btnExcluirPaciente.setVisible(false);
					buttonExcluirMouClick.setEnabled(true);

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
		tablePacientes.getColumnModel().getColumn(2).setResizable(true);
		tablePacientes.getColumnModel().getColumn(3).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(3).setResizable(true);
		tablePacientes.getColumnModel().getColumn(4).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(4).setResizable(true);
		tablePacientes.getColumnModel().getColumn(5).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(5).setResizable(true);
		tablePacientes.getColumnModel().getColumn(6).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(6).setResizable(true);
		tablePacientes.getColumnModel().getColumn(7).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(7).setResizable(true);
		tablePacientes.getColumnModel().getColumn(8).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(8).setResizable(true);
		tablePacientes.getColumnModel().getColumn(9).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(9).setResizable(true);
		tablePacientes.getColumnModel().getColumn(10).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(10).setResizable(true);
		tablePacientes.getColumnModel().getColumn(11).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(11).setResizable(true);
		tablePacientes.getColumnModel().getColumn(12).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(12).setResizable(true);
		tablePacientes.getColumnModel().getColumn(13).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(13).setResizable(true);
		tablePacientes.getColumnModel().getColumn(14).setPreferredWidth(200);
		tablePacientes.getColumnModel().getColumn(14).setResizable(true);

		tablePacientes.getTableHeader().setReorderingAllowed(false); // Não permite reordenar o cabeçalho
		tablePacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite selecionar apenas um dado por

		conexao.desconectar();

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadPacientes frame = new FormCadPacientes();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormCadPacientes() {
		
		preencherTabela("select *from tab_paciente order by nome_paciente");
		textFieldTelefonePaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldTelefonePaciente.setEnabled(false);
		textFieldTelefonePaciente.setColumns(10);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEndereo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDadosPessoais.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldCepPaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldCepPaciente.setEnabled(false);
		textFieldCepPaciente.setColumns(10);
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setEnabled(false);
		textField.setColumns(10);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldBairroPaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldBairroPaciente.setEnabled(false);
		textFieldBairroPaciente.setColumns(10);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldEstadoPaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldEstadoPaciente.setEnabled(false);
		textFieldEstadoPaciente.setColumns(10);
		labelUFPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldCidadePaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldCidadePaciente.setEnabled(false);
		textFieldCidadePaciente.setColumns(10);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNumeroPaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNumeroPaciente.setEnabled(false);
		textFieldNumeroPaciente.setColumns(10);
		lblNmero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldLogradouroPaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldLogradouroPaciente.setEnabled(false);
		textFieldLogradouroPaciente.setColumns(10);
		lblLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.setBackground(SystemColor.activeCaption);
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.activeCaption));
		btnNovoPaciente.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldCepPaciente.setEnabled(false);
		textFieldCpfPaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldCpfPaciente.setColumns(10);
		textFieldCpfPaciente.setEnabled(false);

		btnNovoPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 1;
				textFieldNomePaciente.setEnabled(true);
				comboBoxSexoPaciente.setEnabled(true);
				textFieldLogradouroPaciente.setEnabled(true);
				textFieldCidadePaciente.setEnabled(true);
				textFieldEstadoPaciente.setEnabled(true);
				textFieldBairroPaciente.setEnabled(true);
				textFieldEmailPaciente.setEnabled(true);
				textFieldConvenioPaci.setEnabled(true);
				textFieldNascimentPaciente.setEnabled(true);
				textFieldNumeroPaciente.setEnabled(true);
				textFieldRgPaciente.setEnabled(true);
				textFieldCpfPaciente.setEnabled(true);
				textFieldCepPaciente.setEnabled(true);
				textFieldTelefonePaciente.setEnabled(true);

				btnNovoPaciente.setEnabled(false);
				btnCancelarPaciente.setEnabled(true);
				btnSalvarPaciente.setEnabled(true);
			

			}
		});

		try {
			javax.swing.text.MaskFormatter cpf = new javax.swing.text.MaskFormatter("###.###.###-##"); // Mascara data
			textFieldCpfPaciente = new javax.swing.JFormattedTextField(cpf);
			textFieldCpfPaciente.setEnabled(false);
		} catch (Exception e) {
		}

		try {
			javax.swing.text.MaskFormatter nascimento = new javax.swing.text.MaskFormatter("##/##/####"); // Mascara
																											// data
			textFieldNascimentPaciente = new javax.swing.JFormattedTextField(nascimento);
		} catch (Exception e) {
		}

		try {
			javax.swing.text.MaskFormatter cep = new javax.swing.text.MaskFormatter("##.###-###"); // Mascara CEP
			textFieldCepPaciente = new javax.swing.JFormattedTextField(cep);
			textFieldCepPaciente.setEnabled(false);
		} catch (Exception e) {
		}

		btnSalvarPaciente.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnSalvarPaciente.setEnabled(false);
		btnSalvarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (textFieldNomePaciente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Nome ");

				} else if (textFieldLogradouroPaciente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite O Logradouro ");

				} else if (textFieldCidadePaciente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite a Cidade ");

				} else if (textFieldEstadoPaciente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite a UF ");

				} else if (textFieldBairroPaciente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Bairro ");

				} else if (textFieldEmailPaciente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o E-mail ");
				} else if (textFieldConvenioPaci.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Convênio ");
				} else if (textFieldNascimentPaciente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Nascimento ");
				} else if (textFieldNumeroPaciente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Número ");
				} else if (textFieldRgPaciente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o RG ");
				} else if (textFieldCepPaciente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o CEP ");
				} else if (textFieldTelefonePaciente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Telefone ");

				}

				else {

					if (flag == 1) {

						modPaciente.setNome(textFieldNomePaciente.getText());
						modPaciente.setSexo(comboBoxSexoPaciente.getSelectedItem().toString());
						modPaciente.setLogradouro(textFieldLogradouroPaciente.getText());
						modPaciente.setCidade(textFieldCidadePaciente.getText());
						modPaciente.setEstado(textFieldEstadoPaciente.getText());
						modPaciente.setBairro(textFieldBairroPaciente.getText());
						modPaciente.setEmail(textFieldEmailPaciente.getText());
						modPaciente.setConvenioPaciente(textFieldConvenioPaci.getText());
						modPaciente.setNascimento(textFieldNascimentPaciente.getText());
						modPaciente.setNumero(Integer.parseInt(textFieldNumeroPaciente.getText()));
						modPaciente.setRg(textFieldRgPaciente.getText());
						modPaciente.setCpf(textFieldCpfPaciente.getText());
						modPaciente.setCep(textFieldCepPaciente.getText());
						modPaciente.setTelefone(textFieldTelefonePaciente.getText());

						daoPaciente.salvar(modPaciente);

						textFieldNomePaciente.setText("");
						textFieldLogradouroPaciente.setText("");
						textFieldCidadePaciente.setText("");
						textFieldEstadoPaciente.setText("");
						textFieldBairroPaciente.setText("");
						textFieldEmailPaciente.setText("");
						textFieldConvenioPaci.setText("");
						textFieldNascimentPaciente.setText("");
						textFieldRgPaciente.setText("");
						textFieldCpfPaciente.setText("");
						textFieldCepPaciente.setText("");
						textFieldTelefonePaciente.setText("");

						textFieldNomePaciente.setEnabled(false);
						comboBoxSexoPaciente.setEnabled(false);
						textFieldLogradouroPaciente.setEnabled(false);
						textFieldCidadePaciente.setEnabled(false);
						textFieldEstadoPaciente.setEnabled(false);
						textFieldBairroPaciente.setEnabled(false);
						textFieldEmailPaciente.setEnabled(false);
						textFieldConvenioPaci.setEnabled(false);
						textFieldNascimentPaciente.setEnabled(false);
						textFieldNumeroPaciente.setEnabled(false);
						textFieldRgPaciente.setEnabled(false);
						textFieldCpfPaciente.setEnabled(false);
						textFieldCepPaciente.setEnabled(false);
						textFieldTelefonePaciente.setEnabled(false);
						
						btnNovoPaciente.setEnabled(true);
						btnCancelarPaciente.setEnabled(false);
						btnSalvarPaciente.setEnabled(false);
						comboBoxSexoPaciente.setEnabled(false);

						preencherTabela("select *from tab_paciente order by nome_paciente");

					} else {
						modPaciente.setCodPaciente(Integer.parseInt(textFieldIdPaciente.getText()));
						modPaciente.setNome(textFieldNomePaciente.getText());
						modPaciente.setSexo(comboBoxSexoPaciente.getSelectedItem().toString());
						modPaciente.setLogradouro(textFieldLogradouroPaciente.getText());
						modPaciente.setCidade(textFieldCidadePaciente.getText());
						modPaciente.setEstado(textFieldEstadoPaciente.getText());
						modPaciente.setBairro(textFieldBairroPaciente.getText());
						modPaciente.setEmail(textFieldEmailPaciente.getText());
						modPaciente.setConvenioPaciente(textFieldConvenioPaci.getText());
						modPaciente.setNascimento(textFieldNascimentPaciente.getText());
						modPaciente.setNumero(Integer.parseInt(textFieldNumeroPaciente.getText()));
						modPaciente.setRg(textFieldRgPaciente.getText());
						modPaciente.setCpf(textFieldCpfPaciente.getText());
						modPaciente.setCep(textFieldCepPaciente.getText());
						modPaciente.setTelefone(textFieldTelefonePaciente.getText());

						daoPaciente.editarPaciente(modPaciente); // Faz alteração

						textFieldNomePaciente.setText("");
						textFieldLogradouroPaciente.setText("");
						textFieldCidadePaciente.setText("");
						textFieldEstadoPaciente.setText("");
						textFieldBairroPaciente.setText("");
						textFieldEmailPaciente.setText("");
						textFieldConvenioPaci.setText("");
						textFieldNascimentPaciente.setText("");
						textFieldRgPaciente.setText("");
						textFieldCpfPaciente.setText("");
						textFieldCepPaciente.setText("");
						textFieldTelefonePaciente.setText("");

						textFieldNomePaciente.setEnabled(false);
						comboBoxSexoPaciente.setEnabled(false);
						textFieldLogradouroPaciente.setEnabled(false);
						textFieldCidadePaciente.setEnabled(false);
						textFieldEstadoPaciente.setEnabled(false);
						textFieldBairroPaciente.setEnabled(false);
						textFieldEmailPaciente.setEnabled(false);
						textFieldConvenioPaci.setEnabled(false);
						textFieldNascimentPaciente.setEnabled(false);
						textFieldNumeroPaciente.setEnabled(false);
						textFieldRgPaciente.setEnabled(false);
						textFieldCpfPaciente.setEnabled(false);
						textFieldCepPaciente.setEnabled(false);
						textFieldTelefonePaciente.setEnabled(false);

						btnCancelarPaciente.setEnabled(false);
						btnSalvarPaciente.setEnabled(false);
						comboBoxSexoPaciente.setEnabled(false);

						preencherTabela("select *from tab_paciente order by nome_paciente");

					}
				}
			}
		});
		btnEditarPaciente.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnEditarPaciente.setEnabled(false);
		btnEditarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;

				textFieldNomePaciente.setEnabled(true);
				comboBoxSexoPaciente.setEnabled(true);
				textFieldLogradouroPaciente.setEnabled(true);
				textFieldCidadePaciente.setEnabled(true);
				textFieldEstadoPaciente.setEnabled(true);
				textFieldBairroPaciente.setEnabled(true);
				textFieldEmailPaciente.setEnabled(true);
				textFieldConvenioPaci.setEnabled(true);
				textFieldNascimentPaciente.setEnabled(true);
				textFieldNumeroPaciente.setEnabled(true);
				textFieldRgPaciente.setEnabled(true);
				textFieldCpfPaciente.setEnabled(true);
				textFieldCepPaciente.setEnabled(true);
				textFieldTelefonePaciente.setEnabled(true);

				btnCancelarPaciente.setEnabled(true);
				btnSalvarPaciente.setEnabled(true);
				btnEditarPaciente.setEnabled(false);
				btnNovoPaciente.setEnabled(true);
	//			btnExcluirPaciente.setEnabled(false);

			}
		});

		buttonExcluirMouClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = 0;
				resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir?");
				if (resposta == JOptionPane.YES_NO_OPTION) {

					daoPaciente.excluir(Integer.parseInt(textFieldIdPaciente.getText()));
					textFieldNomePaciente.setText("");
					textFieldLogradouroPaciente.setText("");
					textFieldCidadePaciente.setText("");
					textFieldEstadoPaciente.setText("");
					textFieldBairroPaciente.setText("");
					textFieldEmailPaciente.setText("");
					textFieldConvenioPaci.setText("");
					textFieldNascimentPaciente.setText("");
					textFieldRgPaciente.setText("");
					textFieldCpfPaciente.setText("");
					textFieldCepPaciente.setText("");
					textFieldTelefonePaciente.setText("");
					
					
					textFieldNomePaciente.setEnabled(false);
					comboBoxSexoPaciente.setEnabled(false);
					textFieldLogradouroPaciente.setEnabled(false);
					textFieldCidadePaciente.setEnabled(false);
					textFieldEstadoPaciente.setEnabled(false);
					textFieldBairroPaciente.setEnabled(false);
					textFieldEmailPaciente.setEnabled(false);
					textFieldConvenioPaci.setEnabled(false);
					textFieldNascimentPaciente.setEnabled(false);
					textFieldNumeroPaciente.setEnabled(false);
					textFieldRgPaciente.setEnabled(false);
					textFieldCpfPaciente.setEnabled(false);
					textFieldCepPaciente.setEnabled(false);
					textFieldTelefonePaciente.setEnabled(false);

									
					btnEditarPaciente.setEnabled(false);
					btnNovoPaciente.setEnabled(true);
					buttonExcluirMouClick.setEnabled(false);
					preencherTabela("select *from tab_paciente order by nome_paciente");

				}
			}
		});
		buttonExcluirMouClick.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelarPaciente.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnCancelarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textFieldNomePaciente.setText("");
				textFieldLogradouroPaciente.setText("");
				textFieldCidadePaciente.setText("");
				textFieldEstadoPaciente.setText("");
				textFieldBairroPaciente.setText("");
				textFieldEmailPaciente.setText("");
				textFieldConvenioPaci.setText("");
				textFieldNascimentPaciente.setText("");
				textFieldNumeroPaciente.setText("");
				textFieldRgPaciente.setText("");
				textFieldCpfPaciente.setText("");
				textFieldCepPaciente.setText("");
				textFieldTelefonePaciente.setText("");
				textFieldNomePaciente.setEnabled(false);
				comboBoxSexoPaciente.setEnabled(false);
				textFieldLogradouroPaciente.setEnabled(false);
				textFieldCidadePaciente.setEnabled(false);
				textFieldEstadoPaciente.setEnabled(false);
				textFieldBairroPaciente.setEnabled(false);
				textFieldEmailPaciente.setEnabled(false);
				textFieldConvenioPaci.setEnabled(false);
				textFieldNascimentPaciente.setEnabled(false);
				textFieldNumeroPaciente.setEnabled(false);
				textFieldRgPaciente.setEnabled(false);
				textFieldCpfPaciente.setEnabled(false);
				textFieldCepPaciente.setEnabled(false);
				textFieldTelefonePaciente.setEnabled(false);
				
				btnEditarPaciente.setEnabled(false);
				btnSalvarPaciente.setEnabled(false);
	//			btnExcluirPaciente.setEnabled(false);
				btnNovoPaciente.setEnabled(true);
				
				preencherTabela("select *from tab_paciente order by nome_paciente");

			}
		});

		textFieldPesquisaPaciente = new JTextField();
		textFieldPesquisaPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPesquisaPaciente.setColumns(10);
		btnPesquisarPaciente.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnPesquisarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modPaciente.setPesquisaPaciente(textFieldPesquisaPaciente.getText());
				BeanPaciente model = daoPaciente.pesquisarPaciente(modPaciente);
				textFieldIdPaciente.setText(String.valueOf(model.getCodPaciente()));
				textFieldNomePaciente.setText(model.getNome());
				comboBoxSexoPaciente.setEditable(true);/////////////////////////////// Editar combobox
				comboBoxSexoPaciente.setSelectedItem(model.getSexo());

				textFieldLogradouroPaciente.setText(model.getLogradouro());
				textFieldCidadePaciente.setText(model.getCidade());
				textFieldEstadoPaciente.setText(model.getEstado());
				textFieldBairroPaciente.setText(model.getBairro());
				textFieldEmailPaciente.setText(model.getEmail());
				textFieldConvenioPaci.setText(model.getConvenioPaciente());
				textFieldNascimentPaciente.setText(model.getNascimento());
				textFieldNumeroPaciente.setText(String.valueOf(model.getNumero()));
				textFieldRgPaciente.setText(model.getRg());
				textFieldCpfPaciente.setText(model.getCpf());
				textFieldCepPaciente.setText(model.getCep());
				textFieldTelefonePaciente.setText(model.getTelefone());

	//			btnExcluirPaciente.setEnabled(true);
				comboBoxSexoPaciente.setEditable(false);
				btnEditarPaciente.setEnabled(true);
				btnNovoPaciente.setEnabled(false);
				btnCancelarPaciente.setEnabled(true);
				textFieldPesquisaPaciente.setText("");
				preencherTabela("select *from tab_paciente where nome_paciente like'%"
						+ modPaciente.getPesquisaPaciente() + "%'");

			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(btnNovoPaciente, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
					.addComponent(btnSalvarPaciente, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addGap(69)
					.addComponent(btnEditarPaciente, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(84)
					.addComponent(buttonExcluirMouClick, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(75)
					.addComponent(btnCancelarPaciente, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(textFieldPesquisaPaciente, GroupLayout.PREFERRED_SIZE, 658, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPesquisarPaciente, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
					.addGap(2))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelarPaciente, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNovoPaciente, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonExcluirMouClick, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditarPaciente, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalvarPaciente, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPesquisaPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisarPaciente, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
//		buttonExcluirMouClick.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//			}
//		});

		textFieldIdPaciente.setColumns(10);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		
		//textFieldIdPaciente.setEditable(false);
		textFieldIdPaciente.setVisible(false);

		String tipoUsu[] = { "Masculino", "Feminino" };

		scrollPane = new JScrollPane();
		panel_1.setLayout(gl_panel_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(SystemColor.activeCaption));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 805, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(textFieldIdPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 804, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 805, GroupLayout.PREFERRED_SIZE)
									.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 807, GroupLayout.PREFERRED_SIZE))
								.addGap(100)))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldIdPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
					.addGap(11))
		);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
								.addComponent(lblLogradouro, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textFieldLogradouroPaciente, GroupLayout.PREFERRED_SIZE, 486,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(lblNmero, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(textFieldNumeroPaciente,
										GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING,
								gl_panel_3.createSequentialGroup()
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
										.addGap(4).addComponent(textField, GroupLayout.PREFERRED_SIZE, 222,
												GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING,
								gl_panel_3.createSequentialGroup()
										.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel_3.createSequentialGroup().addContainerGap()
														.addComponent(lblCep, GroupLayout.PREFERRED_SIZE, 41,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(textFieldCepPaciente, GroupLayout.PREFERRED_SIZE,
																227, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel_3
														.createSequentialGroup()
														.addComponent(
																lblCidade, GroupLayout.PREFERRED_SIZE, 58,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(textFieldCidadePaciente, GroupLayout.DEFAULT_SIZE,
																312, Short.MAX_VALUE)))
										.addGap(18)
										.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_3
												.createSequentialGroup()
												.addComponent(labelUFPaciente, GroupLayout.PREFERRED_SIZE, 33,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textFieldEstadoPaciente, GroupLayout.PREFERRED_SIZE, 73,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblBairro, GroupLayout.PREFERRED_SIZE, 57,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textFieldBairroPaciente, GroupLayout.PREFERRED_SIZE, 222,
														GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel_3.createSequentialGroup()
														.addComponent(lblTelefone, GroupLayout.PREFERRED_SIZE, 68,
																GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(textFieldTelefonePaciente,
																GroupLayout.PREFERRED_SIZE, 222,
																GroupLayout.PREFERRED_SIZE))))
						.addComponent(lblEndereo, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_3
				.createSequentialGroup().addComponent(lblEndereo).addGap(8)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldNumeroPaciente, GroupLayout.PREFERRED_SIZE, 22,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLogradouro, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldLogradouroPaciente, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNmero, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup().addGap(13)
								.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 17,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(
												textFieldCidadePaciente, GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_3.createSequentialGroup().addGap(11).addGroup(gl_panel_3
								.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldBairroPaciente, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBairro, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldEstadoPaciente, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelUFPaciente, GroupLayout.PREFERRED_SIZE, 17,
										GroupLayout.PREFERRED_SIZE))))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_3.createSequentialGroup()
						.addGap(11).addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldCepPaciente, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCep, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGap(64).addComponent(label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup().addGap(9)
								.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldTelefonePaciente, GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTelefone, GroupLayout.PREFERRED_SIZE, 17,
												GroupLayout.PREFERRED_SIZE))
								.addGap(59)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_3.setLayout(gl_panel_3);

		JLabel lblCadastroDePacientes = new JLabel("Cadastro de Pacientes");
		panel_2.add(lblCadastroDePacientes);
		lblCadastroDePacientes.setFont(new Font("Tahoma", Font.BOLD, 21));
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.activeCaption));

		JLabel lblNome = new JLabel("Nome :");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldNomePaciente = new JTextField();
		textFieldNomePaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNomePaciente.setEnabled(false);
		textFieldNomePaciente.setColumns(10);

		JLabel lblSexoPaciente = new JLabel("Sexo :");
		lblSexoPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSexoPaciente = new JComboBox<Object>(tipoUsu);
		comboBoxSexoPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSexoPaciente.setEnabled(false);

		JLabel lblRg = new JLabel("RG :");
		lblRg.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldRgPaciente = new JTextField();
		textFieldRgPaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldRgPaciente.setEnabled(false);
		textFieldRgPaciente.setColumns(10);
		lblNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNascimentPaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNascimentPaciente.setEnabled(false);
		textFieldNascimentPaciente.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail :");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldEmailPaciente = new JTextField();
		textFieldEmailPaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldEmailPaciente.setEnabled(false);
		textFieldEmailPaciente.setColumns(10);

		JLabel lblConvnio = new JLabel("Conv\u00EAnio :");
		lblConvnio.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldConvenioPaci = new JTextField();
		textFieldConvenioPaci.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldConvenioPaci.setEnabled(false);
		textFieldConvenioPaci.setColumns(10);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel
								.createParallelGroup(Alignment.LEADING).addComponent(lblDadosPessoais).addGroup(gl_panel
										.createSequentialGroup().addContainerGap().addGroup(gl_panel
												.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
														.createSequentialGroup().addGroup(gl_panel
																.createParallelGroup(Alignment.LEADING, false)
																.addGroup(gl_panel.createSequentialGroup().addComponent(
																		lblNome)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(
																				textFieldNomePaciente,
																				GroupLayout.PREFERRED_SIZE, 458,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18).addComponent(lblSexoPaciente))
																.addGroup(gl_panel
																		.createSequentialGroup().addComponent(lblRg)
																		.addGap(24).addGroup(gl_panel
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(
																						textFieldEmailPaciente,
																						GroupLayout.PREFERRED_SIZE, 396,
																						GroupLayout.PREFERRED_SIZE)
																				.addGroup(gl_panel
																						.createSequentialGroup()
																						.addComponent(
																								textFieldRgPaciente,
																								GroupLayout.PREFERRED_SIZE,
																								127,
																								GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								ComponentPlacement.RELATED)
																						.addComponent(lblCpf)
																						.addPreferredGap(
																								ComponentPlacement.RELATED)
																						.addComponent(
																								textFieldCpfPaciente,
																								GroupLayout.PREFERRED_SIZE,
																								197,
																								GroupLayout.PREFERRED_SIZE)
																						.addGap(18)
																						.addComponent(lblNascimento)))))
														.addPreferredGap(ComponentPlacement.RELATED, 138,
																Short.MAX_VALUE))
												.addGroup(gl_panel.createSequentialGroup()
														.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 58,
																GroupLayout.PREFERRED_SIZE)
														.addGap(418)
														.addComponent(lblConvnio, GroupLayout.PREFERRED_SIZE, 81,
																GroupLayout.PREFERRED_SIZE)))
										.addGap(18)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(textFieldNascimentPaciente, GroupLayout.PREFERRED_SIZE,
														190, GroupLayout.PREFERRED_SIZE)
												.addComponent(comboBoxSexoPaciente, GroupLayout.PREFERRED_SIZE, 190,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(textFieldConvenioPaci, GroupLayout.PREFERRED_SIZE, 190,
														GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addComponent(lblDadosPessoais).addGap(7)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNome)
						.addComponent(textFieldNomePaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSexoPaciente).addComponent(comboBoxSexoPaciente, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblRg)
						.addComponent(textFieldNascimentPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldRgPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldCpfPaciente, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNascimento))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblConvnio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldEmailPaciente, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(textFieldConvenioPaci, GroupLayout.PREFERRED_SIZE, 22,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		scrollPane.setViewportView(tablePacientes);
		contentPane.setLayout(gl_contentPane);

	}
}

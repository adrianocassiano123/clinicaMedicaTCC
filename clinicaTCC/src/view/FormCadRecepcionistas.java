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
import modelo.BeanEndereco;
import modelo.BeanRecepcionista;
import modelo.ModeloTabela;
import modeloDao.DaoRecepcionista;
import modeloDao.TabelaTela;

public class FormCadRecepcionistas extends JFrame implements TabelaTela{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomeRecep;
	private JComboBox<?> comboBoxSexoRecep;
	int flag = 0; // Define se o usuário vai salvar ou alterar

	BeanRecepcionista modRecepcionista = new BeanRecepcionista();
	DaoRecepcionista daoRecepcionista = new DaoRecepcionista();
	BeanEndereco endereco = new BeanEndereco(); 
	ConexaoBD conexao = new ConexaoBD();
	
	private JTextField textFieldPesquisa;
	JButton btnPesquisar = new JButton("Pesquisar");
	JButton btnSalvar = new JButton("Salvar");
	JButton btnCancelar = new JButton("Cancelar");
	JButton btnNovo = new JButton("Novo");
	JButton btnEditar = new JButton("Editar");
	private JTable tableRecepcionistas = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	private final JTextField textFieldIdRecep = new JTextField();
	JButton buttonExcluirMouClick = new JButton("Excluir ");
	private JTextField textFieldRgRecep;
	private JTextField textFieldNascimentRecep= new JTextField();
	private final JLabel lblNascimento = new JLabel("Nascimento :");
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JLabel lblLogradouro = new JLabel("Logradouro :");
	private final JTextField textFieldLogradouroRecep = new JTextField();
	private final JLabel lblNmero = new JLabel("N\u00FAmero :");
	private final JTextField textFieldNumeroRecep = new JTextField();
	private final JLabel lblCpf = new JLabel("CPF :");
	private final JLabel lblCidade = new JLabel("Cidade :");
	private final JTextField textFieldCidadeRecep = new JTextField();
	private final JLabel labelUFPaciente = new JLabel("UF :");
	private final JTextField textFieldEstadoRecep = new JTextField();
	private final JLabel lblBairro = new JLabel("Bairro :");
	private final JTextField textFieldBairroRecep = new JTextField();
	private final JLabel label = new JLabel("Bairro :");
	private final JTextField textField = new JTextField();
	private final JLabel lblCep = new JLabel("CEP :");
	private JTextField textFieldCepRecep = new JTextField();
	private final JLabel lblDadosPessoais = new JLabel("Dados Pessoais:");
	private final JLabel lblEndereo = new JLabel("Endere\u00E7o :");
	private JTextField textFieldEmailRecep;
	private JTextField textFieldMatricula;
	private final JLabel lblTelefone = new JLabel("Telefone :");
	private final JTextField textFieldTelefoneRecep = new JTextField();
	private JTextField textFieldCpfRecep = new JTextField();
	
	
	@Override
	public void preencherTabela(String Sql) {
		
		
		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "ID", "NOME", "SEXO", "LOGRADOURO", "CIDADE", "ESTADO", "BAIRRO", "EMAIL",
				"MATRICULA", "NASCIMENTO", "NUMERO", "RG", "CPF", "CEP", "TELEFONE" };
		conexao.conexao();
		conexao.executarSQL(Sql);

		try {
			conexao.rs.first(); // Seta o primeiro registro

			do {
				dados.add(new Object[] {conexao.rs.getInt("id_recep"), 
										conexao.rs.getString("nome_recep"),
										conexao.rs.getString("sexo_recep"),
										conexao.rs.getString("logradouro_recep"),
										conexao.rs.getString("cidade_recep"),
										conexao.rs.getString("estado_recep"),
										conexao.rs.getString("bairro_recep"),
										conexao.rs.getString("email_recep"),
										conexao.rs.getString("matricula_recep"),
										conexao.rs.getString("nasc_recep"),
										conexao.rs.getInt("num_resid_recep"),
										conexao.rs.getString("rg_recep"),
										conexao.rs.getString("cpf_recep"),
										conexao.rs.getString("cep_recep"),
										conexao.rs.getString("telefone_recep") });

			} while (conexao.rs.next());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(rootPane, "Faça outra Pesquisa");
		}

		ModeloTabela modeloTabela = new ModeloTabela(dados, colunas);
		tableRecepcionistas.setBackground(SystemColor.controlHighlight);
		tableRecepcionistas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableRecepcionistas.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				String nomeRecep = "" + tableRecepcionistas.getValueAt(tableRecepcionistas.getSelectedRow(), 1);
				conexao.conexao();
				conexao.executarSQL("select *from tab_recepcionista where nome_recep= '" + nomeRecep + "'");

				try {

					conexao.rs.first();

					textFieldIdRecep.setText(String.valueOf(conexao.rs.getInt("id_recep")));
					textFieldNomeRecep.setText(conexao.rs.getString("nome_recep"));
					comboBoxSexoRecep.setEditable(true);
					comboBoxSexoRecep.setSelectedItem(conexao.rs.getString("sexo_recep"));
					comboBoxSexoRecep.setEditable(false);
					textFieldLogradouroRecep.setText(conexao.rs.getString("logradouro_recep"));
					textFieldCidadeRecep.setText(conexao.rs.getString("cidade_recep"));
					textFieldEstadoRecep.setText(conexao.rs.getString("estado_recep"));
					textFieldBairroRecep.setText(conexao.rs.getString("bairro_recep"));
					textFieldEmailRecep.setText(conexao.rs.getString("email_recep"));
					textFieldMatricula.setText(conexao.rs.getString("matricula_recep"));
					textFieldNascimentRecep.setText(conexao.rs.getString("nasc_recep"));
					textFieldNumeroRecep.setText(conexao.rs.getString("num_resid_recep"));
					textFieldRgRecep.setText(conexao.rs.getString("rg_recep"));
					textFieldCpfRecep.setText(conexao.rs.getString("cpf_recep"));
					textFieldCepRecep.setText(conexao.rs.getString("cep_recep"));
					textFieldTelefoneRecep.setText(conexao.rs.getString("telefone_recep"));

					btnEditar.setEnabled(true);
					btnNovo.setEnabled(false);
					btnCancelar.setEnabled(true);
					buttonExcluirMouClick.setEnabled(true);
					

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro ao acessar dados" + e);
				}

				conexao.desconectar();

			}
		});

		tableRecepcionistas.setModel(modeloTabela);
		tableRecepcionistas.getColumnModel().getColumn(0).setPreferredWidth(30); // Zero é o primeiro registro
		tableRecepcionistas.getColumnModel().getColumn(0).setResizable(false); // Não permite esticar a tabela com o mouse
		tableRecepcionistas.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableRecepcionistas.getColumnModel().getColumn(1).setResizable(true);
		tableRecepcionistas.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableRecepcionistas.getColumnModel().getColumn(2).setResizable(true);
		tableRecepcionistas.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableRecepcionistas.getColumnModel().getColumn(3).setResizable(true);
		tableRecepcionistas.getColumnModel().getColumn(4).setPreferredWidth(200);
		tableRecepcionistas.getColumnModel().getColumn(4).setResizable(true);
		tableRecepcionistas.getColumnModel().getColumn(5).setPreferredWidth(200);
		tableRecepcionistas.getColumnModel().getColumn(5).setResizable(true);
		tableRecepcionistas.getColumnModel().getColumn(6).setPreferredWidth(200);
		tableRecepcionistas.getColumnModel().getColumn(6).setResizable(true);
		tableRecepcionistas.getColumnModel().getColumn(7).setPreferredWidth(200);
		tableRecepcionistas.getColumnModel().getColumn(7).setResizable(true);
		tableRecepcionistas.getColumnModel().getColumn(8).setPreferredWidth(200);
		tableRecepcionistas.getColumnModel().getColumn(8).setResizable(true);
		tableRecepcionistas.getColumnModel().getColumn(9).setPreferredWidth(200);
		tableRecepcionistas.getColumnModel().getColumn(9).setResizable(true);
		tableRecepcionistas.getColumnModel().getColumn(10).setPreferredWidth(200);
		tableRecepcionistas.getColumnModel().getColumn(10).setResizable(true);
		tableRecepcionistas.getColumnModel().getColumn(11).setPreferredWidth(200);
		tableRecepcionistas.getColumnModel().getColumn(11).setResizable(true);
		tableRecepcionistas.getColumnModel().getColumn(12).setPreferredWidth(200);
		tableRecepcionistas.getColumnModel().getColumn(12).setResizable(true);
		tableRecepcionistas.getColumnModel().getColumn(13).setPreferredWidth(200);
		tableRecepcionistas.getColumnModel().getColumn(13).setResizable(true);
		tableRecepcionistas.getColumnModel().getColumn(14).setPreferredWidth(200);
		tableRecepcionistas.getColumnModel().getColumn(14).setResizable(true);


		tableRecepcionistas.getTableHeader().setReorderingAllowed(false); // Não permite reordenar o cabeçalho
		tableRecepcionistas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite selecionar apenas um dado por

		conexao.desconectar();

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadRecepcionistas frame = new FormCadRecepcionistas();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormCadRecepcionistas() {
		
		preencherTabela("select *from tab_recepcionista order by nome_recep");
		
		textFieldTelefoneRecep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldTelefoneRecep.setEnabled(false);
		textFieldTelefoneRecep.setColumns(10);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEndereo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDadosPessoais.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldCepRecep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldCepRecep.setEnabled(false);
		textFieldCepRecep.setColumns(10);
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setEnabled(false);
		textField.setColumns(10);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldBairroRecep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldBairroRecep.setEnabled(false);
		textFieldBairroRecep.setColumns(10);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldEstadoRecep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldEstadoRecep.setEnabled(false);
		textFieldEstadoRecep.setColumns(10);
		labelUFPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldCidadeRecep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldCidadeRecep.setEnabled(false);
		textFieldCidadeRecep.setColumns(10);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNumeroRecep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNumeroRecep.setEnabled(false);
		textFieldNumeroRecep.setColumns(10);
		lblNmero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldLogradouroRecep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldLogradouroRecep.setEnabled(false);
		textFieldLogradouroRecep.setColumns(10);
		lblLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.setBackground(SystemColor.activeCaption);
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.activeCaption));
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldCepRecep.setEnabled(false);
		textFieldCpfRecep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldCpfRecep.setColumns(10);
		textFieldCpfRecep.setEnabled(false);

		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 1;
				textFieldNomeRecep.setEnabled(true);
				comboBoxSexoRecep.setEnabled(true);
				textFieldLogradouroRecep.setEnabled(true);
				textFieldCidadeRecep.setEnabled(true);
				textFieldEstadoRecep.setEnabled(true);
				textFieldBairroRecep.setEnabled(true);
				textFieldEmailRecep.setEnabled(true);
				textFieldMatricula.setEnabled(true);
				textFieldNascimentRecep.setEnabled(true);
				textFieldNumeroRecep.setEnabled(true);
				textFieldRgRecep.setEnabled(true);
				textFieldCpfRecep.setEnabled(true);
				textFieldCepRecep.setEnabled(true);
				textFieldTelefoneRecep.setEnabled(true);
				

				btnNovo.setEnabled(false);
				btnCancelar.setEnabled(true);
				btnSalvar.setEnabled(true);
				
			}
		});

		try {
			javax.swing.text.MaskFormatter cpf = new javax.swing.text.MaskFormatter("###.###.###-##"); // Mascara data
			textFieldCpfRecep = new javax.swing.JFormattedTextField(cpf);
			textFieldCpfRecep.setEnabled(false);
		} catch (Exception e) {
		}

		try {
			javax.swing.text.MaskFormatter nascimento = new javax.swing.text.MaskFormatter("##/##/####"); // Mascara
																											// data
			textFieldNascimentRecep = new javax.swing.JFormattedTextField(nascimento);
		} catch (Exception e) {
		}

		try {
			javax.swing.text.MaskFormatter cep = new javax.swing.text.MaskFormatter("##.###-###"); // Mascara CEP
			textFieldCepRecep = new javax.swing.JFormattedTextField(cep);
			textFieldCepRecep.setEnabled(false);
		} catch (Exception e) {
		}

		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (textFieldNomeRecep.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Nome ");

				} else if (textFieldLogradouroRecep.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite O Logradouro ");

				} else if (textFieldCidadeRecep.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite a Cidade ");

				} else if (textFieldEstadoRecep.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite a UF ");

				} else if (textFieldBairroRecep.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Bairro ");

				} else if (textFieldEmailRecep.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o E-mail ");
				} else if (textFieldMatricula.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite a Matricula ");
				} else if (textFieldNascimentRecep.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Nascimento ");
				} else if (textFieldNumeroRecep.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Número ");
				} else if (textFieldRgRecep.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o RG ");
				} else if (textFieldCepRecep.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o CEP ");
				} else if (textFieldTelefoneRecep.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Telefone ");

				}

				else {

					if (flag == 1) {

						modRecepcionista.setNome(textFieldNomeRecep.getText());
						modRecepcionista.setSexo(comboBoxSexoRecep.getSelectedItem().toString());
						endereco.setLogradouro(textFieldLogradouroRecep.getText());
						endereco.setCidade(textFieldCidadeRecep.getText());
						endereco.setEstado(textFieldEstadoRecep.getText());
						endereco.setBairro(textFieldBairroRecep.getText());
						modRecepcionista.setEmail(textFieldEmailRecep.getText());
						modRecepcionista.setMatricula(textFieldMatricula.getText());
						modRecepcionista.setNascimento(textFieldNascimentRecep.getText());
						endereco.setNumero(Integer.parseInt(textFieldNumeroRecep.getText()));
						modRecepcionista.setRg(textFieldRgRecep.getText());
						modRecepcionista.setCpf(textFieldCpfRecep.getText());
						endereco.setCep(textFieldCepRecep.getText());
						modRecepcionista.setTelefone(textFieldTelefoneRecep.getText());

						daoRecepcionista.salvar(modRecepcionista,endereco);
						

						textFieldNomeRecep.setText("");
						textFieldLogradouroRecep.setText("");
						textFieldCidadeRecep.setText("");
						textFieldEstadoRecep.setText("");
						textFieldBairroRecep.setText("");
						textFieldEmailRecep.setText("");
						textFieldMatricula.setText("");
						textFieldNascimentRecep.setText("");
						textFieldRgRecep.setText("");
						textFieldCpfRecep.setText("");
						textFieldCepRecep.setText("");
						textFieldTelefoneRecep.setText("");

						textFieldNomeRecep.setEnabled(false);
						comboBoxSexoRecep.setEnabled(false);
						textFieldLogradouroRecep.setEnabled(false);
						textFieldCidadeRecep.setEnabled(false);
						textFieldEstadoRecep.setEnabled(false);
						textFieldBairroRecep.setEnabled(false);
						textFieldEmailRecep.setEnabled(false);
						textFieldMatricula.setEnabled(false);
						textFieldNascimentRecep.setEnabled(false);
						textFieldNumeroRecep.setEnabled(false);
						textFieldRgRecep.setEnabled(false);
						textFieldCpfRecep.setEnabled(false);
						textFieldCepRecep.setEnabled(false);
						textFieldTelefoneRecep.setEnabled(false);
						
						btnNovo.setEnabled(true);
						btnCancelar.setEnabled(false);
						btnSalvar.setEnabled(false);
						comboBoxSexoRecep.setEnabled(false);

						preencherTabela("select *from tab_recepcionista order by nome_recep");

					} else {
						modRecepcionista.setIdRecep(Integer.parseInt(textFieldIdRecep.getText()));
						modRecepcionista.setNome(textFieldNomeRecep.getText());
						modRecepcionista.setSexo(comboBoxSexoRecep.getSelectedItem().toString());
						endereco.setLogradouro(textFieldLogradouroRecep.getText());
						endereco.setCidade(textFieldCidadeRecep.getText());
						endereco.setEstado(textFieldEstadoRecep.getText());
						endereco.setBairro(textFieldBairroRecep.getText());
						modRecepcionista.setEmail(textFieldEmailRecep.getText());
						modRecepcionista.setMatricula(textFieldMatricula.getText());
						modRecepcionista.setNascimento(textFieldNascimentRecep.getText());
						endereco.setNumero(Integer.parseInt(textFieldNumeroRecep.getText()));
						modRecepcionista.setRg(textFieldRgRecep.getText());
						modRecepcionista.setCpf(textFieldCpfRecep.getText());
						endereco.setCep(textFieldCepRecep.getText());
						modRecepcionista.setTelefone(textFieldTelefoneRecep.getText());

						daoRecepcionista.editarRecepcionista(modRecepcionista,endereco); // Faz alteração

						textFieldNomeRecep.setText("");
						textFieldLogradouroRecep.setText("");
						textFieldCidadeRecep.setText("");
						textFieldEstadoRecep.setText("");
						textFieldBairroRecep.setText("");
						textFieldEmailRecep.setText("");
						textFieldMatricula.setText("");
						textFieldNascimentRecep.setText("");
						textFieldRgRecep.setText("");
						textFieldCpfRecep.setText("");
						textFieldCepRecep.setText("");
						textFieldTelefoneRecep.setText("");

						textFieldNomeRecep.setEnabled(false);
						comboBoxSexoRecep.setEnabled(false);
						textFieldLogradouroRecep.setEnabled(false);
						textFieldCidadeRecep.setEnabled(false);
						textFieldEstadoRecep.setEnabled(false);
						textFieldBairroRecep.setEnabled(false);
						textFieldEmailRecep.setEnabled(false);
						textFieldMatricula.setEnabled(false);
						textFieldNascimentRecep.setEnabled(false);
						textFieldNumeroRecep.setEnabled(false);
						textFieldRgRecep.setEnabled(false);
						textFieldCpfRecep.setEnabled(false);
						textFieldCepRecep.setEnabled(false);
						textFieldTelefoneRecep.setEnabled(false);

						btnCancelar.setEnabled(false);
						btnSalvar.setEnabled(false);
						comboBoxSexoRecep.setEnabled(false);
						
						preencherTabela("select *from tab_recepcionista order by nome_recep");
					

					}
				}
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;

				textFieldNomeRecep.setEnabled(true);
				comboBoxSexoRecep.setEnabled(true);
				textFieldLogradouroRecep.setEnabled(true);
				textFieldCidadeRecep.setEnabled(true);
				textFieldEstadoRecep.setEnabled(true);
				textFieldBairroRecep.setEnabled(true);
				textFieldEmailRecep.setEnabled(true);
				textFieldMatricula.setEnabled(true);
				textFieldNascimentRecep.setEnabled(true);
				textFieldNumeroRecep.setEnabled(true);
				textFieldRgRecep.setEnabled(true);
				textFieldCpfRecep.setEnabled(true);
				textFieldCepRecep.setEnabled(true);
				textFieldTelefoneRecep.setEnabled(true);

				btnCancelar.setEnabled(true);
				btnSalvar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnNovo.setEnabled(true);
				

			}
		});
		buttonExcluirMouClick.setEnabled(false);

		buttonExcluirMouClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = 0;
				resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir?");
				if (resposta == JOptionPane.YES_NO_OPTION) {

					daoRecepcionista.excluir(Integer.parseInt(textFieldIdRecep.getText()));
					textFieldNomeRecep.setText("");
					textFieldLogradouroRecep.setText("");
					textFieldCidadeRecep.setText("");
					textFieldEstadoRecep.setText("");
					textFieldBairroRecep.setText("");
					textFieldEmailRecep.setText("");
					textFieldMatricula.setText("");
					textFieldNascimentRecep.setText("");
					textFieldRgRecep.setText("");
					textFieldCpfRecep.setText("");
					textFieldCepRecep.setText("");
					textFieldTelefoneRecep.setText("");
					
					
					textFieldNomeRecep.setEnabled(false);
					comboBoxSexoRecep.setEnabled(false);
					textFieldLogradouroRecep.setEnabled(false);
					textFieldCidadeRecep.setEnabled(false);
					textFieldEstadoRecep.setEnabled(false);
					textFieldBairroRecep.setEnabled(false);
					textFieldEmailRecep.setEnabled(false);
					textFieldMatricula.setEnabled(false);
					textFieldNascimentRecep.setEnabled(false);
					textFieldNumeroRecep.setEnabled(false);
					textFieldRgRecep.setEnabled(false);
					textFieldCpfRecep.setEnabled(false);
					textFieldCepRecep.setEnabled(false);
					textFieldTelefoneRecep.setEnabled(false);

									
					btnEditar.setEnabled(false);
					btnNovo.setEnabled(true);
					buttonExcluirMouClick.setEnabled(false);
					preencherTabela("select *from tab_recepcionista order by nome_recep");

				}
			}
		});
		buttonExcluirMouClick.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonExcluirMouClick.setVisible(true);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textFieldNomeRecep.setText("");
				textFieldLogradouroRecep.setText("");
				textFieldCidadeRecep.setText("");
				textFieldEstadoRecep.setText("");
				textFieldBairroRecep.setText("");
				textFieldEmailRecep.setText("");
				textFieldMatricula.setText("");
				textFieldNascimentRecep.setText("");
				textFieldNumeroRecep.setText("");
				textFieldRgRecep.setText("");
				textFieldCepRecep.setText("");
				textFieldCepRecep.setText("");
				textFieldTelefoneRecep.setText("");
				textFieldCpfRecep.setText("");
				textFieldNomeRecep.setEnabled(false);
				comboBoxSexoRecep.setEnabled(false);
				textFieldLogradouroRecep.setEnabled(false);
				textFieldCidadeRecep.setEnabled(false);
				textFieldEstadoRecep.setEnabled(false);
				textFieldBairroRecep.setEnabled(false);
				textFieldEmailRecep.setEnabled(false);
				textFieldMatricula.setEnabled(false);
				textFieldNascimentRecep.setEnabled(false);
				textFieldNumeroRecep.setEnabled(false);
				textFieldRgRecep.setEnabled(false);
				textFieldCpfRecep.setEnabled(false);
				textFieldCepRecep.setEnabled(false);
				textFieldTelefoneRecep.setEnabled(false);
				
				btnEditar.setEnabled(false);
				btnSalvar.setEnabled(false);
				btnNovo.setEnabled(true);
				
				
				
				preencherTabela("select *from tab_recepcionista order by nome_recep");

			}
		});

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPesquisa.setColumns(10);
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modRecepcionista.setPesquisaRecep(textFieldPesquisa.getText());
				BeanRecepcionista model = daoRecepcionista.pesquisarRecepcionista(modRecepcionista);
				textFieldIdRecep.setText(String.valueOf(model.getIdRecep()));
				
				

				comboBoxSexoRecep.setEditable(false);
				btnEditar.setEnabled(true);
				btnNovo.setEnabled(false);
				btnCancelar.setEnabled(true);
				textFieldPesquisa.setText("");
				preencherTabela("select *from tab_Recepcionista where nome_recep like'%"
						+ modRecepcionista.getPesquisaRecep() + "%'");

			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, 658, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(46)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addComponent(buttonExcluirMouClick, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addGap(2))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonExcluirMouClick, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);



		textFieldIdRecep.setColumns(10);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		
		//textFieldIdPaciente.setEditable(false);
		textFieldIdRecep.setVisible(false);

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
								.addComponent(textFieldIdRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
					.addComponent(textFieldIdRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
								.addComponent(textFieldLogradouroRecep, GroupLayout.PREFERRED_SIZE, 486,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(lblNmero, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(textFieldNumeroRecep,
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
														.addComponent(textFieldCepRecep, GroupLayout.PREFERRED_SIZE,
																227, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel_3
														.createSequentialGroup()
														.addComponent(
																lblCidade, GroupLayout.PREFERRED_SIZE, 58,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(textFieldCidadeRecep, GroupLayout.DEFAULT_SIZE,
																312, Short.MAX_VALUE)))
										.addGap(18)
										.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_3
												.createSequentialGroup()
												.addComponent(labelUFPaciente, GroupLayout.PREFERRED_SIZE, 33,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textFieldEstadoRecep, GroupLayout.PREFERRED_SIZE, 73,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblBairro, GroupLayout.PREFERRED_SIZE, 57,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textFieldBairroRecep, GroupLayout.PREFERRED_SIZE, 222,
														GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel_3.createSequentialGroup()
														.addComponent(lblTelefone, GroupLayout.PREFERRED_SIZE, 68,
																GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(textFieldTelefoneRecep,
																GroupLayout.PREFERRED_SIZE, 222,
																GroupLayout.PREFERRED_SIZE))))
						.addComponent(lblEndereo, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_3
				.createSequentialGroup().addComponent(lblEndereo).addGap(8)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldNumeroRecep, GroupLayout.PREFERRED_SIZE, 22,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLogradouro, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldLogradouroRecep, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNmero, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup().addGap(13)
								.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 17,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(
												textFieldCidadeRecep, GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_3.createSequentialGroup().addGap(11).addGroup(gl_panel_3
								.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldBairroRecep, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBairro, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldEstadoRecep, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelUFPaciente, GroupLayout.PREFERRED_SIZE, 17,
										GroupLayout.PREFERRED_SIZE))))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_3.createSequentialGroup()
						.addGap(11).addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldCepRecep, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCep, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGap(64).addComponent(label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup().addGap(9)
								.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldTelefoneRecep, GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTelefone, GroupLayout.PREFERRED_SIZE, 17,
												GroupLayout.PREFERRED_SIZE))
								.addGap(59)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_3.setLayout(gl_panel_3);

		JLabel lblCadastroDePacientes = new JLabel("Cadastro de Recepcionistas");
		panel_2.add(lblCadastroDePacientes);
		lblCadastroDePacientes.setFont(new Font("Tahoma", Font.BOLD, 21));
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.activeCaption));

		JLabel lblNome = new JLabel("Nome :");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldNomeRecep = new JTextField();
		textFieldNomeRecep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNomeRecep.setEnabled(false);
		textFieldNomeRecep.setColumns(10);

		JLabel lblSexoPaciente = new JLabel("Sexo :");
		lblSexoPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSexoRecep = new JComboBox<Object>(tipoUsu);
		comboBoxSexoRecep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSexoRecep.setEnabled(false);

		JLabel lblRg = new JLabel("RG :");
		lblRg.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldRgRecep = new JTextField();
		textFieldRgRecep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldRgRecep.setEnabled(false);
		textFieldRgRecep.setColumns(10);
		lblNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNascimentRecep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNascimentRecep.setEnabled(false);
		textFieldNascimentRecep.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail :");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldEmailRecep = new JTextField();
		textFieldEmailRecep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldEmailRecep.setEnabled(false);
		textFieldEmailRecep.setColumns(10);

		JLabel lblConvnio = new JLabel("Matricula :");
		lblConvnio.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldMatricula = new JTextField();
		textFieldMatricula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldMatricula.setEnabled(false);
		textFieldMatricula.setColumns(10);

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
																				textFieldNomeRecep,
																				GroupLayout.PREFERRED_SIZE, 458,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18).addComponent(lblSexoPaciente))
																.addGroup(gl_panel
																		.createSequentialGroup().addComponent(lblRg)
																		.addGap(24).addGroup(gl_panel
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(
																						textFieldEmailRecep,
																						GroupLayout.PREFERRED_SIZE, 396,
																						GroupLayout.PREFERRED_SIZE)
																				.addGroup(gl_panel
																						.createSequentialGroup()
																						.addComponent(
																								textFieldRgRecep,
																								GroupLayout.PREFERRED_SIZE,
																								127,
																								GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								ComponentPlacement.RELATED)
																						.addComponent(lblCpf)
																						.addPreferredGap(
																								ComponentPlacement.RELATED)
																						.addComponent(
																								textFieldCpfRecep,
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
												.addComponent(textFieldNascimentRecep, GroupLayout.PREFERRED_SIZE,
														190, GroupLayout.PREFERRED_SIZE)
												.addComponent(comboBoxSexoRecep, GroupLayout.PREFERRED_SIZE, 190,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(textFieldMatricula, GroupLayout.PREFERRED_SIZE, 190,
														GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addComponent(lblDadosPessoais).addGap(7)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNome)
						.addComponent(textFieldNomeRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSexoPaciente).addComponent(comboBoxSexoRecep, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblRg)
						.addComponent(textFieldNascimentRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldRgRecep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldCpfRecep, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNascimento))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblConvnio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldEmailRecep, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(textFieldMatricula, GroupLayout.PREFERRED_SIZE, 22,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		scrollPane.setViewportView(tableRecepcionistas);
		contentPane.setLayout(gl_contentPane);

	}
}

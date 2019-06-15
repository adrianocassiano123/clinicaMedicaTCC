package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import modeloDao.Parametros;

@SuppressWarnings("serial")
public class Pagamento extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldValorConsulta;
	private JTextField textFieldDesconto;
	private JTextField textFieldValorTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Pagamento frame = new Pagamento();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	//public Pagamento() {}
	
	public Pagamento(String valor) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		
		JLabel lblPagamento = new JLabel("Pagamento");
		lblPagamento.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblPagamento);
		
		textFieldValorConsulta = new JTextField();
		textFieldValorConsulta.setEnabled(false);
		textFieldValorConsulta.setColumns(10);
		
		textFieldDesconto = new JTextField();
		textFieldDesconto.setColumns(10);
		
		textFieldValorTotal = new JTextField();
		textFieldValorTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldValorTotal.setEnabled(false);
		textFieldValorTotal.setColumns(10);
		
		
//		try {
//			javax.swing.text.MaskFormatter desconto = new javax.swing.text.MaskFormatter("##,##"); // Mascara data
//			textFieldDesconto = new javax.swing.JFormattedTextField(desconto);
//			textFieldDesconto.setEnabled(true);
//		} catch (Exception e) {
//		}
//		
//		try {
//			javax.swing.text.MaskFormatter total = new javax.swing.text.MaskFormatter("##,##"); // Mascara data
//			textFieldValorTotal = new javax.swing.JFormattedTextField(total);
//			textFieldValorTotal.setEnabled(true);
//		} catch (Exception e) {
//		}
		
		JButton btnDescontar = new JButton("Dar Desconto");
		btnDescontar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldDesconto.setEnabled(true);
				
				BigDecimal valor = new BigDecimal(textFieldValorConsulta.getText());
				BigDecimal desconto = new BigDecimal(textFieldDesconto.getText());
			
				textFieldValorTotal.setText(valor.subtract(desconto).toString());
		
				
				
			}
		});
		
		
		
		System.out.println(valor);
		
		textFieldValorConsulta.setText(valor);
		textFieldValorTotal.setText(valor);
		
		
		
		btnDescontar.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel lblNewLabel = new JLabel("Desconto:");
		
		JLabel lblValorTotal = new JLabel("Valor Total:");
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Parametros parametro = new Parametros();
				//parametro.setParametro(textFieldValorTotal.getText());
				
				//System.out.println(parametro);
				
				FormMarcConsulta f = new FormMarcConsulta();
				
				//f.recebe(textFieldValorTotal.getText());
				
				dispose();
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 10));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textFieldValorTotal, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
									.addGap(145)
									.addComponent(btnConfirmar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textFieldDesconto, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnDescontar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel)
								.addComponent(lblValorTotal, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldValorConsulta, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(textFieldValorConsulta, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldDesconto, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDescontar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblValorTotal)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldValorTotal, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConfirmar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
	}

//	public Pagamento() {
//		// TODO Auto-generated constructor stub
//	}
}

package com.lanazirot.fibonacci.Ventanas;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.lanazirot.fibonacci.Excepciones.FibonacciException;
import com.lanazirot.fibonacci.Fibonacci.Fibonacci;
public class MainFibonacci extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumeroFinal;
	private DefaultListModel<Long> numerosFibo;
	private JTextField txtNumeroInicial;
	private JTextField txtIteraciones;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFibonacci frame = new MainFibonacci();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public MainFibonacci() {
		setResizable(false);
		setTitle("Serie Fibonacci GUI por lanazirot");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 278);
		setBackground(new Color(238, 238, 238));
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNumeroFinal = new JLabel("Numero final");
		lblNumeroFinal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroFinal.setBounds(38, 101, 115, 17);
		contentPane.add(lblNumeroFinal);
		
		JList<Long> listFibos = new JList<Long>();
		listFibos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFibos.setBounds(425, 75, 86, 82);
		
		JScrollPane scrollFibos = new JScrollPane(listFibos);
		scrollFibos.setBounds(310, 70, 115, 123);
		
		contentPane.add(scrollFibos);
		
		txtNumeroFinal = new JTextField();
		txtNumeroFinal.setColumns(10);
		txtNumeroFinal.setBounds(160, 101, 86, 20);
		contentPane.add(txtNumeroFinal);
		
		JButton btnGenerarSerieFibonacci = new JButton("Generar serie");
		btnGenerarSerieFibonacci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numerosFibo = new DefaultListModel<Long>();
				numerosFibo.clear();
				Fibonacci fibo = null;
				try {
					fibo = new Fibonacci(Integer.parseInt(txtNumeroInicial.getText()), Integer.parseInt(txtNumeroFinal.getText()), 
							Integer.parseInt(txtIteraciones.getText()));
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(MainFibonacci.this, "Formato incorrecto en algun campo. " 
							+ ex.getLocalizedMessage(), "Error :(", JOptionPane.ERROR_MESSAGE);
					return;
				} catch (FibonacciException ex) {
					JOptionPane.showMessageDialog(MainFibonacci.this, ex.getMessage(), "Error :(", JOptionPane.ERROR_MESSAGE);
					return;
				}	
				
				for(Long element : fibo) {
					numerosFibo.addElement(element);
				}
				listFibos.setModel(numerosFibo);
				limpiarCampos();
			}

			private void limpiarCampos() {
				for(Component c : getContentPane().getComponents()) {
					if(c instanceof JTextField) {
						((JTextField) c).setText("");
					}
				}
			}
		});
		btnGenerarSerieFibonacci.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGenerarSerieFibonacci.setBounds(145, 170, 132, 23);
		contentPane.add(btnGenerarSerieFibonacci);
		
		txtNumeroInicial = new JTextField();
		txtNumeroInicial.setBounds(160, 70, 86, 20);
		contentPane.add(txtNumeroInicial);
		txtNumeroInicial.setColumns(10);
		
		
		JLabel lblNumeroInicial = new JLabel("Numero inicial");
		lblNumeroInicial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroInicial.setBounds(38, 74, 115, 17);
		contentPane.add(lblNumeroInicial);
		
		JLabel lblIteraciones = new JLabel("Iteraciones");
		lblIteraciones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIteraciones.setBounds(38, 136, 115, 17);
		contentPane.add(lblIteraciones);
		
		txtIteraciones = new JTextField();
		txtIteraciones.setColumns(10);
		txtIteraciones.setBounds(160, 133, 86, 20);
		contentPane.add(txtIteraciones);
		
	}
}

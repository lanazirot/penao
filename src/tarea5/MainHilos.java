package tarea5;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import tarea5.threads.ComponentThread;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
public class MainHilos extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCorrer;
	private JProgressBar progressUno;
	private JProgressBar progressDos;
	private ComponentThread barraUno,barraDos;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainHilos frame = new MainHilos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainHilos() {
		setResizable(false);
		setTitle("Carrera de JProgressBar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 663, 338);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnCorrer = new JButton("Correr");
		btnCorrer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCorrer.addActionListener(this);
		btnCorrer.setBounds(250, 276, 162, 51);
		panel.add(btnCorrer);
		
		progressUno = new JProgressBar();
		progressUno.setBounds(122, 50, 460, 51);
		panel.add(progressUno);
		
		progressDos = new JProgressBar();
		progressDos.setBounds(122, 114, 460, 51);
		panel.add(progressDos);
		
		JLabel lblNewLabel = new JLabel("Progress uno");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(17, 72, 95, 14);
		panel.add(lblNewLabel);
		
		JLabel lblProgressDos = new JLabel("Progress dos");
		lblProgressDos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProgressDos.setBounds(17, 136, 95, 14);
		panel.add(lblProgressDos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCorrer) {
						
			 barraUno = new ComponentThread(progressUno,"Uno");
			 barraDos = new ComponentThread(progressDos, "Dos");			
			
			
			barraUno.start();
			barraDos.start();
			 
			 
		}
	}
	
	
	
}

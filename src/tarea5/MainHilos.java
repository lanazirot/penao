package tarea5;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import tarea5.threads.JProgressThread;
import tarea5.threads.RunningJProgressThread;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
public class MainHilos extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCorrer;
	private JProgressBar progressUno;
	private JProgressBar progressDos;
	private JProgressThread barraUno,barraDos;

	
	private RunningJProgressThread<JProgressThread> compOne, compTwo;
	private JButton btnMatarHilos;

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
		progressUno.setBounds(122, 50, 531, 51);
		panel.add(progressUno);

		progressDos = new JProgressBar();
		progressDos.setBounds(122, 114, 531, 51);
		panel.add(progressDos);

		JLabel lblNewLabel = new JLabel("Tortuga");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(17, 72, 95, 14);
		panel.add(lblNewLabel);

		JLabel lblProgressDos = new JLabel("Liebre");
		lblProgressDos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProgressDos.setBounds(17, 136, 95, 14);
		panel.add(lblProgressDos);

		btnMatarHilos = new JButton("Matar hilos");
		btnMatarHilos.addActionListener(this);
		btnMatarHilos.setBounds(517, 276, 136, 51);
		panel.add(btnMatarHilos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCorrer) {
			RunningJProgressThread.setAllRunning(true);
			//Creates new bar
			barraUno = new JProgressThread(progressUno,"Tortuga");
			barraDos = new JProgressThread(progressDos, "Liebre");	

			//Instance 2 RJPT objects to manipulate b1 and b2
			compOne = new RunningJProgressThread<JProgressThread>(barraUno);
			compTwo =  new RunningJProgressThread<JProgressThread>(barraDos);

			//Logic
			Thread running = new Thread() {
				@Override
				public void run() {
					compOne.start();
					compTwo.start();
					while(isAlive() && RunningJProgressThread.areAllRunning()) {
						btnCorrer.setEnabled(false);
						if(compOne.winner()) {
							JOptionPane.showMessageDialog(MainHilos.this, "La "+compOne.getNombre() + " le gano a la "+
									compTwo.getNombre());
							break;
						}else if(compTwo.winner()) {
							JOptionPane.showMessageDialog(MainHilos.this, "La "+compTwo.getNombre() + " le gano a la "+
									compOne.getNombre());
							break;
						}


					}
					btnCorrer.setEnabled(true);
					return;
				}
			};	 	
			running.start();

		}else if(e.getSource() == btnMatarHilos) {
				RunningJProgressThread.setAllRunning(false);
				compOne.kill();
				compTwo.kill();
				btnCorrer.setEnabled(true);

		}
	}
}

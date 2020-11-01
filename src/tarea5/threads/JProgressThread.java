package tarea5.threads;
import java.util.Random;
import javax.swing.JProgressBar;
public class JProgressThread extends JProgressBar implements Runnable , IProgressRunableMethods, Killable{
	private static final long serialVersionUID = 1L;
	private JProgressBar component;
	private Random rand;
	private volatile boolean estaCompletado = false;
	private Random getRand() {
		return rand;
	}
	
	private String nombre;
	
	@Override
	public String getNombre() {
		return nombre;
	}
	
	public JProgressThread(JProgressBar component, String nombre) {
		this.component = component;
		rand = new Random();
		this.component.setName(nombre);
		this.component.setMaximum(100);
		this.nombre = nombre;
		this.component.setStringPainted(true);
	}
	
	public  boolean isCompleted() {
		return estaCompletado;
	}
	
	@Override
	public void kill() {
		estaCompletado = true;
	}
	
	@Override
	public void run() {
		int intPorciento = 0;
		while(!estaCompletado) {
			if(component.getValue() >= 100) {
				kill();
			}
			component.setValue(intPorciento);
			intPorciento += getRand().nextInt(2);
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		component.setValue(0);
	}
	
}

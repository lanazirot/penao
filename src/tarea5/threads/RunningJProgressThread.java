package tarea5.threads;

import javax.swing.JProgressBar;

public class RunningJProgressThread<T extends JProgressBar & Runnable & IProgressRunableMethods & Killable> extends Thread {

	private static volatile boolean allRunning = true;
	
	public static boolean areAllRunning() {
		return allRunning;
	}
	
	public static void setAllRunning(boolean areRunning) {
		allRunning = areRunning;
	}
	
	private T jprogress;

	public T getJprogress() {
		return jprogress;
	}

	public RunningJProgressThread(T jprogress) {
		this.jprogress = jprogress;
	}

	public String getNombre() {
		return jprogress.getNombre();
	}

	

	// J1
	public synchronized boolean winner() {
		return isCompleted;
	}

	private volatile boolean isRunning = true;
	private volatile boolean isCompleted = false;
	
	public void kill() {
		isRunning = false;
		jprogress.kill();
	}
	
	public static void killAll() {
		allRunning = false;
	}

	public boolean isTerminated() {
		return isRunning;
	}

	@Override
	public void run() {
		jprogress.run();
		while(isRunning && allRunning) {
		 if(jprogress.isCompleted()) {
			isCompleted=true;
			break;
		  }
		}
	}

}

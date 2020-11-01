package tarea5.threads;

public interface IProgressRunableMethods {
	public  boolean isCompleted();
	public default String  getNombre() {
		return "Desconocido";
	}
}

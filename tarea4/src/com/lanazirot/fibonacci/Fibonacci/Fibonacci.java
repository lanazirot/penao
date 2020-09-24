package com.lanazirot.fibonacci.Fibonacci;

import java.util.Iterator;

import com.lanazirot.fibonacci.Excepciones.FibonacciException;

public final class Fibonacci implements Iterable<Long> {


	private long numeroInicial;
	private long numeroFinal;
	private long iteraciones;
	
	
	public Fibonacci(long numeroInicial, long numeroFinal, long iteraciones) throws FibonacciException {
		setNumeroFinal(numeroFinal);
		setNumeroInicial(numeroInicial);
		setIteraciones(iteraciones);
	}
	
	public long getNumeroFinal() {
		return numeroFinal;
	}
	
	public long getNumeroInicial() {
		return numeroInicial;
	}
	public void setIteraciones(long iteraciones) throws FibonacciException {
		if(iteraciones <= 0)
			throw new FibonacciException("Las iteraciones deben ser mayores que cero");
		this.iteraciones = iteraciones;
	}
	public long getIteraciones() {
		return iteraciones;
	}
	
	public void setNumeroInicial(long numeroInicial) throws FibonacciException {
		if(numeroInicial < 0)
			throw new FibonacciException("El valor del numero inicial debe ser mayor o igual a cero");
		this.numeroInicial = numeroInicial;
	}
	public void setNumeroFinal(long numeroFinal) throws FibonacciException {
		if(numeroFinal < 0)
			throw new FibonacciException("El valor del numero final debe ser mayor o igual a cero");
		this.numeroFinal = numeroFinal;
	}

	@Override
	public Iterator<Long> iterator() {
		return new Iterator<Long>() {		
			long ctdIteraciones = 0;
			long inicial = getNumeroInicial();
			long fin = getNumeroFinal();	
			@Override
			public Long next() {
				ctdIteraciones++;		
				final long fibo = inicial+fin;
				inicial = fin;
				fin = fibo;
				return fibo;
			}	
			@Override
			public boolean hasNext() {	
				return ctdIteraciones<iteraciones;
			}
		};
	}
}

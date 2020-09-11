public class Fibonacci{ 
    public static void main(String[] args) {
            
            int fib =0, aux=1, i=0;
            while(i++<=18){
                System.out.println(fib);
                fib+=aux;
                aux = fib - aux;
            }
    }
}
public class Fibonacci{ 

    public static long fib(int n){
        if(n<=0)return 0;
        if(n==1)return 1;
        return fib(n-1)+fib(n-2);
    }

    public static void main(String[] args) {
        
            
            System.out.println("Recursivo fib(18) = "+fib(18));

            int fib =0;
            int aux =1;
            System.out.println("Ciclico:");
            for (int i = 0; i < 18; i++) {
                fib+=aux;
                System.out.println(fib);
                aux = fib - aux;
            }

    }

}
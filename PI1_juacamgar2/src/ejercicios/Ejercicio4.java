package ejercicios;
import java.util.stream.Stream;

import util.Trio;





public class Ejercicio4 {
	//EJERCICIO 4 ITERATIVO
	public static Double ejercicio4Iterativo(Double n, Double e) {
		Double i = 0.0;
		Double b = n;
		Double error = Math.abs(Math.pow((i+b)/2,3)-n);
		Double limite = 0.0;
		while(error>e ) {
			limite = (i+b)/2;
			error = Math.abs(Math.pow((i+b)/2,3)-n);
			if(n>=Math.pow(limite ,3)) {
				i = limite;
			}else {
				b = limite;
			}
		}
		return  limite;
	}
	
	//EJERCICIO 4 RECURSIVO
	public static Double ejercicio4Recursivo(Double n, Double e) {
		Double i = 0.0;
		Double b = n;
		Double error = Math.abs(Math.pow((i+b)/2, 3) -n);
		Double limite = 0.0;
		limite = ejercicio4Recursivo(n,e,error,i,b,limite);
	return limite;	
	}
	private static Double ejercicio4Recursivo(Double n, Double e, Double error, Double i, Double b,Double limite) {
		if(error>e) {
			limite = (i+b)/2;
			error = Math.abs(Math.pow((i+b)/2,3)-n);
			if(n>=Math.pow(limite, 3)) {
				i = limite;
				limite = ejercicio4Recursivo(n,e,error,i,b,limite);
			}else {
				b = limite;
				limite = ejercicio4Recursivo(n,e,error,i,b,limite);
			}
		}
		return limite;
	}
	
	//EJERCICIO 4 FUNCIONAL


	public static Double ejercicio4Funcional(Double n, Double e) {
		Double i = 0.0;
		Double b = n;
		Double limite = (i+b)/2;
		Trio res=  Stream.iterate(Trio.of(i, b, limite),
				t-> e<Math.abs(Math.pow(t.k(), 3)-n),
				t-> Trio.of(n>Math.pow(t.k(), 3)? t.k(): t.i() ,
				n>Math.pow(t.k(), 3)? t.j():t.k(),
						(t.i()+t.j())/2))
				 .dropWhile(t-> e<Math.abs(Math.pow((t.i()+t.j())/2, 3)-n))
				 .findFirst()
				 .get();;

		return (res.i()+res.j())/2;
	}


}

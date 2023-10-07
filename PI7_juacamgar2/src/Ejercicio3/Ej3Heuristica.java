package Ejercicio3;


import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Ej3Heuristica {

	public static Double heuristica(Ej3Vertex v1,Predicate<Ej3Vertex>goal,Ej3Vertex v2) {
		Double r = 0.;
		Ej3Vertex v = v1;
		while(v.i()<DatosEj3.getN()) {
			Double a = v.heuristicAction();
			r+=a*DatosEj3.getValor(v.i());
			v = v.neighbor(a.intValue());
		}
		return r;
	}
	public static Double heristic(Ejercicio3Problem v1) {
		Integer n = DatosEj3.getN();
		Double res = 0.;
		if(v1.i()>=n) {
			return res;
		}else {
			res = IntStream.range(v1.i(), n)
					.boxed()
					.mapToDouble(x->DatosEj3.productos.get(x).precio() * v1.calculaUMax(x))
					.sum();
			return res;
		}
	}
	public static Double cota2(Ejercicio3Problem v1,Integer a) {
		Ejercicio3Problem v2 = v1.vecino(a);
		return heristic(v2) + a*DatosEj3.getValor(v1.i());
	}
	
}

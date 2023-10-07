package Ejercicio1;

import java.util.function.Predicate;


public class Ej1Heuristica2 {
	public static Double heuristic(Ej1Vertex2 v1,Predicate<Ej1Vertex2> goal, Ej1Vertex2 v2) {
		Double res;
		Integer n = DatosEj1.getN();
		if(v1.i() == n) {
			res = 0.;
		}else {
			res = n-v1.i().doubleValue();
		}
		return res;
	}
	public static Double heuristica(Ejercicio1Problem v1) {
		Double res;
		Integer n = DatosEj1.getN();
		if(v1.i()==n) {
			return 0.;
		}else {
			res = n -v1.i().doubleValue();
		}
		return res;
	}
	public static Double cota(Ejercicio1Problem v1,Integer a) {
		Ejercicio1Problem v2 = v1.vecino(a);
		return a<DatosEj1.getM()?1:0 + heuristica(v2);
	}
}

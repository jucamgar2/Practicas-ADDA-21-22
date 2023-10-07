package Ejercicio1;

import java.util.function.Predicate;

public class Ej1Heuristica2 {
	/*
	public static Double heuristic(Ej1Vertex2 v1,Predicate<Ej1Vertex2> goal, Ej1Vertex2 v2) {
		int n = DatosEj1.getN();
		if(v1.i() == n-1) {
			return 1.0;
		}else {
			Double valor = 0.;
			for(Integer item = v1.i();item<n;item++) {
				valor += 1;
			}
			return valor;
		}
	}
	*/
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
}

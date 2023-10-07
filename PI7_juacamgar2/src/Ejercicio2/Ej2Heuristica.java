package Ejercicio2;

import java.util.function.Predicate;


public class Ej2Heuristica {
	public static Double heuristic(Ej2Vertex v1,Predicate<Ej2Vertex> goal,Ej2Vertex v2) {
		int n = DatosEj2.getN();
		if(v1.i() == n-1) {
			if(v1.sueldoRestante()>= DatosEj2.getSu(v1.i())) {
				return DatosEj2.getVal(v1.i()).doubleValue();
			}else {
				return 0.;
			}
		}else {
			Double mayorPeso = 0.;
			for(Integer item = v1.i();item<n;item++) {
				Double valor = DatosEj2.getVal(item).doubleValue();
				mayorPeso+=valor;
			}
			return mayorPeso;
		}
	}
	public static Double heuristica(Ejercicio2Problem v1) {
		Double res = 0.;
		Integer n = DatosEj2.getN();
		if(v1.cualidades().isEmpty()) {
			return res;
		}else {
			for(int i = v1.i();i<n;i++){
				Integer v = DatosEj2.candidatos.get(i).valoracion();
				res += v;
			}
			return res;
		}
	}
	public static Double cota2(Ejercicio2Problem v1,Integer a) {
		Ejercicio2Problem v2 = v1.vecino(a);
		return heuristica(v2) + a*DatosEj2.getVal(v1.i());
	}
}

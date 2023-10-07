package Ejercicio4;

import java.util.function.Predicate;

public class Ej4Heuristica {

	public static Double heuristic(Ej4Vertex2 v1,Predicate<Ej4Vertex2> goal, Ej4Vertex2 v2) {
		int n = DatosEj4.getN();
		if(v1.i() == n-1) {
			return 1.0;
		}else {
			return DatosEj4.getM().doubleValue();
		}
	}
	public static Double heuristica(Ej4Vertex2 v1,Predicate<Ej4Vertex2> goal,Ej4Vertex2 v2) {
		Double r = 0.;
		Ej4Vertex2 v = v1;
		while(v.i()<DatosEj4.getN()) {
			Double a = v.heuristicAction();
			r += v1.numContLlenos().doubleValue();
			v = v.neighbor(a.intValue());
		}
		return r;
	}
	public static Double heuristic(Ejercicio4Problem v1) {
		return v1.numContLlenos() * 1.0;
	}
	public static Double cota2(Ejercicio4Problem v1,Integer a) {
		Ejercicio4Problem v2 = v1.vecion(a);
		return heuristic(v2);
	}
}

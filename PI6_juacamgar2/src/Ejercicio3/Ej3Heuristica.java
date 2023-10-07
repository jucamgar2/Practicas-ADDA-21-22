package Ejercicio3;

import java.util.function.Predicate;


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

}

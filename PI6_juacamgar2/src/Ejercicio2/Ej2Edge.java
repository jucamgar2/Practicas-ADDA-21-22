package Ejercicio2;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Ej2Edge(Ej2Vertex source,Ej2Vertex target, Integer action,Double weight) 
	implements SimpleEdgeAction<Ej2Vertex, Integer>{

	public static Ej2Edge of(Ej2Vertex v1,Ej2Vertex v2,Integer a) {
		Double w = Double.valueOf(DatosEj2.getVal(v1.i()) * a);
		return new Ej2Edge(v1,v2,a,w);
	}
}

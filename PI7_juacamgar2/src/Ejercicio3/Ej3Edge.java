package Ejercicio3;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Ej3Edge(Ej3Vertex source,Ej3Vertex target,Integer action,Double weight) 
	implements SimpleEdgeAction<Ej3Vertex,Integer>{
	
	public static Ej3Edge of(Ej3Vertex v1,Ej3Vertex v2,Integer a) {
		Double w = Double.valueOf(DatosEj3.getValor(v1.i()) * a);
		return new Ej3Edge(v1,v2,a,w);
	}
}

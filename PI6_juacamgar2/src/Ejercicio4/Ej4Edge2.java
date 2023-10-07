package Ejercicio4;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Ej4Edge2(Ej4Vertex2 source,Ej4Vertex2 target,Integer action,Double weight) 
	implements SimpleEdgeAction<Ej4Vertex2,Integer>{

	public static Ej4Edge2 of(Ej4Vertex2 v1,Ej4Vertex2 v2,Integer a) {
		Double w = Double.valueOf(v2.numContLlenos() - v1.numContLlenos());
		return new Ej4Edge2(v1,v2,a,w);
	}
}

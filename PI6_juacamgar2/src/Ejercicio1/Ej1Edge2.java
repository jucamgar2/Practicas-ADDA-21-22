package Ejercicio1;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Ej1Edge2(Ej1Vertex2 source,Ej1Vertex2 target,Integer action,Double weight)
	implements SimpleEdgeAction<Ej1Vertex2, Integer>{
	
	public static Ej1Edge2 of(Ej1Vertex2 v1, Ej1Vertex2 v2, Integer a) {
		Double w = a<DatosEj1.getM()? 1.:0.;
		return new Ej1Edge2(v1,v2,a,w);
	}

	
}

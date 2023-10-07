package Ejercicio3;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import org.jgrapht.GraphPath;
import Soluciones.SolucionEj3;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record Ej3Vertex(Integer i,Integer TPRest,Integer TERest) 
	implements VirtualVertex<Ej3Vertex, Ej3Edge, Integer>{
	
	public static Ej3Vertex initialVertex() {
		return of(0,DatosEj3.getTP(),DatosEj3.getTM());
	}
	public static Ej3Vertex of(Integer i,Integer a,Integer b) {
		return new Ej3Vertex(i,a,b);
	}
	public static Predicate<Ej3Vertex> goal(){
		return v->v.i() ==DatosEj3.getN();
	}
	public static Predicate<Ej3Vertex> constraint(){
		return v-> v.TERest>=0 && v.TPRest>=0;
	}
	public String toGraph() {
		return String.format("(%d)", this.i);
	}
	public String toString() {
		return String.format("(%d)", this.i);
	}

	public static SolucionEj3 getSolucion(GraphPath<Ej3Vertex,Ej3Edge> path) {
		return Ej3Vertex.getSolucion(path.getEdgeList());
	}
	public static SolucionEj3 getSolucion(List<Ej3Edge> ls) {
		SolucionEj3 s= SolucionEj3.empty();
		ls.stream().forEach(e->s.add(DatosEj3.productos.get(e.source().i),e.action().intValue(),e.weight()));
		return s;
	}

	public Double heuristicAction() {
		Integer res = 0;
		res = IntStream.range(0, DatosEj3.getMax(i))
				.filter(x->x*DatosEj3.calculaTiempoE(i)<=TERest && x*DatosEj3.calculaTiempoP(i)<=TPRest)
				.boxed()
				.max(Comparator.comparing(Integer::intValue))
				.orElse(0);
		return res+1.;
	}
	@Override
	public List<Integer> actions() {
		List<Integer> res = List2.empty();
		if(i == DatosEj3.getN()) {
			
		}else {
			res = IntStream.range(0, DatosEj3.getMax(i)+1)
					.filter(x->x*DatosEj3.calculaTiempoE(i)<=TERest && x*DatosEj3.calculaTiempoP(i)<=TPRest)
					.boxed()
					.toList();
		}
		return res;
	}

	@Override
	public Ej3Vertex neighbor(Integer a) {
		Double tiempoEc = DatosEj3.calculaTiempoE(i) *a;
		Double tiempoPc = DatosEj3.calculaTiempoP(i) *a;
		return Ej3Vertex.of(i+1,TPRest-tiempoPc.intValue(),TERest -tiempoEc.intValue());
	}

	@Override
	public Ej3Edge edge(Integer a) {
		Ej3Vertex v = this.neighbor(a);
		return Ej3Edge.of(this, v, a);
	}
}

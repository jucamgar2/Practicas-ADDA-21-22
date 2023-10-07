package Ejercicio2;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.jgrapht.GraphPath;
import Soluciones.SolucionEj2;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record Ej2Vertex(Integer i,Set<Integer> cE)
	implements VirtualVertex<Ej2Vertex, Ej2Edge, Integer>{
	
	public static Ej2Vertex initialVertex() {
		return of(0,new HashSet<>());
	}
	public static Ej2Vertex of(Integer i,Set<Integer> cE) {
		return new Ej2Vertex(i,cE);
	}
	public static Predicate<Ej2Vertex> goal(){
		return v->v.i ==DatosEj2.getN()  && v.cualidades().containsAll(DatosEj2.cualidadesDeseadas);
	}
	public static Predicate<Ej2Vertex> constraint(){
		//return v-> v.cualidades().containsAll(DatosEj2.cualidadesDeseadas) && v.sueldoRestante()>=0;
		return v->v.sueldoRestante()>=0;
	//	return v->v.cualidades().containsAll(DatosEj2.cualidadesDeseadas);
	}
	public String toGraph() {
		return String.format("(%d)", this.i);
	}
	public String toString() {
		return String.format("(%d)", this.i);
	}
//	public Boolean isValid() {
//		return this.i>=0&& this.i<= DatosEj2.getN() && this.sueldoRestante()>=0;
//	}
	public static SolucionEj2 getSolucion(GraphPath<Ej2Vertex,Ej2Edge> path) {
		return Ej2Vertex.getSolucion(path.getEdgeList());
	}
	public static SolucionEj2 getSolucion(List<Ej2Edge> ls) {
		SolucionEj2 s = SolucionEj2.empty();
		ls.stream().forEach(e->s.add(DatosEj2.candidatos.get(e.source().i),e.action().intValue(),e.weight()));
		return s;
	}

//	public Ej2Edge greedyEdge() {
//		return edge(greedyAction());
//	}
//	public Integer greedyAction() {
//		Integer res = 0;
//		if(DatosEj2.sePuedeEscoger(i, cE) && DatosEj2.getSu(i)<sueldoRestante()) {
//			res = 1;
//		}
//		return res;
//	}
//	public Double heuristicAction() {
//		Integer res = 0;
//		if(DatosEj2.sePuedeEscoger(i, cE)) {
//			res =1;
//		}
//		return res.doubleValue();
//	}
	public Double sueldoRestante() {
		List<Integer> aux = new ArrayList<>(cE);
		Double gastado = aux.stream().mapToDouble(x->DatosEj2.getSu(x)).sum();
		return DatosEj2.presupuesto - gastado;
	}
	public Set<String> cualidades(){
		List<Integer> aux = new ArrayList<>(cE);
		Set<String> res = aux.stream().map(x->DatosEj2.candidatos.get(x).cualidades()).flatMap(x->x.stream()).collect(Collectors.toSet());
		return res;
	}
	@Override
	public List<Integer> actions() {
		List<Integer> res = List2.empty();
		if(i == DatosEj2.getN()){

		}else if(DatosEj2.getSu(i)<sueldoRestante() && DatosEj2.sePuedeEscoger(i, cE)) {
			res.add(0);
			res.add(1);
		}else if(i==DatosEj2.getN()-1) {
			if(!cualidades().containsAll(DatosEj2.cualidadesDeseadas)) res.add(1);
			else res.add(0);
		}
		return res;
	}
	@Override
	public Ej2Vertex neighbor(Integer a) {
		Ej2Vertex r;
		if(a==1) {
			Set<Integer> aux = new HashSet<>(cE);
			aux.add(i);
			r = Ej2Vertex.of(i+1, aux);
		}else {
			r = Ej2Vertex.of(i+1, new HashSet<>(cE));
		}
		return r;
	}

	@Override
	public Ej2Edge edge(Integer a) {
		Ej2Vertex v = this.neighbor(a);
		return Ej2Edge.of(this, v, a);
	}
}

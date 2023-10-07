package Ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.jgrapht.GraphPath;
import Soluciones.SolucionEj1;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record Ej1Vertex2(Integer i,List<Integer> cR) 
	implements VirtualVertex<Ej1Vertex2, Ej1Edge2, Integer>{
	
	public static Ej1Vertex2 of(Integer i,List<Integer> cR) {
		return new Ej1Vertex2(i,cR);
	}
	
	public static Ej1Vertex2 initialVertex() {
		return of(0,DatosEj1.memorias.stream().map(x->x.capacidad()).toList());
	}
	public static Predicate<Ej1Vertex2> goal(){
		return v->v.i() == DatosEj1.getN();
	}
	public static Predicate<Ej1Vertex2> constraint(){
		return v->v.cR().stream().allMatch(x->x>=0);
	}
	public static SolucionEj1 getSolucion(GraphPath<Ej1Vertex2,Ej1Edge2> path) {
		return Ej1Vertex2.getSolucion(path.getEdgeList());
	}
	public static SolucionEj1 getSolucion(List<Ej1Edge2> ls) {
		SolucionEj1 s = SolucionEj1.empty();
		ls.stream().forEach(e->s.add(DatosEj1.archivos.get(e.source().i()), e.action().intValue(),e.weight()));
		return s;
	}

	@Override
	public List<Integer> actions() {
		if(i == DatosEj1.getN()) {
			return new ArrayList<>();
		}else {
			List<Integer> alt = List2.empty();
			for(int b = 0;b<DatosEj1.getM();b++) {
				if(DatosEj1.getTam(this.i()) < cR.get(b) && DatosEj1.getTam(this.i())<=DatosEj1.tamMax(b)) {
					alt.add(b);
				}
			}
			if(alt.isEmpty()) {
				alt.add(DatosEj1.getM());
			}
			return alt;
		}
	}

	@Override
	public Ej1Vertex2 neighbor(Integer a) {
		Ej1Vertex2 r;
		if(a<DatosEj1.getM()) {
			Integer cp = this.cR().get(a);
			Integer capRest = cp - DatosEj1.getTam(this.i());
			List<Integer> aux = new ArrayList<>(this.cR());
			aux.set(a, capRest);
			r = Ej1Vertex2.of(this.i()+1, aux);
		}else {
			r = Ej1Vertex2.of(this.i()+1,new ArrayList<>(this.cR()));
		}
		return r;
	}

	@Override
	public Ej1Edge2 edge(Integer a) {
		Ej1Vertex2 v = this.neighbor(a);
		return Ej1Edge2.of(this, v, a);
	}
	
}

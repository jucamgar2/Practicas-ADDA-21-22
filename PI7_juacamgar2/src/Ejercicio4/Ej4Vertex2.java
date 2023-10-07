package Ejercicio4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import org.jgrapht.GraphPath;
import Soluciones.SolucionEj4;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record Ej4Vertex2(Integer i,List<Integer> cR)
	implements VirtualVertex<Ej4Vertex2,Ej4Edge2,Integer>{
	
	public static List<Integer> capacidadInicial;
	
	public static Ej4Vertex2 of(Integer i,List<Integer> cR) {
		return new Ej4Vertex2(i,cR);
	}
	public static Ej4Vertex2 initialVertex() {
		List<Integer> ls = DatosEj4.contenedores.stream().mapToInt(x->x.capacidad()).boxed().toList();
		capacidadInicial = ls;
		return of(0,ls);
	}
	public static Predicate<Ej4Vertex2> goal(){
		return v->v.i() == DatosEj4.getN();
	}
	public static Predicate<Ej4Vertex2> constraint(){
		return x-> IntStream.range(0, DatosEj4.getM())
				.boxed()
				.allMatch(c->x.cR.get(c) == 0|| x.cR.get(c) == capacidadInicial.get(c) );
	}
	public static SolucionEj4 getSolucion(GraphPath<Ej4Vertex2,Ej4Edge2> path) {
		return Ej4Vertex2.getSolucion(path.getEdgeList());
	}
	public static SolucionEj4 getSolucion(List<Ej4Edge2> ls) {
		SolucionEj4 s = SolucionEj4.empty();
		ls.stream().forEach(e->s.add(DatosEj4.elementos.get(e.source().i()), e.action().intValue(),e.weight()));
		return s;
	}
	public Integer numContLlenos() {
		Long res =  cR.stream().filter(x->x<1).count();
		return res.intValue();
	}
	public Double heuristicAction() {
		List<Integer> aux = new ArrayList<>(cR);
		Integer x = aux.stream().filter(r->DatosEj4.elementos.get(this.i).tamaño()<=r ).min(Comparator.comparing(r->DatosEj4.elementos.get(this.i).tamaño()-r)).orElse(0);
		Double y=  Double.valueOf( this.cR().indexOf(x));	
		if(y>=0.0) {
			return y;
		}else {
			return DatosEj4.getM().doubleValue();
		}
	}
	@Override
	public List<Integer> actions() {
		List<Integer> actions = List2.empty();
		if(i>=DatosEj4.getN()) {
			return actions;
		}
		List<Integer> contenedores = IntStream.rangeClosed(0, DatosEj4.getM()).boxed().toList();
		for(int a :contenedores) {
			if(a<DatosEj4.getM()
					&& DatosEj4.elementos.get(i).tamaño() <= cR.get(a)
					&& DatosEj4.elementos.get(i).tipos().contains(DatosEj4.contenedores.get(a).tipo())) {
				actions.add(a);
			}else if(a == DatosEj4.getM()) {
				actions.add(a);
			}
		}
		return actions;
	}

	@Override
	public Ej4Vertex2 neighbor(Integer a) {
		if(a<DatosEj4.getM()) {
			List<Integer> aux = new ArrayList<>(this.cR());
			Integer capR = aux.get(a) - DatosEj4.getPeso(this.i());
			aux.set(a, capR);
			return Ej4Vertex2.of(this.i()+1, aux);
		}else {
			return Ej4Vertex2.of(this.i()+1, new ArrayList<>(this.cR));
		}
	}

	@Override
	public Ej4Edge2 edge(Integer a) {
		Ej4Vertex2 v = this.neighbor(a);
		return Ej4Edge2.of(this,v,a);
	}
	public static void main(String[] args) {
		DatosEj4.iniDatos("././ficheros/PI6Ej4DatosEntrada1.txt");
		System.out.println(Ej4Vertex2.initialVertex().heuristicAction());
	}

}

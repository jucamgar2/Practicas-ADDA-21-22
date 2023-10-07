package Ejercicio4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import us.lsi.common.List2;
import us.lsi.common.Preconditions;

public record Ejercicio4Problem(Integer i,List<Integer> cR) {
	
	public static Ejercicio4Problem of(Integer i,List<Integer> cR) {
		Preconditions.checkArgument(i>=0&&i<=DatosEj4.getN(),"El indice es incorrecto");
		return new Ejercicio4Problem(i,cR);
	}
	
	public Ejercicio4Problem vecion(Integer a) {
		if(a<DatosEj4.getM()) {
			List<Integer> aux = new ArrayList<>(this.cR());
			Integer capR = aux.get(a) - DatosEj4.getPeso(this.i());
			aux.set(a, capR);
			return Ejercicio4Problem.of(this.i()+1, aux);
		}else {
			return Ejercicio4Problem.of(this.i()+1, new ArrayList<>(this.cR));
		}
	}
	public List<Integer> acciones(){
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
	public Double greedyAction() {
		List<Integer> aux = new ArrayList<>(cR);
		Integer x = aux.stream()
				.filter(r->DatosEj4.elementos.get(this.i).tipos().contains(DatosEj4.contenedores.get(aux.indexOf(r)).tipo()))
				.filter(r->DatosEj4.elementos.get(this.i).tamaño()<=r ).min(Comparator.comparing(r->DatosEj4.elementos.get(this.i).tamaño()-r)).orElse(0);
		Double y=  Double.valueOf( this.cR().indexOf(x));	
		if(y>=0.0) {
			return y;
		}else {
			return DatosEj4.getM().doubleValue();
		}
	}
}

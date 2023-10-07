package Ejercicio2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import us.lsi.common.List2;
import us.lsi.common.Preconditions;

public record Ejercicio2Problem(Integer i,Set<Integer> cE) {
	public static Ejercicio2Problem of(Integer i,Set<Integer> cE) {
		Preconditions.checkArgument(i>=0 && i<=DatosEj2.getN(),"El indice es incorrecto");
		return new Ejercicio2Problem(i, cE);
	}
	public Ejercicio2Problem vecino(Integer a) {
		Ejercicio2Problem r;
		if(a==1) {
			Set<Integer> aux = new HashSet<>(cE);
			aux.add(i);
			r = Ejercicio2Problem.of(i+1, aux);
		}else {
			r = Ejercicio2Problem.of(i+1, new HashSet<>(cE));
		}
		return r;
	}
	public List<Integer> acciones(){
		
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

	public Integer greedyAction() {
		Integer res = 0;
		if(DatosEj2.sePuedeEscoger(i, cE) && DatosEj2.getSu(i)<sueldoRestante()) {
			res = 1;
		}
		return res;
	}
	

}

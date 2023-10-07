package Ejercicio1;

import java.util.Comparator;
import java.util.List;
import us.lsi.common.List2;
import us.lsi.common.Preconditions;

public record Ejercicio1Problem(Integer i,List<Integer> cR) {
	public static Ejercicio1Problem of(Integer i ,List<Integer> cR) {
		Preconditions.checkArgument(i>=0 && i<=DatosEj1.getN(),"El indicie es incorrecto");
		return new Ejercicio1Problem(i, cR);
	}
	public Ejercicio1Problem vecino(Integer a) {
		Ejercicio1Problem r;
		if(a<DatosEj1.getM()) {
			Integer cp = this.cR.get(a);
			Integer capRest = cp -DatosEj1.getTam(this.i);
			List<Integer> aux = List2.copy(this.cR);
			aux.set(a, capRest);
			r = Ejercicio1Problem.of(this.i+1, aux);
		}else {
			r = Ejercicio1Problem.of(this.i+1, List2.copy(this.cR));
		}
		return r;
	}
	public List<Integer> acciones(){
		if(i==DatosEj1.getN()) {
			return List2.empty();
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
	public Integer greedyAction() {
		List<Integer> aux = List2.copy(this.cR);
		Integer min = aux.stream().filter(x->DatosEj1.getTam(this.i) <=x).min(Comparator.comparing(Integer::intValue)).orElse(null);
		return this.cR.indexOf(min) >=0 ? this.cR.indexOf(min):DatosEj1.getM();
	}
}

package Ejercicio3;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import us.lsi.common.List2;
import us.lsi.common.Preconditions;

public record Ejercicio3Problem(Integer i,Integer TPRest,Integer TERest) {

	public static Ejercicio3Problem of(Integer i,Integer a,Integer b) {
		Preconditions.checkArgument(i>=0 && i<=DatosEj3.getN());//Comprobar si a=>0 y b=>0 funciona
		return new Ejercicio3Problem(i,a,b);
	}
	public Ejercicio3Problem vecino(Integer a) {
		Double tiempoEc = DatosEj3.calculaTiempoE(i) *a;
		Double tiempoPc = DatosEj3.calculaTiempoP(i) *a;
		return Ejercicio3Problem.of(i+1,TPRest-tiempoPc.intValue(),TERest -tiempoEc.intValue());
	}
	public List<Integer> acciones(){
		
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
	public Integer calculaUMax(Integer i) {
		Double tpi = DatosEj3.calculaTiempoP(i);
		Double tei = DatosEj3.calculaTiempoE(i);
		
		return (int) Math.min(DatosEj3.getMax(i) ,Math.min(tpi/TPRest, tei/TERest));	
	}
	public Integer greedyAction() {
		Integer res =0;
		res = IntStream.range(0, DatosEj3.getMax(i))
				.filter(x->x*DatosEj3.calculaTiempoE(i)<=TERest && x*DatosEj3.calculaTiempoP(i)<=TPRest)
				.boxed()
				.max(Comparator.comparing(Integer::intValue))
				.orElse(0);
		return res;
	}
}

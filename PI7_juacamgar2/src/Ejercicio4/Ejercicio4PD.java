package Ejercicio4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import Soluciones.SolucionEj42;
import us.lsi.common.List2;

public class Ejercicio4PD {
	public static record S4(Integer a,Integer w) implements Comparable<S4>{
		public static S4 of(Integer a,Integer w) {
			return new S4(a,w);
		}

		@Override
		public int compareTo(S4 o) {
			return this.w.compareTo(o.w);
		}
		
	}
	public static Integer maxValue = Integer.MIN_VALUE;
	public static Ejercicio4Problem start;
	
	public static Map<Ejercicio4Problem,S4> memory;
	
	private static SolucionEj42 pd() {
		memory = new HashMap<>();
		List<Integer> ls = DatosEj4.contenedores.stream().mapToInt(x->x.capacidad()).boxed().toList();
		start = Ejercicio4Problem.of(0,ls );
		Ejercicio4PD.pd(start,0,memory);
		return Ejercicio4PD.solucion();
	}
	
	public static S4 pd(Ejercicio4Problem vertex, Integer valorAc,Map<Ejercicio4Problem,S4> memoria) {
		S4 r;
		Boolean ultimoV = vertex.i() == DatosEj4.getN();
		Boolean solucionValida = vertex.cR().stream().allMatch(x->x==0);
		
		if(memoria.containsKey(vertex)) {
			r = memoria.get(vertex);
		}else if(ultimoV && solucionValida) {
			r = S4.of(null, 0);
			memoria.put(vertex, r);
			if(valorAc>maxValue) {
				maxValue = valorAc;
			}
		}else {
			List<S4> soluciones = List2.empty();
			for(Integer action:vertex.acciones()) {
				//Double cota = valorAc +Ej4Heuristica.cota(vertex,action);
				Double cota = valorAc +Ej4Heuristica.cota2(vertex,action);
				if(cota< maxValue) {
					continue;
				}
				Ejercicio4Problem vecino = vertex.vecion(action);
				S4 s = pd(vecino,valorAc +(vecino.numContLlenos() - vertex.numContLlenos()) ,memoria);
				
				if(s!=null) {
					S4 amp = S4.of(action, s.w +(vecino.numContLlenos() - vertex.numContLlenos()) );
					soluciones.add(amp);
				}
			}
			r = soluciones.stream().max(Comparator.naturalOrder()).orElse(null);
			if(r!=null) {
			memoria.put(vertex, r);
			}
		}
		return r;
	}
	public static SolucionEj42 solucion() {
		List<Integer> acciones = List2.empty();
		Ejercicio4Problem v = start;
		S4 s = memory.get(v);
		while(s != null && s.a!= null) {
			Ejercicio4Problem old = v;
			acciones.add(s.a);
			v = old.vecion(s.a);
			s = memory.get(v);
		}
		return SolucionEj42.of(start,acciones);
	}
	
	public static void main(String[] args) {
		TestPd("././ficheros/PI7Ej4DatosEntrada1.txt");
		TestPd("././ficheros/PI7Ej4DatosEntrada2.txt");
	}

	private static void TestPd(String string) {
		Locale.setDefault(new Locale("en","US"));
		DatosEj4.iniDatos(string);
		SolucionEj42 sol = Ejercicio4PD.pd();
		System.out.println(sol);
		
	}
}

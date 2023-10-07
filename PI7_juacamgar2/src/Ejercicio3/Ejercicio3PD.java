package Ejercicio3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import Soluciones.SolucionEj32;
import us.lsi.common.List2;

public class Ejercicio3PD {
	
	public static record S3(Integer a,Integer weight) implements Comparable<S3>{
		public static S3 of(Integer a,Integer weight) {
			return new S3(a,weight);
		}
		public int compareTo(S3 sp) {
			return this.weight.compareTo(sp.weight);
		}
	}
	public static Integer maxValue=Integer.MIN_VALUE;
	public static Ejercicio3Problem start;
	public static Map<Ejercicio3Problem,S3> memory;
	
	public static SolucionEj32 pd() {
		Ejercicio3PD.start = Ejercicio3Problem.of(0, DatosEj3.tProd, DatosEj3.tManual);
		Ejercicio3PD.memory = new HashMap<>();
		pd(start,0,memory);
		return Ejercicio3PD.solucion();
	}
	
	public static S3 pd(Ejercicio3Problem vertex,Integer valorAc,Map<Ejercicio3Problem,S3> memoria) {
		S3 r;
		Boolean ultimoV = vertex.i() == DatosEj3.getN();
		Boolean solucionValida = vertex.TPRest() >=0 && vertex.TERest() >=0;
		
		if(memoria.containsKey(vertex)) {
			r = memoria.get(vertex);
		}else if(ultimoV && solucionValida) {
			r = S3.of(null, 0);
			memoria.put(vertex, r);
			if(valorAc>maxValue) {
				maxValue=valorAc;
			}
		}else {
			List<S3> soluciones = List2.empty();
			for(Integer actions:vertex.acciones()) {
				Double cota = valorAc + Ej3Heuristica.cota2(vertex, actions);
				if(cota<maxValue) {
					continue;
				}
				Ejercicio3Problem vecino = vertex.vecino(actions);
				S3 s = pd(vecino,valorAc + actions*DatosEj3.getValor(vertex.i()),memoria);
				
				if(s!=null) {
					S3 amp = S3.of(actions, s.weight + actions*DatosEj3.getValor(vertex.i()));
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
	
	public static SolucionEj32 solucion() {
		List<Integer> acciones = new ArrayList<>();
		Ejercicio3Problem v = Ejercicio3PD.start;
		S3 s = Ejercicio3PD.memory.get(v);
		while(s.a() != null) {
			acciones.add(s.a());
			v = v.vecino(s.a());
			s = Ejercicio3PD.memory.get(v);
		}
		return SolucionEj32.of(Ejercicio3PD.start, acciones);
	}
	public static void main(String[] args) {
		testPD3("././ficheros/PI7Ej3DatosEntrada1.txt");
		testPD3("././ficheros/PI7Ej3DatosEntrada2.txt");
	}
	private static void testPD3(String string) {
		Locale.setDefault(new Locale("en", "US"));
		DatosEj3.iniDatos(string);
		SolucionEj32 sol = Ejercicio3PD.pd();
		System.out.println(sol);
		
	}
}

package Ejercicio1;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import Soluciones.SolucionEj12;
import us.lsi.common.List2;

public class Ejercicio1BT {
	public static class StateEj1{
		private Ejercicio1Problem vertice;
		private Integer valorAcumulado;
		private List<Integer> acciones;
		private List<Ejercicio1Problem> vertices;
		
		private StateEj1(Ejercicio1Problem vertice,Integer valorAcumulado,List<Integer> acciones, List<Ejercicio1Problem> vertices) {
			super();
			this.vertice = vertice;
			this.valorAcumulado=valorAcumulado;
			this.acciones= acciones;
			this.vertices = vertices;
		}
		public static StateEj1 of(Ejercicio1Problem vertex) {
			List<Ejercicio1Problem> vt = List2.empty();
			vt.add(vertex);
			return new StateEj1(vertex, 0, List2.empty(), vt);
		}
		void fordward(Integer a) {
			this.acciones.add(a);
			Ejercicio1Problem vcn = this.vertice.vecino(a);
			this.vertices.add(vcn);
			this.valorAcumulado = this.valorAcumulado + a<DatosEj1.getM()?1:0;
			this.vertice= vcn;
		}
		void back(Integer a) {
			this.acciones.remove(this.acciones.size()-1);
			this.vertices.remove(this.vertices.size()-1);
			this.vertice = this.vertices.get(this.vertices.size()-1);
			this.valorAcumulado = this.valorAcumulado - a<DatosEj1.getM()?1:0;
		}
		SolucionEj12 solucion() {
			return SolucionEj12.of(Ejercicio1BT.start,this.acciones);
		}
		public Ejercicio1Problem vertice() {
			return vertice;
		}
		public Integer valorAcumulado() {
			return valorAcumulado;
		}
	}
	public static Ejercicio1Problem start;
	public static StateEj1 estado;
	public static Integer maxValue;
	public static Set<SolucionEj12> soluciones;
	
	public static void btm(List<Integer> capacidadInicial) {
		Ejercicio1BT.start = Ejercicio1Problem.of(0, capacidadInicial);
		Ejercicio1BT.estado = StateEj1.of(start);
		Ejercicio1BT.maxValue= Integer.MIN_VALUE;
		Ejercicio1BT.soluciones = new HashSet<>();
		btm();
	}
	
	public static void btm(List<Integer> capacidadInicial,Integer maxValue,SolucionEj12 s) {
		Ejercicio1BT.start = Ejercicio1Problem.of(0, capacidadInicial);
		Ejercicio1BT.estado = StateEj1.of(start);
		Ejercicio1BT.maxValue = maxValue;
		Ejercicio1BT.soluciones = new HashSet<>();
		Ejercicio1BT.soluciones.add(s);
		btm();
	}
	
	public static void btm() {
		if(Ejercicio1BT.estado.vertice.i() == DatosEj1.getN()) {
			Integer value = estado.valorAcumulado;
			if(value>Ejercicio1BT.maxValue) {
				Ejercicio1BT.maxValue= value;
				Ejercicio1BT.soluciones.add(Ejercicio1BT.estado.solucion());
			}
		}else {
			List<Integer> alternativas = Ejercicio1BT.estado.vertice().acciones();
			for(Integer a:alternativas) {
				Double cota = Ejercicio1BT.estado.valorAcumulado +Ej1Heuristica2.cota(Ejercicio1BT.estado.vertice,a);
				if(cota<Ejercicio1BT.maxValue) continue;
				Ejercicio1BT.estado.fordward(a);
				btm();
				Ejercicio1BT.estado.back(a);
			}
		}
	}
	public static void main(String[] args) {
		testBT1("././ficheros/PI7Ej1DatosEntrada1.txt");
		testBT1("././ficheros/PI7Ej1DatosEntrada2.txt");
	}
	public static void testBT1(String string) {
		Locale.setDefault(new Locale("en","US"));
		DatosEj1.iniDatos(string);
		Ejercicio1Problem v1 = Ejercicio1Problem.of(0, DatosEj1.memorias.stream().map(x->x.capacidad()).toList());
		Ejercicio1BT.btm(v1.cR());
		System.out.println(Ejercicio1BT.soluciones);
		
	}
}

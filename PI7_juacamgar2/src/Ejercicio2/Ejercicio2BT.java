package Ejercicio2;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import Soluciones.SolucionEj22;
import us.lsi.common.List2;
import us.lsi.common.Set2;

public class Ejercicio2BT {
	public static class StateEj2{
		private Ejercicio2Problem vertice;
		private Integer valorAcumulado;
		private List<Integer> acciones;
		private List<Ejercicio2Problem> vertices;
		
		private StateEj2(Ejercicio2Problem vertice,Integer valorAcumulado,List<Integer> acciones, List<Ejercicio2Problem> vertices) {
			super();
			this.vertice = vertice;
			this.valorAcumulado=valorAcumulado;
			this.acciones= acciones;
			this.vertices = vertices;
		}
		public static StateEj2 of(Ejercicio2Problem vertex) {
			List<Ejercicio2Problem> vt = List2.empty();
			vt.add(vertex);
			return new StateEj2(vertex,0,List2.empty(),vt);
		}
		void fordward(Integer a) {
			this.acciones.add(a);
			Ejercicio2Problem vcn = this.vertice.vecino(a);
			this.vertices.add(vcn);
			this.valorAcumulado = this.valorAcumulado +a* DatosEj2.getVal(this.vertice.i());
			this.vertice= vcn;
		}
		void back(Integer a) {
			this.acciones.remove(this.acciones.size()-1);
			this.vertices.remove(this.vertices.size()-1);
			this.vertice=this.vertices.get(this.vertices.size()-1);
			this.valorAcumulado = this.valorAcumulado -a*DatosEj2.getVal(this.vertice.i());
		}
		SolucionEj22 solucion() {
			return SolucionEj22.of(Ejercicio2BT.start, acciones);
		}
		public Ejercicio2Problem vertice() {
			return vertice;
		}
		public Integer valorAcumulado() {
			return valorAcumulado;
		}
		
	}
	public static Ejercicio2Problem start;
	public static StateEj2 estado;
	public static Integer maxValue;
	public static Set<SolucionEj22> soluciones;
	
	public static void btm(Set<Integer> cE) {
		Ejercicio2BT.start = Ejercicio2Problem.of(0, cE);
		Ejercicio2BT.estado = StateEj2.of(start);
		Ejercicio2BT.maxValue = Integer.MIN_VALUE;
		Ejercicio2BT.soluciones = new HashSet<>();
		btm();
	}
	public static void btm(Set<Integer> cE,Integer maxValue,SolucionEj22 s) {
		Ejercicio2BT.start = Ejercicio2Problem.of(0, cE);
		Ejercicio2BT.estado = StateEj2.of(start);
		Ejercicio2BT.maxValue = maxValue;
		Ejercicio2BT.soluciones = new HashSet<>();
		Ejercicio2BT.soluciones.add(s);
		btm();
	}
	
	public static void btm() {
		if(Ejercicio2BT.estado.vertice.i() == DatosEj2.getN()) {
			Integer value = estado.valorAcumulado;
			if(value>Ejercicio2BT.maxValue) {
				Ejercicio2BT.maxValue = value;
				Ejercicio2BT.soluciones.add(Ejercicio2BT.estado.solucion());
			}
		}else {
			List<Integer> alternativas = Ejercicio2BT.estado.vertice().acciones();
			for(Integer a:alternativas) {
				Double cota = Ejercicio2BT.estado.valorAcumulado + Ej2Heuristica.cota2(Ejercicio2BT.estado.vertice,a);
				if(cota<Ejercicio2BT.maxValue)continue;
				Ejercicio2BT.estado.fordward(a);
				btm();
				Ejercicio2BT.estado.back(a);
			}
		}
	}
	public static void main(String[] args) {
		testBT2("././ficheros/PI7Ej2DatosEntrada1.txt");
		testBT2("././ficheros/PI7Ej2DatosEntrada2.txt");
	}
	private static void testBT2(String string) {
		Locale.setDefault(new Locale("en","US"));
		DatosEj2.iniDatos(string);
		Ejercicio2Problem v1 = Ejercicio2Problem.of(0, Set2.empty());
		Ejercicio2BT.btm(v1.cE());
		SolucionEj22 sol = Ejercicio2BT.soluciones.stream()
				.filter(x->x.gasto()<= DatosEj2.presupuesto)
				.max(Comparator.comparing(x->x.valoracionTotal())).orElse(null);
		System.out.println(SolucionEj22.toString(sol));
	}
}

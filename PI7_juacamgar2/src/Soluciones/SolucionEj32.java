package Soluciones;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Ejercicio3.DatosEj3;
import Ejercicio3.Ejercicio3Problem;
import us.lsi.common.Map2;
import util.Elemento;
import util.Producto;

public record SolucionEj32(Double val,Map<Producto,Integer> mp) {
	public static SolucionEj32 of(Ejercicio3Problem p,List<Integer> acciones) {
		Map<Producto,Integer> ls = Map2.empty();
		Ejercicio3Problem v = p;
		Double valor = 0.;
		for(int i = 0;i<acciones.size();i++) {
			Integer a = acciones.get(i);
			Producto e = DatosEj3.productos.get(v.i());
			if(a>0){
				ls.put(e, a);
				valor += a*e.precio();
			}
			v = v.vecino(a);
		}
		return new SolucionEj32(valor,ls);
	}
	public String toString() {
		String res = String.format("Beneficio =" + val + "\n");
		for(Entry<Producto,Integer> au :mp.entrySet()) {
			res += au.getKey() + " = " + au.getValue() + "\n";
		}
		return res;
	}
}

package Soluciones;

import java.util.Map;
import java.util.Map.Entry;
import us.lsi.common.Map2;
import util.Producto;

public class SolucionEj3 {
	public static SolucionEj3 empty() {
		return new SolucionEj3();
	}
	private Map<Producto,Integer> mp;
	private Double beneficio;
	private SolucionEj3(){
		mp = Map2.empty();
		beneficio = 0.;
	}
	public void add(Producto p ,Integer num) {
		mp.put(p, num);
		beneficio += p.precio() *num;
	}
	public void add(Producto p,Integer b,Double w) {
		if(b>0) {
			mp.put(p, b);
			beneficio += w;
		}
	}
	public String toString() {
		String res = String.format("Beneficio = %.1f \n", beneficio);
		for(Entry<Producto,Integer> au :mp.entrySet()) {
			res += au.getKey() + " = " + au.getValue() + "\n";
		}
		return res;
	}
	
}

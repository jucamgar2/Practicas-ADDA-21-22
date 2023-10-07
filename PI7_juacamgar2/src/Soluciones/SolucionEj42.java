package Soluciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Ejercicio4.DatosEj4;
import Ejercicio4.Ejercicio4Problem;
import us.lsi.common.Map2;
import util.Contenedor;
import util.Elemento;

public record SolucionEj42(Integer valor,Map<Contenedor,List<Elemento>> mp) {
	public static SolucionEj42 of(Ejercicio4Problem p,List<Integer> acciones) {
		Map<Contenedor,List<Elemento>> mp = Map2.empty();
		Ejercicio4Problem v = p;
		Integer peso = 0;
		for(int i = 0;i<acciones.size();i++) {
			Integer a = acciones.get(i);
			if(a<DatosEj4.getM()) {
				Contenedor c = DatosEj4.contenedores.get(a);
				Elemento e = DatosEj4.elementos.get(v.i());
				if(mp.containsKey(c)) {
					mp.get(c).add(e);
				}else {
					List<Elemento> ls =  new ArrayList<>();
					ls.add(e);
					mp.put(c,ls);
				}
			}
			peso = v.numContLlenos();
			v = v.vecion(a);
		}
		return new SolucionEj42(peso,mp);
	}
	public String toString() {
		String res = String.format("Número de contenedores llenos : " + valor + "\n");
		for (Entry<Contenedor, List<Elemento>> au : mp.entrySet()) {
			res += au.getKey() + ": " + au.getValue() + "\n";
		}
		return res;
	}
}

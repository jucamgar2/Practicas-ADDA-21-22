package Soluciones;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Ejercicio1.DatosEj1;
import Ejercicio1.Ejercicio1Problem;
import us.lsi.common.List2;
import us.lsi.common.Map2;
import util.Archivo;
import util.Memoria;

public record SolucionEj12(Integer numA,Map<Memoria,List<Archivo>> ls) {
	public static SolucionEj12 of(Ejercicio1Problem p,List<Integer> acciones) {
		Map<Memoria,List<Archivo>> mp = Map2.empty();
		Ejercicio1Problem v = p;
		Integer numA = 0;
		for(int i = 0; i<acciones.size();i++) {
			Integer a = acciones.get(i);
			if(a<DatosEj1.getM()) {
				Memoria mem =DatosEj1.memorias.get(a);
				Archivo ac = DatosEj1.archivos.get(v.i());
				if(mp.containsKey(mem)) {
					mp.get(mem).add(ac);
				}else {
					mp.put(mem, List2.of(ac));
				}
				numA +=1;
			}
			v = v.vecino(a);
		}
		return new SolucionEj12(numA,mp);
	}
	public String toString() {
		String res = "Número de archivos metidos en memoria: "+numA+ "\n";
		for(Entry<Memoria,List<Archivo>> au : ls.entrySet()) {
			res += au.getKey() +  ": " + au.getValue() + "\n";
		}
		return res;
	}

}

package Soluciones;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import Ejercicio1.DatosEj1;
import Ejercicio1.Ejercicio1BT;
import Ejercicio1.Ejercicio1Problem;
import us.lsi.common.List2;
import us.lsi.common.Map2;
import util.Archivo;
import util.Memoria;

public class SolucionEj1 {
	public static SolucionEj1 empty() {
		return new SolucionEj1();
	}
	public Map<Memoria,List<Archivo>> ls;
	public Double numArch;
	public SolucionEj1() {
		ls = Map2.empty();
		numArch = 0.;
	}
	public void add(Memoria m, Archivo a) {
		if(ls.containsKey(m)) {
			ls.get(m).add(a);
		}else {
			ls.put(m, List2.of(a));
		}
		numArch +=1;
	}
	public void add(Archivo a,Integer b,Double w) {
		if(w>0) {
			Memoria m = DatosEj1.memorias.get(b);
			if(ls.containsKey(m)) {
				ls.get(m).add(a);
			}else {
				ls.put(m, List2.of(a));
			}
			numArch +=1;
		}
	}
	public String toString() {
		String res = String.format("N�mero de archivos metidos en memoria: %.1f \n", numArch);
		for(Entry<Memoria,List<Archivo>> au : ls.entrySet()) {
			res += au.getKey() +  ": " + au.getValue() + "\n";
		}
		return res;
	}
	
}

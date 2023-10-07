package Soluciones;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import Ejercicio4.DatosEj4;
import us.lsi.common.List2;
import us.lsi.common.Map2;
import util.Contenedor;
import util.Elemento;

public class SolucionEj4 {
	public static SolucionEj4 empty() {
		return new SolucionEj4();
	}

	public Map<Contenedor, List<Elemento>> mp;
	public Double numContLlenos;

	public SolucionEj4() {
		mp = Map2.empty();
		numContLlenos = 0.;
	}

	public void add(Contenedor c, Elemento e) {
		if (mp.containsKey(c)) {
			mp.get(c).add(e);
		} else {
			mp.put(c, List2.of(e));
		}
		Integer a = mp.get(c).stream().mapToInt(x -> x.tama�o()).sum();
		if (c.capacidad() == a) {
			numContLlenos += 1;
		}
	}

	public void add(Elemento e, Integer b, Double w) {
		if (b < DatosEj4.getM()) {
			Contenedor c = DatosEj4.contenedores.get(b);
			if (mp.containsKey(c)) {
				mp.get(c).add(e);
			} else {
				mp.put(c, List2.of(e));
			}
			Integer a = mp.get(c).stream().mapToInt(x -> x.tama�o()).sum();
			if (c.capacidad() == a) {
				numContLlenos += 1;
			}
		}
	}

	public String toString() {
		String res = String.format("N�mero de contenedores llenos : %.1f \n", numContLlenos);
		for (Entry<Contenedor, List<Elemento>> au : mp.entrySet()) {
			res += au.getKey() + ": " + au.getValue() + "\n";
		}
		return res;
	}
}

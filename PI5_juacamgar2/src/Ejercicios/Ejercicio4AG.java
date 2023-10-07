package Ejercicios;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Soluciones.SolucionEj4;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.Map2;
import util.Contenedor;
import util.Elemento;

public class Ejercicio4AG implements ValuesInRangeData<Integer, SolucionEj4> {
	private Double goal;
	private Double fallos;
	private Double fittnes = null;

	@Override
	public Integer size() {
		return DatosEj4.getN();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		this.goal = 0.;
		this.fallos = 0.;
		Map<Contenedor, Integer> map = Map2.empty();
		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) < DatosEj4.getM()) {
				Contenedor c = DatosEj4.contenedores.get(value.get(i));
				Elemento e = DatosEj4.elementos.get(i);
				if (map.containsKey(c)) {
					Integer p = map.get(c) + DatosEj4.getPeso(i);
					map.replace(c, p);
				} else {
					map.put(c, DatosEj4.getPeso(i));
				}
				if (!e.tipos().contains(c.tipo())) {
					this.fallos += 1000;
				}
			}
		}
		for (Entry<Contenedor, Integer> l : map.entrySet()) {
			this.fallos += Math.abs(l.getKey().capacidad() - l.getValue());
			if (l.getKey().capacidad() == l.getValue()) {
				this.goal += 1;
			}
		}
		this.fittnes = this.goal - this.fallos;
		return this.fittnes;
	}

	@Override
	public SolucionEj4 solucion(List<Integer> value) {
		SolucionEj4 res = SolucionEj4.empty();
		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) < DatosEj4.getM()) {
				Contenedor c = DatosEj4.contenedores.get(value.get(i));
				res.add(c, DatosEj4.elementos.get(i));
			}
		}
		return res;
	}

	@Override
	public Integer max(Integer i) {

		return DatosEj4.getM() + 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

}

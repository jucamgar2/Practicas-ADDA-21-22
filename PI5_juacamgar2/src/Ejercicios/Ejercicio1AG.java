package Ejercicios;

import java.util.List;



import Soluciones.SolucionEj1;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

import util.Memoria;

public class Ejercicio1AG implements ValuesInRangeData<Integer, SolucionEj1> {

	private Double goal;
	private Double fallos;
	private Double fittnes = null;

	@Override
	public Integer size() {
		return DatosEj1.getN();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public SolucionEj1 solucion(List<Integer> value) {
		SolucionEj1 res = SolucionEj1.empty();
		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) < DatosEj1.memorias.size()) {
				Memoria m = DatosEj1.memorias.get(value.get(i));
				res.add(m, DatosEj1.archivos.get(i));
			}
		}
		return res;
	}

	@Override
	public Integer max(Integer i) {
		return DatosEj1.getM() + 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}


	public Double fitnessFunction(List<Integer> value) {
		this.goal = 0.;
		this.fallos = 0.;
		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) < DatosEj1.getM() - 1) {
				Memoria mem = DatosEj1.memorias.get(value.get(i));
				Integer peso = DatosEj1.getTam(i);
				if (mem.tamMax() > peso) {
					this.goal += 1;
				} else {
					this.fallos += 1;
				}
			}
		}
		compruebaMemoriasLlenas(value);
		this.fittnes = this.goal - 1000 * this.fallos;
		return this.fittnes;
	}

	private void compruebaMemoriasLlenas(List<Integer> value) {
		for(int i = 0; i<DatosEj1.getM()-1;i++) {
			Memoria mem = DatosEj1.memorias.get(i);
			Integer e = i;
			Integer t = value.stream().mapToInt(x->x == e? DatosEj1.getTam(x):0).sum();
			if(t>mem.capacidad()) {
				this.fallos += Math.abs(mem.capacidad()-t);
			}
		}
		
	}

	
	

}

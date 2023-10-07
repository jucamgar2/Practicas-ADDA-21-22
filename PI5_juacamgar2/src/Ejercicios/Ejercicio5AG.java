package Ejercicios;

import java.util.List;
import java.util.Set;

import Soluciones.SolucionEj5;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.List2;
import us.lsi.common.Set2;
import util.Carretera;
import util.Ciudad;

public class Ejercicio5AG implements SeqNormalData<SolucionEj5>{
	private Double goal;
	private Double fallos;
	private Double fittnes = null;
	@Override
	public ChromosomeType type() {
		return ChromosomeType.PermutationSubList;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		this.goal = 0.;
		this.fallos = 0.;
		List<Carretera> carreteras = List2.empty();
		Set<Ciudad> ciudades = Set2.empty();
		if(value.size()>=3) {
			if(DatosEj5.destino != DatosEj5.ciudades.get(value.get(value.size()-1)) || DatosEj5.origen!=DatosEj5.ciudades.get(value.get(0))) {
				this.fallos += 100;
			}
			for(int i = 0;i<value.size()-1;i++) {
				Ciudad src = DatosEj5.ciudades.get(value.get(i)), target = DatosEj5.ciudades.get(value.get(i+1));
				ciudades.add(target);
				ciudades.add(src);
				if(DatosEj5.g.containsEdge(src, target)) {
					Carretera c = DatosEj5.g.getEdge(src, target);
					carreteras.add(c);
					this.goal += c.getKm();
				}else {
					this.fallos+= 100;
				}
			}
		}else {
			this.fallos += 10000;	
		}
		this.fallos+= ciudades.stream().filter(DatosEj5.pCi).findAny().orElse(null) == null?1000:0;
		this.fallos+= carreteras.stream().filter(DatosEj5.pCa).findAny().orElse(null) == null?1000:0;
		this.fittnes = -this.goal -1000*this.fallos;
		return this.fittnes;
	}


	@Override
	public SolucionEj5 solucion(List<Integer> value) {
		return SolucionEj5.crear(value);
	}

	@Override
	public Integer itemsNumber() {

		return DatosEj5.ciudades.size();
	}
}

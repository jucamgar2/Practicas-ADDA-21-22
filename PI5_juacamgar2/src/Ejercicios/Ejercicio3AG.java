package Ejercicios;

import java.util.List;
import Soluciones.SolucionEj3;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Ejercicio3AG implements ValuesInRangeData<Integer, String>{
	private Double goal;
	private Double fallos;
	private Double fittnes = null;
	@Override
	public Integer size() {
		return DatosEj3.getN();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	
	

	@Override
	public String solucion(List<Integer> value) {
		SolucionEj3 res = SolucionEj3.empty();
		for(int i =0;i<value.size();i++) {
			if(value.get(i)>0) {
				res.add(DatosEj3.productos.get(i), value.get(i));
			}
		}
		return res.toString();
	}

	@Override
	public Integer max(Integer i) {
		return DatosEj3.productos.get(i).uMax() +1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		this.goal = 0.;
		Double tiempoP = DatosEj3.tProd.doubleValue();
		Double timepoM = DatosEj3.tManual.doubleValue();
		for(int i =0; i<value.size();i++) {
			if(value.get(i)>0) {
				this.goal+= DatosEj3.getValor(i);
				for(int j = 0;j<DatosEj3.componentes.size();j++) {
					tiempoP -= DatosEj3.getTiempoP(i, j)*value.get(i);
					timepoM -= DatosEj3.getTiempoE(i, j)*value.get(i);
				}
			}
			if(DatosEj3.getMax(i)<value.get(i)) {
				this.fallos += 1000*value.get(i)-DatosEj3.getMax(i);
			}
		}
		this.fallos = Math.abs(tiempoP)*5000 + Math.abs(timepoM)*5000;
		this.fittnes = this.goal - this.fallos;
		return this.fittnes;
	}
	
}

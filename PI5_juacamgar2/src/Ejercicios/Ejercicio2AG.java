package Ejercicios;

import java.util.List;
import java.util.Set;

import Soluciones.SolucionEj2;
import us.lsi.ag.BinaryData;
import us.lsi.common.List2;
import us.lsi.common.Set2;
import util.Candidato;

public class Ejercicio2AG implements BinaryData<String> {
	private Double goal;
	private Double fallos;
	private Double fittnes = null;

	@Override
	public Integer size() {
		return DatosEj2.n;
	}

	@Override
	public String solucion(List<Integer> value) {
		SolucionEj2 res = SolucionEj2.empty();
		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) > 0) {
				res.add(DatosEj2.candidatos.get(i));
			}
		}
		return res.toString();
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		this.goal = 0.;
		this.fallos = 0.;
		Double presupuesto = DatosEj2.presupuesto.doubleValue();
		Set<String> cualidadesTotales = Set2.empty();
		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) > 0) {
				Candidato c = DatosEj2.candidatos.get(i);
				presupuesto -= c.sueldo();
				cualidadesTotales.addAll(c.cualidades());
				for (int j = 0; j < value.size() && j != i; j++) {
					if (value.get(j) > 0) {
						Integer aux = DatosEj2.esIncomp(i, j) ? 1 : 0;
						this.fallos += 1000000 * aux;
					}
				}
				this.goal += DatosEj2.getVal(i);
			}
		}
		if (!cualidadesTotales.containsAll(DatosEj2.cualidadesDeseadas)) {
			this.fallos += 10000000000.;
		}
		if (presupuesto < 0) {
			this.fallos += Math.pow(Math.abs(presupuesto), 2);
		}
		this.fittnes = this.goal - 100 * this.fallos;

		return this.fittnes;
	}

	
}

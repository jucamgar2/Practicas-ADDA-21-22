package Soluciones;

import java.util.List;
import Ejercicio2.DatosEj2;
import us.lsi.common.List2;
import util.Candidato;
public class SolucionEj2 {
	public static SolucionEj2 empty() {
		return new SolucionEj2();
	}
	private List<Candidato> ls;
	private Double valoracionTotal;
	private Double valoracinoMedia;
	private Double gasto;
	private SolucionEj2() {
		ls = List2.empty();
		valoracionTotal = 0.;
		valoracinoMedia = 0.;
		gasto = 0.;
	}
	public void add(Candidato c) {
		ls.add(c);
		valoracionTotal += c.valoracion();
		gasto += c.sueldo();
		valoracinoMedia = valoracionTotal/ls.size();
	}
	public void add(Candidato c,Integer b,Double w) {
		if(b>0) {
			ls.add(c);
			valoracionTotal += c.valoracion();
			gasto += c.sueldo();
			valoracinoMedia = valoracionTotal/ls.size();
		}
	}
	public String toString() {
		String res = "Cualidades requeridas: " + DatosEj2.cualidadesDeseadas.toString() +"\n";
		res += String.format("Valoración total = %.1f, Valoracion media = %.1f, Gasto = %.1f \n" , valoracionTotal,valoracinoMedia,gasto);
		for(Candidato c:ls) {
			res += c.toString() + "\n";
		}
		return res;
	}
}

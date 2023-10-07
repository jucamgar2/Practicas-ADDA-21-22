package Soluciones;

import java.util.List;

import Ejercicio2.DatosEj2;
import Ejercicio2.Ejercicio2Problem;
import us.lsi.common.List2;
import util.Candidato;

public record SolucionEj22(List<Candidato> ls, Double valoracionTotal,Double valoracionMeida,Double gasto ) {
	public static SolucionEj22 of(Ejercicio2Problem p,List<Integer> acciones) {
		List<Candidato> ls = List2.empty();
		Ejercicio2Problem v = p;
		Double vT = 0.0;
		Double vM = 0.0;
		Double gastos = 0.0;
		for(int i = 0;acciones.size()>i;i++) {
			Integer a = acciones.get(i);
			if(a>0) {
				Candidato c = DatosEj2.candidatos.get(v.i());
				ls.add(c);
				vT += c.valoracion();
				vM = vT/ls.size();
				gastos += c.sueldo();
			}
			v= v.vecino(a);
		}
		return new SolucionEj22(ls,vT,vM,gastos);
	}
	public static String toString(SolucionEj22 sol) {
		String s = "valoracion total = " + sol.valoracionTotal + "\n";
		s += "valoracion media = " + sol.valoracionMeida + "\n";
		s += "gasto = " + sol.gasto +"\n";
		s += "candidatos : \n";
		for(Candidato c:sol.ls) {
			s += c+"\n";
		}
		return s;
	}

}

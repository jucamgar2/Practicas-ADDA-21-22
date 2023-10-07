package Ejercicios;

import java.util.List;
import us.lsi.common.Files2;
import us.lsi.common.List2;
import util.Candidato;

public class DatosEj2 {
	public static List<String> cualidadesDeseadas;
	public static Integer presupuesto;
	public static List<Candidato> candidatos;
	public static Integer n;
	public static Integer m;
	public static Integer getM(){
		return m;
	}
	public static Integer getN() {
		return n;
	}
	public static Integer getVal(Integer i) {
		return candidatos.get(i).valoracion();
	}
	public static Double getSu(Integer i) {
		return candidatos.get(i).sueldo();
	}
	public static Integer getPresupuesto() {
		return presupuesto;
	}
	public static Boolean tieneCualidad(Integer i,Integer j) {
		return candidatos.get(i).cualidades().contains(cualidadesDeseadas.get(j));
	}
	public static Integer sonIncompatibles(Integer i,Integer j){
		Integer res = 0;
		if(candidatos.get(i).incompatibilidades().contains(candidatos.get(j).nombre())) {
			res = 1;
		}else {
			res = 2;
		}
		return res;
	}
	public static void iniDatos(String datos) {
		List<String> aux = Files2.linesFromFile(datos);
		cualidadesDeseadas = List2.parse(aux.get(0).replaceAll("Cualidades Deseadas: ","").strip(), ",",String::toString);
		presupuesto = Integer.valueOf(aux.get(1).replaceAll("Presupuesto Máximo: ", "").strip());
		candidatos = List2.empty();
		for(int i=2;i<aux.size();i++) {
			candidatos.add(Candidato.ofFormat(aux.get(i)));
		}
		n = candidatos.size();
		m = cualidadesDeseadas.size();
	}
	public static void main(String[] args) {
		iniDatos("././ficheros/PI5Ej2DatosEntrada1.txt");
		System.out.println(cualidadesDeseadas);
		System.out.println(presupuesto);
		System.out.println(candidatos);
	}
	public static Boolean esIncomp(Integer i, Integer j) {
		return DatosEj2.candidatos.get(i).incompatibilidades().contains(DatosEj2.candidatos.get(j).nombre());
	}
}

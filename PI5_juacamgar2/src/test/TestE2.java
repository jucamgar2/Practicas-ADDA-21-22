package test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import Ejercicios.DatosEj2;
import us.lsi.common.List2;
import us.lsi.common.String2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;
import util.Candidato;

public class TestE2 {
	
	public static void main(String[] args) {
		try {
			DatosEj2.iniDatos("././ficheros/PI5Ej2DatosEntrada1.txt");
			AuxGrammar.generate(DatosEj2.class, "modelos/Ejercicio2.lsi", "gurobi_models/Ejercicio2_11.lp");
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/Ejercicio2_11.lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println("El valor de la función objetivo es: " + gs.objVal);
			Map<String,Double> e = gs.values;
			List<Entry<String,Double>> ls = e.entrySet().stream().sorted(Comparator.comparing(x->x.getKey())).toList();
			List<Candidato> candidatos = DatosEj2.candidatos;
			candidatos.sort(Comparator.comparing(x->x.nombre()));
			List<Candidato> aux = List2.empty();
			Double gasto = 0.;
			for(int i =0;i<ls.size();i++) {
				if(ls.get(i).getValue()>0) {
					aux.add(candidatos.get(i));
					gasto+= candidatos.get(i).sueldo();
				}
			}
			System.out.println("Candidatos seleccionados: \n");
			for(Candidato c:aux) {
				System.out.println(c +" \n");
			}
			System.out.println("Valoración média: " + gs.objVal/aux.size());
			System.out.println("Gasto: " + gasto);
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(String2.linea());
		try {
			DatosEj2.iniDatos("././ficheros/PI5Ej2DatosEntrada2.txt");
			AuxGrammar.generate(DatosEj2.class, "modelos/Ejercicio2.lsi", "gurobi_models/Ejercicio2_12.lp");
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/Ejercicio2_12.lp");
			Locale.setDefault(new Locale("en", "US"));
			//System.out.println(gs.toString((s,d)->d>0.));
			System.out.println("El valor de la función objetivo es: " + gs.objVal);
			Map<String,Double> e = gs.values;
			List<Entry<String,Double>> ls = e.entrySet().stream().sorted(Comparator.comparing(x->x.getKey())).toList();
			List<Candidato> candidatos = DatosEj2.candidatos;
			candidatos.sort(Comparator.comparing(x->x.nombre()));
			List<Candidato> aux = List2.empty();
			Double gasto = 0.;
			for(int i =0;i<ls.size();i++) {
				if(ls.get(i).getValue()>0) {
					aux.add(candidatos.get(i));
					gasto+= candidatos.get(i).sueldo();
				}
			}
			System.out.println("Candidatos seleccionados: \n");
			for(Candidato c:aux) {
				System.out.println(c +" \n");
			}
			System.out.println("Valoración média: " + gs.objVal/aux.size());
			System.out.println("Gasto: " + gasto);
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(String2.linea());
		try {
			DatosEj2.iniDatos("././ficheros/PI5Ej2DatosEntrada3.txt");
			AuxGrammar.generate(DatosEj2.class, "modelos/Ejercicio2.lsi", "gurobi_models/Ejercicio2_13.lp");
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/Ejercicio2_13.lp");
			Locale.setDefault(new Locale("en", "US"));
			//System.out.println(gs.toString((s,d)->d>0.));
			System.out.println("El valor de la función objetivo es: " + gs.objVal);
			Map<String,Double> e = gs.values;
			List<Entry<String,Double>> ls = e.entrySet().stream().sorted(Comparator.comparing(x->x.getKey())).toList();
			List<Candidato> candidatos = DatosEj2.candidatos;
			candidatos.sort(Comparator.comparing(x->x.nombre()));
			List<Candidato> aux = List2.empty();
			Double gasto = 0.;
			for(int i =0;i<ls.size();i++) {
				if(ls.get(i).getValue()>0) {
					aux.add(candidatos.get(i));
					gasto+= candidatos.get(i).sueldo();
				}
			}
			System.out.println("Candidatos seleccionados: \n");
			for(Candidato c:aux) {
				System.out.println(c +" \n");
			}
			System.out.println("Valoración média: " + gs.objVal/aux.size());
			System.out.println("Gasto: " + gasto);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

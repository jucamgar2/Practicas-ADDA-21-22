package test;

import java.io.IOException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import Ejercicios.DatosEj1;
import us.lsi.common.List2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;
import util.Archivo;
import util.Memoria;

public class TestE1 {

	public static void main(String[] args) {
		try {
			DatosEj1.iniDatos("././ficheros/PI5Ej1DatosEntrada1.txt");
			AuxGrammar.generate(DatosEj1.class, "modelos/Ejercicio1.lsi", "gurobi_models/Ejercicio1_1.lp");
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/Ejercicio1_1.lp");
			Locale.setDefault(new Locale("en", "US"));
			//System.out.println(gs.toString((s,d)->d>0.));
			System.out.println("Nº de archivos = " + gs.objVal);
			Map<String,Double> e = gs.values;
			List<Entry<String,Double>> ls = e.entrySet().stream().sorted(Comparator.comparing(x->x.getKey())).toList();
			List<Memoria> memorias = DatosEj1.memorias;
			List<Archivo> archivos = DatosEj1.archivos;
			memorias.sort(Comparator.comparing(x->x.nombre()));
			archivos.sort(Comparator.comparing(x->x.nombre()));
			System.out.println(memorias);
			System.out.println(archivos);
			Map<Memoria,List<String>> res = new HashMap<>();
			for(Entry<String,Double> ex:ls) {
				if(ex.getValue()>0) {
					String [] b = ex.getKey().split("_");
					Memoria m = memorias.get(Integer.valueOf(b[2]));
					Archivo ar = archivos.get(Integer.valueOf(b[1]));
					if(res.containsKey(m)) {
						res.get(m).add(ar.nombre());
					}else {
						List<String> u = List2.empty();
						u.add(ar.nombre());
						res.put(m, u);
					}
				}
			}
			for(Entry<Memoria,List<String>> m : res.entrySet()) {
				System.out.println(m.getKey().nombre() +": " +  m.getValue() +"\n");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		try {
			DatosEj1.iniDatos("././ficheros/PI5Ej1DatosEntrada2.txt");
			AuxGrammar.generate(DatosEj1.class, "modelos/Ejercicio1.lsi", "gurobi_models/Ejercicio1_2.lp");
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/Ejercicio1_2.lp");
			Locale.setDefault(new Locale("en", "US"));
			//System.out.println(gs.toString((s,d)->d>0.));7
			System.out.println("Nº de archivos = " + gs.objVal);
			Map<String,Double> e = gs.values;
			List<Entry<String,Double>> ls = e.entrySet().stream().sorted(Comparator.comparing(x->x.getKey())).toList();
			List<Memoria> memorias = DatosEj1.memorias;
			List<Archivo> archivos = DatosEj1.archivos;
			memorias.sort(Comparator.comparing(x->x.nombre()));
			archivos.sort(Comparator.comparing(x->x.nombre()));
			System.out.println(memorias);
			System.out.println(archivos);
			Map<Memoria,List<String>> res = new HashMap<>();
			for(Entry<String,Double> ex:ls) {
				if(ex.getValue()>0) {
					String [] b = ex.getKey().split("_");
					Memoria m = memorias.get(Integer.valueOf(b[2]));
					Archivo ar = archivos.get(Integer.valueOf(b[1]));
					if(res.containsKey(m)) {
						res.get(m).add(ar.nombre());
					}else {
						List<String> u = List2.empty();
						u.add(ar.nombre());
						res.put(m, u);
					}
				}
			}
			for(Entry<Memoria,List<String>> m : res.entrySet()) {
				System.out.println(m.getKey().nombre() +": " +  m.getValue() +"\n");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		try {
			DatosEj1.iniDatos("././ficheros/PI5Ej1DatosEntrada3.txt");
			AuxGrammar.generate(DatosEj1.class, "modelos/Ejercicio1.lsi", "gurobi_models/Ejercicio1_3.lp");
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/Ejercicio1_3.lp");
			Locale.setDefault(new Locale("en", "US"));
			//System.out.println(gs.toString((s,d)->d>0.));
			System.out.println("Nº de archivos = " + gs.objVal);
			Map<String,Double> e = gs.values;
			List<Entry<String,Double>> ls = e.entrySet().stream().sorted(Comparator.comparing(x->x.getKey())).toList();
			List<Memoria> memorias = DatosEj1.memorias;
			List<Archivo> archivos = DatosEj1.archivos;
			memorias.sort(Comparator.comparing(x->x.nombre()));
			archivos.sort(Comparator.comparing(x->x.nombre()));
			System.out.println(memorias);
			System.out.println(archivos);
			Map<Memoria,List<String>> res = new HashMap<>();
			for(Entry<String,Double> ex:ls) {
				if(ex.getValue()>0) {
					String [] b = ex.getKey().split("_");
					Memoria m = memorias.get(Integer.valueOf(b[2]));
					Archivo ar = archivos.get(Integer.valueOf(b[1]));
					if(res.containsKey(m)) {
						res.get(m).add(ar.nombre());
					}else {
						List<String> u = List2.empty();
						u.add(ar.nombre());
						res.put(m, u);
					}
				}
			}
			for(Entry<Memoria,List<String>> m : res.entrySet()) {
				System.out.println(m.getKey().nombre() +": " +  m.getValue() +"\n");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}

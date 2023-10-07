package test;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import Ejercicios.DatosEj4;
import us.lsi.common.List2;
import us.lsi.common.String2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;
import util.Contenedor;
import util.Elemento;

public class TestE4 {

	public static void main(String[] args) throws IOException {
		try {
			DatosEj4.iniDatos("././ficheros/PI5Ej4DatosEntrada1.txt");
			AuxGrammar.generate(DatosEj4.class, "modelos/Ejercicio4.lsi", "gurobi_models/Ejercicio4_1.lp");
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/Ejercicio4_1.lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println(gs.toString((s, d) -> d > 0.));
			Map<String, Double> e = gs.values;
			List<Entry<String, Double>> ls = e.entrySet().stream().sorted(Comparator.comparing(x -> x.getKey()))
					.toList();
			List<Elemento> elementos = DatosEj4.elementos;
			List<Contenedor> contenedores = DatosEj4.contenedores;
			elementos.sort(Comparator.comparing(y -> y.nombre()));
			contenedores.sort(Comparator.comparing(c -> c.nombre()));
			Map<Contenedor, List<Elemento>> res = new HashMap<>();
			for (Entry<String, Double> ex : ls) {
				if (ex.getValue() > 0) {
					String[] b = ex.getKey().split("_");
					if (b.length > 2) {
						Contenedor c = contenedores.get(Integer.valueOf(b[2]));
						Elemento el = elementos.get(Integer.valueOf(b[1]));
						if (res.containsKey(c)) {
							res.get(c).add(el);
						} else {
							List<Elemento> u = List2.empty();
							u.add(el);
							res.put(c, u);
						}
					}
				}
			}
			for (Entry<Contenedor, List<Elemento>> m : res.entrySet()) {
				System.out.println(m.getKey() + ": " + m.getValue() + "\n");
			}
			System.out.println("Número de contendores llenos: " + res.keySet().size());
			System.out.println(String2.linea());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			DatosEj4.iniDatos("././ficheros/PI5Ej4DatosEntrada2.txt");
			AuxGrammar.generate(DatosEj4.class, "modelos/Ejercicio4.lsi", "gurobi_models/Ejercicio4_2.lp");
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/Ejercicio4_2.lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println(gs.toString((s, d) -> d > 0.));
			Map<String, Double> e = gs.values;
			List<Entry<String, Double>> ls = e.entrySet().stream().sorted(Comparator.comparing(x -> x.getKey()))
					.toList();
			List<Elemento> elementos = DatosEj4.elementos;
			List<Contenedor> contenedores = DatosEj4.contenedores;

			elementos.sort(Comparator.comparing(y -> y.nombre()));
			contenedores.sort(Comparator.comparing(c -> c.nombre()));
			Map<Contenedor, List<Elemento>> res = new HashMap<>();
			for (Entry<String, Double> ex : ls) {
				if (ex.getValue() > 0) {
					String[] b = ex.getKey().split("_");
					if (b.length > 2) {
						Contenedor c = contenedores.get(Integer.valueOf(b[2]));
						Elemento el = elementos.get(Integer.valueOf(b[1]));
						if (res.containsKey(c)) {
							res.get(c).add(el);
						} else {
							List<Elemento> u = List2.empty();
							u.add(el);
							res.put(c, u);
						}
					}
				}
			}
			for (Entry<Contenedor, List<Elemento>> m : res.entrySet()) {
				System.out.println(m.getKey() + ": " + m.getValue() + "\n");
			}
			System.out.println("Número de contendores llenos: " + res.keySet().size());
			System.out.println(String2.linea());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			DatosEj4.iniDatos("././ficheros/PI5Ej4DatosEntrada3.txt");
			AuxGrammar.generate(DatosEj4.class, "modelos/Ejercicio4.lsi", "gurobi_models/Ejercicio4_3.lp");
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/Ejercicio4_3.lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println(gs.toString((s, d) -> d > 0.));
			System.out.println(String2.linea());
			Map<String, Double> e = gs.values;
			List<Entry<String, Double>> ls = e.entrySet().stream().sorted(Comparator.comparing(x -> x.getKey()))
					.toList();
			List<Elemento> elementos = DatosEj4.elementos;
			List<Contenedor> contenedores = DatosEj4.contenedores;
			elementos.sort(Comparator.comparing(y -> y.getPosicion()));
			contenedores.sort(Comparator.comparing(c -> c.nombre()));
			System.out.println(elementos);
			System.out.println(contenedores);
			Map<Contenedor, List<Elemento>> res = new HashMap<>();
			for (Entry<String, Double> ex : ls) {
				if (ex.getValue() > 0) {
					String[] b = ex.getKey().split("_");
					if (b.length > 2) {
						Contenedor c = contenedores.get(Integer.valueOf(b[2]));
						Elemento el = elementos.get(Integer.valueOf(b[1]));
						if (res.containsKey(c)) {
							res.get(c).add(el);
						} else {
							List<Elemento> u = List2.empty();
							u.add(el);
							res.put(c, u);
						}
					}
				}
			}
			for (Entry<Contenedor, List<Elemento>> m : res.entrySet()) {
				System.out.println(m.getKey() + ": " + m.getValue() + "\n");
			}
			System.out.println("Número de contendores llenos: " + res.keySet().size());
			System.out.println(String2.linea());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

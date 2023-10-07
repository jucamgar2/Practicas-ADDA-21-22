package test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import Ejercicios.DatosEj3;
import us.lsi.common.Map2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;
import util.Producto;

public class TestE3 {

	public static void main(String[] args) {
		try {
			DatosEj3.iniDatos("././ficheros/PI5Ej3DatosEntrada1.txt");
			AuxGrammar.generate(DatosEj3.class, "modelos/Ejercicio3.lsi", "gurobi_models/Ejercicio3_1.lp");
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/Ejercicio3_1.lp");
			Locale.setDefault(new Locale("en", "US"));
			//System.out.println(gs.toString((s,d)->d>0.));
			System.out.println("Beneficio: " + gs.objVal);
			List<Entry<String,Double>> ls = gs.values.entrySet().stream().sorted(Comparator.comparing(x->x.getKey())).toList();
			List<Producto> productos = DatosEj3.productos;
			productos.sort(Comparator.comparing(x->x.nombre()));
			Map<Producto,Double> res = Map2.empty();
			for(Entry<String,Double> ex:ls) {
				if(ex.getValue()>0) {
					String[]  b = ex.getKey().split("_");
					Producto p = productos.get(Integer.valueOf(b[1]));
					res.put(p, ex.getValue());
				}
			}
			System.out.println("Productos seleccionados: \n");
			for(Entry<Producto,Double> t : res.entrySet()) {
				System.out.println(t.getKey()+ " = " + t.getValue());
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
		try {
			DatosEj3.iniDatos("././ficheros/PI5Ej3DatosEntrada2.txt");
			AuxGrammar.generate(DatosEj3.class, "modelos/Ejercicio3.lsi", "gurobi_models/Ejercicio3_2.lp");
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/Ejercicio3_2.lp");
			Locale.setDefault(new Locale("en", "US"));
			//System.out.println(gs.toString((s,d)->d>0.));
			System.out.println("Beneficio: " + gs.objVal);
			List<Entry<String,Double>> ls = gs.values.entrySet().stream().sorted(Comparator.comparing(x->x.getKey())).toList();
			List<Producto> productos = DatosEj3.productos;
			productos.sort(Comparator.comparing(x->x.nombre()));
			Map<Producto,Double> res = Map2.empty();
			for(Entry<String,Double> ex:ls) {
				if(ex.getValue()>0) {
					String[]  b = ex.getKey().split("_");
					Producto p = productos.get(Integer.valueOf(b[1]));
					res.put(p, ex.getValue());
				}
			}
			System.out.println("Productos seleccionados: \n");
			for(Entry<Producto,Double> t : res.entrySet()) {
				System.out.println(t.getKey()+ " = " + t.getValue());
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
		try {
			DatosEj3.iniDatos("././ficheros/PI5Ej3DatosEntrada3.txt");
			AuxGrammar.generate(DatosEj3.class, "modelos/Ejercicio3.lsi", "gurobi_models/Ejercicio3_3.lp");
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/Ejercicio3_3.lp");
			Locale.setDefault(new Locale("en", "US"));
			//System.out.println(gs.toString((s,d)->d>0.));
			System.out.println("Beneficio: " + gs.objVal);
			List<Entry<String,Double>> ls = gs.values.entrySet().stream().sorted(Comparator.comparing(x->x.getKey())).toList();
			List<Producto> productos = DatosEj3.productos;
			productos.sort(Comparator.comparing(x->x.getPosicion()));
			System.out.println(productos);
			Map<Producto,Double> res = Map2.empty();
			for(Entry<String,Double> ex:ls) {
				if(ex.getValue()>0) {
					String[]  b = ex.getKey().split("_");
					Producto p = productos.get(Integer.valueOf(b[1]));
					res.put(p, ex.getValue());
				}
			}
			System.out.println("Productos seleccionados: \n");
			for(Entry<Producto,Double> t : res.entrySet()) {
				System.out.println(t.getKey()+ " = " + t.getValue());
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
	}

}

package Ejercicios;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import org.jgrapht.Graph;
import us.lsi.common.List2;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import util.Carretera;
import util.Ciudad;

public class DatosEj5 {
	public static Integer n;
	public static Integer m;
	public static List<Ciudad> ciudades;
	public static List<Carretera> carreteras;
	public static Ciudad origen;
	public static Ciudad destino;
	public static Graph<Ciudad,Carretera> g;
	public static Predicate<Ciudad> pCi;
	public static Predicate<Carretera> pCa;
	public static Integer getN() {
		return n;
	}
	public static Integer getM() {
		return m;
	}
	public static void iniDatos(String datos) {
		ciudades = List2.empty();
		carreteras = List2.empty();
		Graph<Ciudad,Carretera> gr = GraphsReader.newGraph(datos,
				Ciudad::ofFormat,
				Carretera::ofFormat, 
				Graphs2::simpleWeightedGraph,
				Carretera::getKm);
		g= gr;
		ciudades = g.vertexSet().stream().sorted(Comparator.comparing(x->x.nombre())).toList();;
		carreteras = g.edgeSet().stream().sorted(Comparator.comparing(x->x.source())).toList();;
		n = ciudades.size();
		m = ciudades.size();
	}
	

}

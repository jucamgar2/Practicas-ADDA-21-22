package Tests;

import java.util.Optional;
import org.jgrapht.GraphPath;
import Ejercicio3.DatosEj3;
import Ejercicio3.Ej3Edge;
import Ejercicio3.Ej3Heuristica;
import Ejercicio3.Ej3Vertex;
import Soluciones.SolucionEj3;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;

public class TestEj3PD {

	public static void main(String[] args) {
		TestPD("././ficheros/PI6Ej3DatosEntrada1.txt");
		TestPD("././ficheros/PI6Ej3DatosEntrada2.txt");

	}

	private static void TestPD(String string) {
		DatosEj3.iniDatos(string);
		
		Ej3Vertex start = Ej3Vertex.initialVertex();
		
		EGraph<Ej3Vertex,Ej3Edge> graph = SimpleVirtualGraph.sum(start,
				Ej3Vertex.goal(), x->x.weight(),Ej3Vertex.constraint());
		DynamicProgrammingReduction<Ej3Vertex,Ej3Edge> pdr = 
				DynamicProgrammingReduction.of(graph,
						Ej3Heuristica::heuristica,
						PDType.Max);
		
		Optional<GraphPath<Ej3Vertex,Ej3Edge>> gp = pdr.search();
		System.out.println(gp);
		if(gp.isPresent()) {
			SolucionEj3 s = Ej3Vertex.getSolucion(gp.get());
			System.out.println(s);
		}else {
			System.out.println("NO SE PUDO ENCONTRAR SOLUCIÓN");
		}
	}

}

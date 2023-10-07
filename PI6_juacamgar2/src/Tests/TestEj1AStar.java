package Tests;

import java.util.Optional;
import org.jgrapht.GraphPath;
import Ejercicio1.DatosEj1;
import Ejercicio1.Ej1Edge2;
import Ejercicio1.Ej1Heuristica2;
import Ejercicio1.Ej1Vertex2;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.AStar.AStarType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;

public class TestEj1AStar {

	public static void main(String[] args) {
		TestAS("././ficheros/PI6Ej1DatosEntrada1.txt");
		TestAS("././ficheros/PI6Ej1DatosEntrada2.txt");
	}

	private static void TestAS(String string) {
		DatosEj1.iniDatos(string);
		
		Ej1Vertex2 start = Ej1Vertex2.initialVertex();
		EGraph<Ej1Vertex2,Ej1Edge2> graph = SimpleVirtualGraph.sum(start,
				Ej1Vertex2.goal(),
				x->x.weight(),
				Ej1Vertex2.constraint());
		
		AStar<Ej1Vertex2,Ej1Edge2> aStar = AStar.of(graph,
				Ej1Heuristica2::heuristic,
				AStarType.Max);
		
		Optional<GraphPath<Ej1Vertex2,Ej1Edge2>> gp = aStar.search();
		
		System.out.println(gp);
		if(gp.isPresent()) {
			System.out.println(Ej1Vertex2.getSolucion(gp.get()));
		}else {
			System.out.println("NO SE PUDO ENCONTRAR SOLUCIÓN");
		}
		
	}

}

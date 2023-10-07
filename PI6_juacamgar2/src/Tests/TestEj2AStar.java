package Tests;

import java.util.Optional;
import org.jgrapht.GraphPath;
import Ejercicio2.DatosEj2;
import Ejercicio2.Ej2Edge;
import Ejercicio2.Ej2Heuristica;
import Ejercicio2.Ej2Vertex;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.AStar.AStarType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;

public class TestEj2AStar {

	public static void main(String[] args) {
		TestAS("././ficheros/PI6Ej2DatosEntrada1.txt");
		TestAS("././ficheros/PI6Ej2DatosEntrada2.txt");
	}
	public static void TestAS(String fichero) {

		
		DatosEj2.iniDatos(fichero);
		
		Ej2Vertex start = Ej2Vertex.initialVertex();
		
		EGraph<Ej2Vertex,Ej2Edge> graph = SimpleVirtualGraph.sum(start,
				Ej2Vertex.goal(), x->x.weight(),Ej2Vertex.constraint());
		
		AStar<Ej2Vertex,Ej2Edge> aStar = AStar.of(graph,
				Ej2Heuristica::heuristic,AStarType.Max);
		
		Optional<GraphPath<Ej2Vertex,Ej2Edge>> gp = aStar.search();
		
		System.out.println(gp);
		if(gp.isPresent()) {
			System.out.println(Ej2Vertex.getSolucion(gp.get()));
		}else {
			System.out.println("No se pudo encontrar solución");
		}
	}

}

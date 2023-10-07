package Tests;

import java.util.Optional;
import org.jgrapht.GraphPath;
import Ejercicio4.DatosEj4;
import Ejercicio4.Ej4Edge2;
import Ejercicio4.Ej4Heuristica;
import Ejercicio4.Ej4Vertex2;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.AStar.AStarType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;

public class TestEj4AStar {
	public static void main(String[] args) {
		TestAS("././ficheros/PI6Ej4DatosEntrada1.txt");
		TestAS("././ficheros/PI6Ej4DatosEntrada2.txt");
	}

	private static void TestAS(String string) {
		DatosEj4.iniDatos(string);
		
		Ej4Vertex2 start = Ej4Vertex2.initialVertex();
		EGraph<Ej4Vertex2,Ej4Edge2> graph = SimpleVirtualGraph.sum(start,
				Ej4Vertex2.goal(),
				x->x.weight(),
				Ej4Vertex2.constraint());
		
		AStar<Ej4Vertex2,Ej4Edge2> aStar = AStar.of(graph,
				Ej4Heuristica::heuristic,
				AStarType.Max);
		Optional<GraphPath<Ej4Vertex2,Ej4Edge2>> gp = aStar.search();
		System.out.println(gp);
		if(gp.isPresent()) {
			System.out.println(Ej4Vertex2.getSolucion(gp.get()));
		}else {
			System.out.println("NO SE PUDO ENCONTRAR SOLUCIÓN");
		}
	}

}

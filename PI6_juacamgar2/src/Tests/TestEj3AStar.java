package Tests;

import java.util.Optional;
import org.jgrapht.GraphPath;
import Ejercicio3.DatosEj3;
import Ejercicio3.Ej3Edge;
import Ejercicio3.Ej3Heuristica;
import Ejercicio3.Ej3Vertex;
import Soluciones.SolucionEj3;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.AStar.AStarType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;

public class TestEj3AStar {

	public static void main(String[] args) {
		TestAS("././ficheros/PI6Ej3DatosEntrada1.txt");
		TestAS("././ficheros/PI6Ej3DatosEntrada2.txt");
	}

	private static void TestAS(String string) {
		DatosEj3.iniDatos(string);
		
		Ej3Vertex start = Ej3Vertex.initialVertex();
		
		EGraph<Ej3Vertex,Ej3Edge> graph = SimpleVirtualGraph.sum(start,
				Ej3Vertex.goal(), x->x.weight(),Ej3Vertex.constraint());
		
		AStar<Ej3Vertex,Ej3Edge> aStar = 
				AStar.of(graph,
						Ej3Heuristica::heuristica,
						AStarType.Max);
		Optional<GraphPath<Ej3Vertex,Ej3Edge>> gp = aStar.search();
		
		
		if(gp.isPresent()) {
			SolucionEj3 s = Ej3Vertex.getSolucion(gp.get());
			System.out.println(s);
		}else {
			System.out.println("No se pudo encontrar solución");
		}
	
	}

}

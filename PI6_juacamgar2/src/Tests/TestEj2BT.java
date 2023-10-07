package Tests;

import Ejercicio2.DatosEj2;
import Ejercicio2.Ej2Edge;
import Ejercicio2.Ej2Heuristica;
import Ejercicio2.Ej2Vertex;
import Soluciones.SolucionEj2;
import us.lsi.graphs.alg.BackTracking;
import us.lsi.graphs.alg.BackTracking.BTType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;

public class TestEj2BT {

	public static void main(String[] args) {
		TestBT("././ficheros/PI6Ej2DatosEntrada1.txt");
		TestBT("././ficheros/PI6Ej2DatosEntrada2.txt");
	}
	public static void TestBT(String fichero) {
		
		DatosEj2.iniDatos(fichero);
		
		Ej2Vertex start = Ej2Vertex.initialVertex();
		
		EGraph<Ej2Vertex,Ej2Edge> graph = SimpleVirtualGraph.sum(start,
				Ej2Vertex.goal(), x->x.weight(),Ej2Vertex.constraint());
		
		BackTracking<Ej2Vertex,Ej2Edge, SolucionEj2> bt = 
				BackTracking.of(graph,
						Ej2Heuristica::heuristic,
						Ej2Vertex::getSolucion,
						BTType.Max);
		bt.withGraph = true;
		bt.search();
		if(bt.getSolution().isPresent()) {
			System.out.println(bt.getSolution());
		}else {
			System.out.println("No se pudo encontrar solución");
		}
	}
}

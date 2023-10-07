package Tests;

import Ejercicio3.DatosEj3;
import Ejercicio3.Ej3Edge;
import Ejercicio3.Ej3Heuristica;
import Ejercicio3.Ej3Vertex;
import Soluciones.SolucionEj3;
import us.lsi.graphs.alg.BackTracking;
import us.lsi.graphs.alg.BackTracking.BTType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;

public class TestEj3BT {

	public static void main(String[] args) {
		TestBT("././ficheros/PI6Ej3DatosEntrada1.txt");
		TestBT("././ficheros/PI6Ej3DatosEntrada2.txt");
	}

	private static void TestBT(String string) {
		
		DatosEj3.iniDatos(string);
		
		Ej3Vertex start = Ej3Vertex.initialVertex();
		
		EGraph<Ej3Vertex,Ej3Edge> graph = SimpleVirtualGraph.sum(start,
				Ej3Vertex.goal(), x->x.weight(),Ej3Vertex.constraint());
		
		BackTracking<Ej3Vertex, Ej3Edge, SolucionEj3> bt = 
				BackTracking.of(graph,
						Ej3Heuristica::heuristica,
						Ej3Vertex::getSolucion,
						BTType.Max);
		
		bt.search();
		if(bt.getSolution().isPresent()) {
			System.out.println(bt.getSolution());
		}else {
			System.out.println("NO SE PUDO ENCONTRAR SOLUCIÓN");
		}
	}

}

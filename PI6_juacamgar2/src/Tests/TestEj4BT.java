package Tests;

import Ejercicio4.DatosEj4;
import Ejercicio4.Ej4Edge2;
import Ejercicio4.Ej4Heuristica;
import Ejercicio4.Ej4Vertex2;
import Soluciones.SolucionEj4;
import us.lsi.graphs.alg.BackTracking;
import us.lsi.graphs.alg.BackTracking.BTType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;

public class TestEj4BT {
	public static void main(String[] args) {
		TestBT("././ficheros/PI6Ej4DatosEntrada1.txt");
		TestBT("././ficheros/PI6Ej4DatosEntrada2.txt");
	}
	

	private static void TestBT(String string) {
		DatosEj4.iniDatos(string);
		
		Ej4Vertex2 start = Ej4Vertex2.initialVertex();
		
		EGraph<Ej4Vertex2,Ej4Edge2> graph = SimpleVirtualGraph.sum(start,
				Ej4Vertex2.goal(),
				x->x.weight(),
				Ej4Vertex2.constraint());
		
		BackTracking<Ej4Vertex2,Ej4Edge2,SolucionEj4> bt = 
				BackTracking.of(graph,
						Ej4Heuristica::heuristic,
						Ej4Vertex2::getSolucion,
						BTType.Max);
		
		bt.search();
		if(bt.getSolution().isPresent()) {
			System.out.println(bt.getSolution());
		}else {
			System.out.println("NO SE PUDO ENCONTRAR SOLUCIÓN");
		}
	}
	
}

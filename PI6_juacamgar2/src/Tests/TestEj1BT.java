package Tests;

import Ejercicio1.DatosEj1;
import Ejercicio1.Ej1Edge2;
import Ejercicio1.Ej1Heuristica2;
import Ejercicio1.Ej1Vertex2;
import Soluciones.SolucionEj1;
import us.lsi.graphs.alg.BackTracking;
import us.lsi.graphs.alg.BackTracking.BTType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;

public class TestEj1BT {
	public static void main(String[] args) {
		TestBT("././ficheros/PI6Ej1DatosEntrada1.txt");
		TestBT("././ficheros/PI6Ej1DatosEntrada2.txt");
	}

	private static void TestBT(String string) {
		DatosEj1.iniDatos(string);
		
		Ej1Vertex2 satrt = Ej1Vertex2.initialVertex();
		
		EGraph<Ej1Vertex2,Ej1Edge2> graph = SimpleVirtualGraph.sum(satrt,
				Ej1Vertex2.goal(),x->x.weight(),Ej1Vertex2.constraint());
		
		BackTracking<Ej1Vertex2,Ej1Edge2,SolucionEj1> bt = 
				BackTracking.of(graph,
						Ej1Heuristica2::heuristic,
						Ej1Vertex2::getSolucion,
						BTType.Max);
		
		bt.withGraph= true;
		bt.search();
		if(bt.getSolution().isPresent()) {
			System.out.println(bt.getSolution());
		}else {
			System.out.println("NO SE PUDO ECONTRAR SOLUCIÓN");
		}
	}

}

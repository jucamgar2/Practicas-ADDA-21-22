package Tests;

import java.util.Optional;
import org.jgrapht.GraphPath;
import Ejercicio4.DatosEj4;
import Ejercicio4.Ej4Edge2;
import Ejercicio4.Ej4Heuristica;
import Ejercicio4.Ej4Vertex2;
import Soluciones.SolucionEj4;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;

public class TestEj4PD {
	public static void main(String[] args) {
		TestPD("././ficheros/PI6Ej4DatosEntrada1.txt");
		TestPD("././ficheros/PI6Ej4DatosEntrada2.txt");
	}

	private static void TestPD(String string) {
		DatosEj4.iniDatos(string);
		Ej4Vertex2 start = Ej4Vertex2.initialVertex();
		
		EGraph<Ej4Vertex2,Ej4Edge2> graph = 
				SimpleVirtualGraph.sum(start,
						Ej4Vertex2.goal(),
						x->x.weight(),
						Ej4Vertex2.constraint());
	
		DynamicProgrammingReduction<Ej4Vertex2,Ej4Edge2> pdr = 
				DynamicProgrammingReduction.of(graph,
						Ej4Heuristica::heuristica,
						PDType.Max);
		
		Optional<GraphPath<Ej4Vertex2,Ej4Edge2>> gp = pdr.search();
		System.out.println(gp);
		
		if(gp.isPresent()) {
			SolucionEj4 s = Ej4Vertex2.getSolucion(gp.get());
			System.out.println(s); 
		}else {
			System.out.println("NO SE PUDO ENCONTRAR SOLUCIÓN");
		}
	}

}

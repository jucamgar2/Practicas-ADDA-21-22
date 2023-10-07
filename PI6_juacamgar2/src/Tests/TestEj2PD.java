package Tests;

import java.util.Optional;

import org.jgrapht.GraphPath;

import Ejercicio2.DatosEj2;
import Ejercicio2.Ej2Edge;
import Ejercicio2.Ej2Heuristica;
import Ejercicio2.Ej2Vertex;
import Soluciones.SolucionEj2;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;

public class TestEj2PD {

	public static void main(String[] args) {
		TestPD("././ficheros/PI6Ej2DatosEntrada1.txt");
		TestPD("././ficheros/PI6Ej2DatosEntrada2.txt");
	}
	public static void TestPD(String fichero) {
		DatosEj2.iniDatos(fichero);
		
		Ej2Vertex start = Ej2Vertex.initialVertex();
		
		EGraph<Ej2Vertex, Ej2Edge> graph = 
				SimpleVirtualGraph.sum(start,
						Ej2Vertex.goal(),
						x->x.weight(),
						Ej2Vertex.constraint());
		DynamicProgrammingReduction<Ej2Vertex, Ej2Edge> pdr = 
				DynamicProgrammingReduction.of(graph,
						Ej2Heuristica::heuristic,
						PDType.Max);
		
		Optional<GraphPath<Ej2Vertex,Ej2Edge>> gp = pdr.search();
		System.out.println(gp);
		
		if(gp.isPresent()) {
			SolucionEj2 s = Ej2Vertex.getSolucion(gp.get());
			System.out.println(s);
		}else {
			System.out.println("NO SE PUDO ENCONTRAR SOLUCIÓN");
		}
	}
}

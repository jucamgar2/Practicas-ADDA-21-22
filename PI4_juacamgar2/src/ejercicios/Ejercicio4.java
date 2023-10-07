package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;

import util.Persona;

public class Ejercicio4 {
	
	public static List<Persona> Ejercicio4A(Graph<Persona,DefaultEdge> grafo) {
		ConnectivityInspector<Persona, DefaultEdge> conectividad = new ConnectivityInspector<>(grafo);
		// si el grafo es conexo, desde una persona cualquiera se puede llegar a cualquier otra, 
		//por lo que todas tienen riesgo de infectarse
		List<Persona> sospechosos = new ArrayList<>();
		if(conectividad.isConnected()) {
			sospechosos.addAll(grafo.vertexSet());
		}else {
			//  si el grafo no es conexo, miramos las distintas cadenas de contagios
			List<Set<Persona>> cadenas = conectividad.connectedSets();
			for(Set<Persona> s:cadenas) {
				// si en una cadena hay un positivo, todas las personas de la cadena corren riesgo de infectarse
				// por lo que añadimos a todas las personas de la cadena a la lista de sospechosos se infectarse
				if(s.stream().map(x->x.estado()).anyMatch(x->x==1)) {
					sospechosos.addAll(s);
				}
				// si en una cadena no hay ningún contagiado, nigún integrante de la cadena es sospechoso 
			}
		}
		return sospechosos;
	}
	// Este problema he decicido afrontarlo de la siguiente manera, dado un grafo y una persona, si desde esa persona
	// en el grafo se puede llegar a un positivo, entonces calculamos cual es el positivo que tiene más cerca y el
	// número de aristas que hay entre ellos es el número de días que tardará en contagiarse esta persona
	public static Integer Ejercicio4B(Graph<Persona,DefaultEdge> grafo,Persona p) {
		// En el supuesto de que un alumno no esté en riesgo de contagiarse, en el grafo se mostrará que le hacen falta 
		// 100 dias para contagiarse, por defecto.
		ConnectivityInspector<Persona,DefaultEdge> con = new ConnectivityInspector<>(grafo);
		List<Persona> positivos = grafo.vertexSet().stream().filter(x->x.estado()==1).toList();
		Integer camino = 100;
		for(Persona x: positivos) {
			if(con.pathExists(x, p)) {
				camino = CalculaMejorCamino(grafo,x,p,camino);
			}
		}
		return camino;
	}
	private static Integer CalculaMejorCamino(Graph<Persona, DefaultEdge> grafo, Persona x, Persona p, Integer camino) {
		GraphPath<Persona, DefaultEdge> r = new DijkstraShortestPath<>(grafo).getPath(x, p);
		if(camino>r.getEdgeList().size()) {
			camino = r.getEdgeList().size();
		}
		return camino;
	}


}

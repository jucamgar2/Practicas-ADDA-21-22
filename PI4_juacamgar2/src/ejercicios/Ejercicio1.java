package ejercicios;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm.Coloring;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.util.NeighborCache;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.common.Pair;

import us.lsi.graphs.views.SubGraphView;
import util.Colaboracion;
import util.Investigador;

public class Ejercicio1 {
	public static Graph<Investigador,Colaboracion> Ejercicio1A(Graph<Investigador,Colaboracion> grafo,Predicate<Investigador> pI,Predicate<Colaboracion>pC){
		return SubGraphView.of(grafo,pI,pC); 
	}
	
	public static List<Investigador> Ejercicio1B(Graph<Investigador,Colaboracion> grafo ){
		return grafo.vertexSet().stream()
				.sorted(Comparator.comparing(grafo::degreeOf).reversed())
				.limit(5)
				.toList();
		
	}
	public static Map<Investigador,List<Investigador>> Ejercicio1C(Graph<Investigador,Colaboracion> grafo){
		List<Investigador> vertices = grafo.vertexSet().stream().toList();
		Integer i = 0;
		Map<Investigador,List<Investigador>> res = new HashMap<>();
		NeighborCache<Investigador, Colaboracion> r = new NeighborCache<>(grafo);
		while(i<vertices.size()) {
			Investigador clave = vertices.get(i);
			if(res.containsKey(clave)) {
				res.get(clave).addAll(r.neighborListOf(clave));
				res.get(clave).sort(Comparator.comparingDouble((Investigador x)->grafo.getEdge(x, clave).getColaboraciones()).reversed());
			}else {
				List<Investigador> aux = new ArrayList<>();
				aux.addAll(r.neighborsOf(clave));
				aux.sort(Comparator.comparingDouble((Investigador x)->grafo.getEdge(clave, x).getColaboraciones()).reversed());
				res.put(clave, aux);
			}
			i++;
		}
		return res;
	}
	public static Pair<Investigador, Investigador> Ejercicio1D(Graph<Investigador,Colaboracion>grafo){
		Integer recorrido = 0;
		Integer mejorC = 0;
		List<Investigador> investigadores = grafo.vertexSet().stream().toList();
		Integer i = 0;
		Pair<Investigador,Investigador> res = Pair.of(null, null);
		while(i<investigadores.size()) {
			Integer a =0;
			while(a<investigadores.size()) {
				recorrido = DijkstraShortestPath.findPathBetween(grafo, investigadores.get(i), investigadores.get(a)).getLength();
				if(recorrido>mejorC) {
					mejorC= recorrido;
					res = Pair.of(investigadores.get(i), investigadores.get(a));
				}
			a++;
			}
		i++;
		}
	return res;
	}
	public static Coloring<Investigador> Ejercicio1E(Graph<Investigador,Colaboracion> grafo){
		Graph<Investigador,Colaboracion> g= new SimpleWeightedGraph<>(null,null); 
		Graphs.addGraph(g, grafo);
		for(Investigador a:g.vertexSet()){
			for(Investigador b:g.vertexSet()) {
				if(!a.equals(b)&&g.getEdge(a, b)==null&&a.uni().equals(b.uni())) {
					Colaboracion c = Colaboracion.de(a, b);
					g.addEdge(a, b, c);
				}
			}
		}
		GreedyColoring<Investigador, Colaboracion> alg = new GreedyColoring<>(g);
		return alg.getColoring();
	}
	
}

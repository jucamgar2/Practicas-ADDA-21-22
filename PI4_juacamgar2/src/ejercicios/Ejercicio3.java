package ejercicios;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.tour.HeldKarpTSP;
import org.jgrapht.graph.SimpleWeightedGraph;

import util.Calle;
import util.Interseccion;

public class Ejercicio3 {
	public static GraphPath<Interseccion,Calle> Ejercicio3_1(SimpleWeightedGraph<Interseccion,Calle> grafo, Interseccion source,Interseccion target){
		GraphPath<Interseccion,Calle> camino = DijkstraShortestPath.findPathBetween(grafo, source, target);
		return camino;
	}
	public static GraphPath<Interseccion,Calle> Ejercicio3_2(SimpleWeightedGraph<Interseccion,Calle> grafo){
		GraphPath<Interseccion,Calle> res =  new HeldKarpTSP<Interseccion,Calle>().getTour(grafo);
		return res;
	}
	//Compruebo si el grafo es conexo, si lo es, se podrá accerder a todos los vertices empezando por uno cualquiera
	public static Boolean Ejercicio3_3A(Graph<Interseccion,Calle> grafo) {
		ConnectivityInspector<Interseccion, Calle> con = new ConnectivityInspector<>(grafo);
		return con.isConnected();
	}
	//Esta función auxiliar la uso para que calcule el interes de un recorrido, para en un futuro poder seleccionar el recorrido que tenga más interes
	public static Integer calculaInteresDelRecorrido(Set<Interseccion> lista) {
		List<Interseccion> l = lista.stream().toList();
		Integer res = 0;
		Integer i = 0;
		while(i<l.size()) {
			if(l.get(i).monumento()) {
				res += l.get(i).interes();
			}else {
			}
			i++;
		}
		return res;
	}
	//Esta función devuelve el conjunto de intersecciones del recorrido que tenga más interes
	public static Set<Interseccion> Ejercicio3_3B(Graph<Interseccion,Calle> grafo){
		ConnectivityInspector<Interseccion, Calle> con = new ConnectivityInspector<>(grafo);
		List<Set<Interseccion>> res = con.connectedSets();
		return res.stream().max(Comparator.comparing(x->calculaInteresDelRecorrido(x))).get();
		
	}
	//Esta función devuelve el conjunto de calles que unen las intersecciones del conjunto que se le pasa como parametro, lo uso para poder colorear bién en grafo
	public static Set<Calle> Ejercicio3_3B2(Graph<Interseccion,Calle> grafo, Set<Interseccion> conj){
		List<Interseccion> l = conj.stream().toList();
		Set<Calle> res = new HashSet<>();
		Integer i = 0;
		while(i<l.size()) {
			Interseccion a = l.get(i);
			res.addAll(grafo.incomingEdgesOf(a));
			res.addAll(grafo.outgoingEdgesOf(a));
			i++;
		}
		return res;
	}
}

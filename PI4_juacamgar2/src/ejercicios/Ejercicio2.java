package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.TopologicalOrderIterator;
import util.Libro;

public class Ejercicio2 {

	public static List<Libro> Ejercicio2_1(Graph<Libro,DefaultEdge> grafo){
		Map<Libro,Integer> res = new HashMap<Libro,Integer>();
		List<Libro> libros = grafo.vertexSet().stream().toList();
		Integer i =0;
		while(i<libros.size()) {
			BreadthFirstIterator<Libro, DefaultEdge> bfi = new BreadthFirstIterator<>(grafo, libros.get(i));
			List<Libro> Accesibles= new ArrayList<>();
			bfi.forEachRemaining(v->Accesibles.add(v));
			res.put(libros.get(i), Accesibles.size());
			i++;
		}
		Integer masNecesario = res.values().stream().mapToInt(x->x).max().orElse(0);
		return res.entrySet().stream().filter(x->x.getValue() == masNecesario).map(x->x.getKey()).toList();
	}

	public static Boolean Ejercicio2_2(Graph<Libro,DefaultEdge> grafo, List<Libro> libros) {
		TopologicalOrderIterator<Libro,DefaultEdge> toi =new TopologicalOrderIterator<>(grafo);
		List<Libro> librosOrdenTopologico=new ArrayList<>();
		toi.forEachRemaining(v->librosOrdenTopologico.add(v));
		Integer i = 1;
		Boolean res = true;
		while(i<libros.size()-1 && res) {
			List<Libro> alcanzables = new ArrayList<Libro>();
			List<Libro> necesarios = new ArrayList<Libro>();
			necesarios = librosOrdenTopologico.subList(0, librosOrdenTopologico.indexOf(libros.get(i)));
			alcanzables = librosOrdenTopologico.subList(librosOrdenTopologico.indexOf(libros.get(i)), librosOrdenTopologico.size());
			res = necesarios.containsAll(libros.subList(0, i))
					&& alcanzables.containsAll(libros.subList(i, libros.size()));
			i++;
		}
		return res;
	}
	
	public static List<Libro> Ejercicio2_3(Graph<Libro,DefaultEdge> grafo,Libro libro){
		Libro x = libro;
		List<Libro> res = new ArrayList<>();
		res = Ejercicio2_3(grafo,x,res);
		res.remove(x);
		return res;
	}

	private static List<Libro> Ejercicio2_3(Graph<Libro, DefaultEdge> grafo, Libro libro,List<Libro> res) {
		Integer grado = grafo.inDegreeOf(libro);
		if (!res.contains(libro)) {
			res.add(0,libro);
		}else {
			res.remove(libro);
			res.add(0,libro);
		}
		if(grado>0) {
			grafo.incomingEdgesOf(libro).forEach(x->Ejercicio2_3(grafo,grafo.getEdgeSource(x),res));
		}
		return res;
	}
}

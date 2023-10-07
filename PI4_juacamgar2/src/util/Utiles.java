package util;

import java.util.ArrayList;

import java.util.List;
import us.lsi.common.Files2;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;


public class Utiles {
	public static Graph<Investigador,Colaboracion> importar_grafo(String fichero){
		return GraphsReader.newGraph(fichero,
				Investigador::ofFormat,
				Colaboracion::ofFormat,
				Graphs2::simpleWeightedGraph,
				Colaboracion::colaboraciones);
	}
	public static Graph<Interseccion,Calle> importar_grafo2(String fichero){
		return GraphsReader.newGraph(fichero,
				Interseccion::ofFormat,
				Calle::ofFormat,
				Graphs2::simpleGraph);
	}
	public static SimpleWeightedGraph<Interseccion,Calle> importar_gafoDuracion(String fichero){
		return GraphsReader.newGraph(fichero,
				Interseccion::ofFormat,
				Calle::ofFormat,
				Graphs2::simpleWeightedGraph,
				Calle::tiempo);
				
	}
	public static SimpleWeightedGraph<Interseccion,Calle> importar_gafoEsfuerzo(String fichero){
		return GraphsReader.newGraph(fichero,
				Interseccion::ofFormat,
				Calle::ofFormat,
				Graphs2::simpleWeightedGraph,
				Calle::esfuerzo);
	}
	
	
	
	public static Graph<Investigador,Colaboracion> importar_grafoSinPeso(String fichero){
		return GraphsReader.newGraph(fichero,
				Investigador::ofFormat,
				Colaboracion::ofFormat,
				Graphs2::simpleWeightedGraph,
				x->1.);
	}
	public static Graph<Investigador,Colaboracion> importar_grafoSimple(String fichero){
		return GraphsReader.newGraph(fichero,
				Investigador::ofFormat,
				Colaboracion::ofFormat,
				Graphs2::simpleGraph);
	}
	public static SimpleDirectedGraph<Libro,DefaultEdge> importar_grafoDirigido(String fichero){
		SimpleGraph<Libro,Arista> grafo =  GraphsReader.newGraph(fichero,
				Libro::ofFormat,
				Arista::ofFormat,
				Graphs2::simpleGraph);
		SimpleDirectedGraph<Libro,DefaultEdge> res = new SimpleDirectedGraph<Libro,DefaultEdge>(DefaultEdge.class);
		for(Libro l:grafo.vertexSet().stream().toList()) {
			res.addVertex(l);
		}
		for (Arista a : grafo.edgeSet().stream().toList()) {
			res.addEdge(a.source(), a.destino());
		}
		return res;
		
	}
	public static List<List<Libro>> importaListaLibros(String fichero){
		List<String> res = Files2.linesFromFile(fichero);
		List<List<Libro>> o = new ArrayList<>();
		for (String i: res) {
			List<Libro> libros = new ArrayList<>();
			String r = i.substring(6).replace('[', ' ').replace(']', ' ').strip();
			String [] ro = r.split(",");
			int b = 0;
			while(b<ro.length) {
				libros.add(Libro.ofFormat(ro[b]));
				b++;
			}
			o.add(libros);
		}
		return o;
	}
	///Estas dos funciónes importan los ficheros del ejercicio4, por un lado importo los positivos y por otro los sanos
	public static List<Persona> positivos (String fichero){
		List<String> lineas = Files2.linesFromFile(fichero);
		List<Persona> res = new ArrayList<>();
		int i = 0;
		while(i<lineas.size()) {
			if(lineas.get(i).charAt(0) == '+') {
				res.add(Persona.Positivo(lineas.get(i)));
			}
		i++;
		}
		return res;
	}
	public static List<Persona> negativos (String fichero){
		List<String> lineas = Files2.linesFromFile(fichero);
		List<Persona> res = new ArrayList<>();
		int i = 0;
		while(i<lineas.size()) {
			if(lineas.get(i).charAt(0) == '-') {
				res.add(Persona.Negativo(lineas.get(i)));
			}
		i++;
		}
		return res;
	}
	
}

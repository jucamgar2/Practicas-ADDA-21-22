package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm.Coloring;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import ejercicios.Ejercicio1;
import ejercicios.Ejercicio2;
import ejercicios.Ejercicio3;
import ejercicios.Ejercicio4;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Shape;
import us.lsi.colors.GraphColors.Style;
import us.lsi.common.Pair;
import util.Calle;
import util.Colaboracion;
import util.Contacto;
import util.Interseccion;
import util.Investigador;
import util.Libro;
import util.Persona;
import util.Utiles;

public class Test {

	public static void main(String[] args) {
		testEjercicio1("././ficheros/PI4E1_DatosEntrada.txt");
		testEjercicio2("././ficheros/PI4E2_DatosEntrada1.txt","././ficheros/PI4E2_DatosEntrada2.txt");
		testEjercicio3("././ficheros/PI4E3_DatosEntrada.txt");
		testEjercicio4("././ficheros/PI4E4_DatosEntrada1.txt","././ficheros/PI4E4_DatosEntrada2.txt");
	}
/////////////////////////////////////////////////////////////TEST EJERCICIO4////////////////////////////////////////////////
	private static void testEjercicio4(String string, String string2) {
/////////////////////////////////////////////////////////////APARTADO A ////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////APARTADO B ////////////////////////////////////////////////////
		//En este caso los test de ambos ejercicios estan juntos ya que con el apartado a calculo el coloreado y con el segundo apartado calculo lo que tardarán en infectarse, mostrandolo todo en el mismo grafo
		List<Persona> personas = Utiles.positivos(string);
		personas.addAll(Utiles.negativos(string));
		Graph<Persona,DefaultEdge> grafo1 = new SimpleGraph<>(DefaultEdge.class);
		Double dFilas1 = 0.7;
		Double dColumnas1 = 0.7;
		for(Persona p:personas) {
			grafo1.addVertex(p);
		}
		for(Persona p: personas) {
			for(Persona x:personas) {
				if(Contacto.getDistancia(p, x,dFilas1,dColumnas1) < 1.2 && x != p) {
					grafo1.addEdge(p,x);
				}
			}
		}
		List<Persona> sospechsosos = Ejercicio4.Ejercicio4A(grafo1);
		GraphColors.toDot(grafo1,
				"././ficheros/Resultado4.dot",
				x->x.x()+", " + x.y( ) + "->" + Ejercicio4.Ejercicio4B(grafo1, x).toString(),
				y->Contacto.getDistancia(grafo1.getEdgeSource(y), grafo1.getEdgeTarget(y),dFilas1,dColumnas1).toString(),
				(Persona x)->x.estado()==1? GraphColors.color(Color.red): sospechsosos.contains(x)? GraphColors.color(Color.orange):GraphColors.color(Color.green),
				y->GraphColors.color(Color.blue));
		List<Persona> personas2 = Utiles.positivos(string2);
		personas2.addAll(Utiles.negativos(string2));
		Graph<Persona,DefaultEdge> grafo2 = new SimpleGraph<>(DefaultEdge.class);
		Double dFilas2 = 1.1;
		Double dColumnas2 = 1.1;
		for(Persona p:personas2) {
			grafo2.addVertex(p);
		}
		for(Persona p:personas2) {
			for(Persona x:personas2) {
				if(Contacto.getDistancia(p, x, dFilas2, dColumnas2)<1.2 && x!=p) {
					grafo2.addEdge(p, x);
				}
			}
		}
		List<Persona> sospechosos2 = Ejercicio4.Ejercicio4A(grafo2);
		GraphColors.toDot(grafo2,
				"././ficheros/Resultado4B.dot",
				x->x.x()+", " + x.y( ) + "->" + Ejercicio4.Ejercicio4B(grafo2, x).toString(),
				y->Contacto.getDistancia(grafo2.getEdgeSource(y), grafo2.getEdgeTarget(y),dFilas2,dColumnas2).toString(),
				(Persona x)->x.estado()==1? GraphColors.color(Color.red): sospechosos2.contains(x)? GraphColors.color(Color.orange):GraphColors.color(Color.green),
				y->GraphColors.color(Color.blue));
	
}
/////////////////////////////////////////////////////////////TEST EJERCICIO3////////////////////////////////////////////////
	private static void testEjercicio3(String string) {
		System.out.println("EJERCICIO 3: ");
/////////////////////////////////////////////////////////////APARTADO A ////////////////////////////////////////////////////
		SimpleWeightedGraph<Interseccion,Calle> grafoDu = Utiles.importar_gafoDuracion(string);
		String a[] = {"7","true","m7","5int"};
		String b[] = {"2","true","m2","3int"};
		String c[] = {"4","true","m4","2int"};
		String d[] = {"9","true","m9","3int"};
		GraphPath<Interseccion,Calle> camino72 = Ejercicio3.Ejercicio3_1(grafoDu,Interseccion.ofFormat(a),Interseccion.ofFormat(b));
		GraphPath<Interseccion,Calle> camino49 = Ejercicio3.Ejercicio3_1(grafoDu,Interseccion.ofFormat(c),Interseccion.ofFormat(d));
		List<Interseccion> intersecciones1 = camino72.getVertexList();
		List<Interseccion> intersecciones2 = camino49.getVertexList();
		List<Calle> calles1 = camino72.getEdgeList();
		List<Calle> calles2 = camino49.getEdgeList();
		GraphColors.toDot(grafoDu,
				"././ficheros/Resultado31A.dot",
				v->v.id().toString()+", " + v.nomMonumento(),
				x->x.tiempo().toString(),
				v->GraphColors.colorIf(Color.yellow, intersecciones1.contains(v)),
				x->GraphColors.colorIf(Color.cyan, calles1.contains(x)));
		GraphColors.toDot(grafoDu,
				"././ficheros/Resultado31B.dot",
				v->v.id().toString()+", " + v.nomMonumento(),
				x->x.tiempo().toString(),
				v->GraphColors.colorIf(Color.yellow, intersecciones2.contains(v)),
				x->GraphColors.colorIf(Color.cyan, calles2.contains(x)));
/////////////////////////////////////////////////////////////APARTADO B ////////////////////////////////////////////////////
		SimpleWeightedGraph<Interseccion,Calle> grafoEs = Utiles.importar_gafoEsfuerzo(string);
		GraphPath<Interseccion,Calle> recorrido = Ejercicio3.Ejercicio3_2(grafoEs);
		List<Interseccion> interseccionesR = recorrido.getVertexList();
		List<Calle> callesR = recorrido.getEdgeList();
		GraphColors.toDot(grafoEs,
				"././ficheros/Resultado32.dot",
				v->v.id().toString()+", " + v.nomMonumento(),
				e->e.esfuerzo().toString(),
				v->GraphColors.colorIf(Color.magenta, interseccionesR.contains(v)),
				e->GraphColors.colorIf(Color.magenta, callesR.contains(e)));
/////////////////////////////////////////////////////////////APARTADO C ////////////////////////////////////////////////////
		String [] c0 = {"1","6","3min","1esf"};
		String [] c3 = {"4","7","6min","2esf"};
		String [] c5 = {"4","6","5min","1esf"};
		String [] c6 = {"5","8","4min","2esf"};
		String [] c9 = {"7","8","6min","1esf"};
		List<Calle> callesB1 = List.of(Calle.ofFormat(c0),Calle.ofFormat(c3),Calle.ofFormat(c5),Calle.ofFormat(c6));
		List<Calle> callesB2 = List.of(Calle.ofFormat(c0),Calle.ofFormat(c3),Calle.ofFormat(c5),Calle.ofFormat(c6),Calle.ofFormat(c9));
		Graph<Interseccion,Calle> grafoT1 = Utiles.importar_grafo2(string);
		grafoT1.removeAllEdges(callesB1);
		Boolean res3_3A = Ejercicio3.Ejercicio3_3A(grafoT1);
		if(res3_3A) {
			System.out.println("Borrando c0,c3,c5,c6, se pueden seguir visitando todos los monumentos a pie empezando por cualquiera de ellos");
		}else {
			System.out.println("No se pueden seguir visitando todos los monumentos a pie empezando por cualquiera de ellos");
			Set<Interseccion> VerticesC= Ejercicio3.Ejercicio3_3B(grafoT1);
			Set<Calle> EdgesC = Ejercicio3.Ejercicio3_3B2(grafoT1, VerticesC);
			GraphColors.toDot(grafoT1,
					"././ficheros/Resultado33T1.dot",
					v->v.id().toString()+", " + v.nomMonumento() + "int: " +v.interes(),
					x->" " ,
					v->GraphColors.colorIf(Color.yellow,VerticesC.contains(v)),
					x->GraphColors.colorIf(Color.cyan, EdgesC.contains(x)));
			
		}
		Graph<Interseccion,Calle> grafoT2 = Utiles.importar_grafo2(string);
		grafoT2.removeAllEdges(callesB2);
		Boolean res3_3B = Ejercicio3.Ejercicio3_3A(grafoT2);
		if(res3_3B) {
			System.out.println("Boraando c0,c3,c5,c6,c9, se pueden seguir visitando todos los monumentos a pie empezando por cualquiera de ellos");
		}else {
			System.out.println("No se pueden seguir visitando todos los monumentos a pie empezando por cualquiera de ellos");
			Set<Interseccion> VerticesC2= Ejercicio3.Ejercicio3_3B(grafoT2);
			Set<Calle> EdgesC2 = Ejercicio3.Ejercicio3_3B2(grafoT2, VerticesC2);
			GraphColors.toDot(grafoT2,
					"././ficheros/Resultado33T2.dot",
					v->v.id().toString()+", " + v.nomMonumento() + "int: " +v.interes(),
					x->" ",
					v->GraphColors.colorIf(Color.yellow,VerticesC2.contains(v)),
					x->GraphColors.colorIf(Color.cyan, EdgesC2.contains(x)));
		}
	}
/////////////////////////////////////////////////////////////TEST EJERCICIO2////////////////////////////////////////////////
	private static void testEjercicio2(String string, String string2) {
		System.out.println("EJERCICIO2: ");
/////////////////////////////////////////////////////////////APARTADO A ////////////////////////////////////////////////////
		System.out.println("Apartado 1: ");
		//Graph<Libro, Arista> grafo = Utiles.importar_grafoL(string);
		Graph<Libro,DefaultEdge> grafo = Utiles.importar_grafoDirigido(string);
		List<Libro> masNecesarios = Ejercicio2.Ejercicio2_1(grafo);
		System.out.println("Los libros más necesarios son: " + masNecesarios);
		GraphColors.toDot(grafo,
				"././ficheros/Resultado21.dot" ,
				v->v.libro().toString(),
				c->"",
				vertice->GraphColors.colorIf(Color.magenta, masNecesarios.contains(vertice)),
				arista->GraphColors.style(Style.solid));
/////////////////////////////////////////////////////////////APARTADO B ////////////////////////////////////////////////////
		System.out.println("Apartado 2: ");
		List<List<Libro>> listas = Utiles.importaListaLibros(string2);
		for(List<Libro> l :listas) {
			Boolean bu = Ejercicio2.Ejercicio2_2(grafo, l);
			System.out.println("Los libro de la lista: "+l);
			if(bu) {
				System.out.println("Se pueden leer en ese orden ");
			}else {
				System.out.println("No se pueden leer en ese orden");
			}
		}
/////////////////////////////////////////////////////////////APARTADO C ////////////////////////////////////////////////////
		System.out.println("Apartado 3");
		System.out.println("Para leer L3 hay que leer antes:"+ Ejercicio2.Ejercicio2_3(grafo, Libro.ofFormat("L3")));
		System.out.println("Para leer L9 hay que leer antes:"+ Ejercicio2.Ejercicio2_3(grafo, Libro.ofFormat("L9")));
		System.out.println("Para leer L7 hay que leer antes:"+ Ejercicio2.Ejercicio2_3(grafo, Libro.ofFormat("L7")));
	}
/////////////////////////////////////////////////////////////TEST EJERCICIO1////////////////////////////////////////////////
	private static void testEjercicio1(String string) {
		System.out.println("EJERCICIO1:");
/////////////////////////////////////////////////////////////APARTADO A ////////////////////////////////////////////////////
		System.out.println("Apartado A");
		Graph<Investigador,Colaboracion> grafo = Utiles.importar_grafo(string);
		Predicate<Investigador> pI = x->x.getAño()<1982 ||  grafo.edgesOf(x).stream().anyMatch(z->z.getColaboraciones()>5);
		Predicate<Colaboracion> pC = x->x.getColaboraciones()>5;
		Graph<Investigador,Colaboracion> vista = Ejercicio1.Ejercicio1A(grafo, pI, pC);
		Set<Investigador> investigadoresTest = vista.vertexSet();
		Set<Colaboracion> colaboracionesTest = vista.edgeSet();
		GraphColors.toDot(grafo,
				"././ficheros/Resultado1A.dot",
				v->v.id().toString()+", "+v.año().toString(),
				c->c.getColaboraciones().toString(),
				vertice->GraphColors.colorIf(Color.yellow, investigadoresTest.contains(vertice)),
				arista-> GraphColors.colorIf(Color.cyan, colaboracionesTest.contains(arista)));
/////////////////////////////////////////////////////////////APARTADO B ////////////////////////////////////////////////////
		System.out.println("Apartado B");
		List<Investigador> res = Ejercicio1.Ejercicio1B(grafo);
		System.out.println("Los investigadores con más de 5 colaboradores son:" + res);
		GraphColors.toDot(grafo,
				"././ficheros/Resultado1B.dot",
				v->v.id().toString()+", "+v.año().toString(),
				c->c.getColaboraciones().toString(),
				vertice->GraphColors.colorIf(Color.cyan,res.contains(vertice)),
				arista->GraphColors.color(Color.black));
/////////////////////////////////////////////////////////////APARTADO C ////////////////////////////////////////////////////
		System.out.println("Apartado C");
		Map<Investigador,List<Investigador>> r = Ejercicio1.Ejercicio1C(grafo);
		List<Colaboracion> aristasC = new ArrayList<>();
		for(Investigador i:r.keySet()) {
			System.out.println(i.getId()+"---->" + r.get(i));
			aristasC.add(grafo.getEdge(i, r.get(i).get(0)));
		}
		GraphColors.toDot(grafo,
				"././ficheros/Resultado1C.dot",
				v->v.id().toString()+", "+v.año().toString(),
				a->a.getColaboraciones().toString(),
				x->GraphColors.color(Color.black),
				arista->GraphColors.colorIf(Color.magenta,aristasC.contains(arista)));
/////////////////////////////////////////////////////////////APARTADO D ////////////////////////////////////////////////////		
		System.out.println("Apartado D");
		Graph<Investigador,Colaboracion> grafoSinPeso = Utiles.importar_grafoSinPeso(string);
		Pair<Investigador,Investigador> res1 = Ejercicio1.Ejercicio1D(grafoSinPeso);
		System.out.println("Los investigadores mas alejado son:  "+  res1);
		List<Colaboracion> caminoA = DijkstraShortestPath.findPathBetween(grafoSinPeso, res1.first(), res1.second()).getEdgeList();
		List<Investigador> caminoV = DijkstraShortestPath.findPathBetween(grafoSinPeso, res1.first(), res1.second()).getVertexList();
		GraphColors.toDot(grafoSinPeso,
				"././ficheros/Resultado1D.dot",
				v->v.id().toString()+", "+v.año().toString(),
				a->"1",
				vertice->GraphColors.colorIf(Color.cyan,caminoV.contains(vertice)),
				arista->GraphColors.colorIf(Color.green,caminoA.contains(arista)));
/////////////////////////////////////////////////////////////APARTADO E ////////////////////////////////////////////////////
		System.out.println("Apartado E");
		Graph<Investigador,Colaboracion> grafoSimple = Utiles.importar_grafoSimple(string);
		Coloring<Investigador> coloring = Ejercicio1.Ejercicio1E(grafoSimple);
		GraphColors.toDot(grafoSimple,
				"././ficheros/Resultado1E.dot",
				v->v.toString(),
				e->"",
				v->GraphColors.all(GraphColors.shape(Shape.box),
										GraphColors.color(coloring.getColors().get(v))),
				e->GraphColors.style(Style.solid)
				);
		
	}

}
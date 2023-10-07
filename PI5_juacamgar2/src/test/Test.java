package test;

import java.io.IOException;



import org.jgrapht.Graph;

import util.Carretera;
import util.Ciudad;

import util.Utiles;

public class Test {

	public static void main(String[] args) throws IOException {
//		testEjercicio1("././ficheros/PI5Ej1DatosEntrada1.txt");
//		testEjercicio1("././ficheros/PI5Ej1DatosEntrada2.txt");
//		testEjercicio1("././ficheros/PI5Ej1DatosEntrada3.txt");
//		testEjercicio2("././ficheros/PI5Ej2DatosEntrada1.txt");
//		testEjercicio2("././ficheros/PI5Ej2DatosEntrada2.txt");
//		testEjercicio2("././ficheros/PI5Ej2DatosEntrada3.txt");
//		testEjercicio3("././ficheros/PI5Ej3DatosEntrada1.txt");
//		testEjercicio3("././ficheros/PI5Ej3DatosEntrada2.txt");
//		testEjercicio3("././ficheros/PI5Ej3DatosEntrada3.txt");
//		testEjercicio4("././ficheros/PI5Ej4DatosEntrada1.txt");
//		testEjercicio4("././ficheros/PI5Ej4DatosEntrada2.txt");
//		testEjercicio4("././ficheros/PI5Ej4DatosEntrada3.txt");
		testEjercicio5("././ficheros/PI5Ej5DatosEntrada1.txt");
		testEjercicio5("././ficheros/PI5Ej5DatosEntrada2.txt");
		testEjercicio5("././ficheros/PI5Ej5DatosEntrada3.txt");
	}

	private static void testEjercicio5(String string) {
		Graph<Ciudad,Carretera> grafo = Utiles.importaGrafo(string);
		System.out.println(grafo.vertexSet()+ "---_>"+grafo.edgeSet());
		
	}

	

}

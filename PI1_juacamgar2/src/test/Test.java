package test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import util.Utiles;
import ejercicios.Ejercicio1;
import ejercicios.Ejercicio2;
import ejercicios.Ejercicio3;
import ejercicios.Ejercicio4;

public class Test {

	public static void main(String[] args) {
		testEjercicio1("././ficheros/PI1E1_DatosEntrada.txt");
		testEjercicio2("././ficheros/PI1E2_DatosEntrada1.txt");
		testEjercicio2("././ficheros/PI1E2_DatosEntrada2.txt");
		testEjercicio3("././ficheros/PI1E3_DatosEntrada.txt");
		testEjercicio4("././ficheros/PI1E4_DatosEntrada.txt");
	}
	private static void testEjercicio4(String string) {
		System.out.println("EJERCICIO4: ");
		List<List<Double>> lineas = Utiles.LecturaDouble(string);
		Integer i = 0;
		while(i<lineas.size()) {
			System.out.println("Entrada: " + lineas.get(i));
			System.out.println("Iterativa: " + Ejercicio4.ejercicio4Iterativo(lineas.get(i).get(0), lineas.get(i).get(1)));
			System.out.println("Iterativa: " + Ejercicio4.ejercicio4Recursivo(lineas.get(i).get(0), lineas.get(i).get(1)));
			System.out.println("Funcional: " + Ejercicio4.ejercicio4Funcional(lineas.get(i).get(0), lineas.get(i).get(1)));
			System.out.println("");
			i++;
		}
		
	}
	private static void testEjercicio3(String string) {
		System.out.println("EJERCICIO3: ");
		List<List<Integer>> lineas = Utiles.LecturaInteger(string);
		Integer i = 0;
		while(i<lineas.size()) {
			System.out.println("Entrada: " + lineas.get(i));
			System.out.println("Funcional: " + Ejercicio3.ejercicio3Funcional(lineas.get(i).get(0), lineas.get(i).get(1)));
			System.out.println("Iterativa: " + Ejercicio3.ejercicio3Iterativo(lineas.get(i).get(0), lineas.get(i).get(1)));
			System.out.println("Recursivo: " + Ejercicio3.ejercicio3Recursivo(lineas.get(i).get(0), lineas.get(i).get(1)));
			System.out.println("");
			i++;
		}
		
		
	}
	private static void testEjercicio2(String ruta) {
		System.out.println("EJERCICIO2: ");
		List<List<String>> lineas = Utiles.Lectura(ruta);

		System.out.println("Entrada: " + lineas);
		System.out.println("Funcional: " + Ejercicio2.ejercicio2Funcional(lineas));
		System.out.println("Iterativa: " + Ejercicio2.ejercicio2Iterativa(lineas));
		System.out.println("Recursiva: " + Ejercicio2.ejercicio2Recursiva(lineas));
		System.out.println("");
	}
	private static void testEjercicio1(String ruta) {
		System.out.println("EJERCICIO1: ");
		Predicate<String> pS =( x-> x.contains("a")||x.contains("e")||x.contains("o")||x.contains("A")||x.contains("E")||x.contains("O"));
		Predicate<Integer> pI = (x->x%2==0)  ;
		Function<String, Integer> f = (x->x.length());
		List<List<String>> lineas = Utiles.Lectura(ruta);
		Integer i = 3;
		while(i<lineas.size()) {
			System.out.println("Entrada:" + lineas.get(i));
			System.out.println("Funcional: " + Ejercicio1.ejercicio1Funcional(lineas.get(i),pS,pI,f));
			System.out.println("Iterativa: " + Ejercicio1.ejercicio1Iterativo(lineas.get(i)));
			System.out.println("Recursiva: " + Ejercicio1.ejercicio1Recursivo(lineas.get(i)));
			System.out.println("");
			
			i++;
		}
	}

}

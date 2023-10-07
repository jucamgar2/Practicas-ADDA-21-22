package test;

import java.util.List;
import ejercicios.Ejercicio1;
import ejercicios.Ejercicio2;
import ejercicios.Ejercicio3;
import ejercicios.Ejercicio4;
import ejercicios.Ejercicio5;
import us.lsi.common.Matrix;
import util.Utiles;

public class Test {

	public static void main(String[] args) {
		testEjercicio1("././ficheros/PI2Ej1DatosEntrada.txt");
		testEjercicio2("././ficheros/PI2Ej2DatosEntrada1.txt");
		testEjercicio2("././ficheros/PI2Ej2DatosEntrada2.txt");
		testEjercicio3("././ficheros/PI2Ej3DatosEntrada.txt");
		testEjercicio4("././ficheros/PI2Ej4DatosEntrada.txt");
		testEjercicio5("././ficheros/PI2Ej5DatosEntrada.txt");
	}

	private static void testEjercicio5(String string) {
		System.out.println("EJERCICIO 5");
		List<List<Integer>> inputs = Utiles.LecturaInteger(string);
		for(int i=0;i<inputs.size();i++) {
			System.out.println("Entrada: "+ inputs.get(i));
			System.out.println("Sin memoria:  "+ Ejercicio5.Ejercicio5SM(inputs.get(i).get(0), inputs.get(i).get(1), inputs.get(i).get(2)));
			System.out.println("Con memoria: "+ Ejercicio5.Ejercicio5CM(inputs.get(i).get(0), inputs.get(i).get(1), inputs.get(i).get(2)));
			System.out.println("Iterativo:  " + Ejercicio5.Ejercicio5Iterativo(inputs.get(i).get(0), inputs.get(i).get(1), inputs.get(i).get(2)));
			System.out.println("");
		}
	}

	private static void testEjercicio4(String string) {
		System.out.println("EJERCICIO 4");
		Integer i = 0;
		List<Integer> inputs = Utiles.lecturaEj4(string);
		while(i<inputs.size()) {
			System.out.println("Entrada: " + inputs.get(i));
			System.out.println("Sin memoria :" + Ejercicio4.ejercicio4SM(inputs.get(i)));
			System.out.println("Con memoria :" + Ejercicio4.ejercicio4CM(inputs.get(i)));
			System.out.println("Iterativo   :" + Ejercicio4.ejercicio4Iterativo(inputs.get(i)));
			System.out.println("");
			i++;
		}
		
	}

	private static void testEjercicio3(String string) {
		System.out.println("EJERCICIO 3");
		List<List<Integer>> inputs = Utiles.lecturaEJ3(string);
		Integer i = 0;
		while(i<inputs.size()) {
			System.out.println("Lista: "+ inputs.get(i)+ " Rango: " +inputs.get(i+1));
			System.out.println(Ejercicio3.Ejercicio3Recursivo(inputs.get(i), inputs.get(i+1)));
			i+=2;
		}
		System.out.println("");
		
	}

	private static void testEjercicio2(String string) {
		Matrix<String> inputs = Utiles.creaMatriz(string);
		System.out.println("EJERCICIO 2");
		mostrarEjercicio2(Ejercicio2.Ejercicio2Recursivo(inputs));
		System.out.println("");
	}
	private static void mostrarEjercicio2(List<String> ls) {
		for(int i=0;i<ls.size();i+=4) {
			System.out.println((i/4)+1+")  "+ ls.get(i) + ls.get(i+1) + ls.get(i+2) + ls.get(i+3));
		}
	}
	private static void testEjercicio1(String string) {
		List<List<Integer>> inputs = Utiles.LecturaInteger(string);
		System.out.println("EJERCICIO 1");
		Integer i = 0;
		while(i<inputs.size()) {
			System.out.println("Entradas: " + inputs.get(i).get(0) +" "+ inputs.get(i).get(1)+" "+inputs.get(i).get(2));
			System.out.println("Recursivo final:		" + Ejercicio1.Ejercicio1Final(inputs.get(i).get(0), inputs.get(i).get(1), inputs.get(i).get(2)));
			System.out.println("Iterativo: 			" + Ejercicio1.Ejercicio1Iterativo(inputs.get(i).get(0), inputs.get(i).get(1), inputs.get(i).get(2)));
			System.out.println("Recursivo no final: 	        " + Ejercicio1.Ejercicio1NoFinal(inputs.get(i).get(0), inputs.get(i).get(1), inputs.get(i).get(2)));
			System.out.println(" ");
			i++;
		}
	}

}

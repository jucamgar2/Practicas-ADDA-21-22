package test;

import java.util.List;

import java.util.function.Predicate;

import ejercicios.Ejercicio1;
import ejercicios.Ejercicio2;
import ejercicios.Ejercicio3;
import ejercicios.Ejercicio4;
import ejercicios.Ejercicio5;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;
import util.TreeInteger;
import util.Utiles;

public class Test {

	public static void main(String[] args) {
		testEjercicio1("././ficheros/PI3E1_DatosEntrada.txt");
		testEjercicio2("././ficheros/PI3E2_DatosEntrada.txt");
		testEjercicio3("././ficheros/PI3E3_DatosEntrada.txt");
		testEjercicio4("././ficheros/PI3E4_DatosEntrada.txt");
		testEjercicio5("././ficheros/PI3E5_DatosEntrada.txt");
	}
	private static void testEjercicio5(String string) {
		System.out.println("EJERCICIO5");
		List<BinaryTree<Integer>> inputs = Utiles.lecturaE3(string);
		for(int i=0;i<inputs.size();i++) {
			System.out.println("Entrada: " + inputs.get(i));
			System.out.println("Recursivo: " + Ejercicio5.Ejercicio5Recursivo(inputs.get(i)));
			System.out.println("Iterativo: " + Ejercicio5.Ejercicio5Iterativo(inputs.get(i)));
			System.out.println();
		}
		
	}
	private static void testEjercicio4(String string) {
		System.out.println("EJERCICIO4");
		List<Tree<Character>> inputs = Utiles.lecturaEj4(string);
		for(int i=0;i<inputs.size();i++) {
			System.out.println("");
			System.out.println("Entrada: " + inputs.get(i));
			System.out.println("Recursivo: " + Ejercicio4.Ejercicio4Recursivo(inputs.get(i)));
			System.out.println();
		}
	}
	private static void testEjercicio3(String string) {
		System.out.println("EJERCICIO3");
		List<BinaryTree<Integer>> inputs = Utiles.lecturaE3(string);
		for(int i=0;i<inputs.size();i++) {
			System.out.println("Entrada: " + inputs.get(i));
			System.out.println("Recursivo: " + "("+ Ejercicio3.Ejercicio3Recursivo(inputs.get(i))+Ejercicio3.Ejercicio3Recursivo(inputs.get(i)).stream().reduce((x,y)->x*y).get()+")");
			System.out.println();
		}
	}
	private static void testEjercicio2(String string) {
		System.out.println("EJERCICIO2");
		List<TreeInteger> inputs = Utiles.lecturaE2(string);
		for(int i = 0;i<inputs.size();i++) {
			System.out.println("Arbol: "+inputs.get(i).b() + " Entero: " + inputs.get(i).i());
			System.out.println("Recursivo: " +Ejercicio2.Ejercicio2Recursivo(inputs.get(i).b(), inputs.get(i).i()));
			System.out.println();
		}
		
	}
	public static void testEjercicio1(String ruta) {
		List<Tree<Integer>> inputs = Utiles.lecturaE1(ruta);
		System.out.println("EJERCICIO1 ");
		System.out.println("Predicado es par:");
		Predicate<Integer> p1 = x->x%2==0;
		Predicate<Integer> p2 = x->x<5;
		for(int i=0;i<inputs.size();i++) {
			System.out.println("Entrada: " + inputs.get(i));
			System.out.println("Iterativo funcional: "+Ejercicio1.Ejericicio1IterativoFuncional(inputs.get(i), p1));
			System.out.println("Iterativo imperativo: "+Ejercicio1.Ejercicio1IterativoImperativo(inputs.get(i), p1));
			System.out.println("Iterativo imperativo: "+Ejercicio1.Ejercicio1Recursivo(inputs.get(i), p1));
			System.out.println();
		}
		System.out.println("Predicado es menor que cinco: ");
		for(int a=0;a<inputs.size();a++) {
			System.out.println("Entrada: " + inputs.get(a));
			System.out.println("Iterativo funcional: "+Ejercicio1.Ejericicio1IterativoFuncional(inputs.get(a), p2));
			System.out.println("Iterativo imperativo: "+Ejercicio1.Ejercicio1IterativoImperativo(inputs.get(a), p2));
			System.out.println("Iterativo imperativo: "+Ejercicio1.Ejercicio1Recursivo(inputs.get(a), p2));
			System.out.println();
		}
	}

}
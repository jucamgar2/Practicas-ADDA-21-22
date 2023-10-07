package ejercicios;

import java.util.ArrayList;

import java.util.List;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BinaryType;

public class Ejercicio3 {
	public static List<Integer> Ejercicio3Recursivo(BinaryTree<Integer> arbol){
		List<Integer> mejorC = new ArrayList<>();
		List<Integer> actual = new ArrayList<>();
		Integer act = 1;
		Integer mj = 1;
		mejorC = Ejercicio3Recursivo(arbol, mejorC,mj, actual,act);
		return mejorC;
	}

	private static List<Integer> Ejercicio3Recursivo(BinaryTree<Integer> arbol, List<Integer> mejorC, Integer mj,
			List<Integer> actual, Integer act) {
		BinaryType tipo = arbol.getType();
		switch(tipo) {
		case Empty:
			break;
		case Leaf:
			act = act*arbol.getLabel();
			actual.add(arbol.getLabel());
			if(act>mj) {
				mejorC.clear();
				mejorC.addAll(actual);
			}
			break;
		case Binary:
			act = act*arbol.getLabel();
			actual.add(arbol.getLabel());
			mejorC = Ejercicio3Recursivo(arbol.getLeft(), mejorC, mejorC.size()>0?mejorC.stream().reduce((x, y)->x*y).get():1, new ArrayList<>(actual), act);
			mejorC = Ejercicio3Recursivo(arbol.getRight(), mejorC, mejorC.size()>0?mejorC.stream().reduce((x, y)->x*y).get():1,new ArrayList<>(actual), act);
			break;
		}
		return mejorC;
	}
}

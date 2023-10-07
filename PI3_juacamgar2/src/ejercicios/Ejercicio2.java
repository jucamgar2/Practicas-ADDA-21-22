package ejercicios;

import java.util.HashSet;

import java.util.Set;

import us.lsi.common.Preconditions;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BinaryType;

public class Ejercicio2 {
	public static Set<Integer> Ejercicio2Recursivo(BinaryTree<Integer> arbol, Integer i){
		Preconditions.checkArgument(arbol.getInOrder().stream().sorted().toList().equals(arbol.getInOrder()), "Lo siento pero no puedo aplicar el algoritmo porque el árbol no está ordenado");
		Set <Integer> res = new HashSet<>();
		res = Ejercicio2Recursivo(arbol, i, res);
		return res;
	}

	private static Set<Integer> Ejercicio2Recursivo(BinaryTree<Integer> arbol, Integer i, Set<Integer> res) {
		BinaryType tipo = arbol.getType();
		switch(tipo) {
		case Empty:
			break;
		case Leaf:
			if(arbol.getLabel()>=i) {
				res.add(arbol.getLabel());
			}
			break;
		case Binary:
			if(arbol.getLabel()>=i) {
				res.add(arbol.getLabel());
				res = Ejercicio2Recursivo(arbol.getLeft(), i, res);
			}
			res = Ejercicio2Recursivo(arbol.getRight(), i, res);
			break;
		}
		return res;
	}
}

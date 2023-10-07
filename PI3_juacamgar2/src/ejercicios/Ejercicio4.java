package ejercicios;

import java.util.ArrayList;


import java.util.List;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TreeType;
import util.Utiles;

public class Ejercicio4 {

	public static List<String> Ejercicio4Recursivo(Tree<Character> arbol){
		List<String> res = new ArrayList<>();
		String cadena ="";
		res = Ejercicio4Recursivo(arbol,res,cadena);
		return res;
	}
	
	
	
	
	private static List<String> Ejercicio4Recursivo(Tree<Character> arbol, List<String> res, String cadena){
		TreeType tipo = arbol.getType();
		switch (tipo){
		case Empty:
			break;
		case Leaf:
			cadena += arbol.getLabel();
			if(Utiles.esPalindromo(cadena)) {
				res.add(cadena);
			}
			break;
		case Nary:
			String r = cadena + arbol.getLabel();
			arbol.getChildren().forEach(x-> Ejercicio4Recursivo(x, res, r));
			break;
		}
		return res;
	}
}
  
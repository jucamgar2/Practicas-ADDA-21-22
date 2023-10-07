package util;

import java.util.ArrayList;

import java.util.List;

import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class Utiles {
	public static List<Tree<Integer>> lecturaE1(String ruta){
		List<Tree<Integer>> datos = Files2.streamFromFile(ruta)
							.map(x->Tree.parse(x,y->Integer.parseInt(y)))
							.toList();
	return datos;
	}
	public static List<TreeInteger> lecturaE2(String ruta){
		List<String> lista = Files2.linesFromFile(ruta);
		List<TreeInteger> res = new ArrayList<>();
		for(String l:lista) {
			String[] ps = l.split("#");
			res.add(TreeInteger.of(BinaryTree.parse(ps[0],x-> Integer.parseInt(x)), Integer.parseInt(ps[1])));
		}
		return res;
	}
	public static List<BinaryTree<Integer>> lecturaE3(String ruta){
		List<BinaryTree<Integer>> datos = Files2.streamFromFile(ruta)
								.map(x->BinaryTree.parse(x, y->Integer.parseInt(y)))
								.toList();
		return datos;
	}
	
	
	public static Boolean esPalindromo(String input) {
		Integer a =0;
		Integer z = input.length()-1;
		Boolean res = true;
		res = esPalindromo(input,a,z,res);
		return res;
	}
	
	private static Boolean esPalindromo(String input,Integer a, Integer z,Boolean res) {
		while(a<z && res) {
			if(input.charAt(a) == input.charAt(z)) {
				a++;
				z--;
				res = esPalindromo(input,a ,z,res);
			}
			else {
				res = false;
			}
		}
		return res;
	}

	public static List<Tree<Character>> lecturaEj4(String ruta){
		List<Tree<Character>> datos = Files2.streamFromFile(ruta)
				.map(x->Tree.parse(x,y->y.charAt(0)))
				.toList();
		return datos;

	}
}

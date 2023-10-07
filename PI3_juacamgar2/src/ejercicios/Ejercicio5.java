package ejercicios;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BinaryType;
import util.Paridad;

public class Ejercicio5 {
	public static Map<Paridad,List<Integer>> Ejercicio5Recursivo(BinaryTree<Integer> arbol){
		Map<Paridad, List<Integer>> res = new HashMap<>();
		res.put(Paridad.Impar, new ArrayList<Integer>());
		res.put(Paridad.Par, new ArrayList<Integer>());
		res = Ejercicio5Recursivo(arbol, res);
		return res;
	}

	private static Map<Paridad, List<Integer>> Ejercicio5Recursivo(BinaryTree<Integer> arbol,
			Map<Paridad, List<Integer>> res) {
		BinaryType tipo = arbol.getType();
		switch(tipo) {
		case Empty:
			break;
		case Leaf:
			break;
		case Binary:
			Integer padre = arbol.getLabel();
			Integer hijoD = arbol.getRight().getType()!=BinaryType.Empty? arbol.getRight().getLabel(): null;
			Integer hijoI = arbol.getLeft().getType()!=BinaryType.Empty? arbol.getLeft().getLabel(): null;
			if(hijoD!=null&&hijoI!=null&&padre>hijoI&&padre<hijoD) {
				Paridad p=padre%2==0?Paridad.Par:Paridad.Impar;
				List<Integer> aux = res.get(p);
				aux.add(padre);
				res.put(p, aux);
			}
			res = Ejercicio5Recursivo(arbol.getRight(), res);
			res = Ejercicio5Recursivo(arbol.getLeft(), res);
		}
		return res;
	}
	public static Map<Paridad, List<Integer>> Ejercicio5Iterativo(BinaryTree<Integer> arbol){
		Map<Paridad, List<Integer>> res = new HashMap<>();
		res.put(Paridad.Impar, new ArrayList<Integer>());
		res.put(Paridad.Par, new ArrayList<Integer>());
		Integer altura = arbol.getHeight();
		for(int i= 0; i<altura;i++) {
			for(BinaryTree<Integer> c: arbol.getLevel(i)) {
				if(c.getType()==BinaryType.Binary
						&&c.getRight().getType()!= BinaryType.Empty
						&&c.getLeft().getType()!= BinaryType.Empty
						&&c.getLabel()>c.getLeft().getLabel()
						&&c.getLabel()<c.getRight().getLabel()) {
					
					Paridad p=c.getLabel()%2==0?Paridad.Par:Paridad.Impar;
					List<Integer> med = res.get(p);
					med.add(c.getLabel());
					res.put(p, med);
				}
			}
		}
		return res;
	}
}

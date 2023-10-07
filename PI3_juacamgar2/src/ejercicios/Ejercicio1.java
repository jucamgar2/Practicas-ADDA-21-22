package ejercicios;

import java.util.ArrayList;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TreeType;

public class Ejercicio1 {
	public static <E> Set<E> Ejericicio1IterativoFuncional(Tree<E> arbol,Predicate<E> pd){
		return arbol.stream()
				.filter((x->!x.isEmpty()))
				.map(x->x.getLabel())
				.filter(pd)
				.collect(Collectors.toSet());
	}
	public static <E> Set<E> Ejercicio1IterativoImperativo(Tree<E> arbol, Predicate <E> pd){
		Set<E> res = new HashSet<E>();
		List <E> etiquetas = new ArrayList<>();
		etiquetas.addAll(arbol.getLabelByLevel());
		int i =0;
		while(i<etiquetas.size()) {
			if(pd.test(etiquetas.get(i))) {
				res.add(etiquetas.get(i));
			}
			i++;
		}
		return res;
	}
	public static <E> Set<E> Ejercicio1Recursivo(Tree<E> arbol, Predicate<E> pd){
		Set<E> res = new HashSet<E>();
		res = Ejercicio1Recursivo(arbol, pd, res);
		return res;
	}
	private static <E> Set<E> Ejercicio1Recursivo(Tree<E> arbol, Predicate<E> pd,Set<E> res){
		TreeType tipo = arbol.getType();
		switch(tipo){
		case Empty:
			break;
		case Leaf:
			Boolean v = pd.test(arbol.getLabel());
			if(v) {
				res.add(arbol.getLabel());
			}
			break;
		case Nary:
			Boolean b = pd.test(arbol.getLabel());
			if(b) {
				res.add(arbol.getLabel());
			}
			arbol.getChildren().forEach(x->Ejercicio1Recursivo(x, pd, res));
			break;
		}
		return res;
	}
	


}

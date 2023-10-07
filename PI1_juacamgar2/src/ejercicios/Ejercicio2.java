package ejercicios;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ejercicio2 {
	//EJERCICIO 2 FUNCIONAL
		public static Map<Integer,List<String>> ejercicio2Funcional (List<List<String>> listas) {
			return listas.stream()
			.flatMap(lista -> lista.stream())
			.collect(Collectors.groupingBy(String::length));
			}
		//EJERCICIO 2 ITERATIVA
		public static Map<Integer, List<String>> ejercicio2Iterativa (List<List<String>> listas){
			Integer i = 0;
			Map<Integer, List<String>> res = new HashMap<Integer, List<String>>();
			while(i<listas.size()) {
				Integer a = 0;
				while(a<listas.get(i).size()) {
					Integer clave = listas.get(i).get(a).length();
					
					if(res.containsKey(clave)) {
						res.get(clave).add(listas.get(i).get(a));
					}else {
						List<String> sol = new ArrayList<>();
						sol.add(listas.get(i).get(a));
						res.put(clave, sol);
					}
					a++;
				}
				i++;
			}
			return res;
		}
		//EJERCICIO 2 RECURSVIO
		public static Map<Integer, List<String>> ejercicio2Recursiva(List<List<String>> listas){
			Integer i = 0;
			Map<Integer, List<String>> res = new HashMap<Integer,List<String>>();
			res = ejercicio2Recursiva(listas, res, i);
			return res;
		}
		private static Map<Integer, List<String>> ejercicio2Recursiva(List<List<String>> listas,
				Map<Integer, List<String>> res, Integer i) {
			if(i<listas.size()) {
				Integer a = 0;
				while(a<listas.get(i).size()) {
					Integer clave = listas.get(i).get(a).length();
					
					if(res.containsKey(clave)) {
						res.get(clave).add(listas.get(i).get(a));
					}else {
						List<String> sol = new ArrayList<>();
						sol.add(listas.get(i).get(a));
						res.put(clave, sol);
					}
					a++;
				}
				res = ejercicio2Recursiva(listas,res,i=i+1);
			}
			return res;
		}

}

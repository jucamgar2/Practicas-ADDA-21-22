package ejercicios;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Ejercicio1 {
	//EJERCICIO 1 FUNCIONAL
		public static Boolean ejercicio1Funcional(List<String> ls, Predicate<String> pS, 
				Predicate<Integer> pI, Function<String, Integer> f){
				return ls.stream()
				.filter(pS)
				.map(f)
				.anyMatch(pI);
				}
		//EJERCICIO 1 WHILE
		public static Boolean ejercicio1Iterativo(List<String> ls) {
			Boolean b0 = false;
			Integer i=0;
			while(i<ls.size()&& !b0) {
				if( ls.get(i).contains("a")||ls.get(i).contains("e")||ls.get(i).contains("o")||ls.get(i).contains("A")||ls.get(i).contains("E")||ls.get(i).contains("O")) {
					Integer a = ls.get(i).length();
					b0= a % 2==0;
				}
				i++;
			}
			return b0;
		}
		//EJERCICIO 1 RECURSIVO 
		public static Boolean ejercicio1Recursivo(List<String> ls) {
			Integer i = 0;
			Boolean b0 = false;
			b0 = ejercicio1Recursivo(ls,b0,i);		
			return b0;
		}
		private static Boolean ejercicio1Recursivo(List<String> ls, Boolean b0, Integer i) {
			if(i<ls.size() && !b0) {
				if(ls.get(i).contains("a")||ls.get(i).contains("e")||ls.get(i).contains("o")||ls.get(i).contains("A")||ls.get(i).contains("E")||ls.get(i).contains("O")) {
					//b0=ejercicio1Recursivo(ls,ls.get(i).length()%2==0,i++);
					b0 = ls.get(i).length()%2==0;
					//b0 = ejercicio1Recursivo(ls,b0,i=i+1);
				}
				b0 = ejercicio1Recursivo(ls,b0,i=i+1);
			}
			return b0;
		}

}

package ejercicios;

import java.util.List;
import us.lsi.common.IntegerSet;

public class Ejercicio3 {
	public static IntegerSet Ejercicio3Recursivo(List<Integer> ls, List<Integer> rangos) {
		Integer a = rangos.get(0);
		Integer b = rangos.get(1);
		Integer i = 0;
		Integer j = ls.size() - 1;
		IntegerSet res = IntegerSet.of();
		if(b<ls.get(0)&&a<ls.get(ls.size()-1)) {
			
		}else if(b>ls.get(0)) {
			b = ls.get(0) +1;
		}else if(a<=ls.get(ls.size()-1)){
			a = ls.get(ls.size()-1);
		}
		res = Ejercicio3Recursivo(ls, a, b, i, j, res);
		return res;
	}
	private static IntegerSet Ejercicio3Recursivo(List<Integer> ls, Integer a, Integer b, Integer i, Integer j,
			IntegerSet res) {
		if (!(i - j == 0)) {
			if (ls.get(i) == b-1 && ls.get(j) == a ) {
				res.addAll(ls.subList(i, j+1));
			}else if(b-a==1&&ls.get(i) == b-1 ){
				res.add(j-1);
			}else if(b-a== 1 && ls.get(j) == a) {
				res.add(i);
			}else if (ls.get(i) == b-1) {
				j--;
				res = Ejercicio3Recursivo(ls, a, b, i, j, res);
			}else{
				i++;
				res = Ejercicio3Recursivo(ls, a, b, i, j, res);
			}
		}
		return res;
	}
}

package ejercicios;

import java.util.ArrayList;
import java.util.List;
import us.lsi.common.Matrix;

public class Ejercicio2 {
	public static List<String> Ejercicio2Recursivo(Matrix<String> matriz) {
		List<String> res = new ArrayList<>();
		Integer i = 0;
		Integer a = 0;
		res.addAll(matriz.corners());
		res = Ejercicio2Recursivo(matriz, i, res, a);
		return res;
	}
	private static List<String> Ejercicio2Recursivo(Matrix<String> matriz, Integer i, List<String> res,Integer a) {
		if(i<16) {
			if(!res.containsAll(matriz.view(i/4).corners())) {
				res.addAll(matriz.view(i/4).corners());
			}
			if(a<16) {
				res.addAll(matriz.view(i/4).view(a%4).corners());
				a++;
				i++;
				res = Ejercicio2Recursivo(matriz,i,res,a);
			}
		}
		return res;
	}


}

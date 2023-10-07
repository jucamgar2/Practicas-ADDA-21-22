package ejercicios;

import java.math.BigInteger;

import us.lsi.common.IntTrio;
import java.util.HashMap;
import java.util.Map;
public class Ejercicio5 {
	
	public static BigInteger Ejercicio5SM(Integer a, Integer b, Integer c) {
		BigInteger res = BigInteger.valueOf(0);
		res = Ejercicio5SM(a,b,c,res);
		return res;
	}

	private static BigInteger Ejercicio5SM(Integer a, Integer b, Integer c, BigInteger res) {
		if(a<3||b<3||c<3) {
			res = BigInteger.valueOf(a).add(BigInteger.valueOf(b).pow(2)).add(BigInteger.valueOf(c).multiply(BigInteger.valueOf(2)));
		}else if(a%b ==0) {
			res = Ejercicio5SM(a-1,b/2,c/2,res).add(Ejercicio5SM(a-3, b/3, c/3, res));
		}else {
			res = Ejercicio5SM(a/3, b-3, c-3, res).add(Ejercicio5SM(a/2, b-2, c-2, res));
		}
		return res;
	}
	public static BigInteger Ejercicio5CM(Integer a,Integer b,Integer c) {
		BigInteger res = BigInteger.valueOf(0);
		Map<IntTrio,BigInteger> map = new HashMap<>();
		res = Ejercicio5CM(a,b,c,map,res);
		return res;
	}

	private static BigInteger Ejercicio5CM(Integer a, Integer b, Integer c, Map<IntTrio, BigInteger> map, BigInteger res) {
		IntTrio t = IntTrio.of(a, b, c);
		if(map.containsKey(t)) {
			res = map.get(t);
		}else if(a<3||b<3||c<3) {
			res = BigInteger.valueOf(a).add(BigInteger.valueOf(b).pow(2)).add(BigInteger.valueOf(c).multiply(BigInteger.valueOf(2)));
			map.put(t, res);
		}else if(a%b ==0) {
			res = Ejercicio5CM(a-1,b/2,c/2,map,res).add(Ejercicio5CM(a-3, b/3, c/3,map,res));
		}else {
			res = Ejercicio5CM(a/3, b-3, c-3,map, res).add(Ejercicio5CM(a/2, b-2, c-2,map, res));
		}
		return res;
	}

	public static BigInteger Ejercicio5Iterativo(Integer a, Integer b, Integer c) {
		BigInteger res = BigInteger.valueOf(0);	
		Map<IntTrio,BigInteger> map = new HashMap<>();
		for(int i=0; i<=a; i++ ) {
			for(int j=0; j<=b;j++) {
				for(int k=0;k<=c;k++) {
					if(i<3||j<3||k<3) {
						res = BigInteger.valueOf(i).add(BigInteger.valueOf(j).pow(2)).add(BigInteger.valueOf(k).multiply(BigInteger.valueOf(2)));
						map.put(IntTrio.of(i, j, k), res);
					}else if(i%j ==0) {
						res =  map.get(IntTrio.of(i-1, j/2, k/2)).add(map.get(IntTrio.of(i-3, j/3, k/3)));
					}else {
						res = map.get(IntTrio.of(i/3, j-3, k-3)).add(map.get(IntTrio.of(i/2, j-2, k-2)));
					}
					map.put(IntTrio.of(i, j, k), res);
				}
			}
		}
		return map.get(IntTrio.of(a, b, c));
	}
}

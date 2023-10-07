package ejercicios;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import us.lsi.common.IntTrio;

public class Ejercicio4 {	
	public static BigInteger ejercicio4SM(Integer n) {
		BigInteger res = BigInteger.valueOf(0);
		res = ejercicio4SM(n,res);
		return res;	
	}
	private static BigInteger ejercicio4SM(Integer n, BigInteger res) {
		if(n==0) {
			res = BigInteger.valueOf(2);
		}else if(n==1) {
			res = BigInteger.valueOf(4);
		}else if(n==2) {
			res = BigInteger.valueOf(6);
		}else {
			res =ejercicio4SM(n-1,res).multiply(BigInteger.valueOf(2)).add(ejercicio4SM(n-2, res).multiply(BigInteger.valueOf(4)).add(ejercicio4SM(n-3, res).multiply(BigInteger.valueOf(6))));
		}
		return res;
	}
	public static BigInteger ejercicio4CM(Integer n) {
		BigInteger res = BigInteger.valueOf(0);
		Map<IntTrio,BigInteger> map = new HashMap<>();
		res = ejercicio4CM(n,res,map);
		return res;
	}
	private static BigInteger ejercicio4CM(Integer n, BigInteger res, Map<IntTrio, BigInteger> map) {
		IntTrio t= IntTrio.of(n-1, n-2, n-3);
		if(map.containsKey(t)) {
			res = map.get(t);
		}else if(n==0) {
			res = BigInteger.valueOf(2);
			map.put(t, res);
		}else if(n==1) {
			res = BigInteger.valueOf(4);
			map.put(t, res);
		}else if(n==2) {
			res = BigInteger.valueOf(6);
			map.put(t, res);
		}else {
			res =ejercicio4CM(n-1,res,map).multiply(BigInteger.valueOf(2)).add(ejercicio4CM(n-2, res,map).multiply(BigInteger.valueOf(4)).add(ejercicio4CM(n-3, res,map).multiply(BigInteger.valueOf(6))));
			map.put(t, res);
		}
	return res;
	}
	public static BigInteger ejercicio4Iterativo(Integer n) {
		BigInteger res = BigInteger.valueOf(0);
		Integer i = 0;
		Map<Integer, BigInteger> map = new HashMap<>();
		while(i<=n) {
			if(map.containsKey(i)) {
				res = map.get(i);
			}else if(i==0) {
				res = BigInteger.valueOf(2);
				map.put(i, res);
			}else if(i==1) {
				res = BigInteger.valueOf(4);
				map.put(i, res);
			}else if(i==2) {
				res = BigInteger.valueOf(6);
				map.put(i, res);
			}else {
				res = (map.get(i-1).multiply(BigInteger.valueOf(2))).add((map.get(i-2).multiply(BigInteger.valueOf(4)))).add(map.get(i-3).multiply(BigInteger.valueOf(6)));
				map.put(i, res);
			}
			i++;
		}
		return map.get(n);
	}
}
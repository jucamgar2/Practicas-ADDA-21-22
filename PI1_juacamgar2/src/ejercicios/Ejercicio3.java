package ejercicios;

import java.util.ArrayList;



import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.Par;

public class Ejercicio3 {
	//Ejercicio 3 Funcional
	public static String ejercicio3Funcional(Integer a, Integer limit) {
		Integer i = 0;
		return Stream
		.iterate(Par.of(i, a),
		t -> t.v1() < limit,
		t -> Par.of(t.v1()+1, t.v1() % 3 == 1 ? t.v2() : t.v1()+t.v2()))
		.collect(Collectors.toList())
		.toString();
		}

	//Ejercicio 3 Iterativo
	public static String ejercicio3Iterativo(Integer a, Integer limit) {
		Integer i = 0;
		Par c= Par.of(i, a);
		List<Par> lista = new ArrayList<>();
		while(c.v1()<limit) {
			lista.add(c);
			if(c.v1()%3 == 1) {
				c = Par.of(c.v1()+1,c.v2());
			}else {
				c = Par.of(c.v1()+1, c.v1()+c.v2());
			}
		}
		return lista.toString();
	}
	//Ejericio 3 Recursivo
	public static String ejercicio3Recursivo(Integer a , Integer limit) {
		Integer i = 0;
		Par c = Par.of(i, a);
		List<Par> lista = new ArrayList<>();
		lista = ejercicio3Recursivo(limit,c,lista);
		return lista.toString();
	}

	private static List<Par> ejercicio3Recursivo( Integer limit, Par c, List<Par> lista) {
		if(c.v1()<limit) {
			lista.add(c);
			if(c.v1()%3 == 1) {
				c = Par.of(c.v1()+1,c.v2());
			}else {
				c = Par.of(c.v1()+1, c.v1()+c.v2());
			}
			lista = ejercicio3Recursivo(limit,c,lista);
		}
		return lista;
	}

}

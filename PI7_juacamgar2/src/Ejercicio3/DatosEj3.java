package Ejercicio3;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import us.lsi.common.Files2;
import us.lsi.common.List2;
import util.Componente;
import util.Producto;

public class DatosEj3 {
	public static Integer tProd;
	public static Integer tManual;
	public static List<Componente> componentes;
	public static List<Producto> productos;
	public static Integer n;
	public static Integer m;
	public static Integer getM() {
		return m;
	}
	public static Integer getN() {
		return n;
	}
	public static Integer getValor(Integer i) {
		return productos.get(i).precio();
	}
	public static Integer getMax(Integer i) {
		return productos.get(i).uMax();
	}
	public static Integer getProd(Integer j) {
		return componentes.get(j).tiempoP();
	}
	public static Integer getMan(Integer j) {
		return componentes.get(j).tiempoE();
	}
	public static Double calculaTiempoP(Integer i) {
		Producto p = productos.get(i);
		Map<String,Integer> component = p.requerimientos();
		Double res = 0.;
		for(Entry<String,Integer> s:component.entrySet()) {
			Integer costeP = componentes.stream().filter(x->x.nombre().equals(s.getKey())).mapToInt(x->x.tiempoP()).sum();
			res += s.getValue() * costeP;
		}
		return res;
	}
	public static Double calculaTiempoE(Integer i) {
		Producto p = productos.get(i);
		Map<String,Integer> component = p.requerimientos();
		Double res = 0.;
		for(Entry<String,Integer> s:component.entrySet()) {
			Integer costeE = componentes.stream().filter(x->x.nombre().equals(s.getKey())).mapToInt(x->x.tiempoE()).sum();
			res += s.getValue() * costeE;
		}
		return res;
	}
	public static Double calculaTiempoP(Producto p) {
		Map<String,Integer> component = p.requerimientos();
		Double res = 0.;
		for(Entry<String,Integer> s:component.entrySet()) {
			Integer costeP = componentes.stream().filter(x->x.nombre().equals(s.getKey())).mapToInt(x->x.tiempoP()).sum();
			res += s.getValue() * costeP;
		}
		return res;
	}
	public static Double calculaTiempoE(Producto p) {
		Map<String,Integer> component = p.requerimientos();
		Double res = 0.;
		for(Entry<String,Integer> s:component.entrySet()) {
			Integer costeE = componentes.stream().filter(x->x.nombre().equals(s.getKey())).mapToInt(x->x.tiempoE()).sum();
			res += s.getValue() * costeE;
		}
		return res;
	}
	public static Integer getTiempoP(Integer i, Integer j) {
		Integer res = 0;
		Producto p = productos.get(i);
		Componente c = componentes.get(j);
		Map<String, Integer> map = p.requerimientos();
		if(map.keySet().contains(c.nombre())) {
			res =  map.get(c.nombre())* c.tiempoP();
		}else {
			res = 0;
		}
		return res;
	}
	public static Integer getTiempoE(Integer i, Integer j) {
		Integer res = 0;
		Producto p = productos.get(i);
		Componente c = componentes.get(j);
		Map<String, Integer> map = p.requerimientos();
		if(map.keySet().contains(c.nombre())) {
			res =  map.get(c.nombre())* c.tiempoE();
		}else {
			res = 0;
		}
		return res;
	}
	public static Integer getTP() {
		return tProd;
	}
	public static Integer getTM() {
		return tManual;
	}
	
	public static void iniDatos(String datos) {
		List<String> lineas = Files2.linesFromFile(datos);
		componentes = List2.empty();
		productos = List2.empty();
		tProd = Integer.valueOf(lineas.get(0).replaceAll("T_prod =", "").strip());
		tManual = Integer.valueOf(lineas.get(1).replaceAll("T_manual =", "").strip());
		for(int i = 2;i<lineas.size();i++) {
			if(lineas.get(i).charAt(0) == 'C') {
				componentes.add(Componente.ofFormat(lineas.get(i)));
			}else if(lineas.get(i).charAt(0) == 'P') {
				productos.add(Producto.ofFormat(lineas.get(i)));
			}
		}
		m = componentes.size();
		n = productos.size();
		productos.sort(Comparator.comparing(x->x.precio()/(calculaTiempoE(x)+calculaTiempoP(x))));
	}
	public static void main(String[] args) {
		iniDatos("././ficheros/PI6Ej3DatosEntrada1.txt");
		System.out.println(tProd);
		System.out.println(tManual);
		System.out.println(componentes);
		System.out.println(productos);
		System.out.println(getTiempoP(0, 0));
		//System.out.println(getMax(51));
		System.out.println(calculaTiempoP(0));
		System.out.println(calculaTiempoE(0));
	}
}

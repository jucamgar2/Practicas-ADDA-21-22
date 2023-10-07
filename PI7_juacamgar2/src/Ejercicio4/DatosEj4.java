package Ejercicio4;

import java.util.ArrayList;
import java.util.List;
import us.lsi.common.Files2;
import util.Contenedor;
import util.Elemento;

public class DatosEj4 {
	public static Integer n;
	public static Integer m;
	public static List<Contenedor> contenedores;
	public static List<Elemento> elementos;
	public static Integer getN() {
		return n;
	}
	public static Integer getM() {
		return m;
	}
	public static Integer getTipos(Integer i,Integer j) {
		Elemento e = elementos.get(i);
		Contenedor c = contenedores.get(j);
		Integer res;
		if (e.tipos().contains(c.tipo())) {
			res=1;
		}else {
			res=0;
		}
		return res;
	}
	public static Boolean sonCompatibles(Integer i,Integer j) {
		Elemento e = elementos.get(i);
		Contenedor c = contenedores.get(j);
		return e.tipos().contains(c.tipo());
	}
	public static Integer getPeso(Integer i) {
		return elementos.get(i).tamaño();
	}
	public static Integer getCapacidad(Integer j) {
		return contenedores.get(j).capacidad();
	}
	public static void iniDatos(String datos) {
		contenedores = new ArrayList<>();
		elementos = new ArrayList<>();
		List<String> lineas = Files2.linesFromFile(datos);
		for (String a:lineas) {
			if(a.charAt(0)=='C') {
				contenedores.add(Contenedor.ofFormat(a));
			}else if (a.charAt(0)=='E') {
				elementos.add(Elemento.ofFormat(a));
			}
		}
		n = elementos.size();
		m = contenedores.size();
	}
	
	public static void main(String[] args) {
		iniDatos("././ficheros/PI5Ej4DatosEntrada1.txt");
		System.out.println(elementos);
		System.out.println(contenedores);
		System.out.println(getN());
		System.out.println(getM());
		System.out.println(getPeso(5));
		System.out.println(getTipos(0,0));
		System.out.println(getTipos(1,1));
		System.out.println(getCapacidad(2));
		System.out.println(getPeso(1)+getPeso(13));

	}

}

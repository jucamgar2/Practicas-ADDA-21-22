package Ejercicios;

import java.util.ArrayList;

import java.util.List;
import us.lsi.common.Files2;
import util.Archivo;
import util.Memoria;

public class DatosEj1 {
	public static Integer n;
	public static Integer m;
	public static List<Archivo> archivos;
	public static List<Memoria> memorias;
	public static Integer getN() {
		return n;
	}
	public static Integer getM() {
		return m;
	}
	public static Integer getTam(Integer i) {
		return archivos.get(i).tamaño();
	}
	public static Integer tamMax(Integer j) {
		return memorias.get(j).tamMax();
	}
	public static Integer capacidadM(Integer j) {
		return memorias.get(j).capacidad();
	}
	public static void iniDatos(String datos) {
		memorias = new ArrayList<>();
		archivos = new ArrayList<>();
		List<String> aux = Files2.linesFromFile(datos);
		for(String i:aux){
			if(i.charAt(0)=='M') {
				memorias.add(Memoria.ofFormat(i));
			}else if(i.charAt(0)=='F') {
				archivos.add(Archivo.ofFormat(i));
			}
		}
		m = memorias.size();
		n = archivos.size();
	}
	public static void main(String[] args) {
		iniDatos("././ficheros/PI5Ej1DatosEntrada1.txt");
		System.out.println(memorias);
		System.out.println(archivos);
	}
}

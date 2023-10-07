package Soluciones;

import java.util.List;

import org.jgrapht.Graph;

import Ejercicios.DatosEj5;
import us.lsi.common.List2;
import util.Carretera;
import util.Ciudad;



public class SolucionEj5 {
	private Graph<Ciudad,Carretera> grafo;
	private List<Ciudad> ciudades;
	private Double distancia;
	
	public static SolucionEj5 crear(List<Integer> ls) {
		return new SolucionEj5(ls);
	}
	public SolucionEj5(List<Integer> value) {
		grafo = DatosEj5.g;
		distancia = 0.;
		ciudades = List2.of(DatosEj5.origen);
		for(int i = 0;i<value.size()-1;i++) {
			Ciudad src = DatosEj5.ciudades.get(value.get(i)),target = DatosEj5.ciudades.get(value.get(i+1));
			if(grafo.containsEdge(src,target)) {
				Carretera c = grafo.getEdge(src, target);
				distancia += c.getKm();
			}
			ciudades.add(target);
		}
	}
	
	public String toString() {
		String msg = String.format("Distancia recorrida: %.1f \n", distancia);
		for(Ciudad c: ciudades) {
			msg += c +"\n";
		}
		return msg;
	}
}

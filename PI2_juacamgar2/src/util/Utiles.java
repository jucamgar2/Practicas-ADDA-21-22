package util;

import java.util.ArrayList;
import java.util.List;
import us.lsi.common.Files2;
import us.lsi.common.Matrix;

public class Utiles {
	public static List<List<Integer>> LecturaInteger(String ruta){
		List<String> inputs = Files2.linesFromFile(ruta);
		List<List<Integer>> lectura = new ArrayList<>();
		for(String l:inputs) {
			String[] ps = l.split(",");
			List<Integer> lsNum = List.of(Integer.parseInt(ps[0]), Integer.parseInt(ps[1]),Integer.parseInt(ps[2]));
			lectura.add(lsNum);
		}
		return lectura;
	}

	public static Matrix<String> creaMatriz(String ruta){
		List<String> res = 
				Files2.streamFromFile(ruta)
				.map(linea->{
					String aux[] = linea.split(" ");					
					List<String> lst = new ArrayList<String>();
					for (int i=0;i<aux.length;i++)
						lst.add(aux[i]);
					return lst;						
				})
						.flatMap(s->s.stream())
						.toList();
		int n = (int) Math.sqrt(res.size());

		return Matrix.of(res.toArray(String[]::new), n, n);	
	}
	public static List<List<Integer>> lecturaEJ3(String ruta){
		List<String> inputs = Files2.linesFromFile(ruta);
		List<List<Integer>> lectura = new ArrayList<>();
		for(String l:inputs) {
			String[] ps = l.split("#");
			String[] a = ps[0].split(",");
			String[] b = ps[1].split(",");
			List<Integer> listas = List.of(Integer.parseInt(a[0]),Integer.parseInt(a[1]),Integer.parseInt(a[2]),Integer.parseInt(a[3]),Integer.parseInt(a[4]),Integer.parseInt(a[5]),Integer.parseInt(a[6]),Integer.parseInt(a[7]),Integer.parseInt(a[8]),Integer.parseInt(a[9]),Integer.parseInt(a[10]));
			List<Integer> rangos = List.of(Integer.parseInt(b[0]),Integer.parseInt(b[1])); 
			lectura.add(listas);
			lectura.add(rangos);
		}
		return lectura;
	}
	public static List<Integer> lecturaEj4(String ruta){
		List<Integer> lectura = new ArrayList<>();
		List<String> inputs = Files2.linesFromFile(ruta);
		for(String l:inputs) {
			String [] ps = l.split("=");
			lectura.add(Integer.parseInt(ps[1]));

		}
		return lectura;
	}
}

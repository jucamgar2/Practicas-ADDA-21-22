package util;
import java.util.ArrayList;
import java.util.List;
import us.lsi.common.Files2;
import us.lsi.common.Preconditions;

public class Utiles {

	public static List<List<String>> Lectura(String ruta) {
		List<List<String>>inputs = Files2.streamFromFile(ruta)
				.map(s-> {
					String[] ps = s.split(",");
					return List.of(ps);
				})
				.toList();
		return inputs;
	}
	public static List<List<Integer>> LecturaInteger(String ruta){
		List<String> inputs = Files2.linesFromFile(ruta);
		List<List<Integer>> lectura = new ArrayList<>();
		for(String l:inputs) {
			String[] ps = l.split(",");
			List<Integer> lsNum = List.of(Integer.parseInt(ps[0]), Integer.parseInt(ps[1]));
			lectura.add(lsNum);
		}
		return lectura;
	}
	public static List<List<Double>> LecturaDouble(String ruta){
		List<String> inputs = Files2.linesFromFile(ruta);
		List<List<Double>> lectura = new ArrayList<>();
		for(String l:inputs) {
			String[] ps = l.split(",");
			Preconditions.checkArgument(ps.length ==2 && 0.0<=Double.parseDouble(ps[1])&& Double.parseDouble(ps[1])<=1.0);
			List<Double> lsNum = List.of(Double.parseDouble(ps[0]), Double.parseDouble(ps[1]));
			lectura.add(lsNum);
		}
		return lectura;
	}

}

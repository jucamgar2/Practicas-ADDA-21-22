package util;

import java.util.ArrayList;
import java.util.List;

public record Candidato(String nombre,List<String>cualidades,Double sueldo,Integer valoracion,List<String> incompatibilidades) {
	
	public static Candidato ofFormat(String a) {
		String [] b = a.split(":");
		String [] c = b[1].split(";");
		List<String> cualidades = new ArrayList<>();
		for(String r:c[0].split(",")) {
			cualidades.add(r.strip());
		}
		List<String> inc = new ArrayList<>();
		for(String r:c[3].split(",")) {
			inc.add(r.strip());
		}
		return new Candidato(b[0].strip(),cualidades,Double.valueOf(c[1].strip()),Integer.valueOf(c[2].strip()),inc);
	}
}

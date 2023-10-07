package util;

import java.util.ArrayList;
import java.util.List;

public record Elemento(String nombre,Integer tamaño,List<String> tipos) {
	public static Elemento ofFormat(String a) {
		List<String> tipos = new ArrayList<>();
		String [] b = a.split(":");
		String [] c = b[1].split(";");
		for(String d:c[1].split(",")) {
			tipos.add(d.strip());
		}
		return new Elemento(b[0].strip(),Integer.valueOf(c[0].strip()),tipos);
	}
	public Integer getPosicion() {
		return Integer.valueOf(this.nombre().replace("E", "").strip());
	}

}

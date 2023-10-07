package util;

import java.util.HashMap;
import java.util.Map;

public record Producto(String nombre,Integer precio,Map<String,Integer> requerimientos,Integer uMax) {
	public static Producto ofFormat(String a) {
		String [] b = a.split("->");
		String [] c = b[1].split(";");
		Map<String,Integer> requerimientos = new HashMap<>();
		c[1].replace("comp=(", "").replace(")", "").strip();
		for(String e:c[1].split(",")) {
			String [] aux = e.split(":");
			requerimientos.put(aux[0].replace("comp=(", "").replace("(","").strip(), Integer.valueOf(aux[1].replace(")", "").strip()));
		}
		return new Producto(b[0].strip(),Integer.valueOf(c[0].replaceAll("precio=", "").strip()),requerimientos,Integer.valueOf(c[2].replaceAll("max_u=", "").strip()));
	}
	public Integer getPosicion() {
		return Integer.valueOf(this.nombre().replace("P", "").strip());
	}
}

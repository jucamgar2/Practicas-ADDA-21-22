package util;

public record Contenedor(String nombre,Integer capacidad,String tipo) {
	public static Contenedor ofFormat(String a) {
		String [] b = a.split(":");
		String [] c = b[1].split(";");
		return new Contenedor(b[0],Integer.valueOf(c[0].replaceAll("capacidad=", "").strip()),c[1].replaceAll("tipo=", "").strip());
	}
}

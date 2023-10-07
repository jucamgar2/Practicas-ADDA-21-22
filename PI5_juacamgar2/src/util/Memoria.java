package util;

public record Memoria(String nombre,Integer capacidad,Integer tamMax) {
	public static Memoria ofFormat(String [] a) {
		return new Memoria(a[0],Integer.parseInt(a[1]),Integer.parseInt(a[2]));
	}
	public static Memoria ofFormat(String a) {
		String [] b =a.split(":");
		String [] c = b[1].split(";");
		return new Memoria(b[0].strip(),Integer.valueOf(c[0].replaceAll("capacidad=", "").strip()),Integer.valueOf(c[1].replaceAll("tam_max=", "").strip()));
	}
	
}

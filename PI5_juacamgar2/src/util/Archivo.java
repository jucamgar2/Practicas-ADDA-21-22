package util;

public record Archivo(String nombre,Integer tamaño) {
	public static Archivo ofFormat(String [] a) {
		return new Archivo(a[0].strip(),Integer.parseInt(a[1].trim()));
	}
	public static Archivo ofFormat(String a) {
		String [] b = a.split(":");
		return Archivo.ofFormat(b);
	}
	
}

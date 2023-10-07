package util;

public record Componente(String nombre,Integer tiempoP,Integer tiempoE) {
	public static Componente ofFormat(String a) {
		String [] b= a.split(":");
		String [] c = b[1].split(";");
		return new Componente(b[0].strip(),Integer.valueOf(c[0].replaceAll("prod=", "").strip()),Integer.valueOf(c[1].replaceAll("elab=", "").strip()));
	}
}

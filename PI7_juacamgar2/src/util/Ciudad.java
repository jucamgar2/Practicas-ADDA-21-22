package util;

public record Ciudad(String nombre,Double habitantes) {
	public static Ciudad ofFormat(String[] formato) {
		return new Ciudad(formato[0],Double.valueOf(formato[1]));
	}
}

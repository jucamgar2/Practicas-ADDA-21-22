package util;

public record Carretera(String source,String target,String nombre,Double km) {
	public static Carretera ofFormat(String[] formato) {
		return new Carretera(formato[0],formato[1],formato[2],Double.valueOf(formato[3]));
	}
	public Double getKm() {
		return this.km();
	}
}

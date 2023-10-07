package util;
//record para la arista calle del ejercicio 3
public record Calle(Integer source,Integer destino,Double tiempo,Double esfuerzo) {
	public static Calle ofFormat(String [] p) {
		return new Calle(Integer.valueOf(p[0]),Integer.valueOf(p[1]),Double.valueOf(p[2].toString().replace("min", "")),Double.valueOf(p[3].replace("esf", "")));
	}
	
}

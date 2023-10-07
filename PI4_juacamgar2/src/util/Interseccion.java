package util;
//record para el objeto intersección, vertice del ejercicio 3
public record Interseccion(Integer id,Boolean monumento,String nomMonumento,Integer interes) {
	public static Interseccion ofFormat(String [] p) {
		return new Interseccion(Integer.valueOf(p[0]),Boolean.valueOf(p[1]),p[2].toString(),Integer.valueOf(p[3].toString().replace("int", "")));
	}
}

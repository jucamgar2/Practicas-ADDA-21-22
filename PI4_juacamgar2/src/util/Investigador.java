package util;

public record Investigador(Integer id,Integer a�o,String uni) {
	//Record creado para el objeto Investigador, vertice del ejercicio1
	public static Investigador ofFormat(String [] formato) {
		return new Investigador(Integer.valueOf(formato[0]),Integer.valueOf(formato[1]),formato[2]);
	}
	public Integer getA�o() {
		return this.a�o();
	}
	public Integer getId() {
		return this.id();
	}
	public String getUni() {
		return this.uni();
	}
}

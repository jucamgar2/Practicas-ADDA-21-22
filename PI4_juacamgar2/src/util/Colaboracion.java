package util;

public record Colaboracion(Integer source,Integer target,Double colaboraciones) {
	//Record creado para el objeto Colaboracion, arista del ejercicio1
	public static Colaboracion ofFormat(String[] formato) {
		return new Colaboracion(Integer.valueOf(formato[0]),Integer.valueOf(formato[0]),Double.valueOf(formato[2]));
	}
	public Double getColaboraciones() {
		return this.colaboraciones();
	}
	public Integer getSource() {
		return this.source();
	}
	public static Colaboracion de (Investigador a,Investigador b) {
		return new Colaboracion(a.id(),b.id(),1.);
	}
}

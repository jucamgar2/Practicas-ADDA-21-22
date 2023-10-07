package util;

public record Trio(Double i, Double j,Double k) {

	public static Trio of(Double a, Double b,Double c) {
		return new Trio(a,b,c);
	}
}

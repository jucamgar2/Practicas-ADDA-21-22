package util;


public record Par(Integer v1, Integer v2){
	
	public static Par of(Integer a, Integer b) {
		return new Par(a,b);
	}




}



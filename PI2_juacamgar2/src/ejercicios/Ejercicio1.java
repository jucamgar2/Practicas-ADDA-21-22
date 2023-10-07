package ejercicios;



public class Ejercicio1 {
	
	//Iterativa
	public static String Ejercicio1Iterativo(Integer a, Integer b, Integer c) {
		String msg="";
		while(!(a< 3 && b < 3 && c < 3)&&!(a < 5 || b < 5 || c < 5)) {
			if(a%2==0&&b%2==0&&c%2==0) {
				msg = msg + String.valueOf(a*b*c);
				a=a/2;
				b=b-2;
				c=c/2;
			}else {
				msg = msg + String.valueOf(a+b+c) ;
				a=a/3;
				b=b-3;
				c=c/3;
			}
		}
		if(a< 3 && b < 3 && c < 3) {
			msg = msg + String.format("(%s)", String.valueOf(a*b*c));
		}else if(a < 5 || b < 5 || c < 5) {
			msg = msg + String.format("(%s)", String.valueOf(a+b+c));
		}
		
		return msg;
	}
	
	//Recursiva no final:
	public static String Ejercicio1NoFinal(Integer a, Integer b, Integer c) {
		String msg = "";
		msg = Ejercicio1NoFinal(a,b,c,msg);
		return msg;
	}

	private static String Ejercicio1NoFinal(Integer a, Integer b, Integer c, String msg) {
		if(a< 3 && b < 3 && c < 3) {
			msg = String.format("(%s)", String.valueOf(a*b*c));
		}else if(a < 5 || b < 5 || c < 5) {
			msg = String.format("(%s)", String.valueOf(a+b+c));
		}else if(a%2==0&&b%2==0&&c%2==0) {
			msg = String.valueOf(a*b*c) + Ejercicio1NoFinal(a/2,b-2,c/2,msg);	
		}else {
			msg = String.valueOf(a+b+c) + Ejercicio1NoFinal(a/3,b-3,c/3,msg);
		}
		return msg;
	}
	
	//Recursiva Final
	public static String Ejercicio1Final(Integer a, Integer b,Integer c) {
		String msg = "";
		msg = Ejercicio1Final(a,b,c,msg);
		return msg;
	}
	private static String Ejercicio1Final(Integer a, Integer b, Integer c, String msg) {
		if(a< 3 && b < 3 && c < 3) {
			msg += String.format("(%s)", String.valueOf(a*b*c));
		}else if(a < 5 || b < 5 || c < 5) {
			msg += String.format("(%s)", String.valueOf(a+b+c));
		}else if(a%2==0&&b%2==0&&c%2==0) {
			msg += Ejercicio1Final(a/2, b-2, c/2, String.valueOf(a*b*c));
		}else {
			msg += Ejercicio1Final(a/3,b-3,c/3, String.valueOf(a+b+c));
		}
		return msg;
	}


	
}

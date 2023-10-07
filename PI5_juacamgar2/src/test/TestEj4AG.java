package test;


import Ejercicios.DatosEj4;
import Ejercicios.Ejercicio4AG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.String2;

public class TestEj4AG {
	public static void main(String[] args) {
		testEj4AG("././ficheros/PI5Ej4DatosEntrada1.txt");
		testEj4AG("././ficheros/PI5Ej4DatosEntrada2.txt");
		testEj4AG("././ficheros/PI5Ej4DatosEntrada3.txt");
	}
	private static void testEj4AG(String string) {
		AlgoritmoAG.POPULATION_SIZE = 100;
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		DatosEj4.iniDatos(string);
		var alg = AlgoritmoAG.of(new Ejercicio4AG());
		alg.ejecuta();
		String2.toConsole("Solucion: %s\n%s",alg.bestSolution().toString(),String2.linea());
	}
}

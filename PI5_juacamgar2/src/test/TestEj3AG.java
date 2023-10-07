package test;

import Ejercicios.DatosEj3;
import Ejercicios.Ejercicio3AG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.String2;

public class TestEj3AG {
	public static void main(String[] args) {
		testEj3AG("././ficheros/PI5Ej3DatosEntrada1.txt");
		testEj3AG("././ficheros/PI5Ej3DatosEntrada2.txt");
		testEj3AG("././ficheros/PI5Ej3DatosEntrada3.txt");
	}

	private static void testEj3AG(String string) {
		AlgoritmoAG.POPULATION_SIZE = 100;
		StoppingConditionFactory.NUM_GENERATIONS = 1500;
		DatosEj3.iniDatos(string);
		var alg = AlgoritmoAG.of(new Ejercicio3AG());
		alg.ejecuta();
		String2.toConsole("Solucion: %s\n%s",alg.bestSolution().toString(),String2.linea());
	}


}

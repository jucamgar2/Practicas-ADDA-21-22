package test;

import Ejercicios.DatosEj2;
import Ejercicios.Ejercicio2AG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.String2;

public class TestEj2AG {

	public static void main(String[] args) {
		testEj2AG("././ficheros/PI5Ej2DatosEntrada1.txt");
		testEj2AG("././ficheros/PI5Ej2DatosEntrada2.txt");
		testEj2AG("././ficheros/PI5Ej2DatosEntrada3.txt");
	}

	private static void testEj2AG(String string) {
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 100;
		StoppingConditionFactory.NUM_GENERATIONS= 1000;
		DatosEj2.iniDatos(string);
		var alg = AlgoritmoAG.of(new Ejercicio2AG());
		alg.ejecuta();
		String2.toConsole("Solucion: %s\n%s",alg.bestSolution().toString(),String2.linea());
	}

}

package test;

import Ejercicios.DatosEj1;

import Ejercicios.Ejercicio1AG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.String2;

public class TestEj1AG {
	public static void main(String[] args) {
		testEj1Ag("././ficheros/PI5Ej1DatosEntrada1.txt");
		testEj1Ag("././ficheros/PI5Ej1DatosEntrada2.txt");
		testEj1Ag("././ficheros/PI5Ej1DatosEntrada3.txt");
	}
	private static void testEj1Ag(String string) {
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 100;
		StoppingConditionFactory.NUM_GENERATIONS= 6000;
		DatosEj1.iniDatos(string);
		var alg = AlgoritmoAG.of(new Ejercicio1AG());
		alg.ejecuta();
		String2.toConsole("Solucion: %s\n%s",alg.bestSolution().toString(),String2.linea());
		
	
	}
	

}

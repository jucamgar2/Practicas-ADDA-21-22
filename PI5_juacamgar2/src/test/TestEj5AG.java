package test;

import java.util.function.Predicate;


import Ejercicios.DatosEj5;
import Ejercicios.Ejercicio1AG;
import Ejercicios.Ejercicio5AG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.String2;
import util.Carretera;
import util.Ciudad;

public class TestEj5AG {

	public static void main(String[] args) {
		testEj5Ag("././ficheros/PI5Ej5DatosEntrada1.txt",x->x.getKm()>100,x->x.habitantes()>100000,"Cadiz","Granada");
		testEj5Ag("././ficheros/PI5Ej5DatosEntrada2.txt",x->x.getKm()>=120,x->x.habitantes()<200000,"Toledo","Guadalajara");
		testEj5Ag("././ficheros/PI5Ej5DatosEntrada3.txt",x->x.getKm()< 200,x->x.habitantes()>25000,"C01","C25");
	}

	private static void testEj5Ag(String string,Predicate<Carretera> pCa,Predicate<Ciudad> pCi,String c1,String c2) {
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 100;
		StoppingConditionFactory.NUM_GENERATIONS= 1000;
		DatosEj5.iniDatos(string);
		DatosEj5.pCa = pCa;
		DatosEj5.pCi = pCi;
		DatosEj5.origen = DatosEj5.ciudades.stream().filter(x->x.nombre().equals(c1)).findFirst().get();
		DatosEj5.destino = DatosEj5.ciudades.stream().filter(x->x.nombre().equals(c2)).findFirst().get();
		var alg = AlgoritmoAG.of(new Ejercicio5AG());
		alg.ejecuta();
		String2.toConsole("Solucion: %s\n%s",alg.bestSolution().toString(),String2.linea());
	}

}

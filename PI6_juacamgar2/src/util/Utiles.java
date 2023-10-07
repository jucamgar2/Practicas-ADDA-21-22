package util;

import org.jgrapht.Graph;

import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class Utiles {
	
	
	
	public static Graph<Ciudad,Carretera> importaGrafo(String fichero){
		return GraphsReader.newGraph(fichero,
				Ciudad::ofFormat,
				Carretera::ofFormat, 
				Graphs2::simpleGraph);
				//Carretera::getKm);
	}
}

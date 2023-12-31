package us.lsi.graphs.alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.graph.GraphWalk;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jheaps.AddressableHeap;
import org.jheaps.AddressableHeap.Handle;
import org.jheaps.tree.FibonacciHeap;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.ArrowHead;
import us.lsi.colors.GraphColors.Color;
import us.lsi.common.TriFunction;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.path.EGraphPath;
import us.lsi.streams.Stream2;


public class AStar<V,E> implements Iterator<V>, Iterable<V> {
	
	/**
	 * @param <V> El tipo de los v&eacute;rtices
	 * @param <E> El tipo de las aristas 
	 * @param graph Un grafo
	 * @param heuristic La heur&iacute;stica 
	 * @return Una algoritmo de b&uacute;squeda de AStar
	 */
	public static <V, E> AStar<V, E> of(EGraph<V, E> graph,TriFunction<V,Predicate<V>,V,Double> heuristic, AStarType type) {
		return new AStar<V, E>(graph, heuristic, type);
	}
	
	public static enum AStarType{Min,Max}

	public Comparator<Double> comparator = Comparator.naturalOrder();
	public AStarType type;
	public EGraph<V,E> graph; 
	protected TriFunction<V,Predicate<V>,V,Double> heuristic;
	public Map<V,Handle<Double,Data<V,E>>> tree;
	public AddressableHeap<Double,Data<V,E>> heap; 
	protected EGraphPath<V,E> ePath;
	

	AStar(EGraph<V, E> graph,TriFunction<V,Predicate<V>, V,Double> heuristic, AStarType type) {
		super();
		this.type = type;
		this.comparator = this.type.equals(AStarType.Min)?Comparator.naturalOrder():Comparator.reverseOrder();
		this.graph = graph;
		this.heuristic = heuristic;
		this.tree = new HashMap<>();
		this.ePath = graph.initialPath();
		this.heap = new FibonacciHeap<>(comparator);
		Data<V,E> data = Data.of(graph.startVertex(),null,ePath.getWeight());	
		Double d = ePath.estimatedWeightToEnd(data.distanceToOrigin,graph.startVertex(),graph.goal(),graph.endVertex(),heuristic);
		Handle<Double, Data<V, E>> h = this.heap.insert(d,data);
		this.tree.put(graph.startVertex(),h);
	}
	
	public Stream<V> stream() {
		return Stream2.of(this);
	}
	
	public Iterator<V> iterator() {
		return this;
	}
	
	public boolean hasNext() {
		return !heap.isEmpty(); 
	}

	@Override
	public V next() {
		Handle<Double, Data<V, E>> hActual = heap.deleteMin();
		Data<V, E> dActual = hActual.getValue();
		V vertexActual = dActual.vertex;
		Double actualDistance = dActual.distanceToOrigin;
		E edgeToOrigen = dActual.edge;
		for (E backEdge : graph.edgesListOf(vertexActual)) {
			V v = Graphs.getOppositeVertex(graph,backEdge,vertexActual);
			Double newDistanceToOrigin = ePath.add(actualDistance,v,backEdge,edgeToOrigen);
			Double newDistanceToEnd =  ePath.estimatedWeightToEnd(newDistanceToOrigin,v, graph.goal(), graph.endVertex(), heuristic);
			if (!tree.containsKey(v)) {
				Data<V, E> dv = Data.of(v, backEdge, newDistanceToOrigin);
				Handle<Double, Data<V, E>> hv = heap.insert(newDistanceToEnd, dv);
				tree.put(v, hv);
			} else if (comparator.compare(newDistanceToOrigin,tree.get(v).getValue().distanceToOrigin()) < 0) {
				Data<V, E> dv = Data.of(v, backEdge, newDistanceToOrigin);
				Handle<Double, Data<V, E>> hv = tree.get(v);
				hv.setValue(dv);
				hv.decreaseKey(newDistanceToEnd);
			}
		}
		return vertexActual;
	}

	public E getEdgeToOrigin(V v) {
		return tree.get(v).getValue().edge;
	}

	public EGraph<V, E> getGraph() {
		return this.graph;
	}
	
	public Optional<GraphPath<V, E>> path(V startVertex, Optional<V> last) {
		if (!last.isPresent() || !graph.constraint().test(last.get())) return Optional.empty();
		V endVertex = last.get();
		Handle<Double, Data<V, E>> hav = this.tree.get(endVertex);
		Data<V, E> dav = hav.getValue();
		Double weight = dav.distanceToOrigin;
		E edge = dav.edge;
		List<E> edges = new ArrayList<>();
		while (edge != null) {
			edges.add(edge);
			endVertex = Graphs.getOppositeVertex(graph, edge, endVertex);
			edge = this.getEdgeToOrigin(endVertex);
		}
		Collections.reverse(edges);
		List<V> vertices = new ArrayList<>();
		V v = startVertex;
		vertices.add(v);
		for (E e : edges) {
			v = Graphs.getOppositeVertex(graph, e, v);
			vertices.add(v);
		}
		GraphPath<V, E> gp = new GraphWalk<>(graph, startVertex, endVertex, vertices, edges, weight);
		return Optional.of(gp);
	}

	public Optional<GraphPath<V, E>> search() {
		V startVertex = graph.startVertex();
		if(graph.goal().test(startVertex)) return Optional.of(ePath);
		Optional<V> last = this.stream().filter(graph.goal().and(graph.constraint())).findFirst();	
		return path(startVertex,last);
	}
	
	public List<GraphPath<V, E>> searchAll() {
		V startVertex = graph.startVertex();
		if(graph.goal().test(startVertex)) return List.of(ePath);
		List<V> lasts = this.stream().filter(graph.goal().and(graph.constraint())).toList();	
		return lasts.stream().map(v->path(startVertex,Optional.of(v)))
				.filter(p->p.isPresent())
				.map(p->p.get())
				.collect(Collectors.toList());
	}
	
	public SimpleDirectedGraph<V,E> graph(){
		SimpleDirectedGraph<V,E> g = Graphs2.simpleDirectedGraph();
		for(V v:tree.keySet()) {
			g.addVertex(v);
		}
		for(V v:tree.keySet()) {
			E e = tree.get(v).getValue().edge();
			if (e != null) {
				V source = graph.getEdgeSource(e);
				V target = graph.getEdgeTarget(e);
				g.addEdge(source, target, e);
			}
		}
		return g;
	}
	
	
	public static <V,E> void toDot(SimpleDirectedGraph<V,E> g, GraphPath<V,E> gp, String file){
		List<V> vertices = gp.getVertexList();
		List<E> edges = gp.getEdgeList();
		GraphColors.toDot(g, file, 
				x -> x.toString(),
				x -> x.toString(),
				x -> GraphColors.colorIf(Color.red, vertices.contains(x)),
				e -> GraphColors.all(GraphColors.arrowHead(ArrowHead.none),
						GraphColors.colorIf(Color.red, edges.contains(e))));
	}
	

	public static record Data<V, E> (V vertex, E edge, Double distanceToOrigin) {

		public static <V, E> Data<V, E> of(V vertex, E edge, Double distance) {
			return new Data<>(vertex, edge, distance);
		}

		public static <V, E> Data<V, E> of(Data<V, E> d) {
			return new Data<>(d.vertex, d.edge, d.distanceToOrigin);
		}

	}
	
}

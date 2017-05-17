package it.polito.tdp.porto.model;

import java.util.ArrayList;

import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;


import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	List <Author> autori;
	private UndirectedGraph<Author, DefaultEdge> graph  ;
	PortoDAO dao;
	public Model(){
		dao= new PortoDAO();
		
		
		
	}
	public List<Author> getAutori(){
	
		if(this.autori==null) {
			
			this.autori = new ArrayList( dao.listaAutori());
		}
		return this.autori ;
		
		
	}
public void creaGrafo3() {
		
		this.graph = new SimpleGraph<>(DefaultEdge.class) ;
		
		
		// crea i vertici del grafo
		Graphs.addAllVertices(graph, this.getAutori()) ;
	dao.listaConnessioni();
	
		// crea gli archi del grafo -- versione 3
		for(Author a : autori) {
			for (Author b : autori){
				if(!a.equals(b)&&this.coautori(a,b))
					graph.addEdge(a, b);
			}
			
		}
	}
private boolean coautori(Author a, Author b) {
    for(Paper p: a.getPapers())
    	if(b.getPapers().contains(p))
    		return true;
	return false;
}
private UndirectedGraph<Author, DefaultEdge> getGrafo() {
	if(this.graph==null) {
		this.creaGrafo3();
	}
	return this.graph ;
}

public String displayNeighbours(Author a) {
String f="";
	if(this.getGrafo().containsVertex(a)){
	for(Author b: Graphs.neighborListOf(graph, a)){
		f+=b.toString()+" \n";
	}
	}
	
	return f.trim();
}
public String Dijkstra(Author f1,Author f2){
   String s="";
		DijkstraShortestPath<Author,DefaultEdge> cammino=new DijkstraShortestPath<Author,DefaultEdge>(this.getGrafo(),f1,f2);
		for(DefaultEdge a:cammino.getPathEdgeList())
			s+=graph.getEdgeTarget(a).getPapers().get(0).toString()+"\n";
		return s.trim();
}
}

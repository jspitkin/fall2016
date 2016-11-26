package assignment09;
/**
 * Chenxi Sun and Jordan Newton
 * u0455173 and u1018840
 * 
 */
import java.util.ArrayList;

public class Vertex  {
	public boolean visited=false;
	public ArrayList<Vertex> adjacentneighbor;
	public Vertex comefrom;

	public int distance=Integer.MAX_VALUE;

	public Vertex(){
		this.adjacentneighbor=new ArrayList<Vertex>();
		this.visited=false;
		this.comefrom=null;
	
		
	}


	
	
	
}

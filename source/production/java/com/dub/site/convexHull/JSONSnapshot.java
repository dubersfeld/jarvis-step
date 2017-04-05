package com.dub.site.convexHull;

/** This class represents a Jarvis March result as a JSON object.
 * It is used in response to Ajax requests 
 * It encapsulates only the convexHull current vertex and the candidate */

public class JSONSnapshot {
	
	private int currentVertex;// current vertex index
	private int cand;// current candidate index


	public int getCand() {
		return cand;
	}

	public void setCand(int ind) {
		this.cand = ind;
	}

	
	public int getCurrentVertex() {
		return currentVertex;
	}

	public void setCurrentVertex(int currentVertex) {
		this.currentVertex = currentVertex;
	}

	
	public void display() {
		System.out.println("\nsnapshot: currentVertex: " + currentVertex 
									+ " cand: " + cand);
	}
	
}

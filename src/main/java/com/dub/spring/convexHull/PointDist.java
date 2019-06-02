package com.dub.spring.convexHull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PointDist implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Point[] points;
	private int N;
	
	private boolean right = true;
	
    List<Integer> cand;// list of candidates
    
	private List<Point> convexHull;
	
	private boolean newVertexFound;
	
	private int ind;
	private int first;
	private int index;
	private int curr;
	
	private boolean finished;
	
	public PointDist() {
	}
	
	public PointDist(Point[] points) {
		this.N = points.length;
		this.points = points;
		this.convexHull = new ArrayList<>();
		this.finished = false;
		this.right = true;
	}

	public boolean isNewVertexFound() {
		return newVertexFound;
	}

	public void setNewVertexFound(boolean newVertexFound) {
		this.newVertexFound = newVertexFound;
	}

	public List<Integer> getCand() {
		return cand;
	}

	public void setCand(List<Integer> cand) {
		this.cand = cand;
	}

	public List<Point> getConvexHull() {
		return convexHull;
	}

	public void setConvexHull(List<Point> convexHull) {
		this.convexHull = convexHull;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	
	
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Point[] getPoints() {
		return points;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}
		
	public int getInd() {
		return ind;
	}

	public void setInd(int ind) {
		this.ind = ind;
	}

	public void init() {
		
		findFirst();
		
		System.out.println("first " + first);
		      
		convexHull.add(points[first]);	
		curr = first;
		ind = N;
		
		right = true;
		
		cand = new ArrayList<>();
		
		finished = false;
		
		newVertexFound = false;
		
	}// init
	
	
	
	public void scanStep() {
		
		int i0; 
		int cp;
		
		newVertexFound = false;
		  
		Point vCurr = points[curr];
		Point pref = new Point(-1, 0, 0);
		
		int distCurr, distMax;
		
		pref.setXpos(vCurr.getXpos());
		
		if (right) {// angle consistency
			pref.setXpos(pref.getXpos() + 100);
		} else {
			pref.setXpos(pref.getXpos() -100);
		}// if   
		
	    pref.setYpos(vCurr.getYpos());//.yPos = Vcurr.yPos;
		
	    if (ind == points.length) {// begin a new vertex search
	    
	    	if (convexHull.size() == 1) {// first vertex search
	    		i0 = (curr == 0) ? 1 : 0;
			    cand.clear();
			    cand.add(i0);
	    	} else {// remaining steps
	    		// find a point with acceptable angle          
		        i0 = 0;
		        for (i0 = 0; i0 < N; i0++) {
		          cp = cross(pref, points[i0], vCurr, vCurr);
		          if (i0 != curr && cp > 0) {
		            break;
		          } 
		        }// for
		        if (i0 == N) {
		        	right = false;
			        // do not update
		        } else {// actual search
		        	cand.clear();//.length = 0;
			        cand.add(i0);       
		        }// if    
	    		
	    	}// if
	    	ind = 0;// reset
	    } else {
	    	 
	    	i0 = cand.get(cand.size()-1);    
	    	if (ind != i0 && ind != curr) {
		                 	          
	    		if (convexHull.size() == 1 || cross(pref, points[ind], vCurr, vCurr) > 0) {
		            cp = cross(points[i0], points[ind], vCurr, vCurr);      
		            if (cp < 0) {
		            	cand.clear();
		            }
		            if (cp <= 0) {
		            	cand.add(ind);
		            }  
	    		}// if
		        
	    	}// if      
	    	ind++;
	    }// if
	    
	    if (ind < points.length) {// more candidates to test      
	    	return;// next step
	    } else if (ind == N && (curr != first || convexHull.size() == 1)) {// find next vertex
	   
	    	// find farthest candidate
	    	index = cand.get(0);
		    distMax = distance(points[index], vCurr);
		    for (int k = 1; k < cand.size(); k++) {
		        distCurr = distance(points[cand.get(k)], vCurr);
		        if (distCurr > distMax) {
		          index = cand.get(k);
		          distMax = distCurr;
		        }// if
		      
		    }// for
		    
		    int next = index;
		    
		    if (next != first) {// convex hull not completed yet 
		        convexHull.add(points[next]);		    
		        curr = next;
		              
		       
		      
		        newVertexFound = true;
		        return;// next step
		    } else {
		       
		        finished = true;           
		    }// if        
  
		    
	    }// if
	    		
	
	}

	/** change method name later */
	public void findFirst() {   
		
		// find the point with maximal y in Canvas coordinates
	    int ymax = 0;
	    cand = new ArrayList<>();// list of candidates
	    
	    for (int i = 0; i < N; i++) {
	    	if (ymax < points[i].getYpos()) {
	    		ymax = points[i].getYpos();
	    	}
	    }// for
	    
	    for (int i = 0; i < N; i++) {
	    	if (ymax == points[i].getYpos()) {
	    		cand.add(i);
	    	} 
	    }// for
	    
	    // select the leftmost point
	    int index = 0; 
	    for (int k = 0; k < cand.size(); k++) {
	    	if (points[cand.get(k)].getXpos() < points[cand.get(index)].getXpos()) {
	    		index = k;
	    	}
	    } 
	    first = cand.get(index);
	    
		System.out.println("first: " + first);
	        
	}// findFirst
		 
    // helper geometric function
    private int cross(Point p1, Point p2, Point pref1, Point pref2) {
        //return the cross product of vectors p1-pref1, p2-pref2
        int x1 = p1.getXpos() - pref1.getXpos();
        int x2 = p2.getXpos() - pref2.getXpos();
        int y1 = p1.getYpos() - pref1.getYpos();
        int y2 = p2.getYpos() - pref2.getYpos();
        return -(x1*y2-x2*y1);// left-handed frame
      
    }
    
    // replace by the square because only comparison is of interest
    private int distance(Point p1, Point p2) {
      
    	int x = p1.getXpos() - p2.getXpos();
    	int y = p1.getYpos() - p2.getYpos();
    	 
        return x * x + y * y;
        
      }// distance
    
    public void displayCH() {
    	System.out.println("\ndisplayCH");
    	for (int k = 0; k < convexHull.size(); k++) {
    		System.out.println(convexHull.get(k).getIndex());
    	}
    }
    
    
    
}

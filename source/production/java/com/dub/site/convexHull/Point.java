package com.dub.site.convexHull;


/** POJO represents a geometric point */

public class Point {
	
	private int index;
	private int xpos;
	private int ypos;
	
	
	public Point() {
		
	}
	
	public Point(int index, int xpos, int ypos) {
		this.index = index;
		this.xpos = xpos;
		this.ypos = ypos;
	}

	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	
	
	public String toString() {
		return index + " " + xpos + "," + ypos; 
	} 
	
	
}

package com.dub.site.convexHull;


import org.springframework.stereotype.Service;

@Service
public class ConvexHullServices {

	
	public JSONSnapshot PointsToJSON(PointDist pointDist) {
	
		JSONSnapshot snapshot = new JSONSnapshot();
		snapshot.setCurrentVertex(
					pointDist.getConvexHull().get(pointDist.getConvexHull().size()-1).getIndex());
		
		
		snapshot.setCand(pointDist.getInd());
		
		
		
		return snapshot;
	}
	
}

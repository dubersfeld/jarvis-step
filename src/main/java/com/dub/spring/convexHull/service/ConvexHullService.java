package com.dub.spring.convexHull.service;


import org.springframework.stereotype.Service;

import com.dub.spring.convexHull.JSONSnapshot;
import com.dub.spring.convexHull.PointDist;

@Service
public class ConvexHullService {

	
	public JSONSnapshot PointsToJSON(PointDist pointDist) {
	
		JSONSnapshot snapshot = new JSONSnapshot();
		snapshot.setCurrentVertex(
					pointDist.getConvexHull().get(pointDist.getConvexHull().size()-1).getIndex());
		
		
		snapshot.setCand(pointDist.getInd());
			
		return snapshot;
	}
	
}

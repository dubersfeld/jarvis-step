package com.dub.spring.convexHull.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dub.spring.convexHull.CHResponse;
import com.dub.spring.convexHull.JSONSnapshot;
import com.dub.spring.convexHull.Point;
import com.dub.spring.convexHull.PointDist;
import com.dub.spring.convexHull.PointsInitRequest;
import com.dub.spring.convexHull.SearchRequest;
import com.dub.spring.convexHull.StepResult;
import com.dub.spring.convexHull.service.ConvexHullService;


@Controller
public class ConvexHullController {
	
	// using a service layer
	@Autowired
	private ConvexHullService chServices;
	
	@RequestMapping("/initPoints")
	@ResponseBody
	public CHResponse initPoints(@RequestBody PointsInitRequest message, 
											HttpServletRequest request) 
	{	
	
		int N = message.getPoints().length;
		System.out.println("controller: " + N);
		
		Point[] points = message.getPoints();
		
		PointDist pointDist = new PointDist(points);
		
		pointDist.init();
		
		// add pointDist to session context
		HttpSession session = request.getSession();
		
		if (session.getAttribute("points") != null) {
			session.removeAttribute("points");
		}
		
		session.setAttribute("points", pointDist);
		
		
		CHResponse chResponse = new CHResponse();
		chResponse.setStatus(CHResponse.Status.INIT);
		
		System.out.println("controller: initPoints");
		pointDist.displayCH();
		
		return chResponse;
	}// initPoints
	
	
	@RequestMapping(value="/scanStep")
	@ResponseBody
	public StepResult scanStep(@RequestBody SearchRequest message, 
														HttpServletRequest request) 
	{	
		StepResult result = new StepResult();
			
		// retrieve graph from session context
		HttpSession session = request.getSession();
		PointDist pointDist = (PointDist)session.getAttribute("points");
			
		pointDist.displayCH();
		
		pointDist.scanStep();// actual scan step
			
		JSONSnapshot snapshot = chServices.PointsToJSON(pointDist);
				
		result.setSnapshot(snapshot);
		
		if (pointDist.isFinished()) {
			result.setStatus(StepResult.Status.FINISHED);
		} else if (pointDist.isNewVertexFound()) { 
			result.setStatus(StepResult.Status.REDRAW);
		} else {
			result.setStatus(StepResult.Status.STEP);
		}
		
		snapshot.display();
	
		return result;
	}
	

}

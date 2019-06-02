package com.dub.spring.convexHull;


/** container object for AJAX response 
 * on initGraph request should return a component in a suitable form 
 **/
public class CHResponse {
	private Status status;
	private JSONSnapshot snapshot;
	

	public CHResponse() {
		status = null;
		snapshot = null;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public JSONSnapshot getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(JSONSnapshot snapshot) {
		this.snapshot = snapshot;
	}

	
	public static enum Status {
		OK, ERROR, INIT
	}
}

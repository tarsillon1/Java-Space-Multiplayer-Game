package com.thinkbox.sf.model;

import java.io.Serializable;

public class UniqueIdentifier implements Serializable {
	private static final long serialVersionUID = -3893928098006430890L;
	String id = java.util.UUID.randomUUID().toString();
	
	public UniqueIdentifier(){
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}

package com.learn.dto;

import java.time.LocalDateTime;

public class ApiResponse {
	
	private LocalDateTime timeStamp;
	private String mesg;
	
	public ApiResponse(String mesg) {
		super();
		this.timeStamp = LocalDateTime.now();
		this.mesg = mesg;
	}

	public String getMesg() {
		return mesg;
	}

	public void setMesg(String mesg) {
		this.mesg = mesg;
	}
	
	
}

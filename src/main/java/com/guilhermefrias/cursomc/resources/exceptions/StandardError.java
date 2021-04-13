package com.guilhermefrias.cursomc.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msh;
	private Long timeStamp;
	public StandardError(Integer status, String msh, Long timeStamp) {
		super();
		this.status = status;
		this.msh = msh;
		this.timeStamp = timeStamp;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsh() {
		return msh;
	}
	public void setMsh(String msh) {
		this.msh = msh;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	

}

package com.os.services.exception;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long timeStamp;
	private Integer Status;
	private String Error;

	public StandardError() {
		super();

	}

	public StandardError(Long timeStamp, Integer status, String error) {
		super();
		this.timeStamp = timeStamp;
		Status = status;
		Error = error;
	}

	public Long getMomentoDoErro() {
		return timeStamp;
	}

	public void setMomentErr(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}

}

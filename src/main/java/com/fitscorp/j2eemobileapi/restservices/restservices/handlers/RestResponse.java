package com.fitscorp.j2eemobileapi.restservices.restservices.handlers;

import java.util.List;

public class RestResponse<T> {

    private int status;
    private List<String> errorMessages;
    private T body;
    
	public RestResponse() {}

	public RestResponse(int status, List<String> errorMessages, T body) {
		this.status = status;
		this.errorMessages = errorMessages;
		this.body = body;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}


	@Override
	public String toString() {
		return "RestResponse [status=" + status + ", errorMessages=" + errorMessages + ", body=" + body + "]";
	}
}

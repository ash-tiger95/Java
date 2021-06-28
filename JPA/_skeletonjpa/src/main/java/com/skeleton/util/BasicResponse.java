package com.skeleton.util;

public class BasicResponse {

	public String status;
	public Object header;
	public Object body;

	@Override
	public String toString() {
		return "BasicResponse [status=" + status + ", header=" + header + ", body=" + body + "]";
	}
}

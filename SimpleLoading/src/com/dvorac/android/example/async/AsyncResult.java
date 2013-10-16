package com.dvorac.android.example.async;

public class AsyncResult<T> {
	T data;
	Exception e;
	
	public AsyncResult(T data, Exception e) {
		this.data = data;
		this.e = e;
	}
	
	public T getData() {
		return data;
	}
	
	public Exception getException() {
		return e;
	}
}

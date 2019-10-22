package com.tonels.springIntegration.samples.endpoints;

public interface Activator<T> {
	
	public void handleMessage(T input);

}

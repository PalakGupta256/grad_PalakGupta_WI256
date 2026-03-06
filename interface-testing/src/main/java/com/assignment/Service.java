package com.assignment;

public class Service {

	private I i;
	
	public Service(I i) {
		this.i=i;
	}
	public void executeOnce() {
		i.abc();
	}
	public void executeMultipleTimes(int n) {
		for(int j=0;j<n;j++) {
			i.abc();
		}
	}
}

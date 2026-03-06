package com.assignment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class ServiceTest {

	@Test
	void testInterfaceMethodCalled() {
		I mockInterface=mock(I.class);
		Service service=new Service(mockInterface);
		
		service.executeOnce();
		
		verify(mockInterface).abc();
	}
	
	@Test
	void testVoidMethod() {
		I mockInterface = mock(I.class);
        Service service = new Service(mockInterface);

        doNothing().when(mockInterface).abc();

        service.executeOnce();

        verify(mockInterface, times(1)).abc();
	}
	
	@Test
    void testMethodCalledNTimes() {

        I mockInterface = mock(I.class);
        Service service = new Service(mockInterface);

        service.executeMultipleTimes(5);

        verify(mockInterface, times(5)).abc();
    }


}

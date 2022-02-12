package com.mercari.exceptionHandling;

/**
 * @author Nirmal.Shah
 *
 */
public enum ExceptionMesages {
	
	NoSuchElementException										("Web Element is not found on page"),
	StaleElementReferenceException								("Stale Element Exception throws by Test NG"),
	NullPointerException										("Null Pointer Exception throws by Test NG"),
	NoSuchWindowException										("No such window: target window already closed");
	
	private final String exceptionMsg;
	
	private ExceptionMesages(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	
	@Override
    public String toString() {
        return exceptionMsg;
    }

}

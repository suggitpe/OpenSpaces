package org.openspaces.testinglegacycode.exception;

/**
 * It is worth noting that this code was originally developed by sandro mancuso
 * for his blog on testing code with hardwired dependencies. This code has been
 * copied on the principle of simplicity for the purposes of running an
 * openspace based on the content of Sandro's blog entry.
 * 
 * @author Sandro Mancuso
 * @see http://craftedsw.blogspot.com/2011/07
 */
public class DependendClassCallDuringUnitTestException extends RuntimeException {

	private static final long serialVersionUID = -4584041339906109902L;

	public DependendClassCallDuringUnitTestException() {
		super();
	}

	public DependendClassCallDuringUnitTestException(String message,
			Throwable cause) {
		super(message, cause);
	}

	public DependendClassCallDuringUnitTestException(String message) {
		super(message);
	}

	public DependendClassCallDuringUnitTestException(Throwable cause) {
		super(cause);
	}

}

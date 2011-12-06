package org.openspaces.testinglegacycode.user;

import org.openspaces.testinglegacycode.exception.DependendClassCallDuringUnitTestException;

/**
 * It is worth noting that this code was originally developed by sandro mancuso
 * for his blog on testing code with hardwired dependencies. This code has been
 * copied on the principle of simplicity for the purposes of running an
 * openspace based on the content of Sandro's blog entry.
 * 
 * @author Sandro Mancuso
 * @see http://craftedsw.blogspot.com/2011/07
 */
public class UserSession {

	private static final UserSession userSession = new UserSession();

	private UserSession() {
	}

	public static UserSession getInstance() {
		return userSession;
	}

	public boolean isUserLoggedIn(User user) {
		throw new DependendClassCallDuringUnitTestException(
				"UserSession.isUserLoggedIn() should not be called in an unit test");
	}

	public User getLoggedUser() {
		throw new DependendClassCallDuringUnitTestException(
				"UserSession.getLoggedUser() should not be called in an unit test");
	}

}

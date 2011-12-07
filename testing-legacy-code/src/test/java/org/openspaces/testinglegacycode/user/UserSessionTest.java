package org.openspaces.testinglegacycode.user;

import org.junit.Test;
import org.openspaces.testinglegacycode.exception.DependendClassCallDuringUnitTestException;

public class UserSessionTest {

	@Test(expected = DependendClassCallDuringUnitTestException.class)
	public void shouldThrowExceptionWhenRetrievingLoggedUser() {
		UserSession.getInstance().getLoggedUser();
	}

	@Test(expected = DependendClassCallDuringUnitTestException.class)
	public void shouldThrowExceptionWhen() {
		UserSession.getInstance().isUserLoggedIn(new User());
	}

}

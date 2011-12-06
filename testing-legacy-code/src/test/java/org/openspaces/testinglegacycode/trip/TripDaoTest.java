package org.openspaces.testinglegacycode.trip;

import org.junit.Test;
import org.openspaces.testinglegacycode.exception.DependendClassCallDuringUnitTestException;
import org.openspaces.testinglegacycode.trip.TripDAO;
import org.openspaces.testinglegacycode.user.User;

public class TripDaoTest {

	@Test(expected = DependendClassCallDuringUnitTestException.class)
	public void shouldThrowExceptionWhenTripDaoCalled() {
		TripDAO.findTripsByUser(new User());
	}

}

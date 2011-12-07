package org.openspaces.testinglegacycode.trip;

import java.util.List;

import org.openspaces.testinglegacycode.exception.DependendClassCallDuringUnitTestException;
import org.openspaces.testinglegacycode.user.User;

/**
 * It is worth noting that this code was originally developed by sandro mancuso
 * for his blog on testing code with hardwired dependencies. This code has been
 * copied on the principle of simplicity for the purposes of running an
 * openspace based on the content of Sandro's blog entry.
 * 
 * @author Sandro Mancuso
 * @see http://craftedsw.blogspot.com/2011/07
 */
public class TripDAO {

	private TripDAO() {
	}

	public static List<Trip> findTripsByUser(User user) {
		throw new DependendClassCallDuringUnitTestException(
				"TripDAO should not be invoked on an unit test.");
	}

}
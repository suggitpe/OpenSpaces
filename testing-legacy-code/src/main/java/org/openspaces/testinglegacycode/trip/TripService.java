package org.openspaces.testinglegacycode.trip;

import java.util.ArrayList;
import java.util.List;

import org.openspaces.testinglegacycode.exception.UserNotLoggedInException;
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
public class TripService {

	public List<Trip> getFriendTrips(User loggedUser, User friend)
			throws UserNotLoggedInException {
		validate(loggedUser);
		return (friend.isFriendsWith(loggedUser)) ? findTripsForFriend(friend)
				: new ArrayList<Trip>();
	}

	private void validate(User loggedUser) throws UserNotLoggedInException {
		if (loggedUser == null)
			throw new UserNotLoggedInException();
	}

	protected List<Trip> findTripsForFriend(User friend) {
		return TripDAO.findTripsByUser(friend);
	}
}

package org.openspaces.testinglegacycode.trip;

import java.util.ArrayList;
import java.util.List;

import org.openspaces.testinglegacycode.exception.UserNotLoggedInException;
import org.openspaces.testinglegacycode.user.User;
import org.openspaces.testinglegacycode.user.UserSession;

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

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = UserSession.getInstance().getLoggedUser();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = TripDAO.findTripsByUser(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}

}

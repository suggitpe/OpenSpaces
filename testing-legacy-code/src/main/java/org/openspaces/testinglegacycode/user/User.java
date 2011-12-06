package org.openspaces.testinglegacycode.user;

import java.util.ArrayList;
import java.util.List;

import org.openspaces.testinglegacycode.trip.Trip;

/**
 * It is worth noting that this code was originally developed by sandro mancuso
 * for his blog on testing code with hardwired dependencies. This code has been
 * copied on the principle of simplicity for the purposes of running an
 * openspace based on the content of Sandro's blog entry.
 * 
 * @author Sandro Mancuso
 * @see http://craftedsw.blogspot.com/2011/07
 */
public class User {

	private List<Trip> trips = new ArrayList<Trip>();
	private List<User> friends = new ArrayList<User>();

	public List<User> getFriends() {
		return friends;
	}

	public void addFriend(User user) {
		friends.add(user);
	}

	public boolean isFriendsWith(User friend) {
		return friends.contains(friend);
	}

	public void addTrip(Trip trip) {
		trips.add(trip);
	}

	public List<Trip> trips() {
		return trips;
	}

}

package model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.User;
import model.dao.ClubDAO;
import model.Club;

public class ClubAnalysis {
private ClubDAO dao;
	
	public ClubAnalysis() {}
	
	public ClubAnalysis(ClubDAO dao) {
		super();
		this.dao = dao;
	}

	// an example business method
	/*public List<Club> recommendFriends(String cCode) throws Exception {
		Club thisclub = dao.getClubByCode(cCode);
		if (thisclub == null) {
			throw new Exception("invalid user ID!");
		}
		String mserver1 = thisclub.getClub_code();
		
		List<User> friends = new ArrayList<User>();
		Iterator<User> userIter = userList.iterator();		
		while (userIter.hasNext()) {
			User user = (User)userIter.next();
			
			if (user.getUserId().equals(userId)) continue;
			int m2 = user.getEmail().indexOf('@');
			if (m2 == -1) continue;
			String mserver2 = user.getEmail().substring(m2);

			if (mserver1.equals(mserver2)) 
				friends.add(user);		
		}
		return friends;
	}*/

}

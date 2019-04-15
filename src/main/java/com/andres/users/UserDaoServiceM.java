package com.andres.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoServiceM {

	private static int usersCount = 3;

	private static List<UserM> users = new ArrayList<>();

	static {
		users.add(new UserM(1, "Andres", new Date()));
		users.add(new UserM(2, "Pedro", new Date()));
		users.add(new UserM(3, "John", new Date()));

	}

	public List<UserM> findAll() {
		return users;

	}

	public UserM save(UserM user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	public UserM getOne(int id) {

		for (UserM user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public UserM deleteOne(int id) {
		Iterator<UserM> iterator = users.iterator();

		while (iterator.hasNext()) {
			UserM user = iterator.next();

			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}

		return null;
	}
}

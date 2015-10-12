package com.istore.common.b2b.entity;

public class AdminUser {
	private String username;
	private String password;
	private String storeId;
	private String users_id;
	public final String getUsername() {
		return username;
	}

	public final void setUsername(String username) {
		this.username = username;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final String getStoreId() {
		return storeId;
	}

	public final void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public final String getUsers_id() {
		return users_id;
	}

	public final void setUsers_id(String usersId) {
		users_id = usersId;
	}

}

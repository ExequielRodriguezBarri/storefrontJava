package model;

import java.util.UUID;
import java.util.Date;

public class AuditLog {
	public UUID _id;
	public Store _store;
	/**
	 * 1 -- 1 Store
	 */
	public User _user;
	/**
	 * 1 -- 1 User
	 */
	public String _action;
	public String _entityType;
	public UUID _entityId;
	public Date _timestamp;

	/**
	 * Operations
	 */
	public static AuditLog log(Store aStore, User aUser, String aAction, String aEntityType, UUID aEntityId) {
		throw new UnsupportedOperationException();
	}
}
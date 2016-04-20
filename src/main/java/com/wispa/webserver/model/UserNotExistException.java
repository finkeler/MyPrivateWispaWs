package com.wispa.webserver.model;


public final class UserNotExistException extends Exception {

	private static final long serialVersionUID = 1466595533298918701L;
	private static final String MESSAGE_ID_FORMAT = "User id '%d' does not exist";
	private static final String MESSAGE_NAME_FORMAT = "User name '%s' does not exist";


    public UserNotExistException(Long userId) {
        super(String.format(MESSAGE_ID_FORMAT, userId));
    }
    
    public UserNotExistException(String name) {
        super(String.format(MESSAGE_NAME_FORMAT, name));
    }
}
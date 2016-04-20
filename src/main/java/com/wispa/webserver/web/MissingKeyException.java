package com.wispa.webserver.web;

public final class MissingKeyException extends Exception {

	private static final long serialVersionUID = 6823955282025859105L;
	private static final String MESSAGE_FORMAT = "Payload is missing key '%s'";

    public MissingKeyException(String key) {
        super(String.format(MESSAGE_FORMAT, key));
    }

}
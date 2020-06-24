package com.tedu.sois.controller.ex;

/**
 * if the file has already been moved in the filesystem and is not available
 * anymore for another transfer
 */
public class FileStateException extends FileUploadException {

	private static final long serialVersionUID = -7150153238399934786L;

	public FileStateException() {
		super();
	}

	public FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileStateException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileStateException(String message) {
		super(message);
	}

	public FileStateException(Throwable cause) {
		super(cause);
	}

}

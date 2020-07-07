package com.tedu.sois.controller.ex;

/**
 * 上传的文件大小的异常
 */
public class FileSizeException extends FileUploadException {

	private static final long serialVersionUID = 3652563516851916279L;

	public FileSizeException() {
		super();
	}

	public FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileSizeException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileSizeException(String message) {
		super(message);
	}

	public FileSizeException(Throwable cause) {
		super(cause);
	}

}

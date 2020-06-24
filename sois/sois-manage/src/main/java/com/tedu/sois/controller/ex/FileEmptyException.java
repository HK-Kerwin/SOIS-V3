package com.tedu.sois.controller.ex;

/**
 * 上传的文件为空的异常，例如：没有在表单中选择上传的文件，或上传的文件是0字节的
 */
public class FileEmptyException extends FileUploadException {

	private static final long serialVersionUID = 1241017634099485103L;

	public FileEmptyException() {
		super();
	}

	public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileEmptyException(String message) {
		super(message);
	}

	public FileEmptyException(Throwable cause) {
		super(cause);
	}

}

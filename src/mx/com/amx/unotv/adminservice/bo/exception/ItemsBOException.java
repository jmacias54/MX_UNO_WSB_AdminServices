/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo.exception;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class ItemsBOException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new  Items BO exception.
	 */
	public ItemsBOException() {
		super();
	}

	/**
	 * Instantiates a new  Items BO exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ItemsBOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new  Items BO exception.
	 *
	 * @param message
	 *            the message
	 */
	public ItemsBOException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new  Items BO exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public ItemsBOException(Throwable cause) {
		super(cause);
	}

}

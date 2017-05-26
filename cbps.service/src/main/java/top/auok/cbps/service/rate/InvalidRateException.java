package top.auok.cbps.service.rate;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class InvalidRateException extends Exception {

	private static final long serialVersionUID = 1L;

	private static String getString(ExceptionMessage.KEY key) {
		return ResourceBundle.getBundle(ExceptionMessage.class.getName()).getString(key.toString());
	}

	private InvalidRateException(ExceptionMessage.KEY exceptionMessageKey, Object... arguments) {
		super(MessageFormat.format(getString(exceptionMessageKey), arguments));
	}

	public static class EmptyException extends InvalidRateException {
		private static final long serialVersionUID = 1L;

		public EmptyException() {
			super(ExceptionMessage.KEY.EMPTY_ITEM);
		}
	}
}

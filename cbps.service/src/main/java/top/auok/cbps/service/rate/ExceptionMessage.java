package top.auok.cbps.service.rate;

import java.util.ListResourceBundle;

public class ExceptionMessage extends ListResourceBundle {

	public enum KEY {
		EMPTY_ITEM
		;
	}

	private static final Object[][] contents = {
			{ KEY.EMPTY_ITEM.toString(), "费率明细不能为空" }, 
			};

	@Override
	protected Object[][] getContents() {
		return contents;
	}

}
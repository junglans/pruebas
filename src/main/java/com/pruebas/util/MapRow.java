package com.pruebas.util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapRow {

	public static final Map<String, String> mapRow(final List<String> headers, final List<String> row) {
		HashMap<String, String> map = new HashMap<String, String>();
		if (headers != null && !headers.isEmpty() && row != null && !row.isEmpty()) {
		    for (int ind = 0; ind< headers.size(); ind++) {
		    	String value = "";
		    	if (ind < row.size() && row.get(ind) != null) {
		    		value = row.get(ind);
		    	}
		    	map.put(headers.get(ind), value);
		    }
		}
		return map;
	}
}

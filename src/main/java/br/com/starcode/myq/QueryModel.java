package br.com.starcode.myq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryModel {
	
	protected Map<String, Object> argumentMap;
	protected List<Object> argumentList;
	
	public QueryModel() {
		argumentMap = new HashMap<>();
		argumentList = new ArrayList<>();
		argumentMap.put("P", argumentList);
	}
	
	public QueryModel add(String name, Object value) {
		argumentList.add(value);
		argumentMap.put(name, value);
		return this;
	}
	
	public QueryModel add(Object value) {
		argumentList.add(value);
		return this;
	}
	
	public List<Object> getArgumentList() {
		return argumentList;
	}
	
	public Map<String, Object> getArgumentMap() {
		return argumentMap;
	}

}

package br.com.starcode.myq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Query {

	final Logger logger = LoggerFactory.getLogger(Query.class);
	protected String id;
	protected String source;
	
	public Query(String id, String source) {
		this.id = id;
		this.source = source;
	}
	
	public String getSource() {
		return source;
	}
	
	public String getId() {
		return id;
	}
	
}

package br.com.starcode.myq.parser;

import br.com.starcode.myq.Query;
import br.com.starcode.myq.QueryModel;

public interface QueryParser {

	String parse(Query query);
	
	String parse(Query query, QueryModel model);
	
}
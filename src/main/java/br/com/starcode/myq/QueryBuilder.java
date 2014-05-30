package br.com.starcode.myq;

import br.com.starcode.myq.parser.QueryParser;

public class QueryBuilder {

	//final Logger logger = LoggerFactory.getLogger(QueryBuilder.class);
	protected Query query;
	protected QueryParser parser;
	protected QueryModel model;
	
	/*protected QueryBuilder(Query query, QueryParser parser) {
		this.query = query;
		this.parser = parser;
		this.model = new QueryModel();
	}*/
	
	protected QueryBuilder(Query query, QueryParser parser, QueryModel model) {
		this.query = query;
		this.parser = parser;
		if (model == null) {
			model = new QueryModel();
		}
		this.model = model;
	}
	
	public QueryBuilder add(String name, Object value) {
		model.add(name, value);
		return this;
	}
	
	public QueryBuilder add(Object value) {
		model.add(value);
		return this;
	}
	
	public String done() {
		return parser.parse(query, model);
	}
	
}

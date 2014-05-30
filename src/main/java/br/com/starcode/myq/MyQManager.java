package br.com.starcode.myq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyQManager {
	
	final Logger logger = LoggerFactory.getLogger(MyQManager.class);
	protected Map<String, Query> queryMap = null;
	protected MyQConfiguration configuration;
	
	protected MyQManager(MyQConfiguration configuration) {
		this.configuration = configuration;
		queryMap = new HashMap<>();
		
		if (configuration.getPreload()) {
			//configuration.getPreloadLocations();
			try {
				configuration.getQueryLoader().preload(configuration.getEncoding());
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
				throw new RuntimeException("Could not preload queries!", e);
			}
		}
		
	}
	
	protected Query create(String id) {
		try {
			if (configuration.getAutoExtension() != null) {
				id += configuration.getAutoExtension();
			}
			return new Query(id, configuration.getQueryLoader().load(id, configuration.getEncoding()));
		} catch (IOException e) {
			logger.error("Could not load query '" + id + "': " + e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		} 
	}

	public QueryBuilder get(String id, QueryModel model) {
		Query q = null;
		if (configuration.getCaching()) {
			q = queryMap.get(id);
			if (q == null) {
				q = create(id);
				queryMap.put(id, q);
			}
		} else {
			q = create(id); 
		}
		return new QueryBuilder(q, configuration.getQueryParser(), model);
	}
	
	public QueryBuilder get(String id) {
		return get(id, null);
	}
	
	public String getString(String id) {
		return get(id, null).done();
	}
	
	public String getString(String id, QueryModel model) {
		return get(id, model).done();
	}
	
}

package br.com.starcode.myq;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.starcode.myq.loader.CallerResourceQueryLoader;
import br.com.starcode.myq.loader.QueryLoader;
import br.com.starcode.myq.parser.FreeMarkerQueryParser;
import br.com.starcode.myq.parser.QueryParser;

public class MyQConfiguration {

	final Logger logger = LoggerFactory.getLogger(MyQConfiguration.class);
	
	private boolean preload;
	private List<String> preloadLocations; //needs a type to differenciate resources from directories
	private boolean caching;
	private QueryLoader queryLoader;
	private QueryParser queryParser;
	private String encoding;
	private String autoExtension;
	
	public MyQConfiguration() {
		this(false, null, true, null, null, null, ".sql");
	}
			
	public MyQConfiguration(
			boolean preload,
			List<String> preloadLocations,
			boolean caching,
			QueryLoader queryLoader,
			QueryParser queryParser,
			String encoding,
			String autoExtension) {
		this.preload = preload;
		this.caching = caching;
		
		this.autoExtension = autoExtension;
		
		//locations to preload queries
		this.preloadLocations = new ArrayList<>();
		if (preloadLocations != null) {
			this.preloadLocations.addAll(preloadLocations);
		}
		
		//encoding
		if (encoding == null) {
			encoding = "UTF-8";
		}
		this.encoding = encoding;
		
		//loader
		if (queryLoader == null) {
			queryLoader = new CallerResourceQueryLoader(false);
		}
		this.queryLoader = queryLoader;
		
		//parser
		if (queryParser == null) {
			queryParser = new FreeMarkerQueryParser();
		}
		this.queryParser = queryParser;
	}
	
	public List<String> getPreloadLocations() {
		return preloadLocations;
	}
	
	public boolean getCaching() {
		return caching;
	}
	
	public boolean getPreload() {
		return preload;
	}
	
	public QueryLoader getQueryLoader() {
		return queryLoader;
	}
	
	public QueryParser getQueryParser() {
		return queryParser;
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	public String getAutoExtension() {
		return autoExtension;
	}
	
}

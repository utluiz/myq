package br.com.starcode.myq;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.starcode.myq.loader.QueryLoader;
import br.com.starcode.myq.parser.QueryParser;

public class MyQManagerBuilder {

	final Logger logger = LoggerFactory.getLogger(MyQManagerBuilder.class);
	
	private boolean preload;
	private List<String> preloadLocations; //needs a type to differenciate resources from directories
	private boolean caching;
	private QueryLoader queryLoader;
	private QueryParser queryParser;
	private String encoding;
	private boolean configureStatic;
	private String autoExtension;
	
	protected MyQManagerBuilder(boolean configureStatic) {
		this();
		this.configureStatic = configureStatic;
	}
	
	public MyQManagerBuilder() {
		this.preload = false;
		this.preloadLocations = new ArrayList<>();
		this.caching = true;
		this.queryLoader = null;
		this.queryParser = null;
		this.encoding = null;
		this.autoExtension = ".sql";
	}
	
	public MyQManagerBuilder disableCache() {
		this.caching = false;
		return this;
	}
	
	public MyQManagerBuilder preloadQueries() {
		this.preload = true;
		return this;
	}
	
	public MyQManagerBuilder setLoader(QueryLoader queryLoader) {
		this.queryLoader = queryLoader;
		return this;
	}
	
	public MyQManagerBuilder setParser(QueryParser queryParser) {
		this.queryParser = queryParser;
		return this;
	}
	
	public MyQManagerBuilder encoding(String encoding) {
		this.encoding = encoding;
		return this;
	}
	
	public MyQManagerBuilder autoExtension(String autoExtension) {
		this.autoExtension = autoExtension;
		return this;
	}
	
	public MyQManager done() {
		MyQConfiguration conf = new MyQConfiguration(
				preload, 
				preloadLocations, 
				caching, 
				queryLoader, 
				queryParser, 
				encoding,
				autoExtension);
		MyQManager myQManager = new MyQManager(conf);
		if (configureStatic) {
			MyQ.init(myQManager);
		}
		return myQManager;
	}
	
}

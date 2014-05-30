package br.com.starcode.myq.loader;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.starcode.myq.util.QueryLoaderUtil;

public class CallerResourceQueryLoader implements QueryLoader {
	
	final Logger logger = LoggerFactory.getLogger(CallerResourceQueryLoader.class);
	protected boolean checkParentPaths;
	
	public CallerResourceQueryLoader(boolean checkParentPaths) {
		this.checkParentPaths = checkParentPaths;
	}
	
	@Override
	public String load(String id, String encoding) throws IOException {
		// finds the class calling MyQ
		StackTraceElement[] stack = new Throwable().getStackTrace();
		for (StackTraceElement stackTraceElement : stack) {
			
			// checks if the class is not from this package
			if (!stackTraceElement.getClassName().startsWith("br.com.starcode.myq.")) {
				
				// load class and tries to load the resource from it
				try {
					String pack = Class.forName(stackTraceElement.getClassName()).getPackage().getName();
					String path = QueryLoaderUtil.packageToPath(pack);
					return QueryLoaderUtil.loadResource(path, id, encoding, checkParentPaths);
				} catch (ClassNotFoundException e) {
					logger.error("Error loading class!", e);
					throw new RuntimeException(e);
				}
				
			}
			
		}
		throw new RuntimeException("Could not find the caller class!");
	}
	
	@Override
	public void preload(String encoding) throws IOException {
		// TODO preload queries?
	}
	
}

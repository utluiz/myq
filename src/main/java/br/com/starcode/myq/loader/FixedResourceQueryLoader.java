package br.com.starcode.myq.loader;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.starcode.myq.util.QueryLoaderUtil;

public class FixedResourceQueryLoader implements QueryLoader {
	
	final Logger logger = LoggerFactory.getLogger(FixedResourceQueryLoader.class);
	protected String basePath;
	protected boolean checkParentPaths;
	
	public FixedResourceQueryLoader(String basePackage, boolean checkParentPaths) {
		this.basePath = QueryLoaderUtil.packageToPath(basePackage);
		this.checkParentPaths = checkParentPaths;
	}
	
	public FixedResourceQueryLoader(Class<?> baseClass, boolean checkParentPaths) {
		this.basePath = QueryLoaderUtil.packageToPath(baseClass.getPackage().getName());
		this.checkParentPaths = checkParentPaths;
	}
	
	@Override
	public String load(String id, String encoding) throws IOException {
		return QueryLoaderUtil.loadResource(basePath, id, encoding, checkParentPaths);
	}

	@Override
	public void preload(String encoding) throws IOException {
		// TODO preload queries?
	}
	
}

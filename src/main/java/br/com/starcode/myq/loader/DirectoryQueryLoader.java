package br.com.starcode.myq.loader;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.starcode.myq.util.QueryLoaderUtil;

public class DirectoryQueryLoader implements QueryLoader {
	
	final Logger logger = LoggerFactory.getLogger(DirectoryQueryLoader.class);
	protected String baseDirectory;
	protected boolean checkSubdirs;
	
	public DirectoryQueryLoader(String baseDirectory, boolean checkSubdirs) {
		this.baseDirectory = baseDirectory;
		this.checkSubdirs = checkSubdirs;
	}
	
	@Override
	public String load(String id, String encoding) throws IOException {
		File file = new File(baseDirectory, id);
		if (checkSubdirs && !file.exists()) {
			file = QueryLoaderUtil.findFile(baseDirectory, id);
		}
		if (file == null || !file.exists()) {
			throw new IOException("File '" + id + "' not found " + 
					(checkSubdirs ? " even in subdirs" : "") + "!");
		}
		return QueryLoaderUtil.readFile(file.getAbsolutePath(), encoding);
	}
	
	@Override
	public void preload(String encoding) throws IOException {
		// TODO preload queries?
	}
	
}

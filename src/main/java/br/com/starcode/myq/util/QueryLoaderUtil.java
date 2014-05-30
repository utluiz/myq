package br.com.starcode.myq.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.starcode.myq.loader.FixedResourceQueryLoader;

public abstract class QueryLoaderUtil {
	
	final static Logger logger = LoggerFactory.getLogger(FixedResourceQueryLoader.class);
	
	public static String packageToPath(String basePackage) {
		return basePackage.replaceAll("\\.", "/") + "/";
	}
	
	public static String loadResource(String path, String resource, String encoding, boolean checkParentPaths) throws IOException {
		
		if (logger.isDebugEnabled()) logger.debug("Loading resource " + resource + " from path " + path + " with encoding " + encoding + " (parents = " + checkParentPaths + ")");
		
		//normalizes path
		InputStream input;
		String tmpPath = path.replaceAll("\\\\","/");
		if (tmpPath.endsWith("/")) {
			tmpPath = tmpPath.substring(0, path.length() - 1);
		}
		
		//checks the path for the resource and then the parent paths
		do {
			input = Thread.currentThread().getContextClassLoader().getResourceAsStream(tmpPath + "/" + resource);
			if (input == null) {
				int pos = tmpPath.lastIndexOf('/');
				if (pos > 0) {
					tmpPath = tmpPath.substring(0, pos);
				} else {
					break;
				}
			}
		} while (checkParentPaths && input == null && tmpPath != null);
		
		//exception if not found
		if (input == null) {
			throw new IOException("Resource not found in path '" + path + "'" +
					(checkParentPaths ? " nor in its parent paths" : "") + "!");
		}
		
		//return String
		return readInputStream(input, encoding);
		
	}
	
	public static String readInputStream(InputStream input, String encoding) throws IOException {
		try (java.util.Scanner s = new java.util.Scanner(input, encoding)) {
			return s.useDelimiter("\\A").hasNext() ? s.next() : ""; 	    
		}
	}

	public static String readFile(String path, String encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	/**
	 * TODO dir cache
	 */
	public static File findFile(String basePath, final String fileName) throws IOException  {
		
	    Path startPath = Paths.get(basePath);
	    final List<File> fileResult = new ArrayList<>();
	    
	    // search directories
	    Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
	        @Override
	        public FileVisitResult preVisitDirectory(Path dir,
	                BasicFileAttributes attrs) {
	            return FileVisitResult.CONTINUE;
	        }

	        @Override
	        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
	        	if (file.toFile().getName().equalsIgnoreCase(fileName)) {
	        		fileResult.add(file.toFile());
	        		return FileVisitResult.TERMINATE;
	        	}
	            return FileVisitResult.CONTINUE;
	        }

	        @Override
	        public FileVisitResult visitFileFailed(Path file, IOException e) {
	            return FileVisitResult.CONTINUE;
	        }
	    });
	    
	    // checks result
	    if (fileResult.isEmpty()) {
	    	return null;
	    }
	    return fileResult.get(0);
		    
	}

}

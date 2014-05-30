package br.com.starcode.myqtest.loader;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.starcode.myq.loader.CallerResourceQueryLoader;
import br.com.starcode.myq.loader.DirectoryQueryLoader;
import br.com.starcode.myq.loader.FixedResourceQueryLoader;
import br.com.starcode.myq.loader.QueryLoader;

public class QueryLoaderTest {
	
	final Logger logger = LoggerFactory.getLogger(QueryLoaderTest.class);
	
	String basePath = "src/test/resources/";

	@Test
	public void testCallerLoader() throws IOException {
		
		QueryLoader loader = new CallerResourceQueryLoader(false);
		String q = loader.load("loader_test1.sql", "UTF-8");
		Assert.assertEquals("select * from tab", q);
		
	}
	
	@Test( expected = IOException.class )
	public void testCallerLoaderError() throws IOException {
		
		QueryLoader loader = new CallerResourceQueryLoader(false);
		loader.load("loader_test2.sql", "UTF-8");
		
	}
	
	@Test
	public void testCallerLoaderParent() throws IOException {
		
		QueryLoader loader = new CallerResourceQueryLoader(true);
		String q = loader.load("loader_test2.sql", "UTF-8");
		Assert.assertEquals("select * from tab2", q);
		
	}
	
	@Test
	public void testDirectoryLoader() throws IOException {
		
		QueryLoader loader = new DirectoryQueryLoader(basePath + "br/com/starcode/myqtest", false);
		String q = loader.load("loader_test2.sql", "UTF-8");
		Assert.assertEquals("select * from tab2", q);
		
	}
	
	@Test( expected = IOException.class )
	public void testDirectoryLoaderError() throws IOException {
		
		QueryLoader loader = new DirectoryQueryLoader(basePath + "br/com/starcode/myqtest", false);
		loader.load("loader_test1.sql", "UTF-8");
		
	}
	
	@Test
	public void testDirectoryLoaderSubdir() throws IOException {
		
		QueryLoader loader = new DirectoryQueryLoader(basePath + "br/com/starcode/myqtest", true);
		String q = loader.load("loader_test1.sql", "UTF-8");
		Assert.assertEquals("select * from tab", q);
		
	}
	
	@Test
	public void testFixedResourceLoader() throws IOException {
		
		QueryLoader loader = new FixedResourceQueryLoader(QueryLoaderTest.class, false);
		String q = loader.load("loader_test1.sql", "UTF-8");
		Assert.assertEquals("select * from tab", q);
		
	}
	
	@Test( expected = IOException.class )
	public void testFixedResourceLoaderError() throws IOException {
		
		QueryLoader loader = new FixedResourceQueryLoader("br/com/starcode/myqtest/loader", false);
		loader.load("loader_test2.sql", "UTF-8");
		
	}
	
	@Test
	public void testFixedResourceLoaderSubdir() throws IOException {
		
		QueryLoader loader = new FixedResourceQueryLoader(QueryLoaderTest.class, true);
		String q = loader.load("loader_test2.sql", "UTF-8");
		Assert.assertEquals("select * from tab2", q);
		
	}
	
}

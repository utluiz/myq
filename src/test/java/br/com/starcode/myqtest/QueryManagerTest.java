package br.com.starcode.myqtest;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.starcode.myq.MyQManager;
import br.com.starcode.myq.MyQManagerBuilder;
import br.com.starcode.myq.loader.DirectoryQueryLoader;
import br.com.starcode.myq.loader.QueryLoader;

public class QueryManagerTest {
	
	final Logger logger = LoggerFactory.getLogger(QueryManagerTest.class);
	String basePath = "src/test/resources/";
	
	@Test
	public void basic() throws IOException {
		
		QueryLoader loader = new DirectoryQueryLoader(basePath + "br/com/starcode/myqtest", true);
		MyQManager manager = new MyQManagerBuilder().setLoader(loader).preloadQueries().done();
		
		String str = manager.getString("my_query");
		Assert.assertEquals("select * from table", str);
		
		String str2 = manager.getString("loader_test1");
		Assert.assertEquals("select * from tab", str2);
		
	}
	
	@Test (expected = IOException.class)
	public void notExisting() throws Throwable {
		
		QueryLoader loader = new DirectoryQueryLoader(basePath + "br/com/starcode/myqtest", false);
		MyQManager manager = new MyQManagerBuilder().setLoader(loader).preloadQueries().done();
		try {
			manager.get("not exists");
		} catch (RuntimeException e) {
			throw e.getCause();
		}
		
	}
	
}

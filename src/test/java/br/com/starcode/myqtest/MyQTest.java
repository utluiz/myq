package br.com.starcode.myqtest;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.starcode.myq.MyQ;
import br.com.starcode.myq.MyQManager;
import br.com.starcode.myq.QueryModel;
import br.com.starcode.myq.parser.MessageFormatQueryParser;

public class MyQTest {
	
	final Logger logger = LoggerFactory.getLogger(MyQTest.class);
	
	@Test
	public void testMyQGlobalConfig() throws IOException {
		//changes global config
		MyQ.configure().encoding("ISO-8859-1").done();
		
		//test accent
		String str = MyQ.getString("my_query_encoding");
		Assert.assertEquals("select 'josé' from dual", str);
		
		//restores configuration
		MyQ.configure().done();
	}
	
	@Test
	public void testMyQSimple() throws IOException {
		
		String str = MyQ.getString("my_query");
		Assert.assertEquals("select * from table", str);
		
		String str2 = MyQ.get("my_query2").add("any").done();
		Assert.assertEquals("select * from tab1\r\n", str2);
		
		String str3 = MyQ.getString("my_query2", new QueryModel().add("table", "tab2"));
		Assert.assertEquals("select * from tab2\r\n", str3);
		
		String str4 = MyQ.get("my_query2").add("table", "tab3").add("cond", "10").done();
		Assert.assertEquals("select * from tab3\r\nwhere field = 10\r\n", str4);
		
	}
	
	@Test
	public void testQueryManager() throws IOException {
		
		String q1 = MyQ.getString("my_query");
		
		MyQManager myq = MyQ.configure().disableCache().autoExtension(".ftl").done();
		String q2 = myq.getString("my_query", new QueryModel().add("table", "TB"));
		
		MyQManager myq2 = MyQ.configure()
				.disableCache().
				autoExtension(".txt")
				.setParser(new MessageFormatQueryParser())
				.done();
		String q3 = myq2.getString("my_query", new QueryModel().add("T"));
		
		Assert.assertEquals("select * from table", q1);
		Assert.assertEquals("select * from TB", q2);
		Assert.assertEquals("select * from T", q3);
		
		MyQ.configure().done();
		
	}
	
	// TODO test MyQManagerBuilder
	// TODO check organization of the main package
	// TODO preload
	
	
}

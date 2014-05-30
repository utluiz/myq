package br.com.starcode.myqtest.parser;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.starcode.myq.Query;
import br.com.starcode.myq.QueryModel;
import br.com.starcode.myq.parser.FreeMarkerQueryParser;
import br.com.starcode.myq.parser.MessageFormatQueryParser;

public class QueryParserTest {
	
	final Logger logger = LoggerFactory.getLogger(FreeMarkerQueryParser.class);
	
	@Test
	public void testFreemarkerParser() {
		
		Query q = new Query("q1", "select * from ${tabela} where ${P[1]}"); 
		QueryModel m = new QueryModel()
			.add("tabela", "Tab1")
			.add("1 = 1");
		
		String result = new FreeMarkerQueryParser().parse(q, m);
		Assert.assertEquals("select * from Tab1 where 1 = 1", result);
		
	}
	
	@Test(expected = RuntimeException.class)
	public void testFreemarkerParserExpressionError() {
		
		Query q = new Query("q1", "select * from ${tabela"); 
		new FreeMarkerQueryParser().parse(q);
		
	}
	
	@Test
	public void testFreemarkerParserSimple() {
			
		Query q = new Query("q1", "select * from tab"); 
		String result = new FreeMarkerQueryParser().parse(q);
		Assert.assertEquals("select * from tab", result);
		
	}
	
	@Test
	public void testMessageFormatParser() {
		
		Query q = new Query("q1", "select * from {0} where {1}"); 
		QueryModel m = new QueryModel()
			.add("tabela", "Tab1")
			.add("1 = 1");
		
		String result = new MessageFormatQueryParser().parse(q, m);
		Assert.assertEquals("select * from Tab1 where 1 = 1", result);
		
		result = new MessageFormatQueryParser().parse(q);
		Assert.assertEquals("select * from {0} where {1}", result);
		
	}
	
}

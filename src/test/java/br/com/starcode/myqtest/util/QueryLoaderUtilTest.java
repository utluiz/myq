package br.com.starcode.myqtest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.starcode.myq.parser.FreeMarkerQueryParser;
import br.com.starcode.myq.util.QueryLoaderUtil;

public class QueryLoaderUtilTest {
	
	final Logger logger = LoggerFactory.getLogger(FreeMarkerQueryParser.class);
	
	String basePath = "src/test/resources/";

	@Test
	public void testPackageToPath() {
		
		String path = QueryLoaderUtil.packageToPath(getClass().getPackage().getName());
		Assert.assertEquals("br/com/starcode/myqtest/util/", path);
		
	}
	
	@Test
	public void testReadFile() throws IOException {
		
		String path = basePath + "br/com/starcode/myqtest/util/resource.sql";
		logger.info(new File(path).getAbsolutePath());
		String content = QueryLoaderUtil.readFile(path, "UTF-8");
		Assert.assertEquals("select * from dual", content);
		
	}
	
	@Test
	public void testFindFile() throws IOException {
		
		String path = basePath + "br/com/starcode/myqtest/util/resource.sql";
		logger.info(new File(path).getAbsolutePath());
		File file = QueryLoaderUtil.findFile(basePath + "br/com/starcode/", "resource.sql");
		logger.info(file.getAbsolutePath());
		Assert.assertNotNull(file);
		
	}
	
	@Test
	public void testFindFileError() throws IOException {
		
		String path = basePath + "br/com/starcode/myqtest/util/resource.sql";
		logger.info(new File(path).getAbsolutePath());
		Assert.assertNull(QueryLoaderUtil.findFile(basePath + "br/com/starcode/", "resource not exist.sql"));
		
		
	}
	
	@Test
	public void testReadInputStream() throws IOException {
		
		String path = basePath + "br/com/starcode/myqtest/util/resource.sql";
		logger.info(new File(path).getAbsolutePath());
		String content = QueryLoaderUtil.readInputStream(new FileInputStream(path), "UTF-8");
		Assert.assertEquals("select * from dual", content);
		
	}
	
	@Test
	public void testLoadResource() throws IOException {
		
		String path = QueryLoaderUtil.loadResource("br/com/starcode/myqtest/util", "resource.sql", "UTF-8", false);
		Assert.assertEquals("select * from dual", path);
		
	}
	
	@Test
	public void testLoadResourceParent() throws IOException {
		
		String path = QueryLoaderUtil.loadResource("br/com/starcode/myqtest/util/sub/path", "resource.sql", "UTF-8", true);
		Assert.assertEquals("select * from dual", path);
		
	}
	
	@Test( expected = IOException.class )
	public void testLoadResourceParentError() throws IOException {
		
		String path = QueryLoaderUtil.loadResource("br/com/starcode/myqtest/util/sub/path", "resource.sql", "UTF-8", false);
		Assert.assertEquals("select * from dual", path);
		
	}
	
}

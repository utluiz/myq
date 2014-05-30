package br.com.starcode.myq.loader;

import java.io.IOException;

public interface QueryLoader {
	
	String load(String id, String encoding) throws IOException;
	
	void preload(String encoding) throws IOException;

}

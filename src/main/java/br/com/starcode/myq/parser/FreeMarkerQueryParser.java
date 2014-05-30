package br.com.starcode.myq.parser;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.starcode.myq.Query;
import br.com.starcode.myq.QueryModel;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class FreeMarkerQueryParser implements QueryParser {

	final Logger logger = LoggerFactory.getLogger(FreeMarkerQueryParser.class);
	protected Configuration cfg;
	protected StringTemplateLoader stringLoader;
	
	public FreeMarkerQueryParser() {
		
		// for configuration, see:
		// http://freemarker.org/docs/pgui_quickstart_createconfiguration.html
		cfg = new Configuration();
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));
		
		// configures the "template loader"
		stringLoader = new StringTemplateLoader();
		cfg.setTemplateLoader(stringLoader);
		
	}

	@Override
	public String parse(Query query) {
		return parse(query, null);
	}

	@Override
	public String parse(Query query, QueryModel model) {
		
		// checks if already exists in "template loader"
		if (stringLoader.findTemplateSource(query.getId()) == null) {
			
			// adds to template loader
			stringLoader.putTemplate(query.getId(), query.getSource());
			
		}
		
		// process the template
		try {
			Template template = cfg.getTemplate(query.getId());
			Writer out = new StringWriter(); 
			template.process(model != null ? model.getArgumentMap() : null, out);
			return out.toString();
		} catch (IOException | TemplateException e) {
			logger.error("Erro ao processar a query '" + query.getId() + "': " + e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		}
		
	}
	
	public Configuration getConfiguration() {
		return cfg;
	}
	
}

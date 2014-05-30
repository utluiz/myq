package br.com.starcode.myq.parser;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.starcode.myq.Query;
import br.com.starcode.myq.QueryModel;

public class MessageFormatQueryParser implements QueryParser {

	final Logger logger = LoggerFactory.getLogger(MessageFormatQueryParser.class);

	@Override
	public String parse(Query query) {
		return MessageFormat.format(query.getSource(), (Object[]) null);
	}

	@Override
	public String parse(Query query, QueryModel model) {
		return MessageFormat.format(query.getSource(), model.getArgumentList().toArray());
	}
	
}

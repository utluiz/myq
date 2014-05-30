package br.com.starcode.myq;


public abstract class MyQ {
	
	//final Logger logger = LoggerFactory.getLogger(MyQ.class);
	//protected Map<String, Query> queryMap = null;
	
	protected static MyQManager globalInstance;
	
	public static MyQManager getInstance() {
		if (globalInstance == null) {
			init(null);
		}
		return globalInstance;
	}
	
	public synchronized static void init(MyQManager myqManager) {
		if (myqManager == null) {
			myqManager = new MyQManager(new MyQConfiguration());
		}
		globalInstance = myqManager;
	}
	
	public static MyQManagerBuilder configure() {
		return new MyQManagerBuilder(true);
	}
	
	public static QueryBuilder get(String id) {
		return getInstance().get(id);
	}
	
	public static String getString(String id) {
		return getInstance().getString(id);
	}
	
	public static String getString(String id, QueryModel model) {
		return getInstance().getString(id, model);
	}
	
}

package package1;

import java.util.Map;

public interface GUObject {
	
	int id = 0;
	String tableName = "";
	
	public Void setProperties(Map<String, Object> dict);
	public Map<String, Object> convertToDict();
}

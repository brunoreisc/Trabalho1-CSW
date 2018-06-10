package package1;

import java.util.ArrayList;

public interface GUTable {

	public boolean createObject(GUObject object);
	public GUObject readObject(GUObject object);
	public ArrayList<GUObject> readAllObjects(GUObject object);
	public boolean updateObject(GUObject object);
	public boolean deleteObject(GUObject object);
}

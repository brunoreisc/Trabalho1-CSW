package package1;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class GUDatabaseManager implements GUTable {

	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	MongoDatabase database = mongoClient.getDatabase("Banco");

	@Override
	public boolean createObject(GUObject object) {
		MongoCollection<Document> collection = database.getCollection(object.getTableName());
		Document doc = new Document(object.convertToDict());
		try {
			collection.insertOne(doc);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public GUObject readObject(GUObject object) {
		MongoCollection<Document> collection = database.getCollection(object.getTableName());
		BasicDBObject query = new BasicDBObject("id", object.getId());
		try {
			Document dict = collection.find(query).first();
			object.setProperties(dict);
			return object;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<GUObject> readAllObjects(GUObject object) {
		MongoCollection<Document> collection = database.getCollection(object.getTableName());
		ArrayList<GUObject> list = new ArrayList<>();
		try {
			MongoCursor<Document> cursor = collection.find().iterator();
			while (cursor.hasNext()) {
				Document dict = cursor.next();
				object.setProperties(dict);
				list.add(object);
			}
		} catch (Exception e) {
		}
		return list;
	}

	@Override
	public boolean updateObject(GUObject object) {
		MongoCollection<Document> collection = database.getCollection(object.getTableName());
		BasicDBObject query = new BasicDBObject("id", object.getId());
		Document doc = new Document(object.convertToDict());
		try {
			return collection.replaceOne(query, doc).getModifiedCount() == 1;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteObject(GUObject object) {
		MongoCollection<Document> collection = database.getCollection(object.getTableName());
		BasicDBObject query = new BasicDBObject("id", object.getId());
		try {
			return collection.deleteOne(query).getDeletedCount() == 1;
		} catch (Exception e) {
			return false;
		}
	}
}

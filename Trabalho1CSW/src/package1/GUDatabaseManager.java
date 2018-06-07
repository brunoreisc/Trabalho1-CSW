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
	public void createObject(GUObject object) {
		MongoCollection<Document> collection = database.getCollection(object.getTableName());
		Document doc = new Document(object.convertToDict());
		collection.insertOne(doc);
	}

	@Override
	public GUObject readObject(GUObject object) {
		MongoCollection<Document> collection = database.getCollection(object.getTableName());
		BasicDBObject query = new BasicDBObject("id", object.getId());
		Document dict = collection.find(query).first();
		object.setProperties(dict);
		return object;
	}

	@Override
	public ArrayList<GUObject> readAllObjects(GUObject object) {
		MongoCollection<Document> collection = database.getCollection(object.getTableName());
		MongoCursor<Document> cursor = collection.find().iterator();
		ArrayList<GUObject> list = new ArrayList<>();
		while(cursor.hasNext()) {
			Document dict = cursor.next();
			object.setProperties(dict);
			list.add(object);
		}
		return list;
	}

	@Override
	public boolean updateObject(GUObject object) {
		MongoCollection<Document> collection = database.getCollection(object.getTableName());
		BasicDBObject query = new BasicDBObject("id", object.getId());
		Document doc = new Document(object.convertToDict());
		return collection.replaceOne(query, doc).getModifiedCount() == 1;
	}

	@Override
	public boolean deleteObject(GUObject object) {
		MongoCollection<Document> collection = database.getCollection(object.getTableName());
		BasicDBObject query = new BasicDBObject("id", object.getId());
		return collection.deleteOne(query).getDeletedCount() == 1;
	}

}

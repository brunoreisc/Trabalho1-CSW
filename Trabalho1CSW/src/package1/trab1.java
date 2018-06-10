package package1;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

public class trab1 {

	
	public static void main(String[] args) {
//		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//		MongoDatabase database = mongoClient.getDatabase("Banco");
//		MongoCollection<Document> collection = database.getCollection("Collection1");
//		
//		Document person = new Document("id", 1)
//				.append("name", "uriel")
//				.append("age", 22)
//				.append("address", new Document("line1", "heuheu")
//						.append("number", "229"));
//		
//		collection.insertOne(person);
//		
//		BasicDBObject query = new BasicDBObject("id", 1);
//		collection.replaceOne(query, person);
//		
//
//		MongoCursor<Document> cursor = collection.find().iterator();
//		while(cursor.hasNext()) {
//			Document obj = cursor.next();
//			System.out.println(obj.get("name"));
//		}
//		
//		mongoClient.close();
		
		GUDatabaseManager manager = new GUDatabaseManager();
		Pessoa p = new Pessoa();
//		p.cpf = "00109430212";
//		p.idade = 24;
		p.nome = "Ronaldo";
		p.setId(1);
		p.setTableName("Pessoa");
//		manager.createObject(p);
		manager.updateObject(p);
		manager.readObject(p);
		System.out.println(p.nome);
	}
}

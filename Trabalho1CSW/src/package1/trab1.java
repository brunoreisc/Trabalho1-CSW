package package1;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

public class trab1 {

	
	public static void main(String[] args) {
		GUDatabaseManager manager = new GUDatabaseManager();

		Pessoa pessoa = new Pessoa();
		pessoa.setId(1);
		pessoa.setTableName("Pessoa");

		pessoa.cpf = "00109430212";
		pessoa.idade = 24;
		pessoa.nome = "Ronaldo";

		// create
		manager.createObject(pessoa);

		System.out.println(pessoa.nome);
		System.out.println(pessoa.cpf);
		System.out.println(pessoa.idade);

		System.out.println("-------------------");
		
		// to update edit the object and call updateObject()
		pessoa.nome = "Roberto";
		// update
		manager.updateObject(pessoa);

		System.out.println(pessoa.nome);
		System.out.println(pessoa.cpf);
		System.out.println(pessoa.idade);

		System.out.println("-------------------");
		
		// to fetch from database you need to create a object from the
		// respective class and then set the id you want to query
		Pessoa mesmaPessoa = new Pessoa();
		mesmaPessoa.setId(1);
		mesmaPessoa.setTableName("Pessoa");

		// read
		mesmaPessoa = (Pessoa) manager.readObject(mesmaPessoa);

		System.out.println(mesmaPessoa.nome);
		System.out.println(mesmaPessoa.cpf);
		System.out.println(mesmaPessoa.idade);

		System.out.println("-------------------");
		// delete
		boolean success = manager.deleteObject(mesmaPessoa);
		System.out.println(success);
	}
}

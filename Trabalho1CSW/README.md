#  Trabalho 1 Construção de Software

## Operações CRUD para mongoDB
  O objetivo desta biblioteca é efetuar operações create, read, update and delete no banco de dados não relacional mongoDB.
  Como? Provêndo uma interface de comunicação para o seu sistema na qual ja realiza estas operações.
  
## Configurar o mongoDB

O primeiro passo é estar com o mongo rodando para podermos efetuar as operações sobre os dados.
No link a seguir você pode verificar como instalar e configurar o mongo em sua máquina:
https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/

#### Verificar porta 

Após rodar o mongo, verificar a porta na qual está recebendo conexões, caso não seja a porta 27017, você deve alterar o número da porta na linha 16 da classe GUDatabaseManager com a respectiva porta informado pelo mongo.

```
MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
```

## Efetuando Operações sobre uma classe

Para efetuar as operações implementadas pela GUDataBaseManager, a classe a ser salva deve herdar de GUObject. Além disso é necessário implementar os seguintes métodos:
 * setProperties(Map<String, Object> dict) 
 * Map<String, Object> convertToDict()
 
 Estes métodos são necessários para converter o objeto de seu estado atual para um dicionário que será salvo no mongo e vice-versa.
 
  ### Exemplo

```
public class Pessoa extends GUObject {
	
	String cpf;
	String nome;
	int idade;
	
	@Override
	public void setProperties(Map<String, Object> dict) {
		setId((int) dict.get("id"));
		cpf = (String) dict.get("cpf");
		nome = (String) dict.get("nome");
		idade = (int) dict.get("idade");
	}

	@Override
	public Map<String, Object> convertToDict() {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("id", getId());
		obj.put("cpf", cpf);
		obj.put("nome", nome);
		obj.put("idade", idade);
		return obj;
	}

}
```
 
 Cada objeto desta classe deve obrigatoriamente:
 * Possuir um ID
 * Possuir o nome da tabela

## Efetuando as operações
Após efetuar os passos anteriores sua classe está pronta para ser persistida e manipulada. A seguir um exemplo de como pode ser realizado as operações CRUD.

```
	GUDatabaseManager manager = new GUDatabaseManager();
    
	Pessoa pessoa = new Pessoa();
    	pessoa.setId(1);
	pessoa.setTableName("Pessoa");
		
    	pessoa.cpf = "00109430212";
	pessoa.idade = 24;
	pessoa.nome = "Ronaldo";

  	manager.createObject(p);
    
    	pessoa.nome = "Roberto";
	manager.updateObject(pessoa);
    
    
    	Pessoa mesmaPessoa = new Pessoa();
    	mesmaPessoa.setId(1);
	mesmaPessoa.setTableName("Pessoa");
    
	manager.readObject(mesmaPessoa);
    	manager.deleteObject(mesmaPessoa);
		
```

### Authors
[Gabriel Mocelin 🙋‍♂️](https://github.com/gaabrielmocelin)
[Uriel Battanoli 🙋‍♂️](https://github.com/urielbattanoli)

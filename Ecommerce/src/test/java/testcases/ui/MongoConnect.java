package testcases.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import commonutil.PropertyReader;
import constants.FilePath;

/**
 * Class to perform mongoDB operations
 * 
 * @author manish.gaur
 *
 */
public class MongoConnect {

	private MongoClient client;

	private MongoDatabase mongoDatabase;

	private MongoCollection<Document> mongoCollection;

	/** FieldName of a Mongo Document */
	private String fieldName;

	/** FieldValue of a Mongo Document */
	private Object fieldValue;

	/** Operator inside find query of a Mongo Document */
	private String operator;

	/**
	 * No argument constructor of MongoClient class
	 * 
	 * @author manish.gaur
	 */
	public MongoConnect() {

	}

	/**
	 * Constructor of MongoConnect class
	 * 
	 * @param database
	 *            - Database to connect with
	 * @param collection
	 *            - Collection to connect with
	 * @param fieldname
	 *            - fieldname inside query
	 * @param operator
	 *            - Operator
	 * @param value
	 *            - FieldValue inside query
	 * 
	 */
	public MongoConnect(String database, String collection, String fieldname, String operator, Object value,
			String fieldName) {
		this.fieldName = fieldName;
		this.fieldValue = value;
		this.operator = operator;
		mongoConnect(database);
		mongoCollection = getCollection(mongoDatabase, collection);
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * This method creates MongoClient
	 * 
	 * @param host
	 * @param port
	 * @return MongoClient instance
	 */
	public MongoClient getMongoClient(String host, String port) {
		return new MongoClient(host, Integer.getInteger(port, 27017));
	}

	/**
	 * This method is to return MongoDatabase Instance
	 * 
	 * @param mongo
	 *            - MongoClient instance
	 * @param database
	 *            - name of the database
	 * @return MongoDatabase Instance
	 */

	public MongoDatabase getMongoDatabase(MongoClient client, String database) {
		return client.getDatabase(database);

	}

	/**
	 * Method to get a single collection
	 * 
	 * @param mongoDatabase
	 *            - MongoDataBase instance
	 * @param collectionName
	 *            - Name of collection
	 * @return MongoCollection
	 */

	public MongoCollection<Document> getCollection(MongoDatabase mongoDatabase, String collectionName) {
		return mongoDatabase.getCollection(collectionName);
	}

	/**
	 * Method to get a list of all the collections in a database
	 * 
	 * @param mongoDatabase
	 *            - MongoDataBase instance
	 * 
	 * @return the list collections iterable interface
	 */

	public ListCollectionsIterable<Document> getAllCollection(MongoDatabase mongoDatabase) {
		return mongoDatabase.listCollections();
	}

	/**
	 * Method to get a list of all the collections in a database
	 * 
	 * @param mongoDatabase
	 *            - MongoDataBase instance
	 * 
	 * @return the list collections iterable interface
	 */

	public FindIterable<Document> getAllDocumentsInCollection(MongoCollection<Document> mongoCollection) {
		return mongoCollection.find();

	}

	/**
	 * Method to find all documents and then sort
	 * 
	 * @param key
	 * @param sortValue
	 * @return List<Document> - Result of query in form of a List
	 */

	public List<Document> findallAndSort(String key, int sortValue) {
		BasicDBObject db = new BasicDBObject(key, sortValue);
		FindIterable<Document> Iterable = mongoCollection.find().sort(db);
		List<Document> result = new ArrayList<Document>();

		for (Document doc : Iterable) {
			result.add(doc);

		}
		return result;
	}

	/**
	 * Method to find all documents then applying Sorting and Limit
	 * 
	 * @param key
	 * @param sortValue
	 * @return List<Document> - Result of query in form of a List
	 */
	public List<Document> findallSortwithLimit(String key, int sortValue, int limit) {
		int limitValue = 0;
		if (limit < 0) {
			limitValue = 1;

		}
		BasicDBObject db = new BasicDBObject(key, sortValue);
		FindIterable<Document> Iterable = mongoCollection.find().sort(db).limit(limitValue);
		List<Document> result = new ArrayList<Document>();

		for (Document doc : Iterable) {
			result.add(doc);

		}
		return result;
	}

	/**
	 * Method to find all documents then applying Limit
	 * 
	 * @param key
	 * @param sortValue
	 * @return List<Document> - Result of query in form of a List
	 */
	public List<Document> findallwithLimit(int limit) {
		int limitValue = 0;
		if (limit < 0) {
			limitValue = 1;
		}
		FindIterable<Document> Iterable = mongoCollection.find().limit(limitValue);
		List<Document> result = new ArrayList<Document>();

		for (Document doc : Iterable) {
			result.add(doc);

		}
		return result;
	}

	/**
	 * Method Find all the documents present inside a collection
	 * 
	 * @return List of String where Every String holds a document's json
	 * 
	 * @author manish.gaur
	 */
	public List<String> getDataAsList() {
		List<String> result = new ArrayList<>();
		// Document query = new Document();
		FindIterable<Document> iterDoc = mongoCollection.find();

		for (Document doc : iterDoc) {
			result.add(doc.toJson());
		}
		return result;

	}

	/**
	 * Constructor of MongoConnect Class It sets up MongoClient, MongoDatabase
	 * and MongoCollection
	 * 
	 * 
	 * @param database
	 *            - Name of the database to connect
	 * 
	 * @param collection
	 *            - Name of the collection inside database
	 */

	public MongoConnect(String database, String collection) {
		mongoConnect(database);
		mongoCollection = getCollection(mongoDatabase, collection);

	}

	/**
	 * This method initializes MongoClient and MongoDatabase
	 * 
	 * @param Database
	 */

	public void mongoConnect(String Database) {
		PropertyReader prop = new PropertyReader();
		client = getMongoClient("localhost", prop.getValue(FilePath.ENV_PROPERTIES, "mongo_dbport"));
		mongoDatabase = client.getDatabase(Database);
	}

	/**
	 * Method to construct a Map of username and password
	 * 
	 * @return username and password in Object[][]
	 * @author manish.gaur
	 */
	public Object[][] getMapDataFromMongo() {
		Map<String, String> datamap;
		int result_size = getDataAsList().size();
		Object[][] data = new Object[result_size][1];
		int row = 0;
		for (String jsonObject : getDataAsList()) {
			datamap = new HashMap<>();
			String username = (String) JsonReader.getJsonData(jsonObject, "username");
			String password = (String) JsonReader.getJsonData(jsonObject, "password");
			System.out.println(username + "--" + password);

			datamap = JsonReader.getJsonObjects(jsonObject);
			data[row][0] = datamap;
			row++;

		}
		return data;
	}

	/**
	 * This method will set the map data into Object[][]
	 * 
	 * @return Object[][] where it holds the mongo doucment data inform as Map
	 *         type
	 */

	public Object[][] getMapDataFromMongoDB() {
		Map<String, String> datamap;
		int result_size = getDataAsList().size();
		Object[][] data = new Object[result_size][1];
		int row = 0;
		for (String jsonObject : getDataAsList()) {

			datamap = new HashMap<>();
			datamap = JsonReader.getJsonObjects(jsonObject);
			data[row][0] = datamap;
			row++;

		}
		return data;
	}

	/**
	 * Method to run MongoQuery having Condition
	 * 
	 * @param fieldname
	 * @param operator
	 * @param value
	 * @return List<Document> - Result of query
	 */

	public List<Document> search(String fieldname, String operator, Object value) {
		List<Document> result = new ArrayList<>();
		Document query = new Document();
		this.fieldName = fieldName;
		this.fieldValue = value;
		this.operator = operator;
		switch (operator.toUpperCase()) {
		case "EQ":
			query.append(fieldname, new Document().append("$eq", value));
			break;
		case "NE":
			query.append(fieldname, new Document().append("$ne", value));
			break;
		case "GT":
			query.append(fieldname, new Document().append("$gt", value));
			break;
		case "GTE":
			query.append(fieldname, new Document().append("$gte", value));
			break;
		case "LT":
			query.append(fieldname, new Document().append("$lt", value));
			break;
		case "LTE":
			query.append(fieldname, new Document().append("$lte", value));
			break;
		case "IN":
			query.append(fieldname, new Document().append("$in", value));
			break;
		case "NIN":
			query.append(fieldname, new Document().append("$nin", value));
			break;
		default:
			break;
		}

		FindIterable<Document> iterable = mongoCollection.find(query);
		if (iterable != null) {
			for (Document doc : iterable) {
				result.add(doc);

			}
		}

		return result;

	}

	/**
	 * Method to run MongoQuery having Condition, sort
	 * 
	 * @param fieldname
	 * @param operator
	 * @param value
	 * @param sortKey
	 * @param sortValue
	 * @return List<Document> - Result of query
	 */

	public List<Document> search(String fieldname, String operator, Object value, String sortKey, int sortValue) {
		List<Document> result = new ArrayList<>();
		Document query = new Document();
		this.fieldName = fieldName;
		this.fieldValue = value;
		this.operator = operator;
		switch (operator.toUpperCase()) {
		case "EQ":
			query.append(fieldname, new Document().append("$eq", value));
			break;
		case "NE":
			query.append(fieldname, new Document().append("$ne", value));
			break;
		case "GT":
			query.append(fieldname, new Document().append("$gt", value));
			break;
		case "GTE":
			query.append(fieldname, new Document().append("$gte", value));
			break;
		case "LT":
			query.append(fieldname, new Document().append("$lt", value));
			break;
		case "LTE":
			query.append(fieldname, new Document().append("$lte", value));
			break;
		case "IN":
			query.append(fieldname, new Document().append("$in", value));
			break;
		case "NIN":
			query.append(fieldname, new Document().append("$nin", value));
			break;
		default:
			break;
		}

		BasicDBObject sortQuery = new BasicDBObject(sortKey, sortValue);
		FindIterable<Document> iterable = mongoCollection.find(query).sort(sortQuery);
		if (iterable != null) {
			for (Document doc : iterable) {
				result.add(doc);

			}
		}

		return result;

	}

	/**
	 * Method to run MongoQuery having Condition, sort and limit
	 * 
	 * @param fieldname
	 * @param operator
	 * @param value
	 * @param sortKey
	 * @param sortValue
	 * @param limit
	 * @return List<Document> - Result of query
	 */

	public List<Document> search(String fieldname, String operator, Object value, String sortKey, int sortValue,
			int limit) {
		List<Document> result = new ArrayList<>();
		Document query = new Document();
		this.fieldName = fieldName;
		this.fieldValue = value;
		this.operator = operator;
		switch (operator.toUpperCase()) {
		case "EQ":
			query.append(fieldname, new Document().append("$eq", value));
			break;
		case "NE":
			query.append(fieldname, new Document().append("$ne", value));
			break;
		case "GT":
			query.append(fieldname, new Document().append("$gt", value));
			break;
		case "GTE":
			query.append(fieldname, new Document().append("$gte", value));
			break;
		case "LT":
			query.append(fieldname, new Document().append("$lt", value));
			break;
		case "LTE":
			query.append(fieldname, new Document().append("$lte", value));
			break;
		case "IN":
			query.append(fieldname, new Document().append("$in", value));
			break;
		case "NIN":
			query.append(fieldname, new Document().append("$nin", value));
			break;
		default:
			break;
		}

		BasicDBObject sortQuery = new BasicDBObject(sortKey, sortValue);
		FindIterable<Document> iterable = mongoCollection.find(query).sort(sortQuery).limit(limit);
		if (iterable != null) {
			for (Document doc : iterable) {
				result.add(doc);

			}
		}

		return result;

	}

	public void mongoClientClose() {
		if (client != null)
			client.close();

	}

	public static void main(String[] args) {
		/*
		 * Mongo mongo = new Mongo("ecommerce", "logindetails"); Map<String,
		 * String> datamap; int result_size = mongo.getDataAsList().size();
		 * Object[][] data = new Object[result_size][1]; for (String jsonObject
		 * : mongo.getDataAsList()) { int row = 0; String username = (String)
		 * JsonReader.getJsonData(jsonObject, "username"); String password =
		 * (String) JsonReader.getJsonData(jsonObject, "password");
		 * System.out.println(username + "--" + password); datamap = new
		 * HashMap<>(); datamap.put(username, username); datamap.put(password,
		 * password); data[row][0] = datamap; row++;
		 * 
		 * }
		 */
		MongoConnect mongo = new MongoConnect("ecommerce", "logindetails");
		// System.out.println(mongo.search("username", "eq",
		// "mgaur101gmail.com"));
		System.out.println(mongo.findallwithLimit(1));

	}

}

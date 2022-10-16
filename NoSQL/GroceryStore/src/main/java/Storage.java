import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;

public class Storage {
    MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> storeColl;
    private MongoCollection<Document> productColl;

    public Storage() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        mongoClient = new MongoClient("127.0.0.1", 27017);
        database = mongoClient.getDatabase("test");
        storeColl = database.getCollection("stores");
        productColl = database.getCollection("products");
    }

    public void close() {
        mongoClient.close();
    }

    public void addStore(String name) {
        List<String> products = new ArrayList<>();
        Document doc = new Document()
                .append("name", name)
                .append("products", products);
        storeColl.insertOne(doc);
    }

    public void addProduct(String name, String price) throws NumberFormatException {
        int nPrise = Integer.parseInt(price);
        Document doc = new Document()
                .append("name", name)
                .append("price", nPrise);
        productColl.insertOne(doc);
    }

    public void exposeProduct(String product, String store) throws StorageException {
        long count = productColl.countDocuments(eq("name", product));
        if (count == 0) {
            throw new StorageException("На складе нет товара \"" + product + "\"");
        }
        count = storeColl.countDocuments(eq("name", store));
        if (count == 0) {
            throw new StorageException("Нет магазина с названием \"" + store + "\"");
        }

        Bson filter = Filters.eq("name", store);
        Bson update = Updates.addToSet("products", product);
        storeColl.findOneAndUpdate(filter, update);
    }

    public void showProductStatistics() {
        BasicDBObject fCond = new BasicDBObject("$cond",
                Arrays.asList(new BasicDBObject("$lt",
                        Arrays.asList("$price", 100)), 1, 0)
        );

        AggregateIterable agg = productColl.aggregate(Arrays.asList(
                lookup("stores", "name", "products", "stores_list"),
                unwind("$stores_list"),
                group("$stores_list.name",
                        sum("totalProducts", 1),
                        avg("avgPrice", "$price"),
                        min("minPrice", "$price"),
                        max("maxPrice", "$price"),
                        sum("cheapProducts", new BasicDBObject(
                                "$cond", Arrays.asList(new BasicDBObject(
                                "$lt", Arrays.asList("$price", 100)), 1, 0)))
                )
        ));
        agg.forEach((Consumer<Document>) doc -> {
            System.out.println(doc.get("_id"));
            System.out.printf("%-34s%5d%n", "\tКоличество наименований товаров:", doc.get("totalProducts"));
            System.out.printf("%-34s%8.2f%n", "\tСредняя цена товаров:", doc.get("avgPrice"));
            System.out.printf("%-34s%5d%n", "\tСамый дешёвый товар:", doc.get("minPrice"));
            System.out.printf("%-34s%5d%n", "\tСамый дорогой товар:", doc.get("maxPrice"));
            System.out.printf("%-34s%5d%n", "\tКоличество дешёвых товаров:", doc.get("cheapProducts"));
        });
    }

    public void dropStore(String name) {
        if (name == null) {
            storeColl.drop();
            return;
        }
        storeColl.findOneAndDelete(eq("name", name));
    }

    public void dropProduct(String name) {
        if (name == null) {
            productColl.drop();
            return;
        }
        Bson filter = Filters.all("products", name);
        Bson update = Updates.pull("products", name);
        storeColl.updateMany(filter, update);

        productColl.findOneAndDelete(eq("name", name));
    }

    public void listStores() {
        FindIterable<Document> stores = storeColl.find();
        stores.forEach((Consumer<Document>) doc -> System.out.println(doc.get("name")));
    }

    public void listProducts() {
        FindIterable<Document> products = productColl.find();
        products.forEach((Consumer<Document>) doc -> System.out.println(doc.get("name")));
    }
}

class StorageException extends Exception {
    public StorageException(String message) {
        super(message);
    }
}
package src;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class Main {

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("Films");
        MongoCollection mongoCollection = mongoDatabase.getCollection("Best films eveer!");


//        ODPOWIEDNIE METODY AKTYWUJĄ DANE ZADANIE

//        addFilm(mongoCollection);
//        readFilm(mongoCollection, "Title", "Titanic");
//        deleteFilm(mongoCollection, "Title", "Titanic");
//        updateFilm(mongoCollection);

    }

    private static void updateFilm(MongoCollection mongoCollection) {

        Bson getDocument = Filters.eq("Title", "Titanic");
        Bson newDocument = combine(set("Title", "Titanic 1997"), set("Director", "James Cameron"));
        mongoCollection.updateOne(getDocument, newDocument);

    }

    private static void readFilm(MongoCollection mongoCollection, String param, Object value) {

        Document document = new Document();
        document.put(param, value);
        Document doc = (Document) mongoCollection.find(document).first();
        System.out.println(doc.toJson());

    }


    private static void deleteFilm(MongoCollection mongoCollection, String param, Object value) {

        Document document = new Document();
        document.put(param, value);
        mongoCollection.deleteOne(document);

    }

    private static void addFilm(MongoCollection mongoCollection) {


        Document film1 = new Document();
        film1.put("Title", "Titanic");
        film1.put("ProductionYear", "1997");

        Document film2 = new Document();
        film2.put("Title", "Braveheart");
        film2.put("ProductionYear", "1995");
        film2.put("Director", " Mel Gibson");

        Document film3 = new Document();
        film3.put("Title", "The Last Samurai");
        film3.put("ProductionYear", "2003");
        film3.put("Director", " Mel Gibson");
        film3.put("Actors", Arrays.asList("Tom Cruise", "Ken Watanabe", "Billy Connolly"));

        Document film4 = new Document();
        film4.put("Title", "Matrix");
        film4.put("ProductionYear", "1999");


        mongoCollection.insertMany(Arrays.asList(film4));
    }


}



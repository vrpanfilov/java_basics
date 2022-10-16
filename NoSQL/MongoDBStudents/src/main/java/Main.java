import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static MongoDatabase database;

    public static void main(String[] args) throws Exception {
        createMongoStudents();

        System.out.println();
        printTotalStudentNumber();
        printStudentNumberOlderThan40Years();
        printYongestStudentNames();
        printOldestStudentsCourses();
        System.out.println();
    }

    private static void createMongoStudents() throws Exception {
        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );
        database = mongoClient.getDatabase("local");
        MongoCollection<Document> collection = database.getCollection("students");
        collection.drop();

        final String pathToCsv = "Data/mongo.csv";
        try (BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                Student student = Student.init(row);
                Document docStudent = new Document()
                        .append("name", student.getName())
                        .append("age", student.getAge())
                        .append("courses", student.getCourses());
                collection.insertOne(docStudent);
            }
        }
    }

    private static void printTotalStudentNumber() {
        MongoCollection<Document> collection = database.getCollection("students");
        System.out.println("Общее количество студентов в базе: " + collection.countDocuments());
    }

    private static void printStudentNumberOlderThan40Years() {
        MongoCollection<Document> collection = database.getCollection("students");
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("age", new BasicDBObject("$gt", 40));
        List<Document> list = collection.find(gtQuery).into(new ArrayList<>());
        System.out.println("Количество студентов старше 40 лет: " + list.size());
    }

    private static void printYongestStudentNames() {
        MongoCollection<Document> collection = database.getCollection("students");
        FindIterable<Document> iterDoc = collection.find().sort(Sorts.ascending("age"));
        System.out.print("Имена самых молодых студентов:");
        int minAge = 100;
        boolean first = true;
        for (Object o : iterDoc) {
            Document doc = (Document) o;
            int age = (Integer) doc.get("age");
            if (age > minAge) {
                break;
            }
            minAge = age;
            String name = (first ? " " : ", ") + doc.get("name");
            first = false;
            System.out.print(name);
        }
        System.out.println();
    }

    private static void printOldestStudentsCourses() {
        MongoCollection<Document> collection = database.getCollection("students");
        FindIterable<Document> iterDoc = collection.find().sort(Sorts.descending("age"));
        System.out.print("Списки курсов самых старых студентов:");
        int maxAge = 0;
        boolean first = true;
        for (Object o : iterDoc) {
            Document doc = (Document) o;
            int age = (Integer) doc.get("age");
            if (age < maxAge) {
                break;
            }
            maxAge = age;
            List<String> courses = (ArrayList<String>)doc.get("courses");
            String coursesStr = (first ? " " : ", ") + courses;
            first = false;
            System.out.print(coursesStr);
        }
    }
}
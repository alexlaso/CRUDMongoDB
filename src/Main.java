import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.bson.Document;
import com.mongodb.client.AggregateIterable;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
    MongoClient mongoClient = new MongoClient(
            new MongoClientURI(
                    "mongodb://localhost:27017/"
            )
    );
    MongoDatabase database = mongoClient.getDatabase("Profesores");
    MongoCollection<Document> collection = database.getCollection("Coches_Profesores");


    insertado(collection);
    borrado(collection);
    }

    public static void insertado(MongoCollection<Document> collection){

        ArrayList<Document> lista = new ArrayList<Document>();

        Document document = new Document()
                .append("nombre", "María")
                .append("apellidos", "Suárez Manrique")
                .append("especialidad", Arrays.asList("biología"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Lexus");
        lista.add(document);
        Document documentDos = new Document()
                .append("nombre", "Jose Luis")
                .append("apellidos", "López Pérez")
                .append("especialidad", Arrays.asList("matemáticas", "física"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Toyota");
        lista.add(documentDos);
        Document documentTres = new Document()
                .append("nombre", "Antonio")
                .append("apellidos", "Munguía Arteche")
                .append("especialidad", Arrays.asList("física, química"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Volvo");
        lista.add(documentTres);
        Document documentCuatro = new Document()
                .append("nombre", "Fernando")
                .append("apellidos", "Delgado De La Fuente")
                .append("especialidad", Arrays.asList("física"))
                .append("esTitular", false)
                .append("esAsociado", true)
                .append("vehiculo", "Yamaha");
        lista.add(documentCuatro);
        Document documentCinco = new Document()
                .append("nombre", "Elena")
                .append("apellidos", "Hérnandez Serafín")
                .append("especialidad", Arrays.asList("matemáticas, física"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Seat");
        lista.add(documentCinco);
        Document documentSeis = new Document()
                .append("nombre", "Miguel")
                .append("apellidos", "Jimenes Ochoa")
                .append("especialidad", Arrays.asList("biología"))
                .append("esTitular", true)
                .append("esAsociado", true)
                .append("vehiculo", "Seat");
        lista.add(documentSeis);
        Document documentSiete = new Document()
                .append("nombre", "Paco")
                .append("apellidos", "Fernandez Garcia")
                .append("especialidad", Arrays.asList("matemáticas, geología"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Renault");
        lista.add(documentSiete);
        Document documentOcho = new Document()
                .append("nombre", "Blanca")
                .append("apellidos", "Gutierrez Ochoa")
                .append("especialidad", Arrays.asList("física, química"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Renault");
        lista.add(documentOcho);
        Document documentNueve = new Document()
                .append("nombre", "Laura")
                .append("apellidos", "Garcia Ochoa")
                .append("especialidad", Arrays.asList("biología"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Peugeot");
        lista.add(documentNueve);

        collection.insertMany(lista);
    }

    public static void borrado(MongoCollection<Document> collection){
    }
}
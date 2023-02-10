import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
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
    update(collection);
    agregar(collection);
    listado(collection);
    ordenado(collection);
    primeros(collection);
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
                .append("especialidad", Arrays.asList("física", "química"))
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
                .append("especialidad", Arrays.asList("matemáticas", "física"))
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
                .append("especialidad", Arrays.asList("matemáticas", "geología"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Renault");
        lista.add(documentSiete);
        Document documentOcho = new Document()
                .append("nombre", "Blanca")
                .append("apellidos", "Gutierrez Ochoa")
                .append("especialidad", Arrays.asList("física", "química"))
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
        /*
        Opcion 2
        Bson query = eq("nombre","Antonio");
        DeleteResult deleteado = collection.deleteOne(query);
         */
        DeleteResult deleteado = collection.deleteOne(Filters.eq("nombre","Antonio"));
        System.out.println("Se ha borrado correctamente: " + deleteado.wasAcknowledged());
    }

    public static void update(MongoCollection<Document> collection){
        Bson filtro = Filters.eq("esAsociado",false);
        Bson accion = Updates.set("esAsociado",true);
        UpdateResult cambios = collection.updateMany(filtro,accion);
        System.out.println("Se han actualizado correctamente: " + cambios.getModifiedCount() + " documentos.");
    }

    public static void agregar(MongoCollection<Document> collection){
        Bson filtro = Filters.eq("especialidad","biología");
        Bson cambios = Updates.set("edad",30);
        UpdateResult actualizasao = collection.updateMany(filtro,cambios);
        System.out.println("Se ha añadido la edad corectamente a: "+actualizasao.getModifiedCount()+" documentos.");
    }

    public static void listado(MongoCollection<Document> collection){
        FindIterable<Document> iterDoc = collection.find();
        Iterator it = iterDoc.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    public static void ordenado(MongoCollection<Document> collection){
        List<Document> list = new ArrayList<>();
        collection.find().sort(Sorts.ascending("vehiculo")).into(list);
        for (Document doc : list){
            System.out.println(doc.toJson());
        }
    }

    public static void primeros(MongoCollection<Document> collection){
        List<Document> list = new ArrayList<>();
        collection.find().limit(5).into(list);
        for(Document doc : list){
            System.out.println(doc.toJson());
        }
    }
}

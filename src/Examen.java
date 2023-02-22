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
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Examen {
    public static void main(String[] args){
        MongoClient mongoClient = new MongoClient(
                new MongoClientURI(
                        "mongodb://localhost:27017/"
                )
        );
        MongoDatabase database = mongoClient.getDatabase("Granja");
        MongoCollection<Document> collection = database.getCollection("Granjeros");

        System.out.println("\n EJERCICIO A");
        apartadoA(collection);

        System.out.println("\n EJERCICIO B");
        apartadoB(collection);

        System.out.println("\n EJERCICIO C");
        apartadoC(collection);

        System.out.println("\n EJERCICIO D");
        apartadoD(collection);
    }

    public static void apartadoA(MongoCollection<Document> collection){
        Bson filtro = Filters.and(Filters.eq("descripcion", "agresivo"),Filters.eq("nivel", 10));
        FindIterable<Document> iterDoc = collection.find(filtro);
        Iterator it = iterDoc.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    public static void apartadoB(MongoCollection<Document> collection){
        Bson filtro = Filters.eq("descripcion", "estándar");
        List<Document> list = new ArrayList<>();
        collection.find(filtro).limit(6).sort(Sorts.descending("descripcion")).into(list);
        for (Document doc : list){
            System.out.println(doc.toJson());
        }
    }

    public static void apartadoC(MongoCollection<Document> collection){
        Bson filtro = Filters.gt("nivel",5);
        Bson update = Updates.set("puntos",100);
        UpdateResult actualizacion = collection.updateMany(filtro, update);
        System.out.println("Se han actualizado correctamente: " + actualizacion.getModifiedCount()+" documentos.");
    }

    public static void apartadoD(MongoCollection<Document> collection){
        DeleteResult deleteado = collection.deleteMany(Filters.gt("dinero",200));
        System.out.println("Se han borrado un total de: "+deleteado.getDeletedCount()+" documentos.");
        System.out.println("Se ha borrado correctamente: " + deleteado.wasAcknowledged());
    }
}

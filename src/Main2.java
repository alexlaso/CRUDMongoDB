import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import java.util.Arrays;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Main2 {
    public static void main(String[] args) {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build()
        );
        MongoDatabase database = mongoClient.getDatabase("Profesores").withCodecRegistry(pojoCodecRegistry);
        MongoCollection<Profesor> collection = database.getCollection("Coches_Profesores", Profesor.class);


        insertadoPOJO(collection);

        borradoPOJO(collection);

        updatePOJO(collection);

        agregadoPOJO(collection);

        listadoPOJO(collection);

        ordenadoPOJO(collection);

        primerosPOJO(collection);
    }

    public static void insertadoPOJO(MongoCollection<Profesor> collection) {
        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        Profesor profesor = new Profesor("María", "Suárez Manrique", new ArrayList<>(), true, false, "Lexus");
        profesor.getEspecialidad().add("biología");
        listaProfesores.add(profesor);
        Profesor profesorDos = new Profesor("Jose Luís", "López Pérez", new ArrayList<>(), true, false, "Toyota");
        profesorDos.getEspecialidad().add("matemáticas");
        profesorDos.getEspecialidad().add("física");
        listaProfesores.add(profesorDos);
        Profesor profesorTres = new Profesor("Antonio", "Munguía Arteche", new ArrayList<>(), true, false, "Volvo");
        profesorTres.getEspecialidad().add("física");
        profesorTres.getEspecialidad().add("química");
        listaProfesores.add(profesorTres);
        Profesor profesorCuatro = new Profesor("Fernando", "Delgado de la Fuente", new ArrayList<>(), false, true, "Yamaha");
        profesorCuatro.getEspecialidad().add("física");
        listaProfesores.add(profesorCuatro);
        Profesor profesorCinco = new Profesor();
        profesorCinco.getEspecialidad().add("");
        listaProfesores.add(profesorCinco);
        Profesor profesorSeis = new Profesor("Elena", "Hérnandez Serafín", new ArrayList<>(), true, false, "Seat");
        profesorSeis.getEspecialidad().add("matemáticas");
        profesorSeis.getEspecialidad().add("física");
        listaProfesores.add(profesorSeis);
        Profesor profesorSiete = new Profesor("Miguel", "Jimenes Ochoa", new ArrayList<>(), true, true, "Seat");
        profesorSiete.getEspecialidad().add("biología");
        listaProfesores.add(profesorSiete);
        Profesor profesorOcho = new Profesor("Paco", "Fernandez García", new ArrayList<>(), true, false, "Renault");
        profesorOcho.getEspecialidad().add("matemáticas");
        profesorOcho.getEspecialidad().add("geología");
        listaProfesores.add(profesorOcho);
        Profesor profesorNueve = new Profesor("Blanca", "Gutierrez Ochoa", new ArrayList<>(), true, false, "Renault");
        profesorNueve.getEspecialidad().add("física");
        profesorNueve.getEspecialidad().add("química");
        listaProfesores.add(profesorNueve);
        Profesor profesorDiez = new Profesor("", "", new ArrayList<>(), true, false, "Peugeot");
        profesorDiez.getEspecialidad().add("biología");
        listaProfesores.add(profesorDiez);

        collection.insertMany(listaProfesores);
    }

    public static void borradoPOJO(MongoCollection<Profesor> collection) {
        DeleteResult deleteResult = collection.deleteOne(Filters.eq("nombre", "Antonio"));
        System.out.println("Se ha borrado correctamente: " + deleteResult.wasAcknowledged());
    }

    public static void updatePOJO(MongoCollection<Profesor> collection) {
        Bson filtro = Filters.eq("esAsociado", false);
        Bson accion = Updates.set("esAsociado", true);
        UpdateResult cambios = collection.updateMany(filtro, accion);
        System.out.println("Se han actualizado correctamente: " + cambios.getModifiedCount() + " documentos.");
    }

    public static void agregadoPOJO(MongoCollection<Profesor> collection) {
        Bson filtro = Filters.eq("especialidad", "biología");
        Bson cambios = Updates.set("edad", 30);
        UpdateResult actualizasao = collection.updateMany(filtro, cambios);
        System.out.println("Se ha añadido la edad corectamente a: " + actualizasao.getModifiedCount() + " documentos.");
    }

    public static void listadoPOJO(MongoCollection<Profesor> collection) {
        FindIterable<Profesor> iterDoc = collection.find();
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public static void ordenadoPOJO(MongoCollection<Profesor> collection) {
        List<Profesor> list = new ArrayList<>();
        collection.find().sort(Sorts.ascending("vehiculo")).into(list);
        for (Profesor prof : list) {
            System.out.println(prof.toString());
        }
    }

    public static void primerosPOJO(MongoCollection<Profesor> collection) {
        List<Profesor> list = new ArrayList<>();
        collection.find().limit(5).into(list);
        for (Profesor prof : list) {
            System.out.println(prof.toString());
        }
    }
}
package fipp.leandro.vagasonlinebe.services;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import fipp.leandro.vagasonlinebe.entities.Cargo;
// ...
import fipp.leandro.vagasonlinebe.util.Conexao;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CargoService {
    private MongoDatabase database;

    public CargoService(){
        this.database = Conexao.getInstance().getDatabase();
    }

    public List<Cargo> getAll() throws Exception {
        List<Cargo> cargosList = new ArrayList<>();
        try {
            MongoCollection<Document> collection = database.getCollection("cargos");
            MongoCursor<Document> cursor = collection.find().iterator();

            while (cursor.hasNext())
                cargosList.add(new Gson().fromJson(cursor.next().toJson(), Cargo.class));
        }
        catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao recuperar cargos", e);
        }
        return cargosList;
    }
}
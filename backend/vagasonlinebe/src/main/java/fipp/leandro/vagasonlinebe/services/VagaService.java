package fipp.leandro.vagasonlinebe.services;

import com.google.gson.Gson;
import com.mongodb.client.*;
import fipp.leandro.vagasonlinebe.entities.Vaga;
import fipp.leandro.vagasonlinebe.util.Conexao; // 1. Importar nossa classe Conexao
import org.bson.Document;
// import org.springframework.beans.factory.annotation.Autowired; // 2. Não precisamos mais
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;

@Service
public class VagaService {
    private MongoDatabase database;

    public VagaService() {
        this.database = Conexao.getInstance().getDatabase();
    }

    public List<Vaga> getAll() {
        List<Vaga> vagaList = new ArrayList<>();
        try {
            MongoCollection<Document> collection = database.getCollection("vagas");
            MongoCursor<Document> cursor = collection.find().iterator();

            while (cursor.hasNext())
                vagaList.add(new Gson().fromJson(cursor.next().toJson(), Vaga.class));
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return vagaList;
    }

    public Vaga getOne(String registro) {
        Vaga vaga = null;
        try {
            MongoCollection<Document> collection = database.getCollection("vagas");

            Bson filter = eq("registro", registro);

            Document doc = collection.find(filter).first();

            if (doc != null) {
                vaga = new Gson().fromJson(doc.toJson(), Vaga.class);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return vaga;
    }
}
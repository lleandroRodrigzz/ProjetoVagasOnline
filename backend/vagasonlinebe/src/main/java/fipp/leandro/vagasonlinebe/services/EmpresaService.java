package fipp.leandro.vagasonlinebe.services;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import fipp.leandro.vagasonlinebe.entities.Empresa;
import fipp.leandro.vagasonlinebe.util.Conexao;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {
    private MongoDatabase database;

    public EmpresaService(){
        this.database = Conexao.getInstance().getDatabase();
    }

    public List<Empresa> getAll() throws  Exception {
        List<Empresa> empresasList = new ArrayList<>();
        try {
            MongoCollection<Document> collection = database.getCollection("empresas");
            MongoCursor<Document> cursor = collection.find().iterator();

            while (cursor.hasNext())
                empresasList.add(new Gson().fromJson(cursor.next().toJson(), Empresa.class));
        }
        catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao recuperar empresas", e);
        }
        return empresasList;
    }
}

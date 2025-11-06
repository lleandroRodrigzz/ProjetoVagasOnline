package fipp.leandro.vagasonlinebe.services;

import com.mongodb.client.MongoDatabase;
import fipp.leandro.vagasonlinebe.util.Conexao;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    private MongoDatabase database;

    public EmpresaService(){
        this.database = Conexao.getInstance().getDatabase();
    }
}

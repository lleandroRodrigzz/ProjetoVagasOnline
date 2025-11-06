package fipp.leandro.vagasonlinebe.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Conexao {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "vagas_online";

    private static Conexao instance;

    private final MongoClient mongoClient;
    private final MongoDatabase database;

    private Conexao() {
        try {
            this.mongoClient = MongoClients.create(CONNECTION_STRING);
            this.database = this.mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("Conexão Singleton com MongoDB estabelecida!");
        }
        catch (Exception e) {
            System.err.println("Falha ao conectar no MongoDB: " + e.getMessage());
            // Se não conectar, a aplicação não deve subir
            throw new RuntimeException("Não foi possível conectar ao MongoDB", e);
        }
    }

    //garantir apenas uma instancia
    public static synchronized Conexao getInstance() {
        if (instance == null) {
            instance = new Conexao();
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return this.database;
    }
}
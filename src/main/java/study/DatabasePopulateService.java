package study;

import study.config.Database;
import study.config.SpringScriptUtility;

import java.sql.Connection;

public class DatabasePopulateService {
    public static void main(String[] args) {
        Connection connection = Database.getInstance().getPostgresConnection();
        String path = "C:\\Users\\HP\\IdeaProjects\\java2m6\\src\\main\\resources\\sql\\populate_db.sql";
        SpringScriptUtility.runScript(path, connection);
    }
}

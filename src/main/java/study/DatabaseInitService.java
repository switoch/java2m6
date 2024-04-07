package study;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import study.config.Database;
import study.config.SpringScriptUtility;

import java.sql.Connection;

public class DatabaseInitService {
    public static void main(String[] args) throws Exception {
        Connection connection = Database.getInstance().getPostgresConnection();
        String path = "C:\\Users\\HP\\IdeaProjects\\java2m6\\src\\main\\resources\\sql\\init_db.sql";
        SpringScriptUtility.runScript(path, connection);
    }
}

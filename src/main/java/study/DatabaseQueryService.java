package study;

import study.config.Database;
import study.config.SpringScriptUtility;
import study.data.LongestProject;
import study.data.MaxProjectCountClient;
import study.data.MaxSalaryWorker;
import study.data.YoungestEldestWorker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DatabaseQueryService {
    private static PreparedStatement selectMaxCountProject;
    private static PreparedStatement selectMaxSalaryWorker;
    private static PreparedStatement selectMaxMonths;

    private static PreparedStatement selectYoungestEldestWorker;

    public static void main(String[] args) throws SQLException, IOException {
        List<MaxProjectCountClient> maxProjectCountClients = new DatabaseQueryService().findMaxProjectsClient();
        List<MaxSalaryWorker> maxSalaryWorkers = new DatabaseQueryService().findMaxSalaryWorker();
        List<LongestProject> longestProjects = new DatabaseQueryService().findLongestProject();
        List<YoungestEldestWorker> youngestEldestWorkers = new DatabaseQueryService().findYoungestEldestWorkers();

    }

    public List<MaxProjectCountClient> findMaxProjectsClient() throws SQLException, IOException {
        Connection connection = Database.getInstance().getPostgresConnection();
        String path = "C:\\Users\\HP\\IdeaProjects\\java2m6\\src\\main\\resources\\sql\\find_max_projects_client.sql";
        String query = SpringScriptUtility.readScript(path);
        selectMaxCountProject = connection.prepareStatement(query);
        ResultSet resultSet = selectMaxCountProject.executeQuery();
        int columnsNumber = resultSet.getMetaData().getColumnCount();
        List<String> names = new ArrayList<>();
        List<Integer> count = new ArrayList<>();

        List<MaxProjectCountClient> maxProjectCountClients = new ArrayList<>();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (resultSet.getMetaData().getColumnName(i).equals("name")) {
                    names.add(resultSet.getString(i));
                } else {
                    count.add(resultSet.getInt(i));
                }
            }
        }
        for(int i=0; i<names.size();i++){
            MaxProjectCountClient client = new MaxProjectCountClient(names.get(i), count.get(i));
            maxProjectCountClients.add(client);
        }
        return maxProjectCountClients;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() throws SQLException, IOException {
        Connection connection = Database.getInstance().getPostgresConnection();
        String path = "C:\\Users\\HP\\IdeaProjects\\java2m6\\src\\main\\resources\\sql\\find_max_salary_worker.sql";
        String query = SpringScriptUtility.readScript(path);
        selectMaxSalaryWorker = connection.prepareStatement(query);
        ResultSet resultSet = selectMaxSalaryWorker.executeQuery();
        int columnsNumber = resultSet.getMetaData().getColumnCount();
        List<String> names = new ArrayList<>();
        List<Integer> salary = new ArrayList<>();

        List<MaxSalaryWorker> maxSalaryWorker = new ArrayList<>();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (resultSet.getMetaData().getColumnName(i).equals("name")) {
                    names.add(resultSet.getString(i));
                } else {
                    salary.add(resultSet.getInt(i));
                }
            }
        }
        for(int i=0; i<names.size();i++){
            MaxSalaryWorker worker = new MaxSalaryWorker(names.get(i), salary.get(i));
            maxSalaryWorker.add(worker);
        }
        return maxSalaryWorker;
    }

    public List<LongestProject> findLongestProject() throws SQLException, IOException {
        Connection connection = Database.getInstance().getPostgresConnection();
        String path = "C:\\Users\\HP\\IdeaProjects\\java2m6\\src\\main\\resources\\sql\\find_longest_project.sql";
        String query = SpringScriptUtility.readScript(path);
        selectMaxMonths = connection.prepareStatement(query);
        ResultSet resultSet = selectMaxMonths.executeQuery();
        int columnsNumber = resultSet.getMetaData().getColumnCount();
        List<Integer> ids = new ArrayList<>();
        List<Integer> max_count = new ArrayList<>();

        List<LongestProject> longestProjects = new ArrayList<>();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (resultSet.getMetaData().getColumnName(i).equals("id")) {
                    ids.add(resultSet.getInt(i));
                } else {
                    max_count.add(resultSet.getInt(i));
                }
            }
        }
        for(int i=0; i<ids.size();i++){
            LongestProject longestProject = new LongestProject(ids.get(i), max_count.get(i));
            longestProjects.add(longestProject);
        }
        return longestProjects;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() throws IOException, SQLException {
        Connection connection = Database.getInstance().getPostgresConnection();
        String path = "C:\\Users\\HP\\IdeaProjects\\java2m6\\src\\main\\resources\\sql\\find_youngest_eldest_workers.sql";
        String query = SpringScriptUtility.readScript(path);
        selectYoungestEldestWorker = connection.prepareStatement(query);
        ResultSet resultSet = selectYoungestEldestWorker.executeQuery();
        int columnsNumber = resultSet.getMetaData().getColumnCount();
        List<String> names = new ArrayList<>();
        List<Date> birthdays = new ArrayList<>();
        List<String> types = new ArrayList<>();

        List<YoungestEldestWorker> youngestEldestWorkers = new ArrayList<>();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (resultSet.getMetaData().getColumnName(i).equals("name")) {
                    names.add(resultSet.getString(i));
                } else if(resultSet.getMetaData().getColumnName(i).equals("birthday")){
                    birthdays.add(resultSet.getDate(i));
                }else{
                    types.add(resultSet.getString("type"));
                }
            }
        }
        for(int i=0; i<names.size();i++){
            YoungestEldestWorker worker = new YoungestEldestWorker(names.get(i), birthdays.get(i), types.get(i));
            youngestEldestWorkers.add(worker);
        }
        return youngestEldestWorkers;
    }
}

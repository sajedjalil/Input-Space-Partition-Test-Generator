package isp.database;

import isp.models.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDB {

    public void createTable(){
        String sql = "create table if not exists PROJECT\n" +
                     "(\n" +
                     "    project_id    integer primary key autoincrement ,\n" +
                     "    date_time TEXT not null,\n" +
                     "    category text not null,\n" +
                     "    input text not null,\n" +
                     "    generated_tests text not null,\n" +
                     "    possible_tests text not null\n" +
                     ");";

        try (Connection conn = SQLiteConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Project> projectList() {
        String sql = "SELECT * FROM PROJECT order by project_id DESC;";
        List<Project> projects = new ArrayList<>();

        int counter = 1;
        try (Connection conn = SQLiteConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) projects.add(new Project(rs, counter++));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return projects;
    }

    public void addProject(Project project) {

        String sql = "INSERT INTO PROJECT " +
                "( date_time, category, input, generated_tests, possible_tests)" +
                " VALUES ( ?, ?, ?, ?, ?);";

        try (Connection conn = SQLiteConnection.connect()) {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, project.dateTime.getValue());
            stmt.setString(2, project.category.getValue());
            stmt.setString(3, project.input.getValue());
            stmt.setString(4, project.generatedTests.getValue().toString());
            stmt.setString(5, project.possibleTests.getValue().toString());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropProject() {

        String sql = "DELETE from PROJECT;";

        try (Connection conn = SQLiteConnection.connect();
             Statement stmt = conn.createStatement()) {

            stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

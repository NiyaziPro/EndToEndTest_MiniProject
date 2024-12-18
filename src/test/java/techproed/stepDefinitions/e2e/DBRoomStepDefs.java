package techproed.stepDefinitions.e2e;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import techproed.utilities.ConfigReader;

import java.sql.*;

import static org.junit.Assert.assertEquals;

public class DBRoomStepDefs {
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public static int roomId;

    @Given("Admin connect to the DataBase")
    public void adminConnectToTheDataBase() throws SQLException {
        connection = DriverManager.getConnection(
                ConfigReader.getProperty("dbUrl"),
                ConfigReader.getProperty("dbUser"),
                ConfigReader.getProperty("dbPassword"));
    }

    @When("send query for created room")
    public void sendQueryForCreatedRoom() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from room where room_number =" + UIMedunnaStepdefs.roomNumber);
    }


    @Then("validates created room from resultset")
    public void validatesCreatedRoomFromResultset() throws SQLException {
        resultSet.next();
        assertEquals(UIMedunnaStepdefs.roomNumber, resultSet.getInt("room_number"));
        assertEquals(UIMedunnaStepdefs.expectedPrice, resultSet.getString("price"));
        assertEquals("SUITE", resultSet.getString("room_type"));
        assertEquals(UIMedunnaStepdefs.expectedDescription, resultSet.getString("description"));

        roomId = resultSet.getInt("id");


    }


}

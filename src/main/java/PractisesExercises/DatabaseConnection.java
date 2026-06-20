package PractisesExercises;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;

public class DatabaseConnection {

    public static void main(String[] args) throws SQLException {


       Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommdb", "root", "123456");
        Statement state= con.createStatement();
        ResultSet resultSet= state.executeQuery("select * from logininfo where name ='karthika'");
        resultSet.next();
       String emailid= resultSet.getString("email");
       String password=resultSet.getString("password");


        WebDriver driver =new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys(emailid);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();
        driver.quit();
    }

}

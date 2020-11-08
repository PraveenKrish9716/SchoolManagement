package students;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import loginapp.LoginController;
import Admin.StudentData;
import dbUtil.dbConnection;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {

    @FXML
    private TextField stdid;
    @FXML
    private TextField stdfname;
    @FXML
    private Button logoutButton;
    @FXML
    private TextField stdlname;
    @FXML
    private TextField stdemail;
    @FXML
    private TextField stddob;
    @FXML
    private TextField newmail;
    @FXML
    private Label mailchange;

    private dbConnection dc;
    private ObservableList<StudentData> data;

    private String sql = "SELECT * FROM Students";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dc= new dbConnection();

        try {
            Connection conn = dbConnection.getConnection();

        LoginController l = new LoginController();
       String username = l.returnUname();

       stdid.setEditable(false);
       stdfname.setEditable(false);
       stdlname.setEditable(false);
       stdemail.setEditable(false);
       stddob.setEditable(false);

            String sql = "select * from Students where Fname=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,username);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            this.stdid.setText(rs.getString(1));
            this.stdfname.setText(rs.getString(2));
            this.stdlname.setText(rs.getString(3));
            this.stdemail.setText(rs.getString(4));
            this.stddob.setText(rs.getString(5));
            conn.close();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void changeMail(){
        try{
            Connection conn = dbConnection.getConnection();
            String id = this.stdid.getText();
            String mail = this.newmail.getText();
            String sql = "update Students set Email=? where ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,mail);
            stmt.setString(2,id);
            int x =stmt.executeUpdate();
            if(x>0)
            {
                this.mailchange.setText("Email updated successfully. Reload profile to view changes");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public  void logout(){
        try{
            Stage stage0 = (Stage)this.logoutButton.getScene().getWindow();
            stage0.close();

            Stage stage= new Stage();

            FXMLLoader outloader = new FXMLLoader();
            Pane outroot = (Pane)outloader.load(getClass().getResource("/loginapp/login.fxml"));

            LoginController outController = (LoginController)outloader.getController();
            Scene scene = new Scene(outroot);
            stage.setScene(scene);
            stage.setTitle("Login Dashboard");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

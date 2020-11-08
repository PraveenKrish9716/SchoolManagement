package Teacher;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import loginapp.LoginController;
import Admin.StudentData;
import dbUtil.dbConnection;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {

    @FXML
    private TextField studentId;
    @FXML
    private Button logoutButton;
    @FXML
    private TextField mark1;
    @FXML
    private TextField mark2;
    @FXML
    private Label fname;
    @FXML
    private Label lname;
    @FXML
    private Label submitFlag;
    @FXML
    private TableView<Markllist> markstable;
    @FXML
    private TableColumn<Markllist,String> mark1id;
    @FXML
    private TableColumn<Markllist,String> mark2id;
    @FXML
    private TableColumn<Markllist,String> mark3id;
    @FXML
    private TableColumn<Markllist,String> mark4id;
    @FXML
    private TableColumn<Markllist,String> mark5id;
    @FXML
    private TableColumn<Markllist,String> mark6id;
    @FXML
    private TextField studentID;

    private dbConnection dc;
    private ObservableList<Markllist> data;

    private String sql = "SELECT * FROM Students where id=?";

    public void initialize(URL url, ResourceBundle rb){
        this.dc= new dbConnection();
    }

    @FXML
    public void loadMarks(javafx.event.ActionEvent event) throws SQLException{
        try{
            Connection conn = dbConnection.getConnection();
            this.data= FXCollections.observableArrayList();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,this.studentID.getText());

            ResultSet rs= stmt.executeQuery();
            while(rs.next()){
                this.data.add(new Markllist(rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)));
            }
        }catch(SQLException e){
            System.err.println("Error"+e);
        }
        this.mark1id.setCellValueFactory(new PropertyValueFactory<Markllist,String>("mark1id"));
        this.mark2id.setCellValueFactory(new PropertyValueFactory<Markllist,String>("mark2id"));
        this.mark3id.setCellValueFactory(new PropertyValueFactory<Markllist,String>("mark3id"));
        this.mark4id.setCellValueFactory(new PropertyValueFactory<Markllist,String>("mark4id"));
        this.mark5id.setCellValueFactory(new PropertyValueFactory<Markllist,String>("mark5id"));
        this.mark6id.setCellValueFactory(new PropertyValueFactory<Markllist,String>("mark6id"));

        this.markstable.setItems(null);
        this.markstable.setItems(this.data);
    }

    @FXML
    public void loadData(){
      try{
          Connection conn = dbConnection.getConnection();
          String id = this.studentId.getText();
          String sql = "select * from Students where ID=?";
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setString(1,id);
          ResultSet rs = stmt.executeQuery();
          this.fname.setText(rs.getString(2));
          this.lname.setText(rs.getString(3));
          conn.close();
      } catch (SQLException e) {
          e.printStackTrace();
      }
    }

    @FXML
    public void inputMark(){
        try {
            Connection conn = dbConnection.getConnection();
            String id = this.studentId.getText();
            String mark_1 = this.mark1.getText();
            String mark_2 = this.mark2.getText();
            String sql = "update Students set Mark1=?,Mark2=? where ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,mark_1);
            stmt.setString(2,mark_2);
            stmt.setString(3,id);
            int x =stmt.executeUpdate();
            if(x>0) {
                this.submitFlag.setText("Successfully Submitted");
            }else{
                this.submitFlag.setText("Submission Failed");
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

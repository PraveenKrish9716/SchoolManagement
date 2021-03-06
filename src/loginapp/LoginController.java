package loginapp;

import Admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsController;
import Teacher.TeacherController;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class LoginController implements Initializable{

    LoginModel loginModel = new LoginModel();
      static String uname;

        @FXML
        private Label dbstatus;
        @FXML
        private TextField username;
        @FXML
        private PasswordField password;
        @FXML
        private ComboBox<option> combobox;
        @FXML
        private Button loginButton;
        @FXML
        private Label loginStatus;

        public void initialize(URL url , ResourceBundle rb){
           if(this.loginModel.isDatabaseConnected()){
              this.dbstatus.setText("Connected to database");
           }else{
              this.dbstatus.setText("Not connected to database");
        }
        this.combobox.setItems(FXCollections.observableArrayList(option.values()));
    }
//   public String uname = this.username.getText();
//
//
//    public  String getUname() {
//        return uname;
//    }

    @FXML
    public void Login(ActionEvent event){
      try{
          if(this.loginModel.isLogin(this.username.getText(),this.password.getText(),((option)this.combobox.getValue()).toString())){
              Stage stage = (Stage)this.loginButton.getScene().getWindow();
              stage.close();
              switch (((option)this.combobox.getValue()).toString())
              {
                  case "Admin":
                      System.out.println("admin");
                      adminLogin();
                      break;
                  case "Student":
                      System.out.println("Student");
                      studentLogin();
                      break;
                  case "Teacher":
                      System.out.println("Teacher");
                      teacherLogin();
                      break;
              }

          }
          else{
              this.loginStatus.setText("Wrong Credentials");
          }
      } catch (Exception localException){

      }
    }
    public void studentLogin(){
     try{
         uname = this.username.getText();//new


         Stage userStage = new Stage();
         FXMLLoader loader = new FXMLLoader();
         Pane root = (Pane)loader.load(getClass().getResource("/students/studentFXML.fxml").openStream());

         StudentsController studentsController = (StudentsController)loader.getController();
         Scene scene = new Scene(root);
         userStage.setScene(scene);
         userStage.setTitle("Student Dashboard");
         userStage.setResizable(false);
         userStage.show();


     } catch (IOException ex) {
         ex.printStackTrace();
     }
    }
    public void adminLogin(){
        try{
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane)adminLoader.load(getClass().getResource("/Admin/Admin.fxml").openStream());

            AdminController adminController = (AdminController)adminLoader.getController();
            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void teacherLogin(){
        try{
            Stage teacherStage = new Stage();
            FXMLLoader teacherLoader = new FXMLLoader();
            Pane teacherroot = (Pane)teacherLoader.load(getClass().getResource("/Teacher/Teacher.fxml").openStream());

            TeacherController teacherController = (TeacherController)teacherLoader.getController();
            Scene scene = new Scene(teacherroot);
            teacherStage.setScene(scene);
            teacherStage.setTitle("Teacher Dashboard");
            teacherStage.setResizable(false);
            teacherStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String returnUname(){
            return uname;
    }//new
}

package Teacher;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Markllist {

    public final StringProperty mark1id;
    public final StringProperty mark2id;
    public final StringProperty mark3id;
    public final StringProperty mark4id;
    public final StringProperty mark5id;
    public final StringProperty mark6id;

    public Markllist(String mark1,String mark2, String mark3, String mark4, String mark5, String mark6){
        this.mark1id= new SimpleStringProperty(mark1);
        this.mark2id= new SimpleStringProperty(mark2);
        this.mark3id= new SimpleStringProperty(mark3);
        this.mark4id= new SimpleStringProperty(mark4);
        this.mark5id= new SimpleStringProperty(mark5);
        this.mark6id= new SimpleStringProperty(mark6);
    }

    public String getMark1id() {
        return mark1id.get();
    }

    public String getMark2id() {
        return mark2id.get();
    }

    public String getMark3id() {
        return mark3id.get();
    }

    public String getMark4id() {
        return mark4id.get();
    }

    public String getMark5id() {
        return mark5id.get();
    }

    public String getMark6id(){
        return mark6id.get();
    }

    public StringProperty mark1idProperty() {
        return mark1id;
    }

    public StringProperty mark2idProperty() {
        return mark2id;
    }

    public StringProperty mark3idProperty() {
        return mark3id;
    }

    public StringProperty mark4idProperty() {
        return mark4id;
    }

    public StringProperty mark5idProperty() {
        return mark5id;
    }

    public StringProperty mark6idProperty() {
        return mark6id;
    }
    public void setMark1id(String mark1id){
      this.mark1id.set(mark1id);
    }
    public void setMark2id(String mark2id){
        this.mark2id.set(mark2id);
    }
    public void setMark3id(String mark3id){
        this.mark3id.set(mark3id);
    }
    public void setMark4id(String mark4id){
        this.mark4id.set(mark4id);
    }
    public void setMark5id(String mark5id){
        this.mark5id.set(mark5id);
    }
    public void setMark6id(String mark6id){
        this.mark6id.set(mark6id);
    }

}

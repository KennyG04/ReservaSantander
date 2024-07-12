package Actors;

public class Worker {
    private String ID;
    private String Password;
    public  String Name;

    public Worker(String ID, String Password, String Name) {
        this.ID = ID;
        this.Password = Password;
        this.Name = Name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }  
}

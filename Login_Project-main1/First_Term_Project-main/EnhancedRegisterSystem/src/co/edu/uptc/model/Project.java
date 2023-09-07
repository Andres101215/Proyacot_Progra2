package co.edu.uptc.model;

import java.util.ArrayList;

import co.edu.uptc.model.persontypes.Student;

public class Project {
    private int idProject;
    private String name;
    private boolean state;
    private String description;
    private ArrayList<Person> mmembers;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdProject() {
        return idProject;
    }
    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }
    
    public Project() {
    }

    public Project(int idProject, String name, boolean state, String description) {
        this.idProject = idProject;
        this.name = name;
        this.state = state;
        this.description = description;
    }

    @Override
    public String toString() {
        if(state){
         return "------------------------------------------------------------------------------------------"
         +"\nId:" + idProject + "     |Name:" + name + "       |State:in progress  "+"     |Description: "
                + description+"         |members:"+ mmembers+"\n------------------------------------------------------------------------------------------"
          ;
        }else{
         return  "------------------------------------------------------------------------------------------"
         +"\nId:" + idProject + "      |Name:" + name + "           |State:finalized  "+"       |Description: "
                + description +"         |members:"+ mmembers+"\n------------------------------------------------------------------------------------------"
          ;
        }
      
    }
    public ArrayList<Person> getMmembers() {
        return mmembers;
    }
    public void setMmembers(ArrayList<Person> mmembers) {
        this.mmembers = mmembers;
    }
   
}

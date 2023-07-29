package co.edu.uptc.Model;

public class Project {
    private int idProject;
    private String name;
    private boolean state;
    private String description;

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
        return "project [idProject=" + idProject + ", name=" + name + ", state=" + state + ", description="
                + description + "]";
    }

    

    

    

    

    
    
}

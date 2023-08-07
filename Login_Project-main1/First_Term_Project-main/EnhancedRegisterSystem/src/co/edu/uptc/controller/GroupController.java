package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Group;
import co.edu.uptc.model.Project;

public class GroupController {
    private Group group;
    private Project project;
    private ArrayList<Group> groupList = new ArrayList<>();
    private boolean isLoaded = false;
    

    // Projects
    public void addProject(int groupId, Project p) {
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).getId() == groupId) {
                groupList.get(i).getProjects().add(p);
            }
        }
    }

    public boolean removeProject(int idGroup, int idProject) {
        int aux = searchGroup(idGroup);
        int aux2 = searchProject(idProject);

        if (aux != -1 && aux2 != -1) {
            getGroupList().get(aux).getProjects().remove(aux2);
            return true;
        }
        return false;
    }

    public Project createProject(int projectId, String name, boolean state, String description) {
        project = new Project(projectId, name, state, description);
        return this.project;
    }

    // Method that returns the position in the list of projects
    public int searchProject(int projectId) {
        for (int i = 0; i < group.getProjects().size(); i++) {
            if (group.getProjects().get(i).getIdProject() == projectId) {
                return i;
            }
        }
        return -1;
    }

    public boolean modifyProject(int id, String option, String newIn, Boolean state) {

        int aux = searchProject(id);

        switch (option) {
            case "1":
                group.getProjects().get(aux).setName(newIn);
                return true;
            case "2":
                group.getProjects().get(aux).setDescription(newIn);
                return true;
            case "3":
                group.getProjects().get(aux).setState(state);
                return true;
            default:
                break;
        }
        return false;
    }

    public String showInformationProjects() {
        String information = "";
        for (int i = 0; i < group.getProjects().size(); i++) {
            information += "Project N°" + (i + 1) + " \n " + group.getProjects().get(i).toString() + "\n";
        }
        return information;
    }

    public boolean validationId(int inputId) {
        for (int i = 0; i < groupList.size(); i++) {
            // por grupo
            if (groupList.get(i).getId() == inputId) {
                return true;
            }
            // por proyecto
            for (int j = 0; j < groupList.get(i).getProjects().size(); j++) {
                if (groupList.get(i).getProjects().get(j).getIdProject() == inputId) {
                    return true;
                }
            }

        }
        return false;
    }

    // Groups
    public ArrayList<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(ArrayList<Group> groupList) {
        this.groupList = groupList;
    }

    public void loadGroups() {
        if (!isLoaded) {            
            groupList.add(createGroup(1, "Duitama", "Computer Science, Electronics, and Communications Research Group",
                    "IFELCOM", "ifelcom.20@gmail.com", createObjective()));
                    
            isLoaded = true;
        }
    }

    public ArrayList<String> createObjective() {
        ArrayList<String>listGoal=new ArrayList<>();
        return listGoal;
                  
    }
    public boolean addObjective(int idGroup, String o){
        int g;
        g=searchGroup(idGroup);
        groupList.get(g).getGoal().add(o);
        return true;

    }

     public boolean removeObjective(int idGroup,  int x){
        int g;
        g=searchGroup(idGroup);
        groupList.get(g).getGoal().remove(x-1);
        return true;

    }

    public boolean modifyObjective(int idGroup,  int x, String n){
        int g;
        g=searchGroup(idGroup);
        groupList.get(g).getGoal().set(x-1, n);
        return true;

    }

    public String showObjective(int Id){
        int g;
        String aux="";
        g=searchGroup(Id);    
        for (int i = 0; i < groupList.get(g).getGoal().size(); i++) {
                aux+=i+1+""+groupList.get(g).getGoal().get(i)+"\n";
            
            
        }  
        return aux;
        
    }
     public String showObjective1(int g){
           String aux="";
        for (int i = 0; i < groupList.get(g).getGoal().size(); i++) {
                aux+=i+1+"."+groupList.get(g).getGoal().get(i)+"\n";
            
            
        }  
        return aux;
        
    }

    public String showInformation() {
        String information = "";
        for (int i = 0; i < groupList.size(); i++) {
            information += groupList.get(i).toString() + "\n"+showObjective1(i);
        }
        return information;
    }

    public Group createGroup(int id, String faculty, String name, String initial, String email, ArrayList<String>goal) {

        group = new Group(id, faculty, name, initial, email, goal);
        return this.group;
    }

   

    public void addGroup(Group g) {
        groupList.add(g);
    }

    public boolean deleteGroup(int groupId) {
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).getId() == groupId) {
                groupList.remove(i);
                return true;
            }
        }
        return false;
    }

    public int searchGroup(int id) {
        for (int i = 0; i < groupList.size(); i++) {
            if (id == groupList.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    public String modifyGroup(int id, String option, String inf) {

        int aux = searchGroup(id);

        if (aux != -1) {
            switch (option) {
                case "1":
                    groupList.get(aux).setFaculty(inf);
                    return inf;
                case "2":
                    groupList.get(aux).setName(inf);
                    return inf;
                case "3":
                    groupList.get(aux).setInitial(inf);
                    return inf;
                case "4":
                    groupList.get(aux).setEmail(inf);
                    return inf;
                default:
                    break;
            }
        }

        return inf;
    }

  
    public String showIdGroups() {
        String aux = "";
        for (Group group : groupList) {
            aux += "-----------------------------------------------------------------------------\n"
                    + "Id " + group.getId() + "  |  Group: "
                    + group.getName() + "\n";
        }

        return aux;
    }

    public boolean checkarray() {
        if (groupList.isEmpty()) {
            return true;
        }

        return false;
    }

    public boolean checkemail(String email) {
        String aux = "";
        for (int i = 0; i < email.length(); i++) {
            if (String.valueOf(email.charAt(i)).equals("@")) {
                aux = email.substring(i, i + 12);

                if (aux.length() < 11) {
                    return true;
                } else {
                    if (aux.equals("@uptc.edu.co")) {
                        return false;
                    }
                }

            }
        }

        return true;
    }

    public int  assignid (){
        int x=1000;
        x+=(groupList.size())+1;      
        return x;
    }
    public int assignidproject(int id){
         int x=2000;
          int aux = searchGroup(id);
         x+=(groupList.get(aux).getProjects().size())+1;
         return x;
    }
    public boolean checkNameGroupsize(String aux){
        boolean x;
        if (aux.length() < 5) {
            x= true;
         } else {
            x=false;
         }
        return x;
    }
     public boolean checkInitialGroupsize(String aux){
        boolean x;
        if (aux.length() < 3) {
            x= true;
         } else {
            x=false;
         }
        return x;
    }

    public boolean namevalidation(String name){
        for (Group group : groupList) {
            if(group.getName().equals(name)){
               return true;
            }
        }
    return false;
    }
    public boolean emailvalidation(String email){
        for (Group group : groupList) {
            if(group.getEmail().equals(email)){
               return true;
            }
        }
    return false;
    }
    public boolean namevalidationproject(int id,String nameproject){
         int aux = searchGroup(id);
        for (Project  x: groupList.get(aux).getProjects()) {
            if(x.getName().equals(nameproject)){
             return true;
            }
        }
        return false;
    }

    public boolean initialvalidation(String aux){
        for (Group group : groupList) {
            if(group.getInitial().equals(aux)){
              return true;
            }
        }
      return false;
    }
    public boolean validationObjective(int idGroup){
        int g;
        g=searchGroup(idGroup);
        if(groupList.get(g).getGoal().isEmpty()){
            return true;

        }
        return false;
    }

}

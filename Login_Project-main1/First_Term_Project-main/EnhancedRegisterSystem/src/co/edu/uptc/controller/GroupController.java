package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Group;
import co.edu.uptc.model.Person;
import co.edu.uptc.model.Project;

public class GroupController {
    private Group group;
    private Project project;
    private ArrayList<Group> groupList = new ArrayList<>();
    private boolean isLoaded = false;
    private int x = 1001;
    private PersonController pc= new PersonController();

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
        ArrayList<Person> members = new ArrayList<>();
        project = new Project(projectId, name, state, description);
        project.setMmembers(members);
        return this.project;
    }

    public boolean addProjectmembers(Group g, Person p, int id) {
        for (Project project : group.getProjects()) {
            if (project.getIdProject() == id) {
                project.getMmembers().add(p);
                return false;
            }

        }
        return true;
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

    public boolean modifyProject(int Id, int id, String option, String newIn, Boolean state) {

        int aux1 = searchGroup(Id);
        int aux = searchProject(id);

        switch (option) {
            case "1":
                groupList.get(aux1).getProjects().get(aux).setName(newIn);
                return true;
            case "2":
                groupList.get(aux1).getProjects().get(aux).setDescription(newIn);
                return true;
            case "3":
                groupList.get(aux1).getProjects().get(aux).setState(state);
                return true;
            default:
                break;
        }
        return false;
    }

    public String showInformationProjects(int idgroup) {
        String information = "";
        int g = searchGroup(idgroup);
        for (int i = 0; i < groupList.get(g).getProjects().size(); i++) {
            information += "Project N°" + (i + 1) + " \n " + groupList.get(g).getProjects().get(i).toString() + "\n";
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
            groupList.add(
                    createGroup(1001, "Duitama", "Computer Science, Electronics, and Communications Research Group",
                            "IFELCOM", "ifelcom.20@gmail.com", createObjective()));

            isLoaded = true;
        }
    }

    public ArrayList<String> createObjective() {
        ArrayList<String> listGoal = new ArrayList<>();
        return listGoal;

    }

    public boolean addObjective(int idGroup, String o) {
        int g;
        g = searchGroup(idGroup);
        groupList.get(g).getGoal().add(o);
        return true;

    }

    public boolean removeObjective(int idGroup, int x) {
        int g;
        g = searchGroup(idGroup);
        groupList.get(g).getGoal().remove(x - 1);
        return true;

    }

    public boolean modifyObjective(int idGroup, int x, String n) {
        int g;
        g = searchGroup(idGroup);
        groupList.get(g).getGoal().set(x - 1, n);
        return true;

    }

    public String showObjective(int Id) {
        int g;
        String aux = "Goals:";
        g = searchGroup(Id);
        for (int i = 0; i < groupList.get(g).getGoal().size(); i++) {
            aux += i + 1 + "" + groupList.get(g).getGoal().get(i) + "\n";

        }
        return aux;

    }

    public String showObjective1(int g) {
        String aux = "Goals:\n";
        for (int i = 0; i < groupList.get(g).getGoal().size(); i++) {
            aux += i + 1 + "." + groupList.get(g).getGoal().get(i) + "\n";

        }
        return aux;

    }

    public String showInformation() {
        String information = "";
        for (int i = 0; i < groupList.size(); i++) {
            information += groupList.get(i).toString() + "\n" + showObjective1(i);
        }
        return information;
    }

    public Group createGroup(int id, String faculty, String name, String initial, String email,ArrayList<String> goal) {
        ArrayList<Person> members = new ArrayList<Person>();
        ArrayList<Person> request= new ArrayList<Person>();
           
        group = new Group(id, faculty, name, initial, email, goal);
        group.setMembers(members);
        group.setRequest(request);
        return group;
    }

    public boolean addgroupmembrs(String  id,int idgroup) {
       if(!pc.findPersonById(id).equals(null)){
            for (Group group1 : groupList) {
                if(group1.getId()== idgroup){
                   group1.getMembers().add(pc.findPersonById(id));
                   return false;
                }
            }
       }
        return true;
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
    public String showMembers(int g) {
        String aux = "Members:\n";
        for (int i = 0; i < groupList.get(g).getMembers().size(); i++) {
            aux += i + 1 + "." + groupList.get(g).getMembers().get(i) + "\n";
        }
        return aux;

    }

    public String showgroupmembers() {
        String aux = "";
        for (int i = 0; i < groupList.size(); i++) {
            aux += "-----------------------------------------------------------------------------\n"
                    + "Id " + group.getId() + "  |  Group: "
                    + group.getName() +"   |  "+ showMembers(i)+"\n";
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
                try {
                    aux = email.substring(email.length() - 12);

                    if (aux.length() < 11) {
                        return true;
                    } else {
                        if (aux.equals("@uptc.edu.co")) {
                            return false;
                        }
                    }
                } catch (StringIndexOutOfBoundsException ex) {
                    return true;
                }
            }
        }
        return true;
    }

    public int assignid() {
        x++;
        return x;
    }

    public int assignidproject(int id) {
        int z = 2000;
        int p = searchGroup(id);
        if (groupList.get(p).getProjects().isEmpty()) {
            z += +1;
        } else {
            int y = groupList.get(p).getProjects().size();
            z += (groupList.get(p).getProjects().get(y - 1).getIdProject()) + 1;
        }

        return z;
    }

    public boolean checkNameGroupsize(String aux) {
        boolean x;
        if (aux.length() < 5) {
            x = true;
        } else {
            x = false;
        }
        return x;
    }

    public boolean checkInitialGroupsize(String aux) {
        boolean x;
        if (aux.length() < 2) {
            x = true;
        } else {
            x = false;
        }
        return x;
    }

    public boolean namevalidation(String name) {
        for (Group group : groupList) {
            if (group.getName().equalsIgnoreCase(name.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean emailvalidation(String email) {
        for (Group group : groupList) {
            if (group.getEmail().equalsIgnoreCase(email.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean namevalidationproject(int id, String nameproject) {
        int aux = searchGroup(id);

        for (Project x : groupList.get(aux).getProjects()) {
            if (x.getName().equalsIgnoreCase(nameproject.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean initialvalidation(String aux) {
        for (Group group : groupList) {
            if (group.getInitial().equalsIgnoreCase(aux.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean validationObjective(int idGroup) {
        int g;
        g = searchGroup(idGroup);
        if (groupList.get(g).getGoal().isEmpty()) {
            return true;

        }
        return false;
    }

    public boolean comprobatenumber(String con) {
        int x = 0;
        for (int i = 0; i < con.length(); i++) {
            if (String.valueOf(con.charAt(i)).equals("1")
                    || String.valueOf(con.charAt(i)).equals("2")
                    || String.valueOf(con.charAt(i)).equals("3")
                    || String.valueOf(con.charAt(i)).equals("4")
                    || String.valueOf(con.charAt(i)).equals("5")
                    || String.valueOf(con.charAt(i)).equals("6")
                    || String.valueOf(con.charAt(i)).equals("7")
                    || String.valueOf(con.charAt(i)).equals("8")
                    || String.valueOf(con.charAt(i)).equals("9")
                    || String.valueOf(con.charAt(i)).equals("0")) {
                x++;
            }
        }

        if (x == con.length()) {
            return true;
        } else {
            return false;
        }

    }

}

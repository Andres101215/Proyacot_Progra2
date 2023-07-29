package co.edu.uptc.Controller;

import java.util.ArrayList;

import co.edu.uptc.Model.Grupo;
import co.edu.uptc.Model.Project1;

public class GroupController {
    private Grupo grupo;
    private Project1 project;
    private ArrayList<Grupo> listaGrupos = new ArrayList<>();
    private boolean isLoaded = false;

    public ArrayList<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(ArrayList<Grupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    
    public void addProject(int IdGroup,Project1 p){
        for (int i = 0; i < listaGrupos.size(); i++) {
            if (listaGrupos.get(i).getId()==IdGroup) {
                    listaGrupos.get(i).getProjects().add(p);
            }
        }
    }

    public void removeProject(int IdGroup, Project1 p){
        for (int i = 0; i < listaGrupos.size(); i++) {
            if (listaGrupos.get(i).getId()==IdGroup) {
                    listaGrupos.get(i-1).getProjects().remove(p);
            }
        }
    }

    public Project1 createProject(int idProject, String name, boolean state, String description) {
       project=new Project1(idProject ,name ,state ,description);
       return this.project;
    }

    public void loadGroups() {
        if (!isLoaded) {
            listaGrupos.add(createGroup(1, "Duitama", "Grupo de Investigaci\u00F3n en Inform\u00E1tica, Electr\u00F3nica y Comunicaciones", "IFELCOM", "ifelcom.20@gmail.com", "1....."));
            isLoaded = true; 
        }
    }

    public String showInformation() {
        String information = "";
        for (int i = 0; i < listaGrupos.size(); i++) {
            information += "Grupo N°" + (i + 1) + " \n " + listaGrupos.get(i).toString() + "\n";
        }
        return information;
    }

    public Grupo createGroup(int id, String faculty, String name, String initial, String email, String goal) {
         ArrayList<Project1> listprojects = new ArrayList<>(); 
        grupo = new Grupo(id, faculty, name, initial, email, goal, listprojects);
        return this.grupo;
    }

    public void addGroup(Grupo g) {
        listaGrupos.add(g);
    }

       public void deleteGroup(int IdGroup){
        for (int i = 0; i < listaGrupos.size(); i++) {
            if (listaGrupos.get(i).getId()==IdGroup) {
                    listaGrupos.remove(i);
            }
        }
    }

    public int searchGroup(int id) {
        for (int i = 0; i < listaGrupos.size(); i++) {
            if (id == listaGrupos.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    public String modifyGroup(int num, int mofic, String inf) {
        switch (mofic) {
            case 1:
            for (int i = 0; i < listaGrupos.size(); i++) {
            if (listaGrupos.get(i).getId()==num) {
                    listaGrupos.get(i).setFaculty(inf);
                return inf;
            }
        }               
            case 2:
            for (int i = 0; i < listaGrupos.size(); i++) {
            if (listaGrupos.get(i).getId()==num) {
                   listaGrupos.get(i).setName(inf);
                return inf;
            }
        }
                
            case 3:
            for (int i = 0; i < listaGrupos.size(); i++) {
            if (listaGrupos.get(i).getId()==num) {
                  listaGrupos.get(i).setInitial(inf); 
                   return inf; 
            }
        }
            case 4:
            for (int i = 0; i < listaGrupos.size(); i++) {
            if (listaGrupos.get(i).getId()==num) {
                    listaGrupos.get(i).setEmail(inf);
            return inf;
            }
        }
            
            default:
                break;

        }
        return null;
    }

 
   

    

}

package co.edu.uptc.Controller;

import java.util.ArrayList;

import co.edu.uptc.Model.Grupo;
import co.edu.uptc.Model.Project;

public class GroupController {
    private Grupo grupo;
    private Project project;
    private ArrayList<Grupo> listaGrupos = new ArrayList<>();
    private boolean isLoaded = false;

    //projects
    public void addProject(int IdGroup, Project p){
        for (int i = 0; i < listaGrupos.size(); i++) {
            if (listaGrupos.get(i).getId()==IdGroup) {
                listaGrupos.get(i).getProjects().add(p);
            }
        }
    }

    public void removeProject(int IdGroup, Project p){
        for (int i = 0; i < listaGrupos.size(); i++) {
            if (listaGrupos.get(i).getId()==IdGroup) {
                    listaGrupos.get(i-1).getProjects().remove(p);
            }
        }
    }

    public Project createProject(int idProject, String name, boolean state, String description) {
       project=new Project(idProject ,name ,state ,description);
       return this.project;
    }

    //metodo que retorna la posicion de la lista de proyectos
    public int searchProject(int idProject) {
        for(int i = 0; i < grupo.getProjects().size(); i++){
            if(grupo.getProjects().get(i).getIdProject() == idProject){
                return i;
            }
        }       
        return -1;
    }
    
    public boolean modifyProject(int id, int option, String newIn, Boolean state) {
       
        int aux = searchProject(id);
       
        switch (option) {
            case 1:
                grupo.getProjects().get(aux).setName( newIn );;
            return true;
            case 2:
                grupo.getProjects().get(aux).setDescription(newIn);;
            return true;
            case 3:
                grupo.getProjects().get(aux).setState(state);
            default:
            break;
        }
        return false;
    }
    
    public String showInformationProjects() {
        String information = "";
        for (int i = 0; i <  grupo.getProjects().size(); i++) {
            information += "Project N°" + (i + 1) + " \n " + grupo.getProjects().get(i).toString() + "\n";
        }
        return information;
    }

    


    // Groups
    public ArrayList<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(ArrayList<Grupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
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
         ArrayList<Project> listprojects = new ArrayList<>(); 
        grupo = new Grupo(id, faculty, name, initial, email, goal, listprojects);
        return this.grupo;
    }

    public void addGroup(Grupo g) {
        listaGrupos.add(g);
    }

    public boolean deleteGroup(int IdGroup){
        for (int i = 0; i < listaGrupos.size(); i++) {
            if (listaGrupos.get(i).getId()==IdGroup) {
                    listaGrupos.remove(i);
                    return true;
            }
        }
        return false;
    }


    public int searchGroup(int id) {
        for (int i = 0; i < listaGrupos.size(); i++) {
            if (id == listaGrupos.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    public String modifyGroup(int id, int option, String inf) {
        
        int aux = searchGroup(id);
        
        if(aux!=-1){
            switch (option) {
            case 1:
            listaGrupos.get(aux).setFaculty(inf);
            return inf;
            case 2:
                listaGrupos.get(aux).setName(inf);
            return inf;
            case 3:
                listaGrupos.get(aux).setInitial(inf);; 
            return inf; 
            case 4:
                listaGrupos.get(aux).setEmail(inf);
            return inf;  
            default:
                break;
            }

        }
     
        return inf;
        
    }

    public String modificarObjetivo(String objetivos, int numeroObjetivo, String nuevoObjetivo) {
        String[] lineas = objetivos.split("\n");
        StringBuilder resultado = new StringBuilder();

        for (String linea : lineas) {
            int puntoIndex = linea.indexOf(".");
            if (puntoIndex != -1) {
                int numero = Integer.parseInt(linea.substring(0, puntoIndex).trim());
                if (numero == numeroObjetivo) {
                    linea = numero + ". " + nuevoObjetivo;
                }
            }
            resultado.append(linea).append("\n");
        }

        return resultado.toString();
    }

 
   

    

}








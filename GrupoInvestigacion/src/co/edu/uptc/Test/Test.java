package co.edu.uptc.Test;

import java.util.Scanner;

import co.edu.uptc.Controller.GroupController;

public class Test {
    public static void main(String[] args) {
        GroupController gc = new GroupController();
        Scanner sc = new Scanner(System.in);
        String faculty = "", name = "", initial = "", email = "", goal = "", project = "";
        String nameProject="", descriptionProject="";
        int op = 0, num = 0, Id = 0, state=0, m=0, idProject=0;
        boolean stateProject=false;
        
        while (op != 5) {
            System.out.println("\n----- Principal Menu -----\n1.Ver grupo\n2.Crear un grupo\n3.Modificar grupo\n4.Eliminar un grupo\n5.Salir\n-----");
            op = sc.nextInt();
            switch (op) {
                case 1:
                gc.loadGroups();
                System.out.println(gc.showInformation());
                break;   
                case 2:
                    System.out.println("Input Id of the Investigation group");
                    Id = sc.nextInt();
                    System.out.println("Input faculty of the Investigation group");
                    faculty = sc.nextLine();
                    sc.nextLine();
                    System.out.println("Input name of the Investigation group");
                    name = sc.nextLine();
                    System.out.println("Input initial of the Investigation group");
                    initial = sc.next();
                    System.out.println("Input email of the Investigation group");
                    email = sc.nextLine();
                    sc.nextLine();
                    System.out.println("Input objective of the Investigation group");
                    goal = sc.nextLine();
                    
                    gc.addGroup(gc.createGroup(Id, faculty, name, initial, email, goal));
                    break;
                case 3:
                    int op1 = 0, op2 = 0, op3 = 0, op4 = 0;
                    gc.loadGroups();
                    System.out.println(gc.showInformation());
                    System.out.println("Ingrese ID del grupo que desea que sea modificado");
                    Id = sc.nextInt();  
                    
                    //validacion correcta Id
                    if( gc.searchGroup(Id) != -1){

                        while(op1!=3 ){
                            int search  = 0;     
                            System.out.println("\n------ Menu group: " + Id + " -----");
                            System.out.println("¿que desea modificar?");
                            System.out.println("1. Modify information of the group");
                            System.out.println("2. Modify projects of the group");
                            System.out.println("3. Return\n-----");
                            op1 = sc.nextInt();

                            switch(op1){
                                case 1: //modificar grupos
                                    System.out.println("\n----- Group information -----\n1.Faculty\n2.Name\n3.Initial\n4.Email\n5.Goal\n6.Return");
                                    op2 = sc.nextInt();
                                    switch (op2) {
                                        case 1:
                                            String fac = "";
                                            search = gc.searchGroup(Id);
                                            if (search != -1) {
                                                System.out.println(gc.getListaGrupos().get(search).getFaculty());
                                                System.out.println("Ingrese la nueva facultad");
                                                sc.nextLine();
                                                fac = sc.nextLine();
                                                gc.modifyGroup(Id, op2, fac);
                                            } else {
                                                System.out.println("No existe el grupo");
                                            }
                                            break;
                                        case 2:
                                            String nam = "";
                                            search = gc.searchGroup(Id);
                                            if (search != -1) {
                                                System.out.println(gc.getListaGrupos().get(search).getName());
                                                System.out.println("Ingrese el nuevo nombre");
                                                sc.nextLine();
                                                nam = sc.nextLine();
                                                gc.modifyGroup(Id, op2, nam);
                                            } else {
                                                System.out.println("No existe el grupo");
                                            }
                                            break;
                                        case 3:
                                        String init = "";
                                            search = gc.searchGroup(Id);
                                            if (search != -1) {
                                                System.out.println(gc.getListaGrupos().get(search).getInitial());
                                                System.out.println("Ingrese las nuevas siglas");
                                                sc.nextLine();
                                                init = sc.nextLine();
                                                gc.modifyGroup(Id, op2, init);
                                            } else {
                                                System.out.println("No existe el grupo");
                                            }
                                            break;
                                        case 4:
                                        String ema = "";
                                            search = gc.searchGroup(Id);
                                            if (search != -1) {
                                                System.out.println(gc.getListaGrupos().get(search).getEmail());
                                                System.out.println("Ingrese el nuevo correo");
                                                sc.nextLine();
                                                ema = sc.nextLine();
                                                gc.modifyGroup(Id, op2, ema);
                                            } else {
                                                System.out.println("No existe el grupo");
                                            }
                                            break;
                                            
                                        default:
                                        System.out.println("invalid option");
                                            break;
                                    }
                                break;

                                // Case 2 - Modificar proyectos
                                case 2: 
                                    System.out.println("\n----- Projects -----\n1.Create new project\n2.Modify existing project\n3.Delete project\n4.Show projects\n5.Return\n-----");
                                    op3 = sc.nextInt();
                                    
                                    switch(op3){
                                        case 1:
                                            System.out.println("Input Id of the project");
                                            idProject = sc.nextInt();
                                            sc.nextLine();
                                            System.out.println("Input name of the project");
                                            nameProject = sc.nextLine();
                                            System.out.println("Input state of the project");
                                            stateProject = sc.nextBoolean();
                                            sc.nextLine();
                                            System.out.println("Input description of the project");
                                            descriptionProject = sc.nextLine();

                                            gc.addProject(Id, gc.createProject(idProject, nameProject, stateProject, descriptionProject) );
                                        break;
                                        case 2:
                                        System.out.println("Input Id of the project");
                                            idProject = sc.nextInt();

                                            search = gc.searchProject(idProject);

                                            if(search != -1){
                                                System.out.println("1. Name");
                                                System.out.println("2. Description");
                                                System.out.println("3. State");
                                                op4 = sc.nextInt();
                                                sc.nextLine();
                                                switch(op4){
                                                    case 1:
                                                        System.out.println("- Input new name");
                                                        nameProject = sc.nextLine();
                                                        gc.modifyProject(idProject, op4, nameProject, null);
                                                    break;
                                                    case 2:
                                                        System.out.println("- Input new description");
                                                        descriptionProject = sc.nextLine(); 
                                                        gc.modifyProject(idProject, op4, descriptionProject, null);
                                                    break;
                                                    case 3:
                                                        System.out.println("- Input new state ");
                                                        stateProject = sc.nextBoolean();
                                                        gc.modifyProject(idProject, op4, null, stateProject);
                                                    break;
                                                    default:
                                                    break;
                                                }

                                            }else{
                                                System.out.println("No existe");
                                            }
                                                            
                                        break;
                                        case 3:
                                            gc.removeProject(Id, null);
                                        break;
                                        case 4:
                                            System.out.println(gc.showInformationProjects());    
                                        break;
                                        case 5:
                                            
                                        break;
                                        default:
                                        break;
                                    } 


                                break;//break modificar proyectos

                                case 3:

                                break;

                            } //switch close op1

                        } //close while op1
                    }else{
                        System.out.println("! Error - Invalid Id");
                    } // close validacion id

                break; // break case 3 modificar grupos y proyectos

                case 4:
                    int op5=0;
                    gc.loadGroups();
                    System.out.println(gc.showInformation());
                    System.out.println("Enter group ID that you want to be deleted");
                    num = sc.nextInt();
                    System.out.println("Are you sure to delete the group?");
                    System.out.println("1.Yes\n2.No");
                    op5=sc.nextInt();
                    switch (op5) {
                        case 1:
                            if(gc.deleteGroup(num) ){
                            System.out.println("removed successfully");
                            }else{
                            System.out.println("Error! - removed unsuccessfully");
                            }
                            
                            break;
                        case 2:
                        break;    
                        default:
                            System.out.println("invalid option");
                            break;
                    }

                    
                break;
                case 5:
                break;



                default:
                 System.out.println("invalid option");
                    break;
            }
        }

    }

}

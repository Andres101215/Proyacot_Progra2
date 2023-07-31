
package co.edu.uptc.test;

import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.uptc.controller.GroupController;
import co.edu.uptc.controller.LoginController;
import co.edu.uptc.utilities.InputLibrary;

/**
 * This class is to show the menu with its functions so
 * that it can be instantiated without having to make changes in the logic.
 * @Author Edwin Martinez
 * @Author Samuel Gonzalez
 * @Author Nicolas Sarmiento
 */
public class LoginView {

    private final InputLibrary util;
    private  final LoginController loginController;
    private final String[] roles;
    private final String errorMessage;

    public LoginView(){
        this.util = new InputLibrary();
        this.loginController = new LoginController();
        this.roles = new String[]{"Student", "Professor", "Secretary", "Administrator"};
        this.errorMessage = "Invalid input. Try again";
    }
    /**
     *This is the main method that shows the menu
     */
    public static void main(String[] args) {
       

        LoginView portal = new LoginView();
        int decision;
        String message = """
                ============================= 
                |\t\t\tUPTC\t\t\t|
                |\t\t1.Login\t\t\t\t|
                |\t\t2.Exit\t\t\t\t|
                =============================""";
        String password = "",userName = "";
        do {
            System.out.println(message);
            decision = portal.util.inputInt("Selection --------> ", "Invalid input. Try again");
            switch (decision) {
                case 1 :
                    userName = portal.util.inputString("\tUsername: ", "Invalid Input. Try again");
                    password = portal.util.inputString("\tPassword: ", "Invalid input. Try again");
                    if (portal.loginController.login(userName, password)) {
                        portal.login();
                    } else {
                        System.out.println("User name or password incorrect");
                    }
                break;
                case 2 :
                    System.out.println("Coming out....");
                break;
                default:
                    System.out.println("Invalid option. Try again");
            }
        }while (decision!=2);
    }

    /**
     * This method shows the options after login
     */
    public void login(){
        System.out.println(this.loginController.showInfoLoggedAcount());
        String role = loginController.showRol();

        if (role.equals("ADMINISTRATOR")) {
            this.optionsGeneralsLogin();
            return;
        }else if(role.equals("DIRECTOR")){
           this.optionsDirector();
           return;
        }else if(role.equals("SECRETARY")){
           this.optionsSecretary();
           return;
        }

        this.optionsBasic();
    }

    /**
     * This method shows the option for Administrator to show all accounts and register new accounts
     */
    public void optionsDirector(){
         GroupController gc = new GroupController();
        Scanner sc = new Scanner(System.in);
        String faculty = "", name = "", initial = "", email = "", project = "";
        String nameProject = "", descriptionProject = "", aux = "";
        int op = 0, num = 0, Id = 0, state = 0, m = 0, idProject = 0, cont = 0, stateProjectOp=0;
        boolean stateProject = false, exceptionControl=false;
         String loginMessage = """
                ========================
                |\t1.Investigation groups\t|
                |\t2.logout\t\t\t|
                ========================""";
                int op10=0;
                while(op10 !=2){
                  System.out.println(loginMessage);
                  op10=this.util.inputInt("Selection -----> ", this.errorMessage);
                switch(op10){
                case 1:
                 while (op != 5) {
            System.out.println(
            "\n----- Main Menu -----\n1.View groups\n2.Create a group\n3.Modify a group\n4.Delete a group\n5.Exit\n-----");
            while(exceptionControl==false){
                try {
                    op = sc.nextInt();
                    exceptionControl=true;
                } catch (InputMismatchException e) {
                    System.out.println("!Error - Invalid Input - Try again");
                    sc.nextLine();
                }
            }
            exceptionControl=false;

            switch (op) {
                case 1:
                    gc.loadGroups();
                    if( gc.showInformation().isEmpty() == false){
                        System.out.println(gc.showInformation());   
                        } else{
                            System.out.println("There is no groups created");
                        } 
                    break;
                case 2:
                    gc.loadGroups();
                    int op6=0;
                    System.out.println("Input Id of the Investigation group");
                    
                    while(exceptionControl==false){
                        try {
                            Id = sc.nextInt();
                            exceptionControl=true;
                        } catch (InputMismatchException e) {
                            System.out.println("!Error - Invalid Input - Try again");
                            sc.nextLine();
                        }
                    }
                    exceptionControl=false;
                    
                    //validacion Id
                    if( gc.validationId(Id) ){
                        System.out.println("Error! - The input ID has already been used ");
                    }else{                    
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
                        
                        cont++;
                        aux = cont + "." + sc.nextLine() + "\n";
                        while (op6 != 2) {
                            System.out.println("Do you want to add another objective?");
                            System.out.println("1.Yes\n2.No");
                            
                            while(exceptionControl==false){
                                try {
                                op6 = sc.nextInt();
                                    exceptionControl=true;
                                } catch (InputMismatchException e) {
                                    System.out.println("!Error - Invalid Input - Try again");
                                    sc.nextLine();
                                }
                            }
                            exceptionControl=false;
       
                            switch (op6) {
                                case 1:
                                    System.out.println("Input objective of the Investigation group");
                                    sc.nextLine();
                                    cont++;
                                    aux += cont + "." + sc.nextLine() + "\n";
                                    break;
                                case 2:
                                    break;

                                default:
                                    break;
                            }

                        }
                        
                        gc.addGroup(gc.createGroup(Id, faculty, name, initial, email, aux));
                    }
                    if( gc.showInformation().isEmpty() == false){

                    }else{
                        System.out.println("There is no groups created");
                    } 


                break;
                case 3:
                    int op1 = 0, op2 = 0, op3 = 0, op4 = 0;
                    gc.loadGroups();
                    
                    if( gc.getGroupList().isEmpty() == false ){
                    
                        System.out.println(gc.showIdGroups());
                        System.out.println("Input the ID of the group you want to be modified");
                        
                        while(exceptionControl==false){
                            try {
                            Id = sc.nextInt();
                                exceptionControl=true;
                            } catch (InputMismatchException e) {
                                System.out.println("!Error - Invalid Input - Try again");
                                sc.nextLine();
                            }
                        }
                        exceptionControl=false;
                        
                        // validacion correcta Id
                        if (gc.searchGroup(Id) != -1) {

                            while (op1 != 3) {
                                int search = 0;
                                System.out.println("\n------ Menu group: " + Id + " -----");
                                System.out.println("Choose one of the options to modify");
                                System.out.println("1. Modify information of the group");
                                System.out.println("2. Modify projects of the group");
                                System.out.println("3. Return\n-----");
                                
                                 while(exceptionControl==false){
                                    try {
                                    op1 = sc.nextInt();
                                        exceptionControl=true;
                                    } catch (InputMismatchException e) {
                                        System.out.println("!Error - Invalid Input - Try again");
                                        sc.nextLine();
                                    }
                                }
                                exceptionControl=false;

                                switch (op1) {
                                    case 1: // modificar grupos
                                        System.out.println(
                                        "\n----- Group information -----\n1.Faculty\n2.Name\n3.Initial\n4.Email\n5.Goal\n6.Return");
                                        while(exceptionControl==false){
                                            try {
                                                op2 = sc.nextInt();
                                                exceptionControl=true;
                                            } catch (InputMismatchException e) {
                                                System.out.println("!Error - Invalid Input - Try again");
                                                sc.nextLine();
                                            }
                                        }
                                        exceptionControl=false;    
                                        
                                        switch (op2) {
                                            case 1:
                                                String fac = "";
                                                search = gc.searchGroup(Id);
                                                if (search != -1) {
                                                    System.out.println(gc.getGroupList().get(search).getFaculty());
                                                    System.out.println("Input the new faculty");
                                                    sc.nextLine();
                                                    fac = sc.nextLine();
                                                    gc.modifyGroup(Id, op2, fac);
                                                } else {
                                                    System.out.println("the group does not exist");
                                                }
                                                break;
                                            case 2:
                                                String nam = "";
                                                search = gc.searchGroup(Id);
                                                if (search != -1) {
                                                    System.out.println(gc.getGroupList().get(search).getName());
                                                    System.out.println("Input the new name");
                                                    sc.nextLine();
                                                    nam = sc.nextLine();
                                                    gc.modifyGroup(Id, op2, nam);
                                                } else {
                                                    System.out.println("the group does not exist");
                                                }
                                                break;
                                            case 3:
                                                String init = "";
                                                search = gc.searchGroup(Id);
                                                if (search != -1) {
                                                    System.out.println(gc.getGroupList().get(search).getInitial());
                                                    System.out.println("Input the new acronym");
                                                    sc.nextLine();
                                                    init = sc.nextLine();
                                                    gc.modifyGroup(Id, op2, init);
                                                } else {
                                                    System.out.println("the group does not exist");
                                                }
                                                break;
                                            case 4:
                                                String ema = "";
                                                search = gc.searchGroup(Id);
                                                if (search != -1) {
                                                    System.out.println(gc.getGroupList().get(search).getEmail());
                                                    System.out.println("Input the new email");
                                                    sc.nextLine();
                                                    ema = sc.nextLine();
                                                    gc.modifyGroup(Id, op2, ema);
                                                } else {
                                                    System.out.println("the group does not exist");
                                                }
                                                break;
                                            case 5:
                                                int opObject = 0;
                                                search = gc.searchGroup(Id);
                                                System.out.println(gc.getGroupList().get(search).getGoal());
                                                while (opObject != 4) {
                                                    System.out.println("Choose any of the following options:");
                                                    System.out.println(
                                                            "1.Add objective\n2.Modify objective\n3.remove objective\n4.Return");
                                                    while(exceptionControl==false){
                                                        try {
                                                        opObject = sc.nextInt();
                                                            exceptionControl=true;
                                                        } catch (InputMismatchException e) {
                                                            System.out.println("!Error - Invalid Input - Try again");
                                                            sc.nextLine();
                                                        }
                                                    }
                                                    exceptionControl=false;
                                                            
                                                    switch (opObject) {
                                                        case 1:
                                                            System.out
                                                                    .println("Input objective of the Investigation group");
                                                            cont++;
                                                            sc.nextLine();
                                                            gc.getGroupList().get(search).setGoal(aux += cont + "." + sc.nextLine() + "\n");

                                                            System.out.println("It has been added successfully");

                                                            break;
                                                        case 2:

                                                            System.out.println("Current goals of the group:");
                                                            System.out.println(gc.getGroupList().get(search).getGoal());

                                                            // Solicitar al usuario el número del objetivo que desea
                                                            // modificar
                                                            System.out.println("Input the number of the objective you want to modify:");
                                                            int numeroObjetivo=0;
                                                            while(exceptionControl==false){
                                                                try {
                                                                    numeroObjetivo = sc.nextInt();
                                                                    exceptionControl=true;
                                                                } catch (InputMismatchException e) {
                                                                    System.out.println("!Error - Invalid Input - Try again");
                                                                    sc.nextLine();
                                                                }
                                                            }
                                                            exceptionControl=false;
                                                            sc.nextLine();

                                                            // Solicitar al usuario el nuevo texto del objetivo
                                                            System.out.println("Input the new text of objective:");
                                                            String nuevoTexto = sc.nextLine();

                                                            // Modificar el objetivo con el nuevo texto
                                                            String objetivosActuales = gc.getGroupList().get(search).getGoal();
                                                            String objetivosModificados = gc.modifyObjective(objetivosActuales, numeroObjetivo, nuevoTexto);

                                                            // Actualizar la lista de objetivos del grupo con los
                                                            // objetivos modificados
                                                            gc.getGroupList().get(search).setGoal(objetivosModificados);

                                                            break;
                                                        case 3:
                                                            // Display the current goals of the group
                                                            System.out.println("Current goals of the group:");
                                                            System.out.println(gc.getGroupList().get(search).getGoal());

                                                            // Request the user to input the number of the objective they
                                                            // want to delete
                                                            System.out.println("Input the number of the objective you want to delete:");
                                                            int objectiveNumberToDelete = 0;
                                                            while(exceptionControl==false){
                                                                try {
                                                                    objectiveNumberToDelete = sc.nextInt();
                                                                    exceptionControl=true;
                                                                } catch (InputMismatchException e) {
                                                                    System.out.println("!Error - Invalid Input - Try again");
                                                                    sc.nextLine();
                                                                }
                                                            }
                                                            exceptionControl=false;
                                                            sc.nextLine();

                                                            // Delete the objective with the provided number
                                                            String currentGoalsToDelete = gc.getGroupList().get(search).getGoal();
                                                            String modifiedGoalsAfterDeletion = gc.removeObjectives(currentGoalsToDelete, objectiveNumberToDelete);

                                                            // Update the group's list of objectives with the modified goals
                                                            gc.getGroupList().get(search).setGoal(modifiedGoalsAfterDeletion);

                                                            break;

                                                        default:
                                                            break;
                                                    }

                                                }

                                                break;

                                            default:
                                                System.out.println("invalid option");
                                                break;
                                        }
                                        break;

                                    // Case 2 - Modificar proyectos
                                    case 2:
                                        while (op3 != 5) {
                                            System.out.println( "\n----- Projects -----\n1.Create new project\n2.Modify existing project\n3.Delete project\n4.Show projects\n5.Return\n-----");
                                            
                                            while(exceptionControl==false){
                                                        try {
                                                            op3 = sc.nextInt();
                                                            exceptionControl=true;
                                                        } catch (InputMismatchException e) {
                                                            System.out.println("!Error - Invalid Input - Try again");
                                                            sc.nextLine();
                                                        }
                                                    }
                                                    exceptionControl=false;

                                            switch (op3) {
                                                case 1:
                                                System.out.println("Input Id of the project");
                                                    
                                                    while(exceptionControl==false){
                                                        try {
                                                            idProject = sc.nextInt();
                                                            exceptionControl=true;
                                                        } catch (InputMismatchException e) {
                                                            System.out.println("!Error - Invalid Input - Try again");
                                                            sc.nextLine();
                                                        }
                                                    }
                                                    exceptionControl=false;
                                                    
                                                    if(gc.validationId(idProject)){
                                                        System.out.println("Error! - The input ID has already been used ");
                                                    }else{
                                                        sc.nextLine();
                                                        System.out.println("Input name of the project");
                                                        nameProject = sc.nextLine();
                                                        System.out.println("Input state of the project\n1. In progress\n2.Finalized");
                                                        
                                                        stateProjectOp = 0;                                            
                                                        while(stateProjectOp !=1 && stateProjectOp!=2){   
                                                            while(exceptionControl==false){
                                                                try {
                                                                    stateProjectOp = sc.nextInt();
                                                                    exceptionControl=true;
                                                                } catch (InputMismatchException e) {
                                                                    System.out.println("!Error - Invalid Input - Try again");
                                                                    sc.nextLine();
                                                                }
                                                            }
                                                            exceptionControl=false;
                                                            
                                                            switch(stateProjectOp){
                                                                case 1:
                                                                stateProject = true;
                                                                break;
                                                                case 2:
                                                                stateProject = false;
                                                                break;
                                                                default:
                                                                System.out.println("Invalid - Select the correct option");
                                                                break;
                                                            }
                                                        }
                                                        sc.nextLine();
                                                        System.out.println("Input description of the project");
                                                        descriptionProject = sc.nextLine();

                                                        gc.addProject(Id, gc.createProject(idProject, nameProject, stateProject, descriptionProject) );
                                            
                                                    }
                                                    break;
                                                case 2:
                                                    if( gc.showInformationProjects().isEmpty() == false){
                                                        System.out.println(gc.showInformationProjects());  
                                                        
                                                        System.out.println("Input Id of the project");

                                                        while(exceptionControl==false){
                                                            try {
                                                                idProject = sc.nextInt();
                                                                exceptionControl=true;
                                                            } catch (InputMismatchException e) {
                                                                System.out.println("!Error - Invalid Input - Try again");
                                                                sc.nextLine();
                                                            }
                                                        }
                                                        exceptionControl=false;
                                                        

                                                        search = gc.searchProject(idProject);

                                                        if(search != -1){
                                                            System.out.println("1. Name");
                                                            System.out.println("2. Description");
                                                            System.out.println("3. State");


                                                            while(exceptionControl==false){
                                                                try {
                                                                    op4 = sc.nextInt();
                                                                    exceptionControl=true;
                                                                } catch (InputMismatchException e) {
                                                                    System.out.println("!Error - Invalid Input - Try again");
                                                                    sc.nextLine();
                                                                }
                                                            }
                                                            exceptionControl=false;
                                                            
                                                            
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
                                                            System.out.println("Input Id is invalid");
                                                        }

                                                    } else{
                                                        System.out.println("This group doesn't have projects");
                                                    } 

                                                break;

                                                case 3:
                                                    if( gc.showInformationProjects().isEmpty() == false){
                                                        System.out.println(gc.showInformationProjects());   
                                                        System.out.println("Enter project ID that you want to be deleted");
                                                        
                                                        while(exceptionControl==false){
                                                                try {
                                                                    idProject = sc.nextInt();
                                                                    exceptionControl=true;
                                                                } catch (InputMismatchException e) {
                                                                    System.out.println("!Error - Invalid Input - Try again");
                                                                    sc.nextLine();
                                                                }
                                                            }
                                                        exceptionControl=false;
                                                    
                                                        if (gc.searchProject(idProject) != -1){
                                                            System.out.println("Are you sure to delete the project?");
                                                            System.out.println("1.Yes\n2.No");

                                                            while(exceptionControl==false){
                                                                try {
                                                                    op4 = sc.nextInt();
                                                                    exceptionControl=true;
                                                                } catch (InputMismatchException e) {
                                                                    System.out.println("!Error - Invalid Input - Try again");
                                                                    sc.nextLine();
                                                                }
                                                            }
                                                            exceptionControl=false;
                                                            
                                                            switch (op4) {                                                    
                                                                case 1:
                                                                    if(gc.removeProject(Id, idProject)){
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

                                                        }
                                                    } else{
                                                        System.out.println("This group doesn't have projects");
                                                    }

                                                    break;
                                                case 4:
                                                    if (gc.showInformationProjects().isEmpty() == false) {
                                                        System.out.println(gc.showInformationProjects());
                                                    } else {
                                                        System.out.println("This group doesn't have projects");
                                                    }
                                                    break;

                                                default:
                                                    break;
                                            }
                                    }

                                        break;// break modificar proyectos

                                    case 3:

                                        break;

                                } // switch close op1

                            } // close while op1
                        } else {
                            System.out.println("! Error - Invalid Id");
                        } // close validacion id

                    }else{
                        System.out.println("There is no groups created");
                    }// close validacion listGroups is empty
                    break; // break case 3 modificar grupos y proyectos

                case 4:
                    int op5 = 0;
                    gc.loadGroups();
                    if(gc.checkarray()){
                        System.out.println("There is no groups created");
                        break;
                    }

                    System.out.println(gc.showIdGroups());       
                   
                   
                    System.out.println("Enter group ID that you want to be deleted");
                    
                    while(exceptionControl==false){
                        try {
                            num = sc.nextInt();
                            exceptionControl=true;
                        } catch (InputMismatchException e) {
                            System.out.println("!Error - Invalid Input - Try again");
                            sc.nextLine();
                        }
                    }
                    exceptionControl=false;
              
                    
                    System.out.println("Are you sure to delete the group?");
                    System.out.println("1.Yes\n2.No");
                    while(exceptionControl==false){
                        try {
                            op5 = sc.nextInt();
                            exceptionControl=true;
                        } catch (InputMismatchException e) {
                            System.out.println("!Error - Invalid Input - Try again");
                            sc.nextLine();
                        }
                    }
                    exceptionControl=false;
                    switch (op5) {
                        case 1:
                            if (gc.deleteGroup(num)) {
                                System.out.println("removed successfully");
                            } else {
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
                break;
                case 2:
                  System.out.println(this.logOut() ? "Logging out" : "Error. Try again");
                break;
                default:
                 System.out.println("option not valid, try again");
                break;
                }
            }
    }

    public void optionsSecretary(){
         GroupController gc = new GroupController();
        Scanner sc = new Scanner(System.in);
        String faculty = "", name = "", initial = "", email = "", project = "";
        String nameProject = "", descriptionProject = "", aux = "";
        int op = 0, num = 0, Id = 0, state = 0, m = 0, idProject = 0, cont = 0, stateProjectOp=0;
        boolean stateProject = false, exceptionControl=false;
         String loginMessage = """
                ========================
                |\t1.Investigation groups\t|
                |\t2.logout\t\t\t|
                ========================""";
                int op10=0;
                while(op10 !=2){
                  System.out.println(loginMessage);
                  op10=this.util.inputInt("Selection -----> ", this.errorMessage);
                switch(op10){
                case 1:
                while (op != 5) {
                    System.out.println(
                    "\n----- Main Menu -----\n1.View groups\n2.Create a group\n3.Modify a group\n4.Delete a group\n5.Exit\n-----");
                    while(exceptionControl==false){
                        try {
                            op = sc.nextInt();
                            exceptionControl=true;
                        } catch (InputMismatchException e) {
                            System.out.println("!Error - Invalid Input - Try again");
                            sc.nextLine();
                        }
                    }
                    exceptionControl=false;
        
                    switch (op) {
                        case 1:
                            gc.loadGroups();
                            if( gc.showInformation().isEmpty() == false){
                                System.out.println(gc.showInformation());   
                                } else{
                                    System.out.println("There is no groups created");
                                } 
                            break;
                        case 2:
                            gc.loadGroups();
                            int op6=0;
                            System.out.println("Input Id of the Investigation group");
                            
                            while(exceptionControl==false){
                                try {
                                    Id = sc.nextInt();
                                    exceptionControl=true;
                                } catch (InputMismatchException e) {
                                    System.out.println("!Error - Invalid Input - Try again");
                                    sc.nextLine();
                                }
                            }
                            exceptionControl=false;
                            
                            //validacion Id
                            if( gc.validationId(Id) ){
                                System.out.println("Error! - The input ID has already been used ");
                            }else{                    
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
                                
                                cont++;
                                aux = cont + "." + sc.nextLine() + "\n";
                                while (op6 != 2) {
                                    System.out.println("Do you want to add another objective?");
                                    System.out.println("1.Yes\n2.No");
                                    
                                    while(exceptionControl==false){
                                        try {
                                        op6 = sc.nextInt();
                                            exceptionControl=true;
                                        } catch (InputMismatchException e) {
                                            System.out.println("!Error - Invalid Input - Try again");
                                            sc.nextLine();
                                        }
                                    }
                                    exceptionControl=false;
               
                                    switch (op6) {
                                        case 1:
                                            System.out.println("Input objective of the Investigation group");
                                            sc.nextLine();
                                            cont++;
                                            aux += cont + "." + sc.nextLine() + "\n";
                                            break;
                                        case 2:
                                            break;
        
                                        default:
                                            break;
                                    }
        
                                }
                                
                                gc.addGroup(gc.createGroup(Id, faculty, name, initial, email, aux));
                            }
                            if( gc.showInformation().isEmpty() == false){
        
                            }else{
                                System.out.println("There is no groups created");
                            } 
        
        
                        break;
                        case 3:
                            int op1 = 0, op2 = 0, op3 = 0, op4 = 0;
                            gc.loadGroups();
                            
                            if( gc.getGroupList().isEmpty() == false ){
                            
                                System.out.println(gc.showIdGroups());
                                System.out.println("Input the ID of the group you want to be modified");
                                
                                while(exceptionControl==false){
                                    try {
                                    Id = sc.nextInt();
                                        exceptionControl=true;
                                    } catch (InputMismatchException e) {
                                        System.out.println("!Error - Invalid Input - Try again");
                                        sc.nextLine();
                                    }
                                }
                                exceptionControl=false;
                                
                                // validacion correcta Id
                                if (gc.searchGroup(Id) != -1) {
        
                                    while (op1 != 3) {
                                        int search = 0;
                                        System.out.println("\n------ Menu group: " + Id + " -----");
                                        System.out.println("Choose one of the options to modify");
                                        System.out.println("1. Modify information of the group");
                                        System.out.println("2. Modify projects of the group");
                                        System.out.println("3. Return\n-----");
                                        
                                         while(exceptionControl==false){
                                            try {
                                            op1 = sc.nextInt();
                                                exceptionControl=true;
                                            } catch (InputMismatchException e) {
                                                System.out.println("!Error - Invalid Input - Try again");
                                                sc.nextLine();
                                            }
                                        }
                                        exceptionControl=false;
        
                                        switch (op1) {
                                            case 1: // modificar grupos
                                                System.out.println(
                                                "\n----- Group information -----\n1.Faculty\n2.Name\n3.Initial\n4.Email\n5.Goal\n6.Return");
                                                while(exceptionControl==false){
                                                    try {
                                                        op2 = sc.nextInt();
                                                        exceptionControl=true;
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("!Error - Invalid Input - Try again");
                                                        sc.nextLine();
                                                    }
                                                }
                                                exceptionControl=false;    
                                                
                                                switch (op2) {
                                                    case 1:
                                                        String fac = "";
                                                        search = gc.searchGroup(Id);
                                                        if (search != -1) {
                                                            System.out.println(gc.getGroupList().get(search).getFaculty());
                                                            System.out.println("Input the new faculty");
                                                            sc.nextLine();
                                                            fac = sc.nextLine();
                                                            gc.modifyGroup(Id, op2, fac);
                                                        } else {
                                                            System.out.println("the group does not exist");
                                                        }
                                                        break;
                                                    case 2:
                                                        String nam = "";
                                                        search = gc.searchGroup(Id);
                                                        if (search != -1) {
                                                            System.out.println(gc.getGroupList().get(search).getName());
                                                            System.out.println("Input the new name");
                                                            sc.nextLine();
                                                            nam = sc.nextLine();
                                                            gc.modifyGroup(Id, op2, nam);
                                                        } else {
                                                            System.out.println("the group does not exist");
                                                        }
                                                        break;
                                                    case 3:
                                                        String init = "";
                                                        search = gc.searchGroup(Id);
                                                        if (search != -1) {
                                                            System.out.println(gc.getGroupList().get(search).getInitial());
                                                            System.out.println("Input the new acronym");
                                                            sc.nextLine();
                                                            init = sc.nextLine();
                                                            gc.modifyGroup(Id, op2, init);
                                                        } else {
                                                            System.out.println("the group does not exist");
                                                        }
                                                        break;
                                                    case 4:
                                                        String ema = "";
                                                        search = gc.searchGroup(Id);
                                                        if (search != -1) {
                                                            System.out.println(gc.getGroupList().get(search).getEmail());
                                                            System.out.println("Input the new email");
                                                            sc.nextLine();
                                                            ema = sc.nextLine();
                                                            gc.modifyGroup(Id, op2, ema);
                                                        } else {
                                                            System.out.println("the group does not exist");
                                                        }
                                                        break;
                                                    case 5:
                                                        int opObject = 0;
                                                        search = gc.searchGroup(Id);
                                                        System.out.println(gc.getGroupList().get(search).getGoal());
                                                        while (opObject != 4) {
                                                            System.out.println("Choose any of the following options:");
                                                            System.out.println(
                                                                    "1.Add objective\n2.Modify objective\n3.remove objective\n4.Return");
                                                            while(exceptionControl==false){
                                                                try {
                                                                opObject = sc.nextInt();
                                                                    exceptionControl=true;
                                                                } catch (InputMismatchException e) {
                                                                    System.out.println("!Error - Invalid Input - Try again");
                                                                    sc.nextLine();
                                                                }
                                                            }
                                                            exceptionControl=false;
                                                                    
                                                            switch (opObject) {
                                                                case 1:
                                                                    System.out
                                                                            .println("Input objective of the Investigation group");
                                                                    cont++;
                                                                    sc.nextLine();
                                                                    gc.getGroupList().get(search).setGoal(aux += cont + "." + sc.nextLine() + "\n");
        
                                                                    System.out.println("It has been added successfully");
        
                                                                    break;
                                                                case 2:
        
                                                                    System.out.println("Current goals of the group:");
                                                                    System.out.println(gc.getGroupList().get(search).getGoal());
        
                                                                    // Solicitar al usuario el número del objetivo que desea
                                                                    // modificar
                                                                    System.out.println("Input the number of the objective you want to modify:");
                                                                    int numeroObjetivo=0;
                                                                    while(exceptionControl==false){
                                                                        try {
                                                                            numeroObjetivo = sc.nextInt();
                                                                            exceptionControl=true;
                                                                        } catch (InputMismatchException e) {
                                                                            System.out.println("!Error - Invalid Input - Try again");
                                                                            sc.nextLine();
                                                                        }
                                                                    }
                                                                    exceptionControl=false;
                                                                    sc.nextLine();
        
                                                                    // Solicitar al usuario el nuevo texto del objetivo
                                                                    System.out.println("Input the new text of objective:");
                                                                    String nuevoTexto = sc.nextLine();
        
                                                                    // Modificar el objetivo con el nuevo texto
                                                                    String objetivosActuales = gc.getGroupList().get(search).getGoal();
                                                                    String objetivosModificados = gc.modifyObjective(objetivosActuales, numeroObjetivo, nuevoTexto);
        
                                                                    // Actualizar la lista de objetivos del grupo con los
                                                                    // objetivos modificados
                                                                    gc.getGroupList().get(search).setGoal(objetivosModificados);
        
                                                                    break;
                                                                case 3:
                                                                    // Display the current goals of the group
                                                                    System.out.println("Current goals of the group:");
                                                                    System.out.println(gc.getGroupList().get(search).getGoal());
        
                                                                    // Request the user to input the number of the objective they
                                                                    // want to delete
                                                                    System.out.println("Input the number of the objective you want to delete:");
                                                                    int objectiveNumberToDelete = 0;
                                                                    while(exceptionControl==false){
                                                                        try {
                                                                            objectiveNumberToDelete = sc.nextInt();
                                                                            exceptionControl=true;
                                                                        } catch (InputMismatchException e) {
                                                                            System.out.println("!Error - Invalid Input - Try again");
                                                                            sc.nextLine();
                                                                        }
                                                                    }
                                                                    exceptionControl=false;
                                                                    sc.nextLine();
        
                                                                    // Delete the objective with the provided number
                                                                    String currentGoalsToDelete = gc.getGroupList().get(search).getGoal();
                                                                    String modifiedGoalsAfterDeletion = gc.removeObjectives(currentGoalsToDelete, objectiveNumberToDelete);
        
                                                                    // Update the group's list of objectives with the modified goals
                                                                    gc.getGroupList().get(search).setGoal(modifiedGoalsAfterDeletion);
        
                                                                    break;
        
                                                                default:
                                                                    break;
                                                            }
        
                                                        }
        
                                                        break;
        
                                                    default:
                                                        System.out.println("invalid option");
                                                        break;
                                                }
                                                break;
        
                                            // Case 2 - Modificar proyectos
                                            case 2:
                                                while (op3 != 5) {
                                                    System.out.println( "\n----- Projects -----\n1.Create new project\n2.Modify existing project\n3.Delete project\n4.Show projects\n5.Return\n-----");
                                                    
                                                    while(exceptionControl==false){
                                                                try {
                                                                    op3 = sc.nextInt();
                                                                    exceptionControl=true;
                                                                } catch (InputMismatchException e) {
                                                                    System.out.println("!Error - Invalid Input - Try again");
                                                                    sc.nextLine();
                                                                }
                                                            }
                                                            exceptionControl=false;
        
                                                    switch (op3) {
                                                        case 1:
                                                        System.out.println("Input Id of the project");
                                                            
                                                            while(exceptionControl==false){
                                                                try {
                                                                    idProject = sc.nextInt();
                                                                    exceptionControl=true;
                                                                } catch (InputMismatchException e) {
                                                                    System.out.println("!Error - Invalid Input - Try again");
                                                                    sc.nextLine();
                                                                }
                                                            }
                                                            exceptionControl=false;
                                                            
                                                            if(gc.validationId(idProject)){
                                                                System.out.println("Error! - The input ID has already been used ");
                                                            }else{
                                                                sc.nextLine();
                                                                System.out.println("Input name of the project");
                                                                nameProject = sc.nextLine();
                                                                System.out.println("Input state of the project\n1. En curso\n2.Finalizado");
                                                                
                                                                stateProjectOp = 0;                                            
                                                                while(stateProjectOp !=1 && stateProjectOp!=2){   
                                                                    while(exceptionControl==false){
                                                                        try {
                                                                            stateProjectOp = sc.nextInt();
                                                                            exceptionControl=true;
                                                                        } catch (InputMismatchException e) {
                                                                            System.out.println("!Error - Invalid Input - Try again");
                                                                            sc.nextLine();
                                                                        }
                                                                    }
                                                                    exceptionControl=false;
                                                                    
                                                                    switch(stateProjectOp){
                                                                        case 1:
                                                                        stateProject = true;
                                                                        break;
                                                                        case 2:
                                                                        stateProject = false;
                                                                        break;
                                                                        default:
                                                                        System.out.println("Invalid - Select the correct option");
                                                                        break;
                                                                    }
                                                                }
                                                                sc.nextLine();
                                                                System.out.println("Input description of the project");
                                                                descriptionProject = sc.nextLine();
        
                                                                gc.addProject(Id, gc.createProject(idProject, nameProject, stateProject, descriptionProject) );
                                                    
                                                            }
                                                            break;
                                                        case 2:
                                                            if( gc.showInformationProjects().isEmpty() == false){
                                                                System.out.println(gc.showInformationProjects());  
                                                                
                                                                System.out.println("Input Id of the project");
        
                                                                while(exceptionControl==false){
                                                                    try {
                                                                        idProject = sc.nextInt();
                                                                        exceptionControl=true;
                                                                    } catch (InputMismatchException e) {
                                                                        System.out.println("!Error - Invalid Input - Try again");
                                                                        sc.nextLine();
                                                                    }
                                                                }
                                                                exceptionControl=false;
                                                                
        
                                                                search = gc.searchProject(idProject);
        
                                                                if(search != -1){
                                                                    System.out.println("1. Name");
                                                                    System.out.println("2. Description");
                                                                    System.out.println("3. State");
        
        
                                                                    while(exceptionControl==false){
                                                                        try {
                                                                            op4 = sc.nextInt();
                                                                            exceptionControl=true;
                                                                        } catch (InputMismatchException e) {
                                                                            System.out.println("!Error - Invalid Input - Try again");
                                                                            sc.nextLine();
                                                                        }
                                                                    }
                                                                    exceptionControl=false;
                                                                    
                                                                    
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
                                                                    System.out.println("Input Id is invalid");
                                                                }
        
                                                            } else{
                                                                System.out.println("This group doesn't have projects");
                                                            } 
        
                                                        break;
        
                                                        case 3:
                                                            if( gc.showInformationProjects().isEmpty() == false){
                                                                System.out.println(gc.showInformationProjects());   
                                                                System.out.println("Enter project ID that you want to be deleted");
                                                                
                                                                while(exceptionControl==false){
                                                                        try {
                                                                            idProject = sc.nextInt();
                                                                            exceptionControl=true;
                                                                        } catch (InputMismatchException e) {
                                                                            System.out.println("!Error - Invalid Input - Try again");
                                                                            sc.nextLine();
                                                                        }
                                                                    }
                                                                exceptionControl=false;
                                                            
                                                                if (gc.searchProject(idProject) != -1){
                                                                    System.out.println("Are you sure to delete the project?");
                                                                    System.out.println("1.Yes\n2.No");
        
                                                                    while(exceptionControl==false){
                                                                        try {
                                                                            op4 = sc.nextInt();
                                                                            exceptionControl=true;
                                                                        } catch (InputMismatchException e) {
                                                                            System.out.println("!Error - Invalid Input - Try again");
                                                                            sc.nextLine();
                                                                        }
                                                                    }
                                                                    exceptionControl=false;
                                                                    
                                                                    
                                                                    
                                                                    switch (op4) {                                                    
                                                                        case 1:
                                                                            if(gc.removeProject(Id, idProject)){
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
        
                                                                }
                                                            } else{
                                                                System.out.println("This group doesn't have projects");
                                                            }
        
                                                            break;
                                                        case 4:
                                                            if (gc.showInformationProjects().isEmpty() == false) {
                                                                System.out.println(gc.showInformationProjects());
                                                            } else {
                                                                System.out.println("This group doesn't have projects");
                                                            }
                                                            break;
        
                                                        default:
                                                            break;
                                                    }
                                            }
        
                                                break;// break modificar proyectos
        
                                            case 3:
        
                                                break;
        
                                        } // switch close op1
        
                                    } // close while op1
                                } else {
                                    System.out.println("! Error - Invalid Id");
                                } // close validacion id
        
                            }else{
                                System.out.println("There is no groups created");
                            }// close validacion listGroups is empty
                            break; // break case 3 modificar grupos y proyectos
        
                        case 4:
                            int op5 = 0;
                            gc.loadGroups();
                            if(gc.checkarray()){
                                System.out.println("There is no groups created");
                                break;
                            }
        
                            System.out.println(gc.showIdGroups());       
                           
                           
                            System.out.println("Enter group ID that you want to be deleted");
                            
                            while(exceptionControl==false){
                                try {
                                    num = sc.nextInt();
                                    exceptionControl=true;
                                } catch (InputMismatchException e) {
                                    System.out.println("!Error - Invalid Input - Try again");
                                    sc.nextLine();
                                }
                            }
                            exceptionControl=false;
                      
                            
                            System.out.println("Are you sure to delete the group?");
                            System.out.println("1.Yes\n2.No");
                            while(exceptionControl==false){
                                try {
                                    op5 = sc.nextInt();
                                    exceptionControl=true;
                                } catch (InputMismatchException e) {
                                    System.out.println("!Error - Invalid Input - Try again");
                                    sc.nextLine();
                                }
                            }
                            exceptionControl=false;
                            switch (op5) {
                                case 1:
                                    if (gc.deleteGroup(num)) {
                                        System.out.println("removed successfully");
                                    } else {
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
                break;
                case 2:
                  System.out.println(this.logOut() ? "Logging out" : "Error. Try again");
                break;
                default:
                System.out.println("option not valid, try again");
                break;
                }
            }
    }


    public  void optionsGeneralsLogin(){
        String loginMessage = """
                ========================
                |\t1.Sing in accounts\t|
                |\t2.see accounts\t\t|
                |\t3.change password\t|
                |\t4.logout\t\t\t|
                ========================""";
        int decision,role;
        do{
            System.out.println(loginMessage);
            decision = this.util.inputInt("Selection -----> ", this.errorMessage);
            switch (decision){
                case 1 :
                    System.out.println(this.singInAccounts() ?  "you successfully registered\n" +
                            "your user name is: "+loginController.getUserName()+"\n your password is: "+loginController.getPassword(): "Error with your data");
                break;
                case 2:
                    System.out.println(loginController.showAccounts());
                break;
                case 3:
                    System.out.println(this.changePassword() ? "password change was successful":"Error");
                break;
                case 4:
                    System.out.println(this.logOut() ? "Logging out" : "Error. Try again");
                break;
                default:
                    System.out.println("Invalid option, try again.");
                break;
            }
        }while(decision!=4);
    }

    public boolean singInAccounts(){
        String names = "", lastNames = "", id = "";
        int roleIndex = 0;

        roleIndex = this.util.inputInt("\tType your role in the university\n\t1.Student\n\t2.Professor\n\t3.Secretary\n\t-> ", errorMessage, 1,3);
        names = this.util.inputString("\tType their names: ", this.errorMessage);
        lastNames = this.util.inputString("\tType your last name: ", errorMessage);
        id = this.util.inputString("\tType your identification: ", errorMessage);

        return loginController.signin(names,lastNames,id,this.roles[roleIndex-1]);
    }

    public boolean changePassword(){
        String oldPassword = "", newPassword = "";
        oldPassword = this.util.inputString("\tType old password: ", errorMessage);
        newPassword = this.util.inputString("\t Your password will include 2 Uppercase characters, 2 Lowercase characters, 2 numbers. At least 6 characters.\n\tType new password: ", errorMessage);
        return this.loginController.changePassword(oldPassword, newPassword);
    }

    public boolean logOut(){
        return this.loginController.logout();
    }


    /**
     * This method shows the basic options for people who do not have special permissions
     */
    public  void optionsBasic(){
        GroupController gc = new GroupController();
        String message = """
                ========================
                |\t1.change password\t|
                |\t2.Investigation groups\t|
                |\t3.logout\t\t\t|
                ========================""";

        int decision = 0;
        do{
            System.out.println(message);
            decision = this.util.inputInt("Selection -----> ", this.errorMessage);
            switch (decision){
                case 1:
                    System.out.println(this.changePassword() ? "password change was successful": "Error");
                break;
                case 2:
                  gc.loadGroups();
                    if( gc.showInformation().isEmpty() == false){
                        System.out.println(gc.showInformation());   
                        } else{
                            System.out.println("There is no groups created");
                        } 
                break;
                case 3:
                    System.out.println(this.logOut() ? "Logging out" : "Error. Try again");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
                break;
            }

        }while(decision!=2);
    }
}

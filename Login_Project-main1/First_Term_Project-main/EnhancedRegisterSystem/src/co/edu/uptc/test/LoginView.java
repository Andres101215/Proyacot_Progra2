
package co.edu.uptc.test;

import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.uptc.controller.GroupController;
import co.edu.uptc.controller.LoginController;
import co.edu.uptc.utilities.InputLibrary;

/**
 * This class is to show the menu with its functions so
 * that it can be instantiated without having to make changes in the logic.
 * 
 * @Author Edwin Martinez
 * @Author Samuel Gonzalez
 * @Author Nicolas Sarmiento
 */
public class LoginView {

    private final InputLibrary util;
    private final LoginController loginController;
    private final String[] roles;
    private final String errorMessage;
    private GroupController gc = new GroupController();

    public LoginView() {
        this.util = new InputLibrary();
        this.loginController = new LoginController();
        this.roles = new String[] { "Student", "Professor", "Secretary", "Administrator" };
        this.errorMessage = "Invalid input. Try again";
    }

    /**
     * This is the main method that shows the menu
     */
    public static void main(String[] args) {

        LoginView portal = new LoginView();
        String decision;
        String message = """
                =============================
                |\t\t\tUPTC\t\t\t|
                |\t\t1.Login\t\t\t\t|
                |\t\t2.Exit\t\t\t\t|
                =============================""";
        String password = "", userName = "";
        do {
            System.out.println(message);
            decision = portal.util.inputString("Selection --------> ", "Invalid input. Try again");
            switch (decision) {
                case "1":
                    userName = portal.util.inputString("\tUsername: ", "Invalid Input. Try again");
                    password = portal.util.inputString("\tPassword: ", "Invalid input. Try again");
                    if (portal.loginController.login(userName, password)) {
                        portal.login();
                    } else {
                        System.out.println("User name or password incorrect");
                    }
                    break;
                case "2":
                    System.out.println("Coming out....");
                    break;
                default:
                    System.out.println("Invalid option. Try again");
            }
        } while (!decision.equals("2"));
    }

    /**
     * This method shows the options after login
     */
    public void login() {
        System.out.println(this.loginController.showInfoLoggedAcount());
        String role = loginController.showRol();

        if (role.equals("ADMINISTRATOR")) {
            this.optionsGeneralsLogin();
            return;
        } else if (role.equals("DIRECTOR")) {
            this.optionsDirector();
            return;
        } else if (role.equals("SECRETARY")) {
            this.optionsSecretary();
            ;
            return;
        }

        this.optionsBasic();
    }

    /**
     * This method shows the option for Administrator to show all accounts and
     * register new accounts
     */
    public void optionsDirector() {
        LoginView portal = new LoginView();
        Scanner sc = new Scanner(System.in);
        String faculty = "", name = "", initial = "", email = "", project = "";
        String nameProject = "", descriptionProject = "", aux = "";
        int num = 0, Id = 0, state = 0, m = 0, idProject = 0, cont = 0;
        String stateProjectOp = "";
        String op = "";
        boolean stateProject = false, exceptionControl = false, x = true;
        String loginMessage = """
                ========================
                |\t1.Investigation groups\t|
                |\t2.logout\t\t\t|

                ========================""";
        String op10 = " ";
        while (!op10.equals("2")) {
            System.out.println(loginMessage);
            op10 = this.util.inputString("Selection -----> ", this.errorMessage);
            switch (op10) {
                case "1":
                    op = "";
                    while (!op.equals("5")) {
                        System.out.println(
                                "\n----- Main Menu -----\n1.View groups\n2.Create a group\n3.Modify a group\n4.Delete a group\n5.Exit\n-----");
                        op = this.util.inputString("Selection -----> ", this.errorMessage);

                        switch (op) {
                            case "1":
                                String opx = "";
                                int p = 0;
                                gc.loadGroups();
                                if (gc.showInformation().isEmpty() == false) {
                                    System.out.println(gc.showInformation());
                                    System.out.println(
                                            "Do you want to see the projects of a investigation group?\n1.Yes \n2.No");
                                    opx = this.util.inputString("Selection -----> ", this.errorMessage);
                                    switch (opx) {
                                        case "1":
                                            p = this.util.inputInt(
                                                    " Input the ID of the investigation group whose projects you want to see:\n",
                                                    this.errorMessage);
                                            if (gc.searchGroup(p) != -1) {
                                                if (gc.showInformationProjects(p).isBlank() == false) {
                                                    System.out.println(gc.showInformationProjects(p));
                                                } else {
                                                    System.out.println(
                                                            "This group doesn't have projects");
                                                }

                                                break;
                                            } else {
                                                System.out.println("this id does not exist");
                                            }
                                            break;
                                        case "2":

                                            break;

                                        default:
                                            System.out.println("invalid option");
                                            break;
                                    }

                                } else {
                                    System.out.println("There is no groups created");
                                }
                                break;
                            case "2":
                                gc.loadGroups();
                                String op6 = "";
                                Id = gc.assignid();
                                System.out.println("The id of the new group is:" + Id);
                                faculty = portal.util.inputString("Input faculty of the Investigation group: ",
                                        "Invalid Input. Try again");
                                while (gc.checkNameGroupsize(faculty)) {
                                    System.out.println("The faculty must have at least 5 letters");
                                    faculty = portal.util.inputString("Input faculty of the Investigation group: ",
                                            "Invalid Input. Try again");
                                }

                                name = portal.util.inputString("Input name of the Investigation group: ",
                                        "Invalid Input. Try again");
                                while (gc.checkNameGroupsize(name) || gc.namevalidation(name)) {
                                    if (gc.checkNameGroupsize(name)) {
                                        System.out.println("The name must have at least 5 letters");
                                    }
                                    if (gc.namevalidation(name)) {
                                        System.out.println("this group already exists enter another name");
                                    }

                                    name = portal.util.inputString("Input name of the Investigation group: ",
                                            "Invalid Input. Try again");
                                }
                                initial = portal.util.inputString("Input initials of the Investigation group: ",
                                        "Invalid Input. Try again");
                                while (gc.checkInitialGroupsize(initial) || gc.initialvalidation(initial)) {
                                    if (gc.checkInitialGroupsize(initial)) {
                                        System.out.println("The initials must have a minimum of 2 letters");
                                    }
                                    if (gc
                                            .initialvalidation(initial)) {
                                        System.out.println("These initials are already used, try another");
                                    }

                                    initial = portal.util.inputString("Input initials of the Investigation group: ",
                                            "Invalid Input. Try again");
                                }

                                email = portal.util.inputString("Input email of the Investigation group: ",
                                        "Invalid Input. Try again");
                                while (gc.checkemail(email) || gc.emailvalidation(email)) {

                                    if (gc.checkemail(email)) {
                                        System.out.println("error email invalid");
                                    }
                                    if (gc.emailvalidation(email)) {
                                        System.out.println("This gmail is already registered try with another");
                                    }
                                    email = portal.util.inputString("Input email of the Investigation group: ",
                                            "Invalid Input. Try again");
                                }

                                aux = portal.util.inputString("Input objective of the Investigation group: ",
                                        "Invalid Input. Try again");
                                while (gc.checkNameGroupsize(aux)) {
                                    System.out.println("The objective must have a minimum of 5 letters");
                                    aux = portal.util.inputString("Input objective of the Investigation group: ",
                                            "Invalid Input. Try again");
                                }
                                gc.addGroup(gc.createGroup(Id, faculty, name, initial, email,
                                        gc.createObjective()));
                                gc.addObjective(Id, aux);
                                while (!op6.equals("2")) {
                                    System.out.println("Want to add another objective\n1.Yes\n2.No");
                                    op6 = sc.next();
                                    switch (op6) {
                                        case "1":
                                            String aux2 = "";
                                            aux2 = portal.util.inputString(
                                                    "Input objective of the Investigation group: ",
                                                    "Invalid Input. Try again");
                                            gc.addObjective(Id, aux2);
                                            break;
                                        case "2":
                                            break;

                                        default:
                                            System.out.println("invalid option");
                                            break;
                                    }
                                }
                                System.out.println("The investigation  group was created correctly");

                                if (gc.showInformation().isEmpty() == false) {

                                } else {
                                    System.out.println("There is no groups created");
                                }

                                break;
                            case "3":
                                String op1 = "", op2 = "", op3 = "", op4 = "";
                                gc.loadGroups();

                                if (gc.getGroupList().isEmpty() == false) {

                                    System.out.println(gc.showIdGroups());
                                    System.out.println("Input the ID of the group you want to be modified");

                                    while (exceptionControl == false) {
                                        try {
                                            Id = sc.nextInt();
                                            exceptionControl = true;
                                        } catch (InputMismatchException e) {
                                            System.out.println("!Error - Invalid Input - Try again");
                                            sc.nextLine();
                                        }
                                    }
                                    exceptionControl = false;

                                    // validacion correcta Id
                                    if (gc.searchGroup(Id) != -1) {

                                        while (!op1.equals("3")) {
                                            int search = 0;
                                            System.out.println("\n------ Menu group: " + Id + " -----");
                                            System.out.println("Choose one of the options to modify");
                                            System.out.println("1. Modify information of the group");
                                            System.out.println("2. Modify projects of the group");
                                            System.out.println("3. Return\n-----");

                                            op1 = this.util.inputString("Selection -----> ", this.errorMessage);

                                            switch (op1) {
                                                case "1": // modificar grupos
                                                    System.out.println(
                                                            "\n----- Group information -----\n1.Faculty\n2.Name\n3.Initial\n4.Email\n5.Goal\n6.Return");
                                                    op2 = this.util.inputString("Selection -----> ", this.errorMessage);

                                                    switch (op2) {
                                                        case "1":
                                                            String fac = "";
                                                            search = gc.searchGroup(Id);
                                                            if (search != -1) {
                                                                System.out.println(
                                                                        gc.getGroupList().get(search).getFaculty());
                                                                fac = portal.util.inputString("Input the new faculty: ",
                                                                        "Invalid Input. Try again");
                                                                while (gc.checkNameGroupsize(fac)) {
                                                                    System.out.println(
                                                                            "The faculty must have at least 5 letters");
                                                                    fac = portal.util.inputString(
                                                                            "Input faculty of the Investigation group: ",
                                                                            "Invalid Input. Try again");
                                                                }
                                                                gc.modifyGroup(Id, op2, fac);
                                                                System.out.println("Faculty changed successfully\n");
                                                            } else {
                                                                System.out.println("the group does not exist");
                                                            }
                                                            break;
                                                        case "2":
                                                            String nam = "";
                                                            search = gc.searchGroup(Id);
                                                            if (search != -1) {
                                                                System.out.println(
                                                                        gc.getGroupList().get(search).getName());
                                                                nam = portal.util.inputString("Input the new name: ",
                                                                        "Invalid Input. Try again");
                                                                while (gc.checkNameGroupsize(nam)
                                                                        || gc.namevalidation(nam)) {
                                                                    if (gc.checkNameGroupsize(nam)) {
                                                                        System.out.println(
                                                                                "The name must have at least 5 letters");
                                                                    }
                                                                    if (gc.namevalidation(nam)) {
                                                                        System.out.println(
                                                                                "this group already exists enter another name");
                                                                    }

                                                                    nam = portal.util.inputString(
                                                                            "Input name of the Investigation group: ",
                                                                            "Invalid Input. Try again");
                                                                }
                                                                gc.modifyGroup(Id, op2, nam);
                                                                System.out.println(
                                                                        "The name of the investigation group was successfully changed.");
                                                            } else {
                                                                System.out.println("the group does not exist");
                                                            }
                                                            break;
                                                        case "3":
                                                            String init = "";
                                                            search = gc.searchGroup(Id);
                                                            if (search != -1) {
                                                                System.out.println(
                                                                        gc.getGroupList().get(search).getInitial());
                                                                init = portal.util.inputString(
                                                                        "Input the new initials: ",
                                                                        "Invalid Input. Try again");
                                                                while (gc.checkInitialGroupsize(init)
                                                                        || gc.initialvalidation(init)) {
                                                                    if (gc.checkInitialGroupsize(init)) {
                                                                        System.out.println(
                                                                                "The initials must have a minimum of 2 letters\n");
                                                                    }
                                                                    if (gc.initialvalidation(init)) {
                                                                        System.out.println(
                                                                                "These initials are already used, try another\n");
                                                                    }

                                                                    init = portal.util.inputString(
                                                                            "Input initials of the Investigation group: ",
                                                                            "Invalid Input. Try again");
                                                                }

                                                                gc.modifyGroup(Id, op2, init);
                                                                System.out
                                                                        .println("Group initials changed correctly\n");
                                                            } else {
                                                                System.out.println("the group does not exist\n");
                                                            }
                                                            break;
                                                        case "4":
                                                            String ema = "";
                                                            search = gc.searchGroup(Id);
                                                            if (search != -1) {
                                                                System.out.println(
                                                                        gc.getGroupList().get(search).getEmail());
                                                                System.out.println("Input the new email");
                                                                ema = portal.util.inputString(
                                                                        "Input email of the Investigation group: ",
                                                                        "Invalid Input. Try again");
                                                                while (gc.checkemail(ema)
                                                                        || gc.emailvalidation(ema)) {

                                                                    if (gc.checkemail(ema)) {
                                                                        System.out.println("error email invalid");
                                                                    }
                                                                    if (gc.emailvalidation(ema)) {
                                                                        System.out.println(
                                                                                "This gmail is already registered try with another");
                                                                    }
                                                                    ema = portal.util.inputString(
                                                                            "Input email of the Investigation group: ",
                                                                            "Invalid Input. Try again");
                                                                }
                                                                gc.modifyGroup(Id, op2, ema);
                                                                System.out.println(
                                                                        "The email of the group was modified correctly");
                                                            } else {
                                                                System.out.println("the group does not exist");
                                                            }
                                                            break;
                                                        case "5":
                                                            String opObject = "";
                                                            search = gc.searchGroup(Id);
                                                            System.out.println(gc.showObjective(Id));
                                                            while (!opObject.equals("4")) {
                                                                System.out.println(
                                                                        "Choose any of the following options:");
                                                                System.out.println(
                                                                        "1.Add objective\n2.Modify objective\n3.remove objective\n4.Return");
                                                                opObject = this.util.inputString("Selection -----> ",
                                                                        this.errorMessage);
                                                                switch (opObject) {
                                                                    case "1":
                                                                        aux = portal.util.inputString(
                                                                                "Input the new objective you want to add: ",
                                                                                "Invalid Input. Try again");
                                                                        while (gc.checkNameGroupsize(aux)) {
                                                                            System.out.println(
                                                                                    "The objective must have a minimum of 5 letters");
                                                                            aux = portal.util.inputString(
                                                                                    "Input objective of the Investigation group: ",
                                                                                    "Invalid Input. Try again");
                                                                        }

                                                                        gc.addObjective(Id, aux);
                                                                        System.out.println("Target added successfully");

                                                                        break;

                                                                    case "2":
                                                                        if (gc.validationObjective(Id)) {
                                                                            System.out.println(
                                                                                    "This group has no objectives created");
                                                                        } else {
                                                                            int no = portal.util.inputInt(
                                                                                    "Input the objective number you want to modify: ",
                                                                                    "Invalid Input. Try again");
                                                                            if (no >= 1 && no <= gc.getGroupList()
                                                                                    .get(Id).getGoal().size()) {
                                                                                aux = portal.util.inputString(
                                                                                        "Input the new objective: ",
                                                                                        "Invalid Input. Try again");
                                                                                while (gc.checkNameGroupsize(aux)) {
                                                                                    System.out.println(
                                                                                            "The objective must have a minimum of 5 letters");
                                                                                    aux = portal.util.inputString(
                                                                                            "Input objective of the Investigation group: ",
                                                                                            "Invalid Input. Try again");
                                                                                }

                                                                                gc.modifyObjective(Id, no, aux);
                                                                                System.out.println(
                                                                                        "Target was successfully modified");
                                                                            } else {
                                                                                System.out
                                                                                        .println("invalid objective.");
                                                                            }

                                                                        }
                                                                        break;

                                                                    case "3":
                                                                        String d = "";
                                                                        int n = 0;
                                                                        if (gc.validationObjective(Id)) {
                                                                            System.out.println(
                                                                                    "This group has no objective created");
                                                                        } else {

                                                                            System.out.println(
                                                                                    "Input the objective number you want to delete");
                                                                            n = sc.nextInt();
                                                                            if (n >= 1 && n <= gc.getGroupList().get(Id)
                                                                                    .getGoal().size()) {
                                                                                System.out.println(
                                                                                        "Are you sure to delete the project?\n1.Yes\n2.No");
                                                                                d = this.util.inputString(
                                                                                        "Selection -----> ",
                                                                                        this.errorMessage);
                                                                                switch (d) {
                                                                                    case "1":
                                                                                        gc.removeObjective(Id, n);
                                                                                        System.out.println(
                                                                                                "remove succesfully");
                                                                                        break;
                                                                                    case "2":
                                                                                        break;

                                                                                    default:
                                                                                        System.out
                                                                                                .println(
                                                                                                        "Invalid option");
                                                                                        break;
                                                                                }
                                                                            } else {
                                                                                System.out
                                                                                        .println("invalid objective.");
                                                                            }
                                                                            break;
                                                                        }

                                                                    default:
                                                                        System.out.println("invalid option");
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
                                                case "2":
                                                    op3 = "";
                                                    while (!op3.equals("5")) {
                                                        System.out.println(
                                                                "\n----- Projects -----\n1.Create new project\n2.Modify existing project\n3.Delete project\n4.Show projects\n5.Return\n-----");
                                                        op3 = this.util.inputString("Selection -----> ",
                                                                this.errorMessage);

                                                        switch (op3) {
                                                            case "1":
                                                                idProject = gc.assignidproject(Id);
                                                                System.out.println(
                                                                        " The id of the new project is" + idProject);
                                                                nameProject = this.util.inputString(
                                                                        "Input name of the project :",
                                                                        this.errorMessage);

                                                
                                                        while (gc.checkNameGroupsize(nameProject)
                                                                        || gc.namevalidationproject(Id,
                                                                                nameProject)) {
                                                                    if (gc.checkNameGroupsize(nameProject)) {
                                                                        System.out.println(
                                                                                "The name must have at least 5 letters");
                                                                    }
                                                                    if (gc.namevalidationproject(Id,
                                                                            nameProject)) {
                                                                        System.out.println(
                                                                                "this project already exists enter another name");
                                                                    }
                                                                    nameProject = this.util.inputString(
                                                                            "Input name of the project: ",
                                                                            this.errorMessage);
                                                                }

                                                                System.out.println(
                                                                        "Input state of the project\n1. In progress\n2.Finalized");

                                                                stateProjectOp = "";
                                                                while (!stateProjectOp.equals("1")
                                                                        && !stateProjectOp.equals("2")) {

                                                                    stateProjectOp = this.util.inputString(
                                                                            "Selection -----> ",
                                                                            this.errorMessage);
                                                                    switch (stateProjectOp) {
                                                                        case "1":
                                                                            stateProject = true;
                                                                            break;
                                                                        case "2":
                                                                            stateProject = false;
                                                                            break;
                                                                        default:
                                                                            System.out.println(
                                                                                    "Invalid - Select the correct option");
                                                                            break;
                                                                    }
                                                                }

                                                                descriptionProject = this.util.inputString(
                                                                        "Input description of the project: ",
                                                                        this.errorMessage);
                                                                while (gc.checkNameGroupsize(descriptionProject)) {
                                                                    System.out.println(
                                                                            "The description project must have at least 5 letters");
                                                                    descriptionProject = this.util.inputString(
                                                                            "Input description of the project: ",
                                                                            this.errorMessage);
                                                                }

                                                                gc.addProject(Id,
                                                                        gc.createProject(idProject, nameProject,
                                                                                stateProject, descriptionProject));
                                                                System.out.println(
                                                                        "The project was created successfully");

                                                                break;
                                                            case "2":
                                                                if (gc.showInformationProjects(Id).isEmpty() == false) {
                                                                    System.out.println(gc.showInformationProjects(Id));

                                                                    System.out.println("Input Id of the project: ");

                                                                    while (exceptionControl == false) {
                                                                        try {
                                                                            idProject = sc.nextInt();
                                                                            exceptionControl = true;
                                                                        } catch (InputMismatchException e) {
                                                                            System.out.println(
                                                                                    "!Error - Invalid Input - Try again");
                                                                            sc.nextLine();
                                                                        }
                                                                    }
                                                                    exceptionControl = false;

                                                                    search = gc.searchProject(idProject);

                                                                    if (search != -1) {
                                                                        System.out.println("1. Name");
                                                                        System.out.println("2. Description");
                                                                        System.out.println("3. State");

                                                                        op4 = this.util.inputString("Selection -----> ",
                                                                                this.errorMessage);

                                                                        switch (op4) {
                                                                            case "1":
                                                                                nameProject = this.util.inputString(
                                                                                        "Input new name ",
                                                                                        this.errorMessage);
                                                                                while (gc.checkNameGroupsize(nameProject)
                                                                                        || gc.namevalidationproject(
                                                                                                Id,
                                                                                                nameProject)) {
                                                                                    if (gc.checkNameGroupsize(
                                                                                            nameProject)) {
                                                                                        System.out.println(
                                                                                                "The name must have at least 5 letters");
                                                                                    }
                                                                                    if (gc.namevalidationproject(
                                                                                            Id, nameProject)) {
                                                                                        System.out.println(
                                                                                                "this project already exists enter another name");
                                                                                    }
                                                                                    nameProject = this.util.inputString(
                                                                                            "Input new name ",
                                                                                            this.errorMessage);
                                                                                }

                                                                                gc.modifyProject(Id,idProject, op4,
                                                                                        nameProject, null);
                                                                                System.out.println(
                                                                                        "Changed project name successfully");
                                                                                break;
                                                                            case "2":
                                                                                descriptionProject = this.util
                                                                                        .inputString(
                                                                                                "Input new description of the project",
                                                                                                this.errorMessage);
                                                                                while (gc.checkNameGroupsize(
                                                                                        descriptionProject)) {
                                                                                    System.out.println(
                                                                                            "The description project must have at least 5 letters");
                                                                                    descriptionProject = this.util
                                                                                            .inputString(
                                                                                                    "Input description of the project",
                                                                                                    this.errorMessage);
                                                                                }

                                                                                gc.modifyProject(Id,idProject, op4,
                                                                                        descriptionProject, null);
                                                                                System.out.println(
                                                                                        "Changed the project description correctly");
                                                                                break;
                                                                            case "3":
                                                                                System.out
                                                                                        .println("- Input new state ");
                                                                                System.out.println(
                                                                                        "Input state of the project\n1. In progress\n2.Finalized");

                                                                                stateProjectOp = "";
                                                                                while (!stateProjectOp.equals("1")
                                                                                        && !stateProjectOp
                                                                                                .equals("2")) {

                                                                                    stateProjectOp = this.util
                                                                                            .inputString(
                                                                                                    "Selection -----> ",
                                                                                                    this.errorMessage);
                                                                                    switch (stateProjectOp) {
                                                                                        case "1":
                                                                                            stateProject = true;
                                                                                            break;
                                                                                        case "2":
                                                                                            stateProject = false;
                                                                                            break;
                                                                                        default:
                                                                                            System.out.println(
                                                                                                    "Invalid - Select the correct option");
                                                                                            break;
                                                                                    }
                                                                                }

                                                                                gc.modifyProject(Id,idProject, op4, null,
                                                                                        stateProject);
                                                                                System.out.println(
                                                                                        "Changed project status successfully");
                                                                                break;
                                                                            default:
                                                                                System.out.println(
                                                                                        "Invalid - Select the correct option");
                                                                                break;
                                                                        }

                                                                    } else {
                                                                        System.out.println("Input Id is invalid");
                                                                    }

                                                                } else {
                                                                    System.out.println(
                                                                            "This group doesn't have projects");
                                                                }

                                                                break;

                                                            case "3":
                                                                if (gc.showInformationProjects(Id).isEmpty() == false) {
                                                                    System.out.println(gc.showInformationProjects(Id));
                                                                    idProject = this.util.inputInt(
                                                                            "Enter project ID that you want to be deleted: ",
                                                                            this.errorMessage);

                                                                    if (gc.searchProject(idProject) != -1) {
                                                                        System.out.println(
                                                                                "Are you sure to delete the project?");
                                                                        System.out.println("1.Yes\n2.No");

                                                                        op4 = this.util.inputString("Selection -----> ",
                                                                                this.errorMessage);

                                                                        switch (op4) {
                                                                            case "1":
                                                                                if (gc.removeProject(Id, idProject)) {
                                                                                    System.out.println(
                                                                                            "removed successfully");
                                                                                }
                                                                                break;
                                                                            case "2":
                                                                                System.out.println(
                                                                                        "Error! - removed unsuccessfully");
                                                                                break;
                                                                            default:
                                                                                System.out.println("invalid option");
                                                                                break;
                                                                        }

                                                                    } else {
                                                                        System.out.println(
                                                                                "error this id does not exist");
                                                                    }
                                                                } else {
                                                                    System.out.println(
                                                                            "This group doesn't have projects");
                                                                }

                                                                break;
                                                            case "4":
                                                                if (gc.showInformationProjects(Id).isBlank() == false) {
                                                                    System.out.println(gc.showInformationProjects(Id));
                                                                } else {
                                                                    System.out.println(
                                                                            "This group doesn't have projects");
                                                                }
                                                                break;

                                                            default:
                                                                System.out.println("option not valid, try again");
                                                                break;
                                                        }
                                                    }

                                                    break;// break modificar proyectos

                                                case "3":

                                                    break;

                                            } // switch close op1

                                        } // close while op1
                                    } else {
                                        System.out.println("! Error - Invalid Id");
                                    } // close validacion id

                                } else {
                                    System.out.println("There is no groups created");
                                } // close validacion listGroups is empty
                                break; // break case 3 modificar grupos y proyectos

                            case "4":
                                String op5 = "";
                                gc.loadGroups();
                                if (gc.checkarray()) {
                                    System.out.println("There is no groups created");
                                    break;
                                }

                                System.out.println(gc.showIdGroups());

                                num = this.util.inputInt("Enter group ID that you want to be deleted: ",
                                        this.errorMessage);
                                while (gc.searchGroup(num) == -1) {
                                    System.out.println("Error this id does not exist");
                                    num = this.util.inputInt("Enter group ID that you want to be deleted: ",
                                            this.errorMessage);
                                }

                                System.out.println("Are you sure to delete the group?");
                                System.out.println("1.Yes\n2.No");
                                op5 = this.util.inputString("Selection -----> ",
                                        this.errorMessage);

                                switch (op5) {
                                    case "1":
                                        if (gc.deleteGroup(num)) {
                                            System.out.println("removed successfully");
                                        }

                                        break;
                                    case "2":
                                        System.out.println("Error! - removed unsuccessfully");

                                        break;
                                    default:
                                        System.out.println("invalid option");
                                        break;
                                }

                                break;
                            case "5":
                                break;

                            default:
                                System.out.println("invalid option");
                                break;
                        }
                    }
                    break;
                case "2":
                    System.out.println(this.logOut() ? "Logging out" : "Error. Try again");
                    break;
                default:
                    System.out.println("option not valid, try again");
                    break;
            }
        }
    }

    public void optionsSecretary() {

    }

    public void optionsGeneralsLogin() {
        String loginMessage = """
                ========================
                |\t1.Sing in accounts\t|
                |\t2.see accounts\t\t|
                |\t3.change password\t|
                |\t4.logout\t\t\t|
                ========================""";
        int decision, role;
        do {
            System.out.println(loginMessage);
            decision = this.util.inputInt("Selection -----> ", this.errorMessage);
            switch (decision) {
                case 1:
                    System.out.println(this.singInAccounts() ? "you successfully registered\n" +
                            "your user name is: " + loginController.getUserName() + "\n your password is: "
                            + loginController.getPassword() : "Error with your data");
                    break;
                case 2:
                    System.out.println(loginController.showAccounts());
                    break;
                case 3:
                    System.out.println(this.changePassword() ? "password change was successful" : "Error");
                    break;
                case 4:
                    System.out.println(this.logOut() ? "Logging out" : "Error. Try again");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }
        } while (decision != 4);
    }

    public boolean singInAccounts() {
        String names = "", lastNames = "", id = "";
        int roleIndex = 0;

        roleIndex = this.util.inputInt(
                "\tType your role in the university\n\t1.Student\n\t2.Professor\n\t3.Secretary\n\t-> ", errorMessage, 1,
                3);
        names = this.util.inputString("\tType their names: ", this.errorMessage);
        lastNames = this.util.inputString("\tType your last name: ", errorMessage);
        id = this.util.inputString("\tType your identification: ", errorMessage);

        return loginController.signin(names, lastNames, id, this.roles[roleIndex - 1]);
    }

    public boolean changePassword() {
        String oldPassword = "", newPassword = "";
        oldPassword = this.util.inputString("\tType old password: ", errorMessage);
        newPassword = this.util.inputString(
                "\t Your password will include 2 Uppercase characters, 2 Lowercase characters, 2 numbers. At least 6 characters.\n\tType new password: ",
                errorMessage);
        return this.loginController.changePassword(oldPassword, newPassword);
    }

    public boolean logOut() {
        return this.loginController.logout();
    }

    /**
     * This method shows the basic options for people who do not have special
     * permissions
     */
    public void optionsBasic() {

        String message = """
                ========================
                |\t1.change password\t|
                |\t2.Investigation groups\t|
                |\t3.logout\t\t\t|
                ========================""";

        String decision = "";
        while (!decision.equals("3")) {
            System.out.println(message);
            decision = this.util.inputString("Selection -----> ", this.errorMessage);
            switch (decision) {
                case "1":
                    System.out.println(this.changePassword() ? "password change was successful" : "Error");
                    break;
                case "2":
                    gc.loadGroups();
                    if (gc.showInformation().isEmpty() == false) {
                        System.out.println(gc.showInformation());
                    } else {
                        System.out.println("There is no groups created");
                    }
                    break;
                case "3":
                    System.out.println(this.logOut() ? "Logging out" : "Error. Try again");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }

        }
    }
}

package co.edu.uptc.Test;

import java.util.Scanner;

import co.edu.uptc.Controller.GroupController;

public class GroupTest {
     public static void main(String[] args) {
        GroupController gc = new GroupController();
        Scanner sc = new Scanner(System.in);
        String faculty = "", name = "", initial = "", email = "", project = "";
        String nameProject = "", descriptionProject = "", aux = "";
        int op = 0, num = 0, Id = 0, state = 0, m = 0, idProject = 0, cont = 0;
        boolean stateProject = false;

        while (op != 5) {
            System.out.println(
                    "\n----- Main Menu -----\n1.View groups\n2.Create a group\n3.Modify a group\n4.Delete a group\n5.Exit\n-----");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    gc.loadGroups();
                    System.out.println(gc.showInformation());
                    break;
                case 2:
                    int op6 = 0;
                    System.out.println("Input Id of the Investigation group");
                    Id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Input faculty of the Investigation group");
                    faculty = sc.nextLine();
                    System.out.println("Input name of the Investigation group");
                    name = sc.nextLine();
                    System.out.println("Input initial of the Investigation group");
                    initial = sc.nextLine();
                    System.out.println("Input email of the Investigation group");
                    email = sc.nextLine();
                    System.out.println("Input objective of the Investigation group");

                    cont++;
                    aux = cont + "." + sc.nextLine() + "\n";
                    while (op6 != 2) {
                        System.out.println("Do you want to add another objective?");
                        System.out.println("1.Yes\n2.No");
                        op6 = sc.nextInt();
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

                    break;
                case 3:
                    int op1 = 0, op2 = 0, op3 = 0, op4 = 0;
                    gc.loadGroups();
                    System.out.println(gc.showInformation());
                    System.out.println("Input the ID of the group you want to be modified");
                    Id = sc.nextInt();

                    // validacion correcta Id
                    if (gc.searchGroup(Id) != -1) {

                        while (op1 != 3) {
                            int search = 0;
                            System.out.println("\n------ Menu group: " + Id + " -----");
                            System.out.println("Choose one of the options to modify");
                            System.out.println("1. Modify information of the group");
                            System.out.println("2. Modify projects of the group");
                            System.out.println("3. Return\n-----");
                            op1 = sc.nextInt();

                            switch (op1) {
                                case 1: // modificar grupos
                                    System.out.println(
                                            "\n----- Group information -----\n1.Faculty\n2.Name\n3.Initial\n4.Email\n5.Goal\n6.Return");
                                    op2 = sc.nextInt();
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
                                                opObject = sc.nextInt();
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
                                                        int numeroObjetivo = sc.nextInt();
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
                                                        int objectiveNumberToDelete = sc.nextInt();
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
                                    System.out.println(
                                            "\n----- Projects -----\n1.Create new project\n2.Modify existing project\n3.Delete project\n4.Show projects\n5.Return\n-----");
                                    op3 = sc.nextInt();

                                    switch (op3) {
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

                                            gc.addProject(Id, gc.createProject(idProject, nameProject, stateProject,
                                                    descriptionProject));
                                            break;
                                        case 2:
                                            System.out.println("Input Id of the project");
                                            idProject = sc.nextInt();

                                            search = gc.searchProject(idProject);

                                            if (search != -1) {
                                                System.out.println("1. Name");
                                                System.out.println("2. Description");
                                                System.out.println("3. State");
                                                op4 = sc.nextInt();
                                                sc.nextLine();
                                                switch (op4) {
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

                                            } else {
                                                System.out.println("No existe");
                                            }

                                            break;
                                        case 3:
                                            System.out.println(gc.showInformationProjects());
                                            System.out.println("Enter project ID that you want to be deleted");
                                            idProject = sc.nextInt();

                                            if (gc.searchProject(idProject) != -1) {
                                                System.out.println("Are you sure to delete the project?");
                                                System.out.println("1.Yes\n2.No");
                                                op4 = sc.nextInt();
                                                switch (op4) {
                                                    case 1:
                                                        if (gc.removeProject(Id, idProject)) {
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

                                    break;// break modificar proyectos

                                case 3:

                                    break;

                            } // switch close op1

                        } // close while op1
                    } else {
                        System.out.println("! Error - Invalid Id");
                    } // close validacion id

                    break; // break case 3 modificar grupos y proyectos

                case 4:
                    int op5 = 0;
                    gc.loadGroups();
                    System.out.println("---------------------------------------------------------");
                    for (int i = 0; i < gc.getGroupList().size(); i++) {
                        System.out.println("Id " + gc.getGroupList().get(i).getId() + "  |  Grupo: "
                                + gc.getGroupList().get(i).getName());
                     System.out.println("---------------------------------------------------------");            

                    }
                   
                    System.out.println("Enter group ID that you want to be deleted");
                    num = sc.nextInt();
                    System.out.println("Are you sure to delete the group?");
                    System.out.println("1.Yes\n2.No");
                    op5 = sc.nextInt();
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

    }
    
}

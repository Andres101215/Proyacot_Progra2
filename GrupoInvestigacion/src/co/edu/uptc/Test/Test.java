package co.edu.uptc.Test;

import java.util.Scanner;

import co.edu.uptc.Controller.GroupController;

public class Test {
    public static void main(String[] args) {
        GroupController gc = new GroupController();
        Scanner sc = new Scanner(System.in);
        String faculty = "", name = "", initial = "", email = "", goal = "", project = "";
        String nameProject="", descriptionProject="";
        int op = 0, num = 0, Id = 0, state=0, m=0 ;
        while (op != 5) {
            System.out.println("1.Ver grupo\n2.Crear un grupo\n3.Modificar grupo\n4.Eliminar un grupo\n5.Salir");
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
                    int op2 = 0;
                    int search = 0;
                    gc.loadGroups();
                    System.out.println(gc.showInformation());
                    System.out.println("Ingrese ID del grupo que desea que sea modificado");
                    Id = sc.nextInt();
                    System.out.println("¿Que desea modificar?");
                    System.out.println("1.Faculty\n2.Name\n3.Initial\n4.Email\n5.Goal\n6.Salir");
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
                                gc.modifyGroup(Id - 1, op2, fac);
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
                                gc.modifyGroup(Id - 1, op2, nam);
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
                                gc.modifyGroup(Id - 1, op2, init);
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
                                gc.modifyGroup(Id - 1, op2, ema);
                            } else {
                                System.out.println("No existe el grupo");
                            }
                            break;
                            
                        default:
                        System.out.println("invalid option");
                            break;
                    }

                
                break;
                case 4:
                int op3=0;
                  gc.loadGroups();
                    System.out.println(gc.showInformation());
                    System.out.println("Enter group ID that you want to be deleted");
                    num = sc.nextInt();
                    System.out.println("Are you sure to delete the group?");
                    System.out.println("1.Yes\n2.No");
                    op3=sc.nextInt();
                    switch (op3) {
                        case 1:
                             gc.deleteGroup(num);
                             System.out.println("removed successfully");
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

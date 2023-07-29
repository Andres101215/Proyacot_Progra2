package co.edu.uptc.Test;

import java.util.Scanner;

import co.edu.uptc.Controller.GroupController;

public class Test {
    public static void main(String[] args) {
        GroupController gc = new GroupController();
        Scanner sc = new Scanner(System.in);
        String faculty = "", name = "", initial = "", email = "", goal = "", project = "";
        String nameProject="", descriptionProject="", aux="";
        int op = 0, num = 0, Id = 0, state=0, m=0, cont=0;
        while (op != 5) {
            System.out.println("1.Ver grupo\n2.Crear un grupo\n3.Modificar grupo\n4.Eliminar un grupo\n5.Salir");
            op = sc.nextInt();
            switch (op) {
                case 1:
                 gc.loadGroups();
                    System.out.println(gc.showInformation());
                break;   
                case 2:
                int op4=0; 
                cont=0;
                    System.out.println("Input Id of the Investigation group");
                    Id = sc.nextInt();
                    System.out.println("Input faculty of the Investigation group");
                    sc.nextLine();
                    faculty = sc.nextLine();                 
                    System.out.println("Input name of the Investigation group");
                    name = sc.nextLine();
                    System.out.println("Input initial of the Investigation group");
                    initial = sc.next();
                    System.out.println("Input email of the Investigation group");
                    sc.nextLine();
                    email = sc.nextLine();                   
                    System.out.println("Input objective of the Investigation group");
                    cont++;
                    aux = cont+")"+sc.nextLine()+"\n";
                    while (op4!=2) {
                          System.out.println("Do you want to add another objective?");
                          System.out.println("1.Yes\n2.No");
                    op4=sc.nextInt();
                    switch (op4) {
                        case 1:
                             System.out.println("Input objective of the Investigation group");
                             sc.nextLine();
                             cont++;
                             aux+=cont+")"+sc.nextLine()+"\n";
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
                    int op2 = 0;
                    int search = 0;
                    gc.loadGroups();
                    System.out.println(gc.showInformation());
                    System.out.println("Ingrese ID del grupo que desea que sea modificado");
                    Id = sc.nextInt();
                    System.out.println("¿Que desea modificar?");
                    System.out.println("1.Faculty\n2.Name\n3.Initial\n4.Email\n5.Goal\n6.Salir");
                    op2 = sc.nextInt();
                    search = gc.searchGroup(Id);
                    switch (op2) {
                        case 1:
                            String fac = "";
                            if (search != -1) {
                                System.out.println(gc.getListaGrupos().get(search).getFaculty());
                                System.out.println("Ingrese la nueva facultad");
                                sc.nextLine();
                                fac = sc.nextLine();
                                gc.modifyGroup(Id , op2, fac);
                            } else {
                                System.out.println("No existe el grupo");
                            }
                            break;
                        case 2:
                            String nam = "";
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
                        case 5:
                        int opObject=0;
                         String objective = "";
                            if (search != -1) {
                                System.out.println(gc.getListaGrupos().get(search).getGoal());
                                while (opObject!=4) {
                                    System.out.println("Elija alguna de las siguientes opciones: ");
                                System.out.println("1.Agregar objetivo\n2.Modificar objetivo\n4.salir");
                                opObject=sc.nextInt();
                                switch (opObject) {
                                    case 1:
                                     System.out.println("Input objective of the Investigation group");
                                    cont++;
                                    sc.nextLine();
                                    gc.getListaGrupos().get(search).setGoal(aux+=cont+")"+sc.nextLine()+"\n");
                                        
                                        break;
                                    case 2:
                                    System.out.println("Ingrese objetivos");
                                    String objectives=sc.nextLine();
                                    gc.getListaGrupos().get(search).setGoal(objectives);                                                
                                    break;    
                                
                                    default:
                                        break;
                                }
                                    
                                }
                                 
                               
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

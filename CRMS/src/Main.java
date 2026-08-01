import java.util.Scanner;

class Main
{
    public static void main(String[] args) throws Exception
    {
        //>>>Objects of Classes and Packages....
        Scanner sc=new Scanner(System.in);
        CRMngr crm=new CRMngr();
        CrimeMngr cm=new CrimeMngr();
        DIG dig=new DIG();
        DataBase db=new DataBase();
        Investigation inv=new Investigation();
        User user0=new User();

        boolean isMmenu=false;
        while (!isMmenu)
        {
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("|                    CRIME RECORD MANAGEMENT SYSTEM                    |");
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println();
            System.out.println("1. Criminal Record Manager");
            System.out.println("2. Directory of Police Officer");
            System.out.println("3. Crime Management");
            System.out.println("4. Pending Case Investing");
            System.out.println("5. View Profile");
            System.out.println("6. System Connection & Database Sync");
            System.out.println("7. Exit from System...");
            System.out.println("+----------------------------------------------------------------------+");
            System.out.print("Enter Your Choice: ");
            int ch_mm = sc.nextInt();

            switch (ch_mm)
            {
                case 1:
                {
                    crm.CrimeRecordManager();
                    break;
                }

                case 2:
                {
                    boolean isLoggedIn = dig.DIGLogin();
                    if (isLoggedIn)
                    {
                        dig.DirectoryOfPoliceOfficer();
                    }
                    break;
                }

                case 3:
                {
                    cm.CrimeManagement_Menu();
                    break;
                }

                case 4:
                {
                    inv.Investigation_Menu();
                    break;
                }

                case 5:
                {
                    user0.viewProfile();
                    break;
                }

                case 6:
                {
                    //Database package mathi connection and sync method call karishu as a thread....
                    // jo database sync nahi thay toh Exceotion throw karse and catch karse....
                    db.DBSync();
                    break;
                }

                case 7:
                {
                    isMmenu=true;
                    System.exit(0);
                    break;
                }

                default:
                {
                    System.err.println("[X] [ERROR] Invalid number format. Please enter a valid choice....");
                    break;
                }
            }
        }
    }
}
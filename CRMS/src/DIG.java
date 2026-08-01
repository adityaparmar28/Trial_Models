import java.util.Scanner;

class DIG
{
    Scanner sc=new Scanner(System.in);
    DIGQueries DIGQ=new DIGQueries();

    void DirectoryOfPoliceOfficer() throws Exception
    {
        boolean isDIGML=false;

        while (!isDIGML)
        {
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("|                    DIRECTORY OF POLICE OFFICER                       |");
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println();
            System.out.println("1. Add Police Officer Record");
            System.out.println("2. Search Police Officer");
            System.out.println("3. List of All Police Officers");
            System.out.println("4. Update Police Officer Record");
            System.out.println("5. Home Page");
            System.out.println("+----------------------------------------------------------------------+");
            System.out.print("Enter Your Choice: ");
            int dig_ch = sc.nextInt();

            switch (dig_ch)
            {
                case 1:
                {
                    // Add Police Officer Record
                    addPoliceOfficerRecord();
                    break;
                }

                case 2:
                {
                    // Search Police Officer
                    searchPoliceOfficer();
                    break;
                }

                case 3:
                {
                    // List of All Police Officers
                    AllPoliceOfficers();
                    break;
                }

                case 4:
                {
                    // Update Police Officer Record
                    UpdatePoliceOfficerRecord();
                    break;
                }

                case 5:
                {
                    // Home Page
                    isDIGML=true;
                    return;
                }

                default:
                {
                    System.err.println("[X] [ERROR] Invalid number format. Please enter a valid choice....");
                }
            }
        }
    }

    boolean DIGLogin()
    {
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("|                          DIG LOGIN PAGE                              |");
        System.out.println("+----------------------------------------------------------------------+");
        System.out.print("Enter your UserID:");
        String username = sc.nextLine();
        System.out.print("Enter your Password:");
        String password = sc.nextLine();

        //check credential if false then show forgot user credential option....
        //Login Functionality through Query....
        return true;
    }

    void addPoliceOfficerRecord() throws Exception
    {
        //Adding Police Officer Record through Query....
        DIGQ.AddPoliceRecord();
    }

    void searchPoliceOfficer() throws Exception
    {
        //Searching Police Officer through Query with same logic of crime and criminal....
        DIGQ.SearchPoliceOfficer();
    }

    void AllPoliceOfficers() throws Exception
    {
        //List of All Police Officers through Query....
        DIGQ.AllPoliceOfficers();

    }

    void UpdatePoliceOfficerRecord() throws Exception
    {
        //Updating Police Officer Record through Query....
        DIGQ.UpdatePoliceRecord();
    }
}

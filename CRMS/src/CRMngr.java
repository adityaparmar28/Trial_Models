import java.util.Scanner;

class CRMngr
{
    Scanner sc=new Scanner(System.in);
    CRMngrQueries CRQ=new CRMngrQueries();

    void CrimeRecordManager() throws Exception
    {
        boolean isCRML=false;

        while (!isCRML)
        {
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("|                          CRIME RECORD MANAGER                        |");
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println();
            System.out.println("1. Add Criminal Record");
            System.out.println("2. Search Criminal");
            System.out.println("3. List of All Criminals");
            System.out.println("4. Update Criminal Record");
            System.out.println("5. Home Page");
            System.out.println("+----------------------------------------------------------------------+");
            System.out.print("Enter Your Choice: ");
            int crm_ch = sc.nextInt();

            switch (crm_ch)
            {
                case 1:
                {
                    // Add Criminal Record
                    addCriminalRecord();
                    break;
                }

                case 2:
                {
                    // Search Criminal
                    searchCriminal();
                    break;
                }

                case 3:
                {
                    // List of All Criminals
                    AllCriminal();
                    break;
                }

                case 4:
                {
                    // Update Criminal Record
                    UpdateCriminalRecord();
                    break;
                }

                case 5:
                {
                    // Home Page
                    isCRML=true;
                    return;
                }

                default:
                {
                    System.err.println("[X] [ERROR] Invalid number format. Please enter a valid choice....");
                }
            }
        }
    }

    void addCriminalRecord() throws Exception
    {
        //first login as adg or police officer then process of add criminal record
        CRQ.AddCriminalRQuery();

    }

    void searchCriminal() throws Exception
    {
        // Implementation for searching criminal
        //throgh logic and query...
        CRQ.SearchCriminalRecord();
    }

    void AllCriminal() throws Exception
    {
        //all criminals and recently added criminals synced data through Query....
        CRQ.AllCriminalRecord();
    }

    void UpdateCriminalRecord() throws Exception
    {
        //first login as adg then process of update criminal record
        CRQ.UpdateCriminalRQuery();
    }

}

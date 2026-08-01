import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

class IAQueries
{
    Scanner sc=new Scanner(System.in);
    DataBase db=new DataBase();
    OODataQueries OODQ=new OODataQueries();

    void PendingCases() throws Exception
    {
        //Uses of Data Structures to store pending cases in Queue or Circular Queue....

        /*
        String pendingCasesQuery = "SELECT * FROM criminal_details WHERE Case_Status = 'Pending'";
        PreparedStatement pendingCasesStmt = db.getConnection().prepareStatement(pendingCasesQuery);
        ResultSet rs = pendingCasesStmt.executeQuery();

        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("|                               PENDING CASES LIST                                      |");
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.printf("| %-10s | %-20s | %-5s | %-10s | %-10s | %-15s | %-12s | %-20s | %-12s |\n",
                          "Criminal ID", "Name", "Age", "Gender", "Case ID", "Crime Type");

         */
    }

    void UpdateInvesting() throws Exception
    {
        System.out.print("Enter Invasting Case ID: ");
        int caseID = sc.nextInt();

        //>>>Calling Investing CaseID found in Case_details....by case_status investing through Query....
        //if found then process ahead otherwise show error message that case not found in database....
        if(!isCaseFound(caseID))
        {
            System.out.println("[ERROR] Case ID not found in database....");
            return;
        }


        System.out.println("Which data do you want to Update in Investigation???");
        String UpColummName=sc.next();

        //>>>if UpData column name found in case_details table then process ahead otherwise show error message that column not found in database....

        String TableName="case_details";

        System.out.print("Enter Updating "+UpColummName+": ");

        //input variable static public banana padega..!!
        Object UpdatingValue=OODQ.SQLDType2JDType(UpColummName,TableName);//>>>Done'....

        String UpdateIn="update ? set ?=? where CaseID=?";//update case_details set <colummName>=data where CaseID=id;
        PreparedStatement QUIn=db.getConnection().prepareStatement(UpdateIn);
        QUIn.setString(1,TableName);
        QUIn.setString(2,UpColummName);
        QUIn.setObject(3, UpdatingValue);
        QUIn.setInt(4,caseID);

    }

    boolean isCaseFound(int cid) throws Exception
    {
        String FCID="select is_CaseID(?)";
        PreparedStatement QFCID=db.getConnection().prepareStatement(FCID);
        QFCID.setInt(1, cid);
        ResultSet FCIDrs = QFCID.executeQuery();
        FCIDrs.next();
        return FCIDrs.getBoolean(1);
    }
}

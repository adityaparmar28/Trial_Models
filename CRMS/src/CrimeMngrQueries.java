import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

class CrimeMngrQueries
{
    Scanner sc=new Scanner(System.in);
    DataBase db=new DataBase();

    void FileFIRQ() throws Exception
    {
        System.out.println("-----| FILE FIR REPORT |-----");
        System.out.println("Enter the following details to file a FIR report....");
        System.out.println("---------------------------------------------------------");
        System.out.println("Enter Case ID: ");
        int CaseID=sc.nextInt();

        System.out.println("Enter Case Type: ");
        String CType=sc.next();

        System.out.println("Enter Crime Location: ");
        String CLocation=sc.next();

        System.out.println("Is there Involved Weapon at CrimeScene???");
        String CWIn=sc.next();
        String CWeapon=null;

        if(CWIn.equalsIgnoreCase("Y"))
        {
            System.out.println("Enter Crime Weapon: ");
            CWeapon=sc.next();
        }

        System.out.println("Enter Suspect Name: ");
        String SuspectName=sc.next();

        System.out.println("Enter Victim Name: ");
        String VictimName=sc.next();

        System.out.println("Enter Crime Description: ");
        String CDesc=sc.next();

        System.out.println("Enter Case Name: ");
        String CName=sc.next();

        String FileFIR="insert into case_details(CaseID,Case_Type,Crime_Location,Crime_Weapon,SuspectName,Victim_Name,Crime_Description,CaseName) values(?,?,?,?,?,?,?,?)";
        PreparedStatement QFIR=db.getConnection().prepareStatement(FileFIR);
        QFIR.setInt(1,CaseID);
        QFIR.setString(2,CType);
        QFIR.setString(3,CLocation);
        QFIR.setString(4,CWeapon);
        QFIR.setString(5,SuspectName);
        QFIR.setString(6,VictimName);
        QFIR.setString(7,CDesc);
        QFIR.setString(8,CName);

        int update=QFIR.executeUpdate();

        if(update>0)
        {
            System.out.println("[UPDATED] FIR report filed successfully...");
        }
        else
        {
            System.err.println("[X] [ERROR] Failed to file FIR report. Please try again...");
        }
    }

    void SearchCRQ() throws Exception
    {

    }

    void AllCasesQ() throws Exception
    {
        String AllCase="Select * from case_details";
        PreparedStatement QAC=db.getConnection().prepareStatement(AllCase);
        ResultSet ACrs=QAC.executeQuery();

        while (ACrs.next())
        {
            System.out.println("Case ID: "+ACrs.getInt("CaseID"));
            System.out.println("Case Type: "+ACrs.getString("Case_Type"));
            System.out.println("Crime Location: "+ACrs.getString("Crime_Location"));
            //System.out.println("Crime Weapon: "+ACrs.getString("Crime_Weapon"));
            //System.out.println("Suspect Name: "+ACrs.getString("SuspectName"));
            //System.out.println("Victim Name: "+ACrs.getString("Victim_Name"));
            //System.out.println("Crime Description: "+ACrs.getString("Crime_Description"));
            System.out.println("Case Name: "+ACrs.getString("CaseName"));
            System.out.println("---------------------------------------------");
        }
    }

    void UpdateCaseData() throws Exception
    {
        System.out.print("Enter CASE ID to update: ");
        int CId= sc.nextInt();

        //validation proocedure call....

        //>>>
        System.out.println("Which Data do you want to Update???");
        System.out.println("1. Criminal ID");
        System.out.println("2. Officer ID");
        System.out.println("3. Case Type");
        System.out.println("4. Crime Weapon");
        System.out.println("5. Suspect Name");
        System.out.println("6. Victim Name");
        System.out.println("7. Crime Description");
        System.out.println("8. Case Status");
        System.out.print("Enter Your Choice: ");
        int UpCD_ch= sc.nextInt();

         switch (UpCD_ch)
         {
             case 1:
             {
                 //CriminalID
                 UpdateCriID(CId);
                 break;
             }

             case 2:
             {
                 //OfficerID
                 UpdateOffID(CId);
                 break;
             }

             case 3:
             {
                 //Case Type
                 UpdateCType(CId);
                 break;
             }

             case 4:
             {
                 //Crime Weapon
                 UpdateWeapon(CId);
                 break;
             }

             case 5:
             {
                 //Suspect Name
                 UpdateSuspect(CId);
                 break;
             }

             case 6:
             {
                 //Victim Name
                 UpdateVictim(CId);
                 break;
             }

             case 7:
             {
                 //Crime Description
                 UpdateDesc(CId);
                 break;
             }

             case 8:
             {
                 //Case Status
                 UpdateStatus(CId);
                 break;
             }

             default:
             {
                 System.err.println("[X] [ERROR] Invalid Choice. Please try again...");
                 break;
             }
         }
    }

    void UpdateCriID(int c_id) throws Exception
    {
        System.out.print("Enter Updating Criminal ID:");
        int CriID=sc.nextInt();

        String UpdateCriID="update case_details set CriminalID=? where CaseID=?";
        PreparedStatement QUCriID=db.getConnection().prepareStatement(UpdateCriID);
        QUCriID.setInt(1,CriID);
        QUCriID.setInt(2,c_id);
        int UCID=QUCriID.executeUpdate();

        if (UCID>0)
        {
            System.out.println("[UPDATED] Criminal ID updated successfully...");
        }
        else
        {
            System.err.println("[X] [ERROR] Failed to update Criminal ID. Please try again...");
        }
    }

    void UpdateOffID(int c_id) throws Exception
    {
        System.out.print("Enter Updating Officer ID:");
        String OffID=sc.next();

        //If updating officer have not investigating any case....if found that updating officer is investing other case than show free officers list then ask for oficerID

        String UpdateOffID="update case_details set OfficerID=? where CaseID=?";
        PreparedStatement QUOffID=db.getConnection().prepareStatement(UpdateOffID);
        QUOffID.setString(1,OffID);
        QUOffID.setInt(2,c_id);
        int UOID=QUOffID.executeUpdate();

        if (UOID>0)
        {
            System.out.println("[UPDATED] Officer ID updated successfully...");
        }
        else
        {
            System.err.println("[X] [ERROR] Failed to update Officer ID. Please try again...");
        }
    }

    void UpdateCType(int c_id) throws Exception
    {
        System.out.print("Enter Updating Case Type:");
        String CType=sc.next();

        String UpdateCType="update case_details set Case_Type=? where CaseID=?";
        PreparedStatement QUCType=db.getConnection().prepareStatement(UpdateCType);
        QUCType.setString(1,CType);
        QUCType.setInt(2,c_id);
        int UCT=QUCType.executeUpdate();

        if (UCT>0)
        {
            System.out.println("[UPDATED] Case Type updated successfully...");
        }
        else
        {
            System.err.println("[X] [ERROR] Failed to update Case Type. Please try again...");
        }
    }

    void UpdateWeapon(int c_id) throws Exception
    {
        System.out.print("Enter Updating Crime Weapon:");
        String CWeapon=sc.next();

        String UpdateCWeapon="update case_details set Crime_Weapon=? where CaseID=?";
        PreparedStatement QUCWeapon=db.getConnection().prepareStatement(UpdateCWeapon);
        QUCWeapon.setString(1,CWeapon);
        QUCWeapon.setInt(2,c_id);
        int UCW=QUCWeapon.executeUpdate();

        if (UCW>0)
        {
            System.out.println("[UPDATED] Crime Weapon updated successfully...");
        }
        else
        {
            System.err.println("[X] [ERROR] Failed to update Crime Weapon. Please try again...");
        }
    }

    void UpdateSuspect(int c_id) throws Exception
    {
        System.out.print("Enter Updating Suspect Name:");
        String SName=sc.next();

        String UpdateSuspect="update case_details set SuspectName=? where CaseID=?";
        PreparedStatement QUSuspect=db.getConnection().prepareStatement(UpdateSuspect);
        QUSuspect.setString(1,SName);
        QUSuspect.setInt(2,c_id);
        int USN=QUSuspect.executeUpdate();

        if (USN>0)
        {
            System.out.println("[UPDATED] Suspect Name updated successfully...");
        }
        else
        {
            System.err.println("[X] [ERROR] Failed to update Suspect Name. Please try again...");
        }
    }

    void UpdateVictim(int c_id) throws Exception
    {
        System.out.print("Enter Updating Victim Name:");
        String VName=sc.next();

        String UpdateVictim="update case_details set Victim_Name=? where CaseID=?";
        PreparedStatement QUVictim=db.getConnection().prepareStatement(UpdateVictim);
        QUVictim.setString(1,VName);
        QUVictim.setInt(2,c_id);
        int UVN=QUVictim.executeUpdate();

        if (UVN>0)
        {
            System.out.println("[UPDATED] Victim Name updated successfully...");
        }
        else
        {
            System.err.println("[X] [ERROR] Failed to update Victim Name. Please try again...");
        }
    }

    void UpdateDesc(int c_id) throws Exception
    {
        System.out.print("Enter Updating Case Description:");
        String CDesc=sc.next();

        String UpdateCDesc="update case_details set Case_Description=? where CaseID=?";
        PreparedStatement QUCDesc=db.getConnection().prepareStatement(UpdateCDesc);
        QUCDesc.setString(1,CDesc);
        QUCDesc.setInt(2,c_id);
        int UCD=QUCDesc.executeUpdate();

        if (UCD>0)
        {
            System.out.println("[UPDATED] Case Description updated successfully...");
        }
        else
        {
            System.err.println("[X] [ERROR] Failed to update Case Description. Please try again...");
        }
    }

    void UpdateStatus(int c_id) throws Exception
    {
        System.out.print("Enter Updating Case Status:");
        String CStatus=sc.next();

        String UpdateCStatus="update case_details set Case_Status=? where CaseID=?";
        PreparedStatement QUCStatus=db.getConnection().prepareStatement(UpdateCStatus);
        QUCStatus.setString(1,CStatus);
        QUCStatus.setInt(2,c_id);
        int UCS=QUCStatus.executeUpdate();

        if (UCS>0)
        {
            System.out.println("[UPDATED] Case Status updated successfully...");
        }
        else
        {
            System.err.println("[X] [ERROR] Failed to update Case Status. Please try again...");
        }
    }

    void CrimeRatio() throws Exception
    {
        String CrimeRatioQuery="select Case_Type, count(*) as TotalCases from case_details group by Case_Type";
        PreparedStatement QCR=db.getConnection().prepareStatement(CrimeRatioQuery);
        ResultSet CRrs=QCR.executeQuery();

        System.out.println("Crime Ratio Report:");
        System.out.println("-------------------");
        while (CRrs.next())
        {
            System.out.println("Case Type: "+CRrs.getString("Case_Type")+" | Total Cases: "+CRrs.getInt("TotalCases"));
        }
    }
}

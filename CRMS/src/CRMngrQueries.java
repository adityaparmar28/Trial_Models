import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

class CRMngrQueries
{
    DataBase db = new DataBase();
    Scanner sc = new Scanner(System.in);

    void AddCriminalRQuery() throws Exception
    {
        String addCRQ = "insert into criminal_details(Criminal_ID,Name,Age,Gender,CaseID,Crime_Type,Crime_Date,InvestingOfficerID,Case_Status) values(?,?,?,?,?,?,?,?,?)";

        System.out.print("Enter Criminal ID: ");
        int C_Id = sc.nextInt();

        System.out.print("Enter Criminal Name: ");
        String C_Name = sc.next();

        System.out.print("Enter Criminal Age: ");
        int C_Age = sc.nextInt();

        System.out.print("Enter Criminal Gender: ");
        String C_Gender = sc.next();

        System.out.print("Enter Case ID: ");
        int C_CaseID = sc.nextInt();

        System.out.print("Enter Crime Type: ");
        String C_CrimeType = sc.next();

        System.out.print("Enter Crime Date: ");
        String C_CrimeDate = sc.next();

        System.out.print("Enter Investigating Officer ID: ");
        int C_IOffID = sc.nextInt();

        System.out.print("Enter Case Status: ");
        String C_CaseStatus = sc.next();

        PreparedStatement addCR = db.getConnection().prepareStatement(addCRQ);
        addCR.setInt(1, C_Id);
        addCR.setString(2, C_Name);
        addCR.setInt(3, C_Age);
        addCR.setString(4, C_Gender);
        addCR.setInt(5, C_CaseID);
        addCR.setString(6, C_CrimeType);
        addCR.setDate(7, java.sql.Date.valueOf(C_CrimeDate));
        addCR.setInt(8, C_IOffID);
        addCR.setString(9, C_CaseStatus);

        int update = addCR.executeUpdate();

        if (update > 0)
        {
            System.out.println("[UPDATED] Criminal details added successfully...");
        }
        else
        {
            System.out.println("[FAILED] Criminal Data Couldn't be Added...");
        }
    }

    void UpdateCriminalRQuery() throws Exception
    {
        System.out.print("Enter Case ID to Update: ");
        int C_CaseID = sc.nextInt();

        System.out.println("Which Details do you want to Update?");
        System.out.println("1. Investingating Officer ID.");
        System.out.println("2. Case Status.");
        System.out.println("3. Criminal Status.");
        System.out.println("4. Bail Date.");
        System.out.println("5. Release Date.");
        System.out.println();
        System.out.print("Enter Your Choice: ");
        int updateCri_ch = sc.nextInt();

        switch (updateCri_ch)
        {
            case 1:
            {
                System.out.print("Enter New Investigating Officer ID: ");
                String C_IOffID = sc.next();
                UpdateIOffID(C_IOffID, C_CaseID);

                break;
            }

            case 2:
            {
                System.out.print("Enter Updating Case Status: ");
                String C_CaseStatus = sc.next();
                UpdateCaseStatus(C_CaseStatus, C_CaseID);

                break;
            }

            case 3:
            {
                System.out.print("Enter Updating Criminal Status: ");
                String C_CriminalStatus = sc.next();
                UpdateCriminalStatus(C_CriminalStatus, C_CaseID);

                break;
            }

            case 4:
            {
                System.out.print("Enter Updating Bail Date: ");
                String C_BailDate = sc.next();
                SetUpdateBail(C_BailDate, C_CaseID);

                break;
            }

            case 5:
            {
                System.out.print("Enter Updating Release Date: ");
                String C_ReleaseDate = sc.next();
                UpdateReleaseDate(C_ReleaseDate, C_CaseID);

                break;
            }

            default:
            {
                System.out.println("[X] [ERROR] Invalid number format. Please enter a valid choice....");
            }
        }
    }

    void UpdateIOffID(String oid, int CaseID) throws Exception
    {
        String UCri_IOID = "update criminal_details set InvestingOfficerID=? where CaseID=?";
        PreparedStatement QUCrIOID = db.getConnection().prepareStatement(UCri_IOID);
        QUCrIOID.setString(1, oid);
        QUCrIOID.setInt(2, CaseID);
        int updateCIO = QUCrIOID.executeUpdate();

        String UOf_CaseID = "update officer_details set CaseID=? where InvestingOfficerID=?";
        PreparedStatement QUOfCID = db.getConnection().prepareStatement(UOf_CaseID);
        QUOfCID.setInt(1, CaseID);
        QUOfCID.setString(2, oid);
        int updateIO_cid = QUOfCID.executeUpdate();

        if (updateCIO > 0 && updateIO_cid > 0)
        {
            System.out.println("[UPDATED] Criminal details updated successfully...");
        }
        else
        {
            System.out.println("[FAILED] Criminal Data Couldn't be Updated...");
        }
    }

    void UpdateCaseStatus(String Status, int CaseID) throws Exception
    {
        String UCase_Status = "update criminal_details set Case_Status=? where CaseID=?";
        PreparedStatement QUCS = db.getConnection().prepareStatement(UCase_Status);
        QUCS.setString(1, Status);
        QUCS.setInt(2, CaseID);
        int updateCS = QUCS.executeUpdate();

        String UCD_CaseStatus = "update case_details set Case_Status=? where CaseID=?";
        PreparedStatement QUCD_CS = db.getConnection().prepareStatement(UCD_CaseStatus);
        QUCD_CS.setString(1, Status);
        QUCD_CS.setInt(2, CaseID);
        int updateCD_CS = QUCD_CS.executeUpdate();

        if (updateCS > 0 && updateCD_CS > 0)
        {
            System.out.println("[UPDATED] Case status updated successfully...");
        }
        else
        {
            System.out.println("[FAILED] Case status couldn't be updated...");
        }
    }

    void UpdateCriminalStatus(String Status, int CaseID) throws Exception
    {
        String UCri_Status = "update criminal_details set Criminal_Status=? where CaseID=?";
        PreparedStatement QUCS = db.getConnection().prepareStatement(UCri_Status);
        QUCS.setString(1, Status);
        QUCS.setInt(2, CaseID);
        int updateCS = QUCS.executeUpdate();

        if (updateCS > 0)
        {
            System.out.println("[UPDATED] Criminal status updated successfully...");
        }
        else
        {
            System.out.println("[FAILED] Criminal status couldn't be updated...");
        }
    }

    void SetUpdateBail(String BailDate, int CaseID) throws Exception
    {
        String UBail_Date = "update criminal_details set Bail_Date=? where CaseID=?";
        PreparedStatement QUBD = db.getConnection().prepareStatement(UBail_Date);
        QUBD.setString(1, BailDate);
        QUBD.setInt(2, CaseID);
        int updateBD = QUBD.executeUpdate();

        if (updateBD > 0)
        {
            System.out.println("[UPDATED] Bail date updated successfully...");
        }
        else
        {
            System.out.println("[FAILED] Bail date couldn't be updated...");
        }
    }

    void UpdateReleaseDate(String ReleaseDate, int CaseID) throws Exception
    {
        String URelease_Date = "update criminal_details set Release_Date=? where CaseID=?";
        PreparedStatement QURD = db.getConnection().prepareStatement(URelease_Date);
        QURD.setString(1, ReleaseDate);
        QURD.setInt(2, CaseID);
        int updateRD = QURD.executeUpdate();

        if (updateRD > 0)
        {
            System.out.println("[UPDATED] Release date updated successfully...");
        }
        else
        {
            System.out.println("[FAILED] Release date couldn't be updated...");
        }
    }

    void AllCriminalRecord() throws Exception
    {
        String ACriRec = "select Criminal_ID,Name,Age,Gender,Case_ID,Crime_Type,Crime_Date,Punishment_Type,Criminal_Status from criminal_details";
        PreparedStatement QACRec = db.getConnection().prepareStatement(ACriRec);
        ResultSet rsACRec = QACRec.executeQuery();



        System.out.println("+-------------+----------------------+-----+---------+------------+----------------------+------------+--------------+------------+");
        System.out.printf("| %-4s | %-20s | %-3s | %-7s | %-10s | %-20s | %-10s | %-12s | %-10s |\n", "Criminal ID", "Name", "Age", "Gender","Case ID", "Crime", "Crime Date","Judgement", "Status");
        System.out.println("+-------------+----------------------+-----+---------+------------+----------------------+------------+--------------+------------+");

        while (rsACRec.next())
        {
            System.out.println(String.format("| %-11s | %-20s | %-3s | %-7s | %-10s | %-20s | %-10s | %-12s | %-10s |",
                rsACRec.getInt("Criminal_ID"),
                rsACRec.getString("Name"),
                rsACRec.getInt("Age"),
                rsACRec.getString("Gender"),
                rsACRec.getInt("Case_ID"),
                rsACRec.getString("Crime_Type"),
                rsACRec.getDate("Crime_Date"),
                rsACRec.getString("Punishment_Type"),
                rsACRec.getString("Criminal_Status")));
        }

        System.out.println("Would you like to download Criminal Data as Text File???");
        char is_txt = sc.next().charAt(0);

        if(is_txt=='Y' || is_txt=='y')
        {
            //Text file creation method here....
        }
        else
        {
            System.out.println("[CANCELED] Criminal Data Download Cancelled...");
            return;
        }
    }

    void SearchCriminalRecord() throws Exception
    {
        //logic pending.....
    }
}
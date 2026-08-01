import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class DIGQueries
{
    DataBase db = new DataBase();
    Scanner sc = new Scanner(System.in);

    void AddPoliceRecord() throws Exception
    {
        // Implementation for adding police record
        System.out.print("Enter Officer ID: ");
        int officerID = sc.nextInt();

        System.out.print("Enter Officer Name: ");
        String officerName = sc.next();

        System.out.print("Enter Officer DOB: ");
        String officerDOB = sc.next();

        System.out.print("Enter Officer Rank:");
        String officerRank = sc.next();

        System.out.print("Enter Officer Police Station ID: ");
        String PSID= sc.next();

        System.out.print("Enter Officer Joining Date: ");
        String Joining=sc.next();

        System.out.print("Enter Officer Status: ");
        String officerStatus = sc.next();

        String AddOfficer="insert into officer_details(Officer_ID,Name,DOB,Rank,StationID,JoiningDate,OfficerStatus) values(?,?,?,?,?,?,?)";
        PreparedStatement QAddOff=db.getConnection().prepareStatement(AddOfficer);
        QAddOff.setInt(1,officerID);
        QAddOff.setString(2,officerName);
        QAddOff.setDate(3,java.sql.Date.valueOf(officerDOB));
        QAddOff.setString(4,officerRank);
        QAddOff.setString(5,PSID);
        QAddOff.setDate(6,java.sql.Date.valueOf(Joining));
        QAddOff.setString(7,officerStatus);
        int AOff=QAddOff.executeUpdate();

        if(AOff>0)
        {
            System.out.println("[UPDATED] Officer added successfully....");
        }
        else
        {
            System.out.println("[FAILED] Failed to add officer....");
        }

    }

    void SearchPoliceOfficer() throws Exception
    {
        // Implementation for searching police officer...
        //SAME LOGIC AS CRIMINAL SEARCHING....
    }

    void AllPoliceOfficers() throws Exception
    {
        // Implementation for displaying all police officers
        String AllOfficers="select * from officer_details";
        Statement st=db.getConnection().createStatement();
        ResultSet rs=st.executeQuery(AllOfficers);

        System.out.println("+------------+----------------------+---------------+---------+------------+----------------------+------------+--------------+-------------+-------------+");
        System.out.printf("| %-4s | %-20s | %-13s | %-7s | %-10s | %-10s | %-20s | %-10s | %-12s | %-10s |\n", "Officer ID", "Name", "DOB","Age", "Rank","Station ID", "Joining Date", "Status", "Assigned Case", "Case Status");
        System.out.println("+------------+----------------------+---------------+---------+------------+----------------------+------------+--------------+-------------+-------------+");

        while(rs.next())
        {
            System.out.println(String.format("| %-10s | %-20s | %-13s | %-7s | %-10s | %-10s | %-20s | %-10s | %-12s | %-10s |",
                    rs.getInt("Officer_ID"),
                    rs.getString("Name"),
                    rs.getDate("DOB"),
                    rs.getInt("Age"),
                    rs.getString("Rank"),
                    rs.getString("StationID"),
                    rs.getDate("JoiningDate"),
                    rs.getString("OfficerStatus"),
                    rs.getString("AssignedCase"),
                    rs.getString("CaseStatus")));
        }

        System.out.println("Would you like to Download Police Officer Data as Text file???");
        char is_text=sc.next().charAt(0);

        if (is_text=='Y' || is_text=='y')
        {
            //Text file creation method here....
        }
        else
        {
            System.out.println("[CANCELLED] Police Officer Data Download Cancelled...");
            return;
        }

    }

    void UpdatePoliceRecord() throws Exception
    {
        // Implementation for updating police record
        System.out.print("Enter Officer ID you want to Update: ");
        int officerID = sc.nextInt();

        if(!isFound(officerID))
        {
            System.err.println("[NOT FOUND] Officer with ID " + officerID + " not found.");
            return;
        }

        System.out.println("Which Details do you want to Update?");
        System.out.println("1. Rank");
        System.out.println("2. Station ID");
        System.out.println("3. Officer Status");
        System.out.println("4. Assigned Case");
        System.out.println("5. Case Status");
        System.out.print("Enter your Choice: ");
        int UpdateOff_ch=sc.nextInt();

        switch (UpdateOff_ch)
        {
            case 1:
            {
                // Update Rank
                UpdateRank(officerID);
                break;
            }
            case 2:
            {
                // Update Station ID
                UpdateStationID(officerID);
                break;
            }
            case 3:
            {
                // Update Officer Status
                UpdateOfficerStatus(officerID);
                break;
            }
            case 4:
            {
                // Update Assigned Case
                UpdateAssignedCase(officerID);
                break;
            }
            case 5:
            {
                // Update Case Status
                UpdateCaseStatus(officerID);
                break;
            }
            default:
            {
                System.err.println("[INVALID] Invalid choice...");
                return;
            }
        }
    }

    boolean isFound(int OID) throws Exception
    {
        String FOID="select is_OfficerD(?)";
        PreparedStatement QFOID=db.getConnection().prepareStatement(FOID);
        QFOID.setInt(1,OID);
        ResultSet found=QFOID.executeQuery();
        found.next();
        return found.getBoolean(1);
    }

    void UpdateRank(int OID) throws Exception
    {
        System.out.print("Enter Updating Rank: ");
        String URank=sc.next();

        String Update_Rank="update officer_details set Rank=? where Officer_ID=?";
        PreparedStatement QRank=db.getConnection().prepareStatement(Update_Rank);
        QRank.setString(1,URank);
        QRank.setInt(2,OID);
        int URankRes=QRank.executeUpdate();

        if(URankRes>0)
        {
            System.out.println("[UPDATED] Officer Rank Updated Successfully....");
        }
        else
        {
            System.err.println("[FAILED] Failed to Update Officer Rank....");
        }
    }

    void UpdateStationID(int OID) throws Exception
    {
        System.out.print("Enter Updating Station ID: ");
        String UStationID = sc.next();

        String Update_StationID = "update officer_details set StationID=? where Officer_ID=?";
        PreparedStatement QUSID = db.getConnection().prepareStatement(Update_StationID);
        QUSID.setString(1, UStationID);
        QUSID.setInt(2, OID);
        int USIDRes = QUSID.executeUpdate();

        if (USIDRes > 0)
        {
            System.out.println("[UPDATED] Officer Station ID Updated Successfully....");
        }
        else
        {
            System.err.println("[FAILED] Failed to Update Officer Station ID....");
        }
    }

    void UpdateOfficerStatus(int OID) throws Exception
    {
        System.out.print("Enter Updating Officer Status: ");
        String UOfficerStatus = sc.next();

        String Update_OfficerStatus = "update officer_details set OfficerStatus=? where Officer_ID=?";
        PreparedStatement QUOffS = db.getConnection().prepareStatement(Update_OfficerStatus);
        QUOffS.setString(1, UOfficerStatus);
        QUOffS.setInt(2, OID);
        int UOffSRes = QUOffS.executeUpdate();

        if (UOffSRes > 0)
        {
            System.out.println("[UPDATED] Officer Status Updated Successfully....");
        }
        else
        {
            System.err.println("[FAILED] Failed to Update Officer Status....");
        }
    }

    void UpdateCaseStatus(int OID) throws Exception
    {
        System.out.print("Enter Updating Case Status: ");
        String UCaseStatus = sc.next();

        //>>>Update Case Status in Officer Details table....
        String Update_CaseStatus = "update officer_details set CaseStatus=? where Officer_ID=?";
        PreparedStatement QUCS = db.getConnection().prepareStatement(Update_CaseStatus);
        QUCS.setString(1, UCaseStatus);
        QUCS.setInt(2, OID);
        int UCStatusRes = QUCS.executeUpdate();

        //>>>Automatically Update Case Status in Case Details Table....
        String UCaseD_CaseStatus="update case_details set Case_Status=? where OfficerID=?";
        PreparedStatement QUCaD_CaS=db.getConnection().prepareStatement(UCaseD_CaseStatus);
        QUCaD_CaS.setString(1,UCaseStatus);
        QUCaD_CaS.setInt(2,OID);
        int UCD_CSRes=QUCaD_CaS.executeUpdate();

        //>>>Automatically Update Case Status in Criminal Details table....
        String UCriD_CaSta="update criminal_details set Case_Status=? where InvestingOfficerID=?";
        PreparedStatement QUCriD_CaS=db.getConnection().prepareStatement(UCriD_CaSta);
        QUCriD_CaS.setString(1,UCaseStatus);
        QUCriD_CaS.setInt(2,OID);
        int UCriD_CSRes=QUCriD_CaS.executeUpdate();

        if (UCStatusRes > 0 && UCD_CSRes>0 && UCriD_CSRes>0)
        {
            System.out.println("[UPDATED] Case Status Updated Successfully....");
        }
        else
        {
            System.err.println("[FAILED] Failed to Update Case Status....");
        }
    }

    void UpdateAssignedCase(int OID) throws Exception
    {
        System.out.print("Enter Updating Assigned Case ID: ");
        int UAssignedCase = sc.nextInt();

        //>>>Updating Assigned Case to Officer....
        String Update_AssignedCase = "update officer_details set AssignedCase=? where Officer_ID=?";
        PreparedStatement QUAC = db.getConnection().prepareStatement(Update_AssignedCase);
        QUAC.setInt(1, UAssignedCase);
        QUAC.setInt(2, OID);
        int UACRes = QUAC.executeUpdate();

        //>>>Automatically Update Officer in Case_Details table....
        String UOffId_CaD="update case_details set OfficerID=? where CaseID=?";
        PreparedStatement QOidCaD=db.getConnection().prepareStatement(UOffId_CaD);
        QOidCaD.setInt(1,OID);
        QOidCaD.setInt(2,UAssignedCase);
        int UOIDRes=QOidCaD.executeUpdate();

        //>>>Automatically Update Officer in Criminal_details table....

        if (UACRes > 0 && UOIDRes>0)
        {
            System.out.println("[UPDATED] Assigned Case Updated Successfully....");
        }
        else
        {
            System.err.println("[FAILED] Failed to Update Assigned Case....");
        }
    }
}

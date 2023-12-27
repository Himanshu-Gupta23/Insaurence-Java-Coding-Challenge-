package dao;

import entity.Policy;
import exception.PolicyNotFoundException;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InsuranceServiceImpl implements IPolicyService {

	@Override
	public boolean createPolicy(Policy policy) {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();

		boolean status=false;
		try {
			
			String query = "INSERT INTO Policy (policyId, policyName, policyType, coverageAmount)"
					+ "VALUES (?, ?, ?, ?);";
//			INSERT INTO Policy (policyId, policyName, policyType, coverageAmount)
//			VALUES (1, 'Life Insurance', 'Term Life', 50000.00);
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, policy.getPolicyId());
			pstmt.setString(2, policy.getPolicyName());
			pstmt.setString(3, policy.getPolicyType());
			pstmt.setDouble(4, policy.getCoverageAmount());
			
			status = pstmt.execute();
			return !status;
		}
		catch(Exception e) {
			System.out.println(e);
		}
	
		return status;
	}

	@Override
	public Policy getPolicy(int policyId) {
		// TODO Auto-generated method stub
		
		Connection conn = DBConnection.getConnection();

		Policy result=null;
		try {
			
			String query = "Select * from policy where policyId=?";
//			INSERT INTO Policy (policyId, policyName, policyType, coverageAmount)
//			VALUES (1, 'Life Insurance', 'Term Life', 50000.00);
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, policyId);
			
			
			ResultSet res = pstmt.executeQuery();
			if(res.next())
			result=new Policy(res.getInt("policyId"),res.getString("policyName"),res.getString("policyType"),res.getDouble("coverageAmount"));
			else
				throw new PolicyNotFoundException(policyId);
			
			return result;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<Policy> getAllPolicies() {
		Connection conn = DBConnection.getConnection();

		ArrayList<Policy> result=new ArrayList<Policy>();
		try {
			
			String query = "Select * from policy";
//			INSERT INTO Policy (policyId, policyName, policyType, coverageAmount)
//			VALUES (1, 'Life Insurance', 'Term Life', 50000.00);
			PreparedStatement pstmt=conn.prepareStatement(query);
			
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next())
			result.add(new Policy(res.getInt("policyId"),res.getString("policyName"),res.getString("policyType"),res.getDouble("coverageAmount")));
			return result;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean updatePolicy(Policy policy) {
		Connection conn = DBConnection.getConnection();

		boolean status=false;
		try {
			
			String query = "UPDATE Policy SET policyName=?,policyType=?,coverageAmount=? WHERE policyId = ? ";
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setString(1, policy.getPolicyName());
			pstmt.setString(2, policy.getPolicyType());
			pstmt.setDouble(3, policy.getCoverageAmount());
			pstmt.setInt(4, policy.getPolicyId());
			
			status = pstmt.execute();
			
			if(!status)
            {
                throw new PolicyNotFoundException(0);
            }
			return !status;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
		return status;
	}

	@Override
	public boolean deletePolicy(int policyId) {
		Connection conn = DBConnection.getConnection();

		boolean status=false;
		try {
			
			String query = "DELETE FROM Policy WHERE policyId = ?";
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, policyId);
			
			status = pstmt.execute();
			if(pstmt.getUpdateCount()==0)
            {
                throw new PolicyNotFoundException(policyId);
            }
			return !status;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
		return status;
	}
    // Implement the methods defined in the interface
}

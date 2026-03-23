package main.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.Util.ConnectionUtil;
import main.java.Model.Account;

public class AccountDAO {
    Account ac=new Account();
    AccountDAO() {

    }
    public Account getAccount_id(int account_id){
        Connection con=ConnectionUtil.getConnection();

        String sql="SELECT * FROM account WHERE account_id=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
       
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, account_id);
                      
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        try {
            rs = ps.executeQuery();
            if(rs.next()) {
            Account acct=new Account(rs.getInt("account_id"),rs.getString("username"),
            rs.getString("password"));
            return acct;
            }
        } catch (SQLException e) {
           
            e.printStackTrace();
        }
        
        return null;
    }

    public List<Account> getAllAccounts(){
        Connection con=ConnectionUtil.getConnection();

        List<Account> accounts = new ArrayList<>();
        String sql="SELECT * FROM account";
        PreparedStatement ps=null;
        try {
            ps = con.prepareStatement(sql);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        try {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                accounts.add(new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password")));
            }
        } catch (SQLException e) {
           
            e.printStackTrace();
        }
        return accounts;
        
        
    }

    public Account getUsername(String username){
        Connection con=ConnectionUtil.getConnection();

        String sql="SELECT * FROM account WHERE username=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);         
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        try {
            rs = ps.executeQuery();
            if(rs.next()) {
            Account acct=new Account(rs.getInt("account_id"),rs.getString("username"),
            rs.getString("password"));
            return acct;
            
        } catch (SQLException e) {
           
            e.printStackTrace();
        }
        
        return null;
    }
    
    

    public String getPassword(String password){
        Connection con=ConnectionUtil.getConnection();

        String sql="SELECT * FROM account WHERE password=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, password);
        
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        try {
            rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
           
            e.printStackTrace();
        }
        try {
            return rs.getString("password")+rs.getInt("account_id")+rs.getString("username");
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return rs.toString();
    }
}

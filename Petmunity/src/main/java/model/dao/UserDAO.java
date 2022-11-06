package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.UserList;

/**
 * ����� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * USERINFO ���̺� ����� ������ �߰�, ����, ����, �˻� ���� 
 */
public class UserDAO {
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	/**
	 * ����� ���� ���̺� ���ο� ����� ����.
	 */
	public int create(UserList user) throws SQLException {
		String sql = "INSERT INTO USERLIST VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {user.getUserId(), user.getLoginId(), user.getLoginPwd(),
						user.getUserNickname(), user.getUserBirth(), user.getPhoneNumber(), 
						user.getGender(), user.getAddress(), user.getPetList()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}

	/**
	 * ������ ����� ������ ����.
	 */
	public int update(UserList user) throws SQLException {
		String sql = "UPDATE USERLIST "
					+ "SET loginPwd=?, userNickname=?, userBirth=?, phoneNumber=?, address=?, petList=?"
					+ "WHERE userId=?";
		Object[] param = new Object[] {user.getLoginPwd(), user.getUserNickname(), 
					user.getUserBirth(), user.getPhoneNumber(), 
					user.getAddress(), user.getPetList(),
					user.getUserId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	/**
	 * ����� ID�� �ش��ϴ� ����ڸ� ����.
	 */
	public int remove(String loginId) throws SQLException {
		String sql = "DELETE FROM USERLIST WHERE loginId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {loginId});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	public UserList findUser(String loginId) throws SQLException {
        String sql = "SELECT * "
                 + "FROM USERLIST"
                 + "WHERE loginId=? ";              
      jdbcUtil.setSqlAndParameters(sql, new Object[] {loginId});   // JDBCUtil �� query문과 ׺�개 �  ��  �� ��

      try {
         ResultSet rs = jdbcUtil.executeQuery();      // query  �� ��
         if (rs.next()) {                  //  �� ��  ���  발견
            UserList user = new UserList(      // User 객체�   �� �� �� ��  �� ��  ��보�      ��
               rs.getInt("userId"),
               rs.getString("loginId"),
               rs.getString("loginPwd"),
               rs.getString("userNickname"),
               rs.getDate("userBirth"),
               rs.getString("phoneNumber"),
               rs.getString("gender"),               
               rs.getString("address"),
               rs.getInt("petList"));//dao ����
            return user;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      // resource 반환
      }
      return null;
   }
	
	/**
	 * �־��� ����� ID�� �ش��ϴ� ����ڰ� �����ϴ��� �˻� 
	 */
	public boolean existingUser(String loginId) throws SQLException {
		String sql = "SELECT count(*) FROM USERLIST WHERE loginId=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {loginId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}

}

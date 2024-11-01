package dao;

import java.util.List;

import supereats.Admin;
import supereats.User;

public interface AdminDAO {
	void createAdmin(Admin admin);
	Admin getAdminById(int adminId);
	Admin getAdminByEmail(String email);
	void updateAdmin(Admin admin);
	void deleteAdmin(int adminId);
	List<Admin> getAllAdmins();
	User getAdminByEmailReturnUser(String email);
}

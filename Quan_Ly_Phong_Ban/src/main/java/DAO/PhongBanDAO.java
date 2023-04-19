/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.*;
import Model.PhongBan;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Chau Thinh
 */
public class PhongBanDAO {
    static private PhongBanDAO instance;
    private PhongBanDAO() {}
    public static PhongBanDAO getInstance() {
        if(instance == null)
            instance = new PhongBanDAO();
        return instance;
    }
    
    public ArrayList<PhongBan> getPhongBans() throws Exception {
        return JDBCHelper.getInstance().Execute("select * from PhongBan", null, PhongBan.class);
    }
    
    public PhongBan getPhongBan(PhongBan phongBan) throws Exception {
        ArrayList<PhongBan> arr = JDBCHelper.getInstance().Execute("select * from PhongBan where TenPHG = ?", new Object[] {phongBan.getTenPHG()}, PhongBan.class);
        return arr.get(0);
    }
    
    public Vector getVector() throws Exception {
        return JDBCHelper.getInstance().ExecuteGetVector("select * from PhongBan", null, PhongBan.class);
    }
    
    public boolean addPhongBan(PhongBan phongBan) throws Exception {
        int row = JDBCHelper.getInstance().ExecuteNonQuery("INSERT INTO PhongBan (TenPHG) VALUES (?)", new Object[] {phongBan.getTenPHG()});
        return row > 0;
    }
    
    public boolean deletePhongBan(PhongBan phongBan) throws Exception {
        int row = JDBCHelper.getInstance().ExecuteNonQuery("exec dbo.sp_deletePhongBan @maPB=?", new Object[] {phongBan.getMaPHG()});
        return row > 0;
    }
    
    public boolean updatePhongBan(PhongBan phongBan) throws Exception {
        int row = JDBCHelper.getInstance().ExecuteNonQuery("update PhongBan set TenPHG = ? where MaPHG = ?", new Object[] {phongBan.getTenPHG(), phongBan.getMaPHG()});
        return row > 0;
    }
    
    public boolean checkExistsByName(PhongBan phongBan) throws Exception {
        int row = JDBCHelper.getInstance().ExecuteScalar("select count(*) from PhongBan where TenPHG = ?", new Object[] {phongBan.getTenPHG()});
        return row > 0;
    }
    
    public boolean checkExistsByID(PhongBan phongBan) throws Exception {
        int row = JDBCHelper.getInstance().ExecuteScalar("select count(*) from PhongBan where MaPHG = ?", new Object[] {phongBan.getMaPHG()});
        return row > 0;
    }
    
    public boolean checkInput(Component component, PhongBan phongBan) throws Exception {
        if (phongBan.getTenPHG().isEmpty()) {
            ControlHelper.getInstance().showMessageError(component, "Không được bỏ trống thông tin", "Thông báo");
            return false;
        }
        if (checkExistsByName(phongBan)) {
                ControlHelper.getInstance().showMessageError(component, "Phòng ban đã tồn tại", "Thông báo");
                return false;
            }
        return true;
    }
}

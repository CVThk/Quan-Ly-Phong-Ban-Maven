/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.*;
import Model.*;
import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Chau Thinh
 */
public class NhanVienDAO {
    static private NhanVienDAO instance;
    private NhanVienDAO() {}
    public static NhanVienDAO getInstance() {
        if(instance == null)
            instance = new NhanVienDAO();
        return instance;
    }
    
    public ArrayList<NhanVien> getNhanViens() throws Exception {
        return JDBCHelper.getInstance().Execute("select * from NhanVien", null, NhanVien.class);
    }
    
    public NhanVien getNhanVien(NhanVien nhanVien) throws Exception {
        ArrayList<NhanVien> arr = JDBCHelper.getInstance().Execute("select * from NhanVien where MaNV = ?", new Object[] {nhanVien.getMaNV()}, NhanVien.class);
        return arr.get(0);
    }
    
    public Vector getVector() throws Exception {
        return JDBCHelper.getInstance().ExecuteGetVector("select nv.MaNV, nv.HoTen, nv.Phai, nv.NgaySinh, nv.DiaChi, nv.Luong, pb.TenPHG as 'MaPHG', nv.TrangThai from NhanVien nv, PhongBan pb where pb.MaPHG = nv.MaPHG", null, NhanVien.class);
    }
    
    public boolean addNhanVien(NhanVien nhanVien) throws Exception {
        int row = JDBCHelper.getInstance().ExecuteNonQuery("INSERT INTO NhanVien (HoTen, Phai, NgaySinh, DiaChi, Luong, MaPHG, TrangThai)VALUES(?, ?, ?, ?, ?, ?, ?)",
                new Object[] {nhanVien.getHoTen(), nhanVien.getPhai(), Utility.getInstance().convertDateToString(nhanVien.getNgaySinh(), "yyyy-MM-dd"), nhanVien.getDiachi(), nhanVien.getLuong(), nhanVien.getMaPHG(), nhanVien.getTrangThai()});
        return row > 0;
    }
    
    public boolean deleteNhanVien(NhanVien nhanVien) throws Exception {
        int row = JDBCHelper.getInstance().ExecuteNonQuery("delete NhanVien where MaNV=?", new Object[] {nhanVien.getMaNV()});
        return row > 0;
    }
    
    public boolean updateNhanVien(NhanVien nhanVien) throws Exception {
        int row = JDBCHelper.getInstance().ExecuteNonQuery("update NhanVien set HoTen=?, Phai=?, NgaySinh=?, DiaChi=?, Luong=?, MaPHG=?, TrangThai=? where MaNV=?",
                new Object[] {nhanVien.getHoTen(), nhanVien.getPhai(), Utility.getInstance().convertDateToString(nhanVien.getNgaySinh(), "yyyy-MM-dd"), nhanVien.getDiachi(), nhanVien.getLuong(), nhanVien.getMaPHG(), nhanVien.getTrangThai(), nhanVien.getMaNV()});
        return row > 0;
    }
    
    public boolean checkExistsByID(NhanVien nhanVien) throws Exception {
        int row = JDBCHelper.getInstance().ExecuteScalar("select count(*) from NhanVien where MaNV = ?", new Object[] {nhanVien.getMaNV()});
        return row > 0;
    }
    
    public boolean checkInput(Component component, NhanVien nhanVien) throws Exception {
        if(nhanVien.getHoTen().isEmpty() || nhanVien.getPhai().isEmpty() || nhanVien.getDiachi().isEmpty()) {
            ControlHelper.getInstance().showMessageError(component, "Không được bỏ trống thông tin", "Cảnh báo");
            return false;
        }
        if(nhanVien.getNgaySinh() == null) {
            ControlHelper.getInstance().showMessageError(component, "Ngày sai", "Cảnh báo");
            return false;
        }
        if(nhanVien.getLuong() < 1000) {
            ControlHelper.getInstance().showMessageError(component, "Lương nhập thông tin lỗi", "Cảnh báo");
            return false;
        }
        PhongBan pb = new PhongBan();
        pb.setMaPHG(nhanVien.getMaPHG());
        if(!PhongBanDAO.getInstance().checkExistsByID(pb)) {
            ControlHelper.getInstance().showMessageError(component, "Phòng ban không tồn tại", "Cảnh báo");
            return false;
        }
        return true;
    }
}

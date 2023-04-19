/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Chau Thinh
 */
public class PhongBan {
    int MaPHG;
    String TenPHG;

    public PhongBan() {
    }

    public int getMaPHG() {
        return MaPHG;
    }

    public void setMaPHG(int MaPHG) {
        this.MaPHG = MaPHG;
    }

    public String getTenPHG() {
        return TenPHG;
    }

    public void setTenPHG(String TenPHG) {
        this.TenPHG = TenPHG;
    }

    @Override
    public String toString() {
        return getTenPHG();
    }
}

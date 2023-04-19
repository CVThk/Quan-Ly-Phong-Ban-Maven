/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Frame;

import DAO.NhanVienDAO;
import DAO.PhongBanDAO;
import Helper.*;
import FormatJTable.*;
import Model.NhanVien;
import Model.PhongBan;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Chau Thinh
 */
public class DSNV extends javax.swing.JInternalFrame {

    static final int COL_MANV = 0;
    static final int COL_HOTEN = 1;
    static final int COL_PHAI = 2;
    static final int COL_NGAYSINH = 3;
    static final int COL_DIACHI = 4;
    static final int COL_LUONG = 5;
    static final int COL_MAPHG = 6;
    static final int COL_TRANGTHAI = 7;

    Vector<String> header;
    Vector data;
    ArrayList<PhongBan> phongBans;

    /**
     * Creates new form DSNV
     */
    public DSNV() throws Exception {
        initComponents();
        tblNV.setDefaultEditor(Object.class, null);

        phongBans = PhongBanDAO.getInstance().getPhongBans();
        cboPhongBan.setModel(new DefaultComboBoxModel(phongBans.toArray(new PhongBan[phongBans.size()])));

        header = new Vector<>();
        header.add("Mã NV");
        header.add("Họ Tên");
        header.add("Phái");
        header.add("Ngày Sinh");
        header.add("Địa Chỉ");
        header.add("Lương");
        header.add("Phòng Ban");
        header.add("Trạng Thái");

        loadData();

        tblNV.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tblNV.getSelectedRow();
                int col = tblNV.getSelectedColumn();
                if (row >= 0 && row < tblNV.getRowCount()) {
                    txtHoTen.setText(tblNV.getValueAt(row, COL_HOTEN).toString());
                    switch (tblNV.getValueAt(row, COL_PHAI).toString()) {
                        case "Nam":
                            cboPhai.setSelectedIndex(0);
                            break;
                        default:
                            cboPhai.setSelectedIndex(1);
                            break;
                    }
                    try {
                        jDateChooserNgaySinh.setDate(Utility.getInstance().convertStringToDate(tblNV.getValueAt(row, COL_NGAYSINH).toString()));
                    } catch (ParseException ex) {
                        Logger.getLogger(DSNV.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    txtDiaChi.setText(tblNV.getValueAt(row, COL_DIACHI).toString());
                    txtLuong.setText(Utility.getInstance().formatNumber(Float.parseFloat(tblNV.getValueAt(row, COL_LUONG).toString())));
                    int i = 0;
                    for (; i < phongBans.size(); i++) {
                        if (phongBans.get(i).getTenPHG().equals(tblNV.getValueAt(row, COL_MAPHG).toString())) {
                            break;
                        }
                    }
                    cboPhongBan.setSelectedIndex(i);
                    switch (tblNV.getValueAt(row, COL_TRANGTHAI).toString()) {
                        case "true":
                            cboTrangThai.setSelectedIndex(0);
                            break;
                        default:
                            cboTrangThai.setSelectedIndex(1);
                            break;
                    }
                }
            }
        });

        txtLuong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                    ControlHelper.getInstance().showMessageWarning(DSNV.this, "Chỉ được nhập số", "Thông báo");
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnDong = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboPhai = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jDateChooserNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLuong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboPhongBan = new javax.swing.JComboBox<>();
        cboTrangThai = new javax.swing.JComboBox<>();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblNV);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 10, 910, 190));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem);

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua);

        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa);

        btnDong.setText("Đóng");
        btnDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongActionPerformed(evt);
            }
        });
        jPanel1.add(btnDong);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 950, 50));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Họ Tên:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 27, -1, -1));
        jPanel2.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 24, 344, -1));

        jLabel2.setText("Phái:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 55, -1, -1));

        cboPhai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jPanel2.add(cboPhai, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 52, 344, -1));

        jLabel3.setText("Ngày Sinh:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 80, -1, 22));

        jDateChooserNgaySinh.setDateFormatString("dd/MM/yyyy");
        jPanel2.add(jDateChooserNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 80, 344, -1));

        jLabel4.setText("Địa Chỉ:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 111, -1, -1));
        jPanel2.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 108, 345, -1));

        jLabel5.setText("Lương:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 27, -1, -1));
        jPanel2.add(txtLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(556, 24, 348, -1));

        jLabel6.setText("Trạng Thái:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 83, -1, -1));

        jLabel7.setText("Phòng Ban:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 55, -1, -1));
        jPanel2.add(cboPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(557, 52, 347, -1));

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang làm", "Nghỉ làm" }));
        jPanel2.add(cboTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(557, 80, 347, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 930, 140));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnDongActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        NhanVien nhanVien = getDataNhanVienFromTextField();
        try {
            if (NhanVienDAO.getInstance().checkInput(this, nhanVien)) {
                if (NhanVienDAO.getInstance().addNhanVien(nhanVien)) {
                    loadData();
                    clearText();
                    ControlHelper.getInstance().showMessageInfomation(this, "Thêm thành công", "Thông báo");
                } else {
                    ControlHelper.getInstance().showMessageInfomation(this, "Thêm thất bại", "Thông báo");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DSNV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tblNV.getSelectedRow();
        if (row >= 0 && row < tblNV.getRowCount()) {
            try {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(Integer.parseInt(tblNV.getValueAt(row, COL_MANV).toString()));
                if (!NhanVienDAO.getInstance().checkExistsByID(nhanVien)) {
                    ControlHelper.getInstance().showMessageError(this, "Nhân viên không tồn tại", "Thông báo");
                    return;
                }
                if (ControlHelper.getInstance().confirmYN(this,
                        "Bạn có chắc chắn xoá nhân viên Mã: " + tblNV.getValueAt(row, COL_MANV) + " ?", "Thông báo")) {
                    if (NhanVienDAO.getInstance().deleteNhanVien(nhanVien)) {
                        data.remove(row);
                        clearText();
                        tblNV.updateUI();
                        ControlHelper.getInstance().showMessageInfomation(this, "Xoá thành công", "Thông báo");
                    } else {
                        ControlHelper.getInstance().showMessageError(this, "Xoá thất bại", "Thông báo");
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(DSNV.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ControlHelper.getInstance().showMessageError(this, "Chọn dòng trước khi xoá", "Thông báo");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int row = tblNV.getSelectedRow();
        if (row >= 0 && row < tblNV.getRowCount()) {
            try {
                NhanVien nhanVien = getDataNhanVienFromTextField();
                nhanVien.setMaNV(Integer.parseInt(tblNV.getValueAt(row, COL_MANV).toString()));
                if (!NhanVienDAO.getInstance().checkExistsByID(nhanVien)) {
                    ControlHelper.getInstance().showMessageError(this, "Nhân viên không tồn tại", "Thông báo");
                    return;
                }
                if (NhanVienDAO.getInstance().checkInput(this, nhanVien)) {
                    if (NhanVienDAO.getInstance().updateNhanVien(nhanVien)) {
                        loadData();
                        clearText();
                        ControlHelper.getInstance().showMessageInfomation(this, "Cập nhật thành công", "Thông báo");
                    } else {
                        ControlHelper.getInstance().showMessageInfomation(this, "Cập nhật thất bại", "Thông báo");
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(DSNV.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ControlHelper.getInstance().showMessageError(this, "Chọn dòng trước khi cập nhật", "Thông báo");
        }
    }//GEN-LAST:event_btnSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDong;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboPhai;
    private javax.swing.JComboBox<Object> cboPhongBan;
    private javax.swing.JComboBox<String> cboTrangThai;
    private com.toedter.calendar.JDateChooser jDateChooserNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNV;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuong;
    // End of variables declaration//GEN-END:variables

    private void loadData() throws Exception {
        if (data != null) {
            data.clear();
        }
        data = NhanVienDAO.getInstance().getVector();
        data.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Vector<Object> v1 = (Vector<Object>) o1;
                Vector<Object> v2 = (Vector<Object>) o2;
                return Integer.compare((int) v1.get(0), (int) v2.get(0));
            }
        });

        DefaultTableModel dtm = (DefaultTableModel) tblNV.getModel();
        dtm.setDataVector(data, header);

        TableColumn dateColumn = tblNV.getColumnModel().getColumn(COL_NGAYSINH);
        dateColumn.setCellRenderer(new DateCellRenderer());

        TableColumn moneyColumn = tblNV.getColumnModel().getColumn(COL_LUONG);
        // Set the custom cell renderer for the column
        moneyColumn.setCellRenderer(new MoneyCellRenderer());

        // Get a reference to the column you want to display as a checkbox
        TableColumn statusColumn = tblNV.getColumnModel().getColumn(COL_TRANGTHAI);
        // Set the editor and renderer for the column
        statusColumn.setCellEditor(new CheckboxCellEditor());
        statusColumn.setCellRenderer(new CheckboxCellRenderer());
    }

    private void clearText() {
        txtHoTen.setText("");
        txtDiaChi.setText("");
        txtLuong.setText("");
    }

    private NhanVien getDataNhanVienFromTextField() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setHoTen(txtHoTen.getText());
        nhanVien.setPhai(cboPhai.getSelectedItem().toString());
        nhanVien.setNgaySinh(jDateChooserNgaySinh.getDate());
        nhanVien.setDiachi(txtDiaChi.getText());
        nhanVien.setLuong(Float.parseFloat(txtLuong.getText().isEmpty() ? "-1" : txtLuong.getText()));
        nhanVien.setMaPHG(((PhongBan) cboPhongBan.getSelectedItem()).getMaPHG());
        if (cboTrangThai.getSelectedItem().toString().equals("Đang làm")) {
            nhanVien.setTrangThai(1);
        } else {
            nhanVien.setTrangThai(0);
        }
        return nhanVien;
    }
}

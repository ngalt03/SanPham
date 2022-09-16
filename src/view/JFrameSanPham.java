package view;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.SanPham;
import service.SanPhamService;

public class JFrameSanPham extends javax.swing.JFrame {

    private SanPhamService spService = new SanPhamService();

    public JFrameSanPham() {
        initComponents();
        loadTable();
    }

    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) tbtTable.getModel();
        List<SanPham> ds = spService.getList();
        model.setRowCount(0);
        for (SanPham d : ds) {
            Object[] row = {
                d.getId(),
                d.getMa(),
                d.getTen(),
                d.getNgaySX(),
                d.getSluong(),
                d.getGiaBan(),
                d.getTrangthai() == 0 ? "Còn hàng" : "Hết hàng",
                d.getLoaiSP()
            };
            model.addRow(row);
        }
    }

    private void clearForm() {
        lbId.setText("");
        txtMa.setText("");
        txtTen.setText("");
        txtNgaySX.setText("");
        txtSluong.setText("");
        txtGia.setText("");
        rdoNam.setSelected(true);
        cbbLoaiSP.setSelectedIndex(0);
    }

    private SanPham getFormData() {
        String id = lbId.getText().trim();
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        String ngaySX = txtNgaySX.getText().trim();
        String soLuong = txtSluong.getText().trim();
        String gia = txtGia.getText().trim();
        int trangThai = rdoNam.isSelected() == true ? 0 : 1;
        String loaiSP = cbbLoaiSP.getSelectedItem().toString();

        if (ma.length() == 0 || ten.length() == 0 || ngaySX.length() == 0 || soLuong.length() == 0 || gia.length() == 0) {
            JOptionPane.showMessageDialog(this, "Cần nhập đủ thông tin");
            return null;
        } else if (!checkMa(ma)) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm phải gồm SP+ ");
            return null;
        } else if (!checkSo(soLuong)) {
            JOptionPane.showMessageDialog(this, "Sluong Phải là số ");
            return null;
        } else if (Integer.parseInt(soLuong) <= 0) {
            JOptionPane.showMessageDialog(this, "Phải lớn hơn 0");
            return null;
        }
        try {
            double dongia = Double.parseDouble(gia);
            if (dongia < 0) {
                JOptionPane.showMessageDialog(this, "Giá phải lớn hơn 0");
                return null;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Giá phải là số");
            ex.printStackTrace();
            return null;
        }

        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-mm-dd");
        try {
            date = sdf.parse(ngaySX);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Sai định dạng ngày tháng");
            ex.printStackTrace();
            return null;
        }
        SanPham sp = new SanPham(id, ma, ten, loaiSP, date, Integer.parseInt(soLuong), trangThai, new BigDecimal(gia));
        return sp;
    }

    private boolean checkMa(String txt) {
        Pattern p = Pattern.compile("^SP+[0-9]+$");
        Matcher m = p.matcher(txt);
        return m.matches();
    }

    private boolean checkSo(String txt) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(txt);
        return m.matches();
    }

    private boolean trungMa(String txt) {
        List<SanPham> ds = spService.getList();
        for (SanPham d : ds) {
            if (d.getMa().equalsIgnoreCase(txt)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbId = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSluong = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        txtNgaySX = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbbLoaiSP = new javax.swing.JComboBox<>();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbtTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Số lượng");

        jLabel2.setText("Id");

        jLabel3.setText("Giá");

        jLabel4.setText("Trạng thái");

        jLabel5.setText("Mã ");

        jLabel6.setText("Tên");

        jLabel7.setText("Loại SP");

        lbId.setText("_");

        jLabel9.setText("Ngày SX");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 255));
        jLabel8.setText("QUẢN LÝ SẢN PHẨM");

        cbbLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Để bàn", "Laptop", "Nhà bếp", "Phòng ngủ", "Gia dụng" }));

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Còn hàng");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Hết hàng");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnTim.setText("Tìm kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        tbtTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Mã", "Tên", "Ngày sx", "Số lượng", "Giá", "Trạng thái", "Loại sp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbtTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbtTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                            .addComponent(lbId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(txtNgaySX, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNu))
                            .addComponent(cbbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSluong, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addGap(53, 53, 53)
                .addComponent(btnSua)
                .addGap(58, 58, 58)
                .addComponent(btnXoa)
                .addGap(44, 44, 44)
                .addComponent(btnTim)
                .addGap(69, 69, 69)
                .addComponent(btnClear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbId)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtNgaySX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnTim)
                    .addComponent(btnXoa)
                    .addComponent(btnSua)
                    .addComponent(btnClear))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbtTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbtTableMouseClicked
        int row = tbtTable.getSelectedRow();
        if (row == -1) {
            return;
        }
        String id = tbtTable.getValueAt(row, 0).toString();
        String ma = tbtTable.getValueAt(row, 1).toString();
        String ten = tbtTable.getValueAt(row, 2).toString();
        String ngaySX = tbtTable.getValueAt(row, 3).toString();
        String soLuong = tbtTable.getValueAt(row, 4).toString();
        String gia = tbtTable.getValueAt(row, 5).toString();
        String trangThai = tbtTable.getValueAt(row, 6).toString();
        String loaiSP = tbtTable.getValueAt(row, 7).toString();

        lbId.setText(id);
        txtMa.setText(ma);
        txtTen.setText(ten);
        txtNgaySX.setText(ngaySX);
        txtSluong.setText(soLuong);
        txtGia.setText(gia);
        if (trangThai.equals("Còn hàng")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        cbbLoaiSP.setSelectedItem(loaiSP);

    }//GEN-LAST:event_tbtTableMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tbtTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn một dòng trên table");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xoá");
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        String idStr = tbtTable.getValueAt(row, 0).toString();
        spService.delete(idStr);
        loadTable();
        JOptionPane.showMessageDialog(this, "Xoá thành công!");
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        int row = tbtTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn một dòng trên table");
            return;
        }
//        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xoá");
//        if (confirm != JOptionPane.YES_OPTION) {
//            return;
//        }
        SanPham sp = getFormData();
        if (sp == null) {
            return;
        }
        String idStr = tbtTable.getValueAt(row, 0).toString();
        spService.update(idStr, sp);
        loadTable();
        JOptionPane.showMessageDialog(this, "Sửa thành công!");
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        SanPham sp = getFormData();
        if (sp == null) {
            return;
        } else if (trungMa(sp.getMa())) {
            JOptionPane.showMessageDialog(this, "Mã ko được trùng");
            return;
        }
        spService.insert(sp);
        loadTable();
        JOptionPane.showMessageDialog(this, "Thêm thành công");
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        String maSP = txtMa.getText().trim();
        List<SanPham> lstSP = spService.getList();
        if (maSP.length() == 0) {
            JOptionPane.showMessageDialog(this, "Mời nhập mã");
        }
        for (int i = 0; i < 10; i++) {
            SanPham sp = lstSP.get(i);
            if (sp.getMa().equals(maSP)) {
                lbId.setText(sp.getId());
                txtMa.setText(sp.getMa());
                txtTen.setText(sp.getTen());
                txtNgaySX.setText(sp.getNgaySX() + "");
                txtSluong.setText(sp.getSluong() + "");
                txtGia.setText(sp.getGiaBan() + "");
                if (sp.getTrangthai() == 0) {
                    rdoNam.setSelected(true);
                } else {
                    rdoNu.setSelected(true);
                }
                cbbLoaiSP.setSelectedItem(sp.getLoaiSP());
                
                JOptionPane.showMessageDialog(this,"Đã tìm thấy sp có mã:"+ maSP);
                loadTable();
                tbtTable.setRowSelectionInterval(i, i);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Không tìm thấy");
    }//GEN-LAST:event_btnTimActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbLoaiSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbId;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tbtTable;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNgaySX;
    private javax.swing.JTextField txtSluong;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}

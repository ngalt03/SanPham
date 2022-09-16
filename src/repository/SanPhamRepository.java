package repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import model.SanPham;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamRepository {

    public ArrayList<SanPham> getAll() {
        ArrayList<SanPham> lst = new ArrayList<>();
        try {
            Connection conn = DBContext.getConnection();
            String sql = "select a.Id as 'Id',a.Ma as 'Ma',a.Ten as 'TenSP',NgaySX,SoLuong,GiaBan,TrangThai,b.Ten as 'LoaiSp' \n"
                    + "from SanPham a LEFT JOIN LoaiSP b ON a.IdLoaiSP = b.Id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Id");
                String ma = rs.getString("Ma");
                String ten = rs.getString("TenSP");
                Date ngaySX = rs.getDate("NgaySX");
                int soLuong = rs.getInt("SoLuong");
                BigDecimal gia = rs.getBigDecimal("GiaBan");
                int trangThai = rs.getInt("TrangThai");
                String loaiSP = rs.getString("LoaiSp");

                SanPham sp = new SanPham(id, ma, ten, loaiSP, ngaySX, soLuong, trangThai, gia);
                lst.add(sp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lst;

    }

    public void insert(SanPham sp) {
        try {
            Connection conn = DBContext.getConnection();
            String sql = "INSERT INTO SanPham(Ma,Ten,NgaySX,SoLuong,GiaBan,TrangThai) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
        //    ps.setString(1, sp.getId());
            ps.setString(1, sp.getMa());
            ps.setString(2, sp.getTen());
            java.sql.Date date = new java.sql.Date(sp.getNgaySX().getTime());
            ps.setDate(3, date);
            ps.setInt(4, sp.getSluong());
            ps.setBigDecimal(5, sp.getGiaBan());
            ps.setInt(6, sp.getTrangthai());

            ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void update(String id, SanPham sp) {
        try {
            Connection conn = DBContext.getConnection();
            String sql = "UPDATE SanPham SET Ma=?,Ten=?,NgaySX=?,SoLuong=?,GiaBan=?,TrangThai=? WHERE Id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getMa());
            ps.setString(2, sp.getTen());
            java.sql.Date date = new java.sql.Date(sp.getNgaySX().getTime());
            ps.setDate(3, date);
            ps.setInt(4, sp.getSluong());
            ps.setBigDecimal(5, sp.getGiaBan());
            ps.setInt(6, sp.getTrangthai());
            ps.setString(7, sp.getId());

            ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            Connection conn = DBContext.getConnection();
            String sql = "DELETE FROM SanPham WHERE Id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package model;

import java.math.BigDecimal;
import java.util.Date;

public class SanPham {

    private String id, ma, ten, loaiSP;
    private Date ngaySX;
    private int sluong, trangthai;
    private BigDecimal giaBan;

    public SanPham(String id, String ma, String ten, String loaiSP, Date ngaySX, int sluong, int trangthai, BigDecimal giaBan) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.loaiSP = loaiSP;
        this.ngaySX = ngaySX;
        this.sluong = sluong;
        this.trangthai = trangthai;
        this.giaBan = giaBan;
    }

    public SanPham() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public Date getNgaySX() {
        return ngaySX;
    }

    public void setNgaySX(Date ngaySX) {
        this.ngaySX = ngaySX;
    }

    public int getSluong() {
        return sluong;
    }

    public void setSluong(int sluong) {
        this.sluong = sluong;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    @Override
    public String toString() {
        return "SanPham{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + ", loaiSP=" + loaiSP + ", ngaySX=" + ngaySX + ", sluong=" + sluong + ", trangthai=" + trangthai + ", giaBan=" + giaBan + '}';
    }

}

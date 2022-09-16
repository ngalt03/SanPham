package service;

import java.util.ArrayList;
import model.SanPham;
import repository.SanPhamRepository;

public class SanPhamService {

    private SanPhamRepository sanPhamRepository = new SanPhamRepository();

    public SanPhamService() {
    }

    public ArrayList<SanPham> getList() {
        return sanPhamRepository.getAll();
    }

    public void insert(SanPham sp) {
        sanPhamRepository.insert(sp);
    }

    public void update(String id,SanPham sp) {
        sanPhamRepository.update(id,sp);
    }

    public void delete(String id) {
        sanPhamRepository.delete(id);
    }
}

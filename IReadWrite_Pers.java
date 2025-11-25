package CongTyBatDongSan;

public interface IReadWrite_Pers {
    NhanVien parseNhanVien(String line);
    KhachHang parseKhachHang(String line);
    void ghiFileNV(String path);
    void ghiFileKH(String path);
    void docFileNhanVien(String path);
    void docFileKhachHang(String path);
}

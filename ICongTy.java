package CongTyBatDongSan;

public interface ICongTy{
    void themNhanVien(NhanVien nhanVien);
    void themKhachHang(KhachHang khachHang);
    void xoa(String ma);
    NhanVien timNhanVienTheoMa(String ma);
    KhachHang timKhachHangTheoMa(String ma);
    void hienThiDanhSachNhanVien();
    void hienThiDanhSachKhachHang();
}

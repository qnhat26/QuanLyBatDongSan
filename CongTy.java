package CongTyBatDongSan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator; 


public class CongTy implements ICongTy, IReadWrite_Pers{

    private ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
    private ArrayList<KhachHang> dsKhachHang = new ArrayList<>();

    @Override
    public void themNhanVien(NhanVien nhanVien) {
        for (NhanVien nv : dsNhanVien) {
            if (nv.getMaNv().equalsIgnoreCase(nhanVien.getMaNv())) {
                System.out.println("\nMã nhân viên đã tồn tại, yêu cầu nhập lại");
                Input.sc.nextLine();
                return; // DỪNG HÀM, KHÔNG THÊM NỮA
            } 
        }
        dsNhanVien.add(nhanVien);

        Input.clearScreen();
        System.out.println("\nThêm nhân viên thành công!");
        Input.sc.nextLine();
        
    }


    @Override
    public void themKhachHang(KhachHang khachHang) {
        for (KhachHang kh : dsKhachHang) {
            if (kh.getMaKh().equalsIgnoreCase(khachHang.getMaKh())) {
                System.out.println("\nMã khách hàng đã tồn tại, yêu cầu nhập lại");
                Input.sc.nextLine();
                return; // DỪNG HÀM, KHÔNG THÊM NỮA
            }
        }
        dsKhachHang.add(khachHang);

        Input.clearScreen();
        System.out.println("\nThêm khách hàng thành công!");
        Input.sc.nextLine();
    }

    @Override
    public void xoa(String ma) {
        //Kiểm tra xem mã bắt đầu bằng kí tự gì dể xóa
        if (ma.toUpperCase().startsWith("NV")) {
            
            // Dùng Iterator để duyệt và xóa an toàn
            Iterator<NhanVien> nvIterator = dsNhanVien.iterator();
            while (nvIterator.hasNext()) {
                NhanVien nv = nvIterator.next();
                
                if (nv.getMaNv().equalsIgnoreCase(ma)) {
                    nvIterator.remove();
                    System.out.println("Đã xóa nhân viên: " + ma);
                    return;
                }
            }
            System.out.println("Không tìm thấy nhân viên với mã: " + ma);
        } 
        
        else if (ma.toUpperCase().startsWith("KH")) {
            
            // Dùng Iterator để duyệt và xóa an toàn
            Iterator<KhachHang> khIterator = dsKhachHang.iterator();
            while (khIterator.hasNext()) {
                KhachHang kh = khIterator.next();
                
                if (kh.getMaKh().equalsIgnoreCase(ma)) {
                    khIterator.remove();
                    System.out.println("Đã xóa khách hàng: " + ma);
                    return;
                }
            }
            System.out.println("Không tìm thấy khách hàng với mã: " + ma);
        }
        
        else {
            System.out.println("Mã không hợp lệ. Mã phải bắt đầu bằng 'NV' hoặc 'KH'.");
        }
    }

    public NhanVien timNhanVienTheoMa(String maNv) {
        for (NhanVien nv : dsNhanVien) {
            if (nv.getMaNv().equalsIgnoreCase(maNv)) {
                System.out.println("Tìm thấy nhân viên:");
                nv.Xuat();
                return nv;
            }
        }
        Input.clearScreen();
        System.out.println("Không tìm thấy nhân viên");
        return null;
    }

    public KhachHang timKhachHangTheoMa(String maKh) {
        for (KhachHang kh : dsKhachHang) {
            if (kh.getMaKh().equalsIgnoreCase(maKh)) {
                System.out.println("Tìm thấy khách hàng:");
                kh.Xuat();
                return kh;
            }
        }
        Input.clearScreen();
        System.out.println("Không tìm thấy khách hàng");
        return null;
    }

    @Override
    public void hienThiDanhSachNhanVien() {
        System.out.println("\n================================= DANH SÁCH NHÂN VIÊN =================================");

        if (dsNhanVien.isEmpty()) {
            System.out.println("Không có nhân viên nào!");
        } else {
            System.out.printf("%-10s %-20s %-15s %-30s %-12s %-15s %-10s %-12s %-12s\n",
                    "Mã NV", "Họ tên", "SĐT", "Địa chỉ", "Lương CB",
                    "Loại NV", "GD/GT", "Hoa hồng", "Lương");

            System.out.println("---------------------------------------------------------------------------------------------------------------");

            for (NhanVien nv : dsNhanVien) {

                String loaiNV = "";
                String gd_gt = "";
                String hoahong = "";

                if (nv instanceof NhanVienKinhDoanh) {
                    NhanVienKinhDoanh kd = (NhanVienKinhDoanh) nv;
                    loaiNV = "Kinh Doanh";
                    gd_gt = String.valueOf(kd.getSoGiaoDich());
                    hoahong = String.valueOf(kd.getHoaHong());
                } 
                else if (nv instanceof NhanVienMoiGioi) {
                    NhanVienMoiGioi mg = (NhanVienMoiGioi) nv;
                    loaiNV = "Môi Giới";
                    gd_gt = String.valueOf(mg.getSoKhachGioiThieu());
                    hoahong = String.valueOf(mg.getTienHoaHongMoiKhach());
                }

                System.out.printf("%-5s %-20s %-10s %-50s %-12.2f %-15s %-10s %-12s %-12.2f\n",
                        nv.getMaNv(),
                        nv.getHoTen(),
                        nv.getSdt(),
                        nv.getDc().toDataString(),
                        nv.getLuongCoBan(),
                        loaiNV,
                        gd_gt,
                        hoahong,
                        nv.tinhLuong()
                );
            }
        }

        System.out.println("=========================================================================================\n");

        System.out.println("Nhấn Enter để quay lại...");
        Input.sc.nextLine();
    }

    @Override
    public void hienThiDanhSachKhachHang() {
        System.out.println("\n================================= DANH SÁCH KHÁCH HÀNG =================================");

        if (dsKhachHang.isEmpty()) {
            System.out.println("Không có khách hàng nào!");
        } else {

            System.out.printf("%-5s %-20s %-10s %-50s %-15s %-20s %-25s\n",
                    "Mã KH", "Họ tên", "SĐT", "Địa chỉ", "Loại KH",
                    "CCCD/MST", "Nghề/Lĩnh vực");

            System.out.println("---------------------------------------------------------------------------------------------------------------");

            for (KhachHang kh : dsKhachHang) {

                String loaiKH = "";
                String soCCCD_MST = "";
                String nghe_nganh = "";

                if (kh instanceof KhachHangCaNhan) {
                    KhachHangCaNhan cn = (KhachHangCaNhan) kh;
                    loaiKH = "Cá nhân";
                    soCCCD_MST = cn.getCccd();
                    nghe_nganh = cn.getNgheNghiep();
                }
                else if (kh instanceof KhachHangDoanhNghiep) {
                    KhachHangDoanhNghiep dn = (KhachHangDoanhNghiep) kh;
                    loaiKH = "Doanh nghiệp";
                    soCCCD_MST = dn.getMaSoThue();
                    nghe_nganh = dn.getLinhVucHoatDong();
                }

                System.out.printf("%-10s %-20s %-15s %-30s %-15s %-20s %-25s\n",
                        kh.getMaKh(),
                        kh.getHoTen(),
                        kh.getSdt(),
                        kh.getDc().toDataString(),
                        loaiKH,
                        soCCCD_MST,
                        nghe_nganh
                );
            }
        }

        System.out.println("=========================================================================================\n");

        System.out.println("Nhấn Enter để quay lại...");
        Input.sc.nextLine();
    }

    
    @Override
    public NhanVien parseNhanVien(String line) {
        try {
            String[] a = line.split(";");
            if (a.length < 10) {
                System.out.println("Dòng dữ liệu không hợp lệ: " + line);
                return null;
            }

            String loai = a[0];
            String maNv = a[1];
            String hoTen = a[2];
            String sdt = a[3];

            DiaChi dc = new DiaChi(a[4], a[5], a[6]);

            double luong = Double.parseDouble(a[7]);

            switch (loai) {
                case "NhanVienMoiGioi":
                    int soKhach = Integer.parseInt(a[8]);
                    double hoaHongMoiKhach = Double.parseDouble(a[9]);
                    return new NhanVienMoiGioi(hoTen,sdt, dc, maNv, luong, soKhach, hoaHongMoiKhach);

                case "NhanVienKinhDoanh":
                    int soGD = Integer.parseInt(a[8]);
                    double hoaHong = Double.parseDouble(a[9]);
                    return new NhanVienKinhDoanh(hoTen, sdt, dc, maNv, luong, soGD, hoaHong);

                default:
                    System.out.println("Không nhận dạng loại nhân viên: " + loai);
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Lỗi parse: " + e.getMessage() + " | Dòng: " + line);
            return null;
        }
    }

    @Override
    public KhachHang parseKhachHang(String line) {
        try {
            String[] a = line.split(";");
            if (a.length < 9) {
                System.out.println("Dòng dữ liệu không hợp lệ: " + line);
                return null;
            }

            String loai = a[0];
            String maKh = a[1];
            String hoTen = a[2];
            String sdt = a[3];
            DiaChi dc = new DiaChi(a[4], a[5], a[6]);

            switch (loai) {
                case "KhachHangDoanhNghiep":
                    String maSoThue = a[7];
                    String tenCongTy = a[8];
                    String linhVucHoatDong = a[9];
                    return new KhachHangDoanhNghiep(maKh, hoTen, sdt, dc, maSoThue, tenCongTy, linhVucHoatDong);

                case "KhachHangCaNhan":
                    String cccd=a[7];
                    String ngheNghiep=a[8];
                    return new KhachHangCaNhan(maKh, hoTen, sdt, dc, cccd, ngheNghiep);

                default:
                    System.out.println("Không nhận dạng loại khách hàng: " + loai);
                    return null;
        }
        } catch (Exception e) {
            System.out.println("Lỗi parse: " + e.getMessage() + " | Dòng: " + line);
            return null;
        }
    }
    
    @Override
    public void ghiFileNV(String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (NhanVien nv : dsNhanVien) {
                bw.write(nv.toDataString());
                bw.newLine();
            }
            System.out.println("Ghi file nhân viên thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi ghi file NV: " + e.getMessage());
        }
    }

    @Override
    public void ghiFileKH(String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (KhachHang kh : dsKhachHang) {
                bw.write(kh.toDataString());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Lỗi ghi file KH: " + e.getMessage());
        }
    }
    
    @Override
    public void docFileNhanVien(String path) {
        dsNhanVien.clear(); //xóa toàn bộ danh sách nhân viên trước khi đọc từ file
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                NhanVien nv = parseNhanVien(line);
                if (nv != null) dsNhanVien.add(nv);
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file nhân viên: " + e.getMessage());
        }
    }

    @Override
    public void docFileKhachHang(String path) {
        dsKhachHang.clear();//xóa toàn bộ danh sách nhân viên trước khi đọc từ file
       try (BufferedReader br = new BufferedReader(
            new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            
            String line;
            while ((line = br.readLine()) != null) {
                KhachHang kh = parseKhachHang(line);
                if (kh != null) dsKhachHang.add(kh);
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file KH: " + e.getMessage());
        }
    }
}

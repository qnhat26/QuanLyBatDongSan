package CongTyBatDongSan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public abstract class NhanVien extends Nguoi implements IQuanLy, IReadWrite_BDS {

    //thuoc tinh
    protected String maNv;
    protected double luongCoBan;

    protected ArrayList<BatDongSan> dsBDS = new ArrayList<>();

    //construtor
    public NhanVien() {}
    public NhanVien(String hoTen, String soDienThoai, DiaChi diaChi,
                    String maNv, double luongCoBan) {
        super(hoTen, soDienThoai, diaChi);
        this.maNv = maNv;
        this.luongCoBan = luongCoBan;
    }

    //Nhap
    @Override
    public void Nhap() {
        System.out.println("====NHẬP THÔNG TIN NHÂN VIÊN====");
        while (true) {
            System.out.print("Mã NV: ");
            this.maNv = Input.sc.nextLine().toUpperCase();
            //Kiểm tra mã NV có bắt đầu bắng 'NV' hay không
            if (!maNv.toUpperCase().startsWith("NV")){
                System.out.println("Mã NV phải bắt đầu từ 'NV'!");
            }else break;
        }

        super.Nhap();
        
        System.out.print("Lương cơ bản: ");
        this.luongCoBan = Double.parseDouble(Input.sc.nextLine());
    }

    //Xuat
    @Override
    public void Xuat() {
        System.out.println("====THÔNG TIN NHÂN VIÊN====");
        System.out.println("Mã NV: " + this.maNv);

        super.Xuat();

        System.out.println("Lương cơ bản: " + this.luongCoBan);
    }

    public String toDataString(){
        return "";
    }
    
    @Override
    public void them(BatDongSan bds) {
        if (bds == null) {
            System.out.println("BDS không hợp lệ");
        }

        for (BatDongSan b : dsBDS) {
            if (b.getMaBDS().equalsIgnoreCase(bds.getMaBDS())) {
                System.out.println("BDS đã tồn tại");
            }
        }

        dsBDS.add(bds);
        Input.clearScreen();
        System.out.println("Thành công!\n");
    }

    @Override
    public abstract double tinhLuong();

    @Override
    public void xoa(String maBDS) {
        BatDongSan rm=tim(maBDS);
        if (rm!=null) {
            dsBDS.remove(rm);
            Input.clearScreen();
            System.out.println("Thành công!\n");
        }else System.out.println("Không tìm thấy BDS để xóa");
    }

    @Override
    public BatDongSan tim(String maBDS) {
        System.out.println("====TÌM KIẾM BẤT ĐỘNG SẢN====");
        for (BatDongSan b : dsBDS) {
            if (b.getMaBDS().equalsIgnoreCase(maBDS)) {
                System.out.println("Đã tìm thấy BDS!");
                return b;
            }
        }
        System.out.println("Không tìm thấy BDS!");
        return null;
    }

    public void hienThiBatDongSanQuanLy() {
        Input.clearScreen();
        if (dsBDS.isEmpty()) {
            System.out.println("Nhân viên " + this.maNv + " chưa quản lý BĐS nào.");
            return;
        }

        System.out.println("\n=============================================== DANH SÁCH BẤT ĐỘNG SẢN DO NHÂN VIÊN " + this.maNv + " QUẢN LÝ ===============================================");

        System.out.printf("%-10s %-50s %-10s %-15s %-15s %-15s %-30s\n",
                "Mã BDS", "Địa chỉ", "DT(m2)", "Giá", "Thuế", "Loại", "Thông tin riêng");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

        for (BatDongSan bds : dsBDS) {

            String loai = "";
            String thuocTinhRieng = "";

            if (bds instanceof CanHo) {
                loai = "Căn hộ";
                CanHo c = (CanHo) bds;
                thuocTinhRieng = "Tầng: " + c.getTang() + ", Phòng ngủ: " + c.getSoPhongNgu();
            }
            else if (bds instanceof NhaO) {
                loai = "Nhà ở";
                NhaO n = (NhaO) bds;
                thuocTinhRieng = "Số tầng: " + n.getSoTang() +
                        ", Sân vườn: " + (n.isSanVuon() ? "Có" : "Không");
            }
            else if (bds instanceof DatNen) {
                loai = "Đất nền";
                DatNen d = (DatNen) bds;
                thuocTinhRieng = "Loại đất: " + d.getLoaiDat().getTen();
            }

            System.out.printf("%-10s %-50s %-10.2f %-15.0f %-15.0f %-15s %-30s\n",
                    bds.getMaBDS(),
                    bds.getDiaChi().toDataString(),
                    bds.getDienTich(),
                    bds.getGia(),
                    bds.TinhThue(),
                    loai,
                    thuocTinhRieng
            );
        }

        System.out.println("===============================================================================================================================================\n");
    }

    
    @Override
    public BatDongSan parseBDS(String line) {
        try {
            String[] a = line.split(";");
            if (a.length < 7) { // tối thiểu các trường chung
                System.out.println("Dữ liệu không đủ để parse BĐS: " + line);
                return null;
            }

            String loai = a[0].trim();
            String ma = a[1].trim();
            DiaChi dc = new DiaChi(a[2].trim(), a[3].trim(), a[4].trim());
            float dienTich = Float.parseFloat(a[5].trim());
            double gia = Double.parseDouble(a[6].trim());

            switch (loai) {
                case "CanHo": {
                    if (a.length < 9) {
                        System.out.println("Dữ liệu CanHo không đủ: " + line);
                        return null;
                    }
                    int tang = Integer.parseInt(a[7].trim());
                    int soPhongNgu = Integer.parseInt(a[8].trim());
                    return new CanHo(ma, dc, dienTich, gia, tang, soPhongNgu);
                }

                case "NhaO": {
                    if (a.length < 9) {
                        System.out.println("Dữ liệu NhaO không đủ: " + line);
                        return null;
                    }
                    int soTang = Integer.parseInt(a[7].trim());
                    boolean sanVuon = Boolean.parseBoolean(a[8].trim());
                    return new NhaO(ma, dc, dienTich, gia, soTang, sanVuon);
                }

                case "DatNen": {
                    loaiDat ld;
                    try {
                        ld = loaiDat.valueOf(a[7].trim());
                    } catch (Exception ex) {
                        System.out.println("Loại đất không hợp lệ: " + a[7] + " → mặc định DatO");
                        ld = loaiDat.DatO;
                    }
                    return new DatNen(ma, dc, dienTich, gia, ld);
                }

                default:
                    System.out.println("Không nhận dạng loại BĐS: " + loai);
                    return null;
            }

        } catch (Exception e) {
            System.out.println("Lỗi parse BDS: " + e.getMessage() + " | Dòng: " + line);
            return null;
        }
    }


    @Override    
    public void ghiFileBDS(String path) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {

            for (BatDongSan bds : dsBDS) {
                bw.write(bds.toDataString());
                bw.newLine();
            }

        } catch (Exception e) {
            System.out.println("Lỗi ghi file BĐS: " + e.getMessage());
        }
    }

    @Override
    public void docFileBDS(String path) {
        dsBDS.clear();//xóa toàn bộ danh sách nhân viên trước khi đọc từ file

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                BatDongSan bds = parseBDS(line);

                if (bds != null) dsBDS.add(bds);
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file BĐS: " + e.getMessage());
        }
    }

    
    public String getMaNv() {
        return maNv; 
    }
    public void setMaNv(String maNv) { 
        this.maNv = maNv; 
    }

    public double getLuongCoBan() { 
        return luongCoBan; 
    }
    public void setLuongCoBan(double luongCoBan) { 
        this.luongCoBan = luongCoBan; 
    }
    public ArrayList<BatDongSan> getDsBDS() {
        return dsBDS;
    }
    
}


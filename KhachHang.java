package CongTyBatDongSan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public abstract class KhachHang extends Nguoi implements IGiaoDich, IReadWrite_BDS {    
    //Thuoc tinh
    protected String maKh;
    private ArrayList<BatDongSan> dsBatDongSanCuaKhach=new ArrayList<>();

    //Constructor
    public KhachHang() {}
    public KhachHang(String maKh,
                     String hoTen, String soDienThoai, DiaChi diaChi) {
        super(hoTen, soDienThoai, diaChi);
        this.maKh = maKh;
    }

    //Nhap
    @Override
    public void Nhap(){
        System.out.println("====NHẬP THÔNG TIN KHÁCH HÀNG====");
        while (true) {
            System.out.print("Mã KH: ");
            this.maKh = Input.sc.nextLine().toUpperCase();
            //Kiểm tra mã KH có bắt đầu bắng 'KH' hay không
            if (!maKh.toUpperCase().startsWith("KH")){
                System.out.println("Mã KH phải bắt đầu từ 'KH'!");
            }else break;
        }

        super.Nhap();
    }

    //Xuat
    @Override
    public void Xuat(){
        System.out.println("====THÔNG TIN KHÁCH HÀNG====");
        System.out.println("Mã KH: "+this.maKh);
        super.Xuat();
    }

    public String toDataString() {
        return "";
    }


    //Hien thi danh sach bat dong san cua khach hang
    public void hienThiBatDongSanSohuu() {
        Input.clearScreen();
        if (dsBatDongSanCuaKhach.isEmpty()) {
            System.out.println("Khách hàng " + this.maKh + " chưa sở hữu BĐS nào.");
            return;
        }

        System.out.println("\n=================================================== DANH SÁCH BẤT ĐỘNG SẢN CỦA KHÁCH HÀNG " + this.maKh + " ==================================================");

        System.out.printf("%-10s %-50s %-10s %-15s %-15s %-15s %-30s\n",
                "Mã BDS", "Địa chỉ", "DT(m2)", "Giá", "Thuế", "Loại", "Thông tin riêng");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");

        for (BatDongSan bds : dsBatDongSanCuaKhach) {

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

        System.out.println("==================================================================================================================================================\n");
    }


   @Override
    public void mua(BatDongSan bds) {
        System.out.println("===== GIAO DỊCH MUA BẤT ĐỘNG SẢN =====");

        System.out.println("Xác nhận thông tin BĐS mua:");
        bds.Xuat();

        this.dsBatDongSanCuaKhach.add(bds);

        Input.clearScreen();
        System.out.println("Khách hàng " + this.maKh + " đã MUA BĐS thành công!\n");
        
    }
    @Override
    public void ban(String maBDS) {
        System.out.println("===== GIAO DỊCH BÁN BẤT ĐỘNG SẢN =====");

        BatDongSan timThay = null;

        // Tìm BĐS theo mã
        for (BatDongSan bds : dsBatDongSanCuaKhach) {
            if (bds.getMaBDS().equalsIgnoreCase(maBDS)) {
                timThay = bds;
                break;
            }
        }

        if (timThay == null) {
            System.out.println("❌ Không tìm thấy BĐS có mã: " + maBDS);
            return;
        }

        System.out.println("✔ Khách hàng " + this.maKh + " đã ĐĂNG BÁN BĐS thành công!\n");
    }

    @Override
    public void choThue(String maBDS) {
        System.out.println("===== GIAO DỊCH CHO THUÊ BẤT ĐỘNG SẢN =====");

        BatDongSan timThay = null;

        // Tìm BĐS theo mã
        for (BatDongSan bds : dsBatDongSanCuaKhach) {
            if (bds.getMaBDS().equalsIgnoreCase(maBDS)) {
                timThay = bds;
                break;
            }
        }

        if (timThay == null) {
            System.out.println("Không tìm thấy BĐS có mã: " + maBDS);
            return;
        }

        System.out.println("Khách hàng " + this.maKh + " đã CHO THUÊ BĐS thành công!\n");
    }


    public BatDongSan timTheoMa(String ma) {
        for (BatDongSan bds : dsBatDongSanCuaKhach) {
            if (bds.getMaBDS().equalsIgnoreCase(ma)) return bds;
        }
        return null;
    }


    @Override
    public BatDongSan parseBDS(String line) {
        try {
            String[] a = line.split(";");
            String loai = a[0];

            String ma = a[1];
            DiaChi dc = new DiaChi(a[2], a[3], a[4]);
            float dienTich = Float.parseFloat(a[5]);
            double gia = Double.parseDouble(a[6]);

            switch (loai) {

                case "CanHo": {
                    int tang = Integer.parseInt(a[7]);
                    int soPhongNgu = Integer.parseInt(a[8]);
                    return new CanHo(ma, dc, dienTich, gia, tang, soPhongNgu);
                }

                case "NhaO": {
                    int soTang = Integer.parseInt(a[7]);
                    boolean sanVuon = Boolean.parseBoolean(a[8]);
                    return new NhaO(ma, dc, dienTich, gia, soTang, sanVuon);
                }

                case "DatNen": {
                    loaiDat ld = loaiDat.valueOf(a[7]);
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

            for (BatDongSan bds : dsBatDongSanCuaKhach) {
                bw.write(bds.toDataString());
                bw.newLine();
            }

        } catch (Exception e) {
            System.out.println("Lỗi ghi file BĐS: " + e.getMessage());
        }
    }

    @Override
    public void docFileBDS(String path) {
        dsBatDongSanCuaKhach.clear(); //xóa toàn bộ danh sách nhân viên trước khi đọc từ file

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                BatDongSan bds = parseBDS(line);

                if (bds != null) dsBatDongSanCuaKhach.add(bds);
            }

        } catch (Exception e) {
            System.out.println("Lỗi đọc file BĐS: " + e.getMessage());
        }
    }

    public String getMaKh() {
        return maKh;
    }
    public void setMaKh(String maKh) {
        this.maKh = maKh;
    }
}
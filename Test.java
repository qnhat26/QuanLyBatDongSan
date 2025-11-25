package CongTyBatDongSan;

public class Test {

    private static CongTy congTy = new CongTy();

    public static void main(String[] args) {
        while (true) {
            Input.clearScreen();
            System.out.println("========= MENU CHÍNH - CÔNG TY BĐS =========");
            System.out.println("1. Quản lý Công ty (Nhân viên, Khách hàng)");
            System.out.println("2. Quản lý Bất Động Sản (Giao cho Nhân viên)");
            System.out.println("3. Thực hiện Giao dịch (Khách hàng thực hiện)");
            System.out.println("4. Xem BĐS theo Nhân viên");
            System.out.println("5. Xem BĐS theo Khách hàng");
            System.out.println("0. Thoát chương trình");
            System.out.println("============================================");

            int luaChon = Input.gioiHanNhap("Mời bạn chọn: ", 0, 5);
            Input.clearScreen();

            switch (luaChon) {
                case 1:
                    menuQuanLyCongTy();
                    break;
                case 2:
                    menuQuanLyBatDongSan();
                    break;
                case 3:
                    menuGiaoDich();
                    break;
                case 4:
                    menuHienThiBDSTheoNV();
                    break;
                case 5:
                    menuHienThiBDSTheoKH();
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình. Tạm biệt!");
                    System.exit(0);
                    break;
            }
        }
    }

    public static void menuQuanLyCongTy() {
        while (true) {
            congTy.docFileKhachHang("KhachHang.txt");
            congTy.docFileNhanVien("NhanVien.txt");
            Input.clearScreen();
            System.out.println("--- Menu Quản lý Công ty ---");
            System.out.println("1. Thêm Nhân viên");
            System.out.println("2. Thêm Khách hàng");
            System.out.println("3. Hiển thị tất cả Nhân viên");
            System.out.println("4. Hiển thị tất cả Khách hàng");
            System.out.println("5. Xóa Nhân viên / Khách hàng");
            System.out.println("6. Tìm kiếm Nhân viên");
            System.out.println("7. Tìm kiếm Khách hàng");
            System.out.println("0. Quay lại Menu chính");

            int chon = Input.gioiHanNhap("Mời bạn chọn: ", 0, 7);
            Input.clearScreen();

            char close=' ';
            switch (chon) {
                case 1:
                    themNhanVien();
                    Input.clearScreen();
                    System.out.println("Thêm nhân viên thành công!");
                    break;
                case 2:
                    themKhachHang();
                    Input.clearScreen();
                    System.out.println("Thêm khách hàng thành công!");
                    break;
                case 3:
                    congTy.hienThiDanhSachNhanVien();
                    break;
                case 4:
                    congTy.hienThiDanhSachKhachHang();
                    break;
                case 5:
                    while (close!='0') {
                        System.out.println("Nhập mã (NV hoặc KH) cần xóa: ");
                        String maXoa = Input.sc.nextLine().toUpperCase();
                        congTy.xoa(maXoa);
                        congTy.ghiFileNV("NhanVien.txt");
                        System.out.print("\nNhập '0' để thoát: ");
                        close=Input.sc.nextLine().toUpperCase().charAt(0);
                    }
                    break;
                case 6:
                    while (close!='0') {
                        System.out.print("Nhập mã NV cần tìm: ");
                        String maNv = Input.sc.nextLine();
                        congTy.timNhanVienTheoMa(maNv);
                        System.out.print("\nNhập '0' để thoát: ");
                        close=Input.sc.nextLine().toUpperCase().charAt(0);
                        Input.clearScreen();
                    }
                    break;
                case 7:
                while (close!='0') {
                        System.out.print("Nhập mã KH cần tìm: ");
                        String maKh = Input.sc.nextLine();
                        congTy.timKhachHangTheoMa(maKh); 
                        System.out.print("\nNhập 0 để thoát: ");
                        close=Input.sc.nextLine().toUpperCase().charAt(0);
                        Input.clearScreen();
                    }
                    break;
                case 0:
                    return;
            }
        }
    }

    public static void themNhanVien() {
        System.out.println("Chọn loại nhân viên:\n1. Kinh Doanh\n2. Môi Giới");
        int loaiNv = Input.gioiHanNhap("Mời chọn: ", 1, 2);
        Input.clearScreen();

        NhanVien nv;
        if (loaiNv == 1) {
            nv = new NhanVienKinhDoanh();
        } else {
            nv = new NhanVienMoiGioi();
        }
        
        nv.Nhap();
        congTy.themNhanVien(nv);
        congTy.ghiFileNV("NhanVien.txt");
    }

    public static void themKhachHang() {
        System.out.println("Chọn loại khách hàng: \n1. Cá Nhân \n2. Doanh Nghiệp");
        int loaiKh = Input.gioiHanNhap("Mời chọn: ", 1, 2);
        Input.clearScreen();

        KhachHang kh;
        if (loaiKh == 1) {
            kh = new KhachHangCaNhan();
        } else {
            kh = new KhachHangDoanhNghiep();
        }

        kh.Nhap();
        congTy.themKhachHang(kh);
        congTy.ghiFileKH("KhachHang.txt");
    }

    public static BatDongSan taoVaNhapBDS() {
        System.out.println("Chọn loại BĐS: \n1. Căn Hộ \n2. Đất Nền \n3. Nhà Ở");
        int loaiBds = Input.gioiHanNhap("Mời chọn: ", 1, 3);
        Input.clearScreen();

        BatDongSan bds;
        switch (loaiBds) {
            case 1:
                bds = new CanHo();
                break;
            case 2:
                bds = new DatNen();
                break;
            case 3:
            default:
                bds = new NhaO();
                break;
        }
        
        bds.Nhap();
        return bds;
    }


    public static void menuQuanLyBatDongSan() {
        System.out.println("--- Menu Quản lý Bất Động Sản (Nhân Viên) ---");
        congTy.docFileNhanVien("NhanVien.txt");

        //Tìm nhân viên muốn thêm BDS
        System.out.print("Nhập mã Nhân viên để quản lý BĐS: ");
        String maNv = Input.sc.nextLine().toUpperCase();
        
        NhanVien nv = congTy.timNhanVienTheoMa(maNv);
        if (nv == null) {
            System.out.println("\nLỗi: Không tìm thấy nhân viên với mã " + maNv);
            System.out.println("\nNhấn Enter để quay lại menu chính...");
            Input.sc.nextLine();
            return;//Nếu không có thoát hàm
        }
        
        Input.clearScreen();
        System.out.println("Đã đăng nhập với tư cách NV: " + nv.getHoTen());
        System.out.println("\nNhấn Enter để tiếp tục...");
        Input.sc.nextLine();
        Input.clearScreen();

        while(true) {
            System.out.println("--- Quản lý BĐS của NV " + maNv + " ---");
            System.out.println("1. Thêm BĐS (Nhân viên tự nhập)");
            System.out.println("2. Xóa BĐS");
            System.out.println("3. Xem danh sách BĐS đang quản lý");
            System.out.println("0. Quay lại");
            
            int chon = Input.gioiHanNhap("Mời bạn chọn: ", 0, 3);
            Input.clearScreen();

            switch(chon) {
                case 1:
                    BatDongSan bdsMoi = taoVaNhapBDS();
                    nv.docFileBDS("BatDongSanNV_"+maNv+".txt");
                    nv.them(bdsMoi);
                    nv.ghiFileBDS("BatDongSanNV_"+maNv+".txt");
                    break;
                case 2:
                    nv.docFileBDS("BatDongSanNV_"+maNv+".txt");
                    nv.hienThiBatDongSanQuanLy();
                    System.out.print("Nhập mã BĐS cần xóa: ");
                    String maBdsXoa = Input.sc.nextLine();
                    nv.xoa(maBdsXoa);
                    break;
                case 3:
                    nv.docFileBDS("BatDongSanNV_"+maNv+".txt");
                    nv.hienThiBatDongSanQuanLy();
                    System.out.println("Nhấn Enter để trở lại...");
                    Input.sc.nextLine();
                    Input.clearScreen();
                    break;
                case 0:
                    return;
            }
        }
    }

    public static void menuGiaoDich() {
        System.out.println("--- Menu Giao Dịch (Khách Hàng) ---");
        congTy.docFileKhachHang("KhachHang.txt");

        // Tìm mã khách hàng
        System.out.print("Nhập mã Khách hàng (KH) thực hiện giao dịch: ");
        String maKh = Input.sc.nextLine().toUpperCase();
        KhachHang kh = congTy.timKhachHangTheoMa(maKh);
        Input.clearScreen();

        if (kh == null) {
            System.out.println("Lỗi: Không tìm thấy khách hàng với mã " + maKh);
            System.out.println("Nhấn Enter để quay lại...");
            Input.sc.nextLine();
            return;
        }

        System.out.println("Đã đăng nhập với tư cách KH: " + kh.getHoTen());
        System.out.println("\nNhấn Enter để tiếp tục...");
        Input.sc.nextLine();
        Input.clearScreen();

        System.out.println("Chọn giao dịch: \n1. Mua BĐS \n2. Đăng bán BĐS \n3. Đăng cho thuê BĐS");
        int loaiGd = Input.gioiHanNhap("Mời chọn: ", 1, 3);
        Input.clearScreen();

        if (loaiGd == 1) {

            // Tìm nhân viên đang quản lý BĐS
            congTy.docFileNhanVien("NhanVien.txt");
            System.out.print("Nhập mã Nhân viên (NV) quản lý BĐS bạn muốn mua: ");
            String maNv = Input.sc.nextLine().toUpperCase();
            NhanVien nv = congTy.timNhanVienTheoMa(maNv);
            Input.clearScreen();

            if (nv == null) {
                System.out.println("Lỗi: Không tìm thấy nhân viên!");
                Input.sc.nextLine();
                return;
            }

            nv.docFileBDS("BatDongSanNV_" + maNv + ".txt");
            nv.hienThiBatDongSanQuanLy();

            System.out.print("Nhập mã BĐS bạn muốn mua: ");
            String maBdsMua = Input.sc.nextLine().toUpperCase();

            BatDongSan bdsCanMua = nv.tim(maBdsMua);

            if (bdsCanMua == null) {
                System.out.println("Lỗi: NV không quản lý BĐS có mã: " + maBdsMua);
                return;
            }

            // Thực hiện mua
            kh.mua(bdsCanMua);
            kh.ghiFileBDS("BatDongSanKH_" + maKh + ".txt");

            nv.xoa(maBdsMua);
            nv.ghiFileBDS("BatDongSanNV_" + maNv + ".txt");

            Input.clearScreen();
            System.out.println("Mua thành công!");
            Input.sc.nextLine();
            return;
        }

        // 2 & 3. ĐĂNG BÁN / ĐĂNG CHO THUÊ BĐS

        kh.docFileBDS("BatDongSanKH_" + maKh + ".txt");

        System.out.println("DANH SÁCH BĐS BẠN ĐANG SỞ HỮU:");
        kh.hienThiBatDongSanSohuu();

        System.out.print("Nhập mã BĐS bạn muốn ");
        if (loaiGd == 2) System.out.print("đăng bán: ");
        else System.out.print("đăng cho thuê: ");

        String maBdsNhap = Input.sc.nextLine().toUpperCase();

        // Thực hiện giao dịch
        if (loaiGd == 2) {
            kh.ban(maBdsNhap);
        } else {
            kh.choThue(maBdsNhap);
        }

        kh.ghiFileBDS("BatDongSanKH_" + maKh + ".txt");

        // Gán nhân viên quản lý giao dịch này
        System.out.print("Nhập mã Nhân viên (NV) sẽ quản lý giao dịch này: ");
        String maNv = Input.sc.nextLine().toUpperCase();
        NhanVien nv = congTy.timNhanVienTheoMa(maNv);

        if (nv == null) {
            System.out.println("Không tìm thấy NV. Giao dịch không được gán NV!");
        } else {
            nv.docFileBDS("BatDongSanNV_" + maNv + ".txt");

            BatDongSan bds = kh.timTheoMa(maBdsNhap); // tìm lại BĐS sau khi giao dịch
            if (bds != null) {
                nv.them(bds);
                nv.ghiFileBDS("BatDongSanNV_" + maNv + ".txt");
            }

            System.out.println("Giao dịch được gán cho NV: " + nv.getHoTen());
        }

        System.out.println("\nGiao dịch hoàn tất!");
    }


    public static void menuHienThiBDSTheoNV() {
        congTy.docFileNhanVien("NhanVien.txt");
        System.out.println("--- Xem BĐS theo Nhân Viên ---");
        System.out.print("Nhập mã Nhân viên (NV): ");
        String maNv = Input.sc.nextLine().toUpperCase();
        Input.clearScreen();

        NhanVien nv = congTy.timNhanVienTheoMa(maNv);
        Input.clearScreen();
        if (nv == null) {
            System.out.println("Lỗi: Không tìm thấy nhân viên!");
            System.out.println("\nNhấn Enter để quay lại menu chính...");
            Input.sc.nextLine();
            return;
        }

        nv.docFileBDS("BatDongSanNV_"+maNv+".txt");
        nv.hienThiBatDongSanQuanLy();
        System.out.println("\nNhấn Enter để quay lại menu chính...");
        Input.sc.nextLine();
    }

    public static void menuHienThiBDSTheoKH() {
        congTy.docFileKhachHang("KhachHang.txt");
        System.out.println("--- Xem BĐS theo Khách Hàng ---");
        System.out.print("Nhập mã Khách hàng (KH): ");
        String maKh = Input.sc.nextLine();
        Input.clearScreen();

        KhachHang kh = congTy.timKhachHangTheoMa(maKh);
        Input.clearScreen();
        if (kh == null) {
            System.out.println("Lỗi: Không tìm thấy khách hàng!");
            System.out.println("\nNhấn Enter để quay lại menu chính...");
            Input.sc.nextLine();
            return;
        }

        kh.docFileBDS("BatDongSanKH_"+maKh+".txt");
        kh.hienThiBatDongSanSohuu(); 
        System.out.println("\nNhấn Enter để quay lại menu chính...");
        Input.sc.nextLine();
    }
}
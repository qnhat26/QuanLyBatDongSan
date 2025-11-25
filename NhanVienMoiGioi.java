package CongTyBatDongSan;

public class NhanVienMoiGioi extends NhanVien{
    private int soKhachGioiThieu;
    private double tienHoaHongMoiKhach;

    public NhanVienMoiGioi(){}

    public NhanVienMoiGioi(String hoTen, String soDienThoai, DiaChi diaChi,
                            String maNv, double luongCoBan,
                            int soKhachGioiThieu, double tienHoaHongMoiKhach) {
        super(hoTen, soDienThoai, diaChi, maNv, luongCoBan);
        this.soKhachGioiThieu = soKhachGioiThieu;
        this.tienHoaHongMoiKhach = tienHoaHongMoiKhach;
    }
   
    @Override
    public void Nhap() {
        super.Nhap();

        System.out.print("Số khách giới thiệu: ");
        this.soKhachGioiThieu=Integer.parseInt(Input.sc.nextLine());

        System.out.print("Hoa hồng mỗi khách: ");
        this.tienHoaHongMoiKhach=Double.parseDouble(Input.sc.nextLine());
    }

    @Override
    public void Xuat() {
        super.Xuat();

        System.out.println("Số khách giới thiệu: "+this.soKhachGioiThieu);

        System.out.println("Hoa hồng mỗi khách: "+this.tienHoaHongMoiKhach);

        System.out.println("Lương: "+tinhLuong());
    }

    @Override
    public String toDataString() {
        return "NhanVienMoiGioi;"
                + maNv + ";"
                + hoTen + ";"
                + soDienThoai + ";"
                + diaChi.toDataString() + ";"
                + luongCoBan + ";"
                + soKhachGioiThieu + ";"
                + tienHoaHongMoiKhach;
    }

    public void them(BatDongSan bds) {
        super.them(bds);
    }

    public void xoa(String maBDS) {
        super.xoa(maBDS);
    }

    public BatDongSan tim(String maBDS) {
        return super.tim(maBDS);
    }

    @Override
    public double tinhLuong() {
        return luongCoBan + soKhachGioiThieu * tienHoaHongMoiKhach;
    }

    public int getSoKhachGioiThieu() {
        return soKhachGioiThieu;
    }
    public double getTienHoaHongMoiKhach() {
        return tienHoaHongMoiKhach;
    }

    public void setSoKhachGioiThieu(int soKhachGioiThieu) {
        this.soKhachGioiThieu = soKhachGioiThieu;
    }
    public void setTienHoaHongMoiKhach(double tienHoaHongMoiKhach) {
        this.tienHoaHongMoiKhach = tienHoaHongMoiKhach;
    }
}


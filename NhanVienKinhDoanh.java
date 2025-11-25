package CongTyBatDongSan;

public class NhanVienKinhDoanh extends NhanVien{
    //thuoc tinh
    private int soGiaoDich;
    private double hoaHong;

    //constructor
    public NhanVienKinhDoanh(){}
    public NhanVienKinhDoanh(String hoTen, String soDienThoai, DiaChi diaChi,
                            String maNv, double luongCoBan,
                                int soGiaoDich, double hoaHong) {
        super(hoTen, soDienThoai, diaChi, maNv, luongCoBan);
        this.soGiaoDich = soGiaoDich;
        this.hoaHong = hoaHong;
    }
    
    //Nhap
    @Override
    public void Nhap() {
        super.Nhap();

        System.out.print("Số giao dịch: ");
        this.soGiaoDich=Integer.parseInt(sc.nextLine());

        System.out.print("Hoa hồng: ");
        this.hoaHong=Double.parseDouble(sc.nextLine());
    }

    //Xuat
    @Override
    public void Xuat() {
        super.Xuat();
        
        System.out.println("Số giao dịch: "+this.soGiaoDich);

        System.out.println("Hoa hồng: "+this.hoaHong);
        System.out.println("Lương: "+tinhLuong());
    }
    
    @Override
    public String toDataString() {
        return "NhanVienKinhDoanh;"
                + maNv + ";"
                + hoTen + ";"
                + soDienThoai + ";"
                + diaChi.toDataString() + ";"
                + luongCoBan + ";"
                + soGiaoDich + ";"
                + hoaHong;
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
        return luongCoBan + soGiaoDich * hoaHong;
    }

    public int getSoGiaoDich() {
        return soGiaoDich;
    }
    public double getHoaHong() {
        return hoaHong;
    }

    public void setSoGiaoDich(int soGiaoDich) {
        this.soGiaoDich = soGiaoDich;
    }
    public void setHoaHong(double hoaHong) {
        this.hoaHong = hoaHong;
    }
    
}
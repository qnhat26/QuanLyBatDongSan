package CongTyBatDongSan;

public class KhachHangDoanhNghiep extends KhachHang{
    //thuoc tinh
    private String maSoThue;
    private String tenCongTy;
    private String linhVucHoatDong;

    //constructor
    public KhachHangDoanhNghiep(){}
    public KhachHangDoanhNghiep(String maKh, String tenKh, String soDienThoai, DiaChi diaChi, 
                                String maSoThue, String tenCongTy, String linhVucHoatDong) {
        super(maKh, tenCongTy, soDienThoai, diaChi);
        this.maSoThue = maSoThue;
        this.tenCongTy = tenCongTy;
        this.linhVucHoatDong = linhVucHoatDong;
    }

    //Nhap
    public void Nhap(){
        super.Nhap();
        System.out.print("Mã số thuế: ");
        this.maSoThue=Input.sc.nextLine();

        System.out.print("Tên công ty: ");
        this.tenCongTy=Input.sc.nextLine();
        String[] word=this.tenCongTy.split(" ");//Tách kí tự theo dấu cách
        StringBuilder tenCt =new StringBuilder();
        for(String w:word) {
            w=w.trim();//Xóa khoảng trống
            if (!w.isEmpty()) {
                tenCt.append(Character.toUpperCase(w.charAt(0)))//Thêm, viết hoa kí tự đầu tiên
                .append(w.substring(1).toLowerCase())
                .append(" ");
            }
        }
        this.tenCongTy=tenCt.toString().trim();

        System.out.print("Lĩnh vực hoạt động: ");
        this.linhVucHoatDong=Input.sc.nextLine();
        String[] word2=this.linhVucHoatDong.split(" ");//Tách kí tự theo dấu cách
        StringBuilder lvhd =new StringBuilder();
        for(String w:word) {
            w=w.trim();//Xóa khoảng trống
            if (!w.isEmpty()) {
                lvhd.append(Character.toUpperCase(w.charAt(0)))//Thêm, viết hoa kí tự đầu tiên
                .append(w.substring(1).toLowerCase())
                .append(" ");
            }
        }
        this.linhVucHoatDong=lvhd.toString().trim();
    }

    //Xuat
    public void Xuat(){
        super.Xuat();
        System.out.println("Mã số thuế: "+this.maSoThue);
        System.out.println("Tên công ty: "+this.tenCongTy);
        System.out.println("Lĩnh vực hoạt động: "+this.linhVucHoatDong);
    }

    @Override
    public String toDataString(){
        return "KhachHangDoanhNghiep;"
                + maKh + ";"
                + hoTen + ";"
                + soDienThoai + ";"
                + diaChi.toDataString() + ";"
                + maSoThue + ";"
                + tenCongTy + ";"
                + linhVucHoatDong;
    }

    public String getMaSoThue() {
        return maSoThue;
    }
    public String getTenCongTy() {
        return tenCongTy;
    }
    public String getLinhVucHoatDong() {
        return linhVucHoatDong;
    }
    public void setLinhVucHoatDong(String linhVucHoatDong) {
        this.linhVucHoatDong = linhVucHoatDong;
    }
    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }
    public void setTenCongTy(String tenCongTy) {
        this.tenCongTy = tenCongTy;
    }
    
}



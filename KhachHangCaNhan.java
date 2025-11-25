package CongTyBatDongSan;

public class KhachHangCaNhan extends KhachHang {
    //Thuoc tinh
    private String cccd;
    private String ngheNghiep;

    //Constructor
    public KhachHangCaNhan(){}
    public KhachHangCaNhan(String maKh, String hoTen, String soDienThoai, DiaChi diaChi,
                            String cccd, String ngheNghiep) {
        super(maKh, hoTen, soDienThoai, diaChi);
        this.cccd = cccd;
        this.ngheNghiep = ngheNghiep;
    }

    //Nhap
    @Override
    public void Nhap(){
        super.Nhap();
        System.out.print("Số cccd: ");
        this.cccd=Input.sc.nextLine();

        System.out.print("Nghề nghiệp: ");
        this.ngheNghiep=Input.sc.nextLine();
        String[] word=this.ngheNghiep.split(" ");//Tách kí tự theo dấu cách
        StringBuilder ngheNghiep =new StringBuilder();
        for(String w:word) {
            w=w.trim();//Xóa khoảng trống
            if (!w.isEmpty()) {
                ngheNghiep.append(Character.toUpperCase(w.charAt(0)))//Thêm, viết hoa kí tự đầu tiên
                .append(w.substring(1).toLowerCase())
                .append(" ");
            }
        }
        this.ngheNghiep=ngheNghiep.toString().trim();
    }

    //Xuat
    @Override
    public void Xuat(){
        super.Xuat();
        System.out.println("Số cccd: "+this.cccd);
        System.out.println("Nghề nghiệp: "+this.ngheNghiep);
    }

    @Override
    public String toDataString(){
        return "KhachHangCaNhan;"
                + maKh + ";"
                + hoTen + ";"
                + soDienThoai + ";"
                + diaChi.toDataString() + ";"
                + cccd + ";"
                + ngheNghiep;
    }

    public void hienThiBatDongSanSohuu(){
        super.hienThiBatDongSanSohuu();
    }
    public void mua(BatDongSan bds){
        super.mua(bds);
    }

    public void ban(String maBDS){
        super.ban(null);
    }

    public void choThue(String maBDS){
        super.choThue(maBDS);
    }
    public String getCccd() {
        return cccd;
    }
    public String getNgheNghiep() {
        return ngheNghiep;
    }
    public void setCccd(String cccd) {
        this.cccd = cccd;
    }
    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }
}



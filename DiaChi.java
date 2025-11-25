package CongTyBatDongSan;

public class DiaChi {
    //Thuoc tinh
    private String duong;
    private String phuongXa;
    private String tinh;

    //Constructor
    public DiaChi(){}
    public DiaChi(String duong, String phuongXa, String tinh){
        this.duong=duong;
        this.phuongXa=phuongXa;
        this.tinh=tinh;
    }

    //Nhap
    public void Nhap(){
        System.out.print("Số nhà - Tên đường: ");
        this.duong=Input.sc.nextLine();
        String[] duong=this.duong.split(" ");//Tách kí tự theo dấu cách
        StringBuilder dr =new StringBuilder();
        for(String w:duong) {
            w=w.trim();//Xóa khoảng trống
            if (!w.isEmpty()) {
                dr.append(Character.toUpperCase(w.charAt(0)))//Thêm, viết hoa kí tự đầu tiên
                .append(w.substring(1).toLowerCase())
                .append(" ");
            }
        }
        this.duong=dr.toString().trim();

        System.out.print("Phường/Xã: ");
        this.phuongXa=Input.sc.nextLine();
        String[] phuongxa=this.phuongXa.split(" ");//Tách kí tự theo dấu cách
        StringBuilder pxr =new StringBuilder();
        for(String w:phuongxa) {
            w=w.trim();//Xóa khoảng trống
            if (!w.isEmpty()) {
                pxr.append(Character.toUpperCase(w.charAt(0)))//Thêm, viết hoa kí tự đầu tiên
                .append(w.substring(1).toLowerCase())
                .append(" ");
            }
        }
        this.phuongXa=pxr.toString().trim();

        System.out.print("Tỉnh/Thành phố: ");
        this.tinh=Input.sc.nextLine();
        String[] tinh=this.tinh.split(" ");//Tách kí tự theo dấu cách
        StringBuilder tr =new StringBuilder();
        for(String w:tinh) {
            w=w.trim();//Xóa khoảng trống
            if (!w.isEmpty()) {
                tr.append(Character.toUpperCase(w.charAt(0)))//Thêm, viết hoa kí tự đầu tiên
                .append(w.substring(1).toLowerCase())
                .append(" ");
            }
        }
        this.tinh=tr.toString().trim();
    }

    //Xuat
    public void Xuat(){
        System.out.println("Số nhà - Tên đường: "+this.duong);
        System.out.println("Phường/Xã: "+this.phuongXa);
        System.out.println("Tỉnh/Thành phố: "+this.tinh);
    }
    public String toDataString() {
        return duong + ";" + phuongXa +  ";" + tinh;
    }


    //Getter
    public String getDuong() {
        return duong;
    }
    public String getPhuongXa() {
        return phuongXa;
    }
    public String getTinh() {
        return tinh;
    }

    //Setter
    public void setDuong(String duong) {
        this.duong = duong;
    }
    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }
    public void setTinh(String tinh) {
        this.tinh = tinh;
    }
}
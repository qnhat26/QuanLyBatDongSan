package CongTyBatDongSan;

import java.util.Scanner;

public abstract class Nguoi {
    //Thuoc tinh
    protected String hoTen;
    protected String soDienThoai;
    protected DiaChi diaChi=new DiaChi();
    protected Scanner sc=new Scanner(System.in);

    //Constructor
    public Nguoi(){}
    public Nguoi(String hoTen, String soDienThoai, DiaChi diaChi){
        this.hoTen=hoTen;
        this.soDienThoai=soDienThoai;
        this.diaChi=diaChi;
    }

    //Nhap
    public void Nhap(){
        System.out.print("Họ tên: ");
        this.hoTen=Input.sc.nextLine();

        String[] word=this.hoTen.split(" ");//Tách kí tự theo dấu cách
        StringBuilder result =new StringBuilder();
        for(String w:word) {
            w=w.trim();//Xóa khoảng trống
            if (!w.isEmpty()) {
                result.append(Character.toUpperCase(w.charAt(0)))//Thêm, viết hoa kí tự đầu tiên
                .append(w.substring(1).toLowerCase())
                .append(" ");
            }
        }
        this.hoTen=result.toString().trim();

        System.out.print("Số điện thoại: ");
        this.soDienThoai=Input.sc.nextLine();

        System.out.println("Địa chỉ: ");
        this.diaChi.Nhap();
    }

    //Xuat
    public void Xuat(){
        System.out.println("Họ tên: "+this.hoTen);

        System.out.println("Số điện thoại: "+this.soDienThoai);
        
        System.out.println("Địa chỉ: ");
        this.diaChi.Xuat();
    }

    //Getter
    public String getHoTen() {
        return hoTen;
    }
    public String getSdt() {
        return soDienThoai;
    }
    public DiaChi getDc() {
        return diaChi;
    }

    //Setter
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public void setSdt(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    public void setDc(DiaChi diaChi) {
        this.diaChi = diaChi;
    }
}
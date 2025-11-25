package CongTyBatDongSan;

public enum loaiDat {
    DatO("Đất ở", 0.02),
    DatNongNghiep("Đất nông nghiệp", 0.01),
    DatCongNgiep("Đất công nghiệp", 0.03),
    DatDuLich("Đất du lịch", 0.04);

    private String ten;
    private double thue;

    //comment 
    loaiDat(String ten, double thue) {
        this.ten = ten;
        this.thue = thue;
    }

    //phương thức gọi
    public String getTen() { return ten; }
    public double getThue() { return thue; }
}

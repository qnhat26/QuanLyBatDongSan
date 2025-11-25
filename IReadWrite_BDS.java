package CongTyBatDongSan;

public interface IReadWrite_BDS {
    BatDongSan parseBDS(String line);
    void ghiFileBDS(String path);
    void docFileBDS(String path);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abc
 */
@Entity
@Table(name = "license_bts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicenseBts.findAll", query = "SELECT l FROM LicenseBts l"),
    @NamedQuery(name = "LicenseBts.findById", query = "SELECT l FROM LicenseBts l WHERE l.id = :id"),
    @NamedQuery(name = "LicenseBts.findByLicenseNum", query = "SELECT l FROM LicenseBts l WHERE l.licenseNum = :licenseNum"),
    @NamedQuery(name = "LicenseBts.findByPower", query = "SELECT l FROM LicenseBts l WHERE l.power = :power"),
    @NamedQuery(name = "LicenseBts.findByRadioLlTechLlHourLl", query = "SELECT l FROM LicenseBts l WHERE l.radioLlTechLlHourLl = :radioLlTechLlHourLl"),
    @NamedQuery(name = "LicenseBts.findByAntennaMode", query = "SELECT l FROM LicenseBts l WHERE l.antennaMode = :antennaMode"),
    @NamedQuery(name = "LicenseBts.findByAntennaSize", query = "SELECT l FROM LicenseBts l WHERE l.antennaSize = :antennaSize"),
    @NamedQuery(name = "LicenseBts.findByAntennaHeight", query = "SELECT l FROM LicenseBts l WHERE l.antennaHeight = :antennaHeight"),
    @NamedQuery(name = "LicenseBts.findByHuong", query = "SELECT l FROM LicenseBts l WHERE l.huong = :huong"),
    @NamedQuery(name = "LicenseBts.findByPhanCuc", query = "SELECT l FROM LicenseBts l WHERE l.phanCuc = :phanCuc"),
    @NamedQuery(name = "LicenseBts.findByDoRongBangTan", query = "SELECT l FROM LicenseBts l WHERE l.doRongBangTan = :doRongBangTan"),
    @NamedQuery(name = "LicenseBts.findByDoRongKenh", query = "SELECT l FROM LicenseBts l WHERE l.doRongKenh = :doRongKenh"),
    @NamedQuery(name = "LicenseBts.findByHoHieu", query = "SELECT l FROM LicenseBts l WHERE l.hoHieu = :hoHieu"),
    @NamedQuery(name = "LicenseBts.findByLongLat", query = "SELECT l FROM LicenseBts l WHERE l.longLat = :longLat"),
    @NamedQuery(name = "LicenseBts.findByLoaiNghiepVu", query = "SELECT l FROM LicenseBts l WHERE l.loaiNghiepVu = :loaiNghiepVu"),
    @NamedQuery(name = "LicenseBts.findByMauGiayPhep", query = "SELECT l FROM LicenseBts l WHERE l.mauGiayPhep = :mauGiayPhep"),
    @NamedQuery(name = "LicenseBts.findByMucDich", query = "SELECT l FROM LicenseBts l WHERE l.mucDich = :mucDich"),
    @NamedQuery(name = "LicenseBts.findByExpireDate", query = "SELECT l FROM LicenseBts l WHERE l.expireDate = :expireDate"),
    @NamedQuery(name = "LicenseBts.findBySignedDate", query = "SELECT l FROM LicenseBts l WHERE l.signedDate = :signedDate"),
    @NamedQuery(name = "LicenseBts.findByTanSo", query = "SELECT l FROM LicenseBts l WHERE l.tanSo = :tanSo"),
    @NamedQuery(name = "LicenseBts.findByTanSoSongMangHinh", query = "SELECT l FROM LicenseBts l WHERE l.tanSoSongMangHinh = :tanSoSongMangHinh"),
    @NamedQuery(name = "LicenseBts.findByTanSoSongMangTieng", query = "SELECT l FROM LicenseBts l WHERE l.tanSoSongMangTieng = :tanSoSongMangTieng"),
    @NamedQuery(name = "LicenseBts.findByCustomerName", query = "SELECT l FROM LicenseBts l WHERE l.customerName = :customerName"),
    @NamedQuery(name = "LicenseBts.findByCustomerAddr", query = "SELECT l FROM LicenseBts l WHERE l.customerAddr = :customerAddr"),
    @NamedQuery(name = "LicenseBts.findByTenMay", query = "SELECT l FROM LicenseBts l WHERE l.tenMay = :tenMay"),
    @NamedQuery(name = "LicenseBts.findBySoSanXuat", query = "SELECT l FROM LicenseBts l WHERE l.soSanXuat = :soSanXuat"),
    @NamedQuery(name = "LicenseBts.findByTinhThanhDatThietBi", query = "SELECT l FROM LicenseBts l WHERE l.tinhThanhDatThietBi = :tinhThanhDatThietBi"),
    @NamedQuery(name = "LicenseBts.findByGioHoatDong", query = "SELECT l FROM LicenseBts l WHERE l.gioHoatDong = :gioHoatDong"),
    @NamedQuery(name = "LicenseBts.findByPhamViHoatDong", query = "SELECT l FROM LicenseBts l WHERE l.phamViHoatDong = :phamViHoatDong"),
    @NamedQuery(name = "LicenseBts.findByDoiTuongLienLac", query = "SELECT l FROM LicenseBts l WHERE l.doiTuongLienLac = :doiTuongLienLac"),
    @NamedQuery(name = "LicenseBts.findByTinhTrang", query = "SELECT l FROM LicenseBts l WHERE l.tinhTrang = :tinhTrang"),
    @NamedQuery(name = "LicenseBts.findByTraPhi", query = "SELECT l FROM LicenseBts l WHERE l.traPhi = :traPhi"),
    @NamedQuery(name = "LicenseBts.findByNgayHieuCoLuc", query = "SELECT l FROM LicenseBts l WHERE l.ngayHieuCoLuc = :ngayHieuCoLuc"),
    @NamedQuery(name = "LicenseBts.findByLyDoNgung", query = "SELECT l FROM LicenseBts l WHERE l.lyDoNgung = :lyDoNgung"),
    @NamedQuery(name = "LicenseBts.findByMaHoSo", query = "SELECT l FROM LicenseBts l WHERE l.maHoSo = :maHoSo"),
    @NamedQuery(name = "LicenseBts.findByLoaiMang", query = "SELECT l FROM LicenseBts l WHERE l.loaiMang = :loaiMang"),
    @NamedQuery(name = "LicenseBts.findByTenTau", query = "SELECT l FROM LicenseBts l WHERE l.tenTau = :tenTau"),
    @NamedQuery(name = "LicenseBts.findBySoThietBi", query = "SELECT l FROM LicenseBts l WHERE l.soThietBi = :soThietBi"),
    @NamedQuery(name = "LicenseBts.findByDiaDiemDat", query = "SELECT l FROM LicenseBts l WHERE l.diaDiemDat = :diaDiemDat"),
    @NamedQuery(name = "LicenseBts.findByPhamViPhatSong", query = "SELECT l FROM LicenseBts l WHERE l.phamViPhatSong = :phamViPhatSong"),
    @NamedQuery(name = "LicenseBts.findByUserCode", query = "SELECT l FROM LicenseBts l WHERE l.userCode = :userCode"),
    @NamedQuery(name = "LicenseBts.findByNgayCapLanDau", query = "SELECT l FROM LicenseBts l WHERE l.ngayCapLanDau = :ngayCapLanDau")})
public class LicenseBts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "license_num")
    private String licenseNum;
    @Size(max = 500)
    @Column(name = "power")
    private String power;
    @Size(max = 3000)
    @Column(name = "radio_ll_tech_ll_hour_ll")
    private String radioLlTechLlHourLl;
    @Size(max = 100)
    @Column(name = "antenna_mode")
    private String antennaMode;
    @Size(max = 100)
    @Column(name = "antenna_size")
    private String antennaSize;
    @Size(max = 100)
    @Column(name = "antenna_height")
    private String antennaHeight;
    @Size(max = 100)
    @Column(name = "huong")
    private String huong;
    @Size(max = 100)
    @Column(name = "phan_cuc")
    private String phanCuc;
    @Size(max = 100)
    @Column(name = "do_rong_bang_tan")
    private String doRongBangTan;
    @Size(max = 100)
    @Column(name = "do_rong_kenh")
    private String doRongKenh;
    @Size(max = 2000)
    @Column(name = "ho_hieu")
    private String hoHieu;
    @Size(max = 3000)
    @Column(name = "long_lat")
    private String longLat;
    @Size(max = 100)
    @Column(name = "loai_nghiep_vu")
    private String loaiNghiepVu;
    @Size(max = 100)
    @Column(name = "mau_giay_phep")
    private String mauGiayPhep;
    @Size(max = 500)
    @Column(name = "muc_dich")
    private String mucDich;
    @Size(max = 100)
    @Column(name = "expire_date")
    private String expireDate;
    @Size(max = 100)
    @Column(name = "signed_date")
    private String signedDate;
    @Size(max = 500)
    @Column(name = "tan_so")
    private String tanSo;
    @Size(max = 100)
    @Column(name = "tan_so_song_mang_hinh")
    private String tanSoSongMangHinh;
    @Size(max = 100)
    @Column(name = "tan_so_song_mang_tieng")
    private String tanSoSongMangTieng;
    @Size(max = 500)
    @Column(name = "customer_name")
    private String customerName;
    @Size(max = 500)
    @Column(name = "customer_addr")
    private String customerAddr;
    @Size(max = 1500)
    @Column(name = "ten_may")
    private String tenMay;
    @Size(max = 100)
    @Column(name = "so_san_xuat")
    private String soSanXuat;
    @Size(max = 100)
    @Column(name = "tinh_thanh_dat_thiet_bi")
    private String tinhThanhDatThietBi;
    @Size(max = 100)
    @Column(name = "gio_hoat_dong")
    private String gioHoatDong;
    @Size(max = 100)
    @Column(name = "pham_vi_hoat_dong")
    private String phamViHoatDong;
    @Size(max = 100)
    @Column(name = "doi_tuong_lien_lac")
    private String doiTuongLienLac;
    @Size(max = 100)
    @Column(name = "tinh_trang")
    private String tinhTrang;
    @Size(max = 100)
    @Column(name = "tra_phi")
    private String traPhi;
    @Size(max = 100)
    @Column(name = "ngay_hieu_co_luc")
    private String ngayHieuCoLuc;
    @Size(max = 100)
    @Column(name = "ly_do_ngung")
    private String lyDoNgung;
    @Size(max = 100)
    @Column(name = "ma_ho_so")
    private String maHoSo;
    @Size(max = 100)
    @Column(name = "loai_mang")
    private String loaiMang;
    @Size(max = 100)
    @Column(name = "ten_tau")
    private String tenTau;
    @Size(max = 100)
    @Column(name = "so_thiet_bi")
    private String soThietBi;
    @Size(max = 100)
    @Column(name = "dia_diem_dat")
    private String diaDiemDat;
    @Size(max = 500)
    @Column(name = "pham_vi_phat_song")
    private String phamViPhatSong;
    @Size(max = 100)
    @Column(name = "user_code")
    private String userCode;
    @Size(max = 100)
    @Column(name = "ngay_cap_lan_dau")
    private String ngayCapLanDau;

    public LicenseBts() {
    }

    public LicenseBts(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getRadioLlTechLlHourLl() {
        return radioLlTechLlHourLl;
    }

    public void setRadioLlTechLlHourLl(String radioLlTechLlHourLl) {
        this.radioLlTechLlHourLl = radioLlTechLlHourLl;
    }

    public String getAntennaMode() {
        return antennaMode;
    }

    public void setAntennaMode(String antennaMode) {
        this.antennaMode = antennaMode;
    }

    public String getAntennaSize() {
        return antennaSize;
    }

    public void setAntennaSize(String antennaSize) {
        this.antennaSize = antennaSize;
    }

    public String getAntennaHeight() {
        return antennaHeight;
    }

    public void setAntennaHeight(String antennaHeight) {
        this.antennaHeight = antennaHeight;
    }

    public String getHuong() {
        return huong;
    }

    public void setHuong(String huong) {
        this.huong = huong;
    }

    public String getPhanCuc() {
        return phanCuc;
    }

    public void setPhanCuc(String phanCuc) {
        this.phanCuc = phanCuc;
    }

    public String getDoRongBangTan() {
        return doRongBangTan;
    }

    public void setDoRongBangTan(String doRongBangTan) {
        this.doRongBangTan = doRongBangTan;
    }

    public String getDoRongKenh() {
        return doRongKenh;
    }

    public void setDoRongKenh(String doRongKenh) {
        this.doRongKenh = doRongKenh;
    }

    public String getHoHieu() {
        return hoHieu;
    }

    public void setHoHieu(String hoHieu) {
        this.hoHieu = hoHieu;
    }

    public String getLongLat() {
        return longLat;
    }

    public void setLongLat(String longLat) {
        this.longLat = longLat;
    }

    public String getLoaiNghiepVu() {
        return loaiNghiepVu;
    }

    public void setLoaiNghiepVu(String loaiNghiepVu) {
        this.loaiNghiepVu = loaiNghiepVu;
    }

    public String getMauGiayPhep() {
        return mauGiayPhep;
    }

    public void setMauGiayPhep(String mauGiayPhep) {
        this.mauGiayPhep = mauGiayPhep;
    }

    public String getMucDich() {
        return mucDich;
    }

    public void setMucDich(String mucDich) {
        this.mucDich = mucDich;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(String signedDate) {
        this.signedDate = signedDate;
    }

    public String getTanSo() {
        return tanSo;
    }

    public void setTanSo(String tanSo) {
        this.tanSo = tanSo;
    }

    public String getTanSoSongMangHinh() {
        return tanSoSongMangHinh;
    }

    public void setTanSoSongMangHinh(String tanSoSongMangHinh) {
        this.tanSoSongMangHinh = tanSoSongMangHinh;
    }

    public String getTanSoSongMangTieng() {
        return tanSoSongMangTieng;
    }

    public void setTanSoSongMangTieng(String tanSoSongMangTieng) {
        this.tanSoSongMangTieng = tanSoSongMangTieng;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddr() {
        return customerAddr;
    }

    public void setCustomerAddr(String customerAddr) {
        this.customerAddr = customerAddr;
    }

    public String getTenMay() {
        return tenMay;
    }

    public void setTenMay(String tenMay) {
        this.tenMay = tenMay;
    }

    public String getSoSanXuat() {
        return soSanXuat;
    }

    public void setSoSanXuat(String soSanXuat) {
        this.soSanXuat = soSanXuat;
    }

    public String getTinhThanhDatThietBi() {
        return tinhThanhDatThietBi;
    }

    public void setTinhThanhDatThietBi(String tinhThanhDatThietBi) {
        this.tinhThanhDatThietBi = tinhThanhDatThietBi;
    }

    public String getGioHoatDong() {
        return gioHoatDong;
    }

    public void setGioHoatDong(String gioHoatDong) {
        this.gioHoatDong = gioHoatDong;
    }

    public String getPhamViHoatDong() {
        return phamViHoatDong;
    }

    public void setPhamViHoatDong(String phamViHoatDong) {
        this.phamViHoatDong = phamViHoatDong;
    }

    public String getDoiTuongLienLac() {
        return doiTuongLienLac;
    }

    public void setDoiTuongLienLac(String doiTuongLienLac) {
        this.doiTuongLienLac = doiTuongLienLac;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getTraPhi() {
        return traPhi;
    }

    public void setTraPhi(String traPhi) {
        this.traPhi = traPhi;
    }

    public String getNgayHieuCoLuc() {
        return ngayHieuCoLuc;
    }

    public void setNgayHieuCoLuc(String ngayHieuCoLuc) {
        this.ngayHieuCoLuc = ngayHieuCoLuc;
    }

    public String getLyDoNgung() {
        return lyDoNgung;
    }

    public void setLyDoNgung(String lyDoNgung) {
        this.lyDoNgung = lyDoNgung;
    }

    public String getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(String maHoSo) {
        this.maHoSo = maHoSo;
    }

    public String getLoaiMang() {
        return loaiMang;
    }

    public void setLoaiMang(String loaiMang) {
        this.loaiMang = loaiMang;
    }

    public String getTenTau() {
        return tenTau;
    }

    public void setTenTau(String tenTau) {
        this.tenTau = tenTau;
    }

    public String getSoThietBi() {
        return soThietBi;
    }

    public void setSoThietBi(String soThietBi) {
        this.soThietBi = soThietBi;
    }

    public String getDiaDiemDat() {
        return diaDiemDat;
    }

    public void setDiaDiemDat(String diaDiemDat) {
        this.diaDiemDat = diaDiemDat;
    }

    public String getPhamViPhatSong() {
        return phamViPhatSong;
    }

    public void setPhamViPhatSong(String phamViPhatSong) {
        this.phamViPhatSong = phamViPhatSong;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getNgayCapLanDau() {
        return ngayCapLanDau;
    }

    public void setNgayCapLanDau(String ngayCapLanDau) {
        this.ngayCapLanDau = ngayCapLanDau;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicenseBts)) {
            return false;
        }
        LicenseBts other = (LicenseBts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LicenseBts[ id=" + id + " ]";
    }
    
}

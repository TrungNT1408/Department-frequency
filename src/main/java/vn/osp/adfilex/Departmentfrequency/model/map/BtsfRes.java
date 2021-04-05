/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.model.map;


import vn.osp.adfilex.Departmentfrequency.entity.Bts;
import vn.osp.adfilex.Departmentfrequency.entity.Btsf;
import vn.osp.adfilex.Departmentfrequency.entity.Cell;
import vn.osp.adfilex.Departmentfrequency.entity.CellNoise;
import vn.osp.adfilex.Departmentfrequency.model.CellInfoRes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author abc
 */
public class BtsfRes implements Serializable {
    private Double lat;
    private Double lon;
    private String typeBts;
    private int status;
    private String telco;
    private long btsId;

    List<BtsCellMapInfo> btsCellMapInfos;


    public BtsfRes() {
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }


    public static List<BtsfRes> fromBtsf(List<Btsf> btsfs) {
        List<BtsfRes> btsfResList = new ArrayList<>();
        for (Btsf btsf : btsfs) {
            BtsfRes btsfRes = new BtsfRes();
            btsfRes.setLat(btsf.getLat());
            btsfRes.setLon(btsf.getLon());
            btsfResList.add(btsfRes);
        }

        return btsfResList;
    }

    public static List<BtsfRes> fromBts(List<Bts> btss) {
        List<BtsfRes> btsfResList = new ArrayList<>();
        for (Bts bts : btss) {
            BtsfRes btsfRes = new BtsfRes();
            btsfRes.setLat(bts.getLat());
            btsfRes.setLon(bts.getLon());
            btsfResList.add(btsfRes);
        }

        return btsfResList;
    }

    public static List<BtsfRes> fromCellNoise(List<CellNoise> cellNoises) {
        List<BtsfRes> btsfResList = new ArrayList<>();
        for (CellNoise cellNoise : cellNoises) {
            try {
                BtsfRes btsfRes = new BtsfRes();
//                Cell cell

                btsfRes.setLat(Double.parseDouble(cellNoise.getLat()));
                btsfRes.setLon(Double.parseDouble(cellNoise.getLon()));
                btsfResList.add(btsfRes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return btsfResList;
    }

    public String getTypeBts() {
        return typeBts;
    }

    public void setTypeBts(String typeBts) {
        this.typeBts = typeBts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTelco() {
        return telco;
    }

    public void setTelco(String telco) {
        this.telco = telco;
    }

    public long getBtsId() {
        return btsId;
    }

    public void setBtsId(long btsId) {
        this.btsId = btsId;
    }

    public List<BtsCellMapInfo> getBtsCellMapInfos() {
        return btsCellMapInfos;
    }

    public void setBtsCellMapInfos(List<BtsCellMapInfo> btsCellMapInfos) {
        this.btsCellMapInfos = btsCellMapInfos;
    }
}

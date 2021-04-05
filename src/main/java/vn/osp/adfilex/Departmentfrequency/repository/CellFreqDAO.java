package vn.osp.adfilex.Departmentfrequency.repository;


import vn.osp.adfilex.Departmentfrequency.entity.CellFreq;

import java.util.Date;
import java.util.List;

public interface CellFreqDAO {

    CellFreq getCellFreq(long cellFreqId);
    List<CellFreq> getCellFreq(String cellId);

    /**
     * @param cellId: Cell ID
     * @param hour:   hour of day: 0-23
     * @param date:   <b>MM/dd/yyyy</b> . Ex 1/7/2021
     * @return
     */
    CellFreq getCellFreqNow(String cellId, int hour, String date);
    CellFreq getCellFreqNoOrder(String cellId, int hour, String date);

    List<CellFreq> getAllNoiseCell(int hour, String date, double rtwpNoise);

    List<CellFreq> getHourNoise(String cellId, Date startDate);

    List<CellFreq> getCellFreqs(Date from, Date to);
}

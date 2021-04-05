package vn.osp.adfilex.Departmentfrequency.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.osp.adfilex.Departmentfrequency.constants.ArfmConstants;
import vn.osp.adfilex.Departmentfrequency.entity.*;
import vn.osp.adfilex.Departmentfrequency.model.*;
import vn.osp.adfilex.Departmentfrequency.model.map.*;
import vn.osp.adfilex.Departmentfrequency.repository.*;
import vn.osp.adfilex.Departmentfrequency.utils.ArfmUtils;
import vn.osp.adfilex.Departmentfrequency.utils.StandardDeviationSample;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DashboardServices {

    private final static Logger LOG = LoggerFactory.getLogger(DashboardServices.class);

    @Autowired
    private BtsfDAO btsfDAO;

    @Autowired
    private CellNoiseDAO cellNoiseDAO;


    @Autowired
    private BtsCellDAO btsCellDao;

    @Autowired
    private CellDAO cellDao;


    @Autowired
    private CellFreqDAO cellFreqDao;

    @Autowired
    private HistoryCellNoiseDAO historyCellNoiseDAO;

    private static Map<String, List<BtsfRes>> CACHE = new HashMap<>();

    private static int HOUR_LAST_UPDATE = -1;


    private Map<String, Cell> getCellFromListCellFreq(List<CellFreq> cellFreqs) {

        Map<String, Cell> collect = new ConcurrentHashMap<>();
        cellFreqs.parallelStream().forEach(cellFreq -> {
            if (!collect.containsKey(cellFreq.getCellId())) {
                Cell cell = cellDao.getCell(cellFreq.getCellId());

                if (cell != null) {
                    collect.put(cell.getCellId(), cell);
                }
            }
        });

        return collect;
    }

    public Object getNoiseOverview(DashboardDTO dashboardDTO) {

        final DashboardDTO dbDTONormalize = dashboardDTONormalizeRtwp(dashboardDTO);

        FilterMap filterMap = new FilterMap();

        filterMap.setTelco(dbDTONormalize.getTelcos());
        filterMap.setProvince(dbDTONormalize.getProvince());

//        List<Bts> btss = btsfDAO.listBts(filterMap);

        Date from = new Date();
        Date to = new Date();
        List<CellFreq> cellFreqs = cellFreqDao.getCellFreqs(from, to);

        Map<String, Cell> cellMapInfo = getCellFromListCellFreq(cellFreqs);


        Map<String, List<Double>> cellFreqMap = getCellFreqMap(cellFreqs);

        List<DashboardDTORes> dtoRess = new ArrayList<>();


        cellFreqMap.entrySet().parallelStream().forEach(entry -> {
            StandardDeviationSample stDevs = new StandardDeviationSample(entry.getValue());

            boolean isAdd = true;
            if (dbDTONormalize.getUl_rssi_max() != null) {
                if (stDevs.getMax() > dbDTONormalize.getUl_rssi_max()) {
                    isAdd = false;
                }
            }

            if (dbDTONormalize.getUl_rssi_min() != null) {
                if (stDevs.getMin() < dbDTONormalize.getUl_rssi_min()) {
                    isAdd = false;
                }
            }

            if (dbDTONormalize.getUl_rssi_mean_from() != null) {
                if (dashboardDTO.getUl_rssi_mean_from() > stDevs.getMean()) {
                    isAdd = false;
                }
            }


            if (dbDTONormalize.getUl_rssi_mean_to() != null) {

                if (dbDTONormalize.getUl_rssi_mean_to() < stDevs.getMean()) {
                    isAdd = false;
                }
            }

            if (dbDTONormalize.getFreqs() != null) {

                if (dbDTONormalize.getUl_rssi_mean_to() != (long) (stDevs.countIf(StandardDeviationSample.MODE_LARGER, ArfmConstants.RTWP_NOISE_STANDARD) / 2)) {
                    isAdd = false;
                }
            }



            if (isAdd) {
                Cell cell = cellMapInfo.get(entry.getKey());
                isAdd = isValidTech(cell, dbDTONormalize);

                if (isAdd) {
                    isAdd = isValidFreq(cell, dbDTONormalize);

                    if (isAdd) {
                        isAdd = isValidTelco(cell, dbDTONormalize);
                        if (isAdd) {
                            DashboardDTORes dtoRes = buildDashboardDTORes(cell, stDevs);
                            synchronized (dtoRess) {
                                dtoRess.add(dtoRes);
                            }
                        }
                    }
                }

                //TODO: Add data
            }


        });


        return dtoRess;
    }


    private DashboardDTORes buildDashboardDTORes(Cell cell, StandardDeviationSample stdev) {

        DashboardDTORes dashboardDTORes = new DashboardDTORes();
        dashboardDTORes.setCellName(cell.getCellId());
        dashboardDTORes.setTelco(cell.getBtsId().getTelco());
        dashboardDTORes.setProvince(cell.getBtsId().getProvince());
        dashboardDTORes.setTech(cell.getTechType());
        dashboardDTORes.setFreq(cell.getFreq());
        dashboardDTORes.setUl_rssi_max(stdev.getMax() - 13);
        dashboardDTORes.setUl_rssi_mean(stdev.getMean() - 13);
        dashboardDTORes.setNumber_noise((long) (stdev.countIf(StandardDeviationSample.MODE_LARGER, ArfmConstants.RTWP_NOISE_STANDARD) / 2));
//        dashboardDTORes.setDate_notify();
//        dashboardDTORes.setDate_expire();
        dashboardDTORes.setResult("");
        dashboardDTORes.setLat(cell.getBtsId().getLat());
        dashboardDTORes.setLon(cell.getBtsId().getLon());
//        dashboardDTORes.setFile_notify("upload/FileUploadAdfilex/CV nhiễu QLB Miền Bắc.pdf");
        dashboardDTORes.setNum_notify("272/TB-QLBMB"); // chua lam cho nay
        dashboardDTORes.setFile_notify("upload/FileUploadAdfilex/Bản đồ (1).png");

        return dashboardDTORes;
    }

    private boolean isValidFreq(Cell cell, DashboardDTO dbDTONormalize) {
        if (dbDTONormalize.getFreqs() != null && !dbDTONormalize.getFreqs().isEmpty()) {

            return dbDTONormalize.getFreqs().contains(ArfmUtils.getIdxFreq(cell.getFreq()));
        }

        return true;

    }

    private boolean isValidTech(Cell cell, DashboardDTO dashboardDTO) {

        if (dashboardDTO.getTechs() != null && !dashboardDTO.getTechs().isEmpty()) {
            return dashboardDTO.getTechs().contains(ArfmUtils.getIdxTech(cell.getTechType()));
        }

        return true;
    }

    private boolean isValidTelco(Cell cell, DashboardDTO dbDTONormalize) {
        if (dbDTONormalize.getTelcos() != null && !dbDTONormalize.getTelcos().isEmpty()) {

            return dbDTONormalize.getTelcos().contains(ArfmUtils.getIdxTelco(cell.getBtsId().getTelco()));
        }

        return true;

    }

    private Map<String, List<Double>> getCellFreqMap(List<CellFreq> cellFreqs) {
        Map<String, List<Double>> result = new HashMap<>();
        for (CellFreq cellFreq : cellFreqs) {
            String cellId = cellFreq.getCellId();
            List<Double> temp = new ArrayList<>();
            if (result.containsKey(cellId)) {
                temp = result.get(cellId);
            }
            temp.add(cellFreq.getRtwp());
            result.put(cellId, temp);
        }


        return result;
    }

    public DashboardDTO dashboardDTONormalizeRtwp(DashboardDTO dashboardDTO) {

        if (dashboardDTO.getUl_rssi_max() != null) {
            dashboardDTO.setUl_rssi_max(dashboardDTO.getUl_rssi_max() + 13);
        }

        if (dashboardDTO.getUl_rssi_min() != null) {
            dashboardDTO.setUl_rssi_min(dashboardDTO.getUl_rssi_min() + 13);
        }

        if (dashboardDTO.getUl_rssi_mean_from() != null) {
            dashboardDTO.setUl_rssi_mean_from(dashboardDTO.getUl_rssi_mean_from() + 13);
        }

        if (dashboardDTO.getUl_rssi_mean_to() != null) {
            dashboardDTO.setUl_rssi_mean_to(dashboardDTO.getUl_rssi_mean_to() + 13);
        }
        return dashboardDTO;
    }
}

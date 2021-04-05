package vn.osp.adfilex.Departmentfrequency.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.osp.adfilex.Departmentfrequency.constants.ArfmConstants;
import vn.osp.adfilex.Departmentfrequency.entity.*;
import vn.osp.adfilex.Departmentfrequency.model.Location;
import vn.osp.adfilex.Departmentfrequency.model.MapOverviewRes;
import vn.osp.adfilex.Departmentfrequency.model.map.*;
import vn.osp.adfilex.Departmentfrequency.repository.*;
import vn.osp.adfilex.Departmentfrequency.utils.CellNoiseUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class CellNoiseServices {

    private final static Logger LOG = LoggerFactory.getLogger(CellNoiseServices.class);

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


    public void scanCellNoise() {
        List<Bts> btss = btsfDAO.getAll();

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        List<String> scanDays = Arrays.asList("3/8/2021", "3/9/2021", "3/10/2021", "3/11/2021", "3/12/2021", "3/13/2021", "3/14/2021", "3/15/2021");
//        List<String> scanDays = Arrays.asList("3/8/2021");
        for (String scanDay : scanDays) {
            Map<String, Double> ranking = new HashMap<>();
            for (int i = 0; i < 24; i++) {
                hour = i;

//                List<CellFreq> celNoiseFreq = cellFreqDao.getAllNoiseCell(hour, ArfmConstants.DATE_FIX, ArfmConstants.RTWP_NOISE_STANDARD);
                List<CellFreq> celNoiseFreq = cellFreqDao.getAllNoiseCell(hour, scanDay, ArfmConstants.RTWP_NOISE_STANDARD);

                for (CellFreq cellFreq : celNoiseFreq) {

                    Cell cell = cellDao.getCell(cellFreq.getCellId());

                    double point = 1;
                    if (ranking.containsKey(cell.getCellId())) {
                        point = point + ranking.get(cell.getCellId());
                    }
                    ranking.put(cell.getCellId(), point);

                    List<Cell> cells = getAllNearCell(cell, btss);

                    List<Cell> allNearCellNoise = getAllCellNoise(cells);

                    if (allNearCellNoise.size() > 2 || point == 5) {
                        // This is cell Noise
                        //TODO: Can giai phuong trinh duong thang o day
                        createCellNoise(cell, cellFreq);
                    }

                }

            }

        }


//        List<CellFreq> celNoiseFreq = cellFreqDao.getAllNoiseCell(hour, ArfmConstants.DATE_FIX, ArfmConstants.RTWP_NOISE_STANDARD);
//
//        for (CellFreq cellFreq : celNoiseFreq) {
//
//            Cell cell = cellDao.getCell(cellFreq.getCellId());
//            List<Cell> cells = getAllNearCell(cell, btss);
//
//            List<Cell> allNearCellNoise = getAllCellNoise(cells);
//
//            if (allNearCellNoise.size() > 2) {
//                // This is cell Noise
//                //TODO: Can giai phuong trinh duong thang o day
//                createCellNoise(cell, cellFreq);
//            }
//
//        }

    }


    private CellNoise createCellNoise(Cell cell, CellFreq cf) {
        cellNoiseDAO.getCellNoise(cell.getCellId());
        CellNoise cellNoise = new CellNoise();

        cellNoise.setCellId(cell.getCellId());
        cellNoise.setFreq(cell.getFreq());
        cellNoise.setAzimuth(cell.getAzimuth().toString());
        cellNoise.setTilt(cell.getTilt());
        cellNoise.setRtwp(cf.getRtwp().toString());
        cellNoise.setStatus("NOISE");
        cellNoise.setSys(1);
        cellNoise.setCreateTime(new Date());
        cellNoise.setUpdateTime(cellNoise.getCreateTime());
//        cellNoise.setStartDate(new Date());
        //3/14/2021
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            cellNoise.setStartDate(sdf.parse(cf.getDateTime()));
        } catch (ParseException e) {
            e.printStackTrace();
            cellNoise.setStartDate(new Date());
        }

        //
        LOG.info("INSERT CELLNOISE: {} - {} - {}", cellNoise.getCellId(), cellNoise.getFreq(), cellNoise.getAzimuth());

        if (cellNoiseDAO.getCellNoise(cellNoise.getCellId(), cellNoise.getStartDate()) == null) {
            cellNoiseDAO.insertDb(cellNoise);
        }


        return cellNoise;
    }

    private double getNoiseVector(List<Cell> cells, CellNoise cellNoise, CellNoiseInfo cellNoiseInfo) {
        try {

//            Math

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    private List<Bts> getNearBtss(Location point, List<Bts> btss) {
        try {
            Map<String, Double> collect = new ConcurrentHashMap<>();
            Map<String, Bts> btsAllMap = new ConcurrentHashMap<>();

            for (Bts bts : btss) {
                btsAllMap.put(bts.getBtsId(), bts);
                Double dist = distance(point.getLat(), bts.getLat(), point.getLon(), bts.getLon());
//                System.out.println(dist);
//                Double dist = distance(point.getLat(), point.getLon(), bts.getLat(), bts.getLon(),"D");
                if (dist < ArfmConstants.DISTANCE_NEARLY) {
                    collect.put(bts.getBtsId(), dist);
                }
            }

            Map<String, Double> result = collect.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));

            List<String> btsIds = new ArrayList<>();
            List<Bts> btsRes = new ArrayList<>();


            for (Map.Entry<String, Double> entry : result.entrySet()) {
                btsIds.add(entry.getKey());
                btsRes.add(btsAllMap.get(entry.getKey()));
                if (btsIds.size() > ArfmConstants.MAX_NEAR_POINT) {
                    break;
                }
            }

            LOG.info(btsIds.toString());
            List<Cell> listCellNoise = cellDao.getCells(btsIds.get(0));
            Cell cellNoised = new Cell();
//            List<Cell> listCellNoise = cellDao.getCells(btsIds.get(0));

            return btsRes;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    private List<Cell> getAllNearCell(Cell cell, List<Bts> btss) {
        Bts btsCell = cell.getBtsId();
        Location location = new Location();
        location.setLat(btsCell.getLat());
        location.setLon(btsCell.getLon());

        List<Bts> nearBtsIds = getNearBtss(location, btss);
        List<Cell> cells = new ArrayList<>();

        for (Bts nearBtsId : nearBtsIds) {
            cells.addAll(cellDao.getCells(cell.getTechType(), cell.getFreq(), nearBtsId.getBtsId()));
        }
        cells.addAll(cellDao.getCells(cell.getTechType(), cell.getFreq(), btsCell.getBtsId()));

        return cells;
    }

//    private List<Cell> getAllNearCell(Bts bts) {
//        List<Bts> btss = btsfDAO.getAll();
//
//        return getAllNearCell(bts, btss);
//    }

    private List<Cell> getAllCellNoise(List<Cell> cells) {

        List<Cell> cellNoises = new ArrayList<>();
        for (Cell cell : cells) {
            //TODO: Co dinh ngay
            CellFreq cellFreq = cellFreqDao.getCellFreqNow(cell.getCellId(), ArfmConstants.HOUR_FIX, ArfmConstants.DATE_FIX);
            if (CellNoiseUtils.nowIsCellNoise(cell, cellFreq)) {
                cellNoises.add(cell);
            }
        }
        return cellNoises;
    }


    public static double distance(double lat1, double lat2, double lon1, double lon2) {
        return distance(lat1, lat2, lon1, lon2, 0, 0);
    }

    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }


    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }

    public Object getCellFreqInfo(String cellId) {

        List<CellFreq> cellFreqs = cellFreqDao.getCellFreq(cellId);
        return cellFreqs;
    }

    public Object overview() {

        MapOverviewRes mapOverviewRes = new MapOverviewRes();

        TechOverview techOverview = getTechOverview();
        TelcoOverview telcoOverview = getTelcoOverview();
        StatusOverview statusOverview = getStatusOverview();
        SystemOverview systemOverview = getSystemOverview();
        mapOverviewRes.setStatusOverview(statusOverview);
        mapOverviewRes.setSystemOverview(systemOverview);
        mapOverviewRes.setTechOverview(techOverview);
        mapOverviewRes.setTelcoOverview(telcoOverview);


        return mapOverviewRes;
    }

    private SystemOverview getSystemOverview() {

        SystemOverview systemOverview = new SystemOverview();
        systemOverview.setTotal_5G_NR_2100(123);
        systemOverview.setTotal_5G_NR_2100(124);

        //TODO: 1
        return systemOverview;
    }

    private StatusOverview getStatusOverview() {

        StatusOverview statusOverview = new StatusOverview();
        statusOverview.setTotal_active(12);
        statusOverview.setTotal_interference_initiative(124);
        statusOverview.setTotal_interference_passive(125);

        //TODO: 2
        return statusOverview;
    }

    private TelcoOverview getTelcoOverview() {
        TelcoOverview telcoOverview = new TelcoOverview();
        telcoOverview.setTotal_bts_VIETTEL(btsfDAO.countTelco(ArfmConstants.Map_Telco_str.VIETTEL));
        telcoOverview.setTotal_bts_MOBIFONE(btsfDAO.countTelco(ArfmConstants.Map_Telco_str.MOBIFONE));
        telcoOverview.setTotal_bts_VNPT(btsfDAO.countTelco(ArfmConstants.Map_Telco_str.VNPT));
        telcoOverview.setTotal_bts_VIETNAMMOBILE(btsfDAO.countTelco(ArfmConstants.Map_Telco_str.VIETNAMMOBILE));
        telcoOverview.setTotal_bts_ARFM(btsfDAO.countTelco(ArfmConstants.Map_Telco_str.ARFM));
        return telcoOverview;
    }

    private TechOverview getTechOverview() {
        //TODO: 4
        TechOverview techOverview = new TechOverview();
        techOverview.setTotal_2G(200);
        techOverview.setTotal_3G(300);
        techOverview.setTotal_4G(400);
        techOverview.setTotal_5G(500);
        return techOverview;
    }
}

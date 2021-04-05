package vn.osp.adfilex.Departmentfrequency.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.osp.adfilex.Departmentfrequency.constants.AlphaDictionary;
import vn.osp.adfilex.Departmentfrequency.constants.ArfmConstants;
import vn.osp.adfilex.Departmentfrequency.entity.*;
import vn.osp.adfilex.Departmentfrequency.model.*;
import vn.osp.adfilex.Departmentfrequency.model.map.*;
import vn.osp.adfilex.Departmentfrequency.repository.*;
import vn.osp.adfilex.Departmentfrequency.utils.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class InterferenceMapServices {

    private final static Logger LOG = LoggerFactory.getLogger(InterferenceMapServices.class);

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
    private CellNoiseServices cellNoiseServices;

    @Autowired
    private HistoryCellNoiseDAO historyCellNoiseDAO;

    private static Map<String, List<BtsfRes>> CACHE = new HashMap<>();

    private static int HOUR_LAST_UPDATE = -1;

    public List<BtsfRes> getBTSs(FilterMap filter) {

        int now_hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        try {

            String search = "";
            List<Cell> cells = cellDao.getAllCell(filter);
            List<CellNoise> cellNoises = cellNoiseDAO.listCellNoise(filter);
            Set<String> cellIdNoiseTemp = new HashSet<>();
            for (CellNoise cellNoise : cellNoises) {
                cellIdNoiseTemp.add(cellNoise.getCellId());
            }

//            if (filter.getStatus() != null && !filter.getStatus().isEmpty()) {
//                return buildBtsRes(cellNoises, filter);
//            }

            List<Bts> btss = btsfDAO.listBts(filter);

            Set<BtsfRes> btsfRess = new HashSet<>();
            btss.parallelStream().forEach(bts -> {
                BtsfRes btsfRes = getBtsfRes(bts, filter, cellIdNoiseTemp);
                if (btsfRes != null) {
                    btsfRess.add(btsfRes);
                }
            });
            return new ArrayList<>(btsfRess);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<InterferenceMapRes> btsGetNoiseCells() {

        List<InterferenceMapRes> imrs = new ArrayList<>();

        Set<String> cellIds = new HashSet<>();

        List<CellNoise> cellNoises = cellNoiseDAO.listCellNoise(new FilterMap());
        if (cellNoises != null) {
            for (CellNoise cellNoise : cellNoises) {
                Cell cell = cellDao.getCell(cellNoise.getCellId());
                if (cell != null) {

                    if (cellIds.contains(cell.getCellId())) {
                        continue;
                    }
                    cellIds.add(cell.getCellId());

                    InterferenceMapRes imr = new InterferenceMapRes();
                    imr.setCellId(cell.getCellId());
                    imr.setDate(cell.getStartDate());
                    imr.setProvince(cell.getBtsId().getProvince());
                    imr.setTech(cell.getTechType());
                    imr.setType_noise(cellNoise.getCellNoiseInfoId() == null ? "AUTO" : "SUBSCRIBER");
                    imr.setTelco(cell.getBtsId().getTelco());

                    imr.setFreq(cell.getFreq());
                    imr.setLat(cell.getBtsId().getLat());
                    imr.setLon(cell.getBtsId().getLon());
                    imr.setNum_day_noise(cellNoise.getNumDayNoise());
                    imr.setTelco(cell.getBtsId().getTelco());
                    imr.setTelco(cell.getBtsId().getTelco());

                    imrs.add(imr);

                }
            }
        }


        return imrs;
    }


    private BtsfRes getBtsfRes(Bts bts, FilterMap filter, Set<String> cellNoiseTemp) {
        List<Cell> cells = cellDao.getCells(bts.getBtsId(), filter);
        if ("ARFM".equalsIgnoreCase(bts.getTelco())) {
            BtsfRes btsfRes = new BtsfRes();
            btsfRes.setLat(bts.getLat());
            btsfRes.setLon(bts.getLon());
            btsfRes.setTelco(bts.getTelco());
            btsfRes.setTypeBts(bts.getType());
            btsfRes.setStatus(1);
            return btsfRes;
        }

        if (cells == null || cells.isEmpty()) {
            return null;
        }

        TikTok tikTok = new TikTok();
        boolean isCellNoise = false;
        boolean isOnlyNoise = false;

        if (filter.getStatus() != null && !filter.getStatus().isEmpty()) {
            if (!filter.getStatus().contains(1) && (filter.getStatus().contains(2) || filter.getStatus().contains(3))) {
                isOnlyNoise = true;
            }
        }

        for (Cell cell : cells) {
            if (cellNoiseTemp.contains(cell.getCellId())) {
                isCellNoise = true;
                break;
            }
        }


        BtsfRes btsfRes = new BtsfRes();
        btsfRes.setLat(bts.getLat());
        btsfRes.setLon(bts.getLon());
        btsfRes.setTelco(bts.getTelco());
        btsfRes.setTypeBts(bts.getType());


        List<BtsCellMapInfo> btsCellMapInfos = new ArrayList<>();

        cells.parallelStream().forEach(cell -> {
            BtsCellMapInfo btsCellMapInfo = new BtsCellMapInfo();
            btsCellMapInfo.setAzimuth(cell.getAzimuth());
            btsCellMapInfo.setFreq(Double.parseDouble(cell.getFreq()));
//            btsCellMapInfo.setColor(CellNoiseUtils.getColor(cell.getCellId(),
//                    cellFreqDao.getCellFreqNow(cell.getCellId(), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), ArfmConstants.DATE_FIX)));


            CellFreq cellFreq = cellFreqDao.getCellFreqNow(cell.getCellId(), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), ArfmConstants.DATE_FIX);
            if (cellFreq != null) {
                btsCellMapInfo.setUl_rssi(cellFreq.getRtwp() - ArfmConstants.UL_RSSI_SIGMA);
            }

//            synchronized (btsCellMapInfos) {
            btsCellMapInfos.add(btsCellMapInfo);
//            }
        });
        btsfRes.setBtsCellMapInfos(btsCellMapInfos);

        if (isCellNoise) {
            btsfRes.setStatus(2);
        } else {
            btsfRes.setStatus(1);
            if (isOnlyNoise) {
                return null;
            }
        }
        return btsfRes;

    }

    private List<BtsfRes> buildBtsRes(List<CellNoise> cellNoises, FilterMap filter) {

        List<BtsfRes> btsfResList = new ArrayList<>();
        for (CellNoise cellNoise : cellNoises) {
            Cell cell = cellDao.getCell(cellNoise.getCellId());
            if (cell != null) {
                Bts bts = cell.getBtsId();


                if (filter.getTelco() != null && !filter.getTelco().isEmpty()) {
                    int idxTelco = ArfmUtils.getIdxTelco(bts.getTelco());
                    if (!filter.getTelco().contains(idxTelco)) {
                        continue;
                    }
                }

                if (filter.getTech() != null && !filter.getTech().isEmpty()) {
                    int idxTech = ArfmUtils.getIdxTech(cell.getTechType());
                    if (!filter.getTech().contains(idxTech)) {
                        continue;

                    }
                }

                BtsfRes btsfRes = new BtsfRes();
                btsfRes.setLat(bts.getLat());
                btsfRes.setLon(bts.getLon());
                btsfRes.setTelco(bts.getTelco());
                btsfRes.setTypeBts(bts.getType());
                btsfRes.setStatus(2);
                btsfResList.add(btsfRes);
            }
        }
        return btsfResList;
    }


    public Object getBTSOld(Location location) throws Exception {
        Bts bts = btsfDAO.getBTS(location);
        return getBTS(bts);
    }


    private BtsInfoRes getBTS(Bts bts) throws Exception {
        List<Cell> cells = cellDao.getCells(bts.getBtsId());
        List<CellDto> cellDtos = new ArrayList<>();

        for (Cell cell : cells) {
            CellDto cellDto;
            CellNoise cellNoise = cellNoiseDAO.getCellNoise(cell.getCellId());
            if (cellNoise != null) {

//                List<CellFreq> clusterNoise = new ArrayList<>();
                Map<CellFreq, CellFreq> clusterNoise = new HashMap<>();
                TikTok tikTok = new TikTok();
                tikTok.tick();

                List<Cell> cellSameTech = cellDao.getCells(cell.getTechType(), cell.getFreq(), cell.getBtsId().getBtsId());
                System.out.println("GET IN: " + tikTok.tok());

                tikTok.tick();
                Cell cellNext = getNextNoiseInBts(cellSameTech, cellNoise, clusterNoise);
                System.out.println("PROCESS IN: " + tikTok.tok());

                Double alpha = null;
                Double sigma = null;

                tikTok.tick();
                if (cellNext != null) {
//                    alpha = buildAlpha(cell, cellNext, clusterNoise);
                    List<Double> statistics = new ArrayList<>();
                    for (Map.Entry<CellFreq, CellFreq> entry : clusterNoise.entrySet()) {
                        Double alphaTemp = buildAlpha(cell, cellNext, entry.getKey(), entry.getValue());
                        statistics.add(alphaTemp);
                    }

                    try {
                        StandardDeviationSample sds = new StandardDeviationSample(statistics);
                        sigma = sds.getStDevSample();
                        alpha = sds.getMean();
                    } catch (Exception e) {
                        alpha = statistics.get(0);
                    }
                } else {
                    alpha = cell.getAzimuth();
                }
                System.out.println("ALPHA IN: " + tikTok.tok());
                cellDto = CellDto.fromCellNoise(cellNoise, cell, alpha, sigma);
            } else {
                cellDto = CellDto.fromCell(cell);
            }
            cellDtos.add(cellDto);
        }

        BtsInfoRes btsInfoRes = new BtsInfoRes();
        Location location = new Location();
        location.setLat(bts.getLat());
        location.setLon(bts.getLon());
        btsInfoRes.setLocation(location);
        btsInfoRes.setCellDtos(cellDtos);
        return btsInfoRes;
    }

    public Object getBTS(Location location) throws Exception {

        List<BtsInfoRes> btsfRes = new ArrayList<>();
        Bts bts = btsfDAO.getBTS(location);
        if (bts != null) {
            // add first bts
            btsfRes.add(getBTS(bts));
        }

        // add around bts
        List<Bts> allBts = btsfDAO.getAll();
//        List<Cell> cells = cellDao.getCells(bts.getBtsId());

        List<Bts> aroundBtss = getBtsAround(location, allBts, ArfmConstants.DISTANCE_NEARLY);

        for (Bts btsAround : aroundBtss) {
            btsfRes.add(getBTS(btsAround));
        }

        return btsfRes;
    }

    public static boolean ASC = true;
    public static boolean DESC = false;


    private double buildAlpha2(Cell cellNoise, Cell cellNext, Map<CellFreq, CellFreq> clusterNoise) {

        List<Double> statistics = new ArrayList<>();
        for (Map.Entry<CellFreq, CellFreq> entry : clusterNoise.entrySet()) {
            Double alpha = buildAlpha(cellNoise, cellNext, entry.getKey(), entry.getValue());
            statistics.add(alpha);
        }

        return 0;
    }

    private double buildAlpha(Cell cell1, Cell cell2, CellFreq cellFreqNoise1, CellFreq cellFreqNoise2) {
        Cell cellNoise;
        Cell cellNext;
        CellFreq cellFreqNoise;
        CellFreq cellFreqNext;

        if (cellFreqNoise1.getRtwp() >= cellFreqNoise2.getRtwp()) {
            cellNoise = cell1;
            cellNext = cell2;
            cellFreqNoise = cellFreqNoise1;
            cellFreqNext = cellFreqNoise2;
        } else {
            cellNoise = cell2;
            cellNext = cell1;
            cellFreqNoise = cellFreqNoise2;
            cellFreqNext = cellFreqNoise1;
        }

        return buildAlphaWithOrder(cellNoise, cellNext, cellFreqNoise, cellFreqNext);
    }

    private double buildAlphaWithOrder(Cell cellNoise, Cell cellNext, CellFreq cellFreqNoise, CellFreq cellFreqNext) {


//        Cell cellNoise1 , Cell cellNext, CellFreq cellFreqNoise, CellFreq cellFreqNext

        Map<Double, Double> dictionary = AlphaDictionary.BAND_WAREHOUSE.get(cellNoise.getFreq());
        if (dictionary == null) {
            dictionary = AlphaDictionary.BAND_900;
        }
        double alpha = 0;

        alpha = cellNoise.getAzimuth() + 5;

        Map<Double, Double> ranking = new HashMap<>();

//        double deltaRtwp = Math.abs(cellFreqDao.getCellFreqNow(cellNext.getCellId(), ArfmConstants.HOUR_FIX, ArfmConstants.DATE_FIX).getRtwp() -
//                cellFreqDao.getCellFreqNow(cellNoise.getCellId(), ArfmConstants.HOUR_FIX, ArfmConstants.DATE_FIX).getRtwp());

        double deltaRtwp = Math.abs(cellFreqNoise.getRtwp() - cellFreqNext.getRtwp());

        double deltaAziOrg = Math.abs(cellNext.getAzimuth() - cellNoise.getAzimuth());
        double deltaAzi = deltaAziOrg;
        if (deltaAzi > 180) {
            deltaAzi = 360 - deltaAziOrg;
        }

        double beta = 0;

        double total = Math.round(deltaAzi);
        double aziSmaller = cellNoise.getAzimuth() < cellNext.getAzimuth() ? cellNoise.getAzimuth() : cellNext.getAzimuth();

        for (int i = 0; i < total; i++) {
            alpha = i;
            beta = total - alpha;
            double result = Math.abs(dictionary.get(alpha) - dictionary.get(beta) - deltaRtwp);
            ranking.put(alpha, result);
        }

        Map<Double, Double> sortedMapAsc = ArfmUtils.sortByComparator(ranking, ASC);

        for (Map.Entry<Double, Double> doubleDoubleEntry : sortedMapAsc.entrySet()) {
            alpha = doubleDoubleEntry.getKey();
            beta = deltaAzi - alpha;
            if (cellNoise.getAzimuth() < cellNext.getAzimuth()) {
                if (alpha < beta) {
                    break;
                }
            } else {
                if (alpha >= beta) {
                    break;
                }
            }
        }


        double angleNoise = 0;

        if (deltaAziOrg < 180) {
            angleNoise = aziSmaller + alpha;
            if (angleNoise > 360) {
                angleNoise = angleNoise - 360;
            }
        } else {
            angleNoise = aziSmaller - alpha;
            if (angleNoise < 0) {
                angleNoise = 360 + angleNoise;
            }
        }

        return angleNoise;
    }

    public static AtomicInteger COUNTER = new AtomicInteger(0);

    private Cell getNextNoiseInBts(List<Cell> cells, CellNoise cellNoise, Map<CellFreq, CellFreq> clusterNoise) {

        TikTok tikTok = new TikTok();
        Map<String, Long> ranking = new ConcurrentHashMap<>();
        Map<Long, CellFreq> cellFreqCollect = new ConcurrentHashMap<>();

        Map<Long, Long> collectIds = new ConcurrentHashMap<>();

        tikTok.tick();
        List<CellFreq> cellFreqs = cellFreqDao.getHourNoise(cellNoise.getCellId(), cellNoise.getStartDate());

        System.out.println("GETHOURNOISE: " + tikTok.tok());


        cellFreqs.parallelStream().forEach(cellFreq -> {
            Map<Double, Double> temp = new ConcurrentHashMap<>();
            tikTok.tick();

            cells.parallelStream().forEach(cell -> {
                if (!cell.getCellId().equalsIgnoreCase(cellNoise.getCellId())) {
                    CellFreq cellFreqNext = cellFreqDao.getCellFreqNoOrder(cell.getCellId(), cellFreq.getHour(), cellFreq.getDateTime());
                    if (CellNoiseUtils.nowIsCellNoise(cell, cellFreqNext)) {
                        temp.put(Double.valueOf(cellFreqNext.getId()), cellFreqNext.getRtwp());

                        cellFreqCollect.put(cellFreq.getId(), cellFreq);
                        cellFreqCollect.put(cellFreqNext.getId(), cellFreqNext);

                        collectIds.put(cellFreqNext.getId(), cellFreq.getId());
                    }
                }
            });

            System.out.println("parallelStream: " + tikTok.tok());

            if (temp.size() > 0) {
                Map<Double, Double> sortMap = ArfmUtils.sortByComparator(temp, DESC);
                for (Map.Entry<Double, Double> doubleDoubleEntry : sortMap.entrySet()) {

                    CellFreq cellFreqMax = cellFreqCollect.get(doubleDoubleEntry.getKey().longValue());
                    long point = 1;
                    if (ranking.containsKey(cellFreqMax.getCellId())) {
                        point += ranking.get(cellFreqMax.getCellId());
                    }
                    ranking.put(cellFreqMax.getCellId(), point);
                }
            }
        });

//        for (CellFreq cellFreq : cellFreqs) {
//            Map<Double, Double> temp = new ConcurrentHashMap<>();
//            tikTok.tick();
//
//            cells.parallelStream().forEach(cell -> {
//                if (!cell.getCellId().equalsIgnoreCase(cellNoise.getCellId())) {
//                    CellFreq cellFreqNext = cellFreqDao.getCellFreqNoOrder(cell.getCellId(), cellFreq.getHour(), cellFreq.getDateTime());
//                    if (CellNoiseUtils.nowIsCellNoise(cell, cellFreqNext)) {
//                        temp.put(Double.valueOf(cellFreqNext.getId()), cellFreqNext.getRtwp());
//
//                        cellFreqCollect.put(cellFreq.getId(), cellFreq);
//                        cellFreqCollect.put(cellFreqNext.getId(), cellFreqNext);
//
//                        collectIds.put(cellFreqNext.getId(), cellFreq.getId());
//                    }
//                }
//            });
//
//            System.out.println("parallelStream: " + tikTok.tok() );
//
//            if (temp.size() > 0) {
//                Map<Double, Double> sortMap = ArfmUtils.sortByComparator(temp, DESC);
//                for (Map.Entry<Double, Double> doubleDoubleEntry : sortMap.entrySet()) {
//
//                    CellFreq cellFreqMax = cellFreqCollect.get(doubleDoubleEntry.getKey().longValue());
////                    CellFreq cellFreqMax = cellFreqDao.getCellFreq(doubleDoubleEntry.getKey().longValue());
////                    clusterNoise.add(cellFreq);
////                    clusterNoise.add(cellFreqMax);
////                    return cellDao.getCell(cellFreqMax.getCellId());
//
//                    long point = 1;
//                    if (ranking.containsKey(cellFreqMax.getCellId())) {
//                        point += ranking.get(cellFreqMax.getCellId());
//                    }
//                    ranking.put(cellFreqMax.getCellId(), point);
//                }
//            }
//        }

        if (ranking.size() > 0) {

            // ID cell nhieu
            tikTok.tick();
            String cellNextNoiseId = getKeyMaxValue(ranking);
            Cell noiseCellNext = cellDao.getCell(cellNextNoiseId);
            for (Map.Entry<Long, Long> entry : collectIds.entrySet()) {
                CellFreq cellFreqNoise = cellFreqCollect.get(entry.getKey());
                if (cellFreqNoise.getCellId().equalsIgnoreCase(cellNextNoiseId)) {
                    clusterNoise.put(cellFreqCollect.get(entry.getValue()), cellFreqNoise);
                }
            }

            System.out.println("END: " + tikTok.tok());

            return noiseCellNext;

        }


        return null;
    }

    private String getKeyMaxValue(Map<String, Long> collect) {

        long max = Long.MIN_VALUE;
        String keyMax = "";
        for (Map.Entry<String, Long> entry : collect.entrySet()) {
            if (entry.getValue() > max) {
                keyMax = entry.getKey();
            }
        }

        return keyMax;
    }

    private double getNoiseVector(List<Cell> cells, CellNoise cellNoise, CellNoiseInfo cellNoiseInfo) {
        try {

//            Math

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    private List<Bts> getBtsAround(Location point, List<Bts> btss, double distance) {
        try {
            Map<String, Double> collect = new ConcurrentHashMap<>();
            Map<String, Bts> mapBts = new HashMap<>();

            for (Bts bts : btss) {
                Double dist = MapUtil.distance(point.getLat(), bts.getLat(), point.getLon(), bts.getLon());
                if (dist < distance) {
//                if (dist < ArfmConstants.DISTANCE_NEARLY) {
                    collect.put(bts.getBtsId(), dist);
                    mapBts.put(bts.getBtsId(), bts);
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
                btsRes.add(mapBts.get(entry.getKey()));

                if (btsIds.size() > ArfmConstants.MAX_NEAR_POINT) {
                    break;
                }
            }

//            LOG.info(btsIds.toString());
//            List<Cell> listCellNoise = cellDao.getCells(btsIds.get(0));
//
//            Cell cellNoised = new Cell();
//
////            List<Cell> listCellNoise = cellDao.getCells(btsIds.get(0));
//
            return btsRes;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private void scanCellNoise() {
        List<Bts> btss = btsfDAO.getAll();


    }

    private List<Cell> getAllNearCell(Bts bts, List<Bts> btss) {
        Location location = new Location();
        location.setLat(bts.getLat());
        location.setLon(bts.getLon());

        List<Bts> nearBtss = getBtsAround(location, btss, ArfmConstants.DISTANCE_NEARLY);
        List<Cell> cells = new ArrayList<>();

        for (Bts nearBts : nearBtss) {
            cells.addAll(cellDao.getCells(nearBts.getBtsId()));
        }
        cells.addAll(cellDao.getCells(bts.getBtsId()));

        return cells;
    }

    private List<Cell> getAllNearCell(Bts bts) {
        List<Bts> btss = btsfDAO.getAll();

        return getAllNearCell(bts, btss);
    }

    private List<Cell> getAllCellNoise(List<Cell> cells) {

        List<Cell> cellNoises = new ArrayList<>();
        for (Cell cell : cells) {
            CellFreq cellFreq = cellFreqDao.getCellFreqNow(cell.getCellId(), ArfmConstants.HOUR_FIX, ArfmConstants.DATE_FIX);
            if (CellNoiseUtils.nowIsCellNoise(cell, cellFreq)) {
                cellNoises.add(cell);
            }
        }
        return cellNoises;
    }


    public Object getCellFreqInfo(String cellId) {

        List<CellFreq> cellFreqs = cellFreqDao.getCellFreq(cellId);

        List<CellFreqInfoRes> result = new ArrayList<>();

        for (CellFreq cellFreq : cellFreqs) {
            result.add(CellFreqInfoRes.toCellFreqInfoRes(cellFreq));
        }
        return result;
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


    public Object btsUpdateNoiseCell() {
        cellNoiseServices.scanCellNoise();
        return "OK";

    }

    public Object getCellHistory(String cellId) {
        List<HistoryCellNoise> historyCellNoises = historyCellNoiseDAO.getHistoryCellNoises(cellId);

        List<MapHistoryRes> response = new ArrayList<>();
        for (HistoryCellNoise historyCellNoise : historyCellNoises) {
            MapHistoryRes mapHistoryRes = new MapHistoryRes();
            mapHistoryRes.setId(historyCellNoise.getId());
            mapHistoryRes.setDate(historyCellNoise.getCreateDate());
            mapHistoryRes.setHandler(historyCellNoise.getProcessor());
            mapHistoryRes.setReason(historyCellNoise.getNoiseCause());
            mapHistoryRes.setSolution(historyCellNoise.getSolution());

            response.add(mapHistoryRes);
        }

        return response;

    }

    public Object getBtsInCircle(Circle circle) {
        List<Bts> btss = btsfDAO.getAll();

        List<Bts> aroundBtss = getBtsAround(circle.getCenter(), btss, circle.getRadius());
        List<BTSRes> btsRess = new ArrayList<>();

        for (Bts bts : aroundBtss) {

            List<Cell> cells = cellDao.getCells(bts.getBtsId());
            Set<String> techs = new HashSet<>();
            Set<String> freqs = new HashSet<>();
            for (Cell cell : cells) {
                techs.add(cell.getTechType());
                freqs.add(cell.getFreq());
            }

            BTSRes btsRes = new BTSRes();
            btsRes.setProvince(bts.getProvince());
            btsRes.setTelco(bts.getTelco());
            btsRes.setFreqs(new ArrayList<>(freqs));
            btsRes.setTechs(new ArrayList<>(techs));

            Location location = new Location();
            location.setLat(bts.getLat());
            location.setLon(bts.getLon());

            btsRes.setLocation(location);
            btsRes.setBtsId(bts.getBtsId());

            double distance = MapUtil.distance(circle.getCenter().getLat(), bts.getLat(), circle.getCenter().getLon(), bts.getLon());
            btsRes.setDistance(distance);

            btsRess.add(btsRes);

        }

        return btsRess;
    }
}

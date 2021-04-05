package vn.osp.adfilex.Departmentfrequency.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.osp.adfilex.Departmentfrequency.constants.ArfmConstants;
import vn.osp.adfilex.Departmentfrequency.entity.Cell;
import vn.osp.adfilex.Departmentfrequency.entity.CellFreq;

public class CellNoiseUtils {
    private final static Logger LOG = LoggerFactory.getLogger(CellNoiseUtils.class);

    public static double STANDARD_DELTA = 5;//dBm
    public static double STANDARD_RTWP_2G = -95;//dBm
    public static double STANDARD_RTWP_3G = -95;//dBm
    public static double STANDARD_RTWP_4G = -95;//dBm
    public static double STANDARD_RTWP_5G = -95;//dBm


    public static boolean nowIsCellNoise(Cell cell, CellFreq cellFreqNow){

        try {
            if (cellFreqNow ==null){
                return false;
            }
            String strTech = cell.getTechType();
            int iTech = ArfmUtils.getIdxTech(strTech);
            double rtwp = cellFreqNow.getRtwp();
            if (ArfmConstants.Map_Tech.TECH_2G == iTech){
                return rtwp - STANDARD_RTWP_2G >= 0 ;
            } else if (ArfmConstants.Map_Tech.TECH_3G == iTech){
                return rtwp - STANDARD_RTWP_3G >= 0 ;
            }else if (ArfmConstants.Map_Tech.TECH_4G == iTech){
                return rtwp - STANDARD_RTWP_4G >= 0 ;
            }else if (ArfmConstants.Map_Tech.TECH_5G == iTech){
                return rtwp - STANDARD_RTWP_5G >= 0 ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }


    public static int getColor(String cellId, CellFreq cellFreq) {
        if (cellFreq ==null){
            LOG.warn("Khong lay duoc tan so hien tai");
            return ArfmConstants.COLOR.GREEN;
        }

        if (cellFreq.getRtwp() >= -85){
            return ArfmConstants.COLOR.RED;
        } else if (cellFreq.getRtwp() <= -95){
            return ArfmConstants.COLOR.GREEN;
        } else{
            return ArfmConstants.COLOR.ORANGE;
        }
    }
}

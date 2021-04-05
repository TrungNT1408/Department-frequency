package vn.osp.adfilex.Departmentfrequency.core;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.osp.adfilex.Departmentfrequency.constants.ArfmConstants;
import vn.osp.adfilex.Departmentfrequency.entity.CellNoise;
import vn.osp.adfilex.Departmentfrequency.entity.CellNoiseInfo;
import vn.osp.adfilex.Departmentfrequency.entity.LicenseBts;
import vn.osp.adfilex.Departmentfrequency.model.InterferenceDTO;
import vn.osp.adfilex.Departmentfrequency.model.InterferenceFilterDTO;
import vn.osp.adfilex.Departmentfrequency.repository.CellNoiseDAO;
import vn.osp.adfilex.Departmentfrequency.repository.CellNoiseInfoDAO;
import vn.osp.adfilex.Departmentfrequency.repository.LicenseBTSDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InterferenceServices {

    @Autowired
    private CellNoiseDAO cellNoiseDAO;

    @Autowired
    private CellNoiseInfoDAO cellNoiseInfoDAO;

    @Autowired
    private LicenseBTSDAO licenseBTSDAO;

    public int isValidLicense(InterferenceDTO interferenceDTO) {

        String profileCode = interferenceDTO.getLicense_number();

        LicenseBts licenseBts = licenseBTSDAO.getLicenseBts(profileCode);

        if (licenseBts == null) {
            return ArfmConstants.LicenseCodeRes.NOT_FOUND;
        }

        Date expireDate = getDate(licenseBts.getExpireDate());

        if (expireDate == null) {
            return ArfmConstants.LicenseCodeRes.NOT_FOUND;
        } else {
            if (isExpired(expireDate)) {
                return ArfmConstants.LicenseCodeRes.EXPIRED;
            }
            return ArfmConstants.LicenseCodeRes.VALID;
        }
    }

    private boolean isExpired(Date expireDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(expireDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);


        return calendar.getTimeInMillis() < System.currentTimeMillis();

    }

    private Date getDate(String strDate) {
        //2/17/2022
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object register(InterferenceDTO interferenceDTO) {

        CellNoiseInfo cellNoiseInfo = cellNoiseInfoDAO.from(interferenceDTO);
        cellNoiseInfo = cellNoiseInfoDAO.insertDb(cellNoiseInfo);

        // Insert cellNoise
        CellNoise cellNoise = buildCellNoise(interferenceDTO, cellNoiseInfo);

        cellNoise = cellNoiseDAO.insertDb(cellNoise);


        return cellNoise.getId();
    }

    private CellNoise buildCellNoise(InterferenceDTO interferenceDTO, CellNoiseInfo cellNoiseInfo) {
        CellNoise cellNoise = new CellNoise();
        cellNoise.setCellId(interferenceDTO.getCell_id());
        cellNoise.setStatus(ArfmConstants.CellNoise.STATUS_NOISE);
        cellNoise.setCellNoiseInfoId(cellNoiseInfo);

        return cellNoise;
    }

    public List<InterferenceDTO> getInterferences(InterferenceFilterDTO ifd) {

        List<CellNoiseInfo> cellNoiseInfos = cellNoiseInfoDAO.getCellNoiseInfo(ifd);

        List<InterferenceDTO> interferenceDTOs = new ArrayList<>();
        for (CellNoiseInfo cellNoiseInfo : cellNoiseInfos) {
            InterferenceDTO interferenceDTO = buildInterfrenceDTO(cellNoiseInfo);
            if (interferenceDTO != null) {
                interferenceDTOs.add(interferenceDTO);
            }
        }

        return interferenceDTOs;
    }

    private InterferenceDTO buildInterfrenceDTO(CellNoiseInfo cellNoiseInfo) {

        try {


            InterferenceDTO interferenceDTO = new InterferenceDTO();
            interferenceDTO.setId(cellNoiseInfo.getId());
            interferenceDTO.setCreate_date(cellNoiseInfo.getCreateDate());

            interferenceDTO.setLicense_number(cellNoiseInfo.getLicenseNumber());
            interferenceDTO.setOrganisation_name(cellNoiseInfo.getOrganisationName());
            interferenceDTO.setOrganisation(cellNoiseInfo.getOrganisation());
            interferenceDTO.setPhone_number(cellNoiseInfo.getPhoneNumber());
            interferenceDTO.setMobile_phone(cellNoiseInfo.getMobilePhone());
            interferenceDTO.setEmail(cellNoiseInfo.getEmail());
            interferenceDTO.setRadio_name(cellNoiseInfo.getRadioName());
            interferenceDTO.setFreq_interf(cellNoiseInfo.getFreqInterf());
            interferenceDTO.setLocaltion(cellNoiseInfo.getLocaltion());
            interferenceDTO.setDirection_ranger(cellNoiseInfo.getDirectionRanger());
            interferenceDTO.setTime_interf(cellNoiseInfo.getTimeInterf());
            interferenceDTO.setHour_interf(cellNoiseInfo.getHourInterf());
            interferenceDTO.setLevel_affect(cellNoiseInfo.getLevelAffect());
            interferenceDTO.setPhenomena_interf(cellNoiseInfo.getPhenomenaInterf());
            interferenceDTO.setPhenomena_desc(cellNoiseInfo.getPhenomenaDesc());
            interferenceDTO.setAddInfo(cellNoiseInfo.getAddInfo());
            interferenceDTO.setReson_freq_interf(cellNoiseInfo.getResonFreqInterf());
            interferenceDTO.setIdent_cause_radio(cellNoiseInfo.getIdentCauseRadio());
            interferenceDTO.setLocation_radio_cause(cellNoiseInfo.getLocationRadioCause());
            interferenceDTO.setTime_statistic(cellNoiseInfo.getTimeStatistic());
            interferenceDTO.setProvince(cellNoiseInfo.getProvince());
            interferenceDTO.setCell_id(cellNoiseInfo.getCellId());
            interferenceDTO.setFreq(cellNoiseInfo.getFreq());
            if (cellNoiseInfo.getLon() != null) {
                interferenceDTO.setLon(cellNoiseInfo.getLon());
            }

            if (cellNoiseInfo.getLat() != null) {
                interferenceDTO.setLat(cellNoiseInfo.getLat());
            }
            interferenceDTO.setAzimuth(cellNoiseInfo.getAzimuth());
            interferenceDTO.setRtwp(cellNoiseInfo.getRtwp());
            interferenceDTO.setNum_day_interf(cellNoiseInfo.getNumDayInterf());
            interferenceDTO.setStatus(cellNoiseInfo.getStatus());
            interferenceDTO.setCause_info(cellNoiseInfo.getCauseInfo());
            interferenceDTO.setProcess_info(cellNoiseInfo.getProcessInfo());
            interferenceDTO.setNum_request(cellNoiseInfo.getNumRequest());
            interferenceDTO.setRequest_file(cellNoiseInfo.getRequestFile());

            if (StringUtils.isNotEmpty(cellNoiseInfo.getFiles())){
                interferenceDTO.setFiles(Arrays.asList(cellNoiseInfo.getFiles().split(ArfmConstants.FILE_SPLIT)));
            }

            return interferenceDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Object getInterference(long id) {

        CellNoise cellNoise = cellNoiseDAO.getCellNoiseFromId(id);

        CellNoiseInfo cellNoiseInfo = cellNoise.getCellNoiseInfoId();

        if (cellNoiseInfo != null) {
            return buildInterfrenceDTO(cellNoiseInfo);
        }

        return buildInterferenceDTO(cellNoise);
    }

    private InterferenceDTO buildInterferenceDTO(CellNoise cellNoise) {
        InterferenceDTO interferenceDTO = new InterferenceDTO();
        interferenceDTO.setId(cellNoise.getId());
        interferenceDTO.setCreate_date(cellNoise.getCreateTime());

//        interferenceDTO.setLicense_number(cellNoise.get());
//        interferenceDTO.setOrganisation_name(cellNoise.getOrganisationName());
//        interferenceDTO.setOrganisation(cellNoise.getOrganisation());
//        interferenceDTO.setPhone_number(cellNoise.getPhoneNumber());
//        interferenceDTO.setMobile_phone(cellNoise.getMobilePhone());
//        interferenceDTO.setEmail(cellNoise.getEmail());
//        interferenceDTO.setRadio_name(cellNoise.getRadioName());
//        interferenceDTO.setFreq_interf(cellNoise.getFreqInterf());
        interferenceDTO.setLocaltion(cellNoise.getProvince());
//        interferenceDTO.setDirection_ranger(cellNoise.getDirectionRanger());
//        interferenceDTO.setTime_interf(cellNoise.getTimeInterf());
//        interferenceDTO.setHour_interf(cellNoise.getHourInterf());
//        interferenceDTO.setLevel_affect(cellNoise.getLevelAffect());
//        interferenceDTO.setPhenomena_interf(cellNoise.getPhenomenaInterf());
//        interferenceDTO.setPhenomena_desc(cellNoise.getPhenomenaDesc());
//        interferenceDTO.setAddInfo(cellNoise.getAddInfo());
//        interferenceDTO.setReson_freq_interf(cellNoise.getResonFreqInterf());
//        interferenceDTO.setIdent_cause_radio(cellNoise.getIdentCauseRadio());
//        interferenceDTO.setLocation_radio_cause(cellNoise.getLocationRadioCause());
//        interferenceDTO.setTime_statistic(cellNoise.getTimeStatistic());
        interferenceDTO.setProvince(cellNoise.getProvince());
        interferenceDTO.setCell_id(cellNoise.getCellId());
        interferenceDTO.setFreq(cellNoise.getFreq());
        if (cellNoise.getLon() != null) {
            interferenceDTO.setLon(Double.parseDouble(cellNoise.getLon()));
        }

        if (cellNoise.getLat() != null) {
            interferenceDTO.setLat(Double.parseDouble(cellNoise.getLat()));
        }
        interferenceDTO.setAzimuth(cellNoise.getAzimuth());
        interferenceDTO.setRtwp(cellNoise.getRtwp());
        interferenceDTO.setStatus(cellNoise.getStatus());
//        interferenceDTO.setCause_info(cellNoise.getC());
//        interferenceDTO.setRtwp(cellNoise.getRtwp());
        return interferenceDTO;
    }

    public Object updateInterference(InterferenceDTO interferenceFilterDTO) {
        try {
            CellNoise cellNoise = cellNoiseDAO.getCellNoise(interferenceFilterDTO.getCell_id());
            updateCellNoise(cellNoise, interferenceFilterDTO);

            CellNoiseInfo cellNoiseInfo = cellNoise.getCellNoiseInfoId();

            if (cellNoiseInfo != null) {
                updateCellNoiseInfo(cellNoiseInfo, interferenceFilterDTO);
                buildInterfrenceDTO(cellNoiseInfo);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    private void updateCellNoise(CellNoise cellNoise, InterferenceDTO interferenceFilterDTO) {
        if ("ĐÃ XỬ LÝ".equalsIgnoreCase(cellNoise.getStatus())){
            cellNoise.setStatus("DONE");
        }
        cellNoiseDAO.update(cellNoise);
    }


    private void updateCellNoiseInfo(CellNoiseInfo cellNoiseInfo, InterferenceDTO interferenceFilterDTO) {
        cellNoiseInfo.setStatus(interferenceFilterDTO.getStatus());
        cellNoiseInfo.setCauseInfo(interferenceFilterDTO.getCause_info());
        cellNoiseInfo.setProcessInfo(interferenceFilterDTO.getProcess_info());
        cellNoiseInfoDAO.update(cellNoiseInfo);
    }
}

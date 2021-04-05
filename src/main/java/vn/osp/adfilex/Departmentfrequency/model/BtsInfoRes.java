package vn.osp.adfilex.Departmentfrequency.model;

import vn.osp.adfilex.Departmentfrequency.entity.Bts;
import vn.osp.adfilex.Departmentfrequency.entity.Cell;

import java.util.List;

public class BtsInfoRes {

    private Location location = new Location();
    private Bts bts;
    private List<CellDto> cellDtos;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Bts getBts() {
        return bts;
    }

    public void setBts(Bts bts) {
        this.bts = bts;
    }

    public List<CellDto> getCellDtos() {
        return cellDtos;
    }

    public void setCellDtos(List<CellDto> cellDtos) {
        this.cellDtos = cellDtos;
    }
}

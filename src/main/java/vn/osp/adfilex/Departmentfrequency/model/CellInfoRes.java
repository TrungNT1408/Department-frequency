package vn.osp.adfilex.Departmentfrequency.model;

import vn.osp.adfilex.Departmentfrequency.entity.Cell;
import vn.osp.adfilex.Departmentfrequency.entity.CellFreq;

import java.util.List;

public class CellInfoRes {

    private long cellId;
    private Cell cell;
    private List<CellFreq> cellFreqs;

    public long getCellId() {
        return cellId;
    }

    public void setCellId(long cellId) {
        this.cellId = cellId;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public List<CellFreq> getCellFreqs() {
        return cellFreqs;
    }

    public void setCellFreqs(List<CellFreq> cellFreqs) {
        this.cellFreqs = cellFreqs;
    }
}

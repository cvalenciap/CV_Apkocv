package pe.com.sedapal.ofivirtual.model;

import java.io.Serializable;
import java.util.List;

public class ListHistoricConsumModel implements Serializable {
    private List<HistoricConsumModel> listHistoricConsumModel;

    public List<HistoricConsumModel> getListHistoricConsumModel() {
        return listHistoricConsumModel;
    }

    public void setListHistoricConsumModel(List<HistoricConsumModel> listHistoricConsumModel) {
        this.listHistoricConsumModel = listHistoricConsumModel;
    }
}

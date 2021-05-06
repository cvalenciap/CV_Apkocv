package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RequestLiquidacionEnchufateEntity {

    @JsonProperty("nis_rad")
    private long nisRad;
    @JsonProperty("documentos")
    private List<RequestLiquidationInvoicesEntity> documentos;

    public long getNisRad() {
        return nisRad;
    }

    public void setNisRad(long nisRad) {
        this.nisRad = nisRad;
    }

    public List<RequestLiquidationInvoicesEntity> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<RequestLiquidationInvoicesEntity> documentos) {
        this.documentos = documentos;
    }

    public static class RequestLiquidationInvoicesEntity {
        private String numeroDoc;
        private String fechaEmision;
        private String fechaVencimiento;
        private double deuda;

        public String getNumeroDoc() {
            return numeroDoc;
        }

        public String getFechaEmision() {
            return fechaEmision;
        }

        public String getFechaVencimiento() {
            return fechaVencimiento;
        }

        public double getDeuda() {
            return deuda;
        }

        public void setNumeroDoc(String numeroDoc) {
            this.numeroDoc = numeroDoc;
        }

        public void setFechaEmision(String fechaEmision) {
            this.fechaEmision = fechaEmision;
        }

        public void setFechaVencimiento(String fechaVencimiento) {
            this.fechaVencimiento = fechaVencimiento;
        }

        public void setDeuda(double deuda) {
            this.deuda = deuda;
        }
    }
}

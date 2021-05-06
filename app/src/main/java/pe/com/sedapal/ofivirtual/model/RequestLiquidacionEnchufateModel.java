package pe.com.sedapal.ofivirtual.model;

import java.util.List;

public class RequestLiquidacionEnchufateModel {

    private long nisRad;

    private List<RequestLiquidationInvoicesModel> documentos;

    public long getNisRad() {
        return nisRad;
    }

    public void setNisRad(long nisRad) {
        this.nisRad = nisRad;
    }

    public List<RequestLiquidationInvoicesModel> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<RequestLiquidationInvoicesModel> documentos) {
        this.documentos = documentos;
    }

    public static class RequestLiquidationInvoicesModel {
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

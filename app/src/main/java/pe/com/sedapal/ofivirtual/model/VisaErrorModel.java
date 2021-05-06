package pe.com.sedapal.ofivirtual.model;

public class VisaErrorModel {
    private String errorCode;
    private String errorMessage;
    private Header header;
    private DataMap data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public DataMap getData() {
        return data;
    }

    public void setData(DataMap data) {
        this.data = data;
    }

    public class Header{
        private String ecoreTransactionDate;
        private String ecoreTransactionUUID;
        private String millis;

        public String getEcoreTransactionDate() {
            return ecoreTransactionDate;
        }

        public void setEcoreTransactionDate(String ecoreTransactionDate) {
            this.ecoreTransactionDate = ecoreTransactionDate;
        }

        public String getEcoreTransactionUUID() {
            return ecoreTransactionUUID;
        }

        public void setEcoreTransactionUUID(String ecoreTransactionUUID) {
            this.ecoreTransactionUUID = ecoreTransactionUUID;
        }

        public String getMillis() {
            return millis;
        }

        public void setMillis(String millis) {
            this.millis = millis;
        }
    }

    public class DataMap{
        private String CURRENCY;
        private String TRANSACTION_DATE;
        private String ACTION_CODE;
        private String STATUS;
        private String ACTION_DESCRIPTION;
        private String TRACE_NUMBER;
        private String AMOUNT;
        private String ECI;
        private String SIGNATURE;
        private String CARD;
        private String BRAND;
        private String MERCHANT;
        private String TRANSACTION_ID;

        public String getTRANSACTION_ID() {
            return TRANSACTION_ID;
        }

        public void setTRANSACTION_ID(String TRANSACTION_ID) {
            this.TRANSACTION_ID = TRANSACTION_ID;
        }

        public String getCURRENCY() {
            return CURRENCY;
        }

        public void setCURRENCY(String CURRENCY) {
            this.CURRENCY = CURRENCY;
        }

        public String getTRANSACTION_DATE() {
            return TRANSACTION_DATE;
        }

        public void setTRANSACTION_DATE(String TRANSACTION_DATE) {
            this.TRANSACTION_DATE = TRANSACTION_DATE;
        }

        public String getACTION_CODE() {
            return ACTION_CODE;
        }

        public void setACTION_CODE(String ACTION_CODE) {
            this.ACTION_CODE = ACTION_CODE;
        }

        public String getSTATUS() {
            return STATUS;
        }

        public void setSTATUS(String STATUS) {
            this.STATUS = STATUS;
        }

        public String getACTION_DESCRIPTION() {
            return ACTION_DESCRIPTION;
        }

        public void setACTION_DESCRIPTION(String ACTION_DESCRIPTION) {
            this.ACTION_DESCRIPTION = ACTION_DESCRIPTION;
        }

        public String getTRACE_NUMBER() {
            return TRACE_NUMBER;
        }

        public void setTRACE_NUMBER(String TRACE_NUMBER) {
            this.TRACE_NUMBER = TRACE_NUMBER;
        }

        public String getAMOUNT() {
            return AMOUNT;
        }

        public void setAMOUNT(String AMOUNT) {
            this.AMOUNT = AMOUNT;
        }

        public String getECI() {
            return ECI;
        }

        public void setECI(String ECI) {
            this.ECI = ECI;
        }

        public String getSIGNATURE() {
            return SIGNATURE;
        }

        public void setSIGNATURE(String SIGNATURE) {
            this.SIGNATURE = SIGNATURE;
        }

        public String getCARD() {
            return CARD;
        }

        public void setCARD(String CARD) {
            this.CARD = CARD;
        }

        public String getBRAND() {
            return BRAND;
        }

        public void setBRAND(String BRAND) {
            this.BRAND = BRAND;
        }

        public String getMERCHANT() {
            return MERCHANT;
        }

        public void setMERCHANT(String MERCHANT) {
            this.MERCHANT = MERCHANT;
        }
    }
}

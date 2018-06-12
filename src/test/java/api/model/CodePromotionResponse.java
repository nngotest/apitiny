package api.model;

public class CodePromotionResponse {

    private String message;

    private String status;

    private Promotion promotion;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "ClassPojo [message = " + message + ", status = " + status + ", promotion = " + promotion + "]";
    }

}

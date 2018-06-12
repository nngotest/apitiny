package api.model;

import java.util.List;

public class UserPromotionResponse {

    private String message;

    private String status;

    private List<Promotion> promotions;

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

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    @Override
    public String toString() {
        return "ClassPojo [message = " + message + ", status = " + status + ", promotions = " + promotions + "]";
    }

}

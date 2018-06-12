package api.model;

public class Promotion {

    private String[] usersUsed;

    private String percentValue;

    private String isPercent;

    private String __v;

    private String code;

    private String moneyValue;

    private String used;

    private String id;

    private String isActive;

    private Time time;

    private String maxUsed;

    private String _id;

    private String description;

    private String usingForEvent;

    public String[] getUsersUsed() {
        return usersUsed;
    }

    public void setUsersUsed(String[] usersUsed) {
        this.usersUsed = usersUsed;
    }

    public String getPercentValue() {
        return percentValue;
    }

    public void setPercentValue(String percentValue) {
        this.percentValue = percentValue;
    }

    public String getIsPercent() {
        return isPercent;
    }

    public void setIsPercent(String isPercent) {
        this.isPercent = isPercent;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMoneyValue() {
        return moneyValue;
    }

    public void setMoneyValue(String moneyValue) {
        this.moneyValue = moneyValue;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getMaxUsed() {
        return maxUsed;
    }

    public void setMaxUsed(String maxUsed) {
        this.maxUsed = maxUsed;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsingForEvent() {
        return usingForEvent;
    }

    public void setUsingForEvent(String usingForEvent) {
        this.usingForEvent = usingForEvent;
    }

    @Override
    public String toString() {
        return "ClassPojo [usersUsed = " + usersUsed + ", percentValue = " + percentValue + ", isPercent = " + isPercent + ", __v = " + __v + ", code = " + code + ", moneyValue = " + moneyValue + ", used = " + used + ", id = " + id + ", isActive = " + isActive + ", time = " + time + ", maxUsed = " + maxUsed + ", _id = " + _id + ", description = " + description + ", usingForEvent = " + usingForEvent + "]";
    }

}

package BusinessLayer;

public class Health {
    // ----------------------------------- fields ----------------------------------------------------------------------
    private Integer HealthPool; // maximum life
    public Integer HealthAmount; // current life
    // ----------------------------------- constructor ----------------------------------------------------------------------
    public Health(Integer healthAmount){
        this.HealthAmount=healthAmount;
        this.HealthPool=healthAmount;
    }
    // ----------------------------------- methods ----------------------------------------------------------------------
    public void setHealthAmount(Integer healthAmount) {

            this.HealthAmount = healthAmount;

    }
    public void setHealthPool(Integer healthPool) {
        HealthPool = healthPool;
    }
    public Integer getHealthAmount() {
        return HealthAmount;
    }
    public Integer getHealthPool() {
        return HealthPool;
    }
}

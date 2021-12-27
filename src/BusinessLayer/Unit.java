package BusinessLayer;
// have to change package
public abstract class Unit extends Tile {
    // ----------------------------------- fields ----------------------------------------------------------------------
    protected String name;
    protected Integer attackPoints;
    protected Integer defensePoints;
    protected Health health;
    protected boolean isDead;
    protected level level;
    protected EventHandler messageHandler;
    // ----------------------------------- constructor ----------------------------------------------------------------------
    public Unit(String name,char type,point position,level level,Integer healthAmount,Integer defensePoints,Integer attackPoints){
        super(position,type); // for Tile class
        this.name=name;
        this.isDead=false;
        this.level=level;
        this.health=new Health(healthAmount);
        this.defensePoints=defensePoints;
        this.attackPoints=attackPoints;
        this.messageHandler=new EventHandler();
    }
    // ----------------------------------- methods ----------------------------------------------------------------------
    public void loseHealth(Integer toLose) {
        Integer healthNew = this.getHealthAmount() - toLose;
        if (healthNew <= 0) { // if the new health is lesser then 0 -> put 0
            this.health.setHealthAmount(0);
            this.isDead = true;
        } else
            this.health.setHealthAmount(healthNew);
    }
    public Integer getHealthAmount() {
        return this.health.getHealthAmount();
    }
    public Integer getDefensePoints() {
        return defensePoints;
    }
    public Integer getAttackPoints() {
        return attackPoints;
    }
    public boolean isDead(){ return this.isDead;}
    public String getName() {
        return name;
    }
    public abstract String describe();
    public void setLevel(level currLevel){
        this.level=currLevel;
    }
    public point up() {

        point toReturn = new point(this.getPosition().getX()-1, this.getPosition().getY());
        if (level.isValidPoint(toReturn))
            return toReturn;
        else
            return null;
    }
    public point down() {

        point toReturn = new point(this.getPosition().getX()+1, this.getPosition().getY());
        if (level.isValidPoint(toReturn))
            return toReturn;
        else
            return null;
    }
    public point left() {
        point toReturn = new point(this.getPosition().getX(), this.getPosition().getY()-1);
        if (level.isValidPoint(toReturn))
            return toReturn;
        else
            return null;

    }
    public point right() {
        point toReturn = new point(this.getPosition().getX(), this.getPosition().getY()+1);
        if (level.isValidPoint(toReturn))
            return toReturn;
        else
            return null;
    }

    public void setIsDead(){
        this.isDead=true;
    }
}
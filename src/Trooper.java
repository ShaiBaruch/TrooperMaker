import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public abstract class Trooper {

    /**
     * Name: Shai Baruch
     * Date: 3/21/2023
     * Description: This program simulates a fight between two units.
     * It chooses a trooper, name, unit and create fight cases.
     */
    private String unit;
    private int number;
    String trooperKind;
    double marchSpeed;
    double marchModifier;

    Trooper(){
    this("AA",0);
    }
    public Trooper(String unit, int number){
        this.unit = unit;
        this.number = number;
        this.marchSpeed = 5.0;

    }
    public static void addToUnit(HashMap<String , List<Trooper>> units, Trooper t){
        if(t == null) return;
        if(!units.containsKey(t.unit)){
            List<Trooper> values = new ArrayList<>();
            units.put(t.unit, values);
        }
        units.get(t.unit).add(t);

    }
    public abstract double march(double duration);

    public boolean attack(Trooper target, int roll) {
        if (this == target || roll == 1) {
            System.out.println(this + " is targeting itself.");
            System.out.println(this + " rolled a " + roll + " and hurt itself in the confusion");
            return true;
        }
        if(this.getClass().equals(StormTrooper.class)){
            if(target.getClass().equals(RebelTrooper.class)){
                System.out.println(this + "rolled a " + roll + " against the rebel scum.");
                return  roll > 10 && roll % 2 == 0;
            }else if(target.getClass().equals(StormTrooper.class)){
                System.out.println("No treason in the ranks!");
                return false;
            }else{
                System.out.println("Acceptable Collateral Damage!");
                return roll > 10 || roll % 2 == 0;
            }
        } else if (this.getClass().equals(RebelTrooper.class)) {
            if(target.getClass().equals(RebelTrooper.class)){
                System.out.println("Imperial Spy!");
                return false;
            }else if(target.getClass().equals(StormTrooper.class)){
                System.out.println(this + " rolled a " + roll + " against the imperial scum.");
                return roll > 5 || roll % 2 == 1;
            }else{
                System.out.println("Rebels target an innocent bystander ");
                return roll >= 18 && roll % 2 == 0;
            }

        }
        return false; //only to close the method.
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTrooperKind() {
        return trooperKind;
    }

    public void setTrooperKind(String trooperKind) {
        this.trooperKind = trooperKind;
    }

    public double getMarchSpeed() {
        return marchSpeed;
    }

    public void setMarchSpeed(double marchSpeed) {
        this.marchSpeed = marchSpeed;
    }
    @Override
    public String toString(){
    return this.unit+ this.number+": ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trooper trooper = (Trooper) o;
        return number == trooper.number && Double.compare(trooper.marchSpeed, marchSpeed) == 0 &&
                Double.compare(trooper.marchModifier, marchModifier) == 0 &&
                unit.equals(trooper.unit) && trooperKind.equals(trooper.trooperKind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit, number, trooperKind, marchSpeed, marchModifier);
    }
}

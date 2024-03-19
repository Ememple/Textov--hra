public class Predmety {
    private String jmeno;
    private int bonusZivoty;
    private  int bonusPoskozeni;

    public int pridaniZivotu(int zivoty){
        zivoty=zivoty+bonusZivoty;
        return zivoty;
    }
    public int pridaniPoskozeni(int poskozeni){
        poskozeni=poskozeni+bonusPoskozeni;
        return poskozeni;
    }

    public Predmety(String jmeno, int bonusZivoty, int bonusPoskozeni) {
        this.jmeno = jmeno;
        this.bonusZivoty = bonusZivoty;
        this.bonusPoskozeni = bonusPoskozeni;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public int getBonusZivoty() {
        return bonusZivoty;
    }

    public void setBonusZivoty(int bonusZivoty) {
        this.bonusZivoty = bonusZivoty;
    }

    public int getBonusPoskozeni() {
        return bonusPoskozeni;
    }

    public void setBonusPoskozeni(int bonusPoskozeni) {
        this.bonusPoskozeni = bonusPoskozeni;
    }

    @Override
    public String toString() {
        return jmeno +": "+  bonusZivoty +" životů, " +bonusPoskozeni+ " poškození";
    }
}

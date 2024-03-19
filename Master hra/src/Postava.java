import java.util.Random;

public class Postava {
    protected String jmeno;
    protected int maxZivoty;
    protected int aktualniZivoty;
    protected int sila;

    public Postava(String jmeno, int maxZivoty, int aktualniZivoty, int sila) {
        this.jmeno = jmeno;
        this.maxZivoty = maxZivoty;
        this.aktualniZivoty = aktualniZivoty;
        this.sila = sila;
    }

    public  int utok(){
        Random random = new Random();
        int poskozeni= random.nextInt(20)+getSila();
        return poskozeni;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public int getMaxZivoty() {
        return maxZivoty;
    }

    public void setMaxZivoty(int maxZivoty) {
        this.maxZivoty = maxZivoty;
    }

    public int getAktualniZivoty() {
        return aktualniZivoty;
    }

    public void setAktualniZivoty(int aktualniZivoty) {
        this.aktualniZivoty = aktualniZivoty;
    }

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    @Override
    public String toString() {
        return "Postava{" +
                "jmeno='" + jmeno + '\'' +
                ", zivoty=" + aktualniZivoty +
                ", sila=" + sila +
                '}';
    }
}

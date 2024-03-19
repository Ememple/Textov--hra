public class Mistnost {
    private String jmeno;
    private int sever;
    private int jih;
    private int vychod;
    private int zapad;
    private Postava nepritel;
    private Predmety predmety;

    public Mistnost(String jmeno, int sever, int jih, int vychod, int zapad) {
        this.jmeno = jmeno;
        this.sever = sever;
        this.jih = jih;
        this.vychod = vychod;
        this.zapad = zapad;
    }

    public String vypisStran(){
        String text="Můžete jít na:";
        if (sever!=0){
            text=text+"|sever|";
        }
        if (jih!=0){
            text=text+"|jih|";
        }
        if (vychod!=0){
            text=text+"|východ|";
        }
        if (zapad!=0){
            text=text+"|západ|";
        }

        return text;
    }


    @Override
    public String toString() {
        return jmeno;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public int getSever() {
        return sever;
    }

    public void setSever(int sever) {
        this.sever = sever;
    }

    public int getJih() {
        return jih;
    }

    public void setJih(int jih) {
        this.jih = jih;
    }

    public int getVychod() {
        return vychod;
    }

    public void setVychod(int vychod) {
        this.vychod = vychod;
    }

    public int getZapad() {
        return zapad;
    }

    public void setZapad(int zapad) {
        this.zapad = zapad;
    }

    public Postava getNepritel() {
        return nepritel;
    }

    public void setNepritel(Postava nepritel) {
        this.nepritel = nepritel;
    }

    public Predmety getPredmety() {
        return predmety;
    }

    public void setPredmety(Predmety predmety) {
        this.predmety = predmety;
    }
}

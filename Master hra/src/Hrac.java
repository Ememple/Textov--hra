import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Hrac extends Postava{
    private Mistnost mistoHrace;
    private ArrayList<Predmety> predmety;

    public Hrac(String jmeno, int maxZivoty, int aktualniZivoty, int sila, Mistnost mistoHrace, ArrayList<Predmety> predmety) {
        super(jmeno, maxZivoty, aktualniZivoty, sila);
        this.mistoHrace = mistoHrace;
        this.predmety = predmety;
    }

    public String pohyb(Mapa mapa){
        System.out.println(mistoHrace.vypisStran());
        Scanner scanner = new Scanner(System.in);
        String odpoved =scanner.nextLine();
        switch (odpoved.toLowerCase()){
            case "sever"-> {
                if (mistoHrace.getSever()!=0){
                    setMistoHrace(mapa.getLokace().get(mistoHrace.getSever()));
                }
            }
            case "jih"-> {
                if (mistoHrace.getJih() != 0) {
                    setMistoHrace(mapa.getLokace().get(mistoHrace.getJih()));
                }
            }
            case "východ"-> {
                if (mistoHrace.getVychod() != 0) {
                    setMistoHrace(mapa.getLokace().get(mistoHrace.getVychod()));
                }
            }
            case "západ"->
            {
                if (mistoHrace.getZapad() != 0) {
                    setMistoHrace(mapa.getLokace().get(mistoHrace.getZapad()));
                }
            }
        }

        kontrolaNepritele();
        kontrolaPredmetu();
        if (getAktualniZivoty()<=0){
            System.out.println("Prohrál jste :(");
            System.exit(0);
        }
        String text="Vaše lokace je nyní:"+mistoHrace;
        return text;
    }

    public boolean inventar(){
        if (predmety.size()!=0){
        Scanner scanner = new Scanner(System.in);
        System.out.println(predmety);
        System.out.println("Chcete odebrat nějaký předmět? |ano| |ne|");
        if (scanner.nextLine().equals("ano")){
            System.out.println("Zadejte jméno předmětu");
            String jmenoPredmetu = scanner.nextLine();
            for (int i =0; i<predmety.size(); i++){
                if (predmety.get(i).getJmeno().equals(jmenoPredmetu)){
                    System.out.println("Předmět byl odebrán: "+predmety.get(i));
                    predmety.remove(i);
                 }
                }
            }

        }
        else {
            System.out.println("Nemáte žádný předmět");
        }
        return true;
    }
    public boolean kontrolaNepritele(){
        boolean vyhra=false;
        if (mistoHrace.getNepritel()!=null){
            System.out.println("V místnosti je nepřítel, nyní začíná boj");
            vyhra=souboj(mistoHrace.getNepritel());
            if (vyhra){
                mistoHrace.setNepritel(null);
            }
        }
        return vyhra;
    }

    public boolean kontrolaPredmetu(){
        if (mistoHrace.getPredmety()!=null){
            Scanner scanner = new Scanner(System.in);
            System.out.println("V místnosti je předmět: "+mistoHrace.getPredmety());
            System.out.println("Chcete předmět sebrat? |ano| |ne|");
            String odpoved=scanner.nextLine().toLowerCase();
            if (odpoved.equals("ano")){
                predmety.add(mistoHrace.getPredmety());
                setMaxZivoty(mistoHrace.getPredmety().pridaniZivotu(getMaxZivoty()));
                if (getMaxZivoty()<getAktualniZivoty()){
                    setAktualniZivoty(getMaxZivoty());
                }
                setSila(mistoHrace.getPredmety().pridaniPoskozeni(getSila()));

                System.out.println("Maximální životy: "+maxZivoty+" Aktuální životy: "+ aktualniZivoty+" Poškození: "+ sila);
            }
            else if (odpoved.equals("ne")){
                mistoHrace.setPredmety(null);
            }
        }
        return true;
    }

    public  int utok(){
        Random random = new Random();
        int poskozeni= random.nextInt(20)+getSila();
        return poskozeni;
    }
    public String vyleceni(){
        String text="";
        if (getMaxZivoty()==getAktualniZivoty()){
            System.out.println("Nemůžete se vyléčit, ale vaše životy se zvýšili");
            setMaxZivoty(getMaxZivoty()+10);
            setAktualniZivoty(getMaxZivoty());
            text="Vaše maximální životy se zvedly o 10, vaše aktuální maximální životy jsou:"+getMaxZivoty();
        }
        else {
            setAktualniZivoty(getAktualniZivoty()+25);
            text="Vyléčil jste se o 25,vaše aktuální životy jsou:"+getAktualniZivoty();
        }
        return text;
    }

    public boolean souboj(Postava souper) {
        boolean vyhra = false;
        try {
            System.out.println("Bojují proti sobě "+getJmeno()+" a "+souper.getJmeno());
            Random random = new Random();
            Scanner scanner = new Scanner(System.in);
            while (getAktualniZivoty()>0 || souper.getAktualniZivoty()>0){
                System.out.println("Vaše aktuální životy :"+getAktualniZivoty());
                System.out.println("Aktuální životy soupeře:"+souper.getAktualniZivoty());
                System.out.println();

                System.out.println("Vyberte akci: |útok| |vyléčení|");
                String vyberAkce= scanner.nextLine();
                if (vyberAkce.equals("útok")){
                    souper.setAktualniZivoty(souper.getAktualniZivoty()-utok());
                    System.out.println("Udělil jste "+utok()+" bodů poškození");
                    if (souper.getAktualniZivoty()<0){
                        System.out.println("Výherce je:"+getJmeno());
                        vyhra=true;
                            break;
                    }
                }
                if (vyberAkce.equals("vyléčení")){
                    System.out.println(vyleceni());
                }

                System.out.println("Teď je kolo soupeře");
                if (random.nextInt(4)==3){
                    if (souper.getMaxZivoty() == souper.getAktualniZivoty()) {
                        souper.setMaxZivoty(souper.getMaxZivoty()+10);
                        souper.setAktualniZivoty(souper.getMaxZivoty()+10);
                        System.out.println("Soupeřovi životy se zvedli, maximálni životy soupeře jsou: "+souper.getAktualniZivoty());
                    }
                    souper.setAktualniZivoty(souper.getAktualniZivoty()+25);
                    System.out.println("Soupeř se vyléčil, životy soupeře jsou: "+souper.getAktualniZivoty());
                }
                else {
                    System.out.println("Teď útočí soupeř");
                    setAktualniZivoty(getAktualniZivoty()-souper.utok());
                    System.out.println("Obdržel jste "+souper.utok()+" bodů poškození");
                    if (getAktualniZivoty()<=0){
                        System.out.println("Výherce je:"+souper.getJmeno());
                        break;
                    }
                }
            }
        }
        catch (InputMismatchException mismatchException){
            System.out.println("Zadejte prosím číslo a ne písmeno :)");
        }
        return vyhra;
    }

    public Mistnost getMistoHrace() {
        return mistoHrace;
    }

    public void setMistoHrace(Mistnost mistoHrace) {
        this.mistoHrace = mistoHrace;
    }

    public ArrayList<Predmety> getPredmety() {
        return predmety;
    }

    public void setPredmety(ArrayList<Predmety> predmety) {
        this.predmety = predmety;
    }

    @Override
    public String toString() {
        return "Hrac{" +
                "mistoHrace=" + mistoHrace +
                ", predmety=" + predmety +
                ", jmeno='" + jmeno + '\'' +
                ", maxZivoty=" + maxZivoty +
                ", aktualniZivoty=" + aktualniZivoty +
                ", sila=" + sila +
                '}';
    }
}

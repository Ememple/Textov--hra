import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Mapa mapa = new Mapa(new HashMap<>());
        ArrayList<Predmety> predmety = new ArrayList<>();

        Hrac hrac = new Hrac("Muž",100,100,10,mapa.getLokace().get(6), predmety);
        mapa.getLokace().get(7).setNepritel(new Postava("Vocas",50,50,10));
        mapa.getLokace().get(13).setNepritel(new Postava("Dwarfman",100,100,15));
        mapa.getLokace().get(5).setPredmety(new Predmety("Paflův pes", 40, 20));
        mapa.getLokace().get(8).setPredmety(new Predmety("Epická kytara", -25, 30));
        mapa.getLokace().get(12).setPredmety(new Predmety("Žebřík", 0,500));

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("pribehZacatek.txt"));
            String line="";
            while ((line=bufferedReader.readLine())!=null){
                System.out.println(line);
            }
        }catch (FileNotFoundException fileNotFoundException){
            System.out.println("Chybí vám textový dokument");
        }
        boolean konec=false;
        System.out.println("Vaše lokace je nyní:"+hrac.getMistoHrace());
        while (!konec) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Zadejte vaší akci: |pohyb| |inventář|");
                switch (scanner.nextLine()){
                    case "pohyb" -> System.out.println(hrac.pohyb(mapa));
                    case "inventář" -> hrac.inventar();
                }
                if (hrac.getMistoHrace().getJmeno().equals("Boss-Dwarfmen") && hrac.getMistoHrace().getNepritel()==null){

                    BufferedReader bufferedReader = new BufferedReader(new FileReader("pribehKonec.txt"));
                    String line="";
                    while ((line=bufferedReader.readLine())!=null){
                        System.out.println(line);
                    }
                    konec=true;
                }
            }
            catch (NullPointerException nullPointerException){
                System.out.println("Zadejte jenom platné směry");
            }
        }
    }
}
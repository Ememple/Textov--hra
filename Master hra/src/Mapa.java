import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mapa {
    private HashMap<Integer, Mistnost> lokace;

    public Mapa(HashMap<Integer, Mistnost> lokace) throws IOException {
        this.lokace = lokace;
        nacistMapu();
    }


    public void nacistMapu() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("mapa.txt"));
        String line;
        int cisloMistnosti=0;
        while ((line=bufferedReader.readLine())!=null){
        int sever=0,jih=0,vychod=0,zapad=0;
        String nazev="";
            for (int i=0; i<4; i++){
                switch (i){
                    case 0: sever= (int) nacteniStran(line).get(i);
                        break;
                    case 1: jih= (int) nacteniStran(line).get(i);
                        break;
                    case 2: vychod= (int) nacteniStran(line).get(i);
                        break;
                    case 3: zapad= (int) nacteniStran(line).get(i);
                        break;
                }
            }
            nazev=nazevMistnost(line);
            cisloMistnosti++;
            lokace.put(cisloMistnosti, new Mistnost(nazev,sever,jih,vychod,zapad));
        }
    }


    public ArrayList nacteniStran(String line){
        Pattern pattern = Pattern.compile("([0-9]+,){3}[0-9]+");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        ArrayList<String> stranyString = new ArrayList<>(Arrays.asList(matcher.group().split(",")));
        ArrayList<Integer> stranyInt = new ArrayList<>();
        for (int i =0; i<stranyString.size(); i++){
           stranyInt.add(Integer.parseInt(stranyString.get(i)));
        }

        return stranyInt;
    }

    public String nazevMistnost(String line){
        Pattern pattern = Pattern.compile("([A-Z][a-z]+-*)+");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        String nazev =matcher.group(0);
        return nazev;
    }

    public HashMap<Integer, Mistnost> getLokace() {
        return lokace;
    }

    public void setLokace(HashMap<Integer, Mistnost> lokace) {
        this.lokace = lokace;
    }

    @Override
    public String toString() {
        return "Mapa{" + lokace +
                '}';
    }
}

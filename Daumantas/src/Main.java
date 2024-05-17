import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicijuojame visus kintamuosius kuriu reikes veliau
        Scanner skenuotojas = new Scanner(System.in);
        String vartotojo_Ivestis;
        String failu_Direktorija = "C:\\Projects\\PirminiaiSkaiciai\\Testai";
        File direktorija = new File(failu_Direktorija);
        File[] rasti_Failai = direktorija.listFiles();
        ArrayList<File> txt_Failai = new ArrayList<>();

        // Perziurime visus rastus failus direktorijoje ir visus .txt failus issaugome i txt_failai masyva.
        if (rasti_Failai != null){
            for (File failai : rasti_Failai){
                if (failai.isFile() && failai.getName().endsWith(".txt")){
                    txt_Failai.add(failai);
                }
            }
        }

        // Vartotojo sasaja
        while (true) {
            System.out.println("----------------------------------");
            System.out.println("Ivesti galite tik skaicius");
            System.out.println("Koki algoritma norite vykdyti?");
            System.out.println("1. Tikslu Eratosteno recio algoritma");
            System.out.println("2. Apytiksli Miller-Rabin algoritma");
            System.out.println("3. Uzdaryti");
            System.out.println("*Iveskite 'Palygink', kad palyginti abieju rastu algoritmu visus skaicius iki 1 000 000");
            vartotojo_Ivestis = skenuotojas.nextLine();

            if (vartotojo_Ivestis.equals("1")) {
                System.out.println("Pasirinktas Eratosteno recio algoritmas");
                while (true) {
                    System.out.println("----------------------------------");
                    System.out.println("Pasirinkite ivesties buda");
                    System.out.println("1. Konsole");
                    System.out.println("2. Failas");
                    System.out.println("3. Grizti atgal");
                    vartotojo_Ivestis = skenuotojas.nextLine();

                    if (vartotojo_Ivestis.equals("1")){
                        while (true){
                            System.out.println("------------------------------------------");
                            System.out.println("Iveskite skaiciu nuo [2, 1 000 000 000]. Programa suras visus pirminius skaicius iki ivesto skaiciaus");
                            System.out.println("Iveskite 'atgal', jei norite grizti atgal");
                            vartotojo_Ivestis = skenuotojas.nextLine();

                            if (vartotojo_Ivestis.equals("atgal") || vartotojo_Ivestis.equals("Atgal")){
                                break;
                            }

                            int skaicius = Integer.parseInt(vartotojo_Ivestis);
                            if (skaicius >= 2 && skaicius <= 1000000000){
                                Eratosteno_retis.Algoritmas(skaicius);
                            } else {
                                System.out.println("Ivestas skaicius neatitinka reikalavimu. Iveskite skaiciu ribose nuo [2, 1 000 000 000]");
                            }
                        }

                    } else if (vartotojo_Ivestis.equals("2")){
                        if (!txt_Failai.isEmpty()){
                            while(true){
                                System.out.println("----------------------------------------");
                                System.out.println("Isvedami visi rasti .txt failai kode nurodytoje direktorijoje");

                                int pasirinkimu_Skaicius = 0;
                                for (int skaitliukas = 1; skaitliukas <= txt_Failai.size(); skaitliukas++){
                                    System.out.println(skaitliukas + ". " + txt_Failai.get(skaitliukas - 1));
                                    pasirinkimu_Skaicius = skaitliukas;
                                }
                                System.out.println(pasirinkimu_Skaicius + 1 + ". Grizti atgal");

                                System.out.println("Pasirinkite is kokio failo skaityti pradinius duomenis?");
                                vartotojo_Ivestis = skenuotojas.nextLine();

                                if (Integer.parseInt(vartotojo_Ivestis) > pasirinkimu_Skaicius + 1){
                                    System.out.println("Ivedete skaiciu kurio nera pasirinkime");

                                } else if (Integer.parseInt(vartotojo_Ivestis) < 1) {
                                    System.out.println("Ivedete skaiciu kurio nera pasirinkime");

                                } else if (Integer.parseInt(vartotojo_Ivestis) == pasirinkimu_Skaicius + 1){
                                    break;

                                } else {
                                    System.out.println("Pasirinktas " + txt_Failai.get(Integer.parseInt(vartotojo_Ivestis) - 1));
                                    ArrayList<Integer> kintamieji = new ArrayList<>();
                                    try(BufferedReader failo_Skaitytuvas = new BufferedReader(new FileReader(txt_Failai.get(Integer.parseInt(vartotojo_Ivestis) - 1)))){
                                        String eilute;
                                        while ((eilute = failo_Skaitytuvas.readLine()) != null){
                                            try {
                                                int n_Reiksme = Integer.parseInt(eilute.trim());
                                                kintamieji.add(n_Reiksme);

                                            } catch (NumberFormatException pagauta_Isimtis){
                                                System.err.println("Nuskaitytas skaicius kuris neatitinka reikalavimu. Skaicius turi buti nuo sveikas ir nuo 1 iki 1 000 000 000 " + eilute);
                                            }
                                        }
                                    } catch (IOException pagauta_Isimtis){
                                        System.err.println("Failas nebuvo nuskaitytas: " + pagauta_Isimtis.getMessage());
                                    }

                                    if (kintamieji.isEmpty()){
                                        System.out.println("Pasirinktame faile nera kintamuju");
                                    } else {
                                        while (!kintamieji.isEmpty()){
                                            System.out.println("----------------------------");
                                            int skaicius = kintamieji.get(0);
                                            System.out.println("n reiksme = " + skaicius);
                                            Eratosteno_retis.Algoritmas(skaicius);
                                            kintamieji.remove(0);
                                        }
                                    }
                                }
                            }
                        } else {
                            System.out.println("Nurodytoje direktorijoje nera .txt failu. Pasirinkite kita ivesties buda");
                            break;
                        }
                    } else if (vartotojo_Ivestis.equals("3")){
                        System.out.println("Griztama atgal");
                        break;
                    } else {
                        System.out.println("Ivedete skaiciu kurio nera pasirinkime");
                    }
                }

            } else if (vartotojo_Ivestis.equals("2")) {
                System.out.println("Pasirinktas Miller-Rabin algoritmas");
                while (true) {
                    System.out.println("----------------------------------");
                    System.out.println("Pasirinkite ivesties buda");
                    System.out.println("1. Konsole");
                    System.out.println("2. Failas");
                    System.out.println("3. Grizti atgal");
                    vartotojo_Ivestis = skenuotojas.nextLine();

                    if (vartotojo_Ivestis.equals("1")){
                        System.out.println("Pasiekta konsole");
                        while (true){
                            System.out.println("-----------------------------------------");
                            System.out.println("Iveskite skaiciu kuri norite patikrinti. Skaicius turi buti nelyginis ir didesnis uz 2");
                            System.out.println("Iveskite 'atgal', jei norite grizti atgal");
                            vartotojo_Ivestis = skenuotojas.nextLine();
                            if (vartotojo_Ivestis.equals("atgal") || vartotojo_Ivestis.equals("Atgal")){
                                break;
                            }

                            int skaicius = Integer.parseInt(vartotojo_Ivestis);
                            if (skaicius > 2 & skaicius%2 != 0){
                                System.out.println("Iveskite tiksluma, kiek kartu generuosime atsitiktini skaiciu. Skaicius turi buti nuo [1, 100]");
                                vartotojo_Ivestis = skenuotojas.nextLine();

                                if (vartotojo_Ivestis.equals("atgal") || vartotojo_Ivestis.equals("Atgal")){
                                    break;
                                }

                                int tikslumas = Integer.parseInt(vartotojo_Ivestis);

                                if (tikslumas >= 1 && tikslumas <= 100){
                                    Miller_Rabin.Algoritmas(skaicius, tikslumas);
                                } else {
                                    System.out.println("Ivedete tiksluma , kuris nera nurodytose ribose: [1, 100]");
                                }
                            } else {
                                System.out.println("Ivedete skaiciu kuris mazesnis arba lygus 2, arba ivestas skaicius yra lyginis");
                            }
                        }

                    } else if (vartotojo_Ivestis.equals("2")){

                        if (!txt_Failai.isEmpty()){
                            while(true){
                                System.out.println("----------------------------------------");
                                System.out.println("Isvedami visi rasti .txt failai kode nurodytoje direktorijoje");

                                int pasirinkimu_Skaicius = 0;
                                for (int skaitliukas = 1; skaitliukas <= txt_Failai.size(); skaitliukas++){
                                    System.out.println(skaitliukas + ". " + txt_Failai.get(skaitliukas - 1));
                                    pasirinkimu_Skaicius = skaitliukas;
                                }
                                System.out.println(pasirinkimu_Skaicius + 1 + ". Grizti atgal");

                                System.out.println("Pasirinkite is kokio failo skaityti pradinius duomenis?");
                                vartotojo_Ivestis = skenuotojas.nextLine();

                                if (Integer.parseInt(vartotojo_Ivestis) > pasirinkimu_Skaicius + 1){
                                    System.out.println("Ivedete skaiciu kurio nera pasirinkime");

                                } else if (Integer.parseInt(vartotojo_Ivestis) < 1) {
                                    System.out.println("Ivedete skaiciu kurio nera pasirinkime");

                                } else if (Integer.parseInt(vartotojo_Ivestis) == pasirinkimu_Skaicius + 1){
                                    break;

                                } else {
                                    System.out.println("Pasirinktas " + txt_Failai.get(Integer.parseInt(vartotojo_Ivestis) - 1));

                                    ArrayList<Integer> skaicius = new ArrayList<>();
                                    ArrayList<Integer> tikslumas = new ArrayList<>();

                                    try(BufferedReader failo_Skaitytuvas = new BufferedReader(new FileReader(txt_Failai.get(Integer.parseInt(vartotojo_Ivestis) - 1)))){
                                        String eilute;
                                        while ((eilute = failo_Skaitytuvas.readLine()) != null){
                                            String[] eilutes_Dalys = eilute.split(", ");

                                            if (eilutes_Dalys.length != 2){
                                                System.out.println("Klaidingas paduotas failas");
                                                break;
                                            }

                                            try {
                                                skaicius.add(Integer.parseInt(eilutes_Dalys[0]));
                                                tikslumas.add(Integer.parseInt(eilutes_Dalys[1]));

                                            } catch (NumberFormatException pagauta_Isimtis){
                                                System.err.println("Nuskaitytas skaicius kuris neatitinka reikalavimu. Skaicius turi buti nuo sveikas, nelyginis ir priklausyti intervalui nuo 3 iki 1 000 000 000 " + eilute);
                                            }
                                        }
                                    } catch (IOException pagauta_Isimtis){
                                        System.err.println("Failas nebuvo nuskaitytas: " + pagauta_Isimtis.getMessage());
                                    }

                                    if (skaicius.size() != tikslumas.size()){
                                        System.out.println("Faile pateikti duomenys turi buti pateikti tokiu formatu: 'x, y', kur x ir y yra sveiki skaiciai. Y ribos yra [1, 100], X ribos [3, 1 000 000 000]");
                                    }

                                    if (skaicius.isEmpty() && tikslumas.isEmpty()){
                                        System.out.println("Pateiktas failas klaidingu formatu");
                                    } else {
                                        while (!skaicius.isEmpty() && !tikslumas.isEmpty()){
                                            System.out.println("----------------------------");
                                            Miller_Rabin.Algoritmas(skaicius.get(0), tikslumas.get(0));
                                            skaicius.remove(0);
                                            tikslumas.remove(0);
                                        }
                                    }
                                }
                            }
                        } else {
                            System.out.println("Nurodytoje direktorijoje nera .txt failu. Pasirinkite kita ivesties buda");
                            break;
                        }

                    } else if (vartotojo_Ivestis.equals("3")){
                        System.out.println("Griztama atgal");
                        break;

                    } else {
                        System.out.println("Ivedete skaiciu kurio nera pasirinkime");
                    }
                }
            } else if (vartotojo_Ivestis.equals("3")){
                System.out.println("Baigiamas darbas");
                break;
            } else if (vartotojo_Ivestis.equals("Palygink")){

                //Testai
//                Miller_Rabin.Algoritmas(7,5);
//                int counter = 3;
//                System.out.println("2");
//                while (counter <= 1000000){
//                    Miller_Rabin.Algoritmas(counter, 10);
//                    counter = counter + 2;
//
//                }

                // Nurodome kuriuos 2 failus lyginsime
                String pirmas_Failas = "C:\\Projects\\PirminiaiSkaiciai\\Testai\\Palyginimas\\Eratosteno_retis.txt";
                String antras_Failas = "C:\\Projects\\PirminiaiSkaiciai\\Testai\\Palyginimas\\Miller-Rabin5.txt";
                ArrayList<Integer> pirmo_Failo_Rezultatai = new ArrayList<>();
                ArrayList<Integer> antro_Failo_Rezultatai = new ArrayList<>();
                String nuskaityta;

                // Nuskaitome visas reiksmes is pirmo nurodyto failo i masyva
                try {
                    BufferedReader pirmo_Failo_Skaitytuvas = new BufferedReader(new FileReader(pirmas_Failas));

                    while ((nuskaityta = pirmo_Failo_Skaitytuvas.readLine()) != null){
                        pirmo_Failo_Rezultatai.add(Integer.parseInt(nuskaityta));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Nuskaitome visas reiksmes is antro nurodyto failo i masyva
                try {
                    BufferedReader antro_Failo_Skaitytuvas = new BufferedReader(new FileReader(antras_Failas));

                    while ((nuskaityta = antro_Failo_Skaitytuvas.readLine()) != null){
                        antro_Failo_Rezultatai.add(Integer.parseInt(nuskaityta));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Lyginame 2 masyvus
                // Jei reiksmes lygios, nieko neisvedame i ekrana. Istriname abi reiksmes is abieju masyvu
                // Jei antro masyvo reiksme diddesne uz pirmo, tai reiskias buvo praleistas pirminis skaicius. Istriname tik pirmo masyvo reiksme
                // Jei pirmo masyvo reiksme yra didesne, tai reiksias Miller-Rabin algoritmas tvirtina, kad ta reiksme yra pirminis skaicius
                while (!antro_Failo_Rezultatai.isEmpty()){
                    if (pirmo_Failo_Rezultatai.isEmpty()){
                        System.out.println("Miller-Rabin algoritmas tvirtina, kad skaicius " + antro_Failo_Rezultatai.get(0) + " yra pirminis");
                    }
                    if (antro_Failo_Rezultatai.get(0).equals(pirmo_Failo_Rezultatai.get(0))){
                        antro_Failo_Rezultatai.remove(0);
                        pirmo_Failo_Rezultatai.remove(0);
                    } else if (antro_Failo_Rezultatai.get(0) > pirmo_Failo_Rezultatai.get(0)){
                        System.out.println("Miller-Rabin algoritmas nesurado pirminio skaiciaus " + pirmo_Failo_Rezultatai.get(0));
                        pirmo_Failo_Rezultatai.remove(0);
                    } else {
                        System.out.println("Miller-Rabin algoritmas tvirtina, kad skaicius " + antro_Failo_Rezultatai.get(0) + " yra pirminis");
                        antro_Failo_Rezultatai.remove(0);
                    }
                }
            } else {
                System.out.println("Ivedete skaiciu kurio nera pasirinkime");
            }
        }
    }













}

public class Eratosteno_retis {

    private static long pradzia;
    private static long pabaiga;
    private static long trukme;

    public static void Algoritmas(int skaicius){
        // Sukuriamas skaiciaus ilgio boolean reiksmiu masyvas ir uzpildome ji true reiksmemis
        boolean[] ar_Pirminis = new boolean[skaicius+1];
        for (int skaitliukas = 2; skaitliukas <= skaicius; skaitliukas++){
            ar_Pirminis[skaitliukas] = true;
        }

        pradzia = System.nanoTime();

        // Pradedame tikrinti pirminius skaicius nuo 2.
        // Pagal Eratosteno recio algoritmą, skaicius tikriname tik iki saknies iš nurodyto skaiciaus
        // Visi skaiciai kurie lieka po atrankos yra pirminiai skaiciai
        for (int skaitliukas = 2; skaitliukas * skaitliukas <= skaicius; skaitliukas++){
            // Isimame visus skaiciaus kartotinius pradedant nuo skaitliukas * skaitliukas iki paduoto funkcijai skaiciaus
            if (ar_Pirminis[skaitliukas]){
                for (int vidinis_Skaitliukas = skaitliukas * skaitliukas; vidinis_Skaitliukas <= skaicius; vidinis_Skaitliukas = vidinis_Skaitliukas + skaitliukas){
                    ar_Pirminis[vidinis_Skaitliukas] = false;
                }
            }
        }
        pabaiga = System.nanoTime();
        Rezultato_Isvedimas(skaicius, ar_Pirminis);
    }

    private static void Algoritmo_Trukme(){
        trukme = pabaiga - pradzia;

        long valandos = trukme / 3600000000000L;
        trukme = trukme % 3600000000000L;

        long minutes = trukme / 60000000000L;
        trukme = trukme % 60000000000L;

        long sekundes = trukme / 1000000000L;
        trukme = trukme % 1000000000L;

        long milisekundes = trukme / 1000000L;
        trukme = trukme % 1000000L;

        System.out.println("Algoritmo trukme: " + valandos + " valandos " + minutes + " minutes " + sekundes + " sekundes " + milisekundes + " milisekundes " + trukme + " nanosekundes");
    }

    private static void Rezultato_Isvedimas(int skaicius, boolean[] ar_Pirminis){
        System.out.println("Isvedame visus rastus pirminius iki " + skaicius);
//
//        for (int skaitliukas = 0; skaitliukas < ar_Pirminis.length; skaitliukas++){
//            if (ar_Pirminis[skaitliukas]){
//                System.out.println(skaitliukas);
//            }
//        }
        Algoritmo_Trukme();
    }
}
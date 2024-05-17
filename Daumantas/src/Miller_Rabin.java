import java.math.BigInteger;
import java.util.Random;

public class Miller_Rabin {
    private static long pradzia;
    private static long pabaiga;
    private static long trukme;

    public static void Algoritmas(int skaicius, int tikslumas){
        // Nustatome pradini ekstra atveji, kai programa grazina reiksmes nevykdant programos
        if (skaicius == 3){
//            System.out.println("Skaicius " + skaicius + " yra pirminis");
            System.out.println(skaicius);
            return;
        }
        // Paleidziame algoritma su perduotais duomenimis
        Rezultato_Isvedimas(skaicius, Miller_Rabin_Ciklas(skaicius, tikslumas));
    }

    // Liudytojas reikalingas irodymui, kad funkcijai paduotas skaicius yra sudetinis.
    private static boolean Ar_Sudetinis(int skaicius_A, int vartotojo_Skaicius){
        int lyginis_Skaicius = vartotojo_Skaicius - 1;
        int T_Reiksme = Rask_T(lyginis_Skaicius);
//        System.out.println("Rasta T reiksme = " + T_Reiksme);
        int U_Reiksme = (int) (lyginis_Skaicius / Math.pow(2, T_Reiksme));
//        System.out.println("Rasta U reiksme = " + U_Reiksme);

        int x0_reiksme = Modulinis_Eksponencijavimas(skaicius_A, U_Reiksme, vartotojo_Skaicius);
//        System.out.println("Rasta x0 reiksme = " + x0_reiksme);
        int x1_reiksme;

        for (int skaitliukas = 0; skaitliukas < T_Reiksme - 1; skaitliukas++){
            x1_reiksme = Modulinis_Eksponencijavimas(x0_reiksme, 2, vartotojo_Skaicius);
//            System.out.println("x1 reiksme = " + x1_reiksme);
            if ((x1_reiksme == 1) && (x0_reiksme != 1) && (x0_reiksme != lyginis_Skaicius)){
//                System.out.println("Reached");
                return true;
            }
            x0_reiksme = x1_reiksme;
        }

        // Patikriname ar paskutine reiskinio reiksme - x_t yra nelygi 1
        if ((Modulinis_Eksponencijavimas(x0_reiksme, 2, vartotojo_Skaicius)) != 1){
            return true;
        }

        // Jei nesurandame skaiciaus su kuriuo galetume irodyti, kad vartotojo_Skaicius yra sudetinis, graziname, kad jis pirminis
        return false;
    }

    // Randame T reiksme: 2^T * U = skaicius - 1
    private static int Rask_T(int lyginis_Skaicius){
        int laikinas_Kintamasis = lyginis_Skaicius;
        int T_Reiksme = 0;
        while(true){
            if (laikinas_Kintamasis % 2 > 0){

                return T_Reiksme;
            } else {
                laikinas_Kintamasis = laikinas_Kintamasis / 2;
                T_Reiksme++;
            }
        }
    }

    // lyginis_Skaicius = skaicius - 1
    // kadangi paduotas skaicius yra nelyginis, tai atemus 1, jis tampa lyginiu.
    private static int Atsitiktinis_A(int lyginis_Skaicius){
        Random atsitiktinis_Skaicius = new Random();
        int skaicius = atsitiktinis_Skaicius.nextInt(lyginis_Skaicius - 2) + 2;
//        System.out.println("Atsitiktinis A = " + skaicius);
        return skaicius;
    }

    // Apskaiciuojame: A^U mod N
    private static int Modulinis_Eksponencijavimas(int skaicius_A, int skaicius_U, int skaicius_N){
        BigInteger pagrindas = BigInteger.valueOf(skaicius_A);
        BigInteger laipsnis = BigInteger.valueOf(skaicius_U);
        BigInteger modulis = BigInteger.valueOf(skaicius_N);
        BigInteger rezultatas =  pagrindas.modPow(laipsnis, modulis);
        return rezultatas.intValue();
    }

    private static String Miller_Rabin_Ciklas(int skaicius, int tikslumas){
        pradzia = System.nanoTime();
        // Jei nors su viena atsitiktine reiksme galime irodyti, kad skaicius sudetinis, tai is karto graziname, kad jis sudetinis
        int atsitiktinis;
        for (int skaitiklis = 0; skaitiklis < tikslumas; skaitiklis++){
            atsitiktinis = Atsitiktinis_A(skaicius - 1);
            if (Ar_Sudetinis(atsitiktinis, skaicius)){
                // Jei liudytojo_Paieska grazino 'true', reiskias buvo rastas skaicius ir galime irodyti, kad vartotojo skaicius yra sudetinis
                pabaiga = System.nanoTime();
                return "sudetinis";
            }
        }
        pabaiga = System.nanoTime();
        // Neradome daliklio vartotojo skaiciui, reiskias skaicius yra pirminis
        return "pirminis";
    }


    // Konvertuojame nanosekundes i valandas, minutes, sekundes, milisekundes, nanosekundes
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

    private static void Rezultato_Isvedimas(int skaicius, String rezultatas){
        System.out.println("Skaicius " + skaicius + " yra " + rezultatas);
        Algoritmo_Trukme();
//        if (rezultatas.equals("pirminis")){
//            System.out.println(skaicius);
//        }

    }
}
package Proje;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Kuyruk {


    public static int satirsayisi(File f) throws FileNotFoundException {


        Scanner dosya = new Scanner(f);
        int satir=0;

        while(dosya.hasNextLine()) {dosya.nextLine();satir++;}
        dosya.close();

        return satir;
    }


    public ArrayList<Process> nesneolustur() throws FileNotFoundException {
        /*


        STABLE ARRAYLİST YAP


         */
        File f = new File("C:/Users/ahmet/Desktop/giris.txt");

        Scanner dosyaIslem = new Scanner(f);
        int satir=satirsayisi(f);


        int sayac=0;
        int[][] dizi = new int[satir][6];


        Process[] stable = new Process[satir];


        while(dosyaIslem.hasNextLine()) {
        String satirstring=dosyaIslem.nextLine();

        String[] arrSplit = satirstring.split(", ");
        int dizisatir = 1;
        for (String arrSplit1 : arrSplit) {

            dizi[sayac][dizisatir] =  Integer.parseInt(arrSplit1);

            dizisatir++;

        }
        dizi[sayac][0]= sayac;
        dizi[sayac][4]= 0;
        dizi[sayac][5]= 0;
        Process kukla = new Process(dizi[sayac]);
        stable[sayac] = kukla;
        //ver.add(kukla);
        //System.out.println(stable[sayac]);
        sayac++;
    }
        /*
        System.out.println();

        for(int i = 0; i<satir; i++) {
          for(int j = 0; j<4;j++) {

               System.out.println(dizi[i][j]);
           }
           System.out.println("\n");
        }*/

        ArrayList<Process> ver = new ArrayList<Process>(Arrays.asList(stable));
        return ver;
    }
    public ArrayList<Process> varisZamaniBelirle(ArrayList<Process> kuyruk, int saniye){


        ArrayList<Process> araci = new ArrayList<Process>();
        /*
        int sayac=0;
        for (Process eleman:kuyruk) {
            if (eleman.varisZamani == saniye) {
                sayac++;
            }
        }
        Process[] araci= new Process[sayac];
        */

        for (Process eleman:kuyruk) {
            if(eleman.varisZamani == saniye) {
                araci.add(eleman);
            }
        }

    return araci;
    }


    public ArrayList<ArrayList> kuyrukCheck(ArrayList<Process> kuyrukSaniye, ArrayList<Process> realTimeLastList, ArrayList<Process> userJobLastList){

        for (Process eleman :kuyrukSaniye) {

            switch (eleman.oncelik) {
                case 0:
                    realTimeLastList.add(eleman);
                    break;
                default:
                    userJobLastList.add(eleman);
                    break;
            }
        }
        ArrayList<ArrayList> listeFull = new ArrayList<ArrayList>();
        listeFull.add(realTimeLastList);
        listeFull.add(userJobLastList);
        listeFull.set(0, realTimeLastList);
        listeFull.set(1, userJobLastList);
        return listeFull;
    }
    public void ekran(int saniye,int Statament,Process activeProcess){
        // 0 başladı / 1 yürütülüyor / 2 askıda / 3 sonlandı / 4 zamanaşımı
        switch (Statament) {
            case 0:
                System.out.println(saniye + " sn process başladı\t\t(id:"+activeProcess.Id+"\töncelik:"+activeProcess.oncelik+"\tkalan süre:"+activeProcess.calismaZamani+" sn)");
                break;
            case 1:
                System.out.println(saniye + " sn process yürütülüyor\t\t(id:"+activeProcess.Id+"\töncelik:"+activeProcess.oncelik+"\tkalan süre:"+activeProcess.calismaZamani+" sn)");
                break;
            case 2:
                System.out.println(saniye + " sn process askıda\t\t(id:"+activeProcess.Id+"\töncelik:"+activeProcess.oncelik+"\tkalan süre:"+activeProcess.calismaZamani+" sn)");
                break;
            case 3:
                System.out.println(saniye + " sn process sonlandı\t\t(id:"+activeProcess.Id+"\töncelik:"+activeProcess.oncelik+"\tkalan süre:"+activeProcess.calismaZamani+" sn)");
                break;
            case 4:
                System.out.println(saniye + " sn process zamanaşımı\t\t(id:"+activeProcess.Id+"\töncelik:"+activeProcess.oncelik+"\tkalan süre:"+activeProcess.calismaZamani+" sn)");
                break;
        }
    }
    public ArrayList<ArrayList> realTime(ArrayList<Process> realTimeLastList,Process activeProcess, int saniye) {

        if (activeProcess.Id == -1) {
            activeProcess = realTimeLastList.get(0);
            //activeProcess.Id = realTimeLastList.get(0).Id;
            ekran(saniye, 0,activeProcess);
            activeProcess.calismaZamani -=1;

        }else if((activeProcess.oncelik==0) && (activeProcess.calismaZamani !=0)){
            ekran(saniye, 1,activeProcess);
            activeProcess.calismaZamani -=1;
        }else if((activeProcess.oncelik==0) && (activeProcess.calismaZamani ==0)) {
            ekran(saniye, 3, activeProcess);
            realTimeLastList.remove(0);
            if (!realTimeLastList.isEmpty()) {
                activeProcess = realTimeLastList.get(0);
                ekran(saniye, 0, activeProcess);
                activeProcess.calismaZamani -= 1;
            }
        } else{//onceligi 0 dan farklı olanlar girecek
              //flag true olacak
              //saniye degeri asimzamanina atilir.
              //aktifprocess realtime ile degiscek
            activeProcess.flag=1;
            activeProcess.asimZamani=saniye;
            if(activeProcess.oncelik!=3){activeProcess.oncelik++;}


            }


        /*
            Process ilkim = realTimeLastList.get(0);
            System.out.println(ilkim.Id);
            System.out.println(realTimeLastList);
            System.out.println("Gerçek zamanlı veri...");
        */
            ArrayList<ArrayList> realTimeReturn = new ArrayList<ArrayList>();
            ArrayList<Process> processArray = new ArrayList<Process>();
            processArray.add(activeProcess);
            realTimeReturn.add(processArray);
            realTimeReturn.add(realTimeLastList);
            realTimeReturn.set(0, realTimeLastList);
            realTimeReturn.set(1, processArray);

        return realTimeReturn;

    }

    public ArrayList<ArrayList> UserjobQueue(ArrayList<Process> userJobList,Process activeProcess, int saniye) {


        System.out.println(userJobList);
        System.out.println("UserjobQueue veri...");
        ArrayList<ArrayList> userJobReturn = new ArrayList<ArrayList>();
        ArrayList<Process> processArray = new ArrayList<Process>();
        userJobReturn.add(processArray);
        userJobReturn.add(userJobList);
        userJobReturn.set(0,userJobReturn);
        userJobReturn.set(1,processArray);

        return userJobReturn;

    }
}

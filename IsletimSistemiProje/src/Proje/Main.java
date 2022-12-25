package Proje;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static Proje.Kuyruk.satirsayisi;


public class Main {


    public static void main(String[] args) throws FileNotFoundException {
       Kuyruk kuyruk=new Kuyruk();
       int dongusayisi =kuyruk.donguSayisi();
       ArrayList<Process> realTimeLastList = new ArrayList<Process>();
       ArrayList<Process> userJobLastList = new ArrayList<Process>();
       ArrayList<ArrayList> lastListFull = new ArrayList<ArrayList>();
       ArrayList<ArrayList> realTimeGet = new ArrayList<ArrayList>();
       ArrayList<ArrayList> UserTimeGet = new ArrayList<ArrayList>();
       ArrayList<Process> araArray = new ArrayList<Process>();
       ArrayList<ArrayList> zamanAsimi = new ArrayList<ArrayList>();
       ArrayList<Process> bosArray = new ArrayList<Process>();
       int[] baslangicDizi = {-1,-1,-1,-1,0,0};
       Process activeProcess = new Process(baslangicDizi);
       ArrayList<Process> al = kuyruk.nesneolustur();
        System.out.println("\u001B[33m"+"sadasdasd");
       for(int saniye=0;saniye<dongusayisi;saniye++){

           lastListFull = kuyruk.kuyrukCheck(kuyruk.varisZamaniBelirle(al,saniye), realTimeLastList, userJobLastList);
           realTimeLastList = lastListFull.get(0);
           userJobLastList = lastListFull.get(1);


           if (!realTimeLastList.isEmpty()){

               realTimeGet = kuyruk.realTime(realTimeLastList, activeProcess,saniye);
               araArray = realTimeGet.get(1);
               activeProcess = araArray.get(0);
               //System.out.println(activeProcess);

               if(activeProcess.flag==1){
                   for (Process key:userJobLastList) {
                        if(key.Id==activeProcess.Id){
                            key=activeProcess;
                        }
                   }
                   activeProcess=realTimeLastList.get(0);
               }

           }
           if (realTimeLastList.isEmpty()){

               UserTimeGet = kuyruk.UserjobQueue(userJobLastList , activeProcess,saniye);

               userJobLastList.clear();

                /*
               zamanAsimi = UserTimeGet.get(0);
               for (ArrayList aramaArray : zamanAsimi) {
                   for(Object prom : aramaArray){
                       bosArray.add((Process) prom);
                   }
               }
                */
               araArray = UserTimeGet.get(1);
               activeProcess = araArray.get(0);

            }

            /*
           for(Process key:bosArray){
               if (key.flag==0) {
                   if (saniye - key.asimZamani == 20) {
                       System.out.println("process zaman aşımında sn: " + saniye + "process: " + key);
                       bosArray.remove(key);
                       // Ekran fonksiyonundan zaman asimina ugradi yazicaz
                       //userjoblastListten asima ugrayan proses silinecek
                   }
               }
           }*/

           //System.out.println("\n-----realTime------"+realTimeLastList+"\n ------------------");
           //System.out.println("\n-----userLast------"+userJobLastList+"\n ------------------");
       }
    }
}
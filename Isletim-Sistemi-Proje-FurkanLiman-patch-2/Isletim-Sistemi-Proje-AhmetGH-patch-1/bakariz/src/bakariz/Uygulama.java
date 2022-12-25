package bakariz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




public class Uygulama {
	
	public static int satirsayisi(File f) throws FileNotFoundException {
		

		Scanner dosya = new Scanner(f);		
		int satir=0;
		
		while(dosya.hasNextLine()) {dosya.nextLine();satir++;}
		dosya.close();
		
		return satir;
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		
		File f = new File("C:/Users/ahmet/Desktop/giris.txt");
				
		Scanner dosyaIslem = new Scanner(f);
		
		int satir=satirsayisi(f);
		
		
		int sayac=0;
		int[][] dizi = new int[satir][4];
		
		
		process[] stable = new process[satir];
		
		while(dosyaIslem.hasNextLine()) {
			String satirstring=dosyaIslem.nextLine();
			
			String[] arrSplit = satirstring.split(", ");
			int dizisatir = 1;
			for (String arrSplit1 : arrSplit) {
				
				dizi[sayac][dizisatir] =  Integer.parseInt(arrSplit1);
				
				dizisatir++;
	            
			}
			dizi[sayac][0]=sayac;
			process kukla = new process(dizi[sayac]);
			stable[sayac] = kukla;
			System.out.println(stable[sayac]);
			sayac++;
		}
		
		System.out.println();
		
		//System.out.println(satir);
		for(int i = 0; i<satir; i++) {
			for(int j = 0; j<4;j++) {	
				
				System.out.println(dizi[i][j]);
			}
			System.out.println("\n");
		}
        

}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lvqtitanic;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author nightWalker
 */
public class DataInitial {
        public String [][] dataInitial = new String[2][5];
        public Double [][] initialSet = new Double[2][5];
        public BufferedReader br;
        public String line;
        
        public void loadFile()
        {
            try {
                
            br=new BufferedReader(new FileReader("src/Dataset/DataInitial.csv"));
            int x=0;
            while((line=br.readLine())!=null){
                
                String[] Data=line.split(",");
                //System.out.println("Id:"+siswa[0]+" Nama:"+siswa[1]+" Jenis Kelamin:"+siswa[2]+" Alamat:"+siswa[3]+" Tanggal Lahir:"+siswa[4]);
                for (int i = 0; i < 5; i++) {
                    dataInitial[x][i]=Data[i];
                    //trainingSet[x][i]= Double.parseDouble(Data[i]);
                }
                x++;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }
        
        public void readFile()
        {
            for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                        System.out.print(dataInitial[i][j]+" ");
                    }
                System.out.println("");
            }
        }
        
        public void convertData()
        {
            for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                       initialSet[i][j]= Double.parseDouble(dataInitial[i][j]);
                    }
            }
        }
        
        
        public void readConvert()
        {
            for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                        System.out.print(initialSet[i][j]+" ");
                    }
                System.out.println("");
            }
        }
}

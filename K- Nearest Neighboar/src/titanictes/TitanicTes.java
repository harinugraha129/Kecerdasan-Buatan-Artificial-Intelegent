/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titanictes;

/**
 *
 * @author nightWalker
 */
public class TitanicTes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Integer [][] trainingSet = new Integer[800][6];
        DataTraining Knn = new DataTraining();
        Knn.loadFile();
        Knn.readFile();       
        Knn.convertData();
        Knn.readConvert();
        
        DataTesting KNn = new DataTesting();
        KNn.loadFile();
        KNn.readFile();
        KNn.convertData();
        KNn.readConvert();
        
        double temp=0;
        Double jrkData [][]= new Double[91][800];
        Double idData []= new Double[91];
        Double classTesting []= new Double[91];
        Double classTraining []= new Double[800];
        double tempClass[]=new double[91];
        int accuration=0;
        
        for (int i = 0; i < 91; i++) {
            idData[i]= KNn.testingSet[i][0];
            classTesting[i]= KNn.testingSet[i][1];
            
            //Menghitung Jarak K setiap data
            for (int j = 0; j < 800; j++) {
                
                jrkData[i][j] = ((KNn.testingSet[i][2]-Knn.trainingSet[j][2])*(KNn.testingSet[i][2]-Knn.trainingSet[j][2]))+((KNn.testingSet[i][3]-Knn.trainingSet[j][3])*(KNn.testingSet[i][3]-Knn.trainingSet[j][3]))+((KNn.testingSet[i][4]-Knn.trainingSet[j][4])*(KNn.testingSet[i][4]-Knn.trainingSet[j][4]));
                classTraining[j]=Knn.trainingSet[j][1];
                
            }
            
            //Sorting Bubble untuk menentukan K Terdekat
            for (int x = 0; x < 800-1; x++) {
                //System.out.println(x);
                for (int y = 0; y < 800-x-1 ; y++) {
                    
                    //System.out.println(jrkData[i][y]+" "+jrkData[i][y+1]);
                    
                    if (jrkData[i][y] > jrkData[i][y+1]) {
                        
                        temp=jrkData[i][y + 1];
                        jrkData[i][y + 1]=jrkData[i][y];
                        jrkData[i][y]=temp;
                        
                        temp=classTraining[y+1];
                        classTraining[y+1]=classTraining[y];
                        classTraining[y]=temp;

                    }
                }
            }
            int one=0;
            int zero=0;
            
            //Mencari Nilai K Terdekat
            for (int j = 0; j < 19; j++) {
                if (classTraining[j]==0.0) {
                     zero++;                   
                }else{
                    one++;
                }
            }
            //Cek Class hasil KNN
            if (one>zero) {
                tempClass[i]=0.0;
            }else{
                tempClass[i]=1.0;
            }
            
            //Cek Kesesuaian Hasil dengan Yang Sesungguhnya
            if (classTesting[i]==tempClass[i]) {
                accuration++;
            }
            
            
        
        }

        
        

        for (int i = 0; i < 91; i++) {
            System.out.println((i+1)+". "+idData[i]+" Class . "+ classTesting[i]+" "+tempClass[i]);
        }
        
        System.out.println("Akurasi "+ ((accuration/91.0)*100.0));
          
    }
    
}

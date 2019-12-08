/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lvqtitanic;

/**
 *
 * @author nightWalker
 */
public class LVQTitanic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DataTraining LVQ = new DataTraining();
        LVQ.loadFile();
//        LVQ.readFile();       
        LVQ.convertData();
//        LVQ.readConvert();
//        
        DataTesting Lvq = new DataTesting();
        Lvq.loadFile();
//        Lvq.readFile();
        Lvq.convertData();
//        Lvq.readConvert();
        
        DataInitial LVq = new DataInitial();
        LVq.loadFile();
//        LVq.readFile();
        LVq.convertData();
//        LVq.readConvert();

        double temp=0;
        double weight[][] = new double[2][3];
        double learningRate = 0.1;
        double reducingFactor = 0.1;
        Double jrkData []= new Double[2];
        Double idData []= new Double[91];
        Double classTesting []= new Double[91];
        Double classTraining []= new Double[800-2];
        double tempClass[]=new double[91];
        int accuration=0;
        
        for (int i = 0; i < 2; i++) {
            //Mendapatkan nilai Weight
            for (int j = 0; j < 3; j++) {
                weight[i][j]=LVq.initialSet[i][j];
            }     
        }
        
        //Menghitung Jarak setiap data
        for (int i = 0; i < 800-2; i++) {
            for (int j = 0; j < 2; j++) {
                jrkData[j] = ((LVq.initialSet[j][2]-LVQ.trainingSet[i][2])*(LVq.initialSet[j][2]-LVQ.trainingSet[i][2]))+((LVq.initialSet[j][3]-LVQ.trainingSet[i][3])*(LVq.initialSet[j][3]-LVQ.trainingSet[i][3]))+((LVq.initialSet[j][4]-LVQ.trainingSet[i][4])*(LVq.initialSet[j][4]-LVQ.trainingSet[i][4]));
            }
            //cek jarak terdekat
            if (jrkData[0]<jrkData[1]) {
                //cek kelas apakah sama dengan initial
                if (LVq.initialSet[0][1]==LVQ.trainingSet[i][1]) {
                    //ubah nilai weightnya
                    for (int j = 2; j < 5; j++) {
                        LVq.initialSet[0][j]=LVq.initialSet[0][j]+(learningRate*(LVQ.trainingSet[i][j]-LVq.initialSet[0][j]));
                    }
                }else{
                    for (int j = 2; j < 5; j++) {
                        LVq.initialSet[0][j]=LVq.initialSet[0][j]-(learningRate*(LVQ.trainingSet[i][j]-LVq.initialSet[0][j]));
                    }
                }
            }else{
                if (LVq.initialSet[1][1]==LVQ.trainingSet[i][1]) {
                    for (int j = 2; j < 5; j++) {
                        LVq.initialSet[1][j]=LVq.initialSet[1][j]+(learningRate*(LVQ.trainingSet[i][j]-LVq.initialSet[1][j]));
                    }
                }else{
                    for (int j = 2; j < 5; j++) {
                        LVq.initialSet[1][j]=LVq.initialSet[1][j]-(learningRate*(LVQ.trainingSet[i][j]-LVq.initialSet[1][j]));
                    }
                }
            }        
//            reducingFactor = 0.1 * learningRate ;
//            learningRate = learningRate - reducingFactor;
        }
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(LVq.initialSet[i][j]+" ");
            }
            System.out.println();
        }       
        System.out.println("Learning Rate : " + learningRate);
            //Menghitung Jarak setiap data
            for (int i = 0; i < 91; i++) {
                for (int j = 0; j < 2; j++) {
                    jrkData[j] = ((LVq.initialSet[j][2]-Lvq.testingSet[i][2])*(LVq.initialSet[j][2]-Lvq.testingSet[i][2]))+((LVq.initialSet[j][3]-Lvq.testingSet[i][3])*(LVq.initialSet[j][3]-Lvq.testingSet[i][3]))+((LVq.initialSet[j][4]-Lvq.testingSet[i][4])*(LVq.initialSet[j][4]-Lvq.testingSet[i][4]));
                }
                //cek jarak terdekat
                if (jrkData[0]<jrkData[1]) {
                    //cek kelas apakah sama dengan initial
                    if (LVq.initialSet[0][1]==Lvq.testingSet[i][1]) {
                        //Menghitung akurasi data yang sesuai
                        accuration++;
                    }
                }else{
                    if (LVq.initialSet[1][1]==Lvq.testingSet[i][1]) {
                        accuration++;
                    }
                }
            }
        
        System.out.println("Akurasi "+ ((accuration/91.0)*100.0));
    }
    
}

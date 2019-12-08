import pandas as pd
import numpy as np
import random
import math
import matplotlib.pyplot as plt
from distance import euclidean_D, manhattan_D, minkowski_D, cosine_D, canberra_D, chebishev_D
from dataset import dtTest, dtTrain, n_feature, CFold

    
csv_dataInitial = pd.read_csv("dataset/dataBobot.csv", delimiter=',', header=0)
dataInitial = np.array(csv_dataInitial)                         #konversi bobot csv menjadi array
weight = dataInitial.astype(float)                              #konversi bobot menjadi tipe float
n_dataInitial = len(weight[:,0])                                #menghitung banyaknya bobot
n_epoch = 1                                                     #Jumlah Epoh
alfa_ = 0.001                                                    #learning Rate
reducingFactor_ = 0.01                                           #Reducing Faktor

# Menyiapkan bobot
bobot = []
for a in range(0, CFold):
    bobot.append(weight)

# Cross Fold Bergilir
for a in range(0, CFold):
    print("Data Cross Fold ke - " + str(a+1))
    alfa = alfa_                                                    
    reducingFactor = reducingFactor_                                           
    itr = 0

    # Menghitunga Banyak Data
    n_dataTraining	= len(dtTrain[a])

    # Training LVQ
    while(itr < n_epoch):
        print("Epoch ke - " + str(itr+1))
        for i in range(0, int(n_dataTraining)):
            data =[]
            label=[]
            #Menghitung Jarak Data
            for j in range(0, n_dataInitial ):
                
                x = dtTrain[a][i]
                y = bobot[a][j]
                
                data.append(chebishev_D(x,y))
                label.append(bobot[a][j][n_feature])

                dataJarak = {'jarak' : pd.Series(data),
                            'label' : pd.Series(label)} 
            # end for
            df = pd.DataFrame(dataJarak, columns = ['jarak', 'label'])
            df = df.sort_values(by=['jarak'])
            
            labelTemp = df.iloc[0,1]
            labelTemp = int(labelTemp)
            
            if labelTemp == dtTrain[a][i][n_feature]:
                for x in range(0, n_feature-1):
                    bobot[a][labelTemp][x] = bobot[a][labelTemp][x] + ( alfa * ( bobot[a][labelTemp][x]-dtTrain[a][i][x] ) )
                    
            else:
                for x in range(0, n_feature-1):
                    bobot[a][labelTemp][x] = bobot[a][labelTemp][x] - ( alfa * ( bobot[a][labelTemp][x]-dtTrain[a][i][x] ) )
        itr+=1
        rF = reducingFactor * alfa
        alfa = alfa - rF

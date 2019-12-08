import pandas as pd
import numpy as np
import random
import math
import matplotlib.pyplot as plt
from sklearn import datasets, linear_model
from sklearn.metrics import accuracy_score
from sklearn.metrics import recall_score
from sklearn.model_selection import KFold # import KFold
from sklearn.model_selection import StratifiedKFold
from sklearn.model_selection import train_test_split
from sklearn.metrics import confusion_matrix
from sklearn.metrics import classification_report
from distance import euclidean_D, manhattan_D, minkowski_D, cosine_D, chebishev_D, canberra_D
from dataset import dtTest, dtTrain, n_feature, CFold


K = 5
Avg = 0

for a in range(0, CFold):
	accuracy = 0.0
	y_true = []
	y_pred = []	

	# Menghitunga Banyak Data Testing
	n_dataTesting	= len(dtTest[a])
	
	# Menghitung KNN 
	for i in range(0, n_dataTesting):
		Distance =[]
		label=[]
		print("Data Cross Fold ke - " + str(a+1) + " & Data ke - " + str(i+1))
		# Menghitunga Banyak Data Testing
		n_dataTraining	= len(dtTrain[a])

		#Menghitung Jarak Data
		for j in range(0, n_dataTraining ):
			x = dtTest[a][i]
			y = dtTrain[a][j]
			Distance.append(euclidean_D(x,y))
			label.append(dtTrain[a][j][n_feature])
			dataJarak = {'jarak' : pd.Series(Distance),
						'label' : pd.Series(label)} 

		# Mencari data terdekat dengan K
		df = pd.DataFrame(dataJarak, columns = ['jarak', 'label'])
		df = df.sort_values(by=['jarak'])
		df = df.head(K)
		
		# mengambil mayoritas dari label
		y_pred.append(df.groupby(["label"]).count().sort_values(by=['jarak']).
		tail(1).index.get_level_values('label')[0])
	
		# Mendapatkan Label Sebenarnya
		y_true.append(dtTest[a][i][n_feature])	
		

	# Mendapatkan Akurasi Dan Recall
	recall = recall_score(y_true, y_pred, average='micro')
	print("\nData Cross Fold ke - " + str(a+1))
	acc = accuracy_score(y_true, y_pred)
	Avg = Avg + acc
	print('Accuracy: %f' % acc)
	print('Recall: %f' % recall)
	print("\n")
	

# print(classification_report(y_true,y_pred))
print("\nRata Rata Akurasi : " + str(Avg*100/CFold)+" %")
print("Jumlah Data Training : " +str(n_dataTraining))
print("Jumlah Data Testing : " +str(n_dataTesting))
print("Jumlah Fiture : " + str(n_feature))

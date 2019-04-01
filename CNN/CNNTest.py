# -*- coding: utf-8 -*-

from keras.models import load_model
import cv2
import numpy as np

imageTargetSize = 64

#testings trained model

labels = ['daisy','dandelion','roses','sunflowers','tulips']

model = load_model('Model/trainedModel.h5')


img = cv2.imread('fakeRose.jpg')
imgResized = cv2.resize(img,(imageTargetSize,imageTargetSize))
imgResized = np.reshape(imgResized,[1,imageTargetSize,imageTargetSize,3])
testResult = model.predict(imgResized)


print(testResult)
for i in range(len(labels)):
	if testResult[0][i]==1:
		print('Image Predictions: ',labels[i])
		img = cv2.resize(img, (500, 500))
		cv2.imshow('Image', img)
		cv2.waitKey(0)
		cv2.destroyAllWindows()
		break

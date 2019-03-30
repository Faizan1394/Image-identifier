# -*- coding: utf-8 -*-

from keras.models import Sequential
from keras.layers import Convolution2D
from keras.layers import MaxPooling2D
from keras.layers import Flatten
from keras.layers import Dense
from keras.preprocessing.image import ImageDataGenerator

classifier = Sequential()
imageTargetSize = 64

#Convolution
def convolution(i):
	numKernels = 32
	width = 3          # width of kernel
	height = 3		   # height of kernel
	kernel_size = (height,width) #size of kernel
	inputShape = (imageTargetSize,imageTargetSize,3)       # 64x64 RGB(3) picture
	
	if i==-1:
		          #num kernels, kernel_size(width and height), strides, padding
		conv2d = Convolution2D(numKernels, kernel_size,data_format="channels_last",input_shape=inputShape,activation='relu')
		classifier.add(conv2d)
	else:
		conv2d = Convolution2D(numKernels, kernel_size,data_format="channels_last",activation='relu')
		classifier.add(conv2d)


#max pooling
def maxPooling():
			#using default parameters for now
	mPool = MaxPooling2D()
	classifier.add(mPool)
	
#flattening
def flattening():
	flat = Flatten()
	classifier.add(flat)
	
def ann():
	den = Dense(128,activation = 'relu')
	classifier.add(den)
	output = Dense(5,activation = 'softmax')
	classifier.add(output)
	
	
#data augmentation
#creates more images from our images by applying random transformations to them
# such as rotaion 
def dataAugmentation():
	scale = 1.0/255
	shearRange = 0.2
	zoomRange = 0.2
	numTrainImages = 3505
	numTestImages = 165
	numEpochs = 25
	
	trainDataGeneration = ImageDataGenerator(rescale = scale,shear_range = shearRange,
											  zoom_range = zoomRange, horizontal_flip = True)
	
	testDataGeneration = ImageDataGenerator(rescale = scale)
	
	trainData = trainDataGeneration.flow_from_directory('dataset/training_set',target_size =(imageTargetSize,imageTargetSize),
												   batch_size = 32,class_mode='categorical')
	
	testData = testDataGeneration.flow_from_directory('dataset/test_set',target_size =(imageTargetSize,imageTargetSize),
												   batch_size = 32,class_mode='categorical')
	
	classifier.fit_generator(trainData,steps_per_epoch = numTrainImages,epochs=numEpochs,
						  validation_steps = numTestImages,validation_data = testData);


def run():
	convolution(-1)
	maxPooling()
	
	#multiple convolutional layers for better result
	for i in range(2):
		convolution(1)
		maxPooling()
	flattening()
	ann()
	classifier.compile(optimizer='adam',loss='categorical_crossentropy',metrics=['accuracy'])
	dataAugmentation()
	classifier.save('Model/trainedModel.h5')
 
run()
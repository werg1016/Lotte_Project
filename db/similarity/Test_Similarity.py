# -*- coding: utf-8 -*-

from scipy import linalg, mat, dot
import numpy as np
import sys
import math

def main(argument1, argument2) :
    text = argument1+ " " + argument2
    table = dict()
    wcnt = 0 
    for word in text.split():
        if word not in table:
            table[word] = 0
            wcnt = wcnt + 1
    martix = [[0 for col in range(2)]for row in range(len(table))]

    make(table, argument1, martix,0)
    make(table, argument2, martix,1)

    aa= []
    bb =[]
    for i in range(len(martix)):
        aa.append(martix[i][0])
        bb.append(martix[i][1])

    return vector(aa,bb)

#하나의 document씩 한줄씩 행렬 생성
def make(table,text,martix, cnt):
    i = 0
    for word in text.split():
        if word in table:
            table[word] += 1

    for item in table.items():
        martix[i][cnt] = item[1]
        table[item[0]] = 0
        i = i+1


#코사인 벡터 계산
def vector(aA,bB):

    A = aA
    B = bB
    mA = mat(A)
    mB = mat(B)


    
    #오류제거 코드
    np.seterr(divide='ignore', invalid='ignore')

    cossim_AB = dot(mA, mB.T) / (linalg.norm(mA) * linalg.norm(mB))

    if cossim_AB < 0 or math.isnan(cossim_AB):
        cossim_AB = 0

    if aA[0] == bB[0] and aA[1] == bB[1]:
        cossim_AB = 1

    return int(cossim_AB*100);

def quick_sort(array,data):
    left = []
    center = []
    right = []

    lefts =[]
    centers = []
    rights = []
    
    if len( array ) in [ 0, 1 ]: 
         return array
    pivot = array[ 0 ]
    for i in array:
         if i < pivot:
             left.append( i )
         elif i > pivot:
             right.append( i )
         else:
             center.append( i )
    return quick_sort( left ) + center + quick_sort( right )

if __name__ == "__main__" :
    filename1 = open(sys.argv[1])
    filename2 = open(sys.argv[2])

    content1 = filename1.read()
    content2 = filename2.read()

    data_split = content2.split("////")
    sss=[]
    ddd = dict()
    for i in range(len(data_split)):
        a= data_split[i]
        sss.append(main(content1,a))


    searchdata=[]
    for i in range(5):
        
        a = max(sss)
     
        if(a < 10):
            break
        d = sss.index(a)

        searchdata.append(data_split[d])
        sss[d] = -99999
        

    for i in range(len(searchdata)):
        print searchdata[i]

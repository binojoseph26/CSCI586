import requests
import pandas
import csv

# with open('.csv', 'rb') as f:
#     reader = csv.reader(f)
#     for row in reader:
#         print row

data = pandas.read_csv("df_nan5.csv", header=0)
col_a = list(data.title)
col_b = list(data.year)

movies = {}

for i in range(len(col_a)): 
    movies[col_a[i]] = str(col_b[i])


file = open("thumbs6.txt","w")
count = 0
print (movies)
for key in col_a:
    url = 'http://www.theimdbapi.org/api/find/movie?title='+key+'&apikey=760e0f43'
    response = requests.get(url)
    data=[]
    if(response):
        data = response.json()
    if(data and len(data)>0):
        file.write((data[0]["poster"]["thumb"]))
    else:
        file.write("Empty")
    file.write("\n")
    count+=1
    if(count%50==0):
        print (count)

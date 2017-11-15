import requests
import pandas
import csv

# with open('.csv', 'rb') as f:
#     reader = csv.reader(f)
#     for row in reader:
#         print row

data = pandas.read_csv("IMDB-Movie-Data.csv", header=0)
col_a = list(data.Title)
col_b = list(data.Year)

movies = {}

for i in range(len(col_a)): 
    movies[col_a[i]] = str(col_b[i])


file = open("thumbs.txt","w")

print (movies)
for key in movies: 
    url = 'http://www.theimdbapi.org/api/find/movie?title='+key+'&year='+movies[key]
    response = requests.get(url)
    data = response.json()
    file.write((data[0]["poster"]["thumb"]))

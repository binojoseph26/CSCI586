import pandas as pd

a = pd.read_csv("USvideos.csv")
c = pd.read_csv("category2.csv")
merged = a.merge(c, on='category_id')
merged.to_csv("output_2.csv", index=False)
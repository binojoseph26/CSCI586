import pandas as pd

a = pd.read_csv("USvideos.csv")
b = pd.read_csv("category.csv")
b = b.dropna(axis=1)
merged = a.merge(b, on='category_id')
merged.to_csv("output.csv", index=False)
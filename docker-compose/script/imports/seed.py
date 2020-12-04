from pymongo import MongoClient
import json

DB_NAME = "ads"
COLLECTION_NAME = "ads"

mongo_client = MongoClient('mongodb://mongo:27017')

dbnames = mongo_client.list_database_names()

if DB_NAME not in dbnames:
    print("Start importing")
    mydb = mongo_client[DB_NAME]
    mycol = mydb[COLLECTION_NAME]

    with open(r'/imports/classified.json') as f:
        file_data = json.load(f)
        for d in file_data:
            d['_id'] = d.pop('id')
        mycol.insert_many(file_data)
    print("finished importing")
else:
    print("Already imported")
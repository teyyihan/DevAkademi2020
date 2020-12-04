FROM python:latest
COPY imports /imports
WORKDIR /imports
RUN pip install pymongo
CMD ["python","/imports/seed.py"]
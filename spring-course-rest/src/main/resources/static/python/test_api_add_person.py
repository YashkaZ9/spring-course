import requests

URL = "http://localhost:8080/people/"
person = {
    "name": "Python",
    "age": 33,
    "email": "python@python.py"
}
response = requests.post(url=URL, json=person)
if response.status_code == 200:
    print("Person was created.")
else:
    print("Person was not created.")
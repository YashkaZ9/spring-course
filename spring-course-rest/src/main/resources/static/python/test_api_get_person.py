import requests

id = int(input("Input person id: "))
URL = f"http://localhost:8080/people/{id}"
response = requests.get(url=URL)
if response.status_code == 404:
    error = response.json()
    print(f"[{error['timestamp']}] Server error: {error['message']}")
else:
    person = response.json()
    print(f"Person name: {person['name']}")
from flask import Flask, request, jsonify
from flask_cors import CORS
from random import randrange

app = Flask(__name__)
cors = CORS(app, resources={r"/*": {"origins": "*"}})

def randomNumber():
    return randrange(21)+10

@app.route("/")
def teste():
    return "hello world"
    
@app.route("/data")
def get():
    global numbers
    numbers = numbers[1:]+[randomNumber()]
    return jsonify(numbers)

numbers = []
for i in range(0, 10):
    #gerando numeros aleatorios entre 10 e 30
    numbers.append(randomNumber())

app.run(host='127.0.0.1', port=7070)
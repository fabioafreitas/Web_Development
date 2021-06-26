from flask import Flask

app = Flask(__name__)
cors = CORS(app, resources={r"/imagens/*": {"origins": "*"}})

@app.route('/')
def delete():
    return "Hello World!"

if __name__ == '__main__':
    app.run()
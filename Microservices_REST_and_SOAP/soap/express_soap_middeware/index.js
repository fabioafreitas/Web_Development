const express = require('express');
const cors = require('cors')
const bodyParser = require("body-parser");
const soap = require('soap');
const app = express();
const url = 'http://127.0.0.1:9090/login?wsdl';

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(cors())

app.post('/login',(request,response) => {
    soap.createClient(url, (err, soapClient) => {
        soapClient.autenticar({
            arg0: request.body.username,
            arg1: request.body.password
        }, (err, res) => {
            response.send(res.return);
        });
    });
});

const port = 8080;
app.listen(port, () => console.log(`Hello world app listening on port ${port}!`))

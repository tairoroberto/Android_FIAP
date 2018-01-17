var express = require("express");
var bodyParser = require("body-parser");
var app = express();


app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

var carros = [];

app.get("/carros", function (request, response) {
    response.send(carros);
});

// retorno 201 signigica created
app.post("/carros", function (request, response) {
    carros.push(request.body);
    response.send(201);
});

app.listen(3000, function(){
    console.log("Servidor iniciado na porta: 3000");
});
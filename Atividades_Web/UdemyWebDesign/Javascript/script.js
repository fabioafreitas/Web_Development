/*var msg = "Hello World!";
var x = 10;

if(x % 2 == 0) alert(x+" é par!");
else alert(x+" é ímpar!");

var list = ["fábio","joselito","eduarda","joana"];

var text = "";
for(var i = 0; i<4 ; i++) {
    var text = text+"\n"+list[i];
}

console.log(text);*/

function soma(a,b) {
    return a+b;
}

function helloWorld() {
    alert("Hello World");
}

function changeColor(cor) {
    var element = document.getElementById("mensagem");
    element.style.color = cor;
}

function valida() {
    var nome = document.getElementById('nome');
    if(nome.value == "")
        alert("Campo vazio!");
    else 
        alert(nome.value);
}
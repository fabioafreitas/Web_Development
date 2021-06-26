var tabela = document.getElementById('tabela');
var arrayFunc = [];

function Funcionario(cpf, nome, cargo, salario) {
    this.cpf = cpf;
    this.nome = nome;
    this.cargo = cargo;
    this.salario = salario;
}

function adicionar() {
    var cpf = document.getElementById('cpf').value;
    var nome = document.getElementById('nome').value;
    var cargo = document.getElementById('cargo').value;
    var salario = document.getElementById('salario').value;
    
    if( (cpf == "") || (nome == "") || (cargo == "") || (salario == "")) {
        alert("Algum campo não foi preenchido!");
    }   
    else if(isNaN(parseFloat(cpf))) {
        alert("Cpf Inválido!");
    }
    else if(cpf.length != 11) {
        alert("Cpf Inválido!");
    }
    else if(parseFloat(cpf)%1!=0 || parseFloat(cpf)<0) {
        alert("Cpf Inválido!");
    }
    else if(isNaN(parseFloat(salario))) {
        alert("Salário Inválido!");
    }
    else if(parseFloat(salario) < 0) {
        alert("Salário Inválido!");
    }
    else if( !isNaN(parseFloat(nome)) ) {
        alert("Nome inválido!");
    }
    else if( !isNaN(parseFloat(cargo)) ) {
        alert("Cargo inválido!");
    }
    else {
        var func = new Funcionario(cpf, nome, cargo, salario);
        if(checarFuncionarioRepetido(func)) {
            alert("Funcionário já cadastrado!");
        }
        else {
            arrayFunc.push(func);
            inserirLinha(func);
        }
    }
    limparInputText();
}

/* insere uma linha na tabela
 adiciona os ids da linha e do respectivo botao*/
function inserirLinha(func) {
    var row = tabela.insertRow(-1);
    var numLinhas = tabela.rows.length;
    row.id = "".concat("tablerow",numLinhas);

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);
    cell1.innerHTML = func.cpf;
    cell2.innerHTML = func.nome;
    cell3.innerHTML = func.cargo;
    cell4.innerHTML = func.salario;
    cell5.innerHTML = "".concat('<button ',
                                'id="buttonremove',numLinhas,'" ',
                                'onclick="remover(this.id)"',
                                '>Remover</button>');
}

/* remove a respectiva linha que o botao está inserido
 É buscado o Id da linha de acordo com o nó pai do botão*/
function remover(currentButtonId) {
    var row = document.getElementById(currentButtonId).parentElement.parentElement; // Pegando a tag tr que o botao remover está inserido
    var cpf = row.firstChild.innerHTML; // Pegando o cpf inserido nesta linha
    for(var i = 0; i<arrayFunc.length ; i++) {
        if(arrayFunc[i].cpf == cpf)
            arrayFunc.splice(i,1);
    }
    row.parentNode.removeChild(row);
    alert("Funcionário de Cpf ".concat(cpf," removido!"));
}

/* Retorna true, se o funcionário já foi cadastrado
 checa baseado no cpf*/
function checarFuncionarioRepetido(func) {
    for(var i = 0; i<arrayFunc.length ; i++) {
        if(arrayFunc[i].cpf == func.cpf)
            return true;
    }
    return false;
}

/* só estou limpando os inputs text, pois considerei
 o retorno do form como falso, portanto eles permanereciam
 preenchidos, mesmo após o submit */
function limparInputText() {
    document.getElementById('cpf').value = "";
    document.getElementById('nome').value = "";
    document.getElementById('cargo').value = "";
    document.getElementById('salario').value = "";
    
}

// retorna uma lista dos funcionários com salário maior ou igual ao indicado
// caso o salario de entrada seja negativo, retorna uma lista vazia
function filtrar() {
    var textSalario = document.getElementById('textFilter').value;
    if(textSalario == "") {
        alert("Filtro: Salário inválido!");
    }
    else if(isNaN(parseFloat(textSalario))) {
        alert("Filtro: Salário inválido!")
    }
    else {
        var salario = parseFloat(textSalario);
        if(salario < 0) {
            alert("Filtro: Salário inválido!");
        }
        else {
            var dataRows = getTableFiltro(salario);
            console.log(dataRows);
            setTableData(dataRows);
        }
    }
}

function getTableFiltro(salario) {
    var dataRows = [];
    for (let i = 0; i < arrayFunc.length; i++) {
        if(arrayFunc[i].salario > salario) {
            var dataCells = [];
            dataCells.push(arrayFunc[i].cpf);
            dataCells.push(arrayFunc[i].nome);
            dataCells.push(arrayFunc[i].cargo);
            dataCells.push(arrayFunc[i].salario);
            dataRows.push(dataCells);
        }
    }
    return dataRows;
}

// Retorna uma matriz com os dados da tabela exibida na tela atualmente
function getCurrentTableData() {
    var rows = tabela.rows;
    var dataRows = [];
    for (let i = 1; i < rows.length; i++) {
        var dataCells = [];
        for (let j = 0; j < 4; j++) {
            dataCells.push(rows[i].cells[j].innerHTML);
        }
        dataRows.push(dataCells);
    }
    return dataRows;
}

// Põe os dados da tabela atual na tela, após alguma ordenação
function setTableData(dataRows) {
    var rows = tabela.rows;
    // esvaziando tabela
    for(var i = tabela.rows.length - 1; i > 0; i--) {
        tabela.deleteRow(i);
    }
    // preencher tabela
    for (let i = 0; i < rows.length; i++) {
        var func = new Funcionario( dataRows[i][0],
                                    dataRows[i][1],
                                    dataRows[i][2],
                                    dataRows[i][3]);
        inserirLinha(func);
    }
}

// ordena a tabela atual da tela pelo CPF em ordem crescente e a exibe na tela
function orderByCPF() {
    var dataRows = getCurrentTableData();
    for (let i = 0; i < dataRows.length; i++) {
        for (let j = i+1; j < dataRows.length; j++) {
            if(dataRows[j][0] < dataRows[i][0]) {
                var aux = dataRows[j]
                dataRows[j] = dataRows[i];
                dataRows[i] = aux;
            }
        }
    }
    setTableData(dataRows);
}

function orderByNome() {
    var dataRows = getCurrentTableData();
    for (let i = 0; i < dataRows.length; i++) {
        for (let j = i+1; j < dataRows.length; j++) {
            if(dataRows[i][1].localeCompare(dataRows[j][1]) == 1) {
                var aux = dataRows[j]
                dataRows[j] = dataRows[i];
                dataRows[i] = aux;
            }
        }
    }
    setTableData(dataRows);
}

function orderBySalario() {
    var dataRows = getCurrentTableData();
    for (let i = 0; i < dataRows.length; i++) {
        for (let j = i+1; j < dataRows.length; j++) {
            if(dataRows[j][3] > dataRows[i][3]) {
                var aux = dataRows[j]
                dataRows[j] = dataRows[i];
                dataRows[i] = aux;
            }
        }
    }
    setTableData(dataRows);
}

function dadosIniciais() {
    arrayFunc.push(new Funcionario(11122233344, "João", "Gerente de projeto", 20000));
    inserirLinha(arrayFunc[0]);
    arrayFunc.push(new Funcionario(12345678910, "Maria", "Engenheiro de software", 17000));
    inserirLinha(arrayFunc[1]);
    arrayFunc.push(new Funcionario(22244466688, "Pedro", "Analista de Projeto", 12000));
    inserirLinha(arrayFunc[2]);
    arrayFunc.push(new Funcionario(33355577799, "Gustavo", "Testador", 15000));
    inserirLinha(arrayFunc[3]);
    arrayFunc.push(new Funcionario(12312312300, "Sofia", "Analista de Requisitos", 18000));
    inserirLinha(arrayFunc[4]);
}
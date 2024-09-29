let tbody = document.getElementById("tbody");

let produtos = [];
let carrinho = [];

$.ajax({
    url: '/carrinho',
    type: 'GET',
    contentType: 'application/json',
    success: function (data) {
        carrinho = data;
    }
});

$.ajax({
    url: '/api/produto',
    type: 'GET',
    contentType: 'application/json',
    success: function (data) {

        if (data.length <= 0) {
            table.innerHTML = "Nenhum produto disponivel :/";
            table.classList.add("text-center");
            return;
        }

        produtos = data;
        data.forEach((x) => {
            if (x.estoque > 0) {
                createProduto(x);
            }
        });
    }
});

function setCarrinho() {

    $.ajax({
        url: '/carrinho',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(carrinho),
        complete: function(xhr){
            console.log(xhr.status);
        }
    });

}

function addProdutoCarrinho(produtoId) {
    p = produtos.find(x => x.id == produtoId);
    carrinho.push({produto: p, quantidade: 1});
}

function createProduto(produto) {

    let tr = document.createElement("tr");
    let nome = document.createElement("td");
    let tipo = document.createElement("td");
    let valor = document.createElement("td");
    let estoque = document.createElement("td");
    let buttonTd = document.createElement("td");
    let button = document.createElement("button");

    nome.textContent = produto.nome;
    tipo.textContent = produto.tipo;
    valor.textContent = `R$${produto.valorUnitario}`;
    estoque.textContent = produto.estoque;
    button.textContent = "Adicionar ao carrinho";
    button.addEventListener("click", function () {
        addProdutoCarrinho(produto.id);
    });

    buttonTd.appendChild(button);

    tr.appendChild(nome);
    tr.appendChild(tipo);
    tr.appendChild(valor);
    tr.appendChild(estoque);
    tr.appendChild(buttonTd);

    tbody.appendChild(tr);
}
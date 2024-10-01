let table = document.getElementById("tablebody");
let totalText = document.getElementById("totaltext");
let quantidadeText = document.getElementById("quantidadeText");
let totalPopupText = document.getElementById("popupTotal");
let carrinho = [];

let carrinhoIndex;

window.addEventListener('beforeunload', function (e) {
    setCarrinho();
});

$.ajax({
    url: '/carrinho',
    type: 'GET',
    contentType: 'application/json',
    success: function (data) {
        carrinho = data;
        updateTable();
    }
});

totalText.innerHTML = `R$${totalCarrinho(carrinho)}`;

function updateTable() {
    table.innerHTML = "";
    for (let i = 0; i < carrinho.length; i++) {
        loadTable(carrinho[i], i);
    }
}

function updatePopupText() {
    totalPopupText.innerHTML = carrinho[carrinhoIndex].quantidade * carrinho[carrinhoIndex].produto.valorUnitario;
    console.log("atualizado o textooo");
}

function editProduto(id) {
    carrinhoIndex = id;
    document.getElementById("quantidadeText").value = carrinho[id].quantidade;
    document.querySelector(".popup").style.display = "flex";
    updatePopupText();
}

function setProduto() {
    let popupInputvalue = parseInt(document.getElementById("quantidadeText").value);
    carrinho[carrinhoIndex].quantidade = clamp(popupInputvalue, 1, carrinho[carrinhoIndex].produto.estoque);
    document.querySelector(".popup").style.display = "none";
    totalText.innerHTML = `R$${totalCarrinho(carrinho)}`;
    updateTable();
}

function removerProduto() {
    carrinho.splice(carrinhoIndex, 1);
    document.querySelector(".popup").style.display = "none";
    totalText.innerHTML = `R$${totalCarrinho(carrinho)}`;
    updateTable();
}

function clearCarrinho() {
    carrinho = [];
    setCarrinho();
    updateTable();
}

function setCarrinho() {
    $.ajax({
        url: '/carrinho',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(carrinho),
        complete: function (xhr) {
            console.log(xhr.status);
        }
    });
}

function totalCarrinho(carrinhoProduto) {
    let total = 0;
    carrinhoProduto.forEach((x) => {
        total += (x.quantidade * x.produto.valorUnitario);
    })
    return total;
}

function clamp(value, min, max) {
    if (value > max)
        return max;
    if (value < min)
        return min;
    return value;
}

function loadTable(carrinhoProduto, index) {

    let tr = document.createElement("tr");
    let tdNome = document.createElement("td");
    tdNome.textContent = carrinhoProduto.produto.nome;

    let tdTipo = document.createElement("td");
    tdTipo.textContent = carrinhoProduto.produto.tipo;

    let tdValorUnitario = document.createElement("td");
    tdValorUnitario.textContent = carrinhoProduto.produto.valorUnitario;

    let tdQuantidade = document.createElement("td");
    tdQuantidade.textContent = carrinhoProduto.quantidade;

    let tdTotal = document.createElement("td");
    tdTotal.textContent = carrinhoProduto.total;

    let tdButton = document.createElement("td");

    let editButton = document.createElement("button");
    editButton.textContent = "Editar"
    editButton.addEventListener("click", function () {
        editProduto(index);
    })

    tr.appendChild(tdNome);
    tr.appendChild(tdTipo);
    tr.appendChild(tdValorUnitario);
    tr.appendChild(tdQuantidade);
    tr.appendChild(tdTotal);
    tdButton.appendChild(editButton);
    tr.appendChild(tdButton)

    table.appendChild(tr);
}

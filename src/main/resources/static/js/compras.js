let compraData = document.getElementById("compraData");
let totalCompra = document.getElementById("total");
let tbody = document.getElementById("tbody");
let produtosTable = document.getElementById("produtos");
let compraId = document.getElementById("idCompra");

let compras = [];

$.ajax({
    url: '/api/venda/user',
    type: 'GET',
    contentType: 'application/json',
    success: function (data) {
        compras = data;
        for(let i = 0; i < compras.length; i++){
            loadCompras(compras[i], i);
        }
    }
});

function visualizarCompra(index) {
    let compra = compras[index];

    compraData.textContent = compra.data;
    compraId.textContent = compra.id;
    
    let total = 0.0;
    produtosTable.innerHTML = '';
    for (let p of compra.produtos) {
        insertProduto(p);
        total += p.total;
    }

    totalCompra.textContent = total.toFixed(2);
    document.querySelector(".comprainfo").style.display = "flex";
}

function insertProduto(carrinhoProduto) {
    let row = produtosTable.insertRow();

    row.insertCell(0).innerHTML = carrinhoProduto.produto.id;
    row.insertCell(1).innerHTML = carrinhoProduto.produto.nome;
    row.insertCell(2).innerHTML = carrinhoProduto.produto.valorUnitario;
    row.insertCell(3).innerHTML = carrinhoProduto.quantidade;
    row.insertCell(4).innerHTML = carrinhoProduto.total;
}

function loadCompras(compra, index) {
    let tr = document.createElement("tr");

    let dataTd = document.createElement("td");
    dataTd.textContent = compra.data;

    let quantidadeItensTd = document.createElement("td");
    quantidadeItensTd.textContent = compra.produtos.length;

    let totalTd = document.createElement("td");
    
    let total = 0.0;
    compra.produtos.forEach((x) => {
       total += x.total; 
    });
    
    totalTd.textContent = total;

    let acaoTd = document.createElement("td");

    let visualizarCompraBotao = document.createElement("button");
    visualizarCompraBotao.textContent = "Visualizar";
    visualizarCompraBotao.classList.add("btn");
    visualizarCompraBotao.classList.add("btn-secondary");
    visualizarCompraBotao.addEventListener("click", () => {
        visualizarCompra(index);
    });

    acaoTd.appendChild(visualizarCompraBotao);

    tr.appendChild(dataTd);
    tr.appendChild(quantidadeItensTd);
    tr.appendChild(totalTd);
    tr.appendChild(acaoTd);

    tbody.appendChild(tr);
}

function closePopup() {
    document.querySelector(".comprainfo").style.display = "none";
}

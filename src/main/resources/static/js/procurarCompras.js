let searchForm = document.getElementById('searchForm')
let vendaData = document.getElementById('vendaData')
let nomeCliente = document.getElementById('nomeCliente')
let idCliente = document.getElementById('idCliente')
let totalVenda = document.getElementById('total')

let produtosTable = document.getElementById('produtos');

searchForm.addEventListener("submit", function (event) {
    event.preventDefault();

    const result = $.ajax({
        url: '/loja/pesquisar/venda/' + document.getElementById('vendaId').value,
        type: 'GET',
        contentType: 'application/json',
        success: function (data) {
            
            if(result.status == 204){
                alert("compra '" + document.getElementById('vendaId').value + "' não encontrada!")
                return;
            }
            
            idCliente.textContent = data.venda.clienteId;
            nomeCliente.textContent = data.nomeCliente;
            vendaData.textContent = data.venda.data;
            totalVenda.textContent = data.venda.compra.total;

            produtosTable.innerHTML = '';
            for (let p of data.produtos) {
                insertProduto(p);
            }
        },
        error: function () {
            console.log("erro");
        }
    });
})

function insertProduto(produto) {

    let row = produtosTable.insertRow();

    row.insertCell(0).innerHTML = produto.id;
    row.insertCell(1).innerHTML = produto.nome;
    row.insertCell(2).innerHTML = produto.valorUnitario;
    row.insertCell(3).innerHTML = produto.quantidade;
    row.insertCell(4).innerHTML = produto.total;
}
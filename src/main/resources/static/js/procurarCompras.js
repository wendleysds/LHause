let searchForm = document.getElementById('searchForm');
let vendaData = document.getElementById('vendaData');
let nomeCliente = document.getElementById('nomeCliente');
let idCliente = document.getElementById('idCliente');
let totalVenda = document.getElementById('total');

let produtosTable = document.getElementById('produtos');

searchForm.addEventListener("submit", function (event) {
    event.preventDefault();

    const result = $.ajax({
        url: `/api/venda/${document.getElementById('vendaId').value}`,
        type: 'GET',
        contentType: 'application/json',
        success: function (data) {
            
            if(result.status === 204){
                alert("compra '" + document.getElementById('vendaId').value + "' não encontrada!");
                return;
            }
            
            idCliente.textContent = data.clienteId;
            nomeCliente.textContent = data.clienteNome;
            vendaData.textContent = data.data;
            
            let total = 0.0;
            produtosTable.innerHTML = '';
            for (let p of data.produtos) {
                insertProduto(p);
                total += p.total;
            }
            
            totalVenda.textContent = total.toFixed(2);
        },
        error: function () {
            console.log("erro");
        }
    });
});

function insertProduto(carrinhoProduto) {
    let row = produtosTable.insertRow();

    row.insertCell(0).innerHTML = carrinhoProduto.produto.id;
    row.insertCell(1).innerHTML = carrinhoProduto.produto.nome;
    row.insertCell(2).innerHTML = carrinhoProduto.produto.valorUnitario;
    row.insertCell(3).innerHTML = carrinhoProduto.quantidade;
    row.insertCell(4).innerHTML = carrinhoProduto.total;
}

let table = document.getElementById('table-body');

let produtos = [];

$.ajax({
    url: '/api/produto',
    type: 'GET',
    contentType: 'application/json',
    success: function (data) {
        produtos = data;
        updateTable(produtos);
    }
});

function updateTable(produtos) {
    table.innerHTML = "";
    produtos.forEach((produto) => {
        let tr = document.createElement('tr');
        let tdId = document.createElement('td');
        let tdNome = document.createElement('td');
        let tdTipo = document.createElement('td');
        let tdValorUnitario = document.createElement('td');
        let tdEstoque = document.createElement('td');
        let tdEditar = document.createElement('td');
        let tdRemover = document.createElement('td');

        let aEditar = document.createElement('a');
        let buttonEditar = document.createElement('button');
        let buttonRemover = document.createElement('button');

        tdId.innerText = produto.id;
        tdNome.innerText = produto.nome;
        tdTipo.innerText = produto.tipo;
        tdValorUnitario.innerText = produto.valorUnitario;
        tdEstoque.innerText = produto.estoque;

        aEditar.classList.add('text-white');
        aEditar.href = `/editar/produto/${produto.id}`;

        buttonEditar.classList.add('btn');
        buttonEditar.classList.add('btn-primary');
        buttonEditar.innerText = 'Editar';

        buttonRemover.classList.add('btn');
        buttonRemover.classList.add('btn-danger');
        buttonRemover.addEventListener('click', () => {
            deleteProduto(produto.id);
        });
        buttonRemover.innerText = 'Remover';

        tr.appendChild(tdId);
        tr.appendChild(tdNome);
        tr.appendChild(tdTipo);
        tr.appendChild(tdValorUnitario);
        tr.appendChild(tdEstoque);

        aEditar.appendChild(buttonEditar);
        tdEditar.appendChild(aEditar);
        tr.appendChild(tdEditar);

        tdRemover.appendChild(buttonRemover);
        tr.appendChild(tdRemover);

        table.appendChild(tr);
    });
}

function deleteProduto(id) {
    $.ajax({
        url: `/api/produto/${id}`,
        type: 'DELETE',
        contentType: 'application/json',
        complete: function (xhr) {
            if (xhr.status === 204) {
                alert(`deleted: ${id}`);
                produtos.splice(produtos.indexOf(produtos.find((x) => x.id === id)), 1);
                updateTable(produtos);
            }
            else{
                alert('error');
            }
        }
    });
}

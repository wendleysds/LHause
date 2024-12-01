let table = document.getElementById('table-body');

let computadores = [];

$.ajax({
    url: '/api/computador',
    type: 'GET',
    contentType: 'application/json',
    success: function (data) {
        computadores = data;
        updateTable(computadores);
    }
});

function updateTable(computadores) {
    table.innerHTML = '';
    computadores.forEach((computador) => {
        let tr = document.createElement('tr');
        let tdId = document.createElement('td');
        let tdOcupado = document.createElement('td');
        let tdLigado = document.createElement('td');
        let tdJogos = document.createElement('td');
        let tdEspecificacoes = document.createElement('td');
        let tdEditar = document.createElement('td');
        let tdRemover = document.createElement('td');

        let aEditar = document.createElement('a');
        let buttonEditar = document.createElement('button');
        let buttonRemover = document.createElement('button');

        tdId.innerText = computador.id;
        tdOcupado.innerText = computador.ocupado;
        tdLigado.innerText = computador.ligado;
        tdJogos.innerText = computador.jogos;
        tdEspecificacoes.innerText = computador.especificacoes;

        aEditar.classList.add('text-white');
        aEditar.href = `/editar/computador/${computador.id}`;

        buttonEditar.classList.add('btn');
        buttonEditar.classList.add('btn-primary');
        buttonEditar.innerText = 'Editar';

        buttonRemover.classList.add('btn');
        buttonRemover.classList.add('btn-danger');
        buttonRemover.addEventListener('click', () => {
            deleteComputador(computador.id);
        });
        buttonRemover.innerText = 'Remover';

        tr.appendChild(tdId);
        tr.appendChild(tdOcupado);
        tr.appendChild(tdLigado);
        tr.appendChild(tdJogos);
        tr.appendChild(tdEspecificacoes);

        aEditar.appendChild(buttonEditar);
        tdEditar.appendChild(aEditar);
        tr.appendChild(tdEditar);

        tdRemover.appendChild(buttonRemover);
        tr.appendChild(tdRemover);

        table.appendChild(tr);
    });
}

function deleteComputador(id) {
    $.ajax({
        url: `/api/computador/${id}`,
        type: 'DELETE',
        contentType: 'application/json',
        complete: function (xhr) {
            if (xhr.status === 204) {
                alert(`deleted: ${id}`);
                computadores.splice(computadores.indexOf(computadores.find((x) => x.id === id)), 1);
                updateTable(computadores);
            }
            else {
                alert('error');
            }
        }
    });
}
let table = document.getElementById('table-body');

let agendas = [];

$.ajax({
    url: '/api/agenda',
    type: 'GET',
    contentType: 'application/json',
    success: function (data) {
        agendas = data;
        updateTable(agendas);
    }
});

function updateTable(agendas) {
    table.innerHTML = '';
    agendas.forEach((agenda) => {
        let tr = document.createElement('tr');
        let tdId = document.createElement('td');
        let tdData = document.createElement('td');
        let tdComputadorId = document.createElement('td');
        let tdTempo = document.createElement('td');
        let tdUsuarioId = document.createElement('td');
        let tdRemover = document.createElement('td');

        let buttonRemover = document.createElement('button');

        tdId.innerText = agenda.id;
        tdData.innerText = agenda.data;
        tdComputadorId.innerText = agenda.computadorId;
        tdTempo.innerText = agenda.tempo;
        tdUsuarioId.innerText = agenda.usuarioId;

        buttonRemover.classList.add('btn');
        buttonRemover.classList.add('btn-danger');
        buttonRemover.addEventListener('click', () => {
            deleteAgenda(agenda.id);
        });
        buttonRemover.innerText = 'Remover';

        tr.appendChild(tdId);
        tr.appendChild(tdData);
        tr.appendChild(tdComputadorId);
        tr.appendChild(tdTempo);
        tr.appendChild(tdUsuarioId);

        tdRemover.appendChild(buttonRemover);
        tr.appendChild(tdRemover);

        table.appendChild(tr);
    });
}

function deleteAgenda(id) {
    $.ajax({
        url: `/api/agenda/${id}`,
        type: 'DELETE',
        contentType: 'application/json',
        complete: function (xhr) {
            if (xhr.status === 204) {
                alert(`deleted: ${id}`);
                agendas.splice(agendas.indexOf(agendas.find((x) => x.id === id)), 1);
                updateTable(agendas);
            }
            else {
                alert('error');
            }
        }
    });
}
let table = document.getElementById('table-body');

let usuarios = [];

$.ajax({
    url: '/api/usuario',
    type: 'GET',
    contentType: 'application/json',
    success: function (data) {
        usuarios = data;
        updateTable(data);
    }
});

function updateTable(users) {
    table.innerHTML = "";
    users.forEach((data) => {
        let tr = document.createElement('tr');
        let tdId = document.createElement('td');
        let tdNome = document.createElement('td');
        let tdLogin = document.createElement('td');
        let tdTipo = document.createElement('td');
        let tdCpf = document.createElement('td');
        let tdEmail = document.createElement('td');
        let tdTelefone = document.createElement('td');
        let tdUltimoLogin = document.createElement('td');
        let tdEditar = document.createElement('td');
        let tdRemover = document.createElement('td');

        let aEditar = document.createElement("a");
        let buttonEditar = document.createElement("button");
        let buttonRemover = document.createElement("button");

        tdId.innerText = data.id;
        tdNome.innerText = data.nome;
        tdLogin.innerText = data.login;
        tdTipo.innerText = data.tipo;
        tdCpf.innerText = data.cpf;
        tdEmail.innerText = data.email;
        tdTelefone.innerText = data.telefone;
        tdUltimoLogin.innerText = data.ultimoLogin;

        aEditar.classList.add("text-white");
        aEditar.href = `/editar/usuario/${data.id}`;

        buttonEditar.classList.add("btn");
        buttonEditar.classList.add('btn-primary');
        buttonEditar.innerText = 'Editar';

        buttonRemover.classList.add("btn");
        buttonRemover.classList.add("btn-danger");
        buttonRemover.addEventListener('click', () => {
            deleteUser(data.id)
        });
        buttonRemover.innerText = "Remover";

        tr.appendChild(tdId);
        tr.appendChild(tdNome);
        tr.appendChild(tdLogin);
        tr.appendChild(tdTipo);
        tr.appendChild(tdCpf);
        tr.appendChild(tdEmail);
        tr.appendChild(tdTelefone);
        tr.appendChild(tdUltimoLogin);

        aEditar.appendChild(buttonEditar);
        tdEditar.appendChild(aEditar);
        tr.appendChild(tdEditar);

        tdRemover.appendChild(buttonRemover);
        tr.appendChild(tdRemover);

        table.appendChild(tr);
    });
}

function deleteUser(id) {
    $.ajax({
        url: `/api/usuario/${id}`,
        type: 'DELETE',
        contentType: 'application/json',
        complete: function (xhr) {
            if (xhr.status === 204) {
                alert(`deleted: ${id}`);
                usuarios.splice(usuarios.indexOf(usuarios.find((x) => x.id === id)), 1);
                updateTable(usuarios);
            }
            else{
                alert('error');
            }
        }
    });
}

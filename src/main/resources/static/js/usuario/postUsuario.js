let form = document.getElementById("form");
let nome = document.getElementById("txtNome");
let cpf = document.getElementById("txtCpf");
let email = document.getElementById("txtEmail");
let telefone = document.getElementById("Telefone");
let login = document.getElementById("txtUsuario");
let senha = document.getElementById("txtSenha");

form.addEventListener("submit", (e) => {
    e.preventDefault();

    let user = {
        id: null,
        nome: nome.value,
        tipo: null,
        cpf: cpf.value,
        email: email.value,
        telefone: telefone.value,
        login: login.value,
        senha: senha.value,
        ultimoLogin: null
    };

    $.ajax({
        url: '/api/usuario',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(user),
        complete: function (xhr) {
            if (xhr.status == 204) {
                alert("ok");
                window.location.href = ("/");
            }
            else {
                alert(xhr.responseText);
            }
        }
    });
});

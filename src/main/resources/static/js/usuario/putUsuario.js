let form = document.querySelector("form");

let id = document.getElementById("id");
let nome = document.getElementById("nome");
let tipo = document.getElementById("tipo");
let cpf = document.getElementById("cpf");
let email = document.getElementById("email");
let telefone = document.getElementById("telefone");
let login = document.getElementById("login");
let senha = document.getElementById("senha");
let ultimoLogin = document.getElementById("ultimoLogin");

form.addEventListener("submit", (e) => {
    e.preventDefault();

    let user = {
        id: id.value,
        nome: nome.value,
        tipo: tipo.value,
        cpf: cpf.value,
        email: email.value,
        telefone: telefone.value,
        login: login.value,
        senha: senha.value,
        ultimoLogin: ultimoLogin.value,
    };

    $.ajax({
        url: `/api/usuario/${id.value}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(user),
        complete: function (xhr) {       
            if(xhr.status === 200){
                alert("ok");
                location.reload();
            }
            else{
                alert(xhr.responseText);
            }
        }
    });
});

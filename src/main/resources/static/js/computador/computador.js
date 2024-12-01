let form = document.querySelector("form");

let id = document.getElementById('id');
let ocupado = document.getElementById('ocupado');
let ligado = document.getElementById('ligado');
let especificacoes = document.getElementById('especificacoes');
let jogos = document.getElementById('jogos');

form.addEventListener("submit", (e) => {
    e.preventDefault();

    let computador = {
        id: id.value,
        ocupado: ocupado.checked,
        ligado: ligado.checked,
        especificacoes: especificacoes.value,
        jogos: jogos.value
    };

    $.ajax({
        url: id.value.length == 0 ? '/api/computador' : `/api/computador/${computador.id}`,
        type: id.value.length == 0 ? 'POST' : 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(computador),
        complete: function (xhr) {
            if (xhr.status > 199 < 300) {
                alert("ok");
                location.reload();
            }
            else {
                alert(xhr.responseText);
            }
        }
    });
});

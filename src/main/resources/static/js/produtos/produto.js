let form = document.querySelector('form');

let id = document.getElementById('numId');
let nome = document.getElementById('txtNome');
let tipo = document.getElementById('txtTipo');
let valorUnitario = document.getElementById('numValor');
let estoque = document.getElementById('numEstoque');

form.addEventListener("submit", (e) => {
    e.preventDefault();

    let produto = {
        id: id.value,
        nome: nome.value,
        tipo: tipo.value,
        valorUnitario: valorUnitario.value,
        estoque: estoque.value
    };

    $.ajax({
        url: id.value.length == 0 ? '/api/produto' : `/api/produto/${produto.id}`,
        type: id.value.length == 0 ? 'POST' : 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(produto),
        complete: function (xhr) {          
            if(xhr.status > 199 < 300){
                alert("ok");
                location.reload();
            }
            else{
                alert(xhr.responseText);
            }
        }
    });
});

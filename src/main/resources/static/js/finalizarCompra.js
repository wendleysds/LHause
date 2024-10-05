let numeroC = document.getElementById("numeroC");
let titularC = document.getElementById("titularC");
let parcela = document.getElementById("parcelaS");
let total = document.getElementById("total");
let form = document.getElementById("form");

let loginTextField = document.getElementById("txtLogin");
let passTextField = document.getElementById("txtPassword");
let loginForm = document.getElementById("loginForm");

let carrinho = [];

$.ajax({
    url: '/carrinho',
    type: 'GET',
    contentType: 'application/json',
    success: function (data) {
        carrinho = data;
        updateTotal();
    }
});

parcela.onchange = function () {
    updateTotal();
}

form.addEventListener("submit", (e) => {
    e.preventDefault();

    let compra = {
        tipoPagamento: 'C',
        numeroCartao: numeroC.value,
        numeroParcelas: parcela.value,
        produtos: carrinho,
        total: totalCarrinho(carrinho)
    };

    $.ajax({
        url: '/api/venda/finalizar',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(compra),
        complete: function (xhr) {

            if (xhr.status === 403) {
                document.getElementById("loginpopup").style.display = "flex";
                alert("entre em sua conta primeiro");
            }
            else if (xhr.status === 400) {
                window.location.href = "/loja";
            }
            else if (xhr.status === 201) {
                document.getElementById("comprapopup").style.display = "flex";
                document.getElementById("compraId").textContent = xhr.responseText;
                clearCarrinho();
            }
            else {
                console.log(`ERRO\n ${xhr.status}`);
            }

        }
    });
});

loginForm.addEventListener("submit", (e) => {
    e.preventDefault();

    let loginData = { login: loginTextField.value, password: passTextField.value };

    $.ajax({
        url: '/api/login',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(loginData),
        complete: function (xhr) {
            if (xhr.status != 200) {
                alert("Senha ou usuario incorretos");
            }
            else if (xhr.status === 200) {
                document.getElementById("loginpopup").style.display = "none";
            }
        }
    });
});

function updateTotal() {
    total.textContent = (totalCarrinho(carrinho) / parcela.value).toFixed(2);
}

function clearCarrinho() {
    carrinho = [];
    $.ajax({
        url: '/carrinho',
        type: 'DELETE',
        complete: function (xhr) {
            console.log(xhr.status);
        }
    });
}

function totalCarrinho(carrinhoProduto) {
    let total = 0;
    carrinhoProduto.forEach((x) => {
        total += x.total;
    });
    return total;
}

function clamp(value, min, max) {
    if (value > max)
        return max;
    if (value < min)
        return min;
    return value;
}
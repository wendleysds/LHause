let loginTextField = document.getElementById("txtLogin");
let passTextField = document.getElementById("txtPassword");
let form = document.getElementById("loginForm");

form.addEventListener("submit", (e) => {
    e.preventDefault();

    let loginData = { login: loginTextField.value, password: passTextField.value };

    $.ajax({
        url: '/api/login',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(loginData),
        complete: function(xhr, textStatus){
            if(xhr.status == 302){
                window.location.href = ("/home");
            }
            else{
                alert("Senha ou usuario incorretos")
            }
        }
    });
})
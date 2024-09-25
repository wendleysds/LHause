let form = document.getElementById("formId");
let computadorId = document.getElementById("computadorId").value;
let date = document.getElementById("dateData");
let tempo = document.getElementById("numTempo");

form.addEventListener("submit", (e) => {
    e.preventDefault();

    let agenda = {
        id: null,
        data: date.value,
        computadorId: computadorId,
        tempo: tempo.value,
        usuarioId: null
    };
    
    $.ajax({
        url: '/api/agenda',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(agenda),
        complete: function(xhr){
            if(xhr.status == 201){
                window.location.href = ("/agenda");
            }
            else{
                alert(`Erro ao agendar '${xhr.status}'`)
            }
        }
    });
})

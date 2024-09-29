

$.ajax({
    url: '/api/loja',
    type: 'GET',
    contentType: 'application/json',
    success: function(data){
        data.forEach((x) => {
           createProduto(x); 
        });
    }
});

function createProduto(produto){
    
    
    
}
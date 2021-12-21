$(document).ready(function(){
    var codeBook = window.location.pathname.substring(31);
    $.ajax({
        url:"/api/book/"+codeBook,
    }).then(function(data){
        console.log(data);
        $("#addNewCodeBook").val(data.codeBookParent);
        $("#addNewNameBook").val(data.nameBook);
        $("#quantity").val(data.quantity);
        $("#sales").val(data.sales);
    })
})

function addNewQuantityBook(){
    var newQuantity = document.getElementById("addNewQuantity").value;
    var codeBook = document.getElementById("addNewCodeBook").value;

    const data ={
        codeBook:codeBook,
        newQuantity:newQuantity
    }
    var dataStringJson = JSON.stringify(data);
    const xmlHttp = new XMLHttpRequest();
    xmlHttp.onload = function(){
        window.location.replace("/sach");
    }
    xmlHttp.open("POST","/api/book/update/addNewBook/"+codeBook);
    xmlHttp.setRequestHeader("Content-Type","application/json")
    xmlHttp.send(dataStringJson);
}
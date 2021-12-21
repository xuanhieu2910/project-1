
function register(){
    var nameAdmin = document.getElementById("nameUser").value;
    var phoneAdmin = document.getElementById("phoneUser").value;
    var addressAdmin = document.getElementById("addressUser").value;
    var emailAdmin = document.getElementById("emaiUser").value;
    var password = document.getElementById("passwordUser").value;
    var data ={
        "nameAdmin":nameAdmin,
        "phoneNumber":phoneAdmin,
        "address":addressAdmin,
        "email":emailAdmin,
        "password":password
    }
    var jsonData = JSON.stringify(data);
    $.ajax({
        type:"POST",
        url:"/api/register",
        data:jsonData,
        contentType:"application/json"
    }).done(function(){
        window.location.replace("/login");
    })
}
function submitUpdateAccount(){
    var nameAccount = document.getElementById("nameAccount").value;
    var addressAccount = document.getElementById("addressAccount").value;
    var phoneAccount = document.getElementById("phoneAccount").value;
    var contactAccount = document.getElementById("contactAccount").value;
    var dateOfBirth = document.getElementById("dateOfBirth").value;
    var data={
        "nameAdmin":nameAccount,
        "address":addressAccount,
        "phoneNumber":phoneAccount,
        "contact":contactAccount,
        "dateOfBirth":dateOfBirth
    }
    var stringJsonData= JSON.stringify(data);
    const xml = new XMLHttpRequest();
    xml.onload=function(){
        window.location.replace("/tai-khoan");
    }
    xml.open("POST","/api/admin/update-account");
    xml.setRequestHeader("Content-Type","application/json");
    xml.send(stringJsonData);
}
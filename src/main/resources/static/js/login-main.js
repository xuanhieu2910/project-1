var togglePassword = document.querySelector("#togglePassword");


function viewPassword(element){
    if(element.value!==""){
        document.querySelector(".eyesPassword").classList.add("active--eyes");
    }
    else{
        document.querySelector(".eyesPassword").classList.remove("active--eyes");
    }
}


togglePassword.addEventListener("click",(e)=>{
    e.target.classList.toggle('fa-eye-slash');
    var inputPw  = document.getElementById("text--password").value;
    if(document.getElementById("text--password").getAttribute("type")==='password'){
        document.getElementById("text--password").setAttribute("type","text");
        document.getElementById("text--password").value=inputPw;
    }
    else{
        document.getElementById("text--password").setAttribute("type","password");
    }
})



function loginAccount(){
    console.log("HAHA");
    var email = document.getElementById("emailAddress").value;
    var password = document.getElementById("text--password").value;
    console.log(password);
    console.log(typeof password);
    var data={
        "emailAccount":email,
        "password":password
    }
    var stringJson = JSON.stringify(data);
    const xml = new XMLHttpRequest();
    xml.onload=function(){
        if(this.status==500){
            document.getElementById("text--password").classList.add("failLogin");
            document.getElementById("emailAddress").classList.add("failLogin");
            setTimeout(function(){
                document.getElementById("text--password").classList.remove("failLogin");
                document.getElementById("emailAddress").classList.remove("failLogin");
            },1000)
        }else{
            window.location.replace("/trang-chu");
        }
    }
    xml.open("POST","/api/login");
    xml.setRequestHeader("Content-Type","application/json");
    xml.send(stringJson);
}





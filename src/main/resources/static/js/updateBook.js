$(document).ready(function(){
    var text = window.location.pathname;
    document.querySelector("#codeBook").value=text.substring(21);
})

document.querySelector("#buttonSubmitButton").addEventListener("click",openModal);

function openModal(){
    var input = document.querySelectorAll("input");
    var check = false;
    for(var i =1 ; i<input.length;i++){
        if(input.item(i).value ===""){
            check=true;
            break;
        }
    }
    if(check){
        document.querySelector(".notiNewBook").classList.add("activeNotiNewBook");
        setTimeout(function(){
            document.querySelector(".notiNewBook").classList.remove("activeNotiNewBook");
        },2000);
    }
    else{
        document.querySelector(".modalNewBook").classList.add("activeCreateNewBook");
    }
}

document.querySelector(".agressButtonCreateNewBook").addEventListener("click",submitUpdateNewBook);


document.querySelector(".buttonClose").addEventListener("click",closeModal);
document.querySelector(".closeButtonCreateNewBook").addEventListener("click",closeModal);


function submitUpdateNewBook(){
    const data={
        codeBook:document.getElementById("codeBook").value,
        nameBook:document.getElementById("nameBook").value,
        codeCategory:document.getElementById("nameCategory").value,
        codeMajor:document.getElementById("nameMajorBook").value,
        author:document.getElementById("authorBook").value,
        company:document.getElementById("companyBook").value,
        price: document.getElementById("priceBook").value,
        description:document.getElementById("contentBook").value,
    }
    var dataStringJson = JSON.stringify(data);
    const  xmlHttp = new XMLHttpRequest();
    xmlHttp.onload = function(){
        window.location.replace("/sach");
    }
    xmlHttp.open("PUT","/api/book/update/"+document.getElementById("codeBook").value);
    xmlHttp.setRequestHeader("Content-Type","application/json");
    xmlHttp.send(dataStringJson);
}


function closeModal(){
    document.querySelector(".modalNewBook").classList.remove("activeCreateNewBook");
}



$(document).ready(function(){
    $.ajax({
        url:"/api/category/findAll"
    }).then(function(data){
        if(data.length==0){
            return null;
        }
        else{
            var optionCategory="";
            for(var i=0;i<data.length;i++){
                optionCategory+="<option "+"value="+data[i].codeCategory+">"+data[i].nameCategory+"</option>"
            }
            $("#nameCategory").append(optionCategory);
        }
    })
})

$(document).ready(function(){
    $.ajax({
        url:"/api/majors"
    }).then(function(data){
        var optionMajor ="";
        for(var i=0;i<data.length;i++){
            optionMajor+="<option "+"value="+data[i].codeMajor+">"+data[i].nameMajor+"</option>"
        }
        $("#nameMajorBook").append(optionMajor);
    })
})


function addNewBook(){
    var codeBook = document.getElementById("codeBook").value;
    window.location.replace("/sach/chinh-sua-sach/them-sach/"+codeBook);
}
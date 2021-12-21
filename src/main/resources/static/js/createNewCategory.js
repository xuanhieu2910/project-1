var codeCategory = document.getElementById("codeCategory");
var codeCategories= new Array();

$(document).ready(function() {
    $.ajax({
        url: "/api/category/codeCategories",
    }).then(function(data) {
        if(data.length==0){
            return  null;
        }
        else {
            for (var i = 0; i < data.length; i++) {
                codeCategories.push(data[i]);
            }
            var displayCodeCategory = "<ul id='ulCodeCategories'>";
            for(var i = 0 ; i<codeCategories.length ; i++){
                displayCodeCategory+="<li class='liCodeCategories'  style='display:none'>"+"<span>Mã danh mục: "+codeCategories[i]+"</span>"+"</li>";
            }
            displayCodeCategory+="</ul>";
            $("#resultCategories").append(displayCodeCategory);
        }
    })
});


function checkCodeCategories(e){
    var listCodeCategory = document.querySelectorAll(".liCodeCategories");
    var code = e.value.toUpperCase();
    if(code===""){
        document.querySelector("#resultCategories").style.display="none";
    }
    else {
        document.querySelector("#resultCategories").style.display="";
        for (var i = 0; i < listCodeCategory.length; i++) {
            var textListCode = listCodeCategory[i].textContent;
            if (textListCode.substring(13).indexOf(code) > -1) {
                listCodeCategory[i].style.display = "";
            } else {
                listCodeCategory[i].style.display = "none";
            }

        }
    }
};


codeCategory.addEventListener("blur",(e)=>{

    document.getElementById("codeCategory").value= e.target.value.toLocaleUpperCase();
    for(var i = 0 ; i <codeCategories.length;i++){
        if(codeCategories[i]== e.target.value.toUpperCase()){
            document.querySelector(".haveAllreadyCategoryBook").classList.add("activeNoti");
            setTimeout(()=>{
                document.querySelector(".haveAllreadyCategoryBook").classList.remove("activeNoti");
            },3000)
        }
    }
});


document.getElementById("submitCategory").addEventListener("click",function(){
    const dataCategory={
        codeCategory:$("#codeCategory").val(),
        nameCategory:$("#nameCategory").val()
    }

    var dataCategoryStringJson = JSON.stringify(dataCategory);
    console.log(dataCategoryStringJson);
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.onload=function(){
        window.location.href="/the-loai-sach";
    }
    xmlhttp.open("POST","/api/category/save");
    xmlhttp.setRequestHeader("Content-Type","application/json");
    xmlhttp.send(dataCategoryStringJson);

    window.location.href="/the-loai-sach";
})


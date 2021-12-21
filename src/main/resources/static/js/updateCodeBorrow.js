$(document).ready(function(){
    var pathName = window.location.pathname.split("/");
    var codeBorrow = pathName[3];
    var codeBook = pathName[4];
    $("#codeCard").val(codeBorrow);
    $("#codeOldBook").val(codeBook);
})

var arrayCodeBookChild = new Array();
$(document).ready(function(){
    $.ajax({
        url:"/api/code_book_child"
    }).then(function(data){
        for(var i=0;i<data.length;i++){
            arrayCodeBookChild.push(data[i]);
        }
    })
})

var checkEnter = false;
document.querySelector("#codeNewBook").addEventListener("keyup",function(e){
    var inputCodeBook = e.target.value;
    if(e.key=='Enter'){
        var check = true;
        for(var i=0;i<arrayCodeBookChild.length;i++){
            if(arrayCodeBookChild[i].codeBookChild==inputCodeBook){
                check=false;
                checkEnter=true;
                break;
            }
            else{
                check=true;
            }
        }
        if(check){
            document.querySelector(".paymentNoti").classList.add("activeNoti");
            setTimeout(()=>{
                document.querySelector(".paymentNoti").classList.remove("activeNoti");
            },2000)
        }
    }
});

var pathName = window.location.pathname.split("/");
var oldCodeBook = pathName[4];
var mssv = pathName[3].split("-")[0];
var codeBorrow=pathName[3];
function changeNewCodeBook(){
    if(!checkEnter){
        var inputCodeBook = document.querySelector("#codeNewBook").value;
        var check = true;
        for(var i=0;i<arrayCodeBookChild.length;i++){
            if(arrayCodeBookChild[i].codeBookChild==inputCodeBook){
                check=false;
                checkEnter=true;
                break;
            }
            else{
                check=true;
            }
        }
        if(check){
            document.querySelector(".paymentNoti").classList.add("activeNoti");
            setTimeout(()=>{
                document.querySelector(".paymentNoti").classList.remove("activeNoti");
            },2000)
        }
    }
    else {
        document.querySelector(".updateModal").classList.add("activeModal");
    }
}


document.querySelector(".buttonCloseUpdate").addEventListener("click",close);
document.querySelector(".closeModalFooterUpdate").addEventListener("click",close);

function close(){
    document.querySelector(".updateModal").classList.remove("activeModal");
}

document.querySelector(".agressButtonUpdate").addEventListener("click",()=>{
    var codeBook  = document.querySelector("#codeNewBook").value+" "+oldCodeBook;
    console.log(codeBook);
    const xml = new XMLHttpRequest();
    xml.onload=function(){
        window.location.replace("/muon-tra-sach/hoan-tra/"+mssv);
    }
    xml.open("PUT","/api/borrow-details/update-book-borrow/"+codeBorrow+"/"+codeBook);
    xml.send();
})
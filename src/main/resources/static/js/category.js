$(document).ready(function() {
    var result="";
    $.ajax({
        url: "/api/category/findAll"
    }).then(function(data) {
        console.log(data);
        console.log(data[0].codeCategory);
        for(var i=0;i<data.length;i++){
            result+="<tr>"+
                "<th scope='row'>"+i+"</th>"+
                "<td>"+data[i].codeCategory+"</td>"+
                "<td>"+data[i].nameCategory+"</td>"+
                "<td>"+data[i].createdOn+"</td>"+
                "<td>"+data[i].modifiedOn+"</td>"+
                "<td><button type='button' onclick='modalNoti(this)' style='margin-right: 10px;' class='btn btn-warning updateInventory'>Chỉnh sửa</button>"+
                "<button type='button' onclick='modalTrash(this)' class='btn btn-info deleteInventory'>Xóa</button>"+
                "</td>"+
                "</tr>"
            ;
        }
        $("#categoryTableBody").append(result);
    });
});



var linkUpdateBook = document.querySelectorAll(".updateInventory");
var deleteBook = document.querySelectorAll(".deleteInventory");

var buttonCloseUpdate = document.querySelector(".buttonCloseUpdate");
var closeChooseButtonUpdate = document.querySelector(".closeModalFooterUpdate");

var buttonCloseTrash = document.querySelector(".buttonCloseTrash");
var closeChooseButtonTrash = document.querySelector(".closeModalFooterTrash");



function modalNoti(e){
    var text=e.parentNode.parentNode.childNodes.item(1).textContent;
    console.log(text);
    document.querySelector(".ContentUpdate > span > .textModal").textContent=text;
    document.querySelector(".updateModal").classList.add("activeModal");
    document.querySelector(".updateModal >.modalDetails >.modalFooter > .agressButtonUpdate").addEventListener("click",function(e){
    //     const xhttp = new XMLHttpRequest();
    //     xhttp.onload = function(){
    //        window.location.href="/the-loai-sach/chinh-sua-danh-muc-sach";
    //     }
    //     xhttp.open("GET","/the-loai-sach/chinh-sua-danh-muc-sach",true);
    //     xhttp.send();
        window.location.href="/the-loai-sach/chinh-sua-danh-muc-sach/"+text;
    })
}



function modalTrash(e){
    var text=e.parentNode.parentNode.childNodes.item(1).textContent;
    document.querySelector(".ContentTrash > span > .textModal").textContent=text;
    document.querySelector(".trashModal").classList.add("activeModal");
    document.querySelector(".trashModal >.modalDetails >.modalFooter > .agressButtonTrash").addEventListener("click",function(e){
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function(){
            window.location.href="/the-loai-sach";
        }
        xhttp.open("DELETE","/api/category/delete/"+text);
        xhttp.send();
    })
}



buttonCloseUpdate.addEventListener("click",closeModalUpdate);
closeChooseButtonUpdate.addEventListener("click",closeModalUpdate);
function closeModalUpdate(e){
    document.querySelector(".updateModal").classList.remove("activeModal");
}


buttonCloseTrash.addEventListener("click",closeModalTrash);
closeChooseButtonTrash.addEventListener("click",closeModalTrash);
function closeModalTrash(e){
    document.querySelector(".trashModal").classList.remove("activeModal");
}

const category=[

]





function myFunction(){
    var inputFilterSearch = document.querySelector("#myInput");
    var filter = inputFilterSearch.value.toUpperCase();
    var table = document.querySelector("#myTable");
    var tr = table.getElementsByTagName("tr");
    for(var i = 1 ; i < tr.length ; i++){
        var txt = tr[i].textContent;
        if(txt.toUpperCase().indexOf(filter)>-1){
            tr[i].style.display="";
        }
        else{
            tr[i].style.display="none";
        }
    }
}



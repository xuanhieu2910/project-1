var linkUpdateBook = document.querySelectorAll(".editBook");
var deleteBook = document.querySelectorAll(".deleteBook");

var buttonCloseUpdate = document.querySelector(".buttonCloseUpdate");
var closeChooseButtonUpdate = document.querySelector(".closeModalFooterUpdate");

var buttonCloseTrash = document.querySelector(".buttonCloseTrash");
var closeChooseButtonTrash = document.querySelector(".closeModalFooterTrash");



$(document).ready(function (){
    $.ajax({
        url:"/api/book/getAll"
    }).then(function(data){
        if(data.length==0){
            return null;
        }
        else{
            var html="";
            for(var i=0;i<data.length;i++){
               html+="<tr>"+
                   "<th scope='row'>"+i+"</th>"+
                   "<td class='codeBook'>"+data[i].codeBookParent+"</td>"+
                   "<td>"+data[i].nameCategory+"</td>"+
                   "<td><a href='bookDetails.html'>"+data[i].nameBook+"</a></td>"+
                   "<td class='descriptionBook'>"+data[i].descriptionBook+"</td>"+
                   "<td>"+data[i].nameMajor+"</td>"+
                   "<td>"+data[i].author+"</td>"+
                   "<td>"+data[i].company+"</td>"+
                   "<td>"+data[i].createdOn+"</td>"+
                   "<td>"+data[i].quantity+"</td>"+
                   "<td>"+data[i].inventory+"</td>"+
                   "<td>"+data[i].sales+"</td>"+
                   "<td>"+
                   "<i onclick='modalNoti(this)' class='fas fa-edit'></i>"+
                   "<i onclick='modalTrash(this)' class='fas fa-trash'></i>"+
                   "</td>"+
                   "</tr>";
            }
            $("#inventoryBooks").append(html);
        }
    })
})



function modalNoti(e){
    var codeBook = e.parentNode.parentNode.childNodes.item(1).textContent;
    var text=e.parentNode.parentNode.childNodes.item(3).textContent;
    console.log(text);
    document.querySelector(".ContentUpdate > span > .textModal").textContent=text;
    document.querySelector(".updateModal").classList.add("activeModal");
    document.querySelector(".updateModal >.modalDetails >.modalFooter > .agressButtonUpdate").addEventListener("click",function(e){
            window.location.href="/sach/chinh-sua-sach/"+codeBook;
    })
};




function modalTrash(e){
    var codeBook = e.parentNode.parentNode.childNodes.item(1).textContent;
    var text=e.parentNode.parentNode.childNodes.item(3).textContent;
    console.log(text);
    document.querySelector(".ContentTrash > span > .textModal").textContent=text;
    document.querySelector(".trashModal").classList.add("activeModal");
    document.querySelector(".trashModal >.modalDetails >.modalFooter > .agressButtonTrash").addEventListener("click",function(e){
        const xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.onload = function(){
            window.location.replace("/sach");
        }
        xmlHttpRequest.open("DELETE","/api/book/delete/"+codeBook);
        xmlHttpRequest.send();
    })
};




buttonCloseUpdate.addEventListener("click",closeModalUpdate);
closeChooseButtonUpdate.addEventListener("click",closeModalUpdate);
function closeModalUpdate(e){
    document.querySelector(".updateModal").classList.remove("activeModal");
}

buttonCloseTrash.addEventListener("click",closeModalTrash);
closeChooseButtonTrash.addEventListener("click",closeModalTrash);

function closeModalTrash(){
    document.querySelector(".trashModal").classList.remove("activeModal");
};


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
};



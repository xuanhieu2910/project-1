var linkUpdateBook = document.querySelectorAll(".editBook");
var deleteBook = document.querySelectorAll(".deleteBook");
//---------------------------------------------------------------------------------------------------------
var buttonCloseUpdate = document.querySelector(".buttonCloseUpdate");
var closeChooseButtonUpdate = document.querySelector(".closeModalFooterUpdate");
//---------------------------------------------------------------------------------------------------------
var buttonCloseTrash = document.querySelector(".buttonCloseTrash");
var closeChooseButtonTrash = document.querySelector(".closeModalFooterTrash");

//---------------------------------------------------------------------------------------------------------

var buttonPayment = document.querySelector(".closeModalFooterPayment");
var closeChooseButtonPayment = document.querySelector(".buttonClosePayment");

//---------------------------------------------------------------------------------------------------------


// var buttonExportFile = document.querySelector(".button-exportFile");
// var buttonCloseExport = document.querySelector(".buttonCloseExport");
// var closeModalFooterExportFile = document.querySelector(".closeModalFooterExportFile");
// var buttonAgressExport = document.querySelector(".agressButtonExportFile");



// buttonExportFile.addEventListener("click",openNotiExportFile);
// buttonCloseExport.addEventListener("click",closeExport);
// closeModalFooterExportFile.addEventListener("click",closeExport);
// buttonAgressExport.addEventListener("click",exportAllHistoryBorrow);
//
// function openNotiExportFile(){
//     document.querySelector(".exportAllHistoryBorrowStudent").classList.add("activeModal");
// }
//
// function closeExport(){
//     document.querySelector(".exportAllHistoryBorrowStudent").classList.remove("activeModal");
// }
//
// function exportAllHistoryBorrow(e){
//     console.log("HEHEHEHE");
// }






var listBorrowed=new Array();
class resultBorrowed{
    constructor(codeBorrow){
        this.codeBorrow = codeBorrow;
        this.codeBookss=new Array();
    }
    add(codeBook,price){
        this.codeBookss.push(new PaymentCodeBookDTO(price,codeBook));
    }
}

class PaymentCodeBookDTO{
    constructor(price,codeBook) {
        this.price = price;
        this.codeBook=codeBook;
    }
}


//---------------------------------------------------------------------------------------------------------



var arrayCheckBorrow = new Array();
var arrayMoney = new Array();



$(document).ready(function(){
    var mssv = window.location.pathname;
    mssv= mssv.split("/")[3];
    $.ajax({
        url:"/api/borrow-details/"+mssv
    }).then(function(data){
        console.log(data);
        if(data.length==0){
            return null;
        }
        else{
            var html="";
            for(var i=0;i<data.length;i++){
                html+="<tr>"+
                    "<th scope='row'>"+i+"</th>"+
                    "<td>"+data[i].codeBorrow+"</td>"+
                    "<td>"+data[i].codeBookChild+"</td>"+
                    "<td>"+data[i].createdOn+"</td>"+
                    "<td>"+data[i].expiry+"</td>"+
                    "<td class='moneyForBorrow'>"+
                       "<div id='money'>"+data[i].price+"</div>"+
                        "<div id='moneyValue'>VNĐ</div>"+
                    "</td>"+
                    "<td>"+
                        "<input type='checkbox' name='checkBorrow' onclick='handleClick(this)' class='checkBoxBorrow'>"+
                    "</td>"+
                    "<td>"+
                        "<button type='button'  onclick='modalNoti(this)' class='btn btn-warning'>Chỉnh sửa</button>"+
                        "<button type='button' onclick='modalTrash(this)'  class='btn btn-info'>Xóa</button>"+
                    "</td>"+
                    "</tr>";
            }
            $("#resultDetailsBorrow").append(html);
        }
    })
})



$(document).ready(function(){
    var mssv = window.location.pathname;
    mssv= mssv.split("/")[3];
    $.ajax({
        url:"/api/students/"+mssv
    }).then(function (data){
        var nameStudent = data.nameStudent;
        document.querySelector("#nameStudent").textContent=nameStudent;
    })
})


// Chỉnh sửa chi tiết sách mượn
function modalNoti(e){
    var text=e.parentNode.parentNode.childNodes.item(1).textContent;
    var codeBookChild = e.parentNode.parentNode.childNodes.item(2).textContent
    console.log(text);
    document.querySelector(".ContentUpdate > span > .textModal").textContent=text;
    document.querySelector(".updateModal").classList.add("activeModal");
    document.querySelector(".updateModal >.modalDetails >.modalFooter > .agressButtonUpdate").addEventListener("click",function(e){
        window.location.replace("/muon-tra-sach/chinh-sua-muon-tra/"+text+"/"+codeBookChild);
    })
}


function modalTrash(e){
    var namePath = window.location.pathname.split("/");
    var MSSV = namePath[3];
    var codeBorrow=e.parentNode.parentNode.childNodes.item(1).textContent;
    var codeBook=e.parentNode.parentNode.childNodes.item(2).textContent;
    var text=e.parentNode.parentNode.childNodes.item(1).textContent;
    document.querySelector(".ContentTrash > span > .textModal").textContent=text;
    document.querySelector(".trashModal").classList.add("activeModal");
    document.querySelector(".trashModal >.modalDetails >.modalFooter > .agressButtonTrash").addEventListener("click",function(e){
        var codeBorrowDetails={
            "codeBorrow":text,
            "codeBook":codeBook
        }
        $.ajax({
            type:"DELETE",
            url:"/api/borrow-details/delete?"+$.param(codeBorrowDetails),
            dataType:'json',
            contentType:'application/json',
            success:function(){
                window.location.replace("/muon-tra-sach/hoan-tra/"+MSSV);
            }
        })
    })
}



function modalTrashBorrow(){
    var mssv = window.location.pathname;
    mssv= mssv.split("/")[3];
    document.querySelector(".trashModal").classList.add("activeModal");
        var jsonDataString = JSON.stringify(listBorrowed);
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function(){
            window.location.replace("/muon-tra-sach");
        }
        xhttp.open("POST","/api/borrow-details/payment-borrow/"+mssv);
        xhttp.setRequestHeader("Content-Type","application/json");
        xhttp.send(jsonDataString);
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



buttonPayment.addEventListener("click",closeModalPayment);
closeChooseButtonPayment.addEventListener("click",closeModalPayment);
function closeModalPayment(e){
    document.querySelector(".checkPayment").classList.remove("activeModal");
}




function handleClick(e){
    var idBook=e.parentNode.parentNode.childNodes.item(2).textContent;
    if(e.checked){
        if(!arrayCheckBorrow.includes(idBook)){
            var codeBorrow = e.parentNode.parentNode.childNodes.item(1).textContent;
            var money = e.parentNode.parentNode.childNodes.item(5).childNodes.item(0).textContent;
            console.log(typeof  money);
            createCartBorrow(codeBorrow,money,idBook);
            arrayCheckBorrow.push(idBook);
            money = money.replace( /\s/g, '');
            arrayMoney.push(money);
        }
    }
    else if(!e.checked){
        if(arrayCheckBorrow.includes(idBook)){
            var codeBorrow = e.parentNode.parentNode.childNodes.item(1).textContent;
            deleteResultBorrow(codeBorrow,idBook);
            var index = arrayCheckBorrow.indexOf(idBook);
            arrayCheckBorrow.splice(index,1);
            arrayMoney.splice(index,1);
            console.log(listBorrowed);
        }
    }
    var sum =0;
    for(var i in arrayMoney){
        sum+=Number.parseInt(arrayMoney[i]);
    }

    document.querySelector("#resultMoney").textContent=sum;
    document.querySelector("#resultCount").textContent = arrayCheckBorrow.length;
}


function createCartBorrow(codeBorrow,price,idBook){
    var checkBorrow = true;
    if(listBorrowed.length==0){
        var newCodeBorrow =new resultBorrowed(codeBorrow);
        newCodeBorrow.add(idBook,price);
        listBorrowed.push(newCodeBorrow);
    }
    else{
        for(var i = 0 ; i <listBorrowed.length;i++){
            if(listBorrowed[i].codeBorrow==codeBorrow){
                listBorrowed[i].add(idBook,price);
                i=listBorrowed.length+1;
                checkBorrow=true;
                break;
            }
            else{
                checkBorrow=false;
            }
        }
        if(!checkBorrow) {
            var newCodeBorrow2 = new resultBorrowed(codeBorrow);
            newCodeBorrow2.add(idBook,price);
            console.log(newCodeBorrow2);
            listBorrowed.push(newCodeBorrow2);
        }
    }

}

function deleteResultBorrow(codeBorrow,idBook){
    for(var i=0;i<listBorrowed.length;i++){
        if(listBorrowed[i].codeBorrow==codeBorrow){
            for(var j=0;j<listBorrowed[i].codeBookss.length;j++){
                if(listBorrowed[i].codeBookss[j].codeBook==idBook){
                    listBorrowed[i].codeBookss.splice(j,1);
                }
            }
            if(listBorrowed[i].codeBookss.length==0){
                listBorrowed.splice(i,1);
            }
        }
    }

}



function postBorrow(){
    if(arrayCheckBorrow.length == 0){
        document.querySelector(".paymentNoti").classList.add("activeNoti");
        setTimeout(()=>{
            document.querySelector(".paymentNoti").classList.remove("activeNoti");
        },3000)
    }
    else{
        document.querySelector(".checkPayment").classList.add("activeModal");
    }
}


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






























var buttonExportFile = document.querySelector(".button-exportFile");
var buttonCloseExport = document.querySelector(".buttonCloseExport");
var closeModalFooterExportFile = document.querySelector(".closeModalFooterExportFile");
var buttonAgressExport = document.querySelector("#exportFileHistory");

buttonExportFile.addEventListener("click",openNotiExportFile);
buttonCloseExport.addEventListener("click",closeExport);
closeModalFooterExportFile.addEventListener("click",closeExport);
buttonAgressExport.addEventListener("click",exportAllHistoryBorrow);

function openNotiExportFile(){
    document.querySelector(".exportAllHistoryBorrowStudent").classList.add("activeModal");
}

function closeExport(){
    document.querySelector(".exportAllHistoryBorrowStudent").classList.remove("activeModal");
}

function exportAllHistoryBorrow(e){
    var stringMssv = window.location.search;
    const paramerter = new URLSearchParams(stringMssv);
    var mssv = paramerter.get("mssv");
    buttonAgressExport.setAttribute("href","/export-file?mssv="+mssv);
    document.querySelector(".exportAllHistoryBorrowStudent").classList.remove("activeModal");
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

$(document).ready(function(){
    var stringMssv = window.location.search;
    const paramerter = new URLSearchParams(stringMssv);
    var mssv = paramerter.get("mssv");
    $.ajax({
        type:"GET",
        url:"/api/borrow-details/history-details?mssv="+mssv,
        content:"application/json"
    }).then(function(data){
        console.log(data);
        var html="";
        for(var i=0;i<data.length;i++){
            html+="<tr>"+
                "<th scope='row'>"+i+"</th>"+
                "<td>"+data[i].codeBook+"</td>"+
                "<td>"+data[i].nameBook+"</td>"+
                "<td>"+data[i].createdOn+"</td>"+
                "<td>"+data[i].expiry+"</td>"+
                "<td>"+data[i].datePayment+"</td>"+
                "<td>"+
                    "<span class='moneyStudent'>"+
                        "<span>"+data[i].price+"</span>"+
                        "<span class='badge badge-secondary money'>VNĐ</span>"+
                    "</span>"+
                "</td>"+
                "</tr>";
        }
        $("#resultHistoryBorrow").append(html);
    })
})

$(document).ready(function(){
    var stringMssv = window.location.search;
    const paramerter = new URLSearchParams(stringMssv);
    var mssv = paramerter.get("mssv");
    $.ajax({
        url:"/api/students/"+mssv
    }).then(function(data){
        document.querySelector("#nameStudent").textContent=data.nameStudent;
    })
})
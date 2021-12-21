
$(document).ready(function(){
    $.ajax({
        url:"/api/borrow"
    }).then(function(data){
        console.log(data);
        if(data.length==0){
            return null;
        }
        else{
            var html="";
         for(var i=0;i<data.length;i++){
                html+="<tr>"+
                    "<th scope='col'>"+i+"</th>"+
                    "<td>"+data[i].mssv+"</td>"+
                    "<td>"+data[i].nameStudent+"</td>";
                if(data[i].status=="NOT"){
                    html+="<td>"+"<span class='badge badge-secondary'>Chưa hoàn trả</span>" +"</td>"+
                        "<td>"+"<button type='button' onclick='paymentBorrow(this)' class='btn btn-warning'>Hoàn trả"+
                    "</button>"+"</td>";
                    ;
                }
                html+="</tr>";
            }
         $("#resultStudentBorrowed").append(html);
        }
    })
})



function paymentBorrow(element){
    var mssv = element.parentNode.parentNode.childNodes.item(1).textContent;
    window.location.replace("/muon-tra-sach/hoan-tra/"+mssv);
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




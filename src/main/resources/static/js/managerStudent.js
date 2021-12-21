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
    $.ajax({
        url:"/student-borrowed"
    }).then(function(data){
        console.log(data);
        var html="";
        for(var i =0;i<data.length;i++){
            html+="<tr>"+
                "<th scope='row'>"+i+"</th>"+
                "<td>"+data[i].mssv+"</td>"+
                "<td>"+data[i].nameStudent+"</td>"+
                "<td>"+data[i].sumBorrowed+"</td>"+
                "<td>"+data[i].sumPaymentBorrowed+"</td>"+
                "<td>"+data[i].sumDontPaymentBorrowed+"</td>"+
                "<td>"+
                    "<span class='moneyStudent'>"+
                        "<span>"+data[i].sumPrice+"</span>"+
                        "<span class='badge badge-secondary money'>VNĐ</span>"+
                    "</span>"+
                "</td>"+
                "<td>"+
                    "<button type='button' onclick='historyStudent(this)' id='buttonHistory' class='btn btn-primary'>Chi tiết</button>"+
                "</td>"+
                "</tr>";
        }
        $("#resultStudent").append(html);
    })
})

function historyStudent(element){
    var mssv =element.parentNode.parentNode.childNodes.item(1).textContent;
    window.location.replace("/doc-gia/lich-su-muon-tra?mssv="+mssv);
}
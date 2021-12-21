var inputMssv = document.querySelector('#inputMSSV');
var arr = new Array();
var codeBooks=new Array();
$(document).ready(function(){
    $.ajax({
        url:"/api/students",
    }).then(function(student){
        console.log(student);
        var html="<ul id='studentFilter'>";
        for(var i of student){
            html+="<li class='result' style='display:none'>"+i.mssv+" : "+i.nameStudent+"</li>";
        }
        html+="</ul>";
        $("#outputFilterMssv").append(html);
        var demo = document.querySelectorAll(".result");
        demo.forEach(elements => elements.addEventListener("click",(e)=>{
            e.target.style.display="none";
            document.getElementById("inputMSSV").blur();
            document.getElementById("outputFilterMssv").style.display="none";
            var index = e.target.textContent.indexOf(":");
            var mssv = e.target.textContent.substring(0,index-1);
            const filterStudent=student.filter(e=>mssv==e.mssv);
            document.getElementById("inputMSSV").value=mssv;
            var name=filterStudent[0].nameStudent;
            var major =filterStudent[0].major;
            var email=filterStudent[0].email;
            var phone=filterStudent[0].phone;
            var html="<div class='form-group'>"+
                "<label for='nameStudentForCreateCard'>Họ và tên</label>"+
                "<input type='text' name='nameStudentForCreateCard' id='nameStudentForCard' class='form-control' readonly>"
                +"</div>"+
                "<div class='form-group'>"+
                "<label for='majorStudentForCreateCard'>Viện</label>"+
                "<input type='text' name='majorStudentForCreateCard' id='majorStudentForCreatedCard' class='form-control' readonly>"
                +"</div>"+
                "<div class='form-group'>"+
                "<label for='emailStudentForCreateCard'>Email</label>"+
                "<input type='text' name='majorStudentForCreateCard' id='emailStudentForCreatedCard' class='form-control' readonly>"
                +"</div>"+
                "<div class='form-group'>"+
                "<label for='phoneStudentForCreateCard'>Số điện thoại</label>"+
                "<input type='text' name='phoneStudentForCreateCard' id='phoneStudentForCreateCard' class='form-control'  readonly>"
                +"</div>";
            var result= document.createElement("div");
            result.innerHTML+=html;
            result.setAttribute("id","studentDetails");
            document.querySelectorAll('.form-group')[0].appendChild(result);

            document.getElementById("nameStudentForCard").setAttribute("value",name);
            document.getElementById("majorStudentForCreatedCard").setAttribute("value",major);
            document.getElementById("emailStudentForCreatedCard").setAttribute("value",email);
            console.log(document.getElementById('phoneStudentForCreateCard'));
            document.getElementById("phoneStudentForCreateCard").setAttribute("value",phone);
        }))
    })
})


//-----------------------------------------------------------------------------------------------------------------------------------------------------


$(document).ready(function(){
    $.ajax({
        url:"/api/code_book_child"
    }).then(function(bookDemo){
        if(bookDemo.length==0){
            return null;
        }
        else{
            for(var i=0;i<bookDemo.length;i++){
                codeBooks.push(bookDemo[i]);
            }
            var bookBorrow ="<ul id='ulBookBorrow'>";
            for(var i of bookDemo){
                bookBorrow+="<li class='liBookBorrow' style='display:none' onclick='addBookInCart(this)'> <div class='rowBook'> <span class='titleBook'>Mã sách: </span>"+i.codeBookChild+"</div> </li>";
            };
            bookBorrow+="</ul>";
            $("#outputFilterBookBorrow").append(bookBorrow);
        }
    })
})
document.getElementById("inputMSSV").addEventListener("keyup",(event)=>{
    var textInput = document.getElementById("inputMSSV").value;
    if(textInput===""){
        document.getElementById("outputFilterMssv").style.display="none";
        if( document.getElementsByClassName('form-group')[0].contains(document.getElementById("studentDetails"))){
            document.getElementsByClassName('form-group')[0].removeChild(document.getElementById("studentDetails"));
        }
    }
    else if(textInput!==""){
        document.getElementById("outputFilterMssv").style.display="";
    }
    var check = true;
    if(event.key=="Enter"){
            var li = document.getElementsByClassName("result");
            for(var i=0; i<li.length;i++){
                var text = li[i].textContent;
                if(text.includes(textInput)){
                    li[i].style.display="";
                    check=false;
                    break;
                }
                else {
                    li[i].style.display = "none";
                }
            }
            if(check){
                document.querySelector(".notiNewBook").classList.add("activeNotiNewBook");
                setTimeout(function(){
                    document.querySelector(".notiNewBook").classList.remove("activeNotiNewBook");
                },2000);
            }
        }
})


document.getElementById("codeBook").addEventListener("keyup",(e)=>{
    var textInputCodeBook = document.getElementById("codeBook").value;
    if(textInputCodeBook===""){
        document.getElementById("outputFilterBookBorrow").classList.add("hiden");
    }
    else if(textInputCodeBook!==""){
        document.getElementById("outputFilterBookBorrow").classList.remove("hiden");
        document.querySelector('#outputFilterBookBorrow').style.display="";
    };
    var check=true;
    if(e.key=="Enter"){
        var li = document.getElementsByClassName('liBookBorrow');
        for(var i=0; i <li.length;i++){
            var text = li[i].textContent.toUpperCase();
            if(text.includes(textInputCodeBook.toUpperCase())){
                li[i].style.display='';
                check=false;
                break;
            }
            else{
                li[i].style.display="none";
            }
        }
        if(check){
            document.querySelector(".notiNewBook").classList.add("activeNotiNewBook");
            setTimeout(function(){
                document.querySelector(".notiNewBook").classList.remove("activeNotiNewBook");
            },2000);
        }
    }
})



function addBookInCart(element){
    var id = element.childNodes.item(1).childNodes.item(2).textContent;
    document.getElementById("codeBook").value=id;
    var result = codeBooks.filter(element=>element.codeBookChild == id);
    console.log("Result"+result);
    if(arr.length==0){
        arr.push(id);
        var html="<th>"+result[0].codeBookChild+"</th>"+
            "<th>"+result[0].nameBook+"</th>"+
            "<th>"+result[0].nameMajor+"</th>"+
            "<th>"+result[0].nameCategory+"</th>"+
            "<th>"+result[0].nameAuthor+"</th>"+
            "<th><input type='number' class='numberBorrowBook' value='1' name='inventoryBorrowBook' readonly></th>"+
            "<th class='active'>"+
                "<button type='button' class='btn btn-secondary' onclick='deleteBookBorrow(this)'>Xóa</button>"+
            "</th>";
        var element = document.createElement("tr");
        element.innerHTML+=html;
        document.getElementById('parentResultBorrowBook').appendChild(element);

        document.getElementById("outputFilterBookBorrow").classList.add("hiden");
        document.getElementById("tableBorrow").style.display="";
    }
    else{
        if(arr.includes(id)){
            document.querySelector(".haveAllreadyBook").classList.add("activeNoti");
            setTimeout(()=>{
                document.querySelector(".haveAllreadyBook").classList.remove("activeNoti");
            },3000)
        }
        else{
            arr.push(id);
            var demo = codeBooks.filter(element=>element.codeBookChild == id);
            var html="<th>"+demo[0].codeBookChild+"</th>"+
                "<th>"+demo[0].nameBook+"</th>"+
                "<th>Tài liệu đại cương</th>"+
                "<th>Viện toán tin</th>"+
                "<th>Bùi Xuân Diệu</th>"+
                "<th><input type='number' class='numberBorrowBook' value='1' name='inventoryBorrowBook' readonly></th>"+
                "<th class='active'>"+
                "<button type='button' class='btn btn-secondary' onclick='deleteBookBorrow(this)'>Xóa</button>"+
                "</th>";
            var element = document.createElement("tr");
            element.innerHTML+=html;
            document.getElementById('parentResultBorrowBook').appendChild(element);

            document.getElementById("outputFilterBookBorrow").classList.add("hiden");
            document.getElementById("tableBorrow").style.display="";
        }
    }
}


document.getElementById("codeBook").addEventListener("keyup",(e)=>{

    if(e.key == 'Enter'){
        var id = document.getElementById("codeBook").value;
        var result = codeBooks.filter(element=>element.codeBookChild == id);
        if(result.length==0){
            document.querySelector(".noLookForBook").classList.add("activeNoti");
            setTimeout(()=>{
                document.querySelector(".noLookForBook").classList.remove("activeNoti");
            },3000)
        }
        else{
            if(arr.length==0){
                arr.push(id);
                var html="<th>"+result[0].codeBookChild+"</th>"+
                    "<th>"+result[0].nameBook+"</th>"+
                    "<th>Tài liệu đại cương</th>"+
                    "<th>Viện toán tin</th>"+
                    "<th>Bùi Xuân Diệu</th>"+
                    "<th><input type='number' class='numberBorrowBook' value='1' name='inventoryBorrowBook' readonly></th>"+
                    "<th class='active'>"+
                    "<button type='button' class='btn btn-secondary' onclick='deleteBookBorrow(this)'>Xóa</button>"+
                    "</th>";
                var element = document.createElement("tr");
                element.innerHTML+=html;
                document.getElementById('parentResultBorrowBook').appendChild(element);

                document.getElementById("outputFilterBookBorrow").classList.add("hiden");
                document.getElementById("tableBorrow").style.display="";
            }
            else{
                if(arr.includes(id)){
                    document.querySelector(".haveAllreadyBook").classList.add("activeNoti");
                    setTimeout(()=>{
                        document.querySelector(".haveAllreadyBook").classList.remove("activeNoti");
                    },3000)
                }
                else{
                    arr.push(id);
                    var demo = codeBooks.filter(element=>element.codeBookChild == id);
                    var html="<th>"+demo[0].codeBookChild+"</th>"+
                        "<th>"+demo[0].nameBook+"</th>"+
                        "<th>Tài liệu đại cương</th>"+
                        "<th>Viện toán tin</th>"+
                        "<th>Bùi Xuân Diệu</th>"+
                        "<th><input type='number' class='numberBorrowBook' value='1' name='inventoryBorrowBook' readonly></th>"+
                        "<th class='active'>"+
                        "<button type='button' class='btn btn-secondary' onclick='deleteBookBorrow(this)'>Xóa</button>"+
                        "</th>";
                    var element = document.createElement("tr");
                    element.innerHTML+=html;
                    document.getElementById('parentResultBorrowBook').appendChild(element);

                    document.getElementById("outputFilterBookBorrow").classList.add("hiden");
                    document.getElementById("tableBorrow").style.display="";
                }
            }
        }
    }
})


function deleteBookBorrow(element){
    document.getElementById('parentResultBorrowBook').removeChild(element.parentNode.parentNode);
    var id = element.parentNode.parentNode.childNodes.item(0).textContent;
    var indexID = arr.indexOf(id);
    arr.splice(indexID,1);
}


function submitCardBorrow(){
    var mssv = document.getElementById("inputMSSV").value;
    var codeBorrow = document.getElementById("codeBorrow").textContent;
    const data = {
        codeBorrow:codeBorrow,
        mssv: mssv,
        codeBooksBorrowed : arr
    };
    const jsonString = JSON.stringify(data);
    console.log(jsonString);
    const  xhttp = new XMLHttpRequest();
    xhttp.onload=function(){
        window.location.replace("/muon-tra-sach");
    }
    xhttp.open("POST","/api/borrow/save");
    xhttp.setRequestHeader("Content-Type","application/json");
    xhttp.send(jsonString);
}

function submitCreateNewBorrow(){
    let mssv = document.getElementById("inputMSSV").value;
    let nameStudent = document.getElementById("nameStudentForCard").value;
    let majorStudent = document.getElementById("majorStudentForCreatedCard").value;
    let phoneStudent = document.getElementById("phoneStudentForCreateCard").value;
    let emailStudent= document.getElementById("emailStudentForCreatedCard").value;
    let dateNow = new Date();
    let createdOn = dateNow.getDate()+"-"+(dateNow.getMonth()+1)+"-"+dateNow.getFullYear()+" "+dateNow.getHours()+":"+dateNow.getMinutes()+":"+dateNow.getSeconds();
    console.log(createdOn);
    const  xmlHttp = new XMLHttpRequest();
    xmlHttp.onload = function(){
        var quantityCodeBorrow = parseInt(this.response);
        if(quantityCodeBorrow==0){
            quantityCodeBorrow=0;
        }
        else{
            quantityCodeBorrow+=1;
        }
        var codeBorrow = mssv+"-"+"PM"+"-"+quantityCodeBorrow;
        document.getElementById("codeBorrow").textContent=codeBorrow;
        document.getElementById("mssvBorrow").textContent=mssv;
        document.getElementById("nameStudentBorrow").textContent=nameStudent;
        document.getElementById("majorStudentBorrow").textContent=majorStudent;
        document.getElementById("phoneStudentBorrow").textContent=phoneStudent;
        document.getElementById("emailStudentBorrow").textContent= emailStudent;
        document.getElementById("createdOnBorrow").textContent=createdOn;
        var htmlBorrow="";
        console.log(arr.length);
        console.log(arr);
        for(var i=0;i<arr.length;i++){
            var listBookBorrow = codeBooks.filter(element=>element.codeBookChild == arr[i]);
            htmlBorrow+="<tr>"+
                        "<th scope='col'>"+i+"</th>"+
                        "<th scope='row'>"+listBookBorrow[0].codeBookChild+"</th>"+
                        "<th scope='row'>"+listBookBorrow[0].nameBook+"</th>"+
                        "<th scope='row'>"+1+"</th>"+
                        "</tr>";
        }
        $("#resultCardBorrow").append(htmlBorrow);
        document.querySelector(".modal--cartBorrow").classList.add("cardBorrow--active");
    }
    xmlHttp.open("GET","/api/borrow/number-borrow/"+mssv);
    xmlHttp.send();
}

function cancelCardBorrow(){
    document.querySelector(".modal--cartBorrow").classList.remove("cardBorrow--active");
}


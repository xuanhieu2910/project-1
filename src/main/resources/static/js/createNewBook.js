var codeBook = document.querySelector("#codeBook");
var inputExcelBook  = document.querySelector(".fileExcelBook");
var buttonClose = document.querySelector("#buttonCloseExcel");
var buttonSubmitExcel = document.querySelector(".submitExcel");




codeBook.addEventListener("onkeyup",(e)=>{
   console.log(e.target);
})

function openModal(){
   var input = document.querySelectorAll("input");
   var check = false;
   console.log(input);
   for(var i=2 ; i<input.length;i++){
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

function submitCreateNewBook(){

   const data={
      codeBook:document.getElementById("codeBook").value,
      nameBook:document.getElementById("nameCreateNewBook").value,
      codeCategory:document.getElementById("categoryBook").value,
      codeMajor:document.getElementById("createNewBookMajor").value,
      author:document.getElementById("authorCreateNewBook").value,
      company:document.getElementById("companyCreateNewBook").value,
      price:document.getElementById("priceCreateNewBook").value,
      quantity:document.getElementById("quantityCreateNewBook").value,
      description:document.getElementById("statusCreateNewBook").value
   }
   var dataStringJson = JSON.stringify(data);
   console.log(dataStringJson);
   const  xmlHttp = new XMLHttpRequest();
   xmlHttp.onload = function(){
      window.location.replace("/sach");
   }
   xmlHttp.open("POST","/api/book/save");
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
         $("#categoryBook").append(optionCategory);
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
      $("#createNewBookMajor").append(optionMajor);
   })
})


inputExcelBook.addEventListener("click",function(e){
   document.querySelector(".modalIntroductionExcel").classList.add("activeIntroExcel");
})

buttonClose.addEventListener("click",()=>{
   document.querySelector(".modalIntroductionExcel").classList.remove("activeIntroExcel");
})


buttonSubmitExcel.addEventListener("click",submitBookFromFile);


function submitBookFromFile(){
      console.log(document.querySelector("#buttonFileExcel").files[0]);
      var data = new FormData();
      data.append("file",document.querySelector("#buttonFileExcel").files[0]);
      console.log(data);
      const xml = new XMLHttpRequest();
      xml.onload=function(){
         window.location.replace("/sach");
      }
      xml.open("POST","/api/book/create-new-book/files");
      xml.send(data);
}


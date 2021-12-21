$(document).ready(function(){
    var codeBook = window.location.pathname;
    const abc = codeBook.split("/")[3];
    console.log(abc);
    $("#codeUpdateCategory").val(abc);
})
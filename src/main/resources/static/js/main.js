
var filterTime = document.querySelector("#dateTimeFilterChart");
var listFilterTime = document.querySelectorAll(".filterTime");
var filterChoice = document.querySelectorAll(".dateTimeSelect");
filterTime.addEventListener("click",(e)=>{
    listFilterTime[0].classList.toggle("activeFilterTime");
})

filterChoice.forEach(element=>element.addEventListener("click",(e)=>{
    document.querySelector(".dateTimeFilterChoice #textDisplay").textContent = e.target.textContent;
    var dateTime = e.target.getAttribute("data-value-time");
    console.log(typeof dateTime);
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onload=function(){
        console.log(this.status);
    }
    xmlHttpRequest.open("GET","/api/chart?time="+dateTime);
    xmlHttpRequest.send();
}))

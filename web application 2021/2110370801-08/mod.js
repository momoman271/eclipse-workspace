var a = document.getElementById("a");
var b = document.getElementById("b");
var c = document.getElementById("c");
b.addEventListener('click', function(){
    c.innerHTML = parseInt(a.value, 10) % 50;
}, false);
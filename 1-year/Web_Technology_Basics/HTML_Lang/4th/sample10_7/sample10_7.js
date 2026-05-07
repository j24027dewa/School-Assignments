var value = null;
var num;
function checkForm(){
    var fruits = document.getElementsByName("fruit");
    for(var i = 0; i < fruits.length; i++){
        if(fruits[i].checked){
            value = fruits[i].value;
        }
    }
    num = parseInt(document.getElementById("num").value);
    if(isNaN(num)){
        num = -1;
    }
    if(value != null && num >= 0){
        document.getElementById("calc").disabled = false;
    }else{
        document.getElementById("calc").disabled = true;
    }
}

function calcPrice(){
    document.getElementById("priceSum").value = value * num;
}
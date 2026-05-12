function printHEllo(){
    document.write("Hello");
    document.write("<br />");
}

function factorial(num){
    var prod = 1;
    for(var n = 1; n <= num; n++){
        prod *= n
    }
    return prod;
}

printHEllo();

var N = 6;
var fact = factorial(N);
if(fact > 100){
    document.write(N + "の階乗は" + fact);
    document.write("<br />");
}
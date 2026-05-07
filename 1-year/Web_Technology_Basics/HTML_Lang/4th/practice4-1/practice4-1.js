var array = Array(10);
for(var i = 0; i < array.length; i++){
    array[i] = Math.floor(Math.random() * 1000) / 10;
}
document.write("配列arrayの中身は" + "<br />");

for(var i = 0; i < array.length; i++){
    document.write(array[i] + "<br />");
}

var max = null;

for(var i = 0; i < array.length; i ++){
    if(max < array[i]){
        max = array[i]
    }
}

document.write("最大値は" + max);


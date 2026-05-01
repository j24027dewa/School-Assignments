var array = Array(10);

for(var i = 0; i < 10; i++){
    array[i] = i * i;
}

for(i = 0; i < 10; i++){
    document.write("2*" + ( i * i) + "=" + (2 * array[i]))
    document.write("<br />")
}
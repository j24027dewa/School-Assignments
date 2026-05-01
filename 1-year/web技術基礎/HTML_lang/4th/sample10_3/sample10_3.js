for(var i = 1; i <= 100; i++){
    if((i % 3) == 0 && (i % 5) == 0){
        document.write("ボクイケメン");
   }else if((i % 3) == 0){
    document.write("ラーメン");
   }else if((i % 5) == 0){
    document.write("つけ麺");
   }else{
    document.write(i);
   }
   document.write("<br />")

}
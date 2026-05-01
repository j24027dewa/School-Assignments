function loadXMLdoc(url){
    doc = new XMLHttpRequest();
    doc.open("GET", url, false);
    doc.send();
    return doc.responseXML;
}

var doc = loadXMLdoc("Subject.xml");


/*科目名一覧　箇条書き*/ 
document.write("<h1>科目一覧</h1>");    
var nodes = doc.getElementsByTagName("科目名");
document.write("<ul>");
for(var i = 0; i < nodes.length; i ++){
    document.write("<li>");
    document.write(nodes.item(i).textContent);
    document.write("</li>");
}
document.write("</ul>");


/*区分分け*/
/*必修*/
document.write("<h1>科目一覧</h1>")
var num = 0
var division = doc.getElementsByTagName("区分");
var unitsNum = doc.getElementsByTagName("単位");
for(var i = 0; i < nodes.length; i++){
    if(division.item(i).textContent == "必修" ){
        num += Number(unitsNum.item(i).textContent);
    }
}
document.write("<h2>" + "必修(計" + num + "単位)" + "</h2>");

document.write("<ul>");
for(var i = 0; i < nodes.length; i++){
    if(division.item(i).textContent == "必修" ){
        document.write("<li>");
        document.write(nodes.item(i).textContent);
        document.write("</li>");
    }
}
document.write("</ul>");


/*選必*/
num = 0
for(var i = 0; i < nodes.length; i++){
    if(division.item(i).textContent == "選必" ){
        num += Number(unitsNum.item(i).textContent);
    }
}
document.write("<h2>選必(計" + num + "単位)</h2>");

document.write("<ul>");
for(var i = 0; i < nodes.length; i++){
    if(division.item(i).textContent == "選必" ){
        document.write("<li>");
        document.write(nodes.item(i).textContent);
        document.write("</li>");
    }
}
document.write("</ul>");


/*選択*/
num = 0
for(var i = 0; i < nodes.length; i++){
    if(division.item(i).textContent == "選択" ){
        num += Number(unitsNum.item(i).textContent);
    }
}
document.write("<h2>選択(計" + num + "単位)</h2>");

document.write("<ul>");
for(var i = 0; i < nodes.length; i++){
    if(division.item(i).textContent == "選択" ){
        document.write("<li>");
        document.write(nodes.item(i).textContent);
        document.write("</li>");
    }
}
document.write("</ul>");


/*一覧表*/
document.write("<h1>科目一覧表</h1>");
var subNums = doc.getElementsByTagName("科目");
var type = doc.getElementsByTagName("種類");
document.write("<table border=&quot;1&quot;>");
document.write("<tr>");
document.write("<th>科目ナンバー</th>");
document.write("<th>科目名</th>");
document.write("<th>種類</th>");
document.write("<th>区分</th>");
document.write("<th>単位</th>");
document.write("</tr>");
for(var i = 0; i < nodes.length; i ++){
    document.write("<tr>");
    var subNum = subNums.item(i);
    document.write("<td>" + subNum.getAttribute("科目ナンバー") + "</td>");
    document.write("<td>" + nodes.item(i).textContent + "</td>");
    document.write("<td>" + type.item(i).textContent + "</td>");
    document.write("<td>" + division.item(i).textContent + "</td>");
    document.write("<td>" + unitsNum.item(i).textContent + "</td>");
    document.write("</tr>");
}
document.write("</table>");
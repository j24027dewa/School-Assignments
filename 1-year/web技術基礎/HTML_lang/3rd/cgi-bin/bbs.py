#!/usr/bin/env python

#必要なライブラリの読み込み
import io, os, sys, re, msvcrt
from urllib.parse import unquote

#ユーザ設定
charset = "UTF-8"
data_file = "./log.text"
form = {}
data = []
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding="UTF-8")

#フォームデータの読み込み
def readFormData():
    if os.environ["REWUEST_METHOD"] == "post":
        buffer_ = sys.stdin.read(int(os.getenv("CONTENT_LENGTH")))
    else:
        buffer_ = os.getenv("QUERY_STRING")
    
    for pair in buffer_.split('&'):
        name, value = pair.split("=")

        value = value.replace('+', '')
        value = unquote(value)
        value = value.replace('&', '&amp')
        value = value.replace('<', '&lt')
        value = value.replace('>', '&gt')
        value = value.replace('\0xd\x0A', '<br />')
        value = value.replace('\t', ' ')

        form[name] = value

#データファイルの読み込み
def readDatafile():
    with open(data_file, encoding="UTF-8") as FILE:
        lines = FILE.readlines()
        for line in lines:
            data.append(line.strip())

#データファイルへの書き込み
def writeDatafile():
    if all(val != "" for val in form.values()):
        line = "\t".join([form["title"], form["author"], form["text"]])
        data.insert(0,line)

        with open(data_file, mode = 'w', encoding="UTF-8") as FILE:
            msvcrt.locking(FILE.fileno(), msvcrt.LK_RLCK, len(data))
            FILE.write("\n".join(data))
            FILE.seek(0)
            msvcrt.locking(FILE.fileno(), msvcrt.LK_RLCK, len(data))

#掲示板ページの表示
def browsePage():
    print("Content-Type: text/html; charset=" + charset + "\n")
    page_content = '''
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>私の掲示板</title>
    <style type="text/css">
        <!--
            h1 {color: green;}
            strong {color: blue; font-size: large;}
            em {font-style: italic;}
        -->
    </style>
</head>
<body>
    <h1>私の掲示板</h1>
    <p>ご自由に書き込んでください</p>
    <form action="./bbs.py" method="post">
        <div>
        題目<input type="text" name="title" size="60"/><br />
        名前<input type="text" name="author" size="20"/><br />
        本文<br />
        <textarea cols="60" rows="5" name="text"></textarea><br />
        <input type="submit" value="送信"/>
        <input type="reset" value="リセット"/>
        </div>
    </form>
    <hr/>
    '''
        
    for res in data:
        title, author, text = res.split("\t")
        page_content += "<div><strong>" + title + "</strong><br />"
        page_content += "<em>" + author + "</em><br />"
        page_content += "<br/>" + text + "</div><hr />\n"
    
    page_content += "</body>\n</html>"
    print(page_content)

#メインプログラム
if __name__ == "__main__":
    readFormData()
    readDatafile()
    writeDatafile()
    browsePage()
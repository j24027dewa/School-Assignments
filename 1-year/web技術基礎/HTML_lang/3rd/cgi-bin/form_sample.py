#!/usr/bin/env python
import cgi

form = cgi.FieldStorage()
courses = ["機械・精密システム工学科","航空宇宙工学科","情報電子工学科",
           "バイオサイエンス工学科","柔道整復学科"]
area = ["プログラミング","旅行","読書","スポーツ","映画"]
name = form["name"].value
course = courses[int(form["course"].value)]
if isinstance(form["interest"], list):
    interest = [area[int(inte.value)] for inte in form["interest"]]
else:
    interest = [area[int(form["interest"].value)]]

print("Content-Type: text/html\n")
print('<h2>以下の内容で登録します</h2>')
print('お名前:', name, '<br />')
print("学科:", course, "<br />")
print("興味:", end="")
for inte in interest:
    print(" ", inte, end="")
import requests
import os
import json
from yattag import Doc
from time import gmtime, strftime


url = "https://document-parser-api.lateral.io/"

querystring = {"url":"https://www.lemonde.fr/referendum-sur-le-brexit/article/2018/11/11/semaine-decisive-sur-le-front-du-brexit_5382103_4872498.html"}

headers = {
    'subscription-key': "55ab3caf64ff9f9edb153109aaaf0f87",
    'content-type': "application/json"
    }

response = requests.request("GET", url, headers=headers, params=querystring)

# print(response.text)

#Create a text file in current directory
file = open("output.json", 'w') 


#This allows to work with json data
j = json.loads(response.text)


# print("Title : " + j["title"])
# print("Body  : " + j["body"])


#Edit the file
file.write(response.text)

file.close()


##Generate an HTML file for the article
#Replace \n by <ln> ( for html)

title = j["title"]
bodystring = j["body"].replace("\n", "<br>")
end = "<br><br><br><em>Cet article a été généré par Web2Kindle à "+strftime("%Y-%m-%d %H:%M:%S", gmtime())+"</em>"

htmlstring = "<!DOCTYPE html>\n <html> \n <head> <meta charset=\"UTF-8\"> \n<title>"+\
			title+"</title> \n</head> \n<body> \n <h1>"+title+"</h1> \n <p>"+bodystring+end+ "</p> \n</body>"

html = open("article.html", 'w') 

html.write(htmlstring)
html.close()




import unirest
import json

while True:
	print "\n1. Buscar posts por usuario."
	print "2. Crear post con imagen."
	print "3. Salir."
	
	opcion = raw_input("\tQue desea hacer? ")
	
	if opcion == "1":	
		usuario = raw_input("Inserte el nombre del usuario para buscar sus posts: ")
		posts = unirest.get("http://localhost:4567/rest/listadoPost/" + usuario, headers={ "Accept": "application/json" })

		if posts.body:
			print "Listado post del usuario: " + usuario + "\n" + str(posts.body)
		else:
			print "No existen posts del usuario: " + usuario + "."
	elif opcion == "2":
		texto = raw_input("Inserte el texto del post: ")		
		post = unirest.post("http://localhost:4567/rest/bacanear/", headers={ "Accept": "application/json", "Content-Type": "application/json" }, params=json.dumps({ "texto": texto }))

		if post.body:
			print "Post creado con exito: " + str(post.body)
		else:
			print "Fallo al intentar crear un post con los valores: " + texto + "."
	else:
		break
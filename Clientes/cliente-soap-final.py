from zeep import Client
from zeep import xsd

cliente = Client('http://localhost:7777/ws/ServicioSOAP?wsdl')

while True:
	print "\n1. Buscar posts por usuario."
	print "2. Crear post con imagen."
	print "3. Salir."
	
	opcion = raw_input("\tQue desea hacer? ")
	if opcion == "1":	
		usuario = raw_input("Inserte el nombre del usuario para buscar sus posts: ")
		posts = cliente.service.getListadoPostPorUsuario(usuario)

		if posts:
			print "Listado post del usuario: " + usuario + "\n" + str(posts)
		else:
			print "No existen posts del usuario: " + usuario + "."
	elif opcion == "2":			
		texto = raw_input("Inserte el texto del post: ")
		post = cliente.service.crearPost(texto)

		if post:
			print "Post creado: " + str(post)
		else:
			print "Fallo al crear post."
	else:
		break
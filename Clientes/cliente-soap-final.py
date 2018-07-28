from zeep import Client
from zeep import xsd

cliente = Client('http://localhost:7777/ws/ServicioSOAP?wsdl')

while True:
		usuario = raw_input("Inserte el nombre del usuario para buscar sus posts: ")
		posts = cliente.service.getListadoPostPorUsuario(usuario)

		if posts:
			print "Listado post del usuario: " + usuario + "\n" + str(posts)
		else:
			print "No existen posts del usuario: " + usuario + "."

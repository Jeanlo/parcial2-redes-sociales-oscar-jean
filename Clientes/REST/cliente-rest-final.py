import unirest

usuario = str(input("Inserte el nombre del usuario para buscar sus posts: "))
posts = unirest.get("http://localhost:4567/rest/listadoPost/" + usuario, headers={ "Accept": "application/json" })

if posts.body:
    print "Listado post del usuario: " + usuario + "\n" + str(posts.body)
else:
    print "No existen posts del usuario: " + usuario + "."

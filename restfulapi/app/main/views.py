from flask.ext import restful
from . import main

api = restful.Api(main)

class HelloWorld(restful.Resource):
    def get(self):
        return 'Hello World'

api.add_resource(HelloWorld, '/')


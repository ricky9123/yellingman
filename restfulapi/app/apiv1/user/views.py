from flask.ext import restful
from app.apiv1 import apiv1

api = restful.Api(apiv1)

class User(restful.Resource):
    def get(self, id):
        return 'get %s' % id

    def post(self, id):
        return 'post %s' % id

    def put(self, id):
        return 'put %s' % id

    def delete(self, id):
        return 'delete %s' % id

api.add_resource(User, '/user/<id>')
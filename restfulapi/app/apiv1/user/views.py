from flask import request
from flask.ext import restful
from app.apiv1 import apiv1
from app.apiv1.users_table import create_user

api = restful.Api(apiv1)

class User(restful.Resource):
    def get(self, id):
        return request.data

    def post(self, id):
        data = request.get_json()
        password = data['password']
        name = data['name']
        # print id, password, name
        create_user(id, password, name)



    def put(self, id):
        return 'put %s' % id

    def delete(self, id):
        return 'delete %s' % id

api.add_resource(User, '/user/<id>')
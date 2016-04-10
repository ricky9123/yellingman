from app.models import db, User
from hashlib import md5


def create_user(id, password, name):
    password = md5(id+password).hexdigest()
    user_new = User(id=id, password=password, name=name)
    db.session.add(user_new)
    db.session.commit()


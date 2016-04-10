from app.models import db, User

def create_user(id, password, name):
    user_new = User(id=id, password=password, name=name)
    db.session.add(user_new)
    db.session.commit()


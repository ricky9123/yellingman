from . import db

class User(db.Model):
    __tablename__ = 'users'
    id = db.Column(db.String(16), primary_key=True)
    password = db.Column(db.String(32), nullable=False)
    name = db.Column(db.Unicode(8), nullable=False)

    def __repr__(self):
        pass


class Music(db.Model):
    __tablename__ = 'musics'
    id = db.Column(db.SmallInteger, primary_key=True)
    name = db.Column(db.Unicode(8), nullable=False)

    def __repr__(self):
        pass


class Record(db.Model):
    __tablename__ = 'records'
    id = db.Column(db.SmallInteger, primary_key=True)
    user_id = db.Column(db.String(16), db.ForeignKey('users.id'))
    music_id = db.Column(db.SmallInteger, db.ForeignKey('musics.id'))
    date = db.Column(db.DateTime, nullable=False)
    max_score = db.Column(db.SmallInteger, nullable=False)

    def __repr__(self):
        pass

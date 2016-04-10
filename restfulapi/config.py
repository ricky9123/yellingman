import os


class Config():
    SECRET_KEY = os.environ.get('SECRET_KEY') or '1dc7e39bd2210b6051f98926e0eec2e7'

    SQLALCHEMY_DATABASE_URI = os.environ.get('DATABASE_URL') or \
                              'mysql://wbc:0122@182.254.154.138:3306/yellingman'
    SQLALCHEMY_COMMIT_ON_TEARDOWN = True
    SQLALCHEMY_TRACK_MODIFICATIONS = True

    @staticmethod
    def init_app(app):
        pass

class DevelopmentConfig(Config):
    DEBUG = True
    pass


class TestingConfig(Config):
    TESTING = True
    pass


class ProductionConfig(Config):
    pass

config = {
    'development': DevelopmentConfig,
    'testing': TestingConfig,
    'production': ProductionConfig,

    'default': DevelopmentConfig,
}
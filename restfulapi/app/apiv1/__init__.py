from flask import Blueprint

apiv1 = Blueprint('apiv1', __name__)

from .user import views as user_views
from .record import views as record_views
from .music import views as music_views

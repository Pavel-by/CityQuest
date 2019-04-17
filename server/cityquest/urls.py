from django.urls import path, include

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

from rest_framework_mongoengine.routers import SimpleRouter
from cityquest.quests.views import QuestViewSet

router = SimpleRouter()
router.register(r'quests', QuestViewSet, basename = 'quests')

urlpatterns = [
    #path(r'', QuestViewSet.as_view({"get":"list"})),
    path(r'', include(router.urls))
    # Examples:
    # url(r'^$', 'cityquest.views.home', name='home'),
    # url(r'^cityquest/', include('cityquest.foo.urls')),

    # Uncomment the admin/doc line below to enable admin documentation:
    # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    # url(r'^admin/', include(admin.site.urls)),
]

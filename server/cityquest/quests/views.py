# Create your views here.
from django.template.response import TemplateResponse
from rest_framework.viewsets import ViewSet as MongoViewSet
from cityquest.quests.serializers import *
from cityquest.quests.models import Quest
from rest_framework.decorators import action
from rest_framework.response import Response 

def index_view(request):
    context = {}
    return TemplateResponce(request, 'index.html', context)

class QuestViewSet(MongoViewSet):
    @staticmethod
    def filter_queryset(**kwargs):
        return Quest.objects.filter(**{key : kwargs[key] for key in kwargs
        if kwargs[key]})
    @action(detail = False, methods = ["post"])
    def add(self, request, pk=None):
        print(str(request.data))
        ser = QuestSerializer(data = request.data)
        print(ser.is_valid())
        if ser.is_valid():
           ser.save()
           print(ser)
        return Response(str(ser.errors))
    def list(self, request):
        queryset = QuestViewSet.filter_queryset(**request.data) 
        serializer = QuestSerializer(queryset, many = True)
        return Response(serializer.data)

from rest_framework import serializers
from rest_framework_mongoengine.fields import ObjectIdField
from rest_framework_mongoengine import serializers as mongoserializers
from cityquest.quests.models import Quest
from bson import ObjectId
class QuestSerializer(mongoserializers.DocumentSerializer):
    id = ObjectIdField(read_only = False, default = ObjectId) 
    class Meta:
        model = Quest
        fields = '__all__'

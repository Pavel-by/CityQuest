from mongoengine import Document, fields


class Quest(Document):
    name = fields.StringField(unique = True, requied = True, allow_null = False)
    description = fields.StringField()
    quest_types = fields.ListField(fields.StringField())
    start = fields.GeoPointField()
    rating = fields.FloatField()
    locations = fields.ListField() #тип не определён
    tags = fields.ListField(fields.StringField())

<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.10.2" name="test-tileset" tilewidth="32" tileheight="32" tilecount="64"
         columns="8" fillmode="preserve-aspect-fit">
    <image source="../../tileset/tileset.png" width="256" height="256"/>
    <tile id="7">
        <properties>
            <property name="type" value="WALL"/>
        </properties>
        <objectgroup draworder="index" id="4">
            <object id="7" x="0" y="0" width="24" height="32"/>
        </objectgroup>
    </tile>
    <tile id="40">
        <properties>
            <property name="entity" type="bool" value="true"/>
            <property name="type" value="GROUND"/>
        </properties>
    </tile>
</tileset>

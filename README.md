Profile matcher service.

This is simple service for demonstration purposes 
It uses feign client and hsql database in server mode.

To be able to start it you should first download the hsql database from:


Here are the steps that we should follow:

1. Download HSQLDB from https://hsqldb.org/ and unzip it to a folder
2. Within the data folder,  run the following command: 
          java -cp ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:testdb --dbname.0 testdb   
The above command will start the HSQLDB server and create database whose source files will be stored in the data folder
3. Connect:  
 - Windows: from bin folder run the runManagerSwing.bat file. This will open HSQLDB Database Manager’s initial screen, where we can enter the connection credentials:

            Type: HSQL Database Engine
            URL: jdbc:hsqldb:hsql://localhost/testdb
            User: sa (System Administrator)
            Password:(empty)

 - Linux/Unix/Mac, use NetBeans, Eclipse, or IntelliJ IDEA to create the database connection through the IDE’s visual tools, using the same credentials.

            name: testdb@localhost
            driver: hsqldb (remote)
            driver version: 2.7.0
            host: localhost
 - 
4. Build - gradle clean build and Start the application from the IDE. This will create the database schema.
5. Insert fake data:

			INSERT INTO "CLANS"( "ID", "NAME" )
			VALUES ( 123456, 'Hello world clan');

			INSERT INTO "DEVICES"( "ID", "CARRIER", "FIRMWARE", "MODEL" )
			VALUES (1 , 'vodafone', '123', 'apple iphone 11');

			INSERT INTO "PROFILES"
			( "ID",
			"BIRTH_DATE",
			"COUNTRY",
			"CREATED",
			"CREDENTIAL",
			"XP",
			"GENDER",
			"LANGUAGE",
			"LAST_PURCHASE",
			"LAST_SESSION",
			"LEVEL",
			"PLAYER_ID",
			"TOTAL_PLAY_TIME",
			"TOTAL_REFUND",
			"TOTAL_SPENT",
			"TOTAL_TRANSACTIONS",
			"MODIFIED",
			"CLAN_ID" )
			VALUES (
			1 ,
			TIMESTAMP '2000-01-10 13:37:17+00:00',
			'CA',
			TIMESTAMP '2000-01-10 13:37:17+00:00',
			'apple_credential',
			1000 ,
			'male',
			'fr',
			TIMESTAMP '2000-01-10 13:37:17+00:00',
			TIMESTAMP '2000-01-10 13:37:17+00:00',
			3 ,
			'97983be2-98b7-11e7-90cf-082e5f28d836',
			144,
			0 ,
			400 ,
			5 ,
			TIMESTAMP  '2000-01-10 13:37:17+00:00',
			123456 );

			INSERT INTO "PLAYERS_INVENTORY"( "ID", "PROFILE_ID" )
			VALUES (1 ,1 );

			INSERT INTO "INVENTORY_ITEMS"( "ID", "NAME", "QUANTITY", "INVENTORY_ID" )
			VALUES ( 1, 'cash',123 ,1 );
			
			INSERT INTO "INVENTORY_ITEMS"( "ID", "NAME", "QUANTITY", "INVENTORY_ID" )
			VALUES ( 2, 'coins',123 ,1 );
			
			INSERT INTO "INVENTORY_ITEMS"( "ID", "NAME", "QUANTITY", "INVENTORY_ID" )
			VALUES ( 3, 'item_1',1 ,1 );
			
			INSERT INTO "INVENTORY_ITEMS"( "ID", "NAME", "QUANTITY", "INVENTORY_ID" )
			VALUES ( 4, 'item_34',3 ,1 );
			
			INSERT INTO "INVENTORY_ITEMS"( "ID", "NAME", "QUANTITY", "INVENTORY_ID" )
			VALUES ( 5, 'item_55',2 ,1 );
			

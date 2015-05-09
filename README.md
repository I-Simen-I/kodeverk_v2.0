# kodeverk_v2.0

# Regler
	- Kodeverksnavn skal starte med K_
 	- Første kolonne skal være en index eller en kode
 	- Headernavn kan ikke være lengre enn 30 tegn
 	- Standard kodeverk skal inneholde de syv standardfeltene: DATO_FOM, DATO_TOM, ER_GYLDIG, OPPRETTET_AV, DATO_OPPRETTET, ENDRET_AV og DATO_ENDRET
 	- Mappingkodeverk trenger ikke feltene: DATO_FOM og DATO_TOM

	Obligatoriske felter:
		- DATO_FOM
			- Date string
			- Format: dd.MM.yyyy eller yyyy-MM-dd
			- NOT NULL
		- DATO_TOM
			- Date string
			- Format: dd.MM.yyyy eller yyyy-MM-dd
			- NULLABLE
		- ER_GYLDIG
			- String
			- Gyldige verdier er 0 og 1
			- NOT NULL
		- OPPRETTET_AV
			- String
			- Max 20 tegn
			- NOT NULL
		- DATO_OPPRETTET
			- Timestamp string
			- Format: yyyy-MM-dd HH:mm eller dd.MM.yyyy HH:mm
			- NOT NULL
		- ENDRET_AV
			- String
			- Max 20 tegn
			- NOT NULL
		- DATO_ENDRET
			- Timestamp string
			- Format: yyyy-MM-dd HH:mm eller dd.MM.yyyy HH:mm
			- NOT NULL

	Andre felter:
		- DEKODE
			- Max 500 tegn
			- NULLABLE

	Datatyper:
		- Datatyper kan bli etterfulgt av tall: eks. c1, c2, d3, t4, i5
		- Det kan ikke være en tom kolonne mellom to datatyper: eks. c1, c2, , c3
		- Det er bare kolonner som har en definert datatype som skal gjøre om til SQL-verdier

		Datatypene:
			- Characters = c
			- Timestamp = t
				- Inneholder string datoer på formatet yyyy-MM-dd hh:ss
			- Date = d
				- Inneholder string datoer på formatet dd.MM.yyyy eller yyyy-MM-dd
			- Index = i
				- Kan bare inneholde tall
				- Kan ikke være mindre enn 0
				- NULLABLE
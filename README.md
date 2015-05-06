# kodeverk_v2.0

POC for å generere kodeverk

- DONE: Sjekke om excelsheeten er et gyldig kodeverk
- DONE: Timestamp og date må ha inneholde fnutter
- DONE: Tomme rader skal fjernes under konvertering
- DONE: Alle verdier som er tomme skal erstattes med null
- DONE: Endre datoformat og timestamp under konvertering slik at formatet blir likt i csvfilene
- DONE: Alle datoer som er 01.01.1900 må gjøres om til 31.12.1899 under sql generering eller ved konvertering
- DONE: Lage exceptionklasser som skal gi bruker skikkelig tilbakemelding hvis konvertering/sql generering feiler


# Regler
	- Kodeverksnavn skal starte med K_
 	- Første kolonne skal være en index eller en kode
 	- Headernavn kan ikke være lengre enn 30 tegn
 	- Kodeverk skal inneholde de syv standardfeltene: DATO_FOM, DATO_TOM, ER_GYLDIG, OPPRETTET_AV, DATO_OPPRETTET, ENDRET_AV og DATO_ENDRET

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
			- Format: yyyy-MM-dd hh:ss
			- NOT NULL
		- ENDRET_AV
			- String
			- Max 20 tegn
			- NOT NULL
		- DATO_ENDRET
			- Timestamp string
			- Format: yyyy-MM-dd hh:ss
			- NOT NULL

	Andre felter:
		- DEKODE
			- Max 500 tegn
			- NULLABLE

	Datatyper:
		- Datatyper kan bli etterfulgt av tall: eks. c1, c2, d3, t4
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
				- Starter på 1
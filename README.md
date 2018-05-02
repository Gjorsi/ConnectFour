# [Semesteroppgave 2: “Fire på rad”](https://retting.ii.uib.no/inf101.v18.sem2/blob/master/SEM-2.md)


* **README**
* [Oppgavetekst](SEM-2.md)

Dette prosjektet inneholder [Semesteroppgave 2](SEM-2.md). Du kan også [lese oppgaven online](https://retting.ii.uib.no/inf101.v18.oppgaver/inf101.v18.sem2/blob/master/SEM-2.md) (kan evt. ha små oppdateringer i oppgaveteksten som ikke er med i din private kopi).

**Innleveringsfrist:**
* Hele oppgaven skal være ferdig til **fredag 27. april kl. 2359** ([AoE](https://www.timeanddate.com/worldclock/fixedtime.html?msg=4&iso=20180427T2359&p1=3399))
* [Ekstra tips til innlevering](https://retting.ii.uib.no/inf101/inf101.v18/wikis/innlevering)

(Kryss av under her, i README.md, så kan vi følge med på om du anser deg som ferdig med ting eller ikke.)

**Utsettelse:** Hvis du trenger forlenget frist er det mulig å be om det (spør gruppeleder – evt. foreleser/assistenter hvis det er en spesiell situasjon). Hvis du ber om utsettelse bør du være i gang (ha gjort litt ting, og pushet) før fristen
   * En dag eller to går greit uten begrunnelse.
   * Eksamen er relativt tidlig i år, så vi vil helst unngå lange utsettelser.
   * Om det er spesielle grunner til at du vil trenge lengre tid, så er det bare å ta kontakt, så kan vi avtale noe. Ta også kontakt om du [trenger annen tilrettelegging](http://www.uib.no/student/49241/trenger-du-tilrettelegging-av-ditt-studiel%C3%B8p). 
   
# Fyll inn egne svar/beskrivelse/kommentarer til prosjektet under
* Levert av:   *NAVN* (*BRUKERNAVN*)
* [ ] hele semesteroppgaven er ferdig og klar til retting!
* Code review:
   * [X] jeg har fått tilbakemelding underveis fra @pda008 
   * [X] jeg har gitt tilbakemelding underveis til @pda008, 
* Sjekkliste:
   * [X] Kjørbart Fire på Rad-spill
   * [X] Forklart designvalg, hvordan koden er organisert, abstraksjon, og andre ting 
   * [ ] Tester
   * [ ] Dokumentasjon (JavaDoc, kommentarer, diagrammer, README, etc.)
   * [X] Fornuftige navn på klasser, interfaces, metoder og variabler
   * [X] Fornuftige abstraksjoner og innkapsling (bruk av klasser, interface, metoder, etc.)

## Oversikt
*(oversikt over koden din og det du har gjort)*
* ConnectFour har i sin tilstand:
** Boolean pvp: true hvis spillet er i pvp mode, false hvis player vs AI
** Boolean playersTurn: brukes i AI-mode for å hindre spiller i å utføre mer enn 1 trekk per AI-trekk, 
og brukes til å kalle AI for å utføre trekk når det er dens tur.

* AI-mode
** Det velges tilfeldig med random.nextBoolean hvem som starter.
** ConnectFour.timeStep() kaller AI for å velge sitt trekk når det er dens tur
** Når brukeren trykker på en rute i spillet kalles currentPlayer.doTurn() med gjeldende posisjon.
** Alle trekk, både fra player og AI, bruker ConnectFour.validMove() for å sjekke om kolonnen har en ledig posisjon, og få Y-verdien for første ledige.
** Etter hvert trekk kalles ConnectFourRules.hasWon() for å sjekke om trekket resulterte i at noen vant.

*pvp-mode
** Player 1 starter alltid. Brukere kan bytte på hvem som er Player 1 hvis de spiller flere runder.
** Når en bruker trykker på en rute, utføres et trekk med currentPlayer's brikke dersom det er minst en plass i kolonnen. 
** currentPlayer skifter for hvert utført trekk.
** Som i AI-mode brukes ConnectFour.validMove() i trekkene, og ConnectFourRules.hasWon() etter hvert trekk.

### Bruk
* For å starte programmet kjør: `Main.java`
* Spillet starter i "AI" mode, det er tilfeldig om player eller robot starter.
* For å skifte til "pvp" mode, velg "pvp" i menyen der det står "AI"

## Designvalg
* Jeg valgte å bruke GUI fra inf101.v18.xtra.listeners. 
* AI gjør tilfeldige gyldige trekk.

### Plan
* GUI: ~~egen implementasjon med Swing eller se på~~ xtra.listeners ~~?~~
* Interfaces: 
	- ~~IBoard (generisk)~~ Bruker MyGrid2D fra inf101.v18.xtra.listeners
	- IPlayer
	- IRobot
	- ~~IRules (?)~~ 
	- IConnectFour

* Klasser:
	- ~~Board~~ ConnectFour
	- Player
	- Robot
	- ConnectFourRules
	- SlotState

### Bruk av abstraksjon
*(hvordan du har valgt objekter/klasser for å representere ting i spillet)*
* ConnectFour.java kontrollerer spillet ved hjelp av et MyGrid2D<SlotState> board, og behandler alle click. Timestep brukes av AI for å prøve å gjøre et trekk.
* Først implementerte jeg ConnectFour uten IPlayer, IRobot, Player, Robot. Deretter gjorde jeg spiller-representasjonen om til 
IPlayer interface/Player class og AI-representasjonen til IRobot og Robot. Sånn som spillet fungerer nå er det kanskje unødvendig med IRobot som et ekstra interface,
men jeg lar det stå. Kan eventuelt legge inn mer avansert AI-funksjonalitet senere. 
* SlotState representerer "hullene" i spillbrettet. Et "hull" i spillbrettet kan være tomt, ha en gul brikke, eller en rød brikke. 

### Erfaring – hvilke valg viste seg å være gode / dårlige?
*(designerfaringer – er det noe du ville gjort annerledes?)*
* Det tok litt tid å sette seg inn i GUI'en fra xtra.listeners, og jeg hadde noe problemer med å få brukt Image / ImageLoader til å laste bilder tilhørende SlotStates,
men det ble et bra resultat til slutt.
* Om jeg hadde betydelig mer tid, skulle jeg gjerne laget alt fra bunnen av.

## Testing
*(hvordan du har testet ting)*

## Funksjonalitet, bugs
*(hva virker / virker ikke)*
* Spillet virker som forventet, både i "AI" og "pvp" mode
* Jeg har ikke tatt hensyn til hvordan spillet skalerer på høy oppløsning. Hvis spillbrettet blir veldig lite på en skjerm med høy oppløsning, 
kan ikke rutestørrelsen økes, da bildene som representerer SlotState ikke skalerer. 

## Evt. erfaring fra code review
*(lærte du noe av å gå gjennom din eller andres kode?)*
* Petter (@pda008) hadde løst oppgaven på en helt annen måte, med mye arbeid lagt ned i egen GUI. Resultatet hans ble mer "fancy", men det måtte en del kompliserte løsninger til.
* Det er vanskelig å sette seg inn i andres kode, spesielt når de ikke har mye erfaring med å skrive slik at andre skal kunne bruke det.

## Annet
*(er det noe du ville gjort annerledes?)*

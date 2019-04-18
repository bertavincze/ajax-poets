/*
    Database initialization script that runs on every web-application redeployment.
*/
DROP TABLE IF EXISTS poems CASCADE;
DROP TABLE IF EXISTS poets CASCADE;

CREATE TABLE poets (
    id smallint PRIMARY KEY,
    name character varying(20) NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE poems (
    id smallint PRIMARY KEY,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    poet_id smallint NOT NULL,
    FOREIGN KEY (poet_id) REFERENCES poets(id)
);

INSERT INTO poets VALUES(1, 'Petőfi Sándor', 'alföld'); -- 1
INSERT INTO poets VALUES(2, 'Kosztolányi Dezső', 'béka'); -- 2

INSERT INTO poems VALUES(3, 'Anyám Tyúkja', 'Ej mi a kő! tyúkanyó, kend<br>A szobában lakik itt bent?<br>Lám, csak jó az isten, jót ád,<br>Hogy fölvitte a kend dolgát!<br><br>Itt szaladgál föl és alá,<br>Még a ládára is fölszáll,<br>Eszébe jut, kotkodákol,<br>S nem verik ki a szobábol.<br><br>Dehogy verik, dehogy verik!<br>Mint a galambot etetik,<br>Válogat a kendermagban,<br>A kiskirály sem él jobban.<br><br>Ezért aztán, tyúkanyó, hát<br>Jól megbecsűlje kend magát,<br>Iparkodjék, ne legyen ám<br>Tojás szűkében az anyám. –<br><br>Morzsa kutyánk, hegyezd füled,<br>Hadd beszélek mostan veled,<br>Régi cseléd vagy a háznál,<br>Mindig emberűl szolgáltál,<br><br>Ezután is jó légy, Morzsa,<br>Kedvet ne kapj a tyúkhusra,<br>Élj a tyúkkal barátságba’...<br>Anyám egyetlen jószága.<br><br>', 1);           -- 1
INSERT INTO poems VALUES(4, 'Befordúltam a konyhára...', 'Befordúltam a konyhára,<br>Rágyujtottam a pipára...<br>Azaz rágyujtottam volna,<br>Hogyha már nem égett volna.<br><br>A pipám javában égett,<br>Nem is mentem én a végett!<br>Azért mentem, mert megláttam,<br>Hogy odabenn szép leány van.<br><br>Tüzet rakott eszemadta,<br>Lobogott is, amint rakta;<br>Jaj de hát még szeme párja,<br>Annak volt ám nagy a lángja!<br><br>Én beléptem, ő rám nézett,<br>Aligha meg nem igézett!<br>Égő pipám kialudott,<br>Alvó szívem meggyúladott.', 1);           -- 2
INSERT INTO poems VALUES(5, 'Úgy élünk együtt, két kis idegen,', 'Úgy élünk együtt, két kis idegen,<br>valahol messze, mese-szigeten.', 2);           -- 3
INSERT INTO poems VALUES(6, 'A rút varangyot véresen megöltük.', 'A rút varangyot véresen megöltük.<br><br>Ó iszonyú volt.<br>Vad háború volt.<br>A délután pokoli-sárga.<br>Nyakig a vérbe és a sárba<br>dolgoztunk, mint a hentesek,<br>s a kövér béka elesett.<br>Egész smaragd volt. Rubin a szeme,<br>gyémántot izzadt, mérgekkel tele.<br>A lába türkisz, a hasa zafir,<br>a bőre selymek fonadéka,<br>s regés kincsével elterült<br>a gazdag, undok anya-béka.<br>Botokkal nyomtuk le a földre,<br>az egyik vágta, másik ölte,<br>kivontuk a temető-partra,<br>ezer porontya megsiratta,<br>s az alkonyon, a pállott alkonyon<br>véres szemével visszanézett.<br>Kegyetlenül, meredten álltunk,<br>akár a győztes hadvezérek.<br><br>Most itt vagyunk. A tiszta kisszobában.<br>Szép harc után. A szájunk mosolyog.<br>Maró fogunk az undort elharapja,<br>s gőggel emeljük a fejünk magasra,<br>mi hóhérok, mi törpe gyilkosok.', 2);   -- 4

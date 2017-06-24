# WireWorld

"WireWorld" to implementacja automatu komórkowego „WireWorld” Briana Silvermana. Automat oblicza kolejne generacje komórek w zależności od stanu bieżących a następnie wyświetla je na planszy. 

W automacie komórkowym „WireWorld” wyrózniamy 4 możliwe stany komórki, z których każdy posiada określony kolor. Sa to:
 „Komórka Pusta” - kolor czarny,
 „Głowa elektronu” - kolor niebieski,
 „Ogon elektronu” - kolor czerwony,
 „Przewodnik” - kolor zółty.

Generowanie kolejnych stanów komórek zależy od stanów komórek w bieżącej generacji. W przypadku „WireWorld” Briana Silvermana obowiązują następujace reguły:
 komórka pozostaje Pusta, jesli była Pusta,
 komórka staje sie Ogonem elektronu, jesli była Głowa elektronu,
 komórka staje sie Przewodnkiem, jesli była Ogonem elektronu,
 komórka staje sie Głowa elektronu tylko wtedy, gdy dokładnie 1 lub 2 sasiadujace komórki sa Głowami
elektronu,
 komórka staje sie Przewodnikiem w kazdym innym wypadku.

W programie „WireWorld” stosowane jest sąsiedztwo Moore’a. Sąsiedztwo to charakteryzuje się tym, że każda komórka niestykająca się z granicami siatki posiada 8 sasiadów (w przypadku siatki skończonej komórki na krawędziach posiadają 5 sasiadów, natomiast komórki narożnikowe tylko 3 sąsiadów) znajdujących sie: na południu, na południowym zachodzie, na południowym wschodzie, na północy, na północnym zachodzie, na północnym wschodzie, na zachodzie oraz na wschodzie.

Główne funkcje programu:
 Wczytywanie do programu początkowej konfiguracji oraz zapisywanie bieżącej generacji komórek z pliku w formacie JSON
 przeprowadzenie podanej przez użytkownika liczby generacji,
 wizualizacja kolejnych generacji komórek na planszy.

Dodatkowe funkcje programu:
 Wbudowane struktury komórek - program posiada listę wbudowanych struktur komórek, które
posiadają stałą budowę. Struktury te mogą być wykorzystane do symulowania ich działania.
 Obsługa plików zawierajacych zarówno pojedyncze komórki, jak równiez gotowe struktury z określonymi
początkowymi komórkami.
 Możliwosc dodania utworzonej przez siebie struktury do listy struktur wbudowanych, w celu jej ponownego
wykorzystania w programie.
 Mozliwosc zmiany stanu pojedynczej komórki znajdującej się na planszy poprzez kliknięcie na nią.
 Mozliwosc grupowania komórek znajdujących się na planszy oraz wspólnego edytowania ich poprzez zmianę
stanów wszystkich komórek w grupie jednoczesnie.
 Opcja recznego utworzenia konfiguracji poczatkowej planszy poprzez ustawianie stanów poszczególnych
komórek na planszy.

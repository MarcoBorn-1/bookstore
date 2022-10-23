package com.example.bookstore;

import com.example.bookstore.entity.*;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CategoryRepository;
import com.example.bookstore.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class AddToDatabase implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        Customer user1 = new Customer();
        user1.setName("John");
        user1.setSurname("Adams");
        user1.setPassword(passwordEncoder.encode("password"));
        user1.setEmail("JAdams@gmail.com");
        user1.setPhoneNumber("542100997");
        user1.setCity("Warszawa");
        user1.setStreet("al. Zwycięstwa 12");
        user1.setRole(Role.USER);
        userRepository.save(user1);

        Customer admin = new Customer();
        admin.setName("Admin");
        admin.setSurname("Admin");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setEmail("admin@admin.com");
        admin.setPhoneNumber("123456789");
        admin.setCity("Kraków");
        admin.setStreet("Polna 13");
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Dramat"));
        categories.add(new Category("Fantasy"));
        categories.add(new Category("Poezja"));
        categories.add(new Category("Historia"));
        categories.add(new Category("Kryminał"));
        categories.add(new Category("Biografie"));
        categoryRepository.saveAll(categories);

        ArrayList<Author> authorList = new ArrayList<>();

        Author author1 = new Author("Adam", "Mickiewicz");
        Author author2 = new Author("Craig","Symonds");
        Author author3 = new Author("Antony","Beevor");
        Author author4 = new Author("Michał","Leszczyński");
        Author author5 = new Author("Michał","Rożek");
        Author author6 = new Author("Agata","Christie");
        Author author7 = new Author("Katarzyna","Bonda");
        Author author8 = new Author("Robert","Harris");
        Author author9 = new Author("Przemysław","Słowiński");
        Author author10 = new Author("Ewa","Czaczkowska");
        Author author11 = new Author("Marek","Rybarczyk");
        Author author12 = new Author("Wisława", "Szymborska");
        Author author13 = new Author("Stanisław", "Mrożek");
        Author author14 = new Author("William", "Shakespeare");
        Author author15 = new Author("Samuel", "Beckett");
        Author author16 = new Author("Juliusz", "Słowacki");
        Author author17 = new Author("Kerri", "Maniscalco");
        Author author18 = new Author("Wasilij", "Machanienko");
        Author author19 = new Author("Stephen", "King");

        authorList.add(author1);
        authorList.add(author2);
        authorList.add(author3);
        authorList.add(author4);
        authorList.add(author5);
        authorList.add(author6);
        authorList.add(author7);
        authorList.add(author8);
        authorList.add(author9);
        authorList.add(author10);
        authorList.add(author11);
        authorList.add(author12);
        authorList.add(author13);
        authorList.add(author14);
        authorList.add(author15);
        authorList.add(author12);
        authorList.add(author13);
        authorList.add(author14);
        authorList.add(author15);
        authorList.add(author16);
        authorList.add(author17);
        authorList.add(author18);
        authorList.add(author19);

        authorRepository.saveAll(authorList);

        ArrayList<Book> bookList = new ArrayList<>();
        Book book1 = new Book("Dziady cz.II",50, 1823,
                "DZIADY. Jest to nazwisko uroczystości obchodzona dotąd między pospólstwem w wielu powiatach Litwy, Prus i Kurlandii, " +
                        "na pamiątkę dziadów, czyli w ogólności zmarłych przodków.", author1, categories.get(0), "dziady-cz-2.jpg");


        Book book2 = new Book("Bitwa o Midway",52,2022,"Najważniejsza bitwa morska XX wieku Początek końca " +
                "W historii wojen niewiele jest momentów, w których bieg wydarzeń zmienia się tak gwałtownie i " +
                "niespodziewanie, jak podczas bitwy o Midway.",author2,categories.get(3),"bitwa-o-midway.jpg");


        Book book3 = new Book("D-Day. Bitwa o Normandię",58,2010,"6 czerwca 1944 brytyjscy i amerykańscy żołnierze" +
                " rozpoczęli inwazję na Normandię. Ten dzień przeszedł do historii jako D-Day. Przeprowadzona na" +
                " gigantyczną i bezprecedensową skalę operacja, w której wzięło udział niemal trzy miliony żołnierzy.",author3,categories.get(3),"d-day.jpg");


        Book book4 = new Book("Ostrołęka 1831", 19, 2011, "Polacy podczas wojny polsko-rosyjskiej z 1831 roku" +
                " nie dali się łatwo pokonać liczniejszemu przeciwnikowi. Potrafili wygrywać potyczki i" +
                " mniejsze bitwy, przegrywali jednak, gdy dochodziło do większych starć.",author4,
                categories.get(3),"ostroleka.jpg");


        Book book5 = new Book("Polskie insygnia koronacyjne. Symbole władzy państwowej",32,2011,
                "Książka porusza zagmatwane dzieje polskich koronacji, jak i " +
                        "tajemnicze losy naszych insygniów koronacyjnych. Autor wyjaśnia zawiłą " +
                        "symbolikę insygniów władzy.",author5,categories.get(3),
                "polskie-insygnia-koronacyjne.jpg");

        Book book6 = new Book("Przewodnik po zabytkach Krakowa",38,2021,"Kraków to miasto," +
                " które od wieków przyciąga i urzeka tłumy turystów.",
                author5,categories.get(3),"przewodnik-po-zabytkach-krakowa.jpg");


        Book book7 = new Book("Dom zbrodni",25,2021,"Kiedy grecki milioner Arystydes Leonides zostaje otruty," +
                " podejrzenia padają na Brendę, młodą wdowę. Policja wszczyna śledztwo, ale wyprzedza ją o krok" +
                " Josephine, wnuczka ofiary.",author6,categories.get(4),
                "dom-zbrodni.jpg");

        Book book8 = new Book("I nie było już nikogo",15,2012,"Dziesięć osób, każda podejrzana o morderstwo," +
                " zostaje zaproszonych przez tajemniczego gospodarza do domu na wyspie.",author6,categories.get(4),"i-nie-bylo-juz-nikogo.jpg");

        Book book9 = new Book("Zło czai się wszędzie",25,2021,"Herkules Poirot spędza wakacje w ekskluzywnym" +
                " hotelu na Wyspie Przemytników, ale nie jest mu dane spokojnie wypocząć. Kiedy ginie" +
                " piękna aktorka Arlena Stuart, detektyw może wręcz przebierać w kandydatach na mordercę.",
                author6,categories.get(4),"zlo-czai-sie-wszedzie.jpg");

        Book book10 = new Book("Zagadka Błękitnego Ekspresu",24,2022,"Amerykański milioner Rufus Van Aldin ma tylko" +
                " jedną słabość: swoją córkę Ruth. Ona natomiast ma aż dwie słabości: niebezpiecznych" +
                " mężczyzn i klejnoty. Ojcowski prezent, rubin Serce Płomienia, nie przyniesie jej jednak" +
                " szczęścia…",author6,categories.get(4),"zagadka-blekitnego-ekspresu.jpg");

        Book book11 = new Book("Zimna sprawa",26,2022,"W podziemiach zabytkowej willi policja" +
                " odkrywa zwłoki kobiety i trójki dzieci. Osobliwy sposób pochówku wskazuje na mord rytualny." +
                " Mieszkańcy tego domu od lat uznawani byli za zaginionych. Śledczy wszczynają poszukiwania" +
                " ojca zamordowanej rodziny.",author7,categories.get(4), "zimna-sprawa.jpg");

        Book book12 = new Book("Oficer i szpieg",15,2019,"Powieść osnuta wokół afery Dreyfusa," +
                " politycznego skandalu, który wstrząsnął Europą końca XIX wieku. Oficer żydowskiego pochodzenia został" +
                " oskarżony o szpiegostwo na rzecz Niemiec. Nagonka na niego zjednoczyła narodowców i prawicę.",
                author8,categories.get(4),"oficer-i-szpieg.jpg");

        Book book13 = new Book("Bracia Kliczko",32,2022,"Biografia bezkonkurencyjnych ukraińskich pięściarzy," +
                " którzy dali się poznać jako zręczni biznesmeni i filantropi, angażujący się w różne projekty" +
                " charytatywne.",author9,categories.get(5),"bracia-kliczko.jpg");

        Book book14 = new Book("Siostra Faustyna. Biografia świętej",41,2022,"Oskarżano ją" +
                " o histerię i fantazjowanie. Długo nikt nie wierzył, że ta prosta, niewykształcona dziewczyna" +
                " została wybrana przez Jezusa na sekretarkę Bożego Miłosierdzia.",author10,categories.get(5),
                "siostra-faustyna.jpg");

        Book book15 = new Book("Elżbieta II. Ostatnia taka królowa",41,2022,
                "Kiedy w 1952 roku jako młodziutka kobieta wstępowała na tron, dała umęczonym wojną" +
                        " Brytyjczykom iskierkę nadziei, że znów będą wielkim narodem.",author11,categories.get(5),
                "elzbieta-ii.jpg");

        Book book16 = new Book("Koniec i początek", 31, 2022,
                "Koniec i początek to jeden z najważniejszych tomików Wisławy Szymborskiej, który – wydany w roku 1993" +
                " – być może ostatecznie przesądził o przyznaniu poetce w trzy lata później Nagrody Nobla.",
                author12, categories.get(2), "koniec-i-poczatek.jpg");
        Book book17 = new Book("Dwukropek", 29, 2022,
                "Ulotność, przemijanie, niestabilność. Zaplątanie człowieka w historii i niejednoznaczność dziejów. " +
                "Poetka patrzy na świat uważnie, wychwytując paradoksy codzienności i podkreślając ich wyjątkowośći uniwersalność.",
                author12, categories.get(2), "dwukropek.jpg");
        Book book18 = new Book("Chwila", 31, 2022,
                "Każdy z utworów zgromadzonych w tomie Chwila to krystalicznie czysty," +
                " precyzyjny i zwarty minitraktat: filozoficzny, metafizyczny, egzystencjalny. " +
                "Szymborska mówi o sprawach najważniejszych w sposób skłaniający do odkrywczych medytacji i przemyśleń.",
                author12, categories.get(2), "chwila.jpg");
        Book book19 = new Book("Sól", 31, 2022, "Swoją uwagę kieruje poetka w stronę relacji międzyludzkich, " +
                "zwłaszcza miłości. Mimo żartobliwego tonu snuje refleksję uniwersalną. ",
                author12, categories.get(2), "sol.jpg");
        Book book20 = new Book("Tutaj", 31, 2022,
                "Zdumiewające, ile światów pomieścić można w dziewiętnastu wierszach! " +
                        "Wiersze Szymborskiej uderzają nagromadzeniem szczegółów widzialnego i " +
                        "niewidzialnego świata, który jest źródłem nieustannego zdumienia. ",
                author12, categories.get(2), "tutaj.jpg");
        Book book21 = new Book("Emigranci", 20, 2021,
                "Emigranci to najwybitniejszy utwór Sławomira Mrożka od ukazania się Tanga, " +
                        "a zarazem jeden z najważniejszych polskich dramatów okresu powojennego.",
                author13, categories.get(0), "emigranci.jpg");
        Book book22 = new Book("Sen nocy letniej", 23, 2020, "Starożytne Ateny. " +
                "Trwają przygotowania do ślubu Tezeusza i Hipolity, w które zaangażowane jest całe miasto. " +
                "Aktorzy przygotowują sztukę, by uświetnić uroczystość",
                author14, categories.get(0), "sen-nocy-letniej.jpg");
        Book book23 = new Book("Eleutheria", 40, 2021, "Eleutheria, napisana po francusku w 1947 roku, " +
                "pierwsza sztuka Samuela Becketta, przeleżała w szufladzie autora i światowych archiwach" +
                " blisko pół wieku, zanim ujrzała światło dzienne. ",
                author15, categories.get(0), "eleutheria.jpg");
        Book book24 = new Book("Kordian", 11, 2020,
                "Klasyka literatury polskiej i światowej w starannym wydaniu" +
                " z przypisami – to książka, którą warto mieć w swojej domowej biblioteczce i do której warto wracać." +
                " Tekst opatrzono wskazówkami interpretacyjnymi i hasłami ułatwiającymi nawigację po książce.",
                author16, categories.get(0), "kordian.jpg");
        Book book25 = new Book("Makbet", 11, 2020, "Klasyka literatury polskiej i światowej w starannym wydaniu" +
                " z przypisami – to książka, którą warto mieć w swojej domowej biblioteczce i" +
                " do której warto wracać.",
                author14, categories.get(0), "makbet.jpg");
        Book book26 = new Book("Królestwo nikczemnych", 30, 2022, "Bliźniaczki Vittoria i Emilia wychowały się w " +
                "Palermo pod czujnym okiem babki, sycylijskiej wiedźmy. Zawsze z amuletami na szyi. " +
                "Złote i srebrne cornicello mają je chronić przed złem. ",
                author17, categories.get(1), "krolestwo-nikczemnych.jpg");
        Book book27 = new Book("Droga Szamana. Etap 7: Na tropie stwórcy", 31, 2022,
                "Czy Barliona – ziemia obiecana" +
                " graczy RPG, raj amatorów fantastycznych przygód, źródło godziwego zarobku dla zaradnych," +
                " ale także piekło dla więźniów jest jeszcze w stanie czymś zaskoczyć Machana?",
                author18, categories.get(1), "droga-szamana-7.jpg");
        Book book28 = new Book("Droga Szamana. Etap 6: Nowy początek", 27, 2021,
                "Dmitrij Machan, " +
                "owiany legendą Szaman ze świata Barliony, odsiedział już swój wyrok w wirtualnej rzeczywistości.",
                author18, categories.get(1), "droga-szamana-6.jpg");
        Book book29 = new Book("Droga Szamana. Etap 5: Szachy Karmadonta", 27, 2021,
                "Machan pewnie myśli, że po ostatniej niespodziewanej lawinie wydarzeń jego życie w wirtualnej Barlionie powróci" +
                " w stare wyrobione koleiny.",
                author18, categories.get(1), "droga-szamana-5.jpg");
        Book book30 = new Book("Droga Szamana. Etap 4: Zamek Widmo", 33, 2021,
                "Czymże jest klan bez własnego zamku?" +
                "Machan, Szaman Najwyższy, stojący na czele Legend Barliony, " +
                "przyjmuje propozycję Imperatora – ma unicestwić armię Upiorów zamieszkującą Altamedę",
                author18, categories.get(1), "droga-szamana-4.jpg");
        Book book31 = new Book("Droga Szamana. Etap 3: Tajemnica Mrocznego Lasu", 27, 2020,
                " Machan mógłby się teraz nie wychylać i cieszyć względną wolnością do końca odsiadki." +
                " Jednak Szamana nic nie powstrzyma… Zostaje przywódcą klanu",
                author18, categories.get(1), "droga-szamana-3.jpg");
        Book book32 = new Book("Droga Szamana. Etap 2: Gambit Kartosa", 27, 2020,
                "Dmitrij Machan przeżył już wiele." +
                " Po wyroku skazującym na osiem lat ciężkich prac musi radzić sobie jako Szaman, " +
                "najmniej popularna klasa gracza; na domiar złego ma równie niepopularną specjalność Jubilera.",
                author18, categories.get(1), "droga-szamana-2.jpg");
        Book book33 = new Book("Droga Szamana. Etap 1: Początek", 24, 2020, "Główny bohater został skazany na osiem lat" +
                " więzienia za nieumyślnie popełnione przestępstwo. " +
                "W ramach kary osadzono go w jednej z barliońskich kopalni i przydzielono mu postać Szamana, " +
                "jedną z najmniej popularnych klas.",
                author18, categories.get(1), "droga-szamana-1.jpg");
        Book book34 = new Book("Podpalaczka", 31, 2022, "Charlie McGee jest dzieckiem wyjątkowym, obdarzonym nadludzkim" +
                " darem: zdolnością pirokinezy. Potrafi siłą woli rozniecić ogień.",
                author19, categories.get(1), "podpalaczka.jpg");
        Book book35 = new Book("Oczy smoka", 29, 2022, "Król nie żyje, zatruty Smoczym Piaskiem, na który nie ma lekarstwa… " +
                "Po śmierci dobrego króla Delainu, Rolanda, wybrany przez niego następca, starszy syn Peter, zostaje oskarżony o otrucie ojca",
                author19, categories.get(1), "oczy-smoka.jpg");

        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);
        bookList.add(book6);
        bookList.add(book7);
        bookList.add(book8);
        bookList.add(book9);
        bookList.add(book10);
        bookList.add(book11);
        bookList.add(book12);
        bookList.add(book13);
        bookList.add(book14);
        bookList.add(book15);
        bookList.add(book16);
        bookList.add(book17);
        bookList.add(book18);
        bookList.add(book19);
        bookList.add(book20);
        bookList.add(book21);
        bookList.add(book22);
        bookList.add(book23);
        bookList.add(book24);
        bookList.add(book25);
        bookList.add(book26);
        bookList.add(book27);
        bookList.add(book28);
        bookList.add(book29);
        bookList.add(book30);
        bookList.add(book31);
        bookList.add(book32);
        bookList.add(book33);
        bookList.add(book34);
        bookList.add(book35);

        bookRepository.saveAll(bookList);
    }
}

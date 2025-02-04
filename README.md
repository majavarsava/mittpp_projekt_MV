# MiTTPP Projekt - Testiranje JPetStore aplikacije

Repozitorij za projekt iz kolegija Metode i tehnike testiranja programske podrške.

## Prije pokretanja projekta, potrebno je:
1. Imati instaliran ```IntelliJ IDEA``` i ```Java JDK```.
   
      Dostupno na:

         http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

         https://www.jetbrains.com/idea/download/#section=windows
2. Preuzeti i instalirati ```ChromeDriver``` za pokretanje testova u Chromeu.

      Dostupno na:

         https://developer.chrome.com/docs/chromedriver/downloads/version-selection

## Koraci instaliranja projekta:

1. Klonirati ovaj repozitorij na računalo:
    ```
   https://github.com/majavarsava/mittpp_projekt_MV.git
   ```
2. Otvoriti projekt u ```IntelliJ IDEA```.
3. Instalirati potrebne dependencije putem ```pom.xml``` datoteke.
4. Pokrenuti ```testng.xml``` file kako bi se izvršili svi testovi.


Desnim klikom na ```testng.xml``` te lijevim klikom ```Run '...\testng.xml'``` će se pokrenuti svi testovi. U realnom vremenu možemo vidjeti kako se izvode testovi te kada završe vidimo jesu li bili uspješni ili ne.

## Pokretanje u drugim preglednicima

Ukoliko želimo pokrenuti testove na pretraživaču koji nije Chrome, potrebno je izmijeniti odgovarajući kod te instalirati odgovarajuće drivere:

Za pokretanje u Firefoxu:
1. Preuzimanje GeckoDriver za Firefox: 

         https://github.com/mozilla/geckodriver/releases
2. Raspakirati i smjestiti geckodriver.exe u direktorij u kojem se nalaze ostali driveri.
3. Izmijeniti kod:

```
  import org.openqa.selenium.firefox.FirefoxDriver;


  System.setProperty("webdriver.gecko.driver", "[path do geckodriver.exe]");
  WebDriver driver = new FirefoxDriver();
```


Za pokretanje u Edgeu:
1. Preuzimanje EdgeDriver za Microsoft Edge: 
         
         https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/
2. Raspakirati i smjestiti edgedriver.exe u direktorij u kojem se nalaze ostali driveri.
3. Izmijeniti kod:
```
  import org.openqa.selenium.edge.EdgeDriver;

  System.setProperty("webdriver.edgedriver.driver", "[path do edgedriver.exe]");
  WebDriver driver = new EdgeDriver();
```
Ukoliko želimo automatski odabir preglednika u jednom kodu, postavit ćemo to ovako:
```
    String browser = "firefox";  // Na primjer, odabir preglednika može biti "chrome", "firefox" ili "edge"
    WebDriver driver;
    
    if (browser.equalsIgnoreCase("chrome")) {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver.exe");
        driver = new ChromeDriver();
    } else if (browser.equalsIgnoreCase("firefox")) {
        System.setProperty("webdriver.gecko.driver", "path_to_geckodriver.exe");
        driver = new FirefoxDriver();
    } else if (browser.equalsIgnoreCase("edge")) {
        System.setProperty("webdriver.edge.driver", "path_to_edgedriver.exe");
        driver = new EdgeDriver();
    } else {
        System.out.println("Unsupported browser!");
        return;
    }`
```

    
## Detalji

Projekt se temelji na testiranju **JPetStore** aplikacije, koja simulira online trgovinu za kućne ljubimce.
Aplikacija je dostupna na linku: [JPetStore](https://petstore.octoperf.com/actions/Catalog.action)


Testovi uključuju:
- Verifikaciju funkcionalnosti prijave i registracije korisnika.
- Testiranje procesa kupovine proizvoda.
- Provjera ispravnosti prikaza proizvoda u trgovini.


Svi testovi su napisani koristeći **TestNG** framework i pokreću se putem **Selenium WebDriver**.

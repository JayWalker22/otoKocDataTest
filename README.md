OtoKoç Web Sitesi - İlan Bilgisi Toplama Test Planı
1. Testin Adı
FIAT Egea Araç İlanlarının Fiyat, Başlık ve KM Bilgilerinin Excel'e Yazdırılması Testi
2. Testin Amacı
Kullanıcı tarafından OtoKoç web sitesinde detaylı arama filtresiyle listelenen FIAT Egea marka araçlara ait ilanlardan araç başlığı (model), fiyat ve kilometre bilgilerini toplayıp, bu bilgileri bir Excel dosyasına yazdırmak.
3. Kapsam
- Web sitesi: https://www.otokoc.com.tr/
- Sadece FIAT → EGEA modelleri
- Yakıt türü: Dizel
- Vites türü: Manuel
- Performans seçimi: Var
- Excel dosyasına veri kaydı yapılır.
4. Test Araçları
Araç	Açıklama
Java	Programlama dili
Selenium WebDriver	Web sayfası etkileşimleri
TestNG	Test framework
Apache POI	Excel işlemleri
Custom ExcelUtils.java	Excel yardımcı sınıfı
Custom ReusableMethods.java	Scroll, click gibi yardımcı fonksiyonlar
5. Ön Koşullar
- Tarayıcı sürücüleri hazır ve tanımlı olmalı
- "otoKoc" adresi ConfigReader dosyasında tanımlanmalı
- Excel dosyası (otoKoc.xlsx) oluşturulabilir veya yazılabilir durumda olmalı
6. Test Adımları
1. Web sitesi açılır: https://www.otokoc.com.tr/
2. Açılışta çıkan çerez ve reklam uyarıları kapatılır
3. Detaylı Arama butonuna tıklanır
4. Marka filtresinden FIAT, model filtresinden EGEA seçilir
5. Sayfa aşağı kaydırılır, performans, dizel, manuel filtreleri seçilir
6. Ara butonuna tıklanarak sonuçlar listelenir
7. Her araç ilanının başlık, fiyat ve km bilgisi alınır
8. Excel dosyasına sırayla başlıklarla yazılır: TITLE, PRICE, KM
9. Dosya kaydedilir ve kapatılır
10. Tarayıcı kapatılır
7. Beklenen Sonuç
- Excel dosyası başarıyla oluşturulmuş ve her satırda bir aracın bilgisi olacak şekilde yazılmış olmalı.
- Başlık satırı TITLE, PRICE, KM içermeli.
- Test sonunda tarayıcı kapanmış olmalı.
8. Test Başarısı Kriterleri
- Excel'e veri başarıyla yazılır
- Tüm ilanlar eksiksiz çekilir
- Her satırda üç hücre dolu olur (title, price, km)
- Test hata almadan tamamlanır
